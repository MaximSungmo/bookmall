package bookmall.vo;

public class CartVo {

	private int book_no;
	private int member_no;
	private int quantity;
	private int price;
	
	private String title;
	private String name;
	
	
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CartVo [book_no=" + book_no + ", member_no=" + member_no + ", quantity=" + quantity + ", price=" + price
				+ ", title=" + title + ", name=" + name + "]";
	}
	
	
}
