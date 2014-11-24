package app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.example.activitydesigner.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import app.constant.CommonConst;

public class AppUtil {

	/**
	 * ���ص�ǰ����汾��
	 */
	public static String getAppVersionName(Context context) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return pi.versionName;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * ���ص�ǰ����汾��
	 */
	public static String getAppVersionCode(Context context) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return String.valueOf(pi.versionCode);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}


	
	/**
	 * ���Ͷ���
	 * @param phone
	 * @param personName
	 * @param phoneIndex
	 * @param MSG_content
	 */
	public static void Send(String [] phone,String [] personName,int phoneIndex,String MSG_content){
	    	String temp = MSG_content;
	    	for(int i = 0; i != phoneIndex;++i)
	    	{
	    		SmsManager smsManager = SmsManager.getDefault();
	    	    smsManager.sendTextMessage(phone[i], null, temp, null, null);//���Ͷ���
	    	    Log.d("SendMsg","������"+personName[i]+"�绰��"+phone[i]+"����Ϊ��"+ temp);
	    	}
	    		Log.d("SendMsg", "���ŷ������");	
	    }
	
	/**
	 * �����û������Լ���ǰʱ��,�Լ�һ�������,�����ϴ���Ƭ��id
	 */
	public static String genertatePhotoId(){
	
		String result;
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy��MM��dd�� HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());//��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		int number = new Random().nextInt(1000) + 1; 
		result = CommonConst.current_username+str+String.valueOf(number);
		return result;
	}
	
	
}
