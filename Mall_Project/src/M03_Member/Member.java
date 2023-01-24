package M03_Member;

public class Member {

	private int memberNum;
	private String id;
	private String pw;
	private String name;
	
	
	public Member(int memberNum, String id, String pw, String name) {
		super();
		this.memberNum = memberNum;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	@Override
	public String toString() {
		return "[멤버번호 :" + memberNum + ", id=" + id + ", pw=" + pw
				+ ", 이름 :" + name + "]\n";
	}
	
	
	
}
