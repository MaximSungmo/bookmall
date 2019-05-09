package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

/* 
 * bookmall DB에서 Member table에 접근을 위한 클래스
 * 현재 구현된 기능은 다음과 같음
 * 1. Insert(vo);
 * 2. getList();
 */
public class MemberDao {

	// 사용자의 전체 정보를 가져오는 메소드
	public List<MemberVo> get(MemberVo vo) {
		return null;
	}

	// 사용자의 정보를 member_no로 가져오는 메소드
	public MemberVo get(int member_no) {
		return null;
	}

	// 사용자 정보 업데이트를 위한 메소드
	public Boolean update(MemberVo vo) {
		return false;
	}

	// 사용자의 정보를 insert하는 메소드
	public Boolean insert(MemberVo vo) {
		// 사용자의 정보가 제대로 업데이트가 된 경우 result = true로 메소드 종료 아니면 SQLException 오류 발생
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// connection을 위한 클래스를 별도로 작성하여 conn 변수에 넣어줌
			conn = getConnection();

			String sql = "insert into member values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// SQL의 ?를 받기 위한 바인딩 작업
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone_no());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());

			// 준비된 내용을 실행하여 데이터베이스 업데이트 진행
			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					// 연결 종료
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 사용자의 정보를 select하는 메소드 
	public List<MemberVo> getList() {
		//vo 객체를 받을 리스트 생성
		List<MemberVo> result = new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select member_no, name, phone_no, email, password from member m order by m.member_no asc;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();


			// 리스트에 vo객체 조건에 맞는 지 확인 후 추가하기
			while (rs.next()) {
				int member_no = rs.getInt(1);
				String name = rs.getString(2);
				String phone_no =rs.getString(3);
				String email =rs.getString(4);
				String password =rs.getString(5);

				MemberVo vo = new MemberVo();
				vo.setMember_no(member_no);
				vo.setPhone_no(phone_no);
				vo.setPhone_no(phone_no);
				vo.setEmail(email);
				vo.setPassword(password);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//중복 제거를 위한 getConnection 메소드 별도 정의
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.21:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return conn;
	}
}
