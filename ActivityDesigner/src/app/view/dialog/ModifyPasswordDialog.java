package app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import app.constant.CommonConst;
import app.model.DataResultModel;
import app.utils.HttpUtil;
import app.view.dialog.LoginDialog.LoginTask;

import com.example.activitydesigner.R;

/**
 * ÐÞ¸ÄÃÜÂë
 * @author W.Z.L
 *
 */
public class ModifyPasswordDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mSure;
	Button mBack;
	EditText mPassOriginal;
	EditText mPassNew;
	String password_original;
	String password_new;
	LoginTask mTask;
	LoadingDialog mLoadingDialog;
	Bundle mBundle;
	boolean flag;
	DataResultModel resultModel;
	
	public ModifyPasswordDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		flag = true;
		resultModel = new DataResultModel();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_modify_password);
		mLoadingDialog = new LoadingDialog(mContext);
		mSure = (Button)findViewById(R.id.modify_password_sure);
		mBack = (Button)findViewById(R.id.modify_password_back);
		mPassOriginal = (EditText)findViewById(R.id.modify_password_origin);
		mPassNew = (EditText)findViewById(R.id.modify_password_new);
		mSure.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.modify_password_sure:
			password_original = mPassOriginal.getText().toString();
			password_new = mPassNew.getText().toString();
			if (flag) {
				mTask = new LoginTask();
				mBundle = new Bundle();
				mBundle.putString("password_original", password_original);
				mBundle.putString("password_new", password_new);
				mTask.execute(mBundle);	
			}
			break;
		case R.id.modify_password_back:
			this.dismiss();
			break;
		default:
			break;
		}
	}
	
	
	class LoginTask extends AsyncTask<Bundle, Integer, Integer>
	{
		String userTmp;
		String passTmp;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mLoadingDialog.show();
			flag = false;
		}
		
		@Override
		protected Integer doInBackground(Bundle... arg0) 
		{
			Bundle mBundle = arg0[0];
			String pass1 = mBundle.getString("password_original");
			String pass2 = mBundle.getString("password_new");
			resultModel = HttpUtil.modifyPassword(pass1, pass2);
			return 1;
	    }

		@Override
		protected void onPostExecute(Integer result) 
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingDialog.dismiss();
			flag = true;
			if (result == 1)
			{
				Toast.makeText(mContext, CommonConst.LOGIN_SUCCESS, Toast.LENGTH_SHORT).show();
			}
			else
			{
			    
				Toast.makeText(mContext, CommonConst.LOGIN_FAIL, Toast.LENGTH_SHORT).show();
			}
		}
		
	}
}
