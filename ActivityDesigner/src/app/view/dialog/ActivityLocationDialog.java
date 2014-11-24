package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import app.activity.ActivityTypeDetailActivity2;

public class ActivityLocationDialog extends Dialog implements OnClickListener{

	Context mContext;
	String mString = " ";
	Button mBack;
	Button mSure;
	EditText mEditText;
	TextView mTextView;
	public ActivityLocationDialog(Context context){
		super(context);
		mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_dialog_activity_location);
		mSure  = (Button)findViewById(R.id.activitylocation_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.activitylocation_dialog_back);
		mBack.setOnClickListener(this);
		mEditText  = (EditText)findViewById(R.id.activitylocation_dialog_content);
	}
	
	public void setContent(String content){
		this.mString = content;
		mEditText.setText(mString);
	}
	public void setView(TextView mParent){
		mTextView = mParent;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activitylocation_dialog_back:
			this.dismiss();
			break;
		case R.id.activitylocation_dialog_sure:
			mTextView.setText(mString);
			this.dismiss();	
			break;
		default:
			break;
		}
	}
}
