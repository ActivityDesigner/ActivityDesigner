package app.model;

import java.util.List;

import android.R.string;

/**
 * 通知信息的模型
 * @author W.Z.L
 *
 */
public class MsgModel extends BaseModel{

	public long id; 
	public int type = 11; //活动类型
	public int typeDrawableId;
	public String name = "无"; //活动名称
	public String timeStart = "无"; //活动开始时间
	public String timeEnd = "无";  //活动结束时间
	public String date = "无"; //日期
	public String location = "无";//地点
	public String note = "无"; //备注
	public int state; //活动状态 -1表示未激活 0正在进行 1已经结束
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
