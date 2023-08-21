package edu.kh.jdbc.main.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.main.view.Memeber;
import edu.kh.jdbc.member.model.dto.Member;

public class MainService {

	
	
		private MainDAO dao = new MainDAO();
	
		
	
	
	



	




	public int idDuplicationCheck(String memberId)throws Exception {
		// TODO Auto-generated method stub
		
		
		
		Connection conn = getConnection();
		
		int result = dao.idDuplicationCheck(conn,memberId);
		

		
		close(conn);
		
		return result;
	}



	public  int signUp(Member member) throws Exception {
	Connection conn = getConnection();
	
	int result = dao.signUp(conn,String memberId);
	
	if(result > 0) commit(conn);
	else rollback(conn);
	return result;
	}
	



	public Member loginSelect() {
			
		Connection conn =getConnection();
		
		List<Member> list = dao.loginSelect(conn);
		
		close(conn);
		
		return list;
	}
	
	
	int result = dao.updateMember(conn,memberName,memberGender,memberNo);
	
	// 트랜 잭션 처리
	if(result > 0) commit(conn;)
	
	
	
	
	
}
