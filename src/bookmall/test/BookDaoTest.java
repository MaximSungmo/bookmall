package bookmall.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		System.out.println("----------BookDaoTest_getListTest----------");
		getListTest();
		System.out.println("-------------------------------------------");

		System.out.println("----------BookDaoTest_insertTest----------");
		insertTest("과학책", 7000, 1);
		insertTest("원피스", 1000, 2);
		insertTest("가시고기", 5000, 3);
		System.out.println("-------------------------------------------");

	}
	
	public static void getListTest() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for (BookVo vo : list) {
			System.out.println("책제목: "+vo.getTitle()+", 개당 가격: "+vo.getPrice());
		}
	}

	public static void insertTest(String title, int price, int category_no) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategory_no(category_no);
		new BookDao().insert(vo);
	}

}
