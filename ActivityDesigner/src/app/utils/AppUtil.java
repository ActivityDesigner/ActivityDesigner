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
	 * 返回当前程序版本名
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
	 * 返回当前程序版本号
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
	 * 发送短信
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
	    	    smsManager.sendTextMessage(phone[i], null, temp, null, null);//发送短信
	    	    Log.d("SendMsg","姓名："+personName[i]+"电话："+phone[i]+"内容为："+ temp);
	    	}
	    		Log.d("SendMsg", "短信发送完毕");	
	    }
	
	/**
	 * 根据用户名，以及当前时间,以及一个随机数,生成上传照片的id
	 */
	public static String genertatePhotoId(){
	
		String result;
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		int number = new Random().nextInt(1000) + 1; 
		result = CommonConst.current_username+str+String.valueOf(number);
		return result;
	}
	
	
}
