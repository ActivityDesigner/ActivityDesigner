package app.activity;


import com.example.activitydesigner.R;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import app.utils.AppUtil;
import app.utils.SharedPreferencesUtil;
import app.utils.ShortcutUtil;

/**
 * 
 * @author W.Z.L
 *
 */
public class WelcomeActivity extends BaseActivity{

	WelcomeTask mTask;
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_welcome);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		mTask = new WelcomeTask();
		mTask.execute("excute");
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class WelcomeTask extends AsyncTask<String,integer,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mHandler.sendEmptyMessage(1);
			return null;
		}
		
	}
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			boolean isAlreadyInit = SharedPreferencesUtil.getBooleanValue(
					getApplicationContext(),
					"isAlreadyInit"
							+ AppUtil.getAppVersionCode(WelcomeActivity.this));
			if (!isAlreadyInit) {
				ShortcutUtil.createShortCut(WelcomeActivity.this,
						R.drawable.my_launcher, R.string.app_name);
				startActivity(new Intent(getApplicationContext(),
						LoadingActivity.class));
				SharedPreferencesUtil
						.setValue(
								WelcomeActivity.this,
								"isAlreadyInit"
										+ AppUtil.getAppVersionCode(WelcomeActivity.this),
								true);
			} else {
				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
			}
			finish();
		}
	};
}
