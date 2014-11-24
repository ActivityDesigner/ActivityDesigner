package app.fragment;

import java.util.ArrayList;
import java.util.List;
import com.example.activitydesigner.R;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;
import app.activity.ActivityAlbumActivity;
import app.adapter.ActivityInfoItemListviewAdapter;
import app.constant.NewActivityConst;
import app.constant.TypeConst;
import app.item.ExpandableListItem;
import app.item.MsgInfoListItem;
import app.model.ActivityModel;
import app.model.DataResultModel;
import app.utils.HttpUtil;
import app.view.dialog.ChatDialog;
import app.view.dialog.LoadingDialog;
import app.view.layout.ExpandingLayout;
import app.view.widget.ExpandingListView;

public class ActivityInfoItemFragment extends ScrollTabHolderFragment implements OnScrollListener,OnItemClickListener{

	private static final String ARG_POSITION = "position";

	private final int CELL_DEFAULT_HEIGHT = 200;
	private ExpandingListView mListView;
	//private ArrayList<String> mListItems;
	public  ArrayList<ExpandableListItem> mData;
	public ActivityInfoItemListviewAdapter mAdapter;
	private int mPosition;
	ExpandableListItem []values;
	List<ActivityModel> mActivityList;
	Intent mIntent;
	ChatDialog mChatDialog;
	LoadingDialog mLoadingDialog;
	DataTask mTask;
	DataResultModel mResultModel;
	boolean hasFinished = true;
	public static Fragment newInstance(int position) {
		ActivityInfoItemFragment f = new ActivityInfoItemFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPosition = getArguments().getInt(ARG_POSITION);
		mChatDialog = new ChatDialog(getActivity());
		mLoadingDialog = new LoadingDialog(getActivity());
		if (hasFinished) {
			mTask = new DataTask();
			mTask.execute();	
		}
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_activity_info_item, null);

	    mListView = (ExpandingListView) v.findViewById(R.id.activity_info_item_listview);

		View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mListView, false);
		mListView.addHeaderView(placeHolderView);

		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mListView.setOnScrollListener(this);
		mData = new ArrayList<ExpandableListItem>();
		//mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
		//mListView.setAdapter(mAdapter);
		mListView.setDividerHeight(1);
		mListView.setOnItemClickListener(this);
	}
	
	@Override
	public void adjustScroll(int scrollHeight) {
		// TODO Auto-generated method stub
		if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
			return;
		}
		
		mListView.setSelectionFromTop(1, scrollHeight);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, mPosition);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}


	ExpandingLayout mLayout;
	TextView mType;
	ImageView mDrawable;
	TextView mName;
	TextView mDate;
	TextView mTime;
	TextView mLocation;
	TextView mContact;
	TextView mNote;
	ImageView mAlbum;
	ImageView mChat;
	ImageView mBack;
	ImageView mSure;
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		// TODO Auto-generated method stub
		mLayout = (ExpandingLayout)view.findViewById(R.id.activityinfoitem_item_layout);
		mLayout.setVisibility(View.VISIBLE);
		arg2 = arg2 - 1;
		String type = "1";//mActivityList.get(arg2).getTypeName();
		int drawable = 1;//mActivityList.get(arg2).getDrawableId();
		String name = "name";//mActivityList.get(arg2).getName();
		String date = "date";//mActivityList.get(arg2).getDate();
		String time = "time";//mActivityList.get(arg2).getTimeStart();
		String location = "location";//mActivityList.get(arg2).getLocation();
		String contact = "contact";//mActivityList.get(arg2).allContactName;
		String note = "note";//mActivityList.get(arg2).getNote();
		
		
		mType = (TextView)view.findViewById(R.id.expanding_activityinfo_type);
		mType.setText(type);
		mDrawable = (ImageView)view.findViewById(R.id.expanding_activityinfo_typeimg);
		mDrawable.setImageResource(TypeConst.getTypeDrawableId(drawable));
		mName = (TextView)view.findViewById(R.id.expanding_activityinfo_name);
		mName.setText(name);
		mDate = (TextView)view.findViewById(R.id.expanding_activityinfo_date);
		mDate.setText(date);
		mTime = (TextView)view.findViewById(R.id.expanding_activityinfo_time);
		mTime.setText(time);
		mLocation = (TextView)view.findViewById(R.id.expanding_activityinfo_location);
		mLocation.setText(location);
		mContact = (TextView)view.findViewById(R.id.expanding_activityinfo_contact);
		mContact.setText(contact);
		mNote = (TextView)view.findViewById(R.id.expanding_activityinfo_note);
		mNote.setText(note);
		mChat = (ImageView)view.findViewById(R.id.expanding_activityinfo_chat);
		mChat.setOnClickListener(new ChatListener());
		mAlbum = (ImageView)view.findViewById(R.id.expanding_activityinfo_album);
		mAlbum.setOnClickListener(new AlbumLisener());
		mBack = (ImageView)view.findViewById(R.id.expanding_activityinfo_back);
		mBack.setOnClickListener(new BackListener(mLayout));
		mSure = (ImageView)view.findViewById(R.id.expanding_activityinfo_sure);
		mSure.setOnClickListener(new SureListener(mLayout));
	}

	class BackListener implements OnClickListener{

		ExpandingLayout mLayout;
		BackListener(ExpandingLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
		}
	}
	
	class SureListener implements OnClickListener{

		ExpandingLayout mLayout;
		SureListener(ExpandingLayout layout){
			mLayout = layout;
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mLayout.setVisibility(View.GONE);
		}
	}
	
	class ChatListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
		// TODO Auto-generated method stub
			mChatDialog.show();
		}
		
	}
	
	class AlbumLisener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mIntent = new Intent(getActivity(),ActivityAlbumActivity.class);
			startActivity(mIntent);
		}
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//dataInitial();
		//mAdapter.notifyDataSetChanged();
	}
	
	class DataTask extends AsyncTask<Void, Integer, Integer>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			hasFinished = false;
			mLoadingDialog.show();
		}
		
		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//获得返回List<ActivityInfoModel>
			mResultModel = HttpUtil.getActivity();
			Log.e("mResultModel.success: ", mResultModel.success);
			if (mResultModel.success.equals("true") ) {
				
				return 1;
			}
			else {
				return 0;	
			}
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingDialog.dismiss();
			hasFinished = true;
			if (result == 1) {
				//获取数据成功
				Toast.makeText(getActivity(), "获取数据成功", Toast.LENGTH_LONG).show();
				String success = (String) mResultModel.success;
				String errorMsg = mResultModel.error;
				mActivityList = (List<ActivityModel>) mResultModel.data;
				//显示数据
				//mAdapter.setData_2(mActivityList);
				dataInitial(mActivityList);
				mAdapter = new ActivityInfoItemListviewAdapter(getActivity(),
						R.layout.item_activity_info_listview, mData);
				mListView.setAdapter(mAdapter);
			}
			else {
				//获取数据失败
				Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_LONG).show();
			}
		}
	}
	
    private void dataInitial(List<ActivityModel> mActivityList ){
		   
   	 if (mActivityList != null && mActivityList.size() != 0) {
       	Log.d("mActivityList: size", String.valueOf(mActivityList.size()));
       	values = new ExpandableListItem[mActivityList.size()];
       	 for (int i = 0; i < mActivityList.size(); i++) {
    			//values[i] = new ExpandableListItem("活动"+String.valueOf(i+1),mActivityList.get(i).getDrawableId(),
    				//		CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
       	 values[i] = new ExpandableListItem("活动"+String.valueOf(i+1),R.drawable.type_big_7,
						CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
    				
    			} 
       	 mData = new ArrayList<ExpandableListItem>();
       
       	 for (int i = 0; i < values.length; i++) {
       		 ExpandableListItem obj = values[i % values.length];
       		 mData.add(new ExpandableListItem(obj.getTitle(), obj.getImgResource(),
                   obj.getCollapsedHeight(), obj.getText()));
       	 }
      }
      else {
       	Log.d("MSG", "2");
       	 mData = new ArrayList<ExpandableListItem>();
	    }
   }
}
