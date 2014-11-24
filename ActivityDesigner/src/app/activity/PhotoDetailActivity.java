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
	 * ���ڹ���ͼƬ�Ļ���
	 */
	private ViewPager viewPager;

	/**
	 * ��ʾ��ǰͼƬ��ҳ��
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
		//�������
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
		viewPager.setCurrentItem(imagePosition); //���õ�ǰ��Ƭλ��
		viewPager.setOnPageChangeListener(this);
		viewPager.setEnabled(false);
		// �趨��ǰ��ҳ������ҳ��
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
		// ÿ��ҳ�������ı�ʱ�����趨һ�鵱ǰ��ҳ������ҳ��
		   pageText.setText((currentPage + 1) + "/" + pageLength);
	}

}
