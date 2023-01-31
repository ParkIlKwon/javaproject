package M06_Admin;

import M01_Util.Util;
import M03_Member.MemberDAO;
import M04_item.ItemDAO;
import M05_Cart.CartController;
import M05_Cart.CartDAO;

public class adminController {

	MemberDAO memdao;
	ItemDAO itemdao;
	CartDAO cartdao;
	
	
	private adminController() {}
	static private adminController instance = new adminController();
	static public  adminController getInstance(){
		return instance;
	}
	
	public void menuAdmin(){
		printMenu();
	}
	
	public void init(){
		memdao = MemberDAO.getInstance();
		itemdao = ItemDAO.idao;
		cartdao = cartdao.getCartDAO();
	}
	
	void printMenu() {
		while (true) {
			int sel = Util.input.getValue("[1.회원관리] [2.상품관리] [3.장바구니관리] [0.뒤로가기]", 0, 3);
			if (sel == 0) {
				break;
			}else if (sel == 1) {
				printAccountInfo();
			}else if (sel == 2) {
				printItemInfo();
			}else if (sel == 3) {
				printCartInfo();
			}
		}
	}
	
	void printAccountInfo() {
		while (true) {
			int sel = Util.input.getValue("[1]보기 [2]삭제 [0]뒤로", 0, 2);
			if (sel == 0) {
				break;
			}else if (sel == 1) {
				System.out.println(memdao.getMemberlist());
			}else if (sel == 2) {
				memdao.deleteMember();
			}
		}
	}
	
	void printItemInfo() {
		while (true) {
			int sel = Util.input.getValue("[1]보기 [2]아이템생성 [3]카테고리삭제 [4]아이템삭제 [0]뒤로", 0, 5);
			if (sel == 0) {
				break;
			}else if (itemdao.itemList.size() == 0) {
				System.err.println("저장된 아이템 정보가 없습니다.");
				continue;
			}else if (sel == 1) {
				System.out.println(itemdao.itemList);
			}else if (sel == 2) {
				itemdao.insertitem();
			}else if (sel == 3) {
				itemdao.deletecategory();
			}else if (sel == 4) {
				itemdao.deleteitem();
			}
		}
	}
	
	void printCartInfo(){
		while (true) {
			int sel = Util.input.getValue("[1]보기 [2]전체삭제 [3]분할삭제 [0]뒤로", 0, 3);
			if (sel == 0) {
				break;
			}else if (cartdao.getCartList().size() == 0) {
				System.err.println("저장된 카트 내용이 없습니다.");
				continue;
			}else if (sel == 1) {
				cartdao.ShowCartList();
			}else if (sel == 2) {
				cartdao.delete_all();
			}else if (sel == 3) {
				cartdao.selectiveDelete();
			}
		}
	}
}










