package app.model;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 活动相册模型
 * @author W.Z.L
 *
 */
public class ActivityAlbumModel extends BaseModel{

	private static final long serialVersionUID = 1L;
	private String id;
	private String hasChildren;
	private String title;
	private String isFolder;
	private String description;
	private String owner;
	private String longitude; //经度
	private String latitude;  //纬度
	private static final String ID = "id";
	private static final String HASCHILDREN = "hasChildren";
	private static final String TITLE = "title";
	private static final String ISFOLDER = "isFolder";
	private static final String DESCRIPTION = "description";
	private static final String OWNER = "owner";
	private static final String LONGITUDE = "longitude";
	private static final String LATITUDE = "latitude";
	
	public static ActivityAlbumModel parse(JSONObject object) throws JSONException {
		ActivityAlbumModel bean = new ActivityAlbumModel();
		if (object.has(ID)) {
			bean.id = object.getString(ID);
		}
		if (object.has(HASCHILDREN)) {
			bean.hasChildren = object.getString(HASCHILDREN);
		}
		if (object.has(TITLE)) {
			bean.title = object.getString(TITLE);
		}
		if (object.has(ISFOLDER)) {
			bean.isFolder = object.getString(ISFOLDER);
		}
		if (object.has(DESCRIPTION)) {
			bean.description = object.getString(DESCRIPTION);
		}
		if (object.has(OWNER)) {
			bean.owner = object.getString(OWNER);
		}
		if (object.has(LONGITUDE)) {
			bean.longitude = object.getString(LONGITUDE);
		}
		if (object.has(LATITUDE)) {
			bean.latitude = object.getString(LATITUDE);
		}
		return bean;
	}
	
}
