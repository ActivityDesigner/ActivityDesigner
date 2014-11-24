package app.adapter;

import java.util.List;

import com.example.activitydesigner.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.camera.base.FilterInfo;

public class PhotoFilterAdapter extends BaseAdapter{

	private List<FilterInfo> filterArray;
	private LayoutInflater inflater;
	
	public PhotoFilterAdapter(Context context,List<FilterInfo> filterArray) {
        super();
        inflater = LayoutInflater.from(context);
        this.filterArray = filterArray;
       
    }

    
	public int getCount() {
		return filterArray.size();
	}

	public Object getItem(int position) {
		return position < filterArray.size() ? filterArray.get(position).filter
				: null;
	}

    
    public long getItemId(int position) {
        return position;
    }

    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ViewHolder holder;
    	if (convertView == null) {
    		convertView = inflater.inflate(R.layout.item_photo_filter_gallery, null);
    		holder = new ViewHolder();
    		holder.effectTitle = (TextView)convertView.findViewById(R.id.effectTitle);
    		holder.effectDrawable = (ImageView)convertView.findViewById(R.id.effectDrawable);
    		convertView.setTag(holder);
    	} else {
    		holder = (ViewHolder)convertView.getTag();
    	}
    	
    	holder.effectDrawable.setImageResource(filterArray.get(position).filterID);
        
        if (filterArray.get(position).isSelect) {
        	holder.effectDrawable.setBackgroundResource(R.drawable.photo_filter_gallery_select);
        }else {
        	holder.effectDrawable.setBackgroundDrawable(null);
        }
        
//        notifyDataSetChanged();
        return convertView;
    }
    
    public void changeStatus(int select) {
    	for (int i=0; i<filterArray.size(); i++) {
    		filterArray.get(i).isSelect = false;
    	}
    	
    	filterArray.get(select).isSelect = true;
    }
    
    private class ViewHolder {
    	TextView effectTitle;
    	ImageView effectDrawable;
    }
    
    class GalleryInfo {
    	public String title;
    	public int drawable;
    	public boolean isSelect;
    }
}
