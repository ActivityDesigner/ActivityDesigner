package app.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.example.activitydesigner.R;
import com.example.activitydesigner.R.string;

import android.R.integer;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import app.constant.NewActivityConst;
import app.constant.TypeConst;
import app.db.MainDB;
import app.model.ActivityModel;
import app.model.ContactModel;
import app.model.DataResultModel;
import app.utils.HttpUtil;
import app.view.dialog.ActivityDateDialog;
import app.view.dialog.ActivityNameDialog;
import app.view.dialog.ActivityNoteDialog;
import app.view.dialog.ActivityTimeDialog;
import app.view.dialog.LoadingDialog;
import app.view.dialog.SendDialog;

public class ActivityTypeDetailActivity2 extends BaseActivity {

	private ImageView ivOne;
	private ImageView ivTwo;
	private Interpolator accelerator = new AccelerateInterpolator();
	private Interpolator decelerator = new DecelerateInterpolator();
	private ViewPager vpMain;
	private List<View> views;
	TextView mTypeName;
	EditText mNameText;
	EditText mDateText;
	EditText mTimeText;
	EditText mLocationText;
	EditText mContactText;
	EditText mNoteText;
	Button mNameButton;
	Button mDateButton;
	Button mTimeButton;
	Button mLocationButton;
	Button mContactButton;
	Button mNoteButton;
	
	ActivityNameDialog nameDialog;
	ActivityDateDialog dateDialog;
	ActivityTimeDialog timeDialog;
	ActivityNoteDialog noteDialog;
	SendDialog sendDialog;
	Intent mIntent;
	ImageView mSure;
	ImageView mBack;
	int type;
	String typeStr;
	ActivityModel mModel;
	SQLiteDatabase mSQLiteDataBase;
	
	Spinner mDrawer;
	Spinner mPay;
	EditText mDrawereEditText;
	EditText mPayEditText;
	
	AddActivityTask mTask;
	LoadingDialog mLoadingDialog;
	boolean hasFinished;
	Bundle params;
	DataResultModel mResultModel;
	

	/**运动活动**/
	CheckBox mSportBallot; //允许投票
	CheckBox mSportAlbum; //允许相册
	CheckBox mSportChat;  //允许聊天
	EditText mSportAdvice1; //活动建议1输入框
	Spinner mSportSpinner1; //活动建议1下拉框
	EditText mSportAdvice2;	//活动建议2输入框
	Spinner mSportSpinner2;	//活动建议2下拉框
	EditText mSportAdvice3;	//活动建议3输入框
	Spinner mSportSpinner3;	//活动建议3下拉框
	EditText mSportAdvice4;	//活动建议4输入框
	Spinner mSportSpinner4;	//活动建议4下拉框
	EditText mSportAdvice5;	//活动建议5输入框
	Spinner mSportSpinner5;	//活动建议5下拉框
	/**餐饮活动**/
	CheckBox mDietBallot;	//允许投票
	CheckBox mDietAlbum;	//新建相册
	CheckBox mDietChat;	//允许聊天
	Spinner mDietTypeSpinner;	//聚餐类型下拉
	EditText mDietTypeEdit;		//聚餐类型输入框
	Spinner mDietAdviceSpinner;	//买单建议下拉框
	EditText mDietAdviceEdit;	//买单建议输入框
	CheckBox mDietDrink;	//是否饮酒
	/**会议活动**/
	CheckBox mMeetBallot;	//允许投票
	CheckBox mMeetAlbum;	//新建相册
	CheckBox mMeetChat;	//允许聊天
	EditText mMeetlengthEdit;	//会议时长输入框
	Spinner mMeetlengthSpinner;	//会议时长下拉框
	Button mMeetSpeaker1;	//选择主题1演讲人
	Button mMeetSpeaker2;	//选择主题2演讲人
	Button mMeetSpeaker3;	//选择主题3演讲人
	Button mMeetSpeaker4;	//选择主题4演讲人
	/**消费活动**/
	CheckBox mConsumptionBallot;	//允许投票
	CheckBox mConsumptionAlbum;	//新建相册
	CheckBox mConsumptionChat;	//允许聊天
	Spinner mConsumptionAdviceSpinner;	//买单建议下拉框
	EditText mConsumptionAdviceEdit;	//买单建议输入框
	EditText mConsumptionLocation1Edit;	//推荐地点1输入框
	Button mConsumptionLocation1Btn;	//推荐地点1查看
	EditText mConsumptionLocation2Edit;	//推荐地点2输入框
	Button mConsumptionLocation2Btn;	//推荐地点2查看
	EditText mConsumptionLocation3Edit;	//推荐地点3输入框
	Button mConsumptionLocation3Btn;	//推荐地点3查看
	EditText mConsumptionLocation4Edit;	//推荐地点4输入框
	Button mConsumptionLocation4Btn;	//推荐地点4查看
	
	/**工作活动**/
	CheckBox mWorkBallot;	//允许投票
	CheckBox mWorkAlbum;	//新建相册
	CheckBox mWorkChat;	//允许聊天
	Button mWorkNewtask;	//新建任务
	//Button mWorkTask1Button;	//任务1
	//Button mWorkTask2Button;	//任务2
	//Button mWorkTask3Button;	//任务3
	//Button mWorkTask4Button;	//任务4
	/**自定义活动**/
	CheckBox mDIYBallot;	//允许投票
	CheckBox mDIYAlbum;	//新建相册
	CheckBox mDIYChat;	//允许聊天
	EditText mDIY1Edit;	//自定义1输入框
	Button mDIY1Button;	//	自定义1查看
	EditText mDIY2Edit;	//自定义2输入框
	Button mDIY2Button;	//	自定义2查看
	EditText mDIY3Edit;	//自定义3输入框
	Button mDIY3Button;	//	自定义3查看
	EditText mDIY4Edit;	//自定义4输入框
	Button mDIY4Button;	//	自定义4查看
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		NewActivityConst.clearData();
		type = getIntent().getIntExtra("TYPE_ID",5);
		hasFinished = true;
		mLoadingDialog = new LoadingDialog(this);
		nameDialog = new ActivityNameDialog(this,type);
		dateDialog = new ActivityDateDialog(this);
		timeDialog = new ActivityTimeDialog(this);
		noteDialog = new ActivityNoteDialog(this);
		sendDialog = new SendDialog(this);
		setContentView(R.layout.activity_activity_type_detail_2);
		typeStr = TypeConst.getTypeName(type);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mResultModel = new DataResultModel();
		ivOne = (ImageView)findViewById(R.id.iv_one);
		ivTwo = (ImageView)findViewById(R.id.iv_two);
		vpMain = (ViewPager)findViewById(R.id.vp_main);
		mTypeName = (TextView)findViewById(R.id.activity_type_detail_typename);
		mTypeName.setText(typeStr);
		mSure = (ImageView)findViewById(R.id.activity_type_detail2_sure);
		mBack = (ImageView)findViewById(R.id.activity_type_detail2_back);
		switch (type) {
		case 0:
			sportViewInitial();
			break;
		case 1:
			dietViewInitial();
			break;
		case 2:
			meetingViewInitial();
			break;
		case 3:
			consumptionViewInitial();
			break;
		case 4:
			workViewInitial();
			break;
		case 5:
			DIYViewInitial();
			break;
		default:
			DIYViewInitial();
			break;
		}
		
		MyAdapter adapter = new MyAdapter();
		MyListener listener = new MyListener();
		vpMain.setAdapter(adapter);
		vpMain.setOnPageChangeListener(listener);
		View view0 = views.get(0);
		mNameText = (EditText)view0.findViewById(R.id.type_detail_name_edit);
		mDateText = (EditText)view0.findViewById(R.id.type_detail_date_edit);
		mTimeText = (EditText)view0.findViewById(R.id.type_detail_time_edit);
		mLocationText = (EditText)view0.findViewById(R.id.type_detail_location_edit);
		mContactText = (EditText)view0.findViewById(R.id.type_detail_contact_edit);
		mNoteText = (EditText)view0.findViewById(R.id.type_detail_note_edit);
		mNameButton = (Button)view0.findViewById(R.id.type_detail_name_look);
		mDateButton = (Button)view0.findViewById(R.id.type_detail_date_look);
		mTimeButton = (Button)view0.findViewById(R.id.type_detail_time_look);
		mLocationButton = (Button)view0.findViewById(R.id.type_detail_location_look);
		mContactButton = (Button)view0.findViewById(R.id.type_detail_contact_look);
		mNoteButton = (Button)view0.findViewById(R.id.type_detail_note_look);
	}

	private void sportViewInitial(){
		views = new ArrayList<View>();
		View view;
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		view = View.inflate(this,R.layout.layout_activity_type_detail_sport,null);
		mSportBallot = (CheckBox)view.findViewById(R.id.type_detail_sport_ballot);	//允许投票
		mSportChat = (CheckBox)view.findViewById(R.id.type_detail_sport_chat);	//允许聊天
		mSportAlbum = (CheckBox)view.findViewById(R.id.type_detail_sport_album);	//新建相册
		
		mSportAdvice1 = (EditText)view.findViewById(R.id.type_detail_sport_advice_1);
		mSportSpinner1 = (Spinner)view.findViewById(R.id.type_detail_sport_spinner_1);
		mSportAdvice2 = (EditText)view.findViewById(R.id.type_detail_sport_advice_2);
		mSportSpinner2 = (Spinner)view.findViewById(R.id.type_detail_sport_spinner_2);
		mSportAdvice3 = (EditText)view.findViewById(R.id.type_detail_sport_advice_3);
		mSportSpinner3 = (Spinner)view.findViewById(R.id.type_detail_sport_spinner_3);
		mSportAdvice4 = (EditText)view.findViewById(R.id.type_detail_sport_advice_4);
		mSportSpinner4 = (Spinner)view.findViewById(R.id.type_detail_sport_spinner_4);
		mSportAdvice5 = (EditText)view.findViewById(R.id.type_detail_sport_advice_5);
		mSportSpinner5 = (Spinner)view.findViewById(R.id.type_detail_sport_spinner_5);
		
		views.add(view);
	}
	
	private void dietViewInitial(){
		views = new ArrayList<View>();
		View view;
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		view = View.inflate(this,R.layout.layout_activity_type_detail_diet,null);
		
		mDietBallot = (CheckBox)view.findViewById(R.id.type_detail_diet_ballot);		//允许投票
		mDietChat = (CheckBox)view.findViewById(R.id.type_detail_diet_chat);	//允许聊天
		mDietAlbum = (CheckBox)view.findViewById(R.id.type_detail_diet_album);	//新建相册
		
		mDietTypeEdit = (EditText)view.findViewById(R.id.type_detail_diettype_advice);	//聚餐类型输入框
		mDietTypeSpinner = (Spinner)view.findViewById(R.id.type_detail_diettype_spinner);	//聚餐类型下拉框
		mDietAdviceEdit = (EditText)view.findViewById(R.id.type_detail_dietbuy_advice);	//买单建议输入框
		mDietAdviceSpinner = (Spinner)view.findViewById(R.id.type_detail_dietbuy_spinner);	//买单建议下拉框
		mDietDrink = (CheckBox)view.findViewById(R.id.type_detail_dietdrink_checkbox);	//是否饮酒
		
		views.add(view);
	}
	
	private void meetingViewInitial(){
		views = new ArrayList<View>();
		View view;
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		view = View.inflate(this, R.layout.layout_activity_type_detail_meeting,null);
		
		mMeetBallot = (CheckBox)view.findViewById(R.id.type_detail_meet_ballot);	//允许投票
		mMeetChat = (CheckBox)view.findViewById(R.id.type_detail_meet_chat);		//允许聊天
		mMeetAlbum = (CheckBox)view.findViewById(R.id.type_detail_meet_album);	//新建相册
		
		mMeetlengthEdit = (EditText)view.findViewById(R.id.type_detail_meettime_edit); //会议时长输入框
		mMeetlengthSpinner = (Spinner)view.findViewById(R.id.type_detail_meettime_spinner);	//会议时长下拉框
		mMeetSpeaker1 = (Button)view.findViewById(R.id.type_detail_meetspeaker1_button);	//选择主题1演讲人
		mMeetSpeaker2 = (Button)view.findViewById(R.id.type_detail_meetspeaker2_button);	//选择主题2演讲人
		mMeetSpeaker3 = (Button)view.findViewById(R.id.type_detail_meetspeaker3_button);	//选择主题3演讲人
		mMeetSpeaker4 = (Button)view.findViewById(R.id.type_detail_meetspeaker4_button);	//选择主题4演讲人
		
		views.add(view);
	}
	
	private void consumptionViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		View view;
		view = View.inflate(this, R.layout.layout_activity_type_detail_consumption,null);
		
		mConsumptionBallot = (CheckBox)view.findViewById(R.id.type_detail_consumption_ballot);	//允许投票
		mConsumptionChat = (CheckBox)view.findViewById(R.id.type_detail_consumption_chat);	//允许聊天
		mConsumptionAlbum = (CheckBox)view.findViewById(R.id.type_detail_consumption_album);	//新建相册
		
		mConsumptionAdviceEdit = (EditText)view.findViewById(R.id.type_detail_consumption_advice_edit);	//买单建议输入框
		mConsumptionAdviceSpinner = (Spinner)view.findViewById(R.id.type_detail_consumption_advice_spinner);		//买单建议下拉框
		mConsumptionLocation1Edit = (EditText)view.findViewById(R.id.type_detail_consumption_location1_edit);	//推荐地点输入框
		mConsumptionLocation1Btn = (Button)view.findViewById(R.id.type_detail_consumption_location1_button);	//查看推荐地点
		mConsumptionLocation2Edit = (EditText)view.findViewById(R.id.type_detail_consumption_location2_edit);	//推荐地点输入框
		mConsumptionLocation2Btn = (Button)view.findViewById(R.id.type_detail_consumption_location2_button);	//查看推荐地点
		mConsumptionLocation3Edit = (EditText)view.findViewById(R.id.type_detail_consumption_location3_edit);	//推荐地点输入框
		mConsumptionLocation3Btn = (Button)view.findViewById(R.id.type_detail_consumption_location3_button);	//查看推荐地点
		mConsumptionLocation4Edit = (EditText)view.findViewById(R.id.type_detail_consumption_location4_edit);	//推荐地点输入框
		mConsumptionLocation4Btn = (Button)view.findViewById(R.id.type_detail_consumption_location4_button);	//查看推荐地点
		
		views.add(view);
	}
	
	private void workViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		View view;
		view=View.inflate(this, R.layout.layout_activity_type_detail_work,null);
		
		mWorkBallot = (CheckBox)view.findViewById(R.id.type_detail_work_ballot);	//允许投票
		mWorkChat = (CheckBox)view.findViewById(R.id.type_detail_work_chat);	//允许聊天
		mWorkAlbum = (CheckBox)view.findViewById(R.id.type_detail_work_album);	//新建相册
		
		mWorkNewtask = (Button)view.findViewById(R.id.type_detail_work_addtask_button);	//新建任务
		//mWorkTask1Button = (Button)view.findViewById(R.id.type_detail_work_task1_button);	
		
		views.add(view);
	}
	private void DIYViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		View view;
		view = View.inflate(this, R.layout.layout_activity_type_detail_diy,null);
		
		mDIYBallot = (CheckBox)view.findViewById(R.id.type_detail_diy_ballot);	//允许投票
		mDIYChat = (CheckBox)view.findViewById(R.id.type_detail_diy_chat);	//允许聊天
		mDIYAlbum = (CheckBox)view.findViewById(R.id.type_detail_diy_album);	//新建相册
		
		mDIY1Edit = (EditText)view.findViewById(R.id.type_detail_diy1_edit);	//自定义1输入框
		mDIY1Button = (Button)view.findViewById(R.id.type_detail_diy1_look);	//自定义1查看
		mDIY2Edit = (EditText)view.findViewById(R.id.type_detail_diy2_edit);	//自定义2输入框
		mDIY2Button = (Button)view.findViewById(R.id.type_detail_diy2_look);	//自定义2查看
		mDIY3Edit = (EditText)view.findViewById(R.id.type_detail_diy3_edit);	//自定义3输入框
		mDIY3Button = (Button)view.findViewById(R.id.type_detail_diy3_look);	//自定义3查看
		mDIY4Edit = (EditText)view.findViewById(R.id.type_detail_diy4_edit);	//自定义4输入框
		mDIY4Button = (Button)view.findViewById(R.id.type_detail_diy4_look);	//自定义4查看
		
		views.add(view);
	}
	
	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mSure.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mNameButton.setOnClickListener(this);
		mDateButton.setOnClickListener(this);
		mTimeButton.setOnClickListener(this);
		mLocationButton.setOnClickListener(this);
		mContactButton.setOnClickListener(this);
		mNoteButton.setOnClickListener(this);
		/**运动**/
		/**餐饮**/
		/**会议**/
		mMeetSpeaker1.setOnClickListener(this);
		mMeetSpeaker2.setOnClickListener(this);
		mMeetSpeaker3.setOnClickListener(this);
		mMeetSpeaker4.setOnClickListener(this);
		/**消费**/
		mConsumptionLocation1Btn.setOnClickListener(this);
		mConsumptionLocation2Btn.setOnClickListener(this);
		mConsumptionLocation3Btn.setOnClickListener(this);
		mConsumptionLocation4Btn.setOnClickListener(this);
		/**工作**/
		mWorkNewtask.setOnClickListener(this);
		/**自定义**/
		mDIY1Button.setOnClickListener(this);
		mDIY2Button.setOnClickListener(this);
		mDIY3Button.setOnClickListener(this);
		mDIY4Button.setOnClickListener(this);
		
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activity_type_detail2_sure:
			params = new Bundle();
			mModel = new ActivityModel();
			mModel.setState(0);
			mModel.setType(type);
			mModel.setName(mNameText.getText().toString());
			mModel.setDate(mDateText.getText().toString());
			//mModel.setTimeStart(mTimeText.getText().toString());
			//mModel.setLocation(mLocationText.getText().toString());
			//mModel.setContact(NewActivityConst.Contact);
			mModel.setNote(mNoteText.getText().toString());
			params.putSerializable("model", mModel);
			NewActivityConst.mLastModel = mModel;
			if (hasFinished) {
				mTask = new AddActivityTask();
				mTask.execute(params);
			}
			ContentValues cv = new ContentValues();
			cv.put("name", mNameText.getText().toString());
			cv.put("date", mDateText.getText().toString());
			cv.put("time", mTimeText.getText().toString());
			cv.put("location", mLocationText.getText().toString());
			cv.put("contact", mNameText.getText().toString());
			cv.put("note", NewActivityConst.getContactName());
			mSQLiteDataBase.insert("ActivityDesigner", null, cv);
			NewActivityConst.addActivityInfo(mModel);
			//NewActivityConst.newActivity.add(mModel);
			//是否发送信息到未安装此app的联系人--->进入短信模板选择界面
			//是否在日历中提醒提醒--->进入日历界面
			break;
		case R.id.activity_type_detail2_back:
			this.finish();
			break; 
		case R.id.type_detail_name_look:
			//nameDialog = new ActivityNameDialog(getBaseContext());
			nameDialog.setView(mNameText);
			nameDialog.show();
			break;
		case R.id.type_detail_date_look:
			dateDialog.setView(mDateText);
			dateDialog.show();
			break;
		case R.id.type_detail_time_look:
			timeDialog.setView(mTimeText);
			timeDialog.show();
			break;
		case R.id.type_detail_location_look:
			mIntent = new Intent(this,ActivityLocationActivity.class);
			int LOCATION_SELECT_CODE = 200;
			startActivityForResult(mIntent,LOCATION_SELECT_CODE);
			break;
		case R.id.type_detail_contact_look:
			mIntent = new Intent(this,SelectContactActivity.class);
			//startActivity(mIntent);
			int CONTACT_SELECT_CODE = 100;
			startActivityForResult(mIntent,CONTACT_SELECT_CODE);
			break;
		case R.id.type_detail_note_look:
			noteDialog.setView(mNoteText);
			noteDialog.show();
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100) {
			//得到选择联系人数据
			mContactText.setText(data.getStringExtra("CONTACT_NAME"));	
		}if (requestCode == 200) {
			//得到地图数据
			Log.d("得到地图数据", "200");
			mLocationText.setText(data.getStringExtra("LOCATION_NAME"));
		}
		
	}
	
	private void showDialog(String str) {
		new AlertDialog.Builder(this).setMessage(str)
				.show();
	}
	
	/**
	 * 
	 * 
	 * @param one
	 * @param two
	 */
	private void flipit(View one, View two) {
		final View visible;
		final View invisible;
		if (one.getVisibility() == View.GONE) {
			visible = two;
			invisible = one;

		} else {
			invisible = two;
			visible = one;

		}
		ObjectAnimator visToInvis = ObjectAnimator.ofFloat(visible,
				"rotationY", 0f, 90f);
		visToInvis.setDuration(500);
		visToInvis.setInterpolator(accelerator);
		final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(invisible,
				"rotationY", -90f, 0f);
		invisToVis.setDuration(500);
		invisToVis.setInterpolator(decelerator);
		visToInvis.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator anim) {
				visible.setVisibility(View.GONE);
				invisToVis.start();
				invisible.setVisibility(View.VISIBLE);
			}
		});
		visToInvis.start();
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

	}

	class MyListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			flipit(ivOne, ivTwo);
		}

	}
	class DrawerListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			mDrawereEditText.setText(arg0.getItemAtPosition(arg2).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class PayListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			mDrawereEditText.setText(arg0.getItemAtPosition(arg2).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class AddActivityTask extends AsyncTask<Bundle, Integer, Integer>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			hasFinished = false;
			mLoadingDialog.show();
		}
		
		@Override
		protected Integer doInBackground(Bundle... arg0) {
			// TODO Auto-generated method stub
			Bundle data = arg0[0];
			ActivityModel model = (ActivityModel)data.get("model");
			try {
				mResultModel = HttpUtil.addActivity(model);
				if (mResultModel.success.equals("true")) 
				{
					return 1;
				}
				else {
					return 0;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingDialog.dismiss();
			if (result == 1) {
				hasFinished = true;
				Toast.makeText(getBaseContext(),"创建活动成功", Toast.LENGTH_SHORT).show();
				sendDialog.show();
			}else {
				hasFinished = true;
				Toast.makeText(getBaseContext(),"创建活动失败", Toast.LENGTH_LONG).show();
			}
		}
	}
}
