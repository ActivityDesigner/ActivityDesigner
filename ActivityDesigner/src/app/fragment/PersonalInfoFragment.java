package app.fragment;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import app.activity.ActivityAlbumActivity;
import app.activity.AddRemindActivity;
import app.view.dialog.FeedBackDialog;
import app.view.dialog.ModifyPasswordDialog;
import app.view.widget.PullScrollView;

public class PersonalInfoFragment extends Fragment 
	implements OnClickListener, PullScrollView.OnTurnListener{

	private PullScrollView mScrollView;
    private ImageView mHeadImg;
	ExpandableListView mListView;
	private TextView mCircle;
	Intent mIntent;
	Button mLookRemind;
	Button mModifyPassword;
	Button mFeedBack;
	Dialog mModifyPasswordDialog;
	FeedBackDialog mFeedBackDialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mModifyPasswordDialog = new ModifyPasswordDialog(getActivity());
		mFeedBackDialog = new FeedBackDialog(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_personal_info, container,false);
		mScrollView = (PullScrollView) view.findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) view.findViewById(R.id.background_img);
        mCircle = (TextView)view.findViewById(R.id.personal_info_circle);
        mLookRemind = (Button)view.findViewById(R.id.personal_info_look_remind);
        mModifyPassword = (Button)view.findViewById(R.id.personal_info_modify_password);
        mFeedBack = (Button)view.findViewById(R.id.personal_info_feed_back);
        mCircle.setOnClickListener(this);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);
        mLookRemind.setOnClickListener(this);
        mModifyPassword.setOnClickListener(this);
        mFeedBack.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.personal_info_circle:
			//≈Û”—»¶
			mIntent = new Intent(getActivity(),ActivityAlbumActivity.class);
			startActivity(mIntent);
			break;
		case R.id.personal_info_look_remind:
			mIntent = new Intent(getActivity(),AddRemindActivity.class);
			startActivity(mIntent);
			break;
		case R.id.personal_info_modify_password:
			mModifyPasswordDialog.show();
			break;
		case R.id.personal_info_feed_back:
			mFeedBackDialog.show();
		default:
			break;
		}
	}

	@Override
	public void onTurn() {
		// TODO Auto-generated method stub
		
	}
}
