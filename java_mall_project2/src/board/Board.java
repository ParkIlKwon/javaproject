package board;

public class Board {

	private String title;
	private String body;
	private String userid;
	
	public Board(String title, String body, String userid) {
		super();
		this.title = title;
		this.body = body;
		this.userid = userid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
