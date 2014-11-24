package app.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import app.adapter.CustomArrayAdapter;
import app.adapter.MsgInfoListviewAdapter;
import app.constant.NewActivityConst;
import app.item.ExpandableListItem;
import app.item.MsgInfoListItem;
import app.model.ActivityModel;
import app.view.dialog.ChatDialog;
import app.view.dialog.LoadingDialog;
import app.view.layout.ExpandingLayout;
import app.view.widget.ExpandingListView;


/**
 * @see msg
 * @author markmjw
 * @date 2014-04-30
 */
public class MsgInfoActivity extends Activity implements OnItemClickListener{
   
	private final int CELL_DEFAULT_HEIGHT = 200;

	private ExpandingListView mListView;
	
	List<ActivityModel> mActivityList;
	
	MsgInfoListItem[]values;
	
	List<MsgInfoListItem> mData;
	
	MsgInfoListviewAdapter adapter ;
	
	ImageView mDelete;
	
	ImageView mParentBack;
	
	DataTask mTask;
	
	LoadingDialog mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_msg_info);
        mLoadingDialog = new LoadingDialog(this);
        mParentBack = (ImageView)findViewById(R.id.msg_info_back);
        mParentBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MsgInfoActivity.this.finish();
			}
		});

        dataInitial();
        adapter = new MsgInfoListviewAdapter(this,R.layout.item_list_msg_info,mData);
       // mListView = (ExpandingListView)findViewById(R.id.msg_info_listview);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        
    }
    
    private void dataInitial(){
    	
    	mActivityList = NewActivityConst.newActivity;
        if (mActivityList.size() != 0) {
        	Log.d("mActivityList: size", String.valueOf(mActivityList.size()));
        	values = new MsgInfoListItem[mActivityList.size()];
        	 for (int i = 0; i < mActivityList.size(); i++) {
     			if (mActivityList.get(i).getState() == 0) {
     				/**values[i] = new MsgInfoListItem("֪ͨ"+String.valueOf(i+1),mActivityList.get(i).getDrawableId(),
     						CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
     				};**/
     				values[i] = new MsgInfoListItem("֪ͨ"+String.valueOf(i+1),R.drawable.type_big_10,
     						CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
     			 }
     			}
     		 
        	 
        mData = new ArrayList<MsgInfoListItem>();
        
        for (int i = 0; i < values.length; i++) {
        	MsgInfoListItem obj = values[i % values.length];
            mData.add(new MsgInfoListItem(obj.getTitle(), obj.getImgResource(),
                    obj.getCollapsedHeight(), obj.getText()));
         }
       }
        else {
        	Log.d("MSG", "2");
        	 mData = new ArrayList<MsgInfoListItem>();
		}
    }
    
    ExpandingLayout mLayout;
	TextView mType;
	TextView mName;
	TextView mDate;
	TextView mTime;
	TextView mLocation;
	TextView mContact;
	TextView mNote;
	ImageView mBack;
	ImageView mSure;
	ImageView mChat;
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String name = mActivityList.get(arg2).getName();
		String date = mActivityList.get(arg2).getDate();
		String time = "time";// mActivityList.get(arg2).getTimeStart();
		String location = "location"; //mActivityList.get(arg2).getLocation();
		String contact = "contact";//mActivityList.get(arg2).getAllContactName();
		String note = mActivityList.get(arg2).getNote();
		
		mLayout = (ExpandingLayout)view.findViewById(R.id.msginfo_item_layout);
		mLayout.setVisibility(View.VISIBLE);
		mName = (TextView)view.findViewById(R.id.expanding_msginfo_name);
		mName.setText(name);
		mDate = (TextView)view.findViewById(R.id.expanding_msginfo_date);
		mDate.setText(date);
		mTime = (TextView)view.findViewById(R.id.expanding_msginfo_time);
		mTime.setText(time);
		mLocation = (TextView)view.findViewById(R.id.expanding_msginfo_location);
		mLocation.setText(location);
		mContact = (TextView)view.findViewById(R.id.expanding_msginfo_contact);
		mContact.setText(contact);
		mNote = (TextView)view.findViewById(R.id.expanding_msginfo_note);
		mNote.setText(note);
		mBack = (ImageView)view.findViewById(R.id.expanding_msginfo_back);
		mBack.setOnClickListener(new BackListener(mLayout));
		mSure = (ImageView)view.findViewById(R.id.expanding_msginfo_sure);
		mSure.setOnClickListener(new SureListener(mLayout));
		mChat = (ImageView)findViewById(R.id.expanding_msginfo_chat);
		mChat.setOnClickListener(new ChatLisener(mLayout));
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
	
	ChatDialog mDialog;
	class ChatLisener implements OnClickListener{

		ExpandingLayout mLayout;
		ChatLisener(ExpandingLayout layout){
			mLayout = layout;
			mDialog = new ChatDialog(MsgInfoActivity.this);
			
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mDialog.show();
		}
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dataInitial();
		adapter.notifyDataSetChanged();
		Log.d("MSG", "3");
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		dataInitial();
		adapter.notifyDataSetChanged();
		Log.d("MSG", "4");
	}
	
	class DataTask  extends AsyncTask<Void,integer,integer>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mLoadingDialog.show();
		}
		@Override
		protected integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			return null;
		}		
		@Override
		protected void onPostExecute(integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingDialog.dismiss();
		}
	}
}
