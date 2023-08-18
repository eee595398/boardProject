package edu.kh.jdbc.main.view;

import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;

public class MainView {
	
		
	

		private Scanner sc = new Scanner(System.in);
		
		private MainService service = new MainService();
		
	
		public void mainMenu() {
			
			int input = 0;
			
			do {
				
			try {
				if(Session.loginMember == null) {
					System.out.println("====회원제 게시판 프로그램==");
					System.out.println("1.로그인");
					System.out.println("2.회원가입");
					System.out.println("0.프로그램 종료");
				
						System.out.println("메뉴선택 : ");
						input = sc.nextInt();
						sc.nextLine();
						
						
						switch(input) {
						case 1: login(); break;
						case 2: signUp(); break;
						case 0: System.out.println("\n==프로그램 종류==\n");
						default : System.out.println("메뉴 번호만 입력해주세요" );
						
						}
						
						
				}else {
					
					System.out.println("====로그인 메뉴==");
					System.out.println("1.회원기능");
					System.out.println("2.게시판 기능 ");
					System.out.println("3. 로그아웃");
					System.out.println("0. 포그램종료");
					
					System.out.println("메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1: break;
					case 2:	 break;
					case 3: 
						System.out.println("로그아웃 되었습니다");
						
						Session.loginMember = null;                
						// 참조하고 있던 로그인 회원 객체 없앰
						
						break;
					case 0: break;
					default : 	break;
					
					}
					
					
				}
				
			}
				
				
				catch (Exception e) {
				
				e.printStackTrace();
				
			}
				
			}while(input !=0);
		}
		
		private void login() {
			System.out.println("로그인");
			
			System.out.println("아이디 :");
			String memberId = sc.next();
			
			System.out.println("비밀번호 : ");
			String memberPw = sc.next();
					
			try {
				
				Session.loginMember = service.login(memberId, memberPw);
				// 로그인 서비스 호출 후 결과 반환 받기 
				// -> 반환 받은 결과는 Session.loginMember에 저장
				
				if(Session.loginMember== null) {
					// 로그인 실패
					System.out.println("아이디 / 비밀번호가 일치하지 않습니다");
					
				}else {
					System.out.printf("%s 님 환영합니다", Session.loginMember.getMeberName());
				}
				
				
			}catch(Exception e) {
				System.out.println("로그인 중 예외 발생");
				e.printStackTrace();
				
			}
					
			
			
			
			
		}
		
		
		
		private void signUp() throws Exception {
			System.out.println("아이디 :");
			String memberId = sc.next();
			
			System.out.println("비밀번호 : ");
			String memberPw = sc.next();
			
			
			System.out.println("이름 : ");
			String memberName= sc.next();
			
			
			System.out.println("성별 : ");
			String memberGender = sc.next();
			
	
			Member emp = new Member(memberId,memberPw,memberName,memberGender);
			
			int result = service.signUp(emp);
		
			
			
			
		}
		
		
		
		
		
		
}
