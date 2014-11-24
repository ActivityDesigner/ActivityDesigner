package app.fragment;

import java.io.File;

import com.example.activitydesigner.R;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
import android.R.integer;
import android.R.layout;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.activity.ActivityInfoActivity;
import app.activity.LocationActivity;
import app.activity.ActivityTypeDetailActivity;
import app.activity.ActivityTypeDetailActivity2;
import app.activity.ContactInfoActivity;
import app.activity.MainActivity;
import app.activity.MsgInfoActivity;
import app.activity.ProfilePhotoUploadActivity;
import app.adapter.MainTypeGridviewAdapter;
import app.utils.BMapUtil;
import app.view.dialog.LoginDialog;
import app.view.dialog.RegisterDialog;
import app.view.widget.ImageCircleView;
import app.view.widget.headview.HeadView;
import app.view.widget.headview.ImageCacheManager;
import app.view.widget.headview.ImageCacheManager.CacheType;
import app.view.widget.headview.RequestManager;
import app.view.widget.headview.RingView;

public class MainFragment extends Fragment implements OnClickListener,OnItemClickListener,OnTouchListener{

	Intent mIntent; 
	ImageView mActivityInfo;    //活动信息
	ImageView mAdd;            //添加活动
	ImageView mMap;            //地图功能
	ImageView mMsgInfo; 	  //信息功能
	ImageView mContactInfo;    //联系人功能
	ImageView mSetting;       //个人信息 
	HeadView mProfile;
	Button mLogin;           //登录
	View mRegister;        //注册
	LinearLayout mTypeLayout;  
	LinearLayout mLoginLayout;
	GridView mGridView;  //显示type的内容
	Animation mTypeAnim;      //显示活动类别的动画
	Activity mActivity;
	MainTypeGridviewAdapter mAdapter; //gridview adapter
	LoginDialog mLoginDialog;
	RegisterDialog mRegisterDialog;
	LoginTask mLoginTask;
	TextView mUsername;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		mRegisterDialog = new RegisterDialog(mActivity);
		mLoginTask = new LoginTask();
		initHeadCache();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, container,false);
		mUsername = (TextView)view.findViewById(R.id.main_username);
		mLoginDialog = new LoginDialog(mActivity,mUsername);
		mActivityInfo = (ImageView)view.findViewById(R.id.main_activity_info);
		mAdd = (ImageView)view.findViewById(R.id.main_add);
		mMap = (ImageView)view.findViewById(R.id.main_map);
		mMsgInfo = (ImageView)view.findViewById(R.id.main_msg);
		mContactInfo = (ImageView)view.findViewById(R.id.main_contact);
		mProfile = (HeadView)view.findViewById(R.id.main_profile);
		mProfile.setImageUrl("http://testurl", ImageCacheManager
				.getInstance().getImageLoader());
		mProfile.setErrorImageResId(R.drawable.avatar_default);
		mProfile.setRegisted(true);
		
		mSetting = (ImageView)view.findViewById(R.id.main_setting);
		mLogin = (Button)view.findViewById(R.id.main_login);
		mLogin.getBackground().setAlpha(70);
		mRegister = view.findViewById(R.id.main_register);
		mRegister.getBackground().setAlpha(70);
		mLoginLayout = (LinearLayout)view.findViewById(R.id.main_login_layout);
		mTypeLayout = (LinearLayout)view.findViewById(R.id.main_type_layout);
		mGridView = (GridView)mTypeLayout.findViewById(R.id.main_type_gridView);
		setListener();
		mAdapter = new MainTypeGridviewAdapter(getActivity());
		mGridView.setAdapter(mAdapter);
		return view;
	}
	
	private void setListener()
	{
		mActivityInfo.setOnTouchListener(this);
		mActivityInfo.setOnClickListener(this);
		mAdd.setOnClickListener(this);
		mAdd.setOnTouchListener(this);
		mMap.setOnClickListener(this);
		mMap.setOnTouchListener(this);
		mMsgInfo.setOnClickListener(this);
		mMsgInfo.setOnTouchListener(this);
		mContactInfo.setOnClickListener(this);
		mContactInfo.setOnTouchListener(this);
		mSetting.setOnClickListener(this);
		mGridView.setOnItemClickListener(this);
		mLogin.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		mProfile.setOnClickListener(this);
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.main_activity_info:
			mActivityInfo.setImageResource(R.drawable.main_bottom_activityinfo_click);
			break;
		case R.id.main_map:
			mMap.setImageResource(R.drawable.main_bottom_map_click);
			break;
		case R.id.main_add:
			
			break;
		case R.id.main_msg:
			mMsgInfo.setImageResource(R.drawable.main_bottom_msg_click);
			break;
		case R.id.main_contact:
			mContactInfo.setImageResource(R.drawable.main_bottom_contact_click);
			break;

		default:
			break;
		}
		return false;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.main_login:
			 animate(mLoginLayout).setDuration(800);
			 animate(mLoginLayout).rotationYBy(360);
			 animate(mLoginLayout).setListener(mLoginAnimListener);
			break;
		case R.id.main_register:
			animate(mLoginLayout).setDuration(800);
			animate(mLoginLayout).rotationYBy(360);
			animate(mLoginLayout).setListener(mRegisterAnimListener);
			break;
		case R.id.main_profile:
			animate(mProfile).setDuration(1000);
			animate(mProfile).rotationYBy(180);
			animate(mProfile).setListener(mProfileAnimListener);
			break;
		case R.id.main_activity_info:
			mActivityInfo.setImageResource(R.drawable.main_bottom_activityinfo);
			mIntent = new Intent(mActivity,ActivityInfoActivity.class);
			startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.right_in, R.anim.right_out);
			break;
		case R.id.main_map:
			mMap.setImageResource(R.drawable.main_bottom_map);
			mIntent = new Intent(mActivity,LocationActivity.class);
			startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.from_up_to_bottom_in, R.anim.from_bottom_to_up_out);
			break;
			
		case R.id.main_add:
			if (mTypeLayout.getVisibility() == View.GONE) {
				
		
				mTypeAnim = AnimationUtils.loadAnimation(mActivity, R.anim.in_translate_top);
				mTypeLayout.setAnimation(mTypeAnim);
				mTypeLayout.setVisibility(View.VISIBLE);	
				mAdd.setImageResource(R.drawable.main_bottom_add_click);
				break;
			}
			if (mTypeLayout.getVisibility() == View.VISIBLE) {
				
				mTypeAnim = AnimationUtils.loadAnimation(mActivity, R.anim.from_bottom_to_up_out);
				mTypeLayout.setAnimation(mTypeAnim);
				mTypeLayout.setVisibility(View.GONE);	
				mAdd.setImageResource(R.drawable.main_bottom_add);
				break;
			}	
			break;
		case R.id.main_msg:
			mMsgInfo.setImageResource(R.drawable.main_bottom_msg);
			mIntent = new Intent(mActivity,MsgInfoActivity.class);
			startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.from_up_to_bottom_in,R.anim.from_bottom_to_up_out);
			break;
		case R.id.main_contact:
			mContactInfo.setImageResource(R.drawable.main_bottom_contact);
			mIntent = new Intent(mActivity,ContactInfoActivity.class);
			startActivity(mIntent);
			mActivity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
			break;
		case R.id.main_setting:
			mSetting.setSelected(true);
			MainActivity mainActivity = (MainActivity)getActivity();
			mainActivity.toggle();
			break;
		default:
			break;
		}
	}

	
	public static int dp2Px(Context context, float dp) {
		return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//获得设置profile的图片
		mProfile.setHeadBitmap(ImageCacheManager.getInstance().getBitmap("http://testurl"));
		Log.d("tag", "success");
		//出现裁剪框
		/**if (resultCode == Activity.RESULT_OK) {
			if (data != null) {
			String realPath = data.getStringExtra("PROFILE_PHOTO_PATH");
			Bitmap bmp = BitmapFactory.decodeFile(realPath);
	 		int degree = BMapUtil.readPictureDegree(realPath);
	 		if(degree <= 0){
	 			ImageCacheManager.getInstance().putBitmap("http://testurl", bmp);
	 			mProfile.setHeadBitmap(bmp);
	 		}else{
	 			Log.e("tag", "rotate:"+degree);
	 			//创建操作图片是用的matrix对象
	 	 		Matrix matrix=new Matrix();
	 	 		//旋转图片动作
	 	 		matrix.postRotate(degree);
	 	 		//创建新图片
	 	 		Bitmap resizedBitmap=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
	 	 		ImageCacheManager.getInstance().putBitmap("http://testurl", resizedBitmap);
	 	 		mProfile.setHeadBitmap(resizedBitmap);
	 	 		}
		  }
		}
		else if (resultCode == Activity.RESULT_CANCELED) {
			//mRingView.setVisibility(View.GONE);
		}**/
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		switch (pos) {
		case 0:
			//约会
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",0);
			startActivity(mIntent);
			break;
		case 1:
			//运动
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",1);
			startActivity(mIntent);
			break;
		case 2:
			//餐饮
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",2);
			startActivity(mIntent);
			break;
		case 3:
			//会议
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",3);
			startActivity(mIntent);
			break;
		case 4:
			//游戏
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",4);
			startActivity(mIntent);
			break;
		case 5:
			//看电影
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",5);
			startActivity(mIntent);
			break;
		case 6:
			//购物
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",6);
			startActivity(mIntent);
			break;
		case 7:
			//学习
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",7);
			startActivity(mIntent);
			break;
		case 8:
			//音乐活动
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",8);
			startActivity(mIntent);
			break;
		case 9:
			//旅游
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",9);
			startActivity(mIntent);
			break;
		case 10:
			//工作
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",10);
			startActivity(mIntent);
			break;
		case 11:
			//自定义活动
			mIntent = new Intent(getActivity(),ActivityTypeDetailActivity2.class);
			mIntent.putExtra("TYPE_ID",11);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}
	
	AnimatorListener mLoginAnimListener = new AnimatorListener() {
		
		@Override
		public void onAnimationStart(Animator arg0) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void onAnimationRepeat(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animator arg0) {
			// TODO Auto-generated method stub
			mLoginDialog.show();
		}
		
		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub
		}
	};
	
	AnimatorListener mRegisterAnimListener = new AnimatorListener() {
		
		@Override
		public void onAnimationStart(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationRepeat(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animator arg0) {
			// TODO Auto-generated method stub
			mRegisterDialog.show();
		}
		
		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	AnimatorListener mProfileAnimListener = new AnimatorListener() {
		
		@Override
		public void onAnimationStart(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationRepeat(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animator arg0) {
			// TODO Auto-generated method stub
			mIntent = new Intent(mActivity,ProfilePhotoUploadActivity.class);
			int PROFILE_PHOTO_UPLOAD_CODE = 100;
			startActivityForResult(mIntent, PROFILE_PHOTO_UPLOAD_CODE);
		}
		
		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	/** 头像缓存 */
	private static int DISK_IMAGECACHE_SIZE = 1024 * 1024 * 1; // 1M
	private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.JPEG;
	private static int DISK_IMAGECACHE_QUALITY = 100; // PNG is lossless so
														// quality is ignored
														// but must be provi
	
	/**
	 * Intialize the request manager and the image cache
	 */
	private void initHeadCache() {
		RequestManager.init(mActivity);
		createImageCache();
	}
	
	/**
	 * Create the image cache. Uses Memory Cache by default. Change to Disk for
	 * a Disk based LRU implementation.
	 */
	private void createImageCache() {
		ImageCacheManager.getInstance().init(mActivity, mActivity.getPackageCodePath(),
				DISK_IMAGECACHE_SIZE, DISK_IMAGECACHE_COMPRESS_FORMAT,
				DISK_IMAGECACHE_QUALITY, CacheType.DISK);
	}
	
	class LoginTask extends AsyncTask<Bundle,integer,integer>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected integer doInBackground(Bundle... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}
