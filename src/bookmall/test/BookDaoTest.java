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
		System.out.println("-------------------------------------------");

	}
	
	public static void getListTest() {
		BookDao dao = new BookDao();
		List<BookVo> list = dao.getList();
		for (BookVo vo : list) {
			System.out.println(vo);
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
