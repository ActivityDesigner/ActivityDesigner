package app.activity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.activitydesigner.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.adapter.ActivityNameSpinnerAdapter;
import app.adapter.CustomArrayAdapter;
import app.adapter.MainTypeGridviewAdapter;
import app.adapter.TypeDetailArrayAdapter;
import app.constant.NewActivityConst;
import app.item.ExpandableListItem;
import app.model.ActivityModel;
import app.view.dialog.ActivityNameDialog;
import app.view.dialog.ActivityTimeDialog;
import app.view.widget.ExpandingListView;

/**
 * 活动类型
 * @author W.Z.L
 *
 */
public class ActivityTypeDetailActivity extends BaseFragmentActivity{

	private final int CELL_DEFAULT_HEIGHT = 200;
	private CaldroidFragment caldroidFragment;
	private ExpandingListView mListView; 
	List<ExpandableListItem> mData; 
	Bundle state;
	ImageView mFinish;
	ImageView mBack;
	Intent mIntent;
	ActivityModel mModel;
	TypeDetailArrayAdapter mAdapter;
	
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		//state = savedInstanceState;
		NewActivityConst.clearData();
		mModel = new ActivityModel();
		mModel.setType(getIntent().getIntExtra("TYPE_ID",11));
		ExpandableListItem[] values = new ExpandableListItem[] {
				new ExpandableListItem("活动类型", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
	                    "活动类型"),
                new ExpandableListItem("活动名称", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动名称"),
                new ExpandableListItem("活动日期", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动日期"),
                new ExpandableListItem("活动时间", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动时间"),
                new ExpandableListItem("活动地点", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动地点"),
                new ExpandableListItem("活动参与者", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动参与者"),
                new ExpandableListItem("活动备注", R.drawable.type_big_1, CELL_DEFAULT_HEIGHT,
                        "活动备注"),
        };
		caldroidFragment = new CaldroidFragment();
		mData = new ArrayList<ExpandableListItem>();

        for (int i = 0; i < values.length; i++) {
            ExpandableListItem obj = values[i % values.length];
            mData.add(new ExpandableListItem(obj.getTitle(), obj.getImgResource(),
                    obj.getCollapsedHeight(), obj.getText()));
        }

		setContentView(R.layout.activity_activity_type_detail);
	}

	@Override
	public void initView() {
		mBack = (ImageView)findViewById(R.id.activity_type_detail_back);
		mFinish = (ImageView)findViewById(R.id.activity_type_detail_finish);
		// TODO Auto-generated method stub
		mAdapter = new TypeDetailArrayAdapter(
				this, R.layout.item_list_activity_type_detail, mData);

        mListView = (ExpandingListView)findViewById(R.id.main_list_view);
        mListView.setAdapter(mAdapter);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(new OnItemClickListener());
		mBack.setOnClickListener(this);
		mFinish.setOnClickListener(this);
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();

		// Min date is last 7 days
		cal.add(Calendar.DATE, -18);
		Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 16);
		Date greenDate = cal.getTime();
		if (caldroidFragment != null) {
			caldroidFragment.setBackgroundResourceForDate(R.color.Royalblue,
					blueDate);
			caldroidFragment.setBackgroundResourceForDate(R.color.Darkseagreen,
					greenDate);
			caldroidFragment.setTextColorForDate(R.color.White, blueDate);
			caldroidFragment.setTextColorForDate(R.color.White, greenDate);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activity_type_detail_back:
			finish();
			break;
		case R.id.activity_type_detail_finish:
			//mModel.setDate(NewActivityConst.)
			mModel.setName(NewActivityConst.NAME);
			//mModel.setTimeStart(NewActivityConst.START_TIME);
			//mModel.setLocation(NewActivityConst.LOCATION);
			//mModel.setNote(NewActivityConst.NOTE);
			//mModel.print();
			finish();
			break;
		default:
			break;
		}
	}
	

	EditText  mNameEdit; //活动名称自定义编辑框
	Spinner   mNameSpinner;
	ActivityNameSpinnerAdapter spinnerAdapter;
	EditText  mDateEdit; //活动日期
	EditText  mTimeEdit; //活动时间
	EditText  mLocationEdit; //活动地点
	EditText  mContactEdit; //活动联系人
	EditText  mNoteEdit; //活动备注
	class OnItemClickListener implements android.widget.AdapterView.OnItemClickListener
	{

		LinearLayout mLayout;
		ImageView mTypeBack;
		ImageView mNameBack;
		ImageView mDateBack;
		ImageView mLocationBack;
		ImageView mTimeBack;
		ImageView mContactBack;
		ImageView mNoteBack;
		Button mContactSelect;
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				//活动类型
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_type);
				mLayout.setVisibility(View.VISIBLE);
				mTypeBack = (ImageView)mLayout.findViewById(R.id.expanding_type_back);
				mTypeBack.setOnClickListener(new TypeBackListener(mLayout));
				break;
			case 1:
				//活动名称
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_name);
				mLayout.setVisibility(View.VISIBLE);
				mNameSpinner = (Spinner)mLayout.findViewById(R.id.expanding_name_spinner);
				spinnerAdapter = new ActivityNameSpinnerAdapter(getBaseContext());
				mNameSpinner.setAdapter(spinnerAdapter);
				mNameBack = (ImageView)mLayout.findViewById(R.id.expanding_name_back);
				mNameEdit = (EditText)mLayout.findViewById(R.id.expanding_name_edit);
				mNameBack.setOnClickListener(new NameBackListener(mLayout));
				break;
			case 2:
				//活动日期
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_date);
				mLayout.setVisibility(View.VISIBLE);
				
				// Attach to the activity
				FragmentTransaction t = getSupportFragmentManager().beginTransaction();
				t.replace(R.id.expanding_date_calendar, caldroidFragment);
				t.commit();
				mDateBack = (ImageView)mLayout.findViewById(R.id.expanding_date_back);
				mDateBack.setOnClickListener(new NameBackListener(mLayout));
				break;
			case 3:
				//活动时间
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_time);
				mLayout.setVisibility(View.VISIBLE);
				mTimeBack = (ImageView)mLayout.findViewById(R.id.expanding_time_back);
				mTimeBack.setOnClickListener(new TimeBackListener(mLayout));
				break;
			case 4:
				//活动地点
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_location);
				mLayout.setVisibility(View.VISIBLE);
				mLocationEdit = (EditText)mLayout.findViewById(R.id.expanding_location_edit);
				mLocationBack = (ImageView)mLayout.findViewById(R.id.expanding_location_back);
				mLocationBack.setOnClickListener(new LocationBackListener(mLayout));
				break;
			case 5:
				//活动联系人
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_contact);
				mLayout.setVisibility(View.VISIBLE);
				mContactBack = (ImageView)mLayout.findViewById(R.id.expanding_contact_back);
				mContactBack.setOnClickListener(new ContactBackListener(mLayout));
				mContactSelect = (Button)mLayout.findViewById(R.id.expanding_contact_select);
				mContactSelect.setOnClickListener(new ContactSelectListener());
				break;
			case 6:
				//活动备注
				mLayout = (LinearLayout)view.findViewById(R.id.expanding_item_note);
				mLayout.setVisibility(View.VISIBLE);
				mNoteEdit = (EditText)mLayout.findViewById(R.id.expanding_note_edit);
				mNoteBack = (ImageView)mLayout.findViewById(R.id.expanding_note_back);
				mNoteBack.setOnClickListener(new NoteBackListener(mLayout));
				break;
			default:
				break;
			}
			
			ExpandableListItem viewObject = (ExpandableListItem)
					mListView.getItemAtPosition(mListView.getPositionForView
                    (view));
			if (! viewObject.isExpanded() ) {
				mListView.expandView(view);
				mListView.setOnItemClickListener(this);
			}

		}	
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	// Setup listener
	
	final CaldroidListener listener = new CaldroidListener() {

		@Override
		public void onSelectDate(Date date, View view) {
			Toast.makeText(getApplicationContext(), formatter.format(date),
				Toast.LENGTH_SHORT).show();
			if (! view.hasFocus()) {
				view.setBackgroundResource(R.color.Lemonchiffon);
			}
			else {
				view.setBackgroundResource(R.color.Royalblue);
			}
		}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
			Toast.makeText(getApplicationContext(), text,
							Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLongClickDate(Date date, View view) {
			Toast.makeText(getApplicationContext(),
							"Long click " + formatter.format(date),
							Toast.LENGTH_SHORT).show();
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
	
	
	class TypeBackListener implements OnClickListener{
		
		LinearLayout mLayout;
		TypeBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
		}	
	}
	
	class NameBackListener implements OnClickListener{

		LinearLayout mLayout;
		NameBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
			mAdapter.getItem(1).setText(mNameEdit.getText().toString());
			mAdapter.notifyDataSetChanged();
		}	
	}
	
	class DateBackListener implements OnClickListener{
		
	   LinearLayout mLayout;
	   DateBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
		}
	}
	
	class LocationBackListener implements OnClickListener{

		LinearLayout mLayout;
		LocationBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
			mAdapter.getItem(4).setText(mLocationEdit.getText().toString());
			mAdapter.notifyDataSetChanged();
		}
	}
	
	class TimeBackListener implements OnClickListener{

		LinearLayout mLayout;
		TimePicker mPicker;
		TimeBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mPicker = (TimePicker)mLayout.findViewById(R.id.expanding_time_picker);
			mLayout.setVisibility(View.GONE);
		}
	}
	
	class ContactSelectListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mIntent = new Intent(getBaseContext(),SelectContactActivity.class);
			startActivity(mIntent);
		}
		
	}
	
	class ContactBackListener implements OnClickListener{
		
		LinearLayout mLayout;
		ContactBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
		}
	}

	class NoteBackListener implements OnClickListener{

		LinearLayout mLayout;
		NoteBackListener(LinearLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
			mAdapter.getItem(6).setText(mNoteEdit.getText().toString());
			mAdapter.notifyDataSetChanged();
		}
	}
}
