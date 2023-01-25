package M00_board;

import java.util.ArrayList;

import M01_Util.Util;
import M03_Member.Member;
import M03_Member.MemberController;

public class boardDAO {
	String id ;
	private boardDAO(){};
	private static boardDAO instance = new boardDAO();
	public static boardDAO getinstance() {
		return instance;
	}
	
	private ArrayList<board>boardList = new ArrayList<board>();
	public void init() {
		
		boardList.add(new board("비싸요","내용1","aaa"));
		boardList.add(new board("할인해줘요","내용1","aaa"));
		boardList.add(new board("불친절해요","내용1","aaa"));
		boardList.add(new board("4","내용1","bbb"));
		boardList.add(new board("5","할인해줘요","bbb"));
		 
	}
		
	public void printboard(){
		final int onepage = 4;
		int currentPage = 1;
		id = MemberController.getInstance().id;
		
		int startindex = 0;
		int endindex = 0;
		
		while (true) {
		int totalPage = boardList.size()/onepage + 1;
		startindex = onepage * (currentPage - 1);
		endindex = currentPage == totalPage ? (onepage * currentPage) - (onepage - (boardList.size() % onepage)) 
					: onepage; 
		
			int num = 0;
			for (int i = startindex; i < endindex; i++) {
				System.out.print(++num + ")" + boardList.get(i).getTitle());
				System.out.println("\t --- " + boardList.get(i).getId());
			}
			
			int sel = Util.input.getValue("[1]다음 [2]이전 [3]게시글수정 및 내용확인 \n[4]게시글추가 [5]게시글삭제 [0]뒤로", 0, 5);
			int pageNum = endindex - startindex ;
			if(sel == -1 || sel == 0 ) return; 
			if((currentPage == 1 && sel == 2) || (currentPage == totalPage && sel == 1)) continue;
			if(sel == 1) {
				currentPage ++;
			} else if (sel == 2) {
				currentPage --;
			} else if (sel == 3) {
				fixboard(currentPage,pageNum);
			} else if (sel == 4) {
				addboard();
			} else if (sel == 5) {
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
	
		int nsel = selectBoard(currentValue ,page , "삭제");
		if (chkBoardId(nsel)) {
			int index = ((currentValue -1) * 4) + nsel; 
			boardList.remove(index);
			System.out.println("삭제완료.");
		}else {
			System.err.println("잘못된 입력");
		}
	}
	
	private void fixboard(int currentValue , int page) {
		while (true) {
			int sel = Util.input.getValue("[1]보기 [2]수정 [0]뒤로", 0, 2);
			if(sel == 0) return;
			else if (sel == 1) {
				int nsel = selectBoard(currentValue ,page , "확인할 게시글 번호");
					System.out.println("작성자 >>> " + boardList.get(nsel).getId() );
					System.out.println(boardList.get(nsel).getBody());
			
			}else if (sel == 2) {
				int nsel = selectBoard(currentValue ,page , "수정할 게시글 번호");
				if (chkBoardId(nsel)) {
					System.out.println("내용을 입력하세요.");
					String str = Util.input.sc.next();
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
		if(!boardList.get(nsel).getId().equals(id)) {
			System.err.println("다른 사용자의 글은 수정하거나 삭제 하실 수 없습니다.");
			return false;
		} return true;
	}
}






