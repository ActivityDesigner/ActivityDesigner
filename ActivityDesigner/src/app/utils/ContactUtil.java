package app.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.activitydesigner.R.integer;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

/**
 * 手机联系人相关操作的工具类
 * @author W.Z.L
 *
 */
public class ContactUtil {

	
	/**
	 * 获得联系人id的数组
	 * @param mActivity
	 * @return
	 */
	public static int[] getIdArray(Activity mActivity)
	{
		 int [] id = new int[10000];
		 int idTemp;
		 int index = 0;
		 //将通讯录返回的内容输入这样一个Array中
		Cursor cursor = mActivity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
	    		 null, null, null, null); 
	   if(cursor.moveToFirst())
	    {
	    	do
	    	{
	    		Log.d("aaa", cursor.toString());
	    		idTemp = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
	    		id[index] = idTemp;
	    		Log.d("getIdArray", String.valueOf(idTemp));
	    		index++;
	    		
	    	}while(cursor.moveToNext());
	    }
	    int [] result = new int[index];
	    for (int i = 0; i < index; i++) {
			result[i] = id[i];
		}
	    cursor.close();
	     return result;
	}
	
	/**
	 * 获得联系人id的一个list
	 * @param mActivity
	 * @return
	 */
	public static List<HashMap<String, Object>> getIdList(Activity mActivity)
	{
		 String [] id = new String[10000];
		 int idTemp;
		 int index = 0;
		 List<HashMap<String, Object>> listTemp = new ArrayList<HashMap<String, Object>>();
	     //将通讯录返回的内容输入这样一个ArrayList中
		Cursor cursor = mActivity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
	    		 null, null, null, null); 
	   if(cursor.moveToFirst())
	    {
	    	do
	    	{
	    	
	    		HashMap<String, Object> map = new HashMap<String, Object>();
	    		idTemp = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
	    		id[index] = cursor.getString(idTemp);
	    		map.put("contact_id",id[index]);
	    		listTemp.add(map);
	    		index++;
	    	}while(cursor.moveToNext());
	    }
	    
	     return listTemp;
	}
   
	/**
	 * 初始化数据 
	 * 将联系人的信息传入一个List中 以便视图的构造
	 * @return list 包含两类map
	 * @return map<contact_username,value>&&map<contact_id,value>
	 */
    public static List< HashMap<String, Object> > getdata(Activity mActivity)
	{
		 String [] id = new String[10000];
		 String [] name = new String[10000];
		 int idTemp;
		 int nameTemp;
		 int index = 0;
		 List<HashMap<String, Object>> listTemp = new ArrayList<HashMap<String, Object>>();
	     //将通讯录返回的内容输入这样一个ArrayList中
		 Log.d("getdata", "2");
	     Cursor cursor = mActivity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
	    		 null, null, null, null); 
	     Log.d("getdata", "3");
	    if(cursor.moveToFirst())
	    {
	    	do
	    	{
	    	
	    		HashMap<String, Object> map = new HashMap<String, Object>();
	    		idTemp = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
	    		nameTemp = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
	    		id[index] = cursor.getString(idTemp);
	    		name[index] = cursor.getString(nameTemp);
	    		map.put("contact_username",name[index]);
	    		map.put("contact_id",id[index]);
	    		listTemp.add(map);
	    		index++;
	    	}while(cursor.moveToNext());
	    }
	    
	     return listTemp;
	}
    
    /**
	 * 通过联系人ID 查询联系人的电话
	 * @return 返回内容为联系人电话的数组
	 */
	public static String[] getPhoneNum(Activity mActivity,int[] contact_id,int personCount)
	{
		int index = 0;
    	//int i = 0;
    	String [] phoneNum = new String[1000];
    	for(index = 0; index != personCount ; index++)
    	{
    	    String tempString = Integer.toString(contact_id[index]);
    	    Cursor phones = mActivity.getContentResolver().query
        			(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
        			   ContactsContract.CommonDataKinds.Phone.CONTACT_ID + 
        			   "=" + tempString,null, null );
    	    while(phones.moveToNext())
        	{
        		phoneNum[index] = phones.getString
        		( phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER) );
        	}
    	    phones.close();
    	    Log.d("ContactListActivity","查询到的联系人号码为："+ phoneNum[index]);
    	}
    	return phoneNum;
	}
	
	
	public static List<String> getPhoneFromName(Activity mActivity,String []select_name){
		
		List<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();  
		List<String> phoneList = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		ContentResolver cr = mActivity.getContentResolver();  
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,  
                null ,  null ,  null ,  null );  
        if  (cur.getCount() >  0 ) { 
        map = new HashMap<String, String>();
        while  (cur.moveToNext()) {  
            String id = cur.getString(  
                        cur.getColumnIndex(ContactsContract.Contacts._ID));  
            String name = cur.getString(  
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d("ContactUtil", String.valueOf(name));
         if  (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) >  0 ) {  
              Cursor pCur = cr.query(  
        			 ContactsContract.CommonDataKinds.Phone.CONTENT_URI,   
        			 null ,   
        			 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?" ,   
        			 new  String[]{id},  null );  
        			    while  (pCur.moveToNext()) {  
        			 // Do something with phones   
        			    	String phone = pCur.getString(
        			    			pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        			    	Log.d("ContactUtil", String.valueOf(phone));
        			    	map.put(name, phone);
        			    	mList.add(map);
        			    }   
        			    pCur.close();  
            }  
         }  
      }  
        for (int i = 0; i < mList.size(); i++) {
			for (int j = 0; j < select_name.length; j++) {
				if (mList.get(i).get(select_name[j]) != null){
					if (!phoneList.contains(mList.get(i).get(select_name[j]))) {
						phoneList.add(mList.get(i).get(select_name[j]));	
					}
				}
			}
		}
        return phoneList;
	}
	
	/**public static List<String> getPhoneFromName(Activity mActivity,String[] name){
		
		List<String> mList = new ArrayList<String>();
	    String phoneNum = " ";  
	    ContentResolver cr = mActivity.getContentResolver();  
	    Cursor pCur = cr.query(  
	            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,  
	            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?",  
	            name, null);  
	    if (pCur.moveToFirst()) {  
	        phoneNum = pCur  
	                .getString(pCur  
	                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));  
	        if (phoneNum != null && phoneNum != " ") {
		        mList.add(phoneNum);	
			}
	        pCur.close();  
	    }  
	    return mList; 
	}**/
	
	 /**
	  * 通过联系人ID 查询联系人的姓名
	  * @return 返回内容为联系人姓名的数组
	  */
	public static String [] getPersonName(Activity mActivity,int[] contact_id,int personCount)
	{
		
	    	int index = 0;
	    	String [] PersonName = new String[1000];
	    	String [] result;
	    	for(index = 0; index != personCount ; index++)
	    	{
	    	    String tempString = Integer.toString(contact_id[index]);
	    	    Log.d("sd", tempString);
	    	    Cursor names = mActivity.getContentResolver().query
	        			(ContactsContract.Data.CONTENT_URI, null,
	        			   ContactsContract.CommonDataKinds.Identity._ID + 
	        			   "=" + tempString,null, null );
	    	    Log.d("ss",names.toString());
	    	    while(names.moveToNext())
	        	{
	    	    	PersonName[index] = names.getString
	        		( names.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME) );
	    	    	 Log.d("getPersonName","查询到的联系人姓名："+ PersonName[index]);
	    	    	
	        	}
	    	    names.close();
	    	}
	    	
	    	result = new String[personCount];
    	    for (int i = 0; i < personCount; i++) {
				result[i] = PersonName[i];
			}
    	    
	    	return result;
	}

	public static String [] getAllPersonName(Activity mActivity)
	{
		String [] result;
		String [] PersonName = new String[10000];
		int index = 0;
		ContentResolver resolver = mActivity.getContentResolver();   
        Uri uri = Uri.parse("content://com.android.contacts/contacts");   
        Cursor idCursor = resolver.query(uri, new String[] { "_id" }, null, null, null);
        while (idCursor.moveToNext()) {  
        	//获取到raw_contacts表中的id   
            int id = idCursor.getInt(0);    
            //根据获取到的ID查询data表中的数据 
        	 uri = Uri.parse("content://com.android.contacts/contacts/" + id + "/data");   
             Cursor dataCursor = resolver.query(uri, new String[] { "data1", "mimetype" }, null, null, null); 
             while (dataCursor.moveToNext()) {   
                 String data = dataCursor.getString(0);   
                 String type = dataCursor.getString(1);   
                 if ("vnd.android.cursor.item/name".equals(type))
                 {
                	 PersonName[index] = data; 
                	 Log.d("getPersonName","查询到的联系人姓名："+ PersonName[index]);
                	 index++;
                 }
                 else if ("vnd.android.cursor.item/phone_v2".equals(type))   ;
                     //sb.append(", phone=" + data);   
                 else if ("vnd.android.cursor.item/email_v2".equals(type))   ;
                     //sb.append(", email=" + data);   
             }  
        }
        result = new String[index];
	    for (int i = 0; i < index; i++) {
			result[i] = PersonName[i];
		}
	    
    	return result;
	}
}
