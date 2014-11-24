package app.model;

import android.util.Log;

/**
 * 
 * @author W.Z.L
 *
 */
public class DataResultModel {

	public int status = -1;// �ɹ�0
	public String message = "";// ��ʾ��Ϣ
	public String error = "";// �쳣
	public int totalSize = 0;// �б�����
	public Object data = null;// list
	public long lastTimestamp = 0;// ��ʱ����
	public String datetime = "";// ʱ��
	public String success = "";//�ɹ�Ϊsuccess
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
