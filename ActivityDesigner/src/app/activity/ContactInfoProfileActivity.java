package app.activity;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class ContactInfoProfileActivity extends Activity{

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);	
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_contact_info_profile);
			//requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
			//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);   //全屏显示
			//overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);

	   }
		@Override
		public boolean onTouchEvent(MotionEvent event){
			finish();
			return true;
		}
		
    
}
