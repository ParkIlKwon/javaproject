package board;

import java.util.ArrayList;

import Util.Util;
import menu_member.MemberMain;


public class BoardDAO {

	String id ;
	Util input = Util.input;
	private BoardDAO(){init();}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getinstance() {
		return instance;
	}
	
	ArrayList<Board>boardList = new ArrayList<Board>();
	public void init() {
		for (int i = 1; i < 8; i++) {
			String title = "제목"+i;
			String body = 	"제목"+i+"의내용"+i;
			boardList.add(new Board(title,body, i % 2 == 0 ? "bbb" : "aaa" ));
		}
	}
	
	void addboard() {
		String title = input.getString("게시글 제목을 입력하세요 .", false);
		String body = input.getString("게시글 내용을 입력하세요 .", false);;
		boardList.add(new Board(title,body,id));
	}
	
	void deleteboard(int currentValue , int page) {
	
		int nsel = selectBoard(currentValue ,page , "삭제");
		if(nsel == -1) {
			System.err.println("번호선택에러");
			System.out.println();
			return;
		}
		if (chkBoardId(nsel)) {
			boardList.remove(nsel);
			System.out.println("삭제완료.");
		}else {
			System.err.println("잘못된 입력");
		}
	}
	
	void fixboard(int currentValue , int page) {
		while (true) {
			int sel = Util.input.getValue("[1]보기 [2]수정 [0]뒤로", 0, 2);
			if(sel == 0) return;
			else if (sel == 1) {
				int nsel = selectBoard(currentValue ,page , "확인할 게시글 번호");
				if(nsel == -1) continue;
					if(!chkBoardId(nsel))continue;
					System.out.println("작성자 >>> " + boardList.get(nsel).getUserid());
					System.out.println(boardList.get(nsel).getBody());
			}else if (sel == 2) {
				int nsel = selectBoard(currentValue ,page , "수정할 게시글 번호");
				if(nsel == -1) continue;
				if (chkBoardId(nsel)) {
					String str = input.getString("내용을 입력하세요.", false);
					boardList.get(nsel).setBody(str);
				}else {
					System.err.println("잘못된 입력");
				}
			}
		}
	}
	
	private int selectBoard(int currentValue , int page , String msg) {
	
		int nsel = Util.input.getValue("몇번 게시글을"+ msg +"선택하세요.", 0, page) -1;
		if(nsel == -2 ) return -1;
		return ((currentValue -1) * 4) + nsel; 
		
		}
	
	
	private boolean chkBoardId(int nsel){
		if(!boardList.get(nsel).getUserid().equals(id) && !id.equals("admin")) {
			System.err.println("다른 사용자의 글은 수정하거나 삭제 하실 수 없습니다.");
			return false;
		} return true;
	}
}
