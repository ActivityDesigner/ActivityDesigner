package app.adapter;

import com.example.activitydesigner.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityNameSpinnerAdapter extends BaseAdapter{

	String [] titles = new String[]{"name1","name2","name3"};
	int [] imgs = new int[]{R.drawable.type_big_1,R.drawable.type_big_10,R.drawable.type_big_11};
	Context mContext;
	
	public ActivityNameSpinnerAdapter(Context mContext){
		this.mContext = mContext;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return titles.length;
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
	public View getView(int pos, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		// ��̬����ÿ���������Ӧ��View��ÿ��������View��LinearLayout
		// �а���һ��ImageView��һ��TextView����
		// ��ʼ��LinearLayout
		LinearLayout ll = new LinearLayout(mContext);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		// ��ʼ��ImageView
		ImageView ii = new ImageView(mContext);
		ii.setImageResource(imgs[pos]);
		ll.addView(ii);
		// ��ʼ��TextView
		TextView tv = new TextView(mContext);
		tv.setText(" " +titles[pos]);
		tv.setTextSize(24);
		ll.addView(tv);
		return ll;
	}

}
