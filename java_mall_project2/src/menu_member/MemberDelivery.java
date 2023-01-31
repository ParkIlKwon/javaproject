package menu_member;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDelivery;
import controller.MallController;

public class MemberDelivery implements MenuCommand{

	Util input = new Util();
	MallController mctrl ;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance();
	}

	@Override
	public boolean update() {
		init();
		CartDelivery del = CartDelivery.delivery; 
		System.out.println("[ Delivery Menu ]");
		int sel = input.getValue("[1]배송확인 [2]배송전체취소 [3]배송선택취소 [0]뒤로", 0, 3);
		if(sel == 0) {
			mctrl.nextpage = "MemberMain";
		}else if (sel == 1) {
			del.printUserDelivery(MemberMain.id);
		}else if(sel == 2) {
			del.deleteAllUserDelivery(MemberMain.id);
		}else if(sel == 3) {
			del.deleteSelectiveDelivery(MemberMain.id);
		}
		return true;
	}
}
