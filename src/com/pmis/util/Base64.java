package com.pmis.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	/**         
    * BASE64加密
    */              
    public static String encryptBASE64(String key){               
        return (new BASE64Encoder()).encodeBuffer(key.getBytes());               
    } 
    /**         
    * BASE64解密
    */ 
    public static String decryptBASE64(String key){               
        try {
			return new String((new BASE64Decoder()).decodeBuffer(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
     } 
    public static void main(String []args){
    	System.out.println(decryptBASE64("ZGJoMTIzLm5ldA=="));
    }
    
}
