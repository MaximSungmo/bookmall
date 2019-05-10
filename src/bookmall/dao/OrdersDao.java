package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;
import bookmall.vo.Order_bookVo;
import bookmall.vo.OrdersVo;

/* 
 * bookmall DB에서 orders table에 접근을 위한 클래스
 * 현재 구현된 기능은 다음과 같음
 * 1. Insert();
 * 2. getList();
 */
public class OrdersDao {

	// orders의 정보를 insert하는 메소드
	public int insert(OrdersVo vo) {
		boolean result = false;
		int last_insert_id = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// destination, member_no
			String sql = "insert into orders values(null, ?, ?, ?, concat((date_format(now(),\"%Y%m%d\")),(select lpad(1+count(order_no),4,'0') from orders s where date_format(order_date,\"%Y%m%d\") = date_format(now(),\"%Y%m%d\"))), now());";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getDestination());

			// 준비된 내용을 실행하여 데이터베이스 업데이트 진행
			int count = pstmt.executeUpdate();
			result = (count == 1);

			sql = "select last_insert_id() from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				last_insert_id = rs.getInt(1);
			}
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
		return last_insert_id;
	}

	// order_book의 정보를 insert하는 메소드
	public Boolean insert(Order_bookVo vo, int last_insert_id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// book_no, order_no, quatity, quantity, book_no
			String sql = "insert into order_book values(?, ?, ?, ?*(select price from book where book_no = ?));";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBook_no());
			pstmt.setInt(2, last_insert_id);
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

	// orders select 구현
	public List<OrdersVo> getList(int member_no) {
		List<OrdersVo> result = new ArrayList<OrdersVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 1번주소 2번 맴버변수
			String sql = "select o.order_no, o.price, o.destination, m.name, m.email, o.order_no_view, o.order_date from orders o, member m where o.member_no = ? and m.member_no = o.member_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int order_no = rs.getInt(1);
				int price = rs.getInt(2);
				String destination = rs.getString(3);
				String name = rs.getString(4);
				String email = rs.getString(5);
				String order_no_view = rs.getString(6);
				String order_date = rs.getString(7);

				OrdersVo vo = new OrdersVo();
				vo.setOrder_no(order_no);
				vo.setPrice(price);
				vo.setDestination(destination);
				vo.setMember_no(member_no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setOrder_no_view(order_no_view);
				vo.setOrder_date(order_date);
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

	// orders select 구현
	public List<Order_bookVo> getList_orders_book(int order_no) {
		List<Order_bookVo> result = new ArrayList<Order_bookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.book_no, b.title, ob.quantity, ob.price from book b, orders o, order_book ob where ob.order_no =o.order_no and ob.order_no = ? and b.book_no=ob.book_no;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int book_no = rs.getInt(1);
				String title = rs.getString(2);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);

				Order_bookVo vo = new Order_bookVo();
				vo.setBook_no(book_no);
				vo.setTitle(title);
				vo.setOrder_no(order_no);
				vo.setQuantity(quantity);
				vo.setPrice(price);
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
