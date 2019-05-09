package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersVo;

/* 
 * bookmall DB에서 orders table에 접근을 위한 클래스
 * 현재 구현된 기능은 다음과 같음
 * 1. Insert();
 * 2. getList();
 */
public class OrdersDao {

	// select 구현
	public List<OrdersVo> getList(int member_no) {
		List<OrdersVo> result = new ArrayList<OrdersVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int book_no = rs.getInt(1);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);
				String title = rs.getString(5);
				String name = rs.getString(6);
				OrdersVo vo = new OrdersVo();
				vo.setBook_no(book_no);
				vo.setMember_no(member_no);
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setTitle(title);
				vo.setName(name);
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

	// orders의 정보를 insert하는 메소드
	public Boolean insert(OrdersVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into orders values(?, ?, ?, ?*(select price from book where book_no=?));";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBook_no());
			pstmt.setInt(2, vo.getMember_no());
			pstmt.setInt(3, vo.getQuantity());
			pstmt.setInt(4, vo.getQuantity());
			pstmt.setInt(5, vo.getBook_no());

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
			String url = "jdbc:mariadb://192.168.1.21:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return conn;
	}
}
