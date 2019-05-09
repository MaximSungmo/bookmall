package bookmall.vo;

public class OrdersVo {

	private int order_no;
	private int quantity;
	private int price;
	private String destination;
	private int member_no;
	private String order_no_view;
	private String order_date;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getOrder_no_view() {
		return order_no_view;
	}
	public void setOrder_no_view(String order_no_view) {
		this.order_no_view = order_no_view;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	@Override
	public String toString() {
		return "ordersDaoTest [order_no=" + order_no + ", quantity=" + quantity + ", price=" + price + ", destination="
				+ destination + ", member_no=" + member_no + ", order_no_view=" + order_no_view + ", order_date="
				+ order_date + "]";
	}
	
	
}
