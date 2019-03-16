package com.movie.util;
/**
 * なんかまとめて使いそうなやつあればここに
 * @author yuhei
 *
 */
public class Util {
	//jsonを配列に
	public static String[] toArrayJsonSheetCd(String json) {
		json = json.substring(2, json.length() - 2);
		json = json.replaceAll("\"", "");
		return json.split(",");
	}
	public static String makeReservationCd() {
		String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] num = {"1","2","3","4","5","6","7","8","9"};
		
		String reservationCd = "";
		for(int i = 0;i<4;i++) {
			reservationCd +=alphabet[(int)(Math.random()*25)];
		}
		for(int i = 0;i<4;i++) {
			reservationCd +=num[(int)(Math.random()*8)];
		}
		return reservationCd;
	}
}
