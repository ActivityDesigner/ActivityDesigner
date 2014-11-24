package app.view.dialog;

import com.example.activitydesigner.R;

import android.R.integer;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import app.constant.NewActivityConst;

public class ActivityTimeDialog extends Dialog implements OnClickListener{

	TimePicker mPicker;
	Context mContext;
	Button mBack;
	Button mSure;
	Integer hour;
	Integer minute;
	View mView;
	public ActivityTimeDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_activity_time);
		mPicker = (TimePicker)findViewById(R.id.activitytime_dialog_timePicker);
		mPicker.setIs24HourView(true);
		mSure  = (Button)findViewById(R.id.activitytime_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.activitytime_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activitytime_dialog_back:
			this.dismiss();
			break;
		case R.id.activitytime_dialog_sure:
			hour = mPicker.getCurrentHour();
			minute = mPicker.getCurrentMinute();
			//NewActivityConst.START_TIME = String.valueOf(hour)+"£º"+String.valueOf(minute);
			if (mView != null) {
				((EditText)mView).setText(String.valueOf(hour)+"£º"+String.valueOf(minute));
			}
			this.dismiss();
			break;
		default:
			break;
		}
	}
	
	public void setView(View view){
		mView = view;
	}
}
