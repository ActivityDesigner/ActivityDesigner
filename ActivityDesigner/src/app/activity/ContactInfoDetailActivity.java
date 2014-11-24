package app.activity;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInfoDetailActivity extends Activity implements OnClickListener{

	TextView mBack;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_info_detail);  
        mBack = (TextView)findViewById(R.id.contact_info_detail_back);
        mBack.setOnClickListener(this);
    }

   public void btn_back(View v) {     //标题栏 返回按钮
      	this.finish();
      } 
   public void btn_back_send(View v) {     //标题栏 返回按钮
     	this.finish();
     } 
   public void head_xiaohei(View v) {     //头像按钮
	   Intent intent = new Intent();
		intent.setClass(ContactInfoDetailActivity.this,ContactInfoProfileActivity.class);
		startActivity(intent);
    }

@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	switch (arg0.getId()) {
	case R.id.contact_info_detail_back:
		this.finish();
		break;

	default:
		break;
	}
} 
}
