package app.activity;


import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import app.adapter.PhotoDetailViewpagerAdapter;

public class PhotoDetailActivity extends BaseActivity implements OnPageChangeListener{

	/**
	 * 用于管理图片的滑动
	 */
	private ViewPager viewPager;

	/**
	 * 显示当前图片的页数
	 */
	private TextView pageText;
	
	PhotoDetailViewpagerAdapter mAdapter;
	
	private int pageLength = 0;
	
	ImageView mBack;
	
	ArrayList<String> mData;
	
	int imagePosition = 0;
	
	@Override
	public void setView() {
		// TODO Auto-generated method stub
		imagePosition = getIntent().getIntExtra("image_position", 0);
		//获得数据
		setContentView(R.layout.activity_photo_detail);
		//pageLength = UrlConst.imageUrls.length;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager)findViewById(R.id.photo_detail_viewpager);
		pageText = (TextView)findViewById(R.id.photo_detai_pagenum);
		mBack = (ImageView)findViewById(R.id.photo_detail_back);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		String [] tmp = getIntent().getStringArrayExtra("IMAGE_URLS");
		mData = new ArrayList<String>();
		for (int i = 0; i < tmp.length; i++) {
			mData.add(tmp[i]);
		}
		mAdapter = new PhotoDetailViewpagerAdapter(this,mData);
		viewPager.setAdapter(mAdapter);
		viewPager.setCurrentItem(imagePosition); //设置当前照片位置
		viewPager.setOnPageChangeListener(this);
		viewPager.setEnabled(false);
		// 设定当前的页数和总页数
		pageText.setText((imagePosition + 1) + "/" + pageLength);
	}

	@Override
	public void setProcess() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.photo_detail_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int currentPage) {
		// TODO Auto-generated method stub
		// 每当页数发生改变时重新设定一遍当前的页数和总页数
		   pageText.setText((currentPage + 1) + "/" + pageLength);
	}

}
