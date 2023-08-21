package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {
	
	// 필드 
	// JDBC 객체 참조 변수
	private Statement stmt; // SQl 수행 결과 반환
	private PreparedStatement pstmt; // placeholder를 포함한 SQL 세팅/ 수행
	private ResultSet rs; // Select 수행 결과 저장

	private Properties prop;
	// map<String String> 형태
	// XML 파일 입출력 메서드 제공
	
	//
	public MainDAO() {
		
		// DAO 객체가 생성될 때 xml 파일을 읽어와  PRO 객체에 저장
		
		try {
			prop= new  Properties();
					prop.loadFromXML(new FileInputStream("main-sql.xml"));
			
					// -> prop.getPropertry("key") 호출
					// -> value(SQL) 반환
					
					
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	} 
	
	
	/** 로그인 DAO(아이디, 비밀번호 일치 회원 조회)
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {
		// TODO Auto-generated method stub
		
		// 1. 결과 저장용 변수 선언 
		Member member = null;
		
		try {
			// 2. SQL 작성후 수행
			
			String sql = prop.getProperty("login");
			
			
			pstmt = conn.prepareStatement(sql);
			
			// placeholder 에 알맞은 값 대입
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery(); // SELCET 수행 후 결과 반환 받기
			
			// 3. 조회 결과를 1행씩 접근해서 얻어오기 
			if(rs.next()) {
				int memberNO = rs.getInt("MEMBER_NO");
				
				// memberId = rs.getSTring("MEMBER_NO");
				// 입력 받은 아이디 == 조회한 아이디 
				// DB에서 얻어올 필요가 없음 
				
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDate = rs.getString("ENROLL_DT");
				
				// Member 객체 생성 후 값 세팅
				member = new Member();
				
				member.setMemberNo(memberNO);
				member.setMemberId(memberId);
				member.setMeberName(memberName);
				member.setMeberGender(memberGender);
				member.setEnrollDate(enrollDate);
				
			}
			
			
			
			
			
		}finally{
			// 4. 사용한 JDBC 객체 자원 반환 
			close(rs);
			close(pstmt);
		}
		
		
		return member;
	}


	public int signUp(Connection conn, Member emp) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			
	           String sql = prop.getProperty("signUp");
	           
	           pstmt = conn.prepareStatement(sql);
	           
			
			
			
		}finally {
			
			
		}
		
		
		
		
		
		
		return 0;
	}


	public List<Member> loginSelect(Connection conn) {
		// TODO Auto-generated method stub
	List<Member> list = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("loginSelect");
			
			// Statement 객체 생성
			
			stmt = conn.createStatement();
			
			// SQL을 수행 후 결과(ResultSet) 반환 받음
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 얻어와 한 행씩 접근하여
			// Employee 객체 생성 후 컬럼값 담기
			// -> List 추가
			while(rs.next()) {
				
				int memberNo = rs.getInt("EMP_ID");	
				String memberId = rs.getString("EMP_NAME");
				String memberPw = rs.getString("EMP_NO");
				String meberName = rs.getString("EMAIL");
				String meberGender = rs.getString("PHONE");
				String enrollDate = rs.getString("DEPT_TITLE");
				
				Member mem = new Member( memberNo,memberId, memberPw,meberName,meberGender,enrollDate);
				
				list.add(mem); // List 담기
			
			} // while문 종료
			
			
		} finally {
			
			close(stmt);
			
		}
		
		// 결과 반환
		return empList;
		
		
	}


	public int idDuplicationCheck(Connection conn,String memberId ) throws Exception{
		// TODO Auto-generated method stub
		
		int result = 0;
		try {
			
			String sql = prop.getProperty("idDuplicationCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result =rs.getInt(1);
				
			}finally {
				close(rs);
				close(pstmt);
				
			}
					
			
		}finally {
			
			
		}
		
		
		
		return 0;
	}
		
		try {
			
			pstmt.setString(1,member.getMemberId());
			
			result = pstmt.executeUpdate();
			
			
			String sql = prop.getProperty("signUp");
		} finaly{ 
			
		}
		
	

}
