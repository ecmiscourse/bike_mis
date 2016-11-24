package cn.easybike.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* 登录密码加密<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月24日下午1:18:37 马辉 新建
*/
public class MD5Login {
	 	/** 
	    * md5加密(ITS) 
	    * @param str 
	    * @param charSet 
	    * @return 
	    */  
	public synchronized static final String getMD5Str(String str,String charSet) { 
		//md5加密  
	    MessageDigest messageDigest = null;    
	    try {    
	        messageDigest = MessageDigest.getInstance("MD5");    
	        messageDigest.reset();   
	        if(charSet==null){  
	            messageDigest.update(str.getBytes());  
	        }else{  
	            messageDigest.update(str.getBytes(charSet));    
	        }             
	    } catch (NoSuchAlgorithmException e) {
	    	System.out.println(e);
	    } catch (UnsupportedEncodingException e) {    
	    	System.out.println(e);  
	    }    
	      
	    byte[] byteArray = messageDigest.digest();    
	    StringBuffer md5StrBuff = new StringBuffer();    
	    for (int i = 0; i < byteArray.length; i++) {                
	        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)    
	            md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));    
	        else    
	            md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));    
	    }    
	    return md5StrBuff.toString();    
	}  
}
