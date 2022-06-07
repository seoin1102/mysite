package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;


@Repository
public class GuestbookRepository {
	public static boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
			
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String datetime = now.format(formatter).toString();
			
			//3. SQL 준비
			String sql = "insert into guestbook values(null, ?, ?, ?, ?)"; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			pstmt.setString(4, datetime);


			//5. SQL 실생
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection !=null) {
					connection.close();
				}
				}catch(SQLException e) {
					
				}
			}
		
			return result;
	}
	
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
	try {
		connection = getConnection();
		//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
		Class.forName("org.mariadb.jdbc.Driver");
		
		//2. 연결하기
		String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
		
		connection = DriverManager.getConnection(url, "webdb", "webdb");
		
		//3. Statment 생성
		String sql = "select no, name, password, message, reg_date \r\n"
				+ "from guestbook order by no desc";
		pstmt = connection.prepareStatement(sql);
		
		//4. SQL 실생
		

		rs = pstmt.executeQuery();
		
		//5. 결과처리
		while(rs.next()) {
			Long no = rs.getLong(1);
			String name = rs.getString(2);
			String password = rs.getString(3);
			String message = rs.getString(4);
			String datetime = rs.getString(5);


			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			vo.setDatetime(datetime);

			
			result.add(vo);
		}
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패:" + e);
	}finally {
		try {
			if(rs != null) {
				pstmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(connection !=null) {
				connection.close();
			}
			}catch(SQLException e) {
				
			}
		}
			
		return result;
}
	public static boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
			
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL 준비
			String sql = "delete from guestbook where no=? and password=?"; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());


			//5. SQL 실생
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection !=null) {
					connection.close();
				}
				}catch(SQLException e) {
					
				}
			}
		
			return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패"+ e);
		}
		return connection;
	}

}
