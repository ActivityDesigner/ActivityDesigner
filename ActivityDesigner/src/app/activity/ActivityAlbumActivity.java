package app.activity;


import java.util.Map;

import com.example.activitydesigner.R;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import app.adapter.ActivityAlbumListviewAdapter;


/**
 * 活动相册
 * @author W.Z.L
 *
 */
public class ActivityAlbumActivity extends BaseActivity implements OnItemClickListener{

	

	ImageView mBack;
	ImageView mManage;
	ListView mListView;
	LinearLayout mLayout;
	TextView mLayoutAdd;
	TextView mLayoutDelete;
	TextView mLayoutFinish;
	TextView mLayoutMove;
	TextView mLayoutInfo;
	ActivityAlbumListviewAdapter mAdapter;
	Intent mIntent;
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_activity_album);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mBack = (ImageView)findViewById(R.id.activity_album_back);
		mManage = (ImageView)findViewById(R.id.activity_album_manage);
		mListView = (ListView)findViewById(R.id.activity_album_listView);
		mLayout = (LinearLayout)findViewById(R.id.activity_album_layout);
		mLayoutAdd = (TextView)findViewById(R.id.activity_album_layout_add);
		mLayoutDelete = (TextView)findViewById(R.id.activity_album_layout_delete);
		mLayoutFinish = (TextView)findViewById(R.id.activity_album_layout_finish);
		mLayoutInfo = (TextView)findViewById(R.id.activity_album_layout_info);
		mLayoutMove = (TextView)findViewById(R.id.activity_album_layout_move);
		mLayout.setVisibility(View.INVISIBLE);
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mBack.setOnClickListener(this);
		mManage.setOnClickListener(this);
		mLayoutAdd.setOnClickListener(this);
		mLayoutDelete.setOnClickListener(this);
		mLayoutFinish.setOnClickListener(this);
		mLayoutInfo.setOnClickListener(this);
		mLayoutMove.setOnClickListener(this);
		mAdapter = new ActivityAlbumListviewAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activity_album_back:
			Intent mIntent = new Intent(ActivityAlbumActivity.this,MainActivity.class);
			startActivity(mIntent);
			break;
		case R.id.activity_album_manage:
			mAdapter.setCheckBoxVisible();
			mLayout.setVisibility(arg0.VISIBLE);
			break;
		case R.id.activity_album_layout_add:
			Toast.makeText(getBaseContext(), "添加相册功能...", Toast.LENGTH_SHORT).show();
			break;
		case R.id.activity_album_layout_delete:
			//获得选择编号,得到我要删除哪个item
			//然后更改adapter的内容,notify
			mAdapter.deleteSelectedItem();
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.activity_album_layout_finish:
			mLayout.setVisibility(View.INVISIBLE);
			mAdapter.setCheckBoxInvisible();
			break;
		case R.id.activity_album_layout_move:
			break;
		case R.id.activity_album_layout_info:
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		mIntent = new Intent(getBaseContext(),ActivityAlbumDetailActivity.class);
		startActivity(mIntent);
		mIntent.putExtra("ALBUM_ID",arg2);
		//如果是最后一项，则新建相册
		
	}
}
