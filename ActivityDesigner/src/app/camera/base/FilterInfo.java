package app.camera.base;


public class FilterInfo {

	public int filterID;
	public MyImageFilter filter;
	public boolean isSelect;

	public FilterInfo(int filterID, MyImageFilter filter,boolean isSelect) {
		this.filterID = filterID;
		this.filter = filter;
		this.isSelect = isSelect;
	}
	
}
