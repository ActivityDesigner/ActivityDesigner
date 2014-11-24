package app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户模型
 * @author W.Z.L
 *
 */
public class User extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private String id; //id
	private String username; //用户名(电话号码)
	private String password; //密码
	private String description; //描述
	private String gender;  //性别
	private String name;  //昵称
	private String avatar;  //头像的地址
	
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String DESCRIPTION = "description";
	private static final String GENDER = "gender";
	private static final String NAME = "name";
	private static final String AVATAR = "avatar";

	public static User parse(JSONObject object) throws JSONException {
		User bean = new User();
		if (object.has(ID)) {
			bean.setId(object.getString(ID));
		}
		if (object.has(USERNAME)) {
			bean.setUsername(object.getString(USERNAME));
		}
		if (object.has(PASSWORD)) {
			bean.setUsername(object.getString(PASSWORD));
		}
		if (object.has(DESCRIPTION)) {
			bean.setDescription(object.getString(DESCRIPTION));
		}
		if (object.has(GENDER)) {
			bean.setGender(object.getString(GENDER));
		}
		if (object.has(NAME)) {
			bean.setName(object.getString(NAME));
		}
		if (object.has(AVATAR)) {
			bean.setAvatar(object.getString(AVATAR));
		}
		return bean;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
