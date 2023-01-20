package M05_Cart;

import java.util.ArrayList;
import java.util.Scanner;

import M01_Util.Util;
import M02_Mall.MallController;
import M03_Member.MemberController;
import M04_item.Item;

public class CartController {
	MallController Mctrl;
	MemberController MemberCtrl;
	CartDAO cdao ;
			
	private CartController() {}
	static private CartController instance = new CartController();
	static public CartController getInstance() {
		return instance;
	}
	
	public void init() {
		Mctrl = MallController.getInstance();
		MemberCtrl = MemberController.getInstance();
		cdao = CartDAO.getCartDAO();
	}
	
	public void cartMenu() {
		System.out.println("=== 카트메뉴 ===");
		while (true) {
			int sel = Util.input.getValue("[1]구입  [2]장바구니 확인 [0]뒤로", 0, 2);
			if (sel == 0) {
				break;
			}else if (sel == 1 && cdao.getCartList().size() != 0) {
				int total = 0;
				for (Cart c : cdao.getCartList()) {
					total+= c.getItemPrice();
				}
				System.out.printf("총 [%d] 원 입니다 . :)",total);
				Mctrl.menuMall();
				cdao.deletCart(MemberCtrl.id);
			}else if (sel == 2 && cdao.getCartList().size() != 0) {
				cdao.printUsercart(MemberCtrl.id);
			}else {
				System.err.println("카트에 물건이 존재 하지 않습니다. :(");
			}

		}
	}
	
}
