package app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import app.view.layout.ScrollTabHolder;

/**
 * @see ActivityInfoActivity
 * 
 * activity_info
 * @author W.Z.L
 *
 */
public abstract class ScrollTabHolderFragment extends Fragment implements ScrollTabHolder {

	protected ScrollTabHolder mScrollTabHolder;

	public void setScrollTabHolder(ScrollTabHolder scrollTabHolder) {
		mScrollTabHolder = scrollTabHolder;
	}

	@Override
	public void onScroll(
			AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
		// nothing
	}
	


}