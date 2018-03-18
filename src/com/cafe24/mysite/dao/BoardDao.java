package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;

public class BoardDao {

	private static final String ID = "webdb";
	private static final String PASSWORD = "webdb";
	private static final String URL = "jdbc:mysql://localhost/webdb?autoReconnect=true&useSSL=false&characterEncoding=utf8";

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			// 1. �뱶�씪�씠踰� 濡쒕뵫^_^
			Class.forName("com.mysql.jdbc.Driver");

			// 2. �뿰寃고븯湲�
			conn = DriverManager.getConnection(URL, ID, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("�뱶�씪�씠踰� 濡쒕뵫 �떎�뙣:" + e);
		} 

		return conn;
	}
	
	public BoardVo get(Long no) {

		BoardVo result = new BoardVo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn = getConnection();

			// 4. SQL �떎�뻾
			String sql = "select * from board where no = ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result.setNo(rs.getLong(1));
				result.setTitle(rs.getString(2));
				result.setContent(rs.getString(3));
				result.setRegDate(rs.getString(5));
				result.setGroupNo(rs.getInt(6));
				result.setOrderNo(rs.getInt(7));
				result.setDepth(rs.getInt(8));
				result.setUserNo(rs.getInt(9));
			}
			
		} catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
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

	public boolean delete(int no) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�
			String sql = "delete from board where no=?";
			pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝

			// 4. �뜲�씠�꽣 諛붿씤�뵫(binding)
			
			pstmt.setInt(1, no);

			// 5. SQL臾� �떎�뻾
			int count = pstmt.executeUpdate(); //�뿴�쓽 媛��닔瑜� 由ы꽩�븿!
			
			result = (count != 0);
			
			// 6. 寃곌낵 泥섎━
			if (count == 0) {
				System.out.println("�떎�뙣!");
			} else {
				System.out.println("�꽦怨�!");
			}

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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
	
	
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�
			String sql = "";
			

			// 4. �뜲�씠�꽣 諛붿씤�뵫(binding)  

			if(vo.getOrderNo() == 0) {
				sql = "insert into board(no, title,content, read_count, reg_date, group_no, order_no, depth, user_no )  select \r\n" + 
						" null, " + 
						" ?, " + 
						" ?, " + 
						" 0, " + 
						" now(), " + 
						" ifnull(max(no), 0) + 1, " + 
						" 1, " + 
						" 0, " + 
						" ?  " +
						" from board";
				pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getUserNo());
			} else {
				sql = "insert into board values(null, ?, ?, 0, now(), ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getGroupNo());
				pstmt.setLong(4, vo.getOrderNo() + 1);
				pstmt.setLong(5, vo.getDepth() + 1);
				pstmt.setLong(6, vo.getUserNo());
			}
		
			// 5. SQL臾� �떎�뻾
			int count = pstmt.executeUpdate(); //�뿴�쓽 媛��닔瑜� 由ы꽩�븿!
			
			result = (count != 0);
			
			// 6. 寃곌낵 泥섎━
			if (count == 0) {
				System.out.println("�떎�뙣!");
			} else {
				System.out.println("�꽦怨�!");
			}

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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
	
	
	public boolean insertComment(CommentVo vo) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�

			 String	sql = "insert into comment values(null, ?, now(), ?, ?)";
				pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝
				pstmt.setString(1, vo.getContent());
				pstmt.setLong(2, vo.getBoardNo());
				pstmt.setLong(3, vo.getUserNo());
				
			// 5. SQL臾� �떎�뻾
			int count = pstmt.executeUpdate(); //�뿴�쓽 媛��닔瑜� 由ы꽩�븿!
			
			result = (count != 0);
			
			// 6. 寃곌낵 泥섎━
			if (count == 0) {
				System.out.println("�떎�뙣!");
			} else {
				System.out.println("�꽦怨�!");
			}

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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
	

	public List<BoardVo> getList(int currentDataSizePerPage) {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn = getConnection();

			// 4. SQL �떎�뻾
			String sql = "select no,"
					+ "  title, "
					+ "  read_count, "
					+ "  reg_date, "
					+ "  group_no, "
					+ "  order_no, "
					+ "  depth, "
					+ "  user_no, "
					+ " @rownum := @rownum + 1 as RNUM "
					+ " from board,"
					+ " (select @rownum :=0) as R "
					+ " order by group_no desc,"
					+ " order_no,"
					+ " depth"
					+ " limit ?, 5 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentDataSizePerPage);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setReadCount(rs.getInt(3));
				vo.setRegDate(rs.getString(4));
				vo.setGroupNo(rs.getLong(5));
				vo.setOrderNo(rs.getLong(6));
				vo.setDepth(rs.getLong(7));
				vo.setUserNo(rs.getLong(8));
				vo.setName(new UserDao().get(rs.getLong(8)).getName());
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
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

		return list;
	}
	
	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�
			String sql = "update board set title = ?, content = ? where no = ?";
			pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝

			// 4. �뜲�씠�꽣 諛붿씤�뵫(binding)
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			// 5. SQL臾� �떎�뻾
			int count = pstmt.executeUpdate(); //�뿴�쓽 媛��닔瑜� 由ы꽩�븿!
			
			result = (count != 0);
			
			// 6. 寃곌낵 泥섎━
			if (count == 0) {
				System.out.println("�떎�뙣!");
			} else {
				System.out.println("�꽦怨�!");
			}

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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
	
	
	public boolean updateReadCount(long no) {
		boolean result = false;
		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�
			String sql = "update board set read_count = read_count + 1  where no = ?";
			pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝
			
			pstmt.setLong(1, no);
			// 5. SQL臾� �떎�뻾
			int count = pstmt.executeUpdate(); //�뿴�쓽 媛��닔瑜� 由ы꽩�븿!
			
			result = (count != 0);
			
			// 6. 寃곌낵 泥섎━
			if (count == 0) {
				System.out.println("�떎�뙣!");
			} else {
				System.out.println("�꽦怨�!");
			}

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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
	
	
	public int getCount(int currentGroupPage) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// 3. SQL 以�鍮�
			String sql = "select * from board limit ?, 26";
			pstmt = conn.prepareStatement(sql); // 以�鍮꾨맂 寃껋씠吏� �씠 �긽�깭�뿉�꽌 而ㅻ━�궇由щ㈃ �삤瑜� 嫄몃┝
			
			pstmt.setInt(1, currentGroupPage);
			// 5. SQL臾� �떎�뻾
			rs = pstmt.executeQuery(); 
			
			
			if(rs.last()) {
				return rs.getRow();
			}
			

		}  catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
			try {
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

		return 0;
	}

	public List<CommentVo> getCommentList() {
		List<CommentVo> list = new ArrayList<CommentVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			conn = getConnection();

			// 4. SQL �떎�뻾
			String sql = "select * from comment";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setNo(rs.getLong(1));
				vo.setContent(rs.getString(2));
				vo.setRegDate(rs.getString(3));
				vo.setBoardNo(rs.getLong(4));
				vo.setUserNo(rs.getLong(5));
				vo.setName(new UserDao().get(rs.getLong(5)).getName());
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("�뿉�윭:" + e);
		} finally {
			// �옄�썝�젙由�(Clean-Up)
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

		return list;
	}
	
}
