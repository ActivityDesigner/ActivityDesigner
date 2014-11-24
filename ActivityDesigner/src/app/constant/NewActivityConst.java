package app.constant;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import app.model.ActivityModel;
import app.model.ContactModel;
import app.model.SortModel;
import app.view.widget.ClearEditText;


public class NewActivityConst {

	//public static String Type; //活动类型
	
	public static String NAME; //名字
	
	public static String START_TIME; //开始时间
	
	public static String END_TIME; //结束时间
	
	public static String LOCATION; //地点
	
	public static List<ContactModel> Contact = null; //选中的联系人
	
	public static String NOTE; //备注
	
	public static List<ActivityModel> newActivity = new ArrayList<ActivityModel>();
	
	public static ActivityModel mLastModel = new ActivityModel();
	
	public static List<String> phoneNumList;
	
	public static void clearData(){ 
		NAME = "";
		START_TIME = "";
		END_TIME = "";
		LOCATION = "";
		Contact = new ArrayList<ContactModel>();
		phoneNumList = new ArrayList<String>();
		NOTE = "";
	}
	
	public static String getContactName(){
		String tmp = "";
		for (int i = 0; i < Contact.size(); i++) {
			tmp = tmp+" "+Contact.get(i).getNickname();
		}
		return tmp;
	}
	
	public static void addActivityInfo(ActivityModel mModel){
		if (newActivity != null) {
			newActivity.add(mModel);	
		}
	}
	
	public static void printPhoneNumList(){
		for (int i = 0; i < phoneNumList.size(); i++) {
			Log.d("NewActivityConst", phoneNumList.get(i));
		}
	}
}
