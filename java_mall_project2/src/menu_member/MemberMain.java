package menu_member;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;
import menu_mall.MallMain;

public class MemberMain implements MenuCommand{

	private MallController mctrl;
	private CartDAO cdao;
	static public String id ;
	Util input = new Util();
	
	@Override
	public void init() {
		mctrl = MallController.getinstance(); 
		cdao = CartDAO.getinstance();
	}

	@Override
	public boolean update() {
		init();
		if(id.equals("admin")) {
			mctrl.nextpage = "AdminMain";
			return true;
		}
		while (true) {
			System.out.println("[ Member menu ]");
			System.out.println("login info : " + id);
			System.out.println("===========================");
			int sel = input.getValue("[1]쇼핑 [2]카트 [3]게시판 [4]배송 [0]로그아웃", 0, 4);
			if(sel == 0) {
				mctrl.nextpage = "MallMain";
			}else if (sel == 1) {
				mctrl.nextpage = "MemberShop";
			}else if (sel == 2) {
				mctrl.nextpage = "MemberCart"; 
			}else if (sel == 3) {
				mctrl.nextpage = "BoardMain";
			}else if (sel == 4) {
				mctrl.nextpage = "MemberDelivery";
			}
			return true;
		}
	}
}
