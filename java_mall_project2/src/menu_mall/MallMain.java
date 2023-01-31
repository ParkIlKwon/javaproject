package menu_mall;


import Util.Util;
import _mall.MenuCommand;
import controller.MallController;

public class MallMain implements MenuCommand{

	MallController mctrl;
	@Override
	public void init() {
		mctrl = MallController.getinstance();
	}

	@Override
	public boolean update() {
		init();
		
		int sel = Util.input.getValue("[1] 로그인 [2] 회원가입 [0] 종료", 0, 2);
		
		if (sel == 1) {
			mctrl.nextpage = "MallLogin";
			return true;
		} else if (sel == 2) {
			mctrl.nextpage = "MallAccount";
			return true;
		} else if(sel == 0){
			return false;
		}
		return true;
	}
}
