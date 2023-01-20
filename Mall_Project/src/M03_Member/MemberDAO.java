package M03_Member;

import java.util.ArrayList;

public class MemberDAO {
	
	public int memberNum;
	private ArrayList<Member>Memberlist = new ArrayList<Member>();
	private MemberDAO() {
		memberNum = 1001;
		ealrydata();
	}
	
	static private MemberDAO instance = new MemberDAO();
	static public  MemberDAO getInstance(){
		return instance;
	}
	
	int getMemberNum() {
		if (Memberlist.size() == 1) {
			memberNum = 1001;
		} else {
			memberNum ++;
		}
		return memberNum;
	}
	
	void accountMember(String id , String pw , String name){
		Memberlist.add(new Member(getMemberNum(), id, pw, name));
	}
	
	boolean chkName(String name) {
		for (int i = 0; i < name.length(); i++) {
			char c= name.charAt(i);
			if (c > '0' && c <= '9' ) {
				return false;
			}
		}
		return true;
	}
	
	//로그인 체크
	boolean chkMemberInfo(String id, String pw) {
		for (int i = 0; i < Memberlist.size(); i++) {
			if (Memberlist.get(i).getId().equals(id) && 
					Memberlist.get(i).getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	//중복체크
	boolean chkduplicate(String id) {
		for (Member member : Memberlist) {
			if (member.getId().equals(id)) {
				System.err.println(" 아 이 디 중 복 :< ");
				return true;
			}
		}
		return false;
	}
	
	private void ealrydata() {
		Memberlist.add(new Member(1, "admin", "admin", "관리자"));
		Memberlist.add(new Member(getMemberNum(), "aaa", "111", "김"));
		Memberlist.add(new Member(getMemberNum(), "bbb", "222", "박"));
	}

	public ArrayList<Member> getMemberlist() {
		return Memberlist;
	}
	
	
	
}
