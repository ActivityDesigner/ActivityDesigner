package app.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.CalendarView.OnDateChangeListener;

import com.example.activitydesigner.R;
import com.roomorama.caldroid.CaldroidFragment;

public class ActivityDateDialog extends Dialog implements OnClickListener,OnDateChangeListener{

	Context mContext;
	Button mBack;
	Button mSure;
	CalendarView mCalendarView;
	int month;
	int day;
	View mView;
	public ActivityDateDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_activity_date);
		mCalendarView = (CalendarView)findViewById(R.id.activitydate_dialog_calendar);
		mCalendarView.setOnDateChangeListener(this);
		mSure  = (Button)findViewById(R.id.activitydate_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.activitydate_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activitydate_dialog_back:
			this.dismiss();
			break;
		case R.id.activitydate_dialog_sure:
			if (mView != null) {
				((EditText)mView).setText(String.valueOf(month)+"."+String.valueOf(day));	
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
	@Override
	public void onSelectedDayChange(CalendarView view,
			int year, int month, int dayOfMonth) {
		// TODO Auto-generated method stub
		this.month = month+1;//£¿
		this.day = dayOfMonth;
	}
}
