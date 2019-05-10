package bookmall.test;

import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.Order_bookVo;
import bookmall.vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {


		
		System.out.println("----------OrdersDaoTest_insertTest----------");
		// 배송지, 멤버번호, 북번호, 수량
		insertTest("경기도 성남시 분당구", 2, 1000, 2, 3);
		insertTest("경기도 성남시 분당구", 1, 5000, 3, 1);
		insertTest("서울특별시 강남구", 2, 5000, 3, 3);
		insertTest("서울특별시 강남구", 2, 7000, 1, 2);
		System.out.println("--------------------------------------------");
		
		System.out.println("----------OrdersDaoTest_getListTest---------");
		getListTest(2);
		getList_orders_book(135);
		System.out.println("--------------------------------------------");

	}

	public static void getListTest(int member_no) {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList(member_no);
		for (OrdersVo vo : list) {
			System.out.println("주문번호 : "+vo.getOrder_no_view() + ", 주문자(이름/이메일) : "+vo.getMember_no()+", 결제금액 : "+vo.getPrice()+", 배송지 : "+ vo.getDestination());
		}
		
	}
	public static void getList_orders_book(int order_no) {
		OrdersDao dao = new OrdersDao();
		List<Order_bookVo> list = dao.getList_orders_book(order_no);
		for (Order_bookVo vo : list) {
			System.out.println("주문번호 : "+ vo.getOrder_no()+" 리스트 출력");
			System.out.println("도서번호 : "+vo.getBook_no()+", 도서제목 : "+ vo.getTitle() +", 수량 : "+vo.getQuantity());
		}
	}

	
	public static void insertTest(String destination, int member_no, int price, int book_no, int quantity) {
		OrdersVo vo = new OrdersVo();
		vo.setDestination(destination);
		vo.setMember_no(member_no);
		vo.setPrice(price);
		int last_insert_id = new OrdersDao().insert(vo);
		
		Order_bookVo order_book_vo = new Order_bookVo();
		order_book_vo.setBook_no(book_no);
		order_book_vo.setQuantity(quantity);
		new OrdersDao().insert(order_book_vo, last_insert_id);
	}

}
