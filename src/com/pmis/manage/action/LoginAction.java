package com.pmis.manage.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmis.manage.model.UserInfo;
import com.pmis.manage.service.UserInfoService;
import com.pmis.util.MD5;

@Controller
@RequestMapping("/loginAction")
public class LoginAction {
	
	private UserInfoService userInfoService;
	
	
	@RequestMapping(params = "method=login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String ui_username = request.getParameter("ui_username");
		String ui_password = request.getParameter("ui_password");
		List<UserInfo> userList = (List<UserInfo>) userInfoService.getUserInfoDao().pageQuery("from UserInfo where ui_username = ?", ui_username);
		String select_type = request.getParameter("select_type")==null?"1":request.getParameter("select_type");
		if(userList.size()>0){
			if(MD5.checkpassword(ui_password, userList.get(0).getUi_password())){
				UserInfo userBean = new UserInfo();
				userBean.setUi_id(userList.get(0).getUi_id());
				userBean.setUi_username(ui_username);
				userBean.setUi_password(ui_password);
				request.getSession().setAttribute("userBean", userBean);
				if(select_type.equals("1")){
					return "pc/index";
				}else{
					return "pc/reduceindex";
				}
			}else{
				return "login";
			}
		}else{
			return "login";
		}
	}
	@RequestMapping(params = "method=logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		request.getSession().removeAttribute("userBean");
		return "login";
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	@Resource
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}
