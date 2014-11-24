package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RemindDetailDialog extends Dialog implements OnClickListener{

	Context mContext;
	Button mSure;
	public RemindDetailDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_dialog_remind_detail);
		mSure = (Button)findViewById(R.id.reminddetail_dialog_sure);
		mSure.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.reminddetail_dialog_sure:
			dismiss();
			break;

		default:
			break;
		}
	}
}
