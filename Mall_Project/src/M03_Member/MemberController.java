package M03_Member;

import M01_Util.Util;
import M02_Mall.MallController;
import M04_item.ItemController;
import M05_Cart.CartController;
import M05_Cart.CartDAO;

public class MemberController {

	public String id;
	MemberDAO memdao;
	ItemController itemCtrl;
	CartController CartCtrl;
	CartDAO dao;
	
	public MemberController() {}
	static private MemberController instance = new MemberController();
	static public  MemberController getInstance(){
		return instance;
	}
	public void init(){
		dao = CartDAO.getCartDAO();
		memdao = MemberDAO.getInstance();
		CartCtrl = CartController.getInstance();
		itemCtrl = ItemController.getInstance();
	}
	
	public boolean login() {
		System.out.println("저장된 정보");
		for (Member m : memdao.getMemberlist()) {
			System.out.println(m);
		}
		System.out.println(" ==== 로그인 메뉴 ====");
		id = Util.input.getString("id");
		if(id.equals(""))return false;
		System.out.println("pw 입력");
		String pw = Util.sc.next();
		return memdao.chkMemberInfo(id, pw);
		
	}
	
	public void new_account() {
		System.out.println(" ==== 회원가입 메뉴 ====");
		id = Util.input.getString("id");
		if (memdao.chkduplicate(id)) return;
		System.out.println("pw 입력 >>");
		String pw = Util.sc.next();
		String name = Util.input.getString("name");
		if(!memdao.chkName(name)) {
			System.err.println("이름엔 숫자가 포함될 수 없습니다 :m");
			return;
		}
		memdao.accountMember(id, pw, name);
	}
	
	public void MemberMenu() {
		while (true) {
			int sel = Util.input.getValue("[1.쇼핑] [2.장바구니] [3.게시판] [0.뒤로가기] ", 0, 3);
			
			if (sel == 0 ) {
				break;
			} else if (sel == 1){
				itemCtrl.categoryMenu(id);
			} else if (sel == 2) {
				CartCtrl.cartMenu();
			}else if (sel == 3) {
				
			}
			

		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
