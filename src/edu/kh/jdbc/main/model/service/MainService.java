package edu.kh.jdbc.main.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MainService {

		private MainDAO dao = new MainDAO();
	
	public Member login(String memberId, String memberPw) throws Exception{
		// TODO Auto-generated method stub
		
		// 1. Connection 생성 
		
		Connection conn = getConnection();
		
		// 2. DAO 호출
		Member member = dao.login(conn, memberId, memberPw);
		
		// 3. Connection 반환
		
		close(conn);
		
		// 4. 결과 반환 
		
		return member;
	}


	




	public int signUp(Member emp)throws Exception {
		// TODO Auto-generated method stub
		
		
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, emp);
		
		if(result > 0) commit(conn);
		else   			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
}
