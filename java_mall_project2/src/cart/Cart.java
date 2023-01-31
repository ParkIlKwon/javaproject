package cart;

public class Cart {
	
	private int number;
	private String memberID;
	private String itemName;
	private int itemPrice;
	
	public Cart() {}
	public Cart(int number, String memberID, String itemName , int itemPrice) {
		super();
		this.number = number;
		this.memberID = memberID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String toString() {
		String print = String.format("[%-6d] [아이디: %5s] [상품이름:%6s] [가격:%6d]\n", 
				number , memberID , itemName , itemPrice);
		return print;
	}
}
