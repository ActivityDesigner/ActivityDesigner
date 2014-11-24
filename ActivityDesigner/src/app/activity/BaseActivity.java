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
	 * ���ò����ļ�
	 */
	public abstract void setView();
	
	/**
	 * ��ʼ�������ļ��еĿؼ�
	 */
	public abstract void initView();
	
	/**
	 * ���ÿؼ��ļ���
	 */
	public abstract void setListener();
	
	/**
	 * ��������
	 */
	public abstract void setProcess();
}
