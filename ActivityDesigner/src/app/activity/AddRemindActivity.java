package app.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.activitydesigner.R;
import com.example.activitydesigner.R.drawable;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.R.integer;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.view.dialog.AddRemindDetailDialog;
import app.view.dialog.RemindDetailDialog;

/**
 * 活动提醒
 * @author W.Z.L
 *
 */
public class AddRemindActivity extends FragmentActivity implements OnClickListener{

	private boolean undo = false;
	private CaldroidFragment caldroidFragment;
	ImageView mBack;
	ImageView mSure;
	AddRemindDetailDialog mAddRemindDetailDialog;
	RemindDetailDialog mRemindDetailDialog; 
	Integer hour;
	Integer minute;
	Intent mIntent;
	Map<Integer,List<Integer>> mSelected ; //一个是月，一个是日
	Calendar mCalendar = Calendar.getInstance();
	RemindDetailDialog mDetailDialog;
	private void setCustomResourceForDates() {
		List<Integer> tmp = new ArrayList<Integer>();
		List<Integer> days = new ArrayList<Integer>();
		tmp.add(30);
		mSelected.put(10,tmp); //当前日期加1天 我定了10月30日作为提醒日期
		Calendar cal = Calendar.getInstance();

		// Min date is last 7 days
		cal.add(Calendar.DATE, -18);
		Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 16);
		Date greenDate = cal.getTime();
		//从这里获得实例
		
		if (mSelected.containsKey(10)) {
			days = mSelected.get(10);
		}
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE,1);
		//cal.add(Calendar.DATE, days.get(0));
		Log.e("AddRemindActivity days", String.valueOf(days.get(0)));
		Date selectedDate = cal.getTime();
		Log.e("selecedDate", 
				"date: "+String.valueOf(selectedDate.getDate())+" day:"
						+String.valueOf(selectedDate.getDay())
						+"month: "+String.valueOf(selectedDate.getMonth())
						+"year: "+String.valueOf(selectedDate.getYear()));
		Log.e("cal:", "time: "+String.valueOf(cal.getTime())+
				"first: "+String.valueOf(cal.getFirstDayOfWeek()));
		Log.e("Calendar", "Date: "+String.valueOf(Calendar.DATE)+
						"DAY_OF_MONTH： "+String.valueOf(Calendar.DAY_OF_MONTH)+
						"Year: "+String.valueOf(Calendar.YEAR)+
						"Calendar.DAY_OF_WEEK_IN_MONTH:"+String.valueOf(Calendar.DAY_OF_WEEK_IN_MONTH));
		if (caldroidFragment != null) {
			caldroidFragment.setBackgroundResourceForDate(R.color.Blue,
					blueDate);
			caldroidFragment.setBackgroundResourceForDate(R.color.Green,
					greenDate);
			caldroidFragment.setBackgroundResourceForDate(R.drawable.ic_alarm,selectedDate);
			caldroidFragment.setTextColorForDate(R.color.White, blueDate);
			caldroidFragment.setTextColorForDate(R.color.White, greenDate);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_remind);
		mSelected = new TreeMap<Integer,List<Integer>>();
		mAddRemindDetailDialog = new AddRemindDetailDialog(this);
		mRemindDetailDialog = new RemindDetailDialog(this);
		mDetailDialog = new RemindDetailDialog(this);
		mBack = (ImageView)findViewById(R.id.add_remind_back);
		mBack.setOnClickListener(this);
		mSure = (ImageView)findViewById(R.id.add_remind_sure);
		mSure.setOnClickListener(this);
		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		// Setup caldroid fragment
		// **** If you want normal CaldroidFragment, use below line ****
		caldroidFragment = new CaldroidFragment();

		// //////////////////////////////////////////////////////////////////////
		// **** This is to show customized fragment. If you want customized
		// version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

		// Setup arguments

		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
			// Uncomment this to customize startDayOfWeek
			// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
			// CaldroidFragment.TUESDAY); // Tuesday
			caldroidFragment.setArguments(args);
		}

		setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		// Setup listener
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Toast.makeText(getApplicationContext(), formatter.format(date),
						Toast.LENGTH_SHORT).show();
				Log.e("AddRemindActivity","month: "+String.valueOf(date.getMonth()+1)+
					  " date: "+String.valueOf(date.getDate()));
				if (mSelected.containsKey(date.getMonth()+1)) {
					
					if (mSelected.containsValue(date.getDate())) {
						mRemindDetailDialog.show(); 
					}
					else {
						mAddRemindDetailDialog.getDate(date);
						mAddRemindDetailDialog.getView(view);
						mAddRemindDetailDialog.show();
						//在已选中的部分添加该日期
					}
				}
				else {
					mAddRemindDetailDialog.getDate(date);
					mAddRemindDetailDialog.getView(view);
					mAddRemindDetailDialog.show(); 
					//在已选中的部分添加该日期
				}
				//设置提醒
			}

			@Override
			public void onChangeMonth(int month, int year) {
				String text = "month: " + month + " year: " + year;
				Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLongClickDate(Date date, View view) {
			
			  if (view.getBackground() == getResources().getDrawable(R.drawable.ic_alarm)) {
					//说明此时有提醒
					//显示提醒详情
					Toast.makeText(getApplicationContext(),
							"Remind" + formatter.format(date),
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onCaldroidViewCreated() {
				if (caldroidFragment.getLeftArrowButton() != null) {
					Toast.makeText(getApplicationContext(),
							"Caldroid view is created", Toast.LENGTH_SHORT)
							.show();
				}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);

		final TextView textView = (TextView) findViewById(R.id.textview);

		final Button customizeButton = (Button) findViewById(R.id.customize_button);

		// Customize the calendar
		customizeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (undo) {
					customizeButton.setText("customize");
					textView.setText("");

					// Reset calendar
					caldroidFragment.clearDisableDates();
					caldroidFragment.clearSelectedDates();
					caldroidFragment.setMinDate(null);
					caldroidFragment.setMaxDate(null);
					caldroidFragment.setShowNavigationArrows(true);
					caldroidFragment.setEnableSwipe(true);
					caldroidFragment.refreshView();
					undo = false;
					return;
				}

				// Else
				undo = true;
				customizeButton.setText("Undo");
				Calendar cal = Calendar.getInstance();

				// Min date is last 7 days
				cal.add(Calendar.DATE, -7);
				Date minDate = cal.getTime();

				// Max date is next 7 days
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 14);
				Date maxDate = cal.getTime();

				// Set selected dates
				// From Date
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 2);
				Date fromDate = cal.getTime();

				// To Date
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 3);
				Date toDate = cal.getTime();

				// Set disabled dates
				ArrayList<Date> disabledDates = new ArrayList<Date>();
				for (int i = 5; i < 8; i++) {
					cal = Calendar.getInstance();
					cal.add(Calendar.DATE, i);
					disabledDates.add(cal.getTime());
				}

				// Customize
				caldroidFragment.setMinDate(minDate);
				caldroidFragment.setMaxDate(maxDate);
				caldroidFragment.setDisableDates(disabledDates);
				caldroidFragment.setSelectedDates(fromDate, toDate);
				caldroidFragment.setShowNavigationArrows(false);
				caldroidFragment.setEnableSwipe(false);

				caldroidFragment.refreshView();

				// Move to date
				// cal = Calendar.getInstance();
				// cal.add(Calendar.MONTH, 12);
				// caldroidFragment.moveToDate(cal.getTime());

				String text = "Today: " + formatter.format(new Date()) + "\n";
				text += "Min Date: " + formatter.format(minDate) + "\n";
				text += "Max Date: " + formatter.format(maxDate) + "\n";
				text += "Select From Date: " + formatter.format(fromDate)
						+ "\n";
				text += "Select To Date: " + formatter.format(toDate) + "\n";
				for (Date date : disabledDates) {
					text += "Disabled Date: " + formatter.format(date) + "\n";
				}

				textView.setText(text);
			}
		});
		
	}
	
	private void setAlarm(){
		
		//指定启动AlarmActivity组件
		Intent intent = new Intent(AddRemindActivity.this, AlarmActivity.class);
		//创建PendingIntent对象
		/*
		 * PendingIntent与Intent的区别是PendingIntent处理即将发生的事情
		 * 比如：在通知栏Notification中跳转页面，不是立即跳转
		 * 通常通过  getActivity、getBroadcast、getService得到PendingIntent的实例
		 * 
		 */
		int hourOfDay = 2;
		int minute = 12;
		PendingIntent pi = PendingIntent.getActivity(AddRemindActivity.this, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.HOUR, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.add_remind_sure:
			mIntent = new Intent(AddRemindActivity.this,MainActivity.class);
			startActivity(mIntent);
			this.finish();
			break;
		case R.id.add_remind_back:
			mIntent = new Intent(AddRemindActivity.this,MainActivity.class);
			startActivity(mIntent);
			this.finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mIntent = new Intent(AddRemindActivity.this,MainActivity.class);
		startActivity(mIntent);
		this.finish();
	}
}
