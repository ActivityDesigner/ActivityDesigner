package app.view.dialog;

import java.util.List;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import app.constant.MsgConst;
import app.constant.NewActivityConst;

public class SendMsgDialog extends Dialog implements OnItemSelectedListener,OnClickListener{

	Context mContext;
	Button mBack;
	Button mSure;
	Spinner mSpinner;
	String[] mData;
	String mString;
	EditText mEditText;
	SendMsgTask mTask;
	ProgressDialog mProgress;	//发送中。。。
	AddRemindDialog mRemindDialog;
	public SendMsgDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		mData = new String[]{"标准版", "严肃版","卖萌版","红色版","标准版2"};
		mTask = new SendMsgTask();
		mRemindDialog = new AddRemindDialog(mContext);
		mProgress = new ProgressDialog(mContext);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_send_msg);
		mSpinner = (Spinner)findViewById(R.id.sendmsg_dialog_spinner);
		mSpinner.setAdapter(new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1,mData));
		mSpinner.setOnItemSelectedListener(this);
		mEditText = (EditText)findViewById(R.id.sendmsg_dialog_content);
		mEditText.setText("选择短信");
		mSure  = (Button)findViewById(R.id.sendmsg_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.sendmsg_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sendmsg_dialog_back:
			this.dismiss();
			mRemindDialog.show();
			break;
		case R.id.sendmsg_dialog_sure:
			this.dismiss();
			//发送短信
			String mContent = mEditText.getText().toString();
			mTask.execute(mContent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {
		case 0:
			MsgConst.dataInitial(1);
			mString = MsgConst.getType(1);
			break;
		case 1:
			MsgConst.dataInitial(2);
			mString = MsgConst.getType(2);
			break;
		case 2:
			MsgConst.dataInitial(3);
			mString = MsgConst.getType(3);
			break;
		case 3:
			MsgConst.dataInitial(4);
			mString = MsgConst.getType(4);
			break;
		case 4:
			MsgConst.dataInitial(5);
			mString = MsgConst.getType(5);
			break;

		default:
			break;
		}
		mEditText.setText(mString);
		//mString = arg0.getItemAtPosition(arg2).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class SendMsgTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgress.setMessage("正在发送");
			mProgress.show();
		}
		
		@Override
		protected Integer doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String temp = arg0[0];
			List<String> phoneNumList;
			phoneNumList = NewActivityConst.phoneNumList;
	    	for(int i = 0; i != phoneNumList.size();++i)
	    	{
	    		SmsManager smsManager = SmsManager.getDefault();
	    	    smsManager.sendTextMessage(phoneNumList.get(i), null, temp, null, null);
	    	    Log.d("SendMsg"," "+phoneNumList.get(i)+" "+temp+" "+ temp);
	    	}
	    	
			return 1;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result == 1) {
				mProgress.dismiss();
				Toast.makeText(mContext, "发送成功", Toast.LENGTH_SHORT).show();
			}
			mRemindDialog.show();
		}
	}
}
