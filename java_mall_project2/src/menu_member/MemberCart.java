package menu_member;

import java.util.HashMap;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;

public class MemberCart implements MenuCommand{

	MallController mctrl;
	CartDAO cdao;
	Util input = Util.input;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance();
		cdao = CartDAO.getinstance();
	}

	@Override
	public boolean update() {
		while (true) {
			init();
			
			System.out.println("[ Cart Menu ]");
			int sel = input.getValue("[1]확인 [2]구입 [3]아이템빼기 [4]카트초기화[0]뒤로", 0, 4);
			if(sel == -1)continue;
			if(sel == 0) {
				mctrl.nextpage = "MemberMain"; 
			}else if (sel == 1 && cdao.printUsercart(MemberMain.id)) {
			}else if(sel == 2 && cdao.printUsercart(MemberMain.id)){
				System.out.println("==================");
				System.out.print("합  계:");
				cdao.deleteCart(MemberMain.id, true);
				System.out.println(">>>>>구입완료");
			}else if (sel == 3 && cdao.getCartList().size() != 0) {
				while (true) {
					int num = 0;
					for (int i = 0; i < cdao.getCartList().size(); i++) {
						System.out.printf("(%d)%s\n",++num,cdao.getCartList().get(i).getItemName());
					}
					sel = input.getValue("삭제할 아이템 번호 [0]뒤로", 0, cdao.getCartList().size())-1;
					if(sel == -1) break;
					if(sel != -2) {
						cdao.DeleteCartbyItem(cdao.getCartList().get(sel).getItemName());
					}
				}
			}else if (sel == 4 && cdao.getCartList().size() != 0) {
				cdao.delete_all();
				System.out.println("초기화 완료");
				mctrl.nextpage = "MemberMain"; 
			}else {
				System.err.println(MemberMain.id + "님이 구입한 물건은 없습니다.");
			}
			return true;
		}
	}
}
