package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import app.activity.ActivityTypeDetailActivity2;
import app.activity.AddRemindActivity;
import app.activity.MainActivity;

public class AddRemindDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mBack;
	Button mSure;
	Intent mIntent;
	public AddRemindDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_dialog_add_remind);
		setTitle(" «∑ÒÃÌº”Ã·–—£ø");
		mSure  = (Button)findViewById(R.id.add_remind_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.add_remind_dialog_back);
		mBack.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.add_remind_dialog_sure:
			mIntent = new Intent(mContext,AddRemindActivity.class);
			mContext.startActivity(mIntent);
			break;
		case R.id.add_remind_dialog_back:
			mIntent = new Intent(mContext,MainActivity.class);
			mContext.startActivity(mIntent);
			this.dismiss();
			break;
		default:
			break;
		}
	}
}
