package app.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * นคื๗
 * @author lenovo
 *
 */
public class ActivityWorkModel extends BaseModel{

	private int id;
	private String[] subtasks;
	private User[] chargers;
	
	private static final String ID = "id";
	private static final String SUBTASKS = "subtasks";
	private static final String CHARGERS = "chargers";
	
	public static ActivityWorkModel parse(JSONObject object) throws JSONException{
		JSONArray jsonArray;
		JSONObject jsonObject;
		ActivityWorkModel bean = new ActivityWorkModel();
		if (object.has(ID)) {
			bean.setId(object.getInt(ID));
		}if (object.has(SUBTASKS)) {
			jsonObject = object.getJSONObject(SUBTASKS);
			
		}if (object.has(CHARGERS)) {
			jsonObject = object.getJSONObject(CHARGERS);
		}
		
		return bean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(String[] subtasks) {
		this.subtasks = subtasks;
	}

	public User[] getChargers() {
		return chargers;
	}

	public void setChargers(User[] chargers) {
		this.chargers = chargers;
	}
}
