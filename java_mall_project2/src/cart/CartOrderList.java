package cart;

import java.util.ArrayList;

public class CartOrderList {

	int period;
	public ArrayList<Cart> Cart;
	
	public CartOrderList(int period, ArrayList<Cart> cart) {
		super();
		this.period = period;
		Cart = cart;
	}

	public void printList(int num) {
		int index = 0 ;
		int price = 0;
		System.out.println("========================");
		System.out.printf("(%d) %s 님의 주문내역: \n" ,num,Cart.get(0).getMemberID());
		for (Cart c : Cart) {
			System.out.printf("[%d] %s \n",++index,c.getItemName());
			price += c.getItemPrice();
		}
		System.out.printf("합계 : %d  \n" , price);
		
	}
}
   