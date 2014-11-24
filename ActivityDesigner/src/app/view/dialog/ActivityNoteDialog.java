package app.view.dialog;

import com.example.activitydesigner.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityNoteDialog extends Dialog implements 
android.view.View.OnClickListener,OnItemSelectedListener{

	Context mContext;
	Button mBack;
	Button mSure;
	View mView;
	Spinner mSpinner;
	String[] mData;
	String mString;
	public ActivityNoteDialog(Context context) {
		super(context);
		this.mContext = context;
		// TODO Auto-generated constructor stub
		mData = new String[]{"记得多穿衣", "记得带雨伞", "今天我请客"};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_dialog_activity_note);
		mSpinner = (Spinner)findViewById(R.id.activitynote_dialog_spinner);
		mSpinner.setAdapter(new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1,mData));
		mSpinner.setOnItemSelectedListener(this);
		mSure  = (Button)findViewById(R.id.activitynote_dialog_sure);
		mSure.setOnClickListener(this);
		mBack  = (Button)findViewById(R.id.activitynote_dialog_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.activitynote_dialog_back:
			this.dismiss();
			break;
		case R.id.activitynote_dialog_sure:
			((EditText) mView).setText(mString);
			this.dismiss();
			break;
		default:
			break;
		}
	}
	
	public void setView(View view){
		mView = view;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		mString = arg0.getItemAtPosition(arg2).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		mString = "";
	}
}
