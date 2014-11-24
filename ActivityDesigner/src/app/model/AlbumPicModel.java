package app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author W.Z.L
 *
 */
public class AlbumPicModel extends BaseModel{

	private static final long serialVersionUID = 1L;
	///
		public String id;
		public String smallImageURL;
		public String title;
		public String height;
		public String width;
		public String imageURL;
		////

	private static final String ID = "id";
	private static final String SMALLIMAGEURL = "smallImageURL";
	private static final String TITLE = "title";
	private static final String HEIGHT = "height";
	private static final String WIDTH = "width";
	private static final String IMAGEURL = "imageURL";
	public static AlbumPicModel parse(JSONObject object) throws JSONException {
		AlbumPicModel bean = new AlbumPicModel();
		if (object.has(ID)) {
			bean.id = object.getString(ID);
		}
		if (object.has(SMALLIMAGEURL)) {
			bean.smallImageURL = object.getString(SMALLIMAGEURL);
		}
		if (object.has(TITLE)) {
			bean.title = object.getString(TITLE);
		}
		if (object.has(HEIGHT)) {
			bean.height = object.getString(HEIGHT);
		}
		if (object.has(WIDTH)) {
			bean.width = object.getString(WIDTH);
		}
		if (object.has(IMAGEURL)) {
			bean.imageURL = object.getString(IMAGEURL);
		}
		return bean;
	}
}
