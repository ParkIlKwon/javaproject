package M05_Cart;

import java.util.ArrayList;
import java.util.TreeSet;

import M01_Util.Util;
import M03_Member.MemberController;

public class CartDAO {
	
	private CartDAO(){};
	private ArrayList<Cart>cartList = new ArrayList<Cart>();
	private CartController ctrl;
	private MemberController MemberCtrl; 
	static public CartDAO instance = new CartDAO();
	static public CartDAO getCartDAO(){
		return instance;
	}

	public void init(){
		
		ctrl = CartController.getInstance();
		MemberCtrl = MemberController.getInstance();
	}
	
	//카트전부출력
	void printAllcart(){
		System.out.println(cartList);
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}
	
	//전부삭제
	public void delete_all() {
		cartList.clear();
	}
	
	//분할삭제
	public void selectiveDelete() {
		System.out.println("카트의 아이디 정보");
		TreeSet<String>temp = new TreeSet<>();
		for (Cart c : cartList) {
			System.out.println(c.getMemberID());
			temp.add(c.getMemberID());
		}
		String id = Util.input.getString("삭제하실 아이디를 입력하세요.");
		if(!temp.contains(id)) return;
		
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(id)) {
				cartList.remove(i);
				i --;
			}
		}
		System.out.println("삭제완료");
	}
	
	//카트에 물건 담기 
	public void addCart(String item, String id, int price){
		System.out.println(item + "를 담았습니다.");
		Cart cart = new Cart(getcartNumber(), id, item, price);
		cartList.add(cart);
	}

	//카트 비우기
	public void deletCart(String id) {
		System.out.println(id + "<----");
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(id)) {
				cartList.remove(i);
				i--;
			}
		}
	}
	
	//특정사용자의 카트만 출력
	void printUsercart(String id){
		for (Cart cart : cartList) {
			if (cart.getMemberID().equals(id)) {
				System.out.println(cart);
			}
		}
	}
	
	int getcartNumber() {
		if(cartList.size()==0) return 1001;
		
		int num = 0;
		for (Cart cart : cartList) {
			if (num < cart.getNumber()) {
				num = cart.getNumber();
			}
		}
		
		return ++num;
	}
	
	public void ShowCartList(){
		System.out.println(cartList);
	}
	
}
