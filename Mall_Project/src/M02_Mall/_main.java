package M02_Mall;

import M03_Member.MemberController;
import M04_item.ItemController;
import M05_Cart.CartController;
import M06_Admin.adminController;

public class _main {

	public static void main(String[] args) {

		MallController.getInstance().init();
		MemberController.getInstance().init();
		ItemController.getInstance().init();
		CartController.getInstance().init();
		adminController.getInstance().init();
		MallController.getInstance().menuMall();
	}

}
