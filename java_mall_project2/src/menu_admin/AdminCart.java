package menu_admin;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;
import menu_member.MemberCart;

public class AdminCart implements MenuCommand{

	Util input = Util.input;
	private MallController mctrl;
	private CartDAO cdao;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance(); 
		cdao = CartDAO.getinstance();
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.println("[ Addmin Cart Menu]");
			int sel = input.getValue("[1]카트확인 [2]전체삭제 [3]분할삭제 [0]뒤로", 0, 3);
			if (sel == 0) {
				mctrl.nextpage = "AdminMain";
			} else if (sel == 1) {
				System.out.println(cdao.getCartList());
			} else if (sel == 2) {
				cdao.delete_all();
			} else if (sel == 3) {
				int index = 0;
				for (String s : cdao.getcartUserid()) {
					System.out.println(++index + " - " + s);
				}
				sel = input.getValue("카트에서 삭제할 유저 입력 (0 - 취소)", 0, cdao.getcartUserid().size())-1;
				if(sel == -1 || sel == -2)continue;
				String id = cdao.getcartUserid().get(sel);
				cdao.deleteCart(id,false);
			}
			return true;
		}
	}
}
