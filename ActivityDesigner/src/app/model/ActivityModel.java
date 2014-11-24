package app.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import app.constant.TypeConst;
import app.utils.AppUtil;

/**
 * 活动信息的模型
 * @author W.Z.L
 *
 */
public class ActivityModel extends BaseModel{

	private int id; 	         //活动id
	private String name;        //活动名称
	private Location location; //地点
	private String date;      //活动日期
	private String time;      //活动时间
	private User sponsor;    //活动发起人
	private User[] invite;   //活动参与人数组
	private int type;        //活动类型
	private int state;       //活动状态 -1表示未激活 0待确认 1正在进行 2已经结束
	private Object detail;   //活动的特别内容
	private boolean isChat;  //是否允许聊天
	private boolean isBallot; //是否允许投票
	private boolean isAlbum;  //是否允许相册
	private String note;     //个人备注

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String LOCATION = "location";
	private static final String DATE = "date";
	private static final String TIME = "time";
	private static final String SPONSOR = "sponsor";
	private static final String INVITE = "invite";
	private static final String TYPE = "type";
	private static final String STATE = "state";
	private static final String DETAIL = "detail";
	private static final String ISCHAT = "isChat";
	private static final String ISBALLOT = "isBallot";
	private static final String ISALBUM = "isAlbum";
	private static final String NOTE = "note";
	
	public static ActivityModel parse(JSONObject object) throws JSONException {
		JSONObject jsonObject;
		JSONArray jsonArray;
		ActivityModel bean = new ActivityModel();
		if (object.has(ID)) {
			bean.setId(object.getInt(ID));
		}
		if (object.has(NAME)) {
			bean.setName(object.getString(NAME));
		}
		if (object.has(LOCATION)) {
			jsonObject = object.getJSONObject(LOCATION);
			bean.setLocation(Location.parse(jsonObject));
		}
		if (object.has(DATE)) {
			bean.setDate(object.getString(DATE));
		}
		if (object.has(TIME)) {
			bean.setTime(object.getString(TIME));
		}
		if (object.has(SPONSOR)) {
			jsonObject = object.getJSONObject(SPONSOR);
			bean.setSponsor(User.parse(jsonObject));
		}
		if (object.has(INVITE)) {
			jsonArray = object.getJSONArray(INVITE);
			User [] users = new User[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				users[i] = User.parse(jsonObject);
			}
			bean.setInvite(users);
		}
		if (object.has(TYPE)) {
			bean.setType(object.getInt(TYPE));
		}
		if (object.has(STATE)) {
			bean.setState(object.getInt(STATE));
		}
		if (object.has(DETAIL)) {
			jsonObject = object.getJSONObject(DETAIL);
			
		}
		if (object.has(ISCHAT)) {
			bean.setIsChat(object.getBoolean(ISCHAT));
		}
		if (object.has(ISBALLOT)) {
			bean.setIsBallot(object.getBoolean(ISBALLOT));
		}
		if (object.has(ISALBUM)) {
			bean.setIsAlbum(object.getBoolean(ISALBUM));
		}
		if (object.has(NOTE)) {
			bean.setNote(object.getString(NOTE));
		}
		return bean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getSponsor() {
		return sponsor;
	}

	public void setSponsor(User sponsor) {
		this.sponsor = sponsor;
	}

	public User[] getInvite() {
		return invite;
	}

	public void setInvite(User[] invite) {
		this.invite = invite;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

	public boolean isChat() {
		return isChat;
	}

	public void setIsChat(boolean isChat) {
		this.isChat = isChat;
	}

	public boolean isBallot() {
		return isBallot;
	}

	public void setIsBallot(boolean isBallot) {
		this.isBallot = isBallot;
	}

	public boolean isAlbum() {
		return isAlbum;
	}

	public void setIsAlbum(boolean isAlbum) {
		this.isAlbum = isAlbum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	private void findType(int type,JSONObject jsonObject){
		
		//根据type判断类型
		switch (type) {
		case 0:
			//工作
			
			break;
		case 1:
			//会议
			break;
		case 2:
			//消费
			break;
		case 3:
			//运动
			break;
		case 4:
			//吃饭
			break;
		case 5:
			//自定义
			break;
		default:
			break;
		}
	}
}
