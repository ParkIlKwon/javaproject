package M02_Mall;

import M01_Util.Util;
import M03_Member.MemberController;
import M05_Cart.CartController;
import M05_Cart.CartDAO;
import M06_Admin.adminController;

public class MallController {

	MemberController mctrl ;
	adminController adminctrl;
	CartController cartctrl;
	
	
	private MallController() {}
	static private MallController instance = new MallController();
	static public  MallController getInstance(){
		return instance;
	}
		
	void printMenu(){
		System.out.println(">>>>>> Mall <<<<<< ");
		System.out.println("[1] log in [2] new account [3] exit");
	}
	
	void init(){
		
		mctrl = MemberController.getInstance();
		adminctrl = adminController.getInstance();
		cartctrl = CartController.getInstance();
	}
	
	public void menuMall(){
		while (true) {
			int sel = -1;
			boolean flag = false;
			printMenu();
			
			sel = Util.input.getValue("메뉴",1,3);
			
			if(sel == -1 )continue;
			
			if (sel == 1) {
			flag = mctrl.login();
				if (flag) {
					if (mctrl.id.equals("admin")) {
						adminctrl.menuAdmin();
					}else {
						mctrl.MemberMenu();
					}
				}else {
					System.err.println("로그인 실패 wwww");
					continue;
				}
			} else if(sel == 2){
				mctrl.new_account();
			} else if (sel == 3) {
				System.out.println("종료합니다.");
				break;
			}
		}
	}
	
	
}
