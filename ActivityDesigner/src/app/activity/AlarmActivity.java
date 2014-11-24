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
		//����ָ�����֣���Ϊ֮����MediaPlayer����
		alarmMusic = MediaPlayer.create(this, R.raw.onestop);
		alarmMusic.setLooping(true);
		//��������
		alarmMusic.start();
		//����һ���Ի���
		new AlertDialog.Builder(AlarmActivity.this).setTitle("����")
			.setMessage("�л������鿴���飡")
			.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//ֹͣ����
					alarmMusic.stop();
					AlarmActivity.this.finish();
					Intent mIntent = new Intent(AlarmActivity.this,ActivityInfoActivity.class);
					startActivity(mIntent);
				}
			}).show();
	}
}
