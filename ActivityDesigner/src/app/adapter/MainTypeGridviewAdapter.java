package app.adapter;

import com.example.activitydesigner.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @see 
 * 活动类型
 * adapter
 * @author W.Z.L
 *
 */
public class MainTypeGridviewAdapter extends BaseAdapter{

	int []imgs = new int[]{R.drawable.sports,
			R.drawable.eat,R.drawable.conference,R.drawable.shopping,
				R.drawable.work,R.drawable.more,};
	String [] titles = new String[]
			{
				"运动","餐饮","会议","消费","工作","自定义活动"
			};
	Context mContext;
	public MainTypeGridviewAdapter(Context context)
	{
		this.mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertview == null){
			convertview = LayoutInflater.from(mContext).inflate(R.layout.item_grid_main_type,null);
			viewHolder = new ViewHolder();
			viewHolder.img = (ImageView)convertview.findViewById(R.id.maintype_item_img);
			viewHolder.title  = (TextView)convertview.findViewById(R.id.maintype_item_text);
			convertview.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)convertview.getTag();
		}
		viewHolder.img.setImageResource(imgs[pos]);
		viewHolder.title.setText(titles[pos]);
		return convertview;
	}
	
	class ViewHolder 
	{
		ImageView img;
		TextView title;
	}

}
