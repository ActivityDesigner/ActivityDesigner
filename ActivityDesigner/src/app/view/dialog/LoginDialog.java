package app.view.dialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.activity.MainActivity;
import app.constant.CommonConst;
import app.constant.UrlConst;
import app.fragment.MainFragment;
import app.model.DataResultModel;
import app.utils.HttpUtil;

/**
 * @see MainFragment
 * µÇÂ¼¿ò
 * @author W.Z.L
 *
 */
public class LoginDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mSure;
	Button mBack;
	EditText mUser;
	EditText mPass;
	String username;
	String password;
	LoginTask mTask;
	LoadingDialog mLoadingDialog;
	Bundle mBundle;
	boolean flag;
	DataResultModel resultModel;
	TextView mUsername;
	public LoginDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		flag = true;
		resultModel = new DataResultModel();
	}
	
	public LoginDialog(Context context,TextView name) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		flag = true;
		resultModel = new DataResultModel();
		mUsername = name;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_login);
		mLoadingDialog = new LoadingDialog(mContext);
		mSure = (Button)findViewById(R.id.login_sure);
		mBack = (Button)findViewById(R.id.login_back);
		mUser = (EditText)findViewById(R.id.login_username);
		mPass = (EditText)findViewById(R.id.login_password);
		mSure.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.login_sure:
			username = mUser.getText().toString();
			password = mPass.getText().toString();
			if (flag) {
				mTask = new LoginTask();
				mBundle = new Bundle();
				mBundle.putString("username", username);
				mBundle.putString("password", password);
				mTask.execute(mBundle);	
			}
			break;
		case R.id.login_back:
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
		View view;
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
			String username = mBundle.getString("username");
			String password = mBundle.getString("password");
			//resultModel = HttpUtil.login(username, password);
			//Log.e("resultModel,success: ", resultModel.success);
			if (true) {
				return 1;	
			}else {
				return 0;
			}
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
				//CommonConst.current_id = resultModel.user_id;
				mUsername.setText("textss");
				LoginDialog.this.dismiss();
	
			}
			else
			{
			    
				Toast.makeText(mContext, CommonConst.LOGIN_FAIL, Toast.LENGTH_SHORT).show();
				LoginDialog.this.dismiss();
			}
		}
		
	}

	
}
