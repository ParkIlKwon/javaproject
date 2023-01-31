package M04_item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import M01_Util.Util;
import M03_Member.MemberDAO;
import M05_Cart.CartDAO;

public class ItemDAO {

	public ArrayList<Item> itemList;
	public ArrayList<String>categoryList;
	private int itemNumber;
	public static ItemDAO idao = new ItemDAO();
	private CartDAO cdao;
	
	public ItemDAO() {
		cdao = CartDAO.getCartDAO();
		itemList = new ArrayList<Item>();
		itemNumber = 1000;
		earlyset();
		categoryList = getCategory();
	}
	
	public ArrayList<String> getCategory() {
		TreeSet<String>CategoryList = new TreeSet<String>();
		ArrayList<String>Temp = new ArrayList<String>();
		
		for (int i = 0; i <itemList.size() ; i++) {
			CategoryList.add(itemList.get(i).getCategoryName());
		}
		
		CategoryList.descendingSet();
		for (String s : CategoryList) {
			Temp.add(s);
		}
		return Temp;
	}
	
	public ArrayList<String> getItem(String category) {
		ArrayList<String>Temp = new ArrayList<String>();
		for (Item i : itemList) {
			if (i.getCategoryName().equals(category)) {
				Temp.add(i.getItemName());
			}
		}
		return Temp;
	}
	
	int getItemNumber() {
		return itemNumber++;
	}
	
	ArrayList<Item> filterling(String category){
		ArrayList<Item>Temp = new ArrayList<Item>();
		for (Item i : itemList) {
			if (i.getCategoryName().equals(category)) {
				Temp.add(i);
			}
		}
		return Temp;
	}
	
	public boolean insertcategory(String categoryName) {
		if (!MemberDAO.getInstance().chkName(categoryName)) {
			return false;
		} else {
			categoryList.add(categoryName);
			return true;
		}
	}
	
	public void insertitem() {
		int sel = Util.input.getValue("[1]신규 카테고리 추가 [2]이전 카테고리 사용", 1, 2);
		if (sel == 1) {
			String categoryName = Util.input.getString("저장할 카테고리 이름을 입력하시오 [0: 취소] -");
			for (String string : getCategory()) {
				if (string.equals(categoryName)) {
					System.err.println("카테고리 이름중복");
					return;
				}
			}
			if (insertcategory(categoryName)) {
				while (true) {
					String str =  newItem();
					if (!str.equals("")) {
						itemList.add(new Item(getItemNumber(), categoryName, str
								,Util.input.getValue("아이템가격", 0, 99999)));
						break;
					}
				}
			}
		} else {
			System.out.println("아래의 카테고리 목록중 선택하세요 .");
			for (int i = 0; i < categoryList.size(); i++) {
				System.out.println(i+1+" >> " + categoryList.get(i));
			}
			sel = Util.input.getValue("", 1, categoryList.size())-1;
			itemList.add(new Item(getItemNumber(), categoryList.get(sel), Util.input.getString("아이템 이름")
		, Util.input.getValue("아이템가격", 0, 99999)));
				
			}
		if(itemList.get(itemList.size()-1).getItemName().equals("")||
				itemList.get(itemList.size()-1).getPrice() == -1) {
			System.err.println("아이템 생성 실패...");
			itemList.remove(itemList.size()-1);
		}
	}
	
	String newItem() {
		String str = Util.input.getString("추가할 아이템을 입력하세요 ^o^");
		return MemberDAO.getInstance().chkName(str) ? str : ""; 
	}
	
	
	public void deletecategory() {
		System.out.println("저장된 카테고리 이름");
		int number = 1;
		for (Item item : itemList) {
			System.out.println(number+ " -> " + item.getCategoryName());
			number++;
		}
		String category = itemList.get(Util.input.getValue("삭제할 번호입력 취소는 0 번 ", 1, itemList.size()-1)).getCategoryName();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getCategoryName().equals(category)) {
				itemList.remove(i);
				i--;
			}
		}
	}
	
	public void deleteitem() {
		System.out.println("저장된 아이템 이름");
		int number = 1;
		for (Item item : itemList) {
			System.out.println(number+ " -> " + item.getItemName());
			number++;
		}
		
		int sel = Util.input.getValue("삭제할 번호입력 취소는 0 번 ", 1, itemList.size())-1;
		for (int i = 0; i < cdao.getCartList().size(); i++) {
			if(cdao.getCartList().get(i).getItemName().
					equals(itemList.get(sel).getItemName())) {
				System.err.println("카트에 담긴 아이디는 지울 수 없습니다.");
				return;
			}
		}
		
		itemList.remove(sel);
	}
	
	
	void earlyset(){
		String itemArrCategory[] = {"과자","과자","과자","음료수"};
		String itemName[] = {"새우깡","칸쵸","포카칩","마운틴듀"};
		int itemPrice [] = {2000,1500,1800,3000};
		for (int i = 0; i < itemName.length; i++) {
			itemList.add(new Item(getItemNumber(), itemArrCategory[i], itemName[i], itemPrice[i]));
		}
	}
}
