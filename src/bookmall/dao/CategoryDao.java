package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	// select 구현
	public List<CategoryVo> getList() {
		List<CategoryVo> result = new ArrayList<CategoryVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select c.category_no, c.category_name from category c order by c.category_no asc;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int category_no = rs.getInt(1);
				String category_name = rs.getString(2);

				CategoryVo vo = new CategoryVo();
				vo.setCategory_no(category_no);
				vo.setCategory_name(category_name);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// category의 정보를 insert하는 메소드
	public Boolean insert(CategoryVo vo) {
		// 사용자의 정보가 제대로 업데이트가 된 경우 result = true로 메소드 종료 아니면 SQLException 오류 발생
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// connection을 위한 클래스를 별도로 작성하여 conn 변수에 넣어줌
			conn = getConnection();
			String sql = "insert into category values(null, ?);";
			pstmt = conn.prepareStatement(sql);

			// SQL의 ?를 받기 위한 바인딩 작업
			pstmt.setString(1, vo.getCategory_name());

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

	// 중복 제거를 위한 getConnection 메소드 별도 정의
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://14.32.18.223:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return conn;
	}
}
