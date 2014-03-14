package com.pmis.util;

public class PageConfig {
	//页面显示条数
	public static int page_num = 15;
	
	//树结构根节点id
	public static int root_num = 0;
	
	//获得总页数
	public static int getTotalPage(int max_num)
	{
		if(max_num%PageConfig.page_num==0){
			return max_num/PageConfig.page_num;
		}else{
			return max_num/PageConfig.page_num + 1;
		}
	}
}
