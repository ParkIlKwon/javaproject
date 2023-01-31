package M04_item;

import java.util.ArrayList;

import M01_Util.Util;
import M03_Member.MemberController;
import M05_Cart.CartDAO;

public class ItemController {
	private ItemController() {}
	static private ItemController instance = new ItemController();
	static public ItemController getInstance() {
		return instance;
	}
	
	ItemDAO itemDAO;
	CartDAO cartDAO;
	MemberController memctrl;
	String id;

	public void init(){
		itemDAO = itemDAO.idao;
		cartDAO = CartDAO.getCartDAO();
		memctrl = new MemberController();
	}
	
	//카테고리 메뉴
	public void categoryMenu(String id) {
		this.id = id;
		ArrayList<String>iList = itemDAO.getCategory();
		
		while (true) {
			int num = 0;
			for (String s  : iList) {
				++num;
				System.out.println(num+ " -> " + s);
			}
			int sel = Util.input.getValue("카테고리를 선택하세요. [0 누르면 뒤로]", 0, iList.size())-1;
			if(sel == -2) continue;
			if (sel == -1) {
				break;
			}else {
				itemMenu(iList.get(sel));
			}
		}
	}

	//아이템 매뉴
	public void itemMenu(String category) {
		ArrayList<Item>iList = itemDAO.filterling(category);
		
		for (Item item : iList) {
			System.out.println(item.getPrice());
		}
		
		while (true) {
			int num = 0;
			System.out.printf("현재 선택된 카테고리 : %-4s, 하위 품목\n", category);
			for (Item item: iList) {
				++num;
				System.out.println(num + " -> " + item.getItemName());
			}
			int sel = Util.input.getValue("아이템을 선택하세요. [0 누르면 뒤로]", 0, iList.size())-1;
			if(sel == -2) continue;
			if (sel == -1) {
				break;
			}else {
				cartDAO.addCart(iList.get(sel).getItemName(), id, iList.get(sel).getPrice());
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
