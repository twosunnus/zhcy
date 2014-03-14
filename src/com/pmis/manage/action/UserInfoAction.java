package com.pmis.manage.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmis.manage.model.UserInfo;
import com.pmis.manage.service.JobInfoService;
import com.pmis.manage.service.UserInfoService;
import com.pmis.util.Base64;
import com.pmis.util.MD5;
import com.pmis.util.PageConfig;

@Controller
@RequestMapping("/userInfoAction")
public class UserInfoAction {

	private UserInfoService userInfoService;
	private JobInfoService jobInfoService;
	
	@RequestMapping(params = "method=look")
	public String look(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page").toString());
		int totalpage = PageConfig.getTotalPage(userInfoService.getUserInfoDao().pageQuery("from UserInfo", null).size());
		request.setAttribute("userInfoList",userInfoService.pageQuery(page,PageConfig.page_num,""));
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("page", page+"");
		return "pc/manage/lookUserInfo";
	}
	@RequestMapping(params = "method=toadd")
	public String toadd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("jobInfoList",jobInfoService.pageQuery(""));
		return "pc/manage/addUserInfo";
	}
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		UserInfo uiBean = new UserInfo();
		String username = null,password = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload up = new ServletFileUpload(factory);
		List<FileItem> ls = up.parseRequest(request);
		for (FileItem fileItem : ls) {
			if (fileItem.isFormField()) {
				if(fileItem.getFieldName().equals("ui_id")){
					uiBean.setUi_id(Integer.parseInt(fileItem.getString("utf-8")==null?"0":fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("ui_username")){
					username = fileItem.getString("utf-8");
					uiBean.setUi_username(fileItem.getString("utf-8"));
				}else if(fileItem.getFieldName().equals("ui_password")){
					password = fileItem.getString("utf-8");
					uiBean.setUi_password(MD5.EncoderByMd5(fileItem.getString("utf-8")));
					uiBean.setUi_basepassword(Base64.encryptBASE64(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("ui_ji_id")){
					uiBean.setUi_ji_id(fileItem.getString("utf-8"));
				}
			}
		}
		userInfoService.saveOrUpdate(uiBean);
		return "forward:/userInfoAction.do?method=look";
	}
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest request,HttpServletResponse response) {
		int ui_id = Integer.parseInt(request.getParameter("ui_id")==null?"0":request.getParameter("ui_id"));
		request.setAttribute("userInfoBean", userInfoService.getById(ui_id));
		request.setAttribute("jobInfoList",jobInfoService.pageQuery(""));
		return "pc/manage/editUserInfo";
	}
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		int ui_id = Integer.parseInt(request.getParameter("ui_id")==null?"0":request.getParameter("ui_id"));
		userInfoService.delete(userInfoService.getById(ui_id));
		return "forward:/userInfoAction.do?method=look";
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	@Resource
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	public JobInfoService getJobInfoService() {
		return jobInfoService;
	}
	@Resource
	public void setJobInfoService(JobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}
	
}
