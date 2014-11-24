package app.adapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.baidu.lbsapi.auth.i;
import com.example.activitydesigner.R;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.activity.BaseActivity;

public class ActivityAlbumListviewAdapter extends BaseAdapter{

	private Map<String,String> itemSelected; 
	List<String> mData = new ArrayList<String>();
	int [] img = new int[]{R.drawable.album1,R.drawable.album2,R.drawable.album3,
			R.drawable.album1,R.drawable.album2,R.drawable.album3};
	int [] visible;
	Context mContext;
	ViewHolder viewHolder;
	public ActivityAlbumListviewAdapter(Context context)
	{
		this.mContext = context;
		itemSelected = new HashMap<String, String>();
		Log.e("con", "yes");
		mData.add("2014/图书馆/学习");
		mData.add("2014/车库/车队项目");
		mData.add("2013/图书馆/自习");
		mData.add("2012/图书馆/绘画");
		mData.add("2011/图书馆/写作");
		mData.add("2010/图书馆/阅读");
		mData.add(" ");
		visible =  new int[mData.size()]; 
		for (int i = 0; i < visible.length; i++) {
			visible[i] = 0;
		}
		for (int i = 0; i < mData.size(); i++) {
			itemSelected.put(String.valueOf(i),"false");
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int pos, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (convertview == null){
			convertview = LayoutInflater.from(mContext).inflate(R.layout.item_list_activity_album,null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView)convertview.findViewById(R.id.activity_album_item_title);
			viewHolder.img = (ImageView)convertview.findViewById(R.id.activity_album_item_img);
			viewHolder.checkBox = (CheckBox)convertview.findViewById(R.id.activity_album_item_checkbox);
			convertview.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)convertview.getTag();
		}
			viewHolder.title.setText(mData.get(pos));
			viewHolder.checkBox.setVisibility(View.INVISIBLE);
			if (visible[pos] == 1) {
				if (pos != mData.size() - 1) {
					viewHolder.checkBox.setVisibility(View.VISIBLE);
					viewHolder.checkBox.setOnCheckedChangeListener(new CheckBoxListener(pos));	
				}
			}
			if (pos != mData.size()-1) {

				viewHolder.img.setImageResource(img[pos]);	
			}
			else
			{
				viewHolder.img.setImageDrawable(null);//最后一项为空
				viewHolder.img.setClickable(false);
			}
		return convertview;
	}
	
	public void setCheckBoxVisible()
	{
		for (int i = 0; i < visible.length; i++) {
			visible[i] = 1; 
			if (i == visible.length - 1) {
				visible[i] = 0;//最后一项不显示
			}
		}
		notifyDataSetChanged();
	}
	
	public void setCheckBoxInvisible()
	{
		for (int i = 0; i < visible.length; i++) {
			visible[i] = 0;
		}
		notifyDataSetChanged();
	}
	
	/**
	 * 1.编号
	 * 2.true 被选中，false 没被选中
	 * @return
	 */
	public Map<String, String> getCheckBoxItem(){
		return itemSelected;
	}
	
	public void deleteSelectedItem(){
		List<String> del  = new ArrayList<String>();
		for (String key : itemSelected.keySet()) {
			Log.e("key：", key);
			if (itemSelected.get(key) == "true") {
				del.add(key);
				Log.e("keyselected：", key);
			}
		}
		for (int i = 0; i < del.size(); i++) {
			Log.e("test", mData.get(Integer.valueOf(del.get(i))));
			mData.remove(mData.get(Integer.valueOf(del.get(i))));
			Log.e("keyremove：", del.get(i));
		}
		Log.e("size",String.valueOf(mData.size()));
	}
	class ViewHolder 
	{
		TextView title;
		ImageView img;
		CheckBox checkBox;
	}
	
	class CheckBoxListener implements OnCheckedChangeListener{

		int id;
		CheckBoxListener(int pos){
			id = pos;
		}
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean judge) {
			// TODO Auto-generated method stub
			itemSelected.put(String.valueOf(id),String.valueOf(judge));
		}
	}
}
