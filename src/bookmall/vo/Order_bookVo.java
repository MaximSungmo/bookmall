package bookmall.vo;

public class Order_bookVo {

	private int book_no;
	private int order_no;
	private int quantity;
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "order_bookVo [book_no=" + book_no + ", order_no=" + order_no + ", quantity=" + quantity + "]";
	}
	
	
}
