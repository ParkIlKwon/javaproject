package Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	public static Scanner sc = new Scanner(System.in);
	public static Util input = new Util();
	
	public String getString(String msg , boolean flag){
		System.out.println(msg + " 입력 >>");
		String str = "";
		try {
			str  = sc.next();
			int cnt = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) > '0' && str.charAt(i) <= '9') {
					cnt ++;
				}
			}
			if(str.equals("")) {
				throw new Exception("아무것도 입력되지 않았습니다.");
			}else if (cnt > 0 && flag) {
				throw new Exception("숫자가 입력될 수 없습니다.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "";
		}
		sc.nextLine();
		return str;
	}
	//
	public int getValue(String msg,int n , int m) {
		System.out.println(msg + " 입력 >>");
		int num = 0;
		try {
			num = sc.nextInt();
			if (num < n || num > m) {
				throw new Exception();
			}
		}catch (InputMismatchException e) {
		System.err.println("문자열이 입력될 수 없습니다 :(");
		sc.nextLine();
		return -1;
		} 
		catch (Exception e) {
		System.err.println("범위초과 에러 :(");
		return -1;
		}
		return num;
	}
}
