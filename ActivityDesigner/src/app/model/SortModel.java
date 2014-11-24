package app.model;

/**
 * ��ϵ���б�����
 * @author W.Z.L
 *
 */
public class SortModel {

	private String name;   //��ʾ������
	private String sortLetters;  //��ʾ����ƴ��������ĸ
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
