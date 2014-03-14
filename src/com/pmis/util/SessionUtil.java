package com.pmis.util;

import javax.servlet.http.HttpServletRequest;

import com.pmis.manage.model.UserInfo;

public class SessionUtil {
	public static int getUi_id(HttpServletRequest request){
		return ((UserInfo)request.getSession().getAttribute("userBean")).getUi_id();
	}
}
