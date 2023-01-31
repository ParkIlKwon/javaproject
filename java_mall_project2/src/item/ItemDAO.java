package item;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import cart.Cart;
import cart.CartDAO;

public class ItemDAO {

	public ArrayList<Item> itemList;
	public ArrayList<String> categoryList;
	private int itemNumber;

	// 아이템 번호
	int getItemNumber() {
		if (itemList.size() == 0) {
			itemNumber = 1001;
		}
		return itemList.size() == 0 ? itemNumber : itemList.get(itemList.size()-1).getNumber()+1;
	}

	private ItemDAO() {
		itemList = new ArrayList<Item>();
		itemNumber = 1000;
		earlyset();
	}
	private static ItemDAO instance = new ItemDAO();
	public static ItemDAO getinstance() {
		return instance;
	}
	
	// 카테고리 목록 가져오기.
	public ArrayList<String> getCategory() {
		TreeSet<String>list = new TreeSet<String>();

		for (Item i : itemList) {
			list.add(i.getCategoryName());
		}
		ArrayList<String>temp = new ArrayList<>(list);
		
		return temp;
	}

	// 해당 카테고리에 해당되는 아이템만 가져옴
	public ArrayList<Item> getItemlist(String category) {
		ArrayList<Item> Temp = new ArrayList<Item>();
		for (Item i : itemList) {
			if (i.getCategoryName().equals(category)) {
				Temp.add(i);
			}
		}
		return Temp;
	}
	
	//카테고리 중복체크
	public boolean checkCategory(String category) {
		for (String string : getCategory()) {
			if(string.equals(category)) {
				return true;
			}
		}
		return false;
	}
	
	//아이템 중복체크
		public boolean checkitem(String item) {
			for (int i = 0; i < itemList.size(); i++) {
				if(itemList.get(i).getItemName().equals(item)) {
					return true;
				}
			}
			return false;
		}
	
	//카테고리로 해당되는 아이템 삭제
		public boolean deletebyCategory(String category) {
			CartDAO dao = CartDAO.getinstance();
			ArrayList<Cart>list = dao.getCartList();
			
			for (int k = 0; k < getItemlist(category).size(); k++) {
				for (int i = 0; i < list.size() ; i++) {
					if(list.get(i).getItemName().
							equals(getItemlist(category).get(k).getItemName())) {
						System.err.println("카트에 담긴 아이템은 삭제할 수 없습니다.");
						return false;
					}
				}
			}
			
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getCategoryName().equals(category)) {
					itemList.remove(i);
					i--;
				}
			}
			return true;
		}
		
		// 아이템으로 해당되는 아이템 삭제
		public boolean deletebyItem(String item) {
			CartDAO dao = CartDAO.getinstance();
			ArrayList<Cart> list = dao.getCartList();
			for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItemName().equals(item)){
						System.err.println("카트에 담긴 아이템은 삭제할 수 없습니다.");
						return false;
					}
			}
					
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getItemName().equals(item)) {
					itemList.remove(i);
					return true;
				}
			}
			return true;
		}
		
		
		
		
	// 아이템 넣기.
	public void addItem(String category,String itemName,int price) {
		itemList.add(new Item(getItemNumber(), category, itemName, price));
	}
	
	
	
	
	

	// 초기 세팅
	private void earlyset() {
		String itemArrCategory[] = { "과자", "과자", "과자", "음료수" };
		String itemName[] = { "새우깡", "칸쵸", "포카칩", "마운틴듀" };
		int itemPrice[] = { 2000, 1500, 1800, 3000 };
		for (int i = 0; i < itemName.length; i++) {
			itemList.add(new Item(getItemNumber(), itemArrCategory[i], itemName[i], itemPrice[i]));
		}
	}
	
	
	
	
}
