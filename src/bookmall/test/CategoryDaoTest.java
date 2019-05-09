package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;



public class CategoryDaoTest {

	public static void main(String[] args) {
		
		System.out.println("----------CategoryDaoTest_insertTest----------");
		insertTest("만화");
		System.out.println("----------------------------------------------");
		
		System.out.println("----------CategoryDaoTest_getListTest----------");
		getListTest();
		System.out.println("----------------------------------------------");
		
	}
	
	public static void getListTest() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> list = dao.getList();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void insertTest(String category_name) {
		CategoryVo vo = new CategoryVo();
		vo.setCategory_name(category_name);
		new CategoryDao().insert(vo);
	}
}
