package M00_board;

import M01_Util.Util;

public class board_Controller {
   
	private board_Controller(){}
	private static board_Controller instance = new board_Controller();
	public static board_Controller getinstance() {
		return instance;
	}
	public void init(){
		boardDAO.getinstance().init();
	}
	
	public void menu() {
		while (true) {
			System.out.println("[0]뒤로가기 [1]게시판확인 및 글 쓰기");
			int sel = Util.input.getValue("메뉴를 ", 0, 1);
			if (sel == 0) {
				break;
			}else if (sel == 1) {
				boardmenu();
			}
		}
	}
	
	private void boardmenu() {
		boardDAO.getinstance().printboard();
	}
}
