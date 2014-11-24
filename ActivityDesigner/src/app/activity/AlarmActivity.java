package app.activity;


import com.example.activitydesigner.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmActivity extends Activity{

	MediaPlayer alarmMusic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载指定音乐，并为之创建MediaPlayer对象
		alarmMusic = MediaPlayer.create(this, R.raw.onestop);
		alarmMusic.setLooping(true);
		//播放闹钟
		alarmMusic.start();
		//创建一个对话框
		new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟")
			.setMessage("有活动啦，快查看详情！")
			.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//停止音乐
					alarmMusic.stop();
					AlarmActivity.this.finish();
					Intent mIntent = new Intent(AlarmActivity.this,ActivityInfoActivity.class);
					startActivity(mIntent);
				}
			}).show();
	}
}
