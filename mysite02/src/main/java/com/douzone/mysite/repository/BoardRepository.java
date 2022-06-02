package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardRepository {
	public static boolean insert(BoardVo vo) {
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
			String sql = "insert into board values(null, ?, ?, ?, ?, ?, ?, ?,?)"; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setString(4, vo.getRegDate());
			pstmt.setLong(5, vo.getgNo());
			pstmt.setLong(6, vo.getoNo());
			pstmt.setInt(7, vo.getDepth());
			pstmt.setLong(8, vo.getUserNo());


			//5. SQL 실생
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
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
	
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
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
		String sql = "select a.no, a.title, a.contents, a.hit, \r\n"
				+ 			"a.reg_date, a.g_no, a.o_no, a.depth, \r\n"
				+ 			"a.user_no, b.name \r\n"
				+ 	"from board a, user b \r\n"
				+ 	"where a.user_no = b.no\r\n"
				+   "order by g_no desc, o_no asc";
		pstmt = connection.prepareStatement(sql);
		
		//4. SQL 실생
		rs = pstmt.executeQuery();
		
		//5. 결과처리
		while(rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String contents = rs.getString(3);
			Long hit = rs.getLong(4);
			String regDate = rs.getString(5);
			Long gNo = rs.getLong(6);
			Long oNo = rs.getLong(7);
			int depth = rs.getInt(8);
			Long userNo = rs.getLong(9);
			String userName = rs.getString(10);
	
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			vo.setUserNo(userNo);
			vo.setUserName(userName);

			
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
	public BoardVo findByNo(long no) {
		BoardVo result = null;
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
		String sql = "select * from board where no=?";
		pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, no);

		//4. SQL 실생
		
		rs = pstmt.executeQuery();
		
		//5. 결과처리
		if(rs.next()) {
			String title = rs.getString(2);
			String contents = rs.getString(3);
			Long hit = rs.getLong(4);
			String regDate = rs.getString(5);
			Long gNo = rs.getLong(6);
			Long oNo = rs.getLong(7);
			int depth = rs.getInt(8);
			Long userNo = rs.getLong(9);

	
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			vo.setUserNo(userNo);

			
			result=vo;
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
	public BoardVo modify(BoardVo vo) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
		
			
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
			
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL 준비
			String sql = "update board set title=? , contents=? ,hit=? where no=? "; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setLong(4, vo.getNo());
		
			//5. SQL 실생
			int count = pstmt.executeUpdate();
			
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
	
	public boolean update(Long no) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			BoardVo vo = findByNo(no);
			
			//1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://192.168.10.46:3306/webdb?charset=utf8";
			
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL 준비
			String sql = "update board set o_no = o_no+1 where o_no >= ? and g_no=?"; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setLong(1, vo.getoNo()+1);
			pstmt.setLong(2, vo.getgNo());
		
			//5. SQL 실생
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
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
	
	public static boolean delete(BoardVo vo) {
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
			String sql = "delete from board where g_no=? and o_no=? and user_no=?"; //값을 바인딩시킴. 치환시키는것이 아님!
			pstmt = connection.prepareStatement(sql);
			
			//4. Mapping(bind)
			pstmt.setLong(1, vo.getgNo());
			pstmt.setLong(2, vo.getoNo());
			pstmt.setLong(3, vo.getUserNo());

			//5. SQL 실생
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
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
