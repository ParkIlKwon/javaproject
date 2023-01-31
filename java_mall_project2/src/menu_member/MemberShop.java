package menu_member;

import java.util.ArrayList;

import Util.Util;
import _mall.MenuCommand;
import controller.MallController;
import item.ItemDAO;
import menu_mall.MallMain;

public class MemberShop implements MenuCommand {

	Util input = Util.input;
	MallController mctrl;
	ItemDAO idao;
	ArrayList<String> cateList;

	@Override
	public void init() {
		idao = ItemDAO.getinstance();
		mctrl = MallController.getinstance();
		cateList = idao.getCategory();
	}

	@Override
	public boolean update() {
		
		init();
		while (true) {
			System.out.println("[ Shopping Menu ]");
			for (int i = 0; i < cateList.size(); i++) {
				System.out.printf("(%d)%-4s\n", i+1 ,cateList.get(i));
			}
			System.out.print("(0)뒤로\n");
			int sel = input.getValue("카테고리 선택", 0, cateList.size()) - 1;

			if(sel == -2) {
				System.err.println("카테고리 선택오류");
				continue;
			}else if (sel == -1) {
				mctrl.nextpage = "MemberMain";
				
			}else {
				mctrl.nextpage = "MemberItem"; 
				MemberItem.categoryName = cateList.get(sel);
			}
			return true;
		}
	}
}













