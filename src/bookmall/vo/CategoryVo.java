package bookmall.vo;

public class CategoryVo {

	private int category_no;
	private String category_name;
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	@Override
	public String toString() {
		return "categoryVo [category_no=" + category_no + ", category_name=" + category_name + "]";
	}
	
	
}
