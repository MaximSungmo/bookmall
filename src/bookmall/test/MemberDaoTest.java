package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {

		System.out.println("----------MemberDaoTest_insertTest----------");
		insertTest("강아지", "010-5959-8282", "cute@pet.co.kr", "5959");
		insertTest("김성모", "010-2323-1111", "232e@pet.co.kr", "5959");
		insertTest("몰라몰라", "010-4555-2222", "1111e@pet.co.kr", "5959");
		System.out.println("--------------------------------------------");
		
		System.out.println("----------MemberDaoTest_getListTest---------");
		getListTest();
		System.out.println("--------------------------------------------");

	}

	public static void getListTest() {
		MemberDao dao = new MemberDao();
		List<MemberVo> list = dao.getList();
		for (MemberVo vo : list) {
			System.out.println("고객명 : "+vo.getName()+", 전화번호 : "+vo.getPhone_no() + ", 이메일 : "+vo.getEmail()+", 비밀번호 : "+vo.getPassword());
		}
	}

	public static void insertTest(String name, String phone_no, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone_no(phone_no);
		vo.setEmail(email);
		vo.setPassword(password);
		new MemberDao().insert(vo);
	}

}
