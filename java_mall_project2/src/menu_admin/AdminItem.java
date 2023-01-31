package menu_admin;

import java.util.ArrayList;

import Util.Util;
import _mall.MenuCommand;
import cart.CartDAO;
import cart.CartDelivery;
import controller.MallController;
import item.Item;
import item.ItemDAO;
import menu_mall.MallMain;

public class AdminItem implements MenuCommand {

	private Util input = Util.input;
	private MallController mctrl;
	private ItemDAO idao;
	private CartDelivery dell;

	@Override
	public void init() {
		mctrl = MallController.getinstance();
		idao = ItemDAO.getinstance();
		dell = CartDelivery.delivery;
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.println("[ Addmin Item Menu ]");
			int sel = input.getValue("[1]아이템보기 [2]아이템추가 [3]아이템삭제 [0]뒤로", 0, 3);
			if (sel == -1) {
				continue;}
			if (sel == 0) {
				mctrl.nextpage = "AdminMain";
			} else if (sel == 1) {
				System.out.println(idao.itemList);
			} else if (sel == 2) {
				String category = "";
				String itemname = "";
				int price = 0;

				sel = input.getValue("[1]카테고리 신규추가 [2]이전 카테고리사용", 1, 2);
				if (sel == 1) {
					while (true) {
						category = input.getString("추가할 카테고리 이름", true);
						if(category.equals(""))continue;
						if (idao.checkCategory(category)) {
							System.err.println("카테고리 이름이 겹칩니다.");
							continue;
						}
						break;
					}

				} else if (sel == 2) {
					for (int i = 0; i < idao.getCategory().size(); i++) {
						System.out.printf("[%d]%s \n", i + 1, idao.getCategory().get(i));
					}
					while (true) {
						sel = input.getValue("사용할 카테고리를 고르세요.", 1, idao.getCategory().size()) - 1;
						if (sel == -2)
							continue;
						category = idao.getCategory().get(sel);
						break;
					}
				}

				while (true) {
					itemname = input.getString("[추가]아이템 이름", true);
					if(itemname.equals("")) continue;
					if (idao.checkitem(itemname)) {
						System.err.println("아이템 이름이 겹칩니다");
						continue;
					}
					price = input.getValue("[추가]아이템 가격", 0, 99999);
					if (!itemname.equals("") && price != -1)
						break;
				}

				idao.addItem(category, itemname, price);
				System.out.println("추가완료.");
				break;
			} else if (sel == 3) {
				sel = input.getValue("[1]카테고리로 삭제 [2] 아이템선택으로 삭제", 0, 2);
				if (sel == 1) {
					for (int i = 0; i < idao.getCategory().size(); i++) {
						System.out.printf("[%d]%s\n", i + 1, idao.getCategory().get(i));
					}
					sel = input.getValue("삭제할 카테고리 번호", 0, idao.getCategory().size()) - 1;
					if (sel != -2) {
						String category = idao.getCategory().get(sel);
						if (idao.deletebyCategory(category)) {
							System.out.println("삭제완료.");
						}
					}

				} else if (sel == 2) {
					
					for (int i = 0; i < idao.getCategory().size(); i++) {
						System.out.printf("[%d]%s\n", i + 1, idao.getCategory().get(i));
					}
					sel = input.getValue("카테고리 번호 선택", 0, idao.getCategory().size()) - 1;
					if (sel != -1) {
						String category = idao.getCategory().get(sel);
						ArrayList<Item> temp = idao.getItemlist(category);
						for (int i = 0; i < temp.size(); i++) {
							System.out.printf("[%d]%s\n",i+1,temp.get(i).getItemName());
						}
						sel = input.getValue("삭제할 아이템 번호 선택", 0, temp.size()) - 1;
						if (sel != -2) {
							String item = temp.get(sel).getItemName();
							
							if (idao.deletebyItem(item)) {
								System.out.println("삭제완료.");
							}
						}
					}
				}
			}
			return true;
		}
		return true;
	}
}
