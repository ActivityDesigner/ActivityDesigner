package app.adapter;


import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.utils.BMapUtil;
import app.view.widget.OnlyImageView;

public class PhotoDetailViewpagerAdapter extends PagerAdapter{

	Context mContext;
	private int pageLength = 0;
	List<String> mData;
	public PhotoDetailViewpagerAdapter(Context mContext,List<String> data){
		this.mContext = mContext;
	    mData = data;
		pageLength = mData.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		String imagePath = BMapUtil.getImagePath(mData.get(position));
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
		if (bitmap == null) {
			bitmap = BitmapFactory.decodeResource(mContext.getResources(),
					R.drawable.photo_display_stub);
		}
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.item_viewpager_photo_detail, null);
		OnlyImageView onlyImageView = (OnlyImageView) view
				.findViewById(R.id.imagedetail_item_view);
		onlyImageView.setImageBitmap(bitmap);
		container.addView(view);
		return view;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pageLength;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		View view = (View) object;
		container.removeView(view);
	}
}
