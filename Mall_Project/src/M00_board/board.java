package M00_board;

public class board {
	
	public board() {
		body = "";
	}
	
	public board(String title, String body, String id) {
		super();
		this.title = title;
		this.body = body;
		this.id = id ;
	}


	private String title;
	private String body;
	private String id;
	
	public String getTitle() {
		return title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getId() {
		return id;
	}
	
	
	
}
