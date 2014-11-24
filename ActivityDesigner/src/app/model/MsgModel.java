package app.model;

import java.util.List;

import android.R.string;

/**
 * ֪ͨ��Ϣ��ģ��
 * @author W.Z.L
 *
 */
public class MsgModel extends BaseModel{

	public long id; 
	public int type = 11; //�����
	public int typeDrawableId;
	public String name = "��"; //�����
	public String timeStart = "��"; //���ʼʱ��
	public String timeEnd = "��";  //�����ʱ��
	public String date = "��"; //����
	public String location = "��";//�ص�
	public String note = "��"; //��ע
	public int state; //�״̬ -1��ʾδ���� 0���ڽ��� 1�Ѿ�����
	public List<ContactModel> contact;
	public String yes;
	public String no;
	
	public MsgModel(){
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setTime(String time){
		this.timeStart = time;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public void setYes(String yes){
		this.yes = yes;
	}
	public void setNo(String no){
		this.no = no;
	}
	public String getName(){
		return name;
	}
	
	public String getDate(){
		return date;
	}
	public String getTime(){
		return timeStart;
	}
	public String getLocation(){
		return location;
	}
	public String getNote(){
		return note;
	}
	public String getYes(){
		return yes;
	}
	public String getNo(){
		return no;
	}
	
	public List<ContactModel>  getContact(){
		return contact;
	}
}
