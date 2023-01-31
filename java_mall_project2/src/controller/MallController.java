package controller;

import java.util.HashMap;

import _mall.MenuCommand;
import board.BoardMain;
import menu_admin.*;
import menu_mall.*;
import menu_member.*;


public class MallController {
	private HashMap<String, MenuCommand>mall_List;
	private MenuCommand menuCommand;
	public String nextpage;
	
	private MallController () {}
	private static MallController instance = new MallController();
	public static MallController getinstance() {
		return instance;
	}
	
	public void init() {
		mall_List = new HashMap<>();
		
		mall_List.put("MallMain", new MallMain());
		mall_List.put("MallLogin", new MallLogin());	
		mall_List.put("MallAccount", new MallAccount());
		
		mall_List.put("MemberMain",new MemberMain());
		mall_List.put("MemberShop", new MemberShop());
		mall_List.put("MemberItem", new MemberItem()); 
		mall_List.put("MemberCart", new MemberCart());
		mall_List.put("MemberDelivery", new MemberDelivery());
		
		mall_List.put("BoardMain", new BoardMain());
		
		mall_List.put("AdminMain", new AdminMain());
		mall_List.put("AdminItem", new AdminItem());
		mall_List.put("AdminCart", new AdminCart());
		mall_List.put("AdminMember", new  AdminMember());
		
		menuCommand = mall_List.get("MallMain");
		update();
	}
	
	public void update() {
		while (true) {
			if (menuCommand.update()) {
				menuCommand = mall_List.get(nextpage);
			}else {
				break;
			}
		}
	}
}
