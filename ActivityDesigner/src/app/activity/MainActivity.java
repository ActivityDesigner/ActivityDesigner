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
 * 主界面
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
		WIN_WIDTH = metric.widthPixels; // 屏幕宽度（像素）
		WIN_HEIGHT = metric.heightPixels; // 屏幕高度（像素）
	}
	
	/**
     * 资源初始化
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
      //setBehindOffset()为设置SlidingMenu打开后，右边留下的宽度。可以把这个值写在dimens里面去:60dp
        sm.setBehindOffset(60);
        sm.setFadeDegree(0.35f);
        //设置slding menu的几种手势模式
        //TOUCHMODE_FULLSCREEN 全屏模式，在content页面中，滑动，可以打开sliding menu
        //TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开slding ,你需要在屏幕边缘滑动才可以打开slding menu
        //TOUCHMODE_NONE 自然是不能通过手势打开啦
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

      //使用左上方icon可点，这样在onOptionsItemSelected里面才可以监听到R.id.home
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
       
    }
	
	/**
	 *    各个fragment间的切换
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
		builder.setTitle("你确定要离开吗？");
		builder.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						// 这里添加点击确定后的逻辑
						showDialog("你选择了确定");
					}
				});
		builder.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton) {
						// 这里添加点击确定后的逻辑
						showDialog("你选择了取消");
					}
				});
	}
	
	private void showDialog(String str) {
		new AlertDialog.Builder(MainActivity.this).setMessage(str)
				.show();
	}

}
