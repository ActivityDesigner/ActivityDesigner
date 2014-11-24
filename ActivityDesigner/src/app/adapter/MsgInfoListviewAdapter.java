package app.adapter;

import java.util.List;


import com.example.activitydesigner.R;
import com.example.activitydesigner.R.layout;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import app.item.ExpandableListItem;
import app.item.MsgInfoListItem;
import app.view.layout.ExpandingLayout;

/**
 * 
 * @author W.Z.L
 *
 */
public class MsgInfoListviewAdapter extends ArrayAdapter<MsgInfoListItem>{

	Context mContext;
	private List<MsgInfoListItem> mData; //各组数据
    private int mLayoutViewResourceId;
	
    public MsgInfoListviewAdapter(Context context, int layoutViewResourceId,
             List<MsgInfoListItem> data) {
		 super(context, layoutViewResourceId, data);
		 mContext = context;
		 mData = data;
		 mLayoutViewResourceId = layoutViewResourceId;	
	 }
    
    /**
     * Populates the item in the listview cell with the appropriate data. This method
     * sets the thumbnail image, the title and the extra text. This method also updates
     * the layout parameters of the item's view so that the image and title are centered
     * in the bounds of the collapsed view, and such that the extra text is not displayed
     * in the collapsed state of the cell.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

    	ViewHolder viewHolder;
        final MsgInfoListItem object = mData.get(position);
        if(convertView == null) {
             convertView = LayoutInflater.from(mContext).inflate(mLayoutViewResourceId,null);
			 viewHolder = new ViewHolder();
			 viewHolder.type = (ImageView)convertView.findViewById(R.id.msginfo_item_img);
			 viewHolder.title = (TextView)convertView.findViewById(R.id.msginfo_item_title);
			 viewHolder.delete = (ImageView)convertView.findViewById(R.id.msginfo_item_delete);
			 viewHolder.mLayout = (ExpandingLayout)convertView.findViewById(R.id.msginfo_item_layout);
	         viewHolder.mLayout.setExpandedHeight(object.getExpandedHeight());
	         viewHolder.mLayout.setSizeChangedListener(object);
	         convertView.setTag(viewHolder);
            }
        else {
        	viewHolder = (ViewHolder)convertView.getTag();
		}
            
        viewHolder.type.setBackgroundResource(mData.get(position).getImgResource());
        viewHolder.title.setText(mData.get(position).getTitle());
        viewHolder.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mData.remove(position);
				MsgInfoListviewAdapter.this.notifyDataSetChanged();
			}
		});
           //viewHolder.content.setTextColor(R.color.White);
        return convertView;
    }
    
    class ViewHolder 
    {
    	ImageView type;
    	TextView title;
    	ImageView delete;
    	ExpandingLayout mLayout;
    }
}
