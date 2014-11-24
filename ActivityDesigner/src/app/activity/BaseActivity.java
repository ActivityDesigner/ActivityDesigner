package app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.View.OnClickListener;
import app.view.dialog.LoadingDialog;

public abstract class BaseActivity extends Activity implements OnClickListener{

	LoadingDialog mLoadingDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mLoadingDialog = new LoadingDialog(this);
		setView();
		initView();
		setListener();
		setProcess();
	}
	
	/**
	 * 设置布局文件
	 */
	public abstract void setView();
	
	/**
	 * 初始化布局文件中的控件
	 */
	public abstract void initView();
	
	/**
	 * 设置控件的监听
	 */
	public abstract void setListener();
	
	/**
	 * 设置其他
	 */
	public abstract void setProcess();
}
