package app.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import app.constant.CommonConst;
import app.view.widget.OnlyScrollView;

/**
 * œ‡≤·œÍ«È
 * @author W.Z.L
 *
 */
public class ActivityAlbumDetailActivity extends BaseActivity{

	String []imgDemo = new String[]
			{"http://a.hiphotos.baidu.com/album/pic/item/aa18972bd40735fa751e37a39d510fb30f240839.jpg",
			"http://f.hiphotos.baidu.com/album/pic/item/b3b7d0a20cf431adaefe7c214836acaf2edd98fd.jpg",
			"http://f.hiphotos.baidu.com/album/pic/item/8326cffc1e178a824c3c40b8f503738da977e8a2.jpg",
			"http://e.hiphotos.baidu.com/album/pic/item/e850352ac65c1038075bf1dab1119313b07e8984.jpg",
			"http://b.hiphotos.baidu.com/album/pic/item/0b7b02087bf40ad1a3e192f3542c11dfa9ecce9d.jpg",
			"http://c.hiphotos.baidu.com/album/pic/item/03087bf40ad162d930eb370c12dfa9ec8a13cd9d.jpg",
			"http://f.hiphotos.baidu.com/album/pic/item/ac4bd11373f08202d749fb2f48fbfbedaa641bc2.jpg",
			"http://e.hiphotos.baidu.com/album/pic/item/95eef01f3a292df5d4c7f905bf315c6034a8739d.jpg",};
	
	ImageView mUpload;
	OnlyScrollView mScrollView;
	Intent mIntent;
	ImageView mBack;
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_activity_album_detail);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mUpload = (ImageView)findViewById(R.id.activity_album_detail_upload);
		mScrollView = (OnlyScrollView)findViewById(R.id.activity_album_detail_scrollview);
		mBack = (ImageView)findViewById(R.id.activity_album_detail_back);
		mBack.setOnClickListener(this);
		mScrollView.SetImageUrls(imgDemo);
		if (CommonConst.localImage.size() != 0) {
			mScrollView.setLocalImage(CommonConst.localImage);	
		}
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		mUpload.setOnClickListener(this);
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activity_album_detail_upload:
			mIntent = new Intent(this,PhotoUploadActivity.class);
			startActivity(mIntent);
			break;
		case R.id.activity_album_detail_back:
			this.finish();
			break;
		default:
			break;
		}
	}
}
