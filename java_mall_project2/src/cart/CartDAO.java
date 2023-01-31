package cart;

import java.util.ArrayList;
import java.util.TreeSet;

import item.ItemDAO;

public class CartDAO {
	private CartDAO() {
	};

	private ArrayList<Cart> cartList = new ArrayList<Cart>();
	static public CartDAO instance = new CartDAO();

	static public CartDAO getinstance() {
		return instance;
	}

	// 카트전부출력
	void printAllcart() {
		System.out.println(cartList);
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	// 전부삭제
	public void delete_all() {
		cartList.clear();
	}

	// 카트에 물건 담기
	public void addCart(String item, String id, int price) {
		System.out.println(item + "를 담았습니다.");
		Cart cart = new Cart(getcartNumber(), id, item, price);
		cartList.add(cart);
	}

	// 특정사용자의 카트만 출력
	public boolean printUsercart(String id) {
		int cnt = 0;
		ItemDAO dao = ItemDAO.getinstance();
		boolean check = false;
		for (int i = 0; i < dao.itemList.size(); i++) {
			cnt = 0;
			String item1 = dao.itemList.get(i).getItemName();
			for (int k = 0; k < cartList.size(); k++) {
				String item2 = cartList.get(k).getItemName();
				if (item1.equals(item2) && cartList.get(k).getMemberID().equals(id)) {
					cnt++;
				}
			}
			if (cnt != 0) {
				check = true;
				System.out.printf("[ %s ] %d 개\n", item1, cnt);
			}
		}
		return check == true ? true : false;
	}

	// 카트에 담긴 아이템 삭제
	public void deleteCart(String id, boolean check) {

		int price = 0;
		ArrayList<Cart>list = null;
		if(check) {
			list = new ArrayList<Cart>();
		}
		
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(id)) {
				if(check) {
				list.add(cartList.get(i));
				price += cartList.get(i).getItemPrice();}
				cartList.remove(i);
				i --;
			}
		}
		
		if(check) {
			CartDelivery.delivery.SetDelivery(list);
			System.out.println(price);
		}
	}
	
	// 카트에 담긴 아이템 아이템 선택해서 삭제
	public void DeleteCartbyItem(String item) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemName().equals(item)) {
				cartList.remove(i);
			}
		}
	}
	
	public ArrayList<String> getcartUserid() {
		TreeSet<String>list = new TreeSet<String>();
		for (int i = 0; i < cartList.size(); i++) {
			list.add(cartList.get(i).getMemberID());
		}
		list.descendingIterator();
		return new ArrayList<String>(list);
	}

	private int getcartNumber() {
		if (cartList.size() == 0)
			return 1001;

		int num = 0;
		for (Cart cart : cartList) {
			if (num < cart.getNumber()) {
				num = cart.getNumber();
			}
		}
		return ++num;
	}

}
