package app.adapter;

import java.util.List;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.item.ExpandableListItem;
import app.view.layout.ExpandingLayout;

/**
 * @see ActivityTypeDetailActivity
 * @author W.Z.L
 *
 */
public class TypeDetailArrayAdapter extends ArrayAdapter<ExpandableListItem>{

		Context mContext;
	    private List<ExpandableListItem> mData; //各组数据
	    private int mLayoutViewResourceId;

	    public TypeDetailArrayAdapter(Context context, int layoutViewResourceId,
	                              List<ExpandableListItem> data) {
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
	    public View getView(int position, View convertView, ViewGroup parent) {

	    	ViewHolder viewHolder;
	    	int []bgs = new int[]{R.drawable.color_block_1,R.drawable.color_block_2,R.drawable.color_block_3,
	    			R.drawable.color_block_4,R.drawable.color_block_5,R.drawable.color_block_6,R.drawable.color_block_7};
	        final ExpandableListItem object = mData.get(position);
	        if(convertView == null) {
	            convertView = LayoutInflater.from(mContext).inflate(mLayoutViewResourceId,null);
				viewHolder = new ViewHolder();
		        viewHolder.title = (TextView)convertView.findViewById(R.id.activitytypedetail_item_title);
		        viewHolder.content = (TextView)convertView.findViewById(R.id.activitytypedetail_item_content);
		        viewHolder.mParentLayout = (RelativeLayout)convertView.findViewById(R.id.activitytypedetail_item_layout);
	            viewHolder.mLayout = (ExpandingLayout)convertView.findViewById(R.id.expanding_layout);
	            viewHolder.mLayout.setExpandedHeight(object.getExpandedHeight());
	            viewHolder.mLayout.setSizeChangedListener(object);
	            convertView.setTag(viewHolder);
	            }
	        else {
	        	viewHolder = (ViewHolder)convertView.getTag();
			}
	            
	           viewHolder.title.setText(mData.get(position).getTitle());
	           //viewHolder.content.setTextColor(R.color.White);
	           viewHolder.content.setText(mData.get(position).getText());
	           viewHolder.mParentLayout.setBackgroundResource(bgs[position]);
	       /** if (!object.isExpanded()) {
	             viewHolder.mLayout.setVisibility(View.GONE);
	        } else {
	            viewHolder.mLayout.setVisibility(View.VISIBLE);
	        }**/
	        return convertView;
	    }
	    
	    class ViewHolder 
	    {
	    	TextView title;
	    	TextView content;
	    	ExpandingLayout mLayout;
	    	RelativeLayout mParentLayout;
	    }
	    
	 

	    /**
	     * Crops a circle out of the thumbnail photo.
	     */
	    public Bitmap getCroppedBitmap(Bitmap bitmap) {
	        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
	                Config.ARGB_8888);

	        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

	        Canvas canvas = new Canvas(output);

	        final Paint paint = new Paint();
	        paint.setAntiAlias(true);

	        int halfWidth = bitmap.getWidth()/2;
	        int halfHeight = bitmap.getHeight()/2;

	        canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth, halfHeight), paint);

	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

	        canvas.drawBitmap(bitmap, rect, rect, paint);

	        return output;
	    }
}
