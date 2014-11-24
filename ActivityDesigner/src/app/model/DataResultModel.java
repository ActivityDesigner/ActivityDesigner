package app.model;

import android.util.Log;

/**
 * 
 * @author W.Z.L
 *
 */
public class DataResultModel {

	public int status = -1;// 成功0
	public String message = "";// 提示信息
	public String error = "";// 异常
	public int totalSize = 0;// 列表总数
	public Object data = null;// list
	public long lastTimestamp = 0;// 暂时不用
	public String datetime = "";// 时间
	public String success = "";//成功为success
	public String user_id = "";
	
	public void printLog(){
		Log.e("DataResultModel", "user_id:  "+user_id);
		Log.e("DataResultModel", "status:  "+status);
		Log.e("DataResultModel", "message:  "+message);
		Log.e("DataResultModel", "error:  "+error);
		Log.e("DataResultModel", "totalSize:  "+totalSize);
		Log.e("DataResultModel", "lastTimestamp:  "+lastTimestamp);
		Log.e("DataResultModel", "datetime:  "+datetime);
		Log.e("DataResultModel", "success:  "+success);
	}
}
