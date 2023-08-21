package edu.kh.jdbc.member.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	/**회원기능 메뉴 
	 * 
	 */
	public void memberMenu() {
		int input = 0;
		
		do {
			try {
				System.out.println("====회원기능==");
				System.out.println("1.내 정보 조회");
				System.out.println("2. 회원목록 조회");
				System.out.println("3.내 정보 수정");
				System.out.println("4.비밀번호 변경(현재 비밀번호, 새비밀번호, 새비밀번호 확인");
				System.out.println("5.회원 탈퇴(보안코드,비밀번호,UPDATE");
				System.out.println("9.메인 메뉴로 돌아가기");
				System.out.println("0.프로그램 종료");
			
					System.out.println("메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1: selectMyInfo(); break;
					case 2:	selectMemberList(); break;
					case 3: updateMember(); break
					case 4: updatePassword();; break
					case 5: break;
					case 9: 
						System.out.println("메인메뉴로");
						break;
					case 0 :      
					System.out.println("\n==프로그램 종료==\n");
					System.exit(0); 
					// JVM 강제 종료 기본 0
					default : System.out.println("메뉴번호만 입력하시오");
					}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}while(input !=9);
		
		
	}
	
	
	private void updateMember() {
		// TODO Auto-generated method stub
		System.out.print("이름 :");
		String memberName= sc.next();
		
		
		String memberGender = null;
		
		while(true) {
			System.out.println("수정할 성별 :");
			
			String memberGender = sc.next().toUpperCase();
			
			if(memberGender.equals("M") || memberGender.equals("F")) {
				break;
			}
			
			System.out.println("M또는F를 입력하세요");
		}
		
		try {
			
			int result = service.updateMember(memberName,memberGender, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				 System.out.println("수정되었습니다.");
				// DB와 Java프로그램 데이터 동기화 필요
				Session.loginMember.setMemberName(memberName);
				Session.loginMember.setMemberGender(memberGender);
				
				
			}else {
				System.out.println("수정 실패되었습니다.");
			
			}
			
			
			
			
			
		}catch(Exception e) {
			System.out.println("수정 중 예외 발생");
			e.printStackTrace();
		}
		
		
	}


	/** 회원 목록 조회
	 * 
	 */
	private void selectMemberList() {
		// TODO Auto-generated method stub
		System.out.println("회원목록 조회");
		try {
			// 회원 목록 조회 서비스 호출후 결과 반환받기 
			
			List<Member> memberList =service.selectMemberList();
			if(memberList.isEmpty()) {
				System.out.println("조회결과 없습니다");
			} else {
				for(int i=0; i<memberList.size(); i++) {
					System.out.printf("%d\t\t%s\t\t%s\t\t%s\n",i+1,memberList.get(i).getMemberId()
							,memberList.get(i).getMemberName(),memberList.get(i).getMeberGender());
				}
				
			}
			
			
		} catch(Exception e) {
			System.out.println("예외 발생");
			e.printStackTrace();
		}
		
	}


	private void selectMyInfo() {
		// TODO Auto-generated method stub
			// Sesccion.loginMember 이용
		
		System.out.println("회원번호" + Session.loginMember.getMemberNo());
		System.out.println("아이디" + Session.loginMember.getMemberId());
		System.out.println("이름" + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("m")) {
			System.out.println("성별 남 ");
			
			
		}else {
			System.out.println("성별 여");
			
			
		}
		System.out.println("가입일" + Session.loginMember.getEnrollDate());
		
	}
}
