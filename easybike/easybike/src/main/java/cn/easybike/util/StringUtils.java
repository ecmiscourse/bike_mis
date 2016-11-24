package cn.easybike.util;

import java.util.Random;

public class StringUtils {
	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";    
	   /** 
	    * 获取任意位的随机字符串(0-9 a-z A-Z) 
	    * @param size 位数 
	    * @return 
	    */  
	   public static final String getRandomNum(int size){  
	    StringBuffer sb = new StringBuffer();    
	    Random random = new Random();  
	    for (int i = 0; i < size; i++) {    
	        sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));    
	    }  
	    return sb.toString();  
	   }  
}
