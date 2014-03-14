package com.pmis.manage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmis.manage.model.AllotInfo;
import com.pmis.manage.model.MenuInfo;
import com.pmis.manage.service.AllotInfoService;
import com.pmis.manage.service.JobInfoService;
import com.pmis.manage.service.MenuTreeService;
import com.pmis.util.StringFormat;

@Controller
@RequestMapping("/allotInfoAction")
public class AllotInfoAction {
	private AllotInfoService allotInfoService;
	private MenuTreeService menuTreeService;
	private JobInfoService jobInfoService;
	
	@RequestMapping(params = "method=look")
	public String look(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("jobInfoList",jobInfoService.pageQuery(""));
		return "pc/manage/lookAllotInfo";
	} 
	/**
	 * 前台通过AJAX传参ji_id(选中的职位id)
	 * 此Action分为3部
	 * 第一部获得当前职位的有拥有的树结构（当前职位权限菜单）
	 * 第二部获得当前系统的全部树结构
	 * 第三部根据当前职位的树结构以及当前系统的树结构获得前台需要的json串（通过递归）
	 */
	@RequestMapping(params = "method=showMenu")
	public void showMenu(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String ji_id = request.getParameter("ji_id");
		List<MenuInfo> jobMenuList = menuTreeService.getMenuTree(ji_id);
		List<MenuInfo> allMenuList = (List<MenuInfo>) menuTreeService.getMenuTreeDao().pageQuery("from MenuInfo",null);
		allotInfoService.getCheckedList(allMenuList, jobMenuList);
		JSONObject json = new JSONObject();
		json.put("checkedMenuList", menuTreeService.getJsonTreeStr(allMenuList));
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	/**
	 * 该Action当前台点击保存时触发
	 * 首先删除该职位下所有的菜单
	 * 然后根据前台所选中的菜单进行重新的配置
	 */
	@RequestMapping(params = "method=saveCheckMenu")
	public void saveCheckMenu(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		String ji_id = request.getParameter("ji_id")==null?"0":request.getParameter("ji_id");
		String mi_ids = request.getParameter("mi_id")==null?"0":request.getParameter("mi_id");
		allotInfoService.deleteAllMenu(ji_id);
		String mi_id [] = mi_ids.split(",");
		for(int i = 0 ; i < mi_id.length ; i ++){
			if(StringFormat.notEmpty(mi_id[i])){
				AllotInfo aiBean = new AllotInfo();
				aiBean.setJi_id(Integer.parseInt(ji_id));
				aiBean.setMi_id(Integer.parseInt(mi_id[i]));
				allotInfoService.getAllotInfoDao().save(aiBean);
			}
		}
		
	}
	public JobInfoService getJobInfoService() {
		return jobInfoService;
	}
	@Resource
	public void setJobInfoService(JobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}
	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}
	@Resource
	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	public AllotInfoService getAllotInfoService() {
		return allotInfoService;
	}
	@Resource
	public void setAllotInfoService(AllotInfoService allotInfoService) {
		this.allotInfoService = allotInfoService;
	}
	
}
