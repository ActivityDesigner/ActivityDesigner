package app.view.dialog;

import java.util.Calendar;

import com.example.activitydesigner.R;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import app.activity.AddRemindActivity;
import app.activity.AlarmActivity;
import app.constant.NewActivityConst;

public class AddRemindDetailDialog extends Dialog implements OnClickListener{

	TimePicker mPicker;
	Context mContext;
	Button mBack;
	Button mSure;
	Integer hour;
	Integer minute;
	View alarm;
	boolean isSuccess = false;
	public AddRemindDetailDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_add_remind_detail);
		mPicker = (TimePicker)findViewById(R.id.addreminddetail_dialog_timePicker);
		mPicker.setIs24HourView(true);
		mSure  = (Button)findViewById(R.id.addreminddetail_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.addreminddetail_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.addreminddetail_dialog_back:
			this.dismiss();
			break;
		case R.id.addreminddetail_dialog_sure:
			hour = mPicker.getCurrentHour();
			minute = mPicker.getCurrentMinute();
			alarm.setBackgroundResource(R.drawable.ic_alarm);
			setAlarm();
			this.dismiss();
			break;
		default:
			break;
		}
	}
	
	public void getView(View view)
	{
		alarm = view;
	}
	
	public void getDate(java.util.Date date){
		
	}
	public boolean isSuccess(){
		return isSuccess;
	}
	
	public Integer getHour(){
		return hour;
	}
	public Integer getMinute(){
		return minute;
	}
	private void setAlarm()
	{
	    Intent intent = new Intent(mContext, AlarmActivity.class);
		PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Service.ALARM_SERVICE);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.HOUR, hour);
		c.set(Calendar.MINUTE, minute);
		alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
		Log.d("setAlarm", "success");
	}
}
