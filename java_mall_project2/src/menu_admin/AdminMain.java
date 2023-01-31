package menu_admin;

import Util.Util;
import _mall.MenuCommand;
import board.BoardMain;
import controller.MallController;

public class AdminMain implements MenuCommand{

	Util input = Util.input;
	private MallController mctrl;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance(); 
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.println("[ Admin Menu ]");
			int sel = input.getValue("[1]멤버관리 [2]아이템관리 [3]카트관리 [4]게시판관리 [0]종료", 0, 4);
			if(sel == -1) continue;
			if(sel == 0) {
				mctrl.nextpage = "MallMain";
			}else if (sel== 1) {
				mctrl.nextpage = "AdminMember";
			}else if (sel == 2) {
				mctrl.nextpage = "AdminItem";
			}else if (sel == 3) {
				mctrl.nextpage = "AdminCart";
			}else if (sel == 4) {
				mctrl.nextpage = "BoardMain";
			}
			return true;
		}
	}
}
