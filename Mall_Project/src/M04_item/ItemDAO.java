package M04_item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ItemDAO {

	ArrayList<Item> itemList;
	private int itemNumber;
	public ItemDAO() {
		itemList = new ArrayList<Item>();
		itemNumber = 1000;
		earlyset();
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
	
	
	
	
	
	void earlyset(){
		String itemArrCategory[] = {"과자","과자","과자","음료수"};
		String itemName[] = {"새우깡","칸쵸","포카칩","마운틴듀"};
		int itemPrice [] = {2000,1500,1800,3000};
		for (int i = 0; i < itemName.length; i++) {
			itemList.add(new Item(getItemNumber(), itemArrCategory[i], itemName[i], itemPrice[i]));
		}
	}
	
	
	
}
