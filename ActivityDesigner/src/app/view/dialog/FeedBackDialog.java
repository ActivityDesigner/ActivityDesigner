package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * ”√ªß∑¥¿°
 * @author W.Z.L
 *
 */
public class FeedBackDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mSure;
	Button mBack;
	EditText mContent;
	public FeedBackDialog(Context context){
		super(context);
		mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_feed_back);
		mSure = (Button)findViewById(R.id.feed_back_sure);
		mBack = (Button)findViewById(R.id.feed_back_back);
		mContent = (EditText)findViewById(R.id.feed_back_edit);
		mSure.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.feed_back_sure:
			dismiss();
			break;
		case R.id.feed_back_back:
			dismiss();
			break;
		default:
			break;
		}
	}

}
