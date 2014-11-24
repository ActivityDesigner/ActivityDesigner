package app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Location extends BaseModel{

	private String name;
	private double longtitude;
	private double latituede;
	
	private static final String NAME = "name";
	private static final String LONGTITUDE = "longtitude";
	private static final String LATITUEDE = "latituede";
	
	public static Location parse(JSONObject object) throws JSONException {
		Location bean = new Location();
		if (object.has(NAME)) {
			bean.setName(object.getString(NAME));
		}
		if (object.has(LONGTITUDE)) {
			bean.setLongtitude(object.getDouble(LONGTITUDE));
		}
		if (object.has(LATITUEDE)) {
			bean.setLatituede(object.getDouble(LATITUEDE));
		}
		return bean;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getLatituede() {
		return latituede;
	}
	public void setLatituede(double latituede) {
		this.latituede = latituede;
	}
	
}
