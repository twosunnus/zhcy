package com.pmis.util;
/*
 * 对字符串的一些处理，包括为空判断，转码等
 */
public class StringFormat {

	/**
	 *	判断字符串是否为空或为""
	 *  为空返回false
	 */
	public static boolean notEmpty(Object value){
		if(value == null || "".equals(value) ){
			return false;
		}
		return true;
	}
}
