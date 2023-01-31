package member;

import java.util.ArrayList;
import Util.Util;

public class MemberDAO {

	public int memberNum;
	private ArrayList<Member>Memberlist = new ArrayList<Member>();
	private MemberDAO() {
		memberNum = 1001;
		System.out.println("초기세팅완료");
		ealrydata();
	}
	
	static private MemberDAO instance = new MemberDAO();
	static public  MemberDAO getInstance(){
		return instance;
	}
	
	private int getMemberNum() {
		if (Memberlist.size() == 1) {
			memberNum = 1001;
		} else {
			memberNum ++;
		}
		return memberNum;
	}
	
	public void accountMember(String id , String pw , String name){
		Memberlist.add(new Member(getMemberNum(), id, pw, name));
	}
	
	//로그인 체크
	public boolean chkMemberInfo(String id, String pw) {
		for (int i = 0; i < Memberlist.size(); i++) {
			if (Memberlist.get(i).getId().equals(id) && 
					Memberlist.get(i).getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
	
	//중복체크
	public boolean chkduplicate(String id) {
		for (Member member : Memberlist) {
			if (member.getId().equals(id)) {
				System.err.println(" 아 이 디 중 복 :< ");
				return true;
			}
		}
		return false;
	}
	
	public void deleteMember() {
		int index = 1;
		ArrayList<String>temp = new ArrayList<String>();
		for (Member member : Memberlist) {
			if(!member.getId().equals("admin")) {
				System.out.printf("[%-2d] %s\n",index,member.getId());
				temp.add(member.getId());
				index ++;
			}
		}
		int sel = Util.input.getValue("삭제하실 멤버", 1, Memberlist.size())-1;
		if(sel == -2)return;
		Memberlist.remove(sel);
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
