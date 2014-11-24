package app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.View.OnClickListener;

public abstract class BaseFragmentActivity extends FragmentActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
