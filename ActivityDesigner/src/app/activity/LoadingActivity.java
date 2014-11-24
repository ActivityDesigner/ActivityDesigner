package app.activity;

import com.example.activitydesigner.R;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import app.listener.OnViewChangeListener;
import app.utils.AppUtil;
import app.utils.SharedPreferencesUtil;
import app.view.widget.MyScrolLayout;

/**
 * 第一次安装引导
 * @author W.Z.L
 *
 */
public class LoadingActivity extends BaseActivity implements OnViewChangeListener{

	private MyScrolLayout mScrolLayout;
	private RelativeLayout mGo;
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_loading);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mScrolLayout = (MyScrolLayout) findViewById(R.id.loading_scrollayout);
		mGo = (RelativeLayout)findViewById(R.id.loading_go);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mScrolLayout.SetOnViewChangeListener(this);
		mGo.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.loading_go:
			startActivity(new Intent(getApplicationContext(),
					MainActivity.class));
			SharedPreferencesUtil
			.setValue(getBaseContext(),"isAlreadyInit"
							+AppUtil.getAppVersionCode(getBaseContext()),true);
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		
	}

}
