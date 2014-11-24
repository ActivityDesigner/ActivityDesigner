package app.constant;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import app.model.ActivityModel;
import app.model.ContactModel;
import app.model.SortModel;
import app.view.widget.ClearEditText;


public class NewActivityConst {

	//public static String Type; //�����
	
	public static String NAME; //����
	
	public static String START_TIME; //��ʼʱ��
	
	public static String END_TIME; //����ʱ��
	
	public static String LOCATION; //�ص�
	
	public static List<ContactModel> Contact = null; //ѡ�е���ϵ��
	
	public static String NOTE; //��ע
	
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
