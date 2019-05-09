package bookmall.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {

		System.out.println("----------CartDaoTest_getListTest----------");
		getListTest(1);
		System.out.println("-------------------------------------------");
		
		System.out.println("----------CartDaoTest_insertTest-----------");
		insertTest(1,3,5);
		System.out.println("-------------------------------------------");
		
		System.out.println("----------CartDaoTest_getListTest----------");
		getListTest(3);
		System.out.println("-------------------------------------------");

	}

	public static void getListTest(int member_no) {
		CartDao dao = new CartDao();
		int count = 0;
		List<CartVo> list = dao.getList(member_no);
		for (CartVo vo : list) {
			System.out.println("책제목: "+ vo.getTitle()+" 수량: "+vo.getQuantity() + " 가격: "+vo.getPrice());
			count = count + vo.getPrice();
		}System.out.println("총 금액 : "+ count);
	}
	
	
	public static void insertTest(int book_no, int member_no, int quantity) {
		CartVo vo = new CartVo();
		vo.setBook_no(book_no);
		vo.setMember_no(member_no);
		vo.setQuantity(quantity);
		new CartDao().insert(vo);
	}
	
	
}
