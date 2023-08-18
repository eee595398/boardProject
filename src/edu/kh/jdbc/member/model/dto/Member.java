package edu.kh.jdbc.member.model.dto;

public class Member {

	private int memberNo; // 회원번호
	private String memberId; // 회원 아이디 
	private String memberPw; // 비밀번호
	private String meberName; // 이름
	private String meberGender; // 성별
	private String enrollDate; // 가입일 
	private String upregisterFlag; // 탈퇴여부
	
	public Member() {
		
	}
	


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMeberName() {
		return meberName;
	}

	public void setMeberName(String meberName) {
		this.meberName = meberName;
	}

	public String getMeberGender() {
		return meberGender;
	}

	public void setMeberGender(String meberGender) {
		this.meberGender = meberGender;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUpregisterFlag() {
		return upregisterFlag;
	}

	public void setUpregisterFlag(String upregisterFlag) {
		this.upregisterFlag = upregisterFlag;
	}
	
}
