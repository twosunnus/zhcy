package com.pmis.util;

import java.util.List;

import com.pmis.manage.model.AllotInfo;

//获得sql常用字符串
public class SqlCommon {
	public static String getInSqlWithClass(Object obj,List<?> objList){
		StringBuffer returnStr = new StringBuffer("");
		if(obj instanceof AllotInfo){
			for(int i = 0 ; i < objList.size() ; i++){
				AllotInfo allotBean = (AllotInfo)objList.get(i);
				if(returnStr.toString().equals("")||returnStr.length() == 0){
					returnStr.append("(" + allotBean.getMi_id());
				}else{
					returnStr.append("," + allotBean.getMi_id());
				}
				if( i == objList.size() - 1){
					returnStr.append(")");
				}
			}
		}
		return returnStr.toString();
	}
	
}
