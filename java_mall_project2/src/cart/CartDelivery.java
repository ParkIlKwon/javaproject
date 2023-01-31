package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Util.Util;

public class CartDelivery {

	int index = 0;
	Util input = Util.input;
	public ArrayList<CartOrderList> totalList ;
	HashMap<String, ArrayList<CartOrderList>> dellist = new HashMap<>();

	public static CartDelivery delivery = new CartDelivery();

	public void SetDelivery(ArrayList<Cart> list) {
		int period = list.size() >= 3? 3 : 2;
		String id = list.get(0).getMemberID();
		ArrayList<CartOrderList> orderList = new ArrayList<>();
		totalList = new ArrayList<CartOrderList>();
		
		if(dellist.get(id) != null ) {
		for (int i = 0; i < dellist.get(id).size(); i++) {
			totalList.add(new CartOrderList(dellist.get(id).get(i).period
					, dellist.get(id).get(i).Cart));
		}}
		
		totalList.add(new CartOrderList(period,list));
		
		for (int i = 0; i < totalList.size(); i++) {
				orderList.add(totalList.get(i));
		}
		
		dellist.put(id, orderList);

	}

	public void printUserDelivery(String id) {
		int num = 0;
		if(dellist.get(id) == null) {
			System.out.println("해당사용자의 주문내역은 없습니다.");
			return;
		}
		for (int i = 0; i < dellist.get(id).size(); i++) {
			dellist.get(id).get(i).printList(++num);
			System.out.printf("예상배송시간 : %d 일\n" , dellist.get(id).get(i).period);
		}
	}
	
	public void deleteAllUserDelivery(String id) {
		dellist.remove(id);
	}
	
	public void deleteSelectiveDelivery(String id) {
		if(dellist.get(id) == null) {
			System.out.println("해당사용자의 주문내역은 없습니다.");
			return;
		}
		printUserDelivery(id);
		int sel = input.getValue("삭제하실 번호 선택", 1, dellist.get(id).size());
			if(sel == -1)return;
			dellist.get(id).remove(sel-1);
			
	}
}













