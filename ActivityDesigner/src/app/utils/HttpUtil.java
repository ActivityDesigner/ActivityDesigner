package app.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.UserDataHandler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import app.constant.CommonConst;
import app.constant.UrlConst;
import app.model.ActivityAlbumModel;
import app.model.ActivityModel;
import app.model.AlbumPicModel;
import app.model.DataResultModel;
import app.model.MsgModel;

public class HttpUtil extends CommonConst{


	private static final String TAG = "HttpUtil";	
	/**
	 * ���������Ƿ����
	 */
	public static boolean isConnnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connectivityManager) {
			NetworkInfo networkInfo[] = connectivityManager.getAllNetworkInfo();

			if (null != networkInfo) {
				for (NetworkInfo info : networkInfo) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						Log.e(TAG, "the net is ok");
						return true;
					}
				}
			}
		}
//		Toast.makeText(context, "��������ʧ��", Toast.LENGTH_SHORT).show();
		return false;
	}
	
	/**
	 * ע��
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public static DataResultModel register(String phoneNum,String password) {
		DataResultModel result = new DataResultModel();
		String jsonString = null;
		try {			
			 ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		     params.add(new BasicNameValuePair("Reg_username", phoneNum));
		     params.add(new BasicNameValuePair("Reg_password", password));
		     String str = UrlConst.register_url;
		     Log.e("register", "url:  "+str);
		     jsonString = HttpUtil.getResponseForPost(str,params,"cookie");			
			 Log.e("register", "jsonString:  "+jsonString);
		     if(jsonString != null && !jsonString.equals("")){
			 JSONObject jsonObject = new JSONObject(jsonString);
			 String cookie = null ;
			 Log.e("register", "JSONObject:  "+jsonObject.toString());
			 if (jsonObject.has("SUCCESS")) {
				String success = jsonObject.getString("SUCCESS");
				if(success.equals("true")){
					String resultStr = jsonObject.getString("content");
		    		JSONObject contentObj = new JSONObject(resultStr);
		    		String u_phone = contentObj.getString("u_phone");
		    		Log.e("u_phone", "1");
		    		result.message = cookie;
				}
				 	result.success = success;
			}
			if(jsonObject.has("errorMsg")){
				result.error = jsonObject.getString("errorMsg");
			}
			if(jsonObject.has("errorCode")){
				result.status = jsonObject.getInt("errorCode");
				}
			jsonObject = null;
			}else{
			}
		} catch (JSONException e) {
			Log.e(TAG, e.toString(), e);
			result.message = JSONExceptionMessage;
			result.error = ExceptionUtil.printException(e).append(jsonString)
					.toString();
			result.status = JSONExceptionCode;
		}
		return result;
	}
	
	

	/**
	 * ��¼
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public static DataResultModel login(String phoneNum,String password){
		DataResultModel result = new DataResultModel();
		String jsonString = null;
		try {
				List<NameValuePair> mdata = new ArrayList<NameValuePair>();
		        String str = UrlConst.login_url;
		        NameValuePair user = new BasicNameValuePair("Log_username",phoneNum);
		        NameValuePair pass = new BasicNameValuePair("Log_password", password);
		        mdata.add(user);
		        mdata.add(pass);
			    jsonString = HttpUtil.getResponseForPost(str,mdata,"cookie");
			    Log.e("login", jsonString);
			    if(jsonString != null && !jsonString.equals("")){
			      JSONObject jsonObject = new JSONObject(jsonString);
			      String cookie = null ; 
			      Log.e("jsonObject", jsonObject.toString());
			      if (jsonObject.has("SUCCESS")) {
			    	  Log.e("jsonObject", "SUCCESS");
			    	  String success = jsonObject.getString("SUCCESS");
			    	 if(success.equals("true")){
			    		 result.user_id = jsonObject.getString("user_id");
				    	 result.success = success;
				    	   
				      }		
			}
			if(jsonObject.has("errorMsg")){
				result.error = jsonObject.getString("errorMsg");
			}
			if(jsonObject.has("errorCode")){
				result.status = jsonObject.getInt("errorCode");
				}
			jsonObject = null;
			}else{
			}
		} catch (JSONException e) {
			Log.e("JSONException ", e.toString(), e);
			result.message = JSONExceptionMessage;
			result.error = ExceptionUtil.printException(e).append(jsonString)
					.toString();
			result.status = JSONExceptionCode;
		}
		Log.e("result", result.success);
		Log.e("result", result.user_id);
		return result;
	}
	/**
	 * ͨ��Get��ʽ�������󣬲�������Ӧ����
	 * 
	 * @param strUrl ������ַ
	 * @return ��Ӧ��JSON����
	 */
	public static String getResponseForGet(String strUrl,String cookie) {
		HttpGet httpRequest = new HttpGet(strUrl);
	  	if(cookie!=null&&!cookie.equals("")){
		httpRequest.setHeader("USER_TOKEN", cookie);
		}
		return getRespose(httpRequest);
	}
	
	/**
	 * ��Ӧ�ͻ�������
	 * 
	 * @param request �ͻ�������get/post
	 * @return ��Ӧ����
	 */
	public static String getRespose(HttpUriRequest request) {
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			Log.e(TAG, "statusCode=" + statusCode);
			if (HttpStatus.SC_OK == statusCode) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				Log.e(TAG, "results=" + result);
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ͨ��post��ʽ��������������󣬲�������Ӧ����
	 * 
	 * @param strUrl ������ַ
	 * @param nameValuePairs ������Ϣ
	 * @return ��Ӧ����
	 */
	public static String getResponseForPost(String market_uri, List<NameValuePair> nameValuePairs,String cookie) {
		if (null == market_uri || "" == market_uri) {
			return null;
		}
		HttpPost request = new HttpPost(market_uri);
		if(cookie!=null&&!cookie.equals("")){
		request.setHeader("USER_TOKEN", cookie);
		}
		try {
			if(nameValuePairs!=null){
				
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8);
			request.setEntity(ent);
			}
			return getRespose(request);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return null;
	}
		
	/**
	 * �ϴ��û�ͷ��
	 */
	public static void uploadProfile(){
		
	}
	
	/**
	 * ����û�ͷ��
	 */
	public static void getProfile(){
		
	}
	
	/**
	 * ��ӻ
	 * @throws JSONException 
	 */
	public static DataResultModel addActivity(ActivityModel activityInfoModel) throws JSONException{
		
		DataResultModel result = new DataResultModel();
		String jsonString;
		String cookie = null;
		if (activityInfoModel == null) {
			activityInfoModel = new ActivityModel();
			Log.e("HttpUtil", "addActivity activityInfoModel==null");
		}
		List<NameValuePair> mdata = new ArrayList<NameValuePair>();
		String url = UrlConst.add_activity_url;
		Log.e("AddActivity", CommonConst.current_id);
		BasicNameValuePair id = new BasicNameValuePair("user_id",CommonConst.current_id);
		//BasicNameValuePair type = new BasicNameValuePair("Ac_type", activityInfoModel.getType());
		BasicNameValuePair name  = new BasicNameValuePair("Ac_name",activityInfoModel.getName());
		//BasicNameValuePair location = new BasicNameValuePair("Ac_location",activityInfoModel.getLocation());
		BasicNameValuePair date = new BasicNameValuePair("Ac_date",activityInfoModel.getDate()); 
		//BasicNameValuePair beginTime = new BasicNameValuePair("Ac_beginTime",activityInfoModel.getTimeStart());
		//BasicNameValuePair invited = new BasicNameValuePair("Ac_invited", activityInfoModel.getContact().toString());
		BasicNameValuePair note = new BasicNameValuePair("Ac_note", activityInfoModel.getNote());
		//mdata.add(type);
		mdata.add(id);
		mdata.add(name);
		//mdata.add(location);
		mdata.add(date);
		//mdata.add(beginTime);
		//mdata.add(invited);
		mdata.add(note);
		jsonString = getResponseForPost(url,mdata, cookie);
		 if(jsonString != null && !jsonString.equals("")){
		      JSONObject jsonObject = new JSONObject(jsonString);
		      if (jsonObject.has("SUCCESS")) {
		    	  String success = jsonObject.getString("SUCCESS");
		    	  String user_id = jsonObject.getString("user_id");
		    	  if(success.equals("true")){
		    		  result.success = success;
		    		  result.user_id = user_id;
		    	  }
		}
		if(jsonObject.has("errorMsg")){
			result.error = jsonObject.getString("errorMsg");
		}
		if(jsonObject.has("errorCode")){
			result.status = jsonObject.getInt("errorCode");
			}
		jsonObject = null;
		}else{
		}
	   return result;
	}

	
	/**
	 * ��ȡ���Ϣ
	 * @return
	 */
	public static DataResultModel getActivity(){
	DataResultModel result = new DataResultModel();
	List<ActivityModel> mList = new ArrayList<ActivityModel>();
		String jsonString = null;
		try {
		        String str = UrlConst.get_activity_url;	 
		        List<NameValuePair> list = new ArrayList<NameValuePair>();
		        NameValuePair pair = new BasicNameValuePair("user_id",CommonConst.current_id);
		        list.add(pair);
			    jsonString = HttpUtil.getResponseForPost(str,list,"cookie");
			    Log.e("getActivity:", jsonString);
			    if(jsonString != null && !jsonString.equals("")){
			      JSONObject jsonObject = new JSONObject(jsonString);
			      String cookie = null ;
			      if (jsonObject.has("SUCCESS")) {
			    	  String success = jsonObject.getString("SUCCESS");
			    	  if(success.equals("true")){
			    		  String resultStr = jsonObject.getString("content");
				    	  JSONArray contentObjArray = new JSONArray(resultStr);
				    	  for (int i = 0; i < contentObjArray.length(); i++) {
								ActivityModel myAlbum = ActivityModel.parse(contentObjArray.getJSONObject(i));
								mList.add(myAlbum);
								//myAlbum.print();
							}
						  result.data = mList;
				    	  result.message = cookie;
				}
		
			    	  result.success = success;
			}
			if(jsonObject.has("errorMsg")){
				result.error = jsonObject.getString("errorMsg");
			}
			if(jsonObject.has("errorCode")){
				result.status = jsonObject.getInt("errorCode");
				}
			jsonObject = null;
			}else{
			}
		} catch (JSONException e) {
			Log.e(TAG, e.toString(), e);
			result.message = JSONExceptionMessage;
			result.error = ExceptionUtil.printException(e).append(jsonString)
					.toString();
			result.status = JSONExceptionCode;
		}
		return result;
	}
	
	/**
	 * ���ͻ֪ͨ
	 */
	public static void sendMsg(MsgModel msgModel){
		
		String cookie = null;
		if (msgModel == null) {
			msgModel = new MsgModel();
			Log.e("HttpUtil", "sendMsg msgModel==null");
		}
		List<NameValuePair> mdata = new ArrayList<NameValuePair>();
		String url = UrlConst.send_msg_url;
		BasicNameValuePair id = new BasicNameValuePair("Log_uid","1");
		BasicNameValuePair name  = new BasicNameValuePair("Ac_name",msgModel.getName());
		BasicNameValuePair location = new BasicNameValuePair("Ac_location",msgModel.getLocation());
		BasicNameValuePair date = new BasicNameValuePair("Ac_date",msgModel.getDate()); 
		BasicNameValuePair beginTime = new BasicNameValuePair("Ac_beginTime",msgModel.getTime());
		BasicNameValuePair invited = new BasicNameValuePair("Ac_invited", msgModel.getContact().toString());
		BasicNameValuePair note = new BasicNameValuePair("Ac_note", msgModel.getNote());
		mdata.add(id);
		mdata.add(name);
		mdata.add(location);
		mdata.add(date);
		mdata.add(beginTime);
		mdata.add(invited);
		mdata.add(note);
		getResponseForPost(url,mdata, cookie);
	}
	
	/**
	 * ��û֪ͨ
	 * @return
	 */
	public static DataResultModel getMsg(){
		DataResultModel result = new DataResultModel();
		String jsonString = null;
		try {			
			 ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		     params.add(new BasicNameValuePair("_id", CommonConst.current_id));
		     String str = UrlConst.get_msg_url;
		     jsonString = HttpUtil.getResponseForPost(str,params,"cookie");			
			 if(jsonString != null && !jsonString.equals("")){
			 JSONObject jsonObject = new JSONObject(jsonString);
			 String cookie = null ;
			 if (jsonObject.has("SUCCESS")) {
				String success = jsonObject.getString("SUCCESS");
				if(success.equals("true")){
					String resultStr = jsonObject.getString("content");
		    		JSONObject contentObj = new JSONObject(resultStr);
		    		String u_phone = contentObj.getString("msg_name");
		    		result.message = cookie;
				}
				 	result.success = success;
			}
			if(jsonObject.has("errorMsg")){
				result.error = jsonObject.getString("errorMsg");
			}
			if(jsonObject.has("errorCode")){
				result.status = jsonObject.getInt("errorCode");
				}
			jsonObject = null;
			}else{
			}
		} catch (JSONException e) {
			Log.e(TAG, e.toString(), e);
			result.message = JSONExceptionMessage;
			result.error = ExceptionUtil.printException(e).append(jsonString)
					.toString();
			result.status = JSONExceptionCode;
		}
		return result;
	}
	
	/**
	 * �ϴ�����ͼƬ
	 * @param filePath
	 */
	public static void uploadActivityPhoto(String filePath)
	{
		//����fileKey
		String key = AppUtil.genertatePhotoId();
		Map<String, String> param = new HashMap<String, String>();
		String url = UrlConst.upload_activity_photo_url;
		UploadUtil.getInstance().
		uploadFile(filePath,key,url, param);
	}
	
	/**
	 * ��û���ͼƬ
	 * @return
	 */
	public static DataResultModel getActivityPhoto(){
		
		String mcookie = "";
  	    AlbumPicModel picModel = new AlbumPicModel();
		ArrayList<AlbumPicModel> list = new ArrayList<AlbumPicModel>();
		DataResultModel result = new DataResultModel();
		String jsonString = null;
		try {
		    String str = UrlConst.get_activity_photo_url;
		    //����һ��id
		    jsonString = HttpUtil.getResponseForGet(str,mcookie);
			if(jsonString!=null&&!jsonString.equals("")){
			  JSONObject jsonObject = new JSONObject(jsonString);
			  String cookie = null ;
			  if (jsonObject.has(SUCCESS)) {
				  String success = jsonObject.getString(SUCCESS);
				  if(success.equals("true")){
					  String resultStr = jsonObject.getString("result");
					  JSONObject contentObj = new JSONObject(resultStr);
					  String content = contentObj.getString("content");
					  JSONArray contentObjArr = new JSONArray(content);
					  for (int i = 0; i < contentObjArr.length(); i++) {
						  JSONObject childObj = (JSONObject) contentObjArr.get(i);
						  picModel = AlbumPicModel.parse(childObj);
					      list.add(picModel);
					  }
					result.data = list;
				}
		
				result.success = success;
			}
			if(jsonObject.has("errorMsg")){
				result.error = jsonObject.getString("errorMsg");
			}
			if(jsonObject.has("errorCode")){
				result.status = jsonObject.getInt("errorCode");
				}
			jsonObject = null;
			}else{
			}
		} catch (JSONException e) {
				Log.e(TAG, e.toString(), e);
				result.message = JSONExceptionMessage;
				result.error = ExceptionUtil.printException(e).append(jsonString)
					.toString();
				result.status = JSONExceptionCode;
		}
		return result;
	}

	/**
	 * �޸�����
	 * @return
	 */
	public static DataResultModel modifyPassword(String pass_1,String pass_2){
		DataResultModel result = new DataResultModel();
		return result;
	}
	
	/**
	 * ������Ϣ
	 * @param msg
	 * @return
	 */
	public static DataResultModel SendfeedBack(String msg){
		DataResultModel resultModel = new DataResultModel();
		return resultModel;
	}
}
