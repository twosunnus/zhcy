package com.pmis.manage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmis.manage.model.MenuInfo;
import com.pmis.manage.model.UserInfo;
import com.pmis.manage.service.JobInfoService;
import com.pmis.manage.service.MenuTreeService;

@Controller
@RequestMapping("/menuTreeAction")
public class MenuTreeAction {
	
	public MenuTreeService menuTreeService;
	public JobInfoService jobInfoService;
	/**
	 * 该Action为PC登录时左侧树状结构的树
	 * 1.获得登录人的用户ID
	 * 2.根据用户ID获得当前用的职位
	 * 3.通过职位ID获得职位的权限树
	 */
	@RequestMapping(params = "method=getMenuTree")
	public String getMenuTree(HttpServletRequest request,HttpServletResponse response){
		int ui_id = ((UserInfo)request.getSession().getAttribute("userBean")).getUi_id();
		String ji_id = jobInfoService.getJiIdWithUiId(ui_id);
		request.setAttribute("menuTreeList",menuTreeService.getMenuTree(ji_id));
		return "pc/left";
	}
	/**
	 * 获得当前系统的全部树状结构图
	 */
	@RequestMapping(params = "method=lookMenuTree")
	public String lookMenuTree(HttpServletRequest request,HttpServletResponse response){
		List<MenuInfo> menuTreeList = (List<MenuInfo>) menuTreeService.getMenuTreeDao().pageQuery("from MenuInfo order by mi_order asc",null);
		request.setAttribute("jsonTreeStr", menuTreeService.getJsonTreeStr(menuTreeList));
		MenuInfo miBean = (MenuInfo) menuTreeService.getMenuTreeDao().pageQuery("FROM MenuInfo ORDER BY mi_id DESC LIMIT 1", null).get(0);
		request.setAttribute("max_id",miBean.getMi_id());
		return "pc/manage/lookMenuTree";
	}
	@RequestMapping(params = "method=getReduceMenuTree")
	public String getReduceMenuTree(HttpServletRequest request,HttpServletResponse response){
		return "pc/reduceindex";
	}
	//前台ajax获得某一个节点的详细信息
	@RequestMapping(params = "method=getById")
	public void getById(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String mi_id = request.getParameter("mi_id");
		JSONObject obj = new JSONObject();
		obj.put("menuInfoBean", menuTreeService.getMenuTreeDao().getById(MenuInfo.class,Integer.parseInt(mi_id)));
		PrintWriter out = response.getWriter();
		out.print(obj.toString());
		out.flush();
		out.close();
	}
	//增加节点及修改节点
	@RequestMapping(params = "method=saveorupdate")
	public void saveorupdate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String mi_id = request.getParameter("mi_id")==null?"0":request.getParameter("mi_id");
		String mi_name = request.getParameter("mi_name");
		String mi_url = request.getParameter("mi_url");
		String mi_parentid = (request.getParameter("mi_parentid")==null||request.getParameter("mi_parentid").equals(""))?"0":request.getParameter("mi_parentid");
		String mi_order = (request.getParameter("mi_order")==null||request.getParameter("mi_order").equals(""))?"99":request.getParameter("mi_order");
		MenuInfo miBean = (MenuInfo) menuTreeService.getMenuTreeDao().getById(MenuInfo.class,Integer.parseInt(mi_id)) == null ? new MenuInfo() :(MenuInfo) menuTreeService.getMenuTreeDao().getById(MenuInfo.class,Integer.parseInt(mi_id)) ;
			if(miBean.getMi_id()==0){
				miBean.setMi_id(Integer.parseInt(mi_id));
				miBean.setMi_name(mi_name);
				miBean.setMi_order(Integer.parseInt(mi_order));
				miBean.setMi_parentid(Integer.parseInt(mi_parentid));
				miBean.setMi_url(mi_url);
				menuTreeService.save(miBean);
			}else{
				miBean.setMi_name(mi_name);
				miBean.setMi_order(Integer.parseInt(mi_order));
				miBean.setMi_url(mi_url);
				menuTreeService.update(miBean);
			}
		
	}
	//删除节点
	@RequestMapping(params = "method=remove")
	public void remove(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String mi_id = request.getParameter("mi_id")==null?"0":request.getParameter("mi_id");
		menuTreeService.getMenuTreeDao().delete(menuTreeService.getMenuTreeDao().getById(MenuInfo.class,Integer.parseInt(mi_id)));
	}
	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}
	@Resource
	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	public JobInfoService getJobInfoService() {
		return jobInfoService;
	}
	@Resource
	public void setJobInfoService(JobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}
	
	
}
