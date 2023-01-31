package menu_member;

import java.util.ArrayList;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;
import item.Item;
import item.ItemDAO;

public class MemberItem implements MenuCommand{

	static String categoryName;
	
	Util input = Util.input;
	MallController mctrl; 
	ItemDAO idao;
	CartDAO cdao;
	
	ArrayList<Item>itemList;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance();
		idao = ItemDAO.getinstance();
		cdao = CartDAO.getinstance();
		itemList = idao.getItemlist(categoryName);
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.printf("- [%s]의 아이템 목록 -\n",categoryName);
			for (int i = 0; i < itemList.size(); i++) {
				System.out.printf("(%d) %s\n" , i+1 , itemList.get(i).getItemName());
			}
			System.out.println("(0)뒤로");
			int sel = input.getValue("아이템을 선택하세요.", 0, itemList.size())-1;
			
			if (sel == -2) {
				continue;
			}else if(sel == -1){
				mctrl.nextpage = "MemberShop";
				return true;
			}else {
				cdao.addCart(itemList.get(sel).getItemName(),MemberMain.id, 
						itemList.get(sel).getPrice());
			}
		}
	}
}













