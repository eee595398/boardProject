package edu.kh.jdbc.board.model.dao;


import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;

public class BoardDAO {
	
	private Statement stmt; // SQL 수행, 결과 반환
	private PreparedStatement pstmt; // placeholder를 포함한 SQL 세팅/수행
	private ResultSet rs; // SELECT 수행 결과 저장
	private Properties prop;
	
	public BoardDAO() {
		
		// DAO 객체가 생성될때 Xml 파일을 읽어와 Properties 객체에 저장
		try {
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("board-sql.xml") );
			
			// -> prop.getProperty("key") 호출
			// --> value (SQL) 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	public List<Board> selectAllBoard(Collection conn) throws Exception {
		// TODO Auto-generated method stub
		List<Board> boardList = new ArrayList<Board>();
		try {
			
			String sql = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				int readCount = rs.getInt(3);
				String createDate = rs.getString(4);
				int commentCount = rs.getInt(5);
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);
				
				
			}
			
		}finally {
			
			close(rs);
			close(stmt);
			
			
		}
		
		
		return boardList;
	}




	/** 게시글 상세 조회 SQL 수행
	 * @param conn
	 * @param input
	 * @return
	 */
	public Board selectBoard(Connection conn, int input) throws Exception{
		// TODO Auto-generated method stub
		
		Board board = null;
		try {
			String sql = prop.getProperty("selectBoard");
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, input);
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()) {
		    	int boardNo = rs.getInt("BOARD_NO");
		    	String boardTitle = rs.getString("BOARD_TITLE");
		    	String memberName = rs.getString("MEMBER_NM");
		    	int readCount = rs.getInt("READ_COUNT");
		    	String createDate = rs.getString("CREATE_DT");
		    	
		    	
		    	board = new Board();
		    	
		    	board.setBoardNo(boardNo);
		    	board.setBoardTitle(boardTitle);
		    	board.setMemberName(memberName);
		    	
		    	
		    }
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return null;
	}




	/** 조회수 증가 SQL 수행
	 * @param conn
	 * @param input
	 * @return
	 */
	public int updateReadCount(Connection conn, int input)throws Exception {
		// TODO Auto-generated method stub
		
		int result = 0;
		try {
			String sql = prop.getProperty("updateReadCount");
			
			  pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, input);
			    result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		
		return 0;
	}




	/** 게시글 수정 sql 수행
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param boardNo
	 * @return
	 */
	public Connection updateBoard(Connection conn, String boardTitle, String boardContent, int boardNo) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		
		try {
			String sql = prop.getProperty("updateBoard");
			 pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		
		
		
		return result;
	}




	/** 게시물 삭제 sql
	 * @param conn
	 * @param boardNo
	 * @return
	 */
	public int deleteBoard(Connection conn, int boardNo) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, boardNo);
			 	
			 
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}




	public int nextBoardNo(Connection conn) throws Exception{
		// TODO Auto-generated method stub
		
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1); //6
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return 0;
	}




	/** 게시글 삽입 SQL 수행
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param memberNo
	 * @param boardNo
	 * @return
	 */
	public int insertBoard(Connection conn, String boardTitle, String boardContent, int memberNo, int boardNo) {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, boardNo);
					pstmt.setString(2, boardTitle);
					pstmt.setString(3, boardContent);
					pstmt.setInt(4, memberNo);
					
					result = pstmt.executeUpdate();
					
					
					
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	/** 게시글 검색 DAO
	 * @param conn
	 * @param condition
	 * @param query
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, int condition, String query) throws Exception{
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("searchBoard1")
					   + prop.getProperty("searchBoard2_" + condition)
					   + prop.getProperty("searchBoard3");
					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, query);
			
			// 3번(제목+내용)은 ?가 2개 존재하기 때문에 추가 세팅 구문 작성
			if(condition == 3)   pstmt.setString(2, query);
			
			rs = pstmt.executeQuery();
			
			// ResultSet에 저장된 값을 List 옮겨 담기
			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	
	


}
