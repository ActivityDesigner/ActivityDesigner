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
	EditText mSportAdvice2;
	Spinner mSportSpinner2;
	EditText mSportAdvice3;
	Spinner mSportSpinner3;
	EditText mSportAdvice4;
	Spinner mSportSpinner4;
	EditText mSportAdvice5;
	Spinner mSportSpinner5;
	/**餐饮活动**/
	CheckBox mDietBallot;
	CheckBox mDietAlbum;
	CheckBox mDietChat;
	Spinner mDietTypeSpinner;
	EditText mDietTypeEdit;
	Spinner mDietAdviceSpinner;
	EditText mDietAdviceEdit;
	CheckBox mDietDrink;
	/**会议活动**/
	
	/**消费活动**/
	
	/**工作活动**/
	
	/**运动活动**/
	
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
		mSportBallot = (CheckBox)view.findViewById(R.id.type_detail_sport_ballot);
		
		views.add(view);
	}
	
	private void dietViewInitial(){
		views = new ArrayList<View>();
		View view;
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		view = View.inflate(this,R.layout.layout_activity_type_detail_diet,null);
		views.add(view);
	}
	
	private void meetingViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		views.add(View.inflate(this, R.layout.layout_activity_type_detail_meeting,null));
	}
	
	private void consumptionViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		views.add(View.inflate(this, R.layout.layout_activity_type_detail_consumption,null));
	}
	
	private void workViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		views.add(View.inflate(this, R.layout.layout_activity_type_detail_work,null));
	}
	private void DIYViewInitial(){
		views = new ArrayList<View>();
		views.add(View.inflate(this,R.layout.layout_activity_type_detail_1, null));
		views.add(View.inflate(this, R.layout.layout_activity_type_detail_diy,null));
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
