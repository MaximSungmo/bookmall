package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrdersDao;
import bookmall.test.BookDaoTest;
import bookmall.test.CartDaoTest;
import bookmall.test.CategoryDaoTest;
import bookmall.test.MemberDaoTest;
import bookmall.test.OrdersDaoTest;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.Order_bookVo;
import bookmall.vo.OrdersVo;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addTestDB();
		member_getList();
		category_getList();
		book_getList();
		cart_getList(1);
		cart_getList(2);
		orders_getListTest(1);
		orders_getListTest(2);
		order_book_getList(1);
		order_book_getList(2);

		
	}
	
	public static void addTestDB() {
		System.out.println("----------CategoryDaoTest_insertTest----------");
		CategoryDaoTest.insertTest("만화");
		CategoryDaoTest.insertTest("과학");
		CategoryDaoTest.insertTest("수필");
		System.out.println("----------------------------------------------");
		
		System.out.println("----------MemberDaoTest_insertTest----------");
		MemberDaoTest.insertTest("강아지", "010-5959-8282", "cute@pet.co.kr", "5959");
		MemberDaoTest.insertTest("김성모", "010-2323-1111", "232e@pet.co.kr", "5959");
		MemberDaoTest.insertTest("도레미", "010-4555-2222", "1111e@pet.co.kr", "5959");
		System.out.println("--------------------------------------------");
		
		System.out.println("----------BookDaoTest_insertTest----------");
		BookDaoTest.insertTest("과학책", 7000, 1);
		BookDaoTest.insertTest("원피스", 1000, 2);
		BookDaoTest.insertTest("가시고기", 5000, 3);
		BookDaoTest.insertTest("너와나의연결고리", 12000, 3);
		System.out.println("-------------------------------------------");
		
		System.out.println("----------CartDaoTest_insertTest-----------");
		CartDaoTest.insertTest(1,1,5);
		CartDaoTest.insertTest(2,1,5);
		CartDaoTest.insertTest(1,2,3);
		CartDaoTest.insertTest(3,2,2);
		System.out.println("-------------------------------------------");
		
		System.out.println("----------OrdersDaoTest_insertTest----------");
//		OrdersDaoTest.insertTest(1, 5000, "경기도 성남시 분당구", 2, 2, 3);
//		OrdersDaoTest.insertTest(1, "경기도 성남시 분당구", 1, 3, 1);
//		OrdersDaoTest.insertTest("서울특별시 강남구", 2, 3, 3);
//		OrdersDaoTest.insertTest("서울특별시 강남구", 2, 1, 2);
		System.out.println("--------------------------------------------");
	}
		
	public static void member_getList() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		for (MemberVo vo : list) {
			System.out.println("고객명 : "+vo.getName()+", 전화번호 : "+vo.getPhone_no() + ", 이메일 : "+vo.getEmail()+", 비밀번호 : "+vo.getPassword());
		}
	}
	
	public static void category_getList() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for (CategoryVo vo : list) {
			System.out.println("카테고리명 : "+ vo.getCategory_name());
		}
	}
	
	public static void book_getList() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for (BookVo vo : list) {
			System.out.println("책제목: "+vo.getTitle()+", 개당 가격: "+vo.getPrice());
		}
	}
	
	public static void cart_getList(int member_no) {
		CartDao dao = new CartDao();
		int count = 0;
		List<CartVo> list = dao.getList(member_no);
		for (CartVo vo : list) {
			System.out.println("책제목: "+ vo.getTitle()+" 수량: "+vo.getQuantity() + " 가격: "+vo.getPrice());
			count = count + vo.getPrice();
		}System.out.println("카트 총 금액 : "+ count);
	}
	
	public static void orders_getListTest(int member_no) {
		OrdersDao dao = new OrdersDao();
		List<OrdersVo> list = dao.getList(member_no);
		for (OrdersVo vo : list) {
			System.out.println("주문번호 : "+vo.getOrder_no_view() + ", 주문자(이름/이메일) : "+vo.getMember_no()+", 결제금액 : "+vo.getPrice()+", 배송지 : "+ vo.getDestination());
		}
		
	}
	public static void order_book_getList(int order_no) {
		OrdersDao dao = new OrdersDao();
		List<Order_bookVo> list = dao.getList_orders_book(order_no);
		for (Order_bookVo vo : list) {
			System.out.println("주문번호 : "+ vo.getOrder_no()+" 리스트 출력");
			System.out.println("도서번호 : "+vo.getBook_no()+", 도서제목 : "+ vo.getTitle() +", 수량 : "+vo.getQuantity());
		}
	}
}
