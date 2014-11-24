package app.model;

/**
 * 联系人列表排序
 * @author W.Z.L
 *
 */
public class SortModel {

	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private boolean isSelected = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public void setSelected(){
		isSelected = true;
	}
	public void setUnselected(){
		isSelected = false;
	}
	public boolean hasSelected(){
		return isSelected;
	}
}
