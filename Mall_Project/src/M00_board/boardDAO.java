package M00_board;

import java.util.ArrayList;

import M01_Util.Util;
import M03_Member.Member;
import M03_Member.MemberController;

public class boardDAO {
	String id = MemberController.getInstance().id;
	private boardDAO(){};
	private static boardDAO instance = new boardDAO();
	public static boardDAO getinstance() {
		return instance;
	}
	
	private ArrayList<board>boardList = new ArrayList<board>();
	public void init() {
		
		boardList.add(new board("1","내용1","aaa"));
		boardList.add(new board("2","내용1","aaa"));
		boardList.add(new board("3","내용1","aaa"));
		boardList.add(new board("4","내용1","bbb"));
		boardList.add(new board("5","내용1","bbb"));
		
	}
		
	public void printboard(){
		final int onepage = 4;
		int currentPage = 1;
		
		
		int startindex = 0;
		int endindex = 0;
		
		while (true) {
		int totalPage = boardList.size()/onepage + 1;
		startindex = onepage * (currentPage - 1);
		endindex = currentPage == totalPage ? (onepage * currentPage) - (onepage - (boardList.size() % onepage)) 
					: onepage; 
		
			int num = 0;
			System.out.println("end --> " + endindex);
			for (int i = startindex; i < endindex; i++) {
				System.out.print(++num + ")" + boardList.get(i).getTitle());
				System.out.println("\t --- " + boardList.get(i).getId());
			}
			
			int sel = Util.input.getValue("[1]다음 [2]이전 [3]게시글선택 [4]게시글추가 [5]게시글삭제 [0]뒤로", 1, 2) - 1;
			if(sel == -1) return; 
			if(sel == 0) {
				currentPage ++;
			} else if (sel == 1) {
				currentPage --;
			} else if (sel == 2) {
				selectBoard();
			} else if (sel == 3) {
				addboard();
			} else if (sel == 4) {
				int pageNum = startindex - endindex;
				deleteboard(currentPage,pageNum);
			}
		}
	}
	
	private void addboard() {
		System.out.println("게시글 제목을 입력하세요 .");
		String title = Util.sc.next();
		System.out.println("게시글 내용을 입력하세요 .");
		String body = Util.sc.next();
		boardList.add(new board(title,body,id));
	}
	
	private void deleteboard(int currentValue , int page) {
	
		int nsel = Util.input.getValue("몇번게시글을 지우겠습니까 ?", 1, page);
		if(!boardList.get(nsel).getId().equals(id)) return;
		
	}
	
	private void selectBoard() {
		int sel = Util.input.getValue("몇번 게시글을 수정할지 선택하세요.", 0, 4);
	}
	
}






