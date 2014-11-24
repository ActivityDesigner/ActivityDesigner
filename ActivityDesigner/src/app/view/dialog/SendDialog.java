package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SendDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mBack;
	Button mSure;
	TextView mContent;
	AddRemindDialog mRemindDialog;
	SendMsgDialog mSendMsgDialog;
	public SendDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		mRemindDialog = new AddRemindDialog(mContext);
		mSendMsgDialog = new SendMsgDialog(mContext);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_send);
		mSure  = (Button)findViewById(R.id.send_dialog_sure);
		mSure.setOnClickListener(this);
		mContent = (TextView)findViewById(R.id.send_dialog_content);
		mContent.setText("是否向未安装此app的活动联系人发送活动通知短信？");
		mBack  = (Button)findViewById(R.id.send_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.send_dialog_back:
			this.dismiss();
			mRemindDialog.show();
			break;
		case R.id.send_dialog_sure:
			//打开发送短信页面
			mSendMsgDialog.show();
			this.dismiss();
			break;
		default:
			break;
		}
	}
}
