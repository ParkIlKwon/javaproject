package board;


import Util.Util;
import _mall.MenuCommand;
import controller.MallController;
import menu_member.MemberMain;

public class BoardMain implements MenuCommand{

	MallController mctrl;
	BoardDAO bdao;
	Util input = Util.input;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance();
		bdao = BoardDAO.getinstance();
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.println("[ Board Menu ]");
			System.out.println("=========================");
			
			final int onepage = 4;
			int currentPage = 1;
			bdao.id = MemberMain.id;
			
			int startindex = 0;
			int endindex = 0;
			
			while (true) {
			int totalPage = bdao.boardList.size()/onepage + 1;
			startindex = onepage * (currentPage - 1);
		
			endindex = currentPage == totalPage ? bdao.boardList.size() - onepage * (totalPage-1)
						: onepage; 
				int num = 0;
				for (int i = startindex; i < startindex + endindex; i++) {
					System.out.print(++num + ")" + bdao.boardList.get(i).getTitle());
					if(MemberMain.id.equals("admin")) {
						System.out.print("   ");
						for (int j = 0; j < bdao.boardList.get(i).getBody().length()-3; j++) {
							System.out.print(bdao.boardList.get(i).getBody().charAt(j));
						}
						System.out.print("...");
					}
					System.out.println("\t --- " + bdao.boardList.get(i).getUserid());
				}
				
				int sel = Util.input.getValue("[1]다음 [2]이전 [3]게시글수정 및 내용확인 \n[4]게시글추가 [5]게시글삭제 [0]뒤로", 0, 5);
				if (sel == -1) continue;
				if((currentPage == 1 && sel == 2) || (currentPage == totalPage && sel == 1)) continue;
				if(sel == 0) {
					mctrl.nextpage = "MemberMain";
					return true;
				}
				if(sel == 1) {
					currentPage ++;
				} else if (sel == 2) {
					currentPage --;
				} else if (sel == 3) {
					bdao.fixboard(currentPage,endindex);
				} else if (sel == 4) {
					bdao.addboard();
				} else if (sel == 5) {
					bdao.deleteboard(currentPage,endindex);	
				}
			}
			
		}
	}
}
