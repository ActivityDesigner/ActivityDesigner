package app.activity;

import java.util.ArrayList;

import com.example.activitydesigner.R;
import com.example.activitydesigner.R.layout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import app.constant.NewActivityConst;
import app.db.MainDB;
import app.fragment.MainFragment;
import app.fragment.PersonalInfoFragment;
import app.model.ActivityModel;
import app.utils.ContactUtil;

/**
 * ������
 * @author W.Z.L
 *
 */
public class MainActivity extends SlidingActivity{


	Fragment newFragment;
	

	SQLiteDatabase mSQLiteDataBase;
	public static int WIN_WIDTH;
	public static int WIN_HEIGHT;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				//WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		initWindowHW();
		MyInitial();
	   //MainDB.openDataBase("ActivityDesigner", this,mSQLiteDataBase);
		//ContactUtil.getAllNameAndPhone(this);
	}
	 
	private void initWindowHW() {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metric);
		WIN_WIDTH = metric.widthPixels; // ��Ļ��ȣ����أ�
		WIN_HEIGHT = metric.heightPixels; // ��Ļ�߶ȣ����أ�
	}
	
	/**
     * ��Դ��ʼ��
     */
    private void MyInitial()
    {	
    	newFragment = new  MainFragment();
    	getFragmentManager().
    	beginTransaction().
    	replace(R.id.content, newFragment).
    	commit();
        
        // set the Behind View
        setBehindContentView(R.layout.fragment_personal_info);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment personalInfoFragment = new PersonalInfoFragment();
        fragmentTransaction.replace(R.id.personal_info_fragment,personalInfoFragment);
        
       // fragmentTransaction.replace(R.id.content, new set("Welcome"),"Welcome");
        fragmentTransaction.commit();

        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
        sm.setShadowDrawable(R.drawable.shadow);
      //setBehindOffset()Ϊ����SlidingMenu�򿪺��ұ����µĿ�ȡ����԰����ֵд��dimens����ȥ:60dp
        sm.setBehindOffset(60);
        sm.setFadeDegree(0.35f);
        //����slding menu�ļ�������ģʽ
        //TOUCHMODE_FULLSCREEN ȫ��ģʽ����contentҳ���У����������Դ�sliding menu
        //TOUCHMODE_MARGIN ��Եģʽ����contentҳ���У�������slding ,����Ҫ����Ļ��Ե�����ſ��Դ�slding menu
        //TOUCHMODE_NONE ��Ȼ�ǲ���ͨ�����ƴ���
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

      //ʹ�����Ϸ�icon�ɵ㣬������onOptionsItemSelected����ſ��Լ�����R.id.home
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
       
    }
	
	/**
	 *    ����fragment����л�
	 */
	public void switchContent(Fragment fragment) {
		newFragment = fragment;
		getFragmentManager()
		.beginTransaction()
		.replace(R.id.content,fragment)
		.commit();
		getSlidingMenu().showContent();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setIcon(R.drawable.my_launcher);
		builder.setTitle("��ȷ��Ҫ�뿪��");
		builder.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						// ������ӵ��ȷ������߼�
						showDialog("��ѡ����ȷ��");
					}
				});
		builder.setNegativeButton("ȡ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						// ������ӵ��ȷ������߼�
						showDialog("��ѡ����ȡ��");
					}
				});
	}
	
	private void showDialog(String str) {
		new AlertDialog.Builder(MainActivity.this).setMessage(str)
				.show();
	}

}
