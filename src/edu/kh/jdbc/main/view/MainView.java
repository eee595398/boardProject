package edu.kh.jdbc.main.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.view.BoardView;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

public class MainView {
	
		
	

		private Scanner sc = new Scanner(System.in);
		
		private MainService service = new MainService();
		
		// 회원 기능 화면 객체 
		private MemberView memberView = new MemberView();
		
		private BoardView boardView = new BoardView();
		
	
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
						case 0: System.out.println("\n==프로그램 종료==\n");
						default : System.out.println("메뉴 번호만 입력해주세요" );
						
						}
						
						
				}else {
					
					System.out.println("====로그인 메뉴==");
					System.out.println("1.회원기능");
					System.out.println("2.게시판 기능 ");
					System.out.println("3.로그아웃");
					System.out.println("0.프로그램종료");
					
					System.out.println("메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1: memberView.memberMenu();; break;
					case 2:	menu(); break;
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
		



		



	


		private void updateMemberPw() {
			// TODO Auto-generated method stub
			System.out.println("현재 비밀번호 입력 : ");
			String input = sc.nextLine();
			if(input.equals(meberPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
				
			
			
			System.out.println("새 비밀 번호 입력 : ");
			String input1 = sc.nextLine();
			
			System.out.println("새 비밀 번호 확인 : ");
			String input2 = sc.nextLine();
			if(input1 != input2) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}else {
				String meberPw = input1;
			}
		}



		private void loginUpdate() throws Exception{
			// TODO Auto-generated method stub
			
			System.out.println("아이디 :");
			String memberId = sc.next();
			
			System.out.println("비밀번호 : ");
			String memberPw = sc.next();
			
			
			System.out.println("이름 : ");
			String memberName= sc.next();
			
			
			System.out.println("성별 : ");
			String memberGender = sc.next();
			
			Member emp = new Member();
			
			emp.setMemberId( memberId);
			emp.setMemberPw(memberPw);
			emp.setMemberName( memberName);
			emp.setMemberGender(memberGender);
			
	
	
			
			int result = service.signUp(emp);
			
			
			
		}



		private void loginSelect()throws Exception {
			// TODO Auto-generated method stub
			
	       Member me = service.loginSelect();
	       
	       printAll(me);
			
	    		   
				
			
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
			
			String memberId = null;
			String emberPw= null;
			String pwConfirm = null;
			String memberName = null;
			String memberGender = null;

			try {
					while(true) {
					    System.out.println("아이디 :");
						memberId = sc.next();
						
						// 아이디 중복 확인 서비스 호출
						// 중복인 경우 1, 아니면 0반환
						
						int result = service.idDuplicationCheck(memberId);
						if(result ==0) {
							System.out.println("사용가능 ");
							break;
						}else {
							System.out.println("이미 사용 중인 아이디입니다.");
						}
						
			
				

						
					}
					

				
					while(true) {
						System.out.println("비밀번호 입력 : ");
						memberPw = sc.next();
						
						System.out.println("비밀 번호 확인 :");
						pwConfirm =sc.next();
						
						if(memberPw.equals(pwConfirm)) {
							
							System.out.println("비밀번호 일치");
							break;
						}else {
							System.out.println("비밀번호가 일치하지 않습니다.");
						}
						
						
					}
					
					
			
					System.out.println();
					memberName =sc.next();
					
					// 성별 입력 
					while(true) {
						System.out.println("성별 입력 :");
						meberGender = sc.next().toUpperCase();
						
						if(meberGender.equals("m") || meberGender.equals("f")){
							break;
						}else {
							
							System.out.println("m,f만 입력");
						}
					}
					
					//Member 객체 입력 값 세팅
					
					Member member = new Member();
					member.setMemberId(memberId);
					
					
					int result =service.signUp(member);
				
					if(result > 0) {
						System.out.println();
						
					}else {
						
					}
					
					
					
		
			}catch(Exception e){
				System.out.println("회원가입 중 예외 발생");
				e.printStackTrace();
			}
				
				
				
			}
			
			
		}
		
	
		
		
		
		

