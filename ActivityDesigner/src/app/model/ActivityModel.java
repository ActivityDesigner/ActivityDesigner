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
 * ���Ϣ��ģ��
 * @author W.Z.L
 *
 */
public class ActivityModel extends BaseModel{

	private int id; 	         //�id
	private String name;        //�����
	private Location location; //�ص�
	private String date;      //�����
	private String time;      //�ʱ��
	private User sponsor;    //�������
	private User[] invite;   //�����������
	private int type;        //�����
	private int state;       //�״̬ -1��ʾδ���� 0��ȷ�� 1���ڽ��� 2�Ѿ�����
	private Object detail;   //����ر�����
	private boolean isChat;  //�Ƿ���������
	private boolean isBallot; //�Ƿ�����ͶƱ
	private boolean isAlbum;  //�Ƿ��������
	private String note;     //���˱�ע

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
		
		//����type�ж�����
		switch (type) {
		case 0:
			//����
			
			break;
		case 1:
			//����
			break;
		case 2:
			//����
			break;
		case 3:
			//�˶�
			break;
		case 4:
			//�Է�
			break;
		case 5:
			//�Զ���
			break;
		default:
			break;
		}
	}
}
