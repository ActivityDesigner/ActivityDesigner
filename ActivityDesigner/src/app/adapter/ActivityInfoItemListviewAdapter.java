package app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.activitydesigner.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.adapter.TypeDetailArrayAdapter.ViewHolder;
import app.item.ExpandableListItem;
import app.model.ActivityModel;
import app.view.layout.ExpandingLayout;

public class ActivityInfoItemListviewAdapter extends ArrayAdapter<ExpandableListItem>{

	Context mContext;
	private List<ExpandableListItem> mData; //各组数据
    private int mLayoutViewResourceId;
	private final int CELL_DEFAULT_HEIGHT = 200;
	ExpandableListItem []values;
	
    public ActivityInfoItemListviewAdapter(Context context, int layoutViewResourceId,
             List<ExpandableListItem> data) {
		 super(context, layoutViewResourceId, data);
		 mContext = context;
		 mData = data;
		 mLayoutViewResourceId = layoutViewResourceId;	
	 }
    
    public void setData(List<ActivityModel> mList){
    	ActivityModel model = new ActivityModel();
    	for (int i = 0; i < mList.size(); i++) {
    		model = mList.get(i);
    	/**	ExpandableListItem item = new ExpandableListItem
        			("活动"+String.valueOf(i+1), 
        					model.getDrawableId(), 
        					CELL_DEFAULT_HEIGHT, model.getName() );**/
    		ExpandableListItem item = new ExpandableListItem
        			("活动"+String.valueOf(i+1), 
        					R.drawable.type_big_10, 
        					CELL_DEFAULT_HEIGHT, model.getName() );
    		mData.add(item);
		}
    	notifyDataSetChanged();
    }
    
    public void setData_2(List<ActivityModel> mList){
    	for (int i = 0; i < mList.size(); i++) {
			Log.e("setData_2", mList.get(i).getName());
		}
    	dataInitial(mList);
    	this.notifyDataSetChanged();
    }
    /**
     * Populates the item in the listview cell with the appropriate data. This method
     * sets the thumbnail image, the title and the extra text. This method also updates
     * the layout parameters of the item's view so that the image and title are centered
     * in the bounds of the collapsed view, and such that the extra text is not displayed
     * in the collapsed state of the cell.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    	ViewHolder viewHolder;
        final ExpandableListItem object = mData.get(position);
        if(convertView == null) {
             convertView = LayoutInflater.from(mContext).inflate(mLayoutViewResourceId,null);
			 viewHolder = new ViewHolder();
			 viewHolder.type = (ImageView)convertView.findViewById(R.id.activityinfoitem_item_img);
			 viewHolder.title = (TextView)convertView.findViewById(R.id.activityinfoitem_item_title);
			 viewHolder.mLayout = (ExpandingLayout)convertView.findViewById(R.id.activityinfoitem_item_layout);
	         viewHolder.mLayout.setExpandedHeight(object.getExpandedHeight());
	         viewHolder.mLayout.setSizeChangedListener(object);
	         convertView.setTag(viewHolder);
            }
        else {
        	viewHolder = (ViewHolder)convertView.getTag();
		}
            
        viewHolder.type.setBackgroundResource(mData.get(position).getImgResource());
        viewHolder.title.setText(mData.get(position).getTitle());
           //viewHolder.content.setTextColor(R.color.White);
        return convertView;
    }
    
    class ViewHolder 
    {
    	ImageView type;
    	TextView title;
    	ExpandingLayout mLayout;
    }
    
    private void dataInitial(List<ActivityModel> mActivityList ){
		   
    	 if (mActivityList != null && mActivityList.size() != 0) {
        	Log.d("mActivityList: size", String.valueOf(mActivityList.size()));
        	values = new ExpandableListItem[mActivityList.size()];
        	 for (int i = 0; i < mActivityList.size(); i++) {
     			/**values[i] = new ExpandableListItem("活动"+String.valueOf(i+1),mActivityList.get(i).getDrawableId(),
     						CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
     				**/
        		 values[i] = new ExpandableListItem("活动"+String.valueOf(i+1),R.drawable.type_big_1,
  						CELL_DEFAULT_HEIGHT,mActivityList.get(i).getName());
     			} 
        	 mData = new ArrayList<ExpandableListItem>();
        
        	 for (int i = 0; i < values.length; i++) {
        		 ExpandableListItem obj = values[i % values.length];
        		 mData.add(new ExpandableListItem(obj.getTitle(), obj.getImgResource(),
                    obj.getCollapsedHeight(), obj.getText()));
        	 }
       }
       else {
        	Log.d("MSG", "2");
        	 mData = new ArrayList<ExpandableListItem>();
	    }
    }
}
