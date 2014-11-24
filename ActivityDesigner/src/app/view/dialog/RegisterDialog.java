package app.view.dialog;

import java.util.TreeMap;

import org.apache.http.protocol.HTTP;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import app.model.DataResultModel;
import app.utils.HttpUtil;

public class RegisterDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mBack;
	Button mSure;
	EditText mUser;
	EditText mPass;
	DataResultModel resultModel;
	LoadingDialog mLoadingDialog;
	RegisterTask mTask;
	boolean flag;
	public RegisterDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mLoadingDialog = new LoadingDialog(mContext);
		flag = true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_dialog_register);
		mBack = (Button)findViewById(R.id.register_back);
		mBack.setOnClickListener(this);
		mSure = (Button)findViewById(R.id.register_sure);
		mSure.setOnClickListener(this);
		mUser = (EditText)findViewById(R.id.register_username);
		mPass = (EditText)findViewById(R.id.register_password);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.register_back:
			this.dismiss();
			break;
		case R.id.register_sure:
			String userTmp = mUser.getText().toString();
			String passTmp = mPass.getText().toString();
			if (flag) {
				mTask = new RegisterTask();
				Bundle mBundle = new Bundle();
				mBundle.putString("username", userTmp);
				mBundle.putString("password", passTmp);
				mTask.execute(mBundle);	
			}
			break;
		default:
			break;
		}
	}
	
	class RegisterTask extends AsyncTask<Bundle, Integer, Integer>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mLoadingDialog.show();
			flag = false;
		}
		
		@Override
		protected Integer doInBackground(Bundle... arg0) {
			// TODO Auto-generated method stub
			String username = arg0[0].getString("username");
			String password = arg0[0].getString("password");
			resultModel = HttpUtil.register(username, password);
			resultModel.printLog();
			return 1;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingDialog.dismiss();
			flag = true;
		}
	}
}
