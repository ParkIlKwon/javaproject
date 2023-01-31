package M06_Admin;

import java.util.ArrayList;
import java.util.TreeSet;

public class treeset {

	public static void main(String[] args) {
		ArrayList<String>list = new ArrayList<String>();
		list.add("c");
		list.add("a");
		list.add("a");
		list.add("b");
		TreeSet<String> categorySet = new TreeSet<String>();
		
		for (int i = 0; i < list.size(); i++) {
			categorySet.add(list.get(i));
		}
		categorySet.descendingSet();
		for (String string : categorySet) {
			System.out.println(string); //이거 배웠었나 ??
		}
	}
}
