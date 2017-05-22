package com.faith.tools;

public class HtmlCode {
	
	//编码
	public static String encode(String text){
		char[] m = text.toCharArray();
		StringBuffer st = new StringBuffer();
		for(int i = 0; i<m.length ;i++){
			st.append((int) m[i]+" ");
		}
		return st.toString();
	}
	
	//解码
	public static String decode(String text){
		String[] m = text.split(" ");
		StringBuffer sb = new StringBuffer();
		try{
			for(int i = 0; i<m.length ;i++){
				sb.append((char)Integer.parseInt(m[i]));
			}
		}
		catch (Exception e) {
			sb = new StringBuffer();
		}
		return sb.toString();
	}
}
