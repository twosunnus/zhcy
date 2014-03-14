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

import com.pmis.manage.model.DishesTypeInfo;
import com.pmis.manage.service.DishesTypeInfoService;
import com.pmis.util.PageConfig;
import com.pmis.util.SessionUtil;
@Controller
@RequestMapping("/dishesTypeInfoAction")
public class DishesTypeInfoAction {
	private DishesTypeInfoService dishesTypeInfoService;

	@RequestMapping(params = "method=look")
	public String look(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page").toString());
		int totalpage = PageConfig.getTotalPage(dishesTypeInfoService.getDishesTypeInfoDao().pageQuery("from DishesTypeInfo", null).size());
		request.setAttribute("dishesTypeInfoList",dishesTypeInfoService.pageQuery(page,PageConfig.page_num," where ui_id = "+SessionUtil.getUi_id(request)+""));
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("page", page+"");
		return "pc/manage/lookDishesTypeInfo";
	}
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		DishesTypeInfo dtiBean = new DishesTypeInfo();
		String path=request.getRealPath("/upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload up = new ServletFileUpload(factory);
		List<FileItem> ls = up.parseRequest(request);
		for (FileItem fileItem : ls) {
			if (fileItem.isFormField()) {
				if(fileItem.getFieldName().equals("dti_id")){
					dtiBean.setDti_id(Integer.parseInt(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("dti_name")){
					dtiBean.setDti_name(fileItem.getString("utf-8"));
				}
			}
		}
		dtiBean.setUi_id(SessionUtil.getUi_id(request));
		dishesTypeInfoService.saveOrUpdate(dtiBean);
		return "forward:/dishesTypeInfoAction.do?method=look";
	}
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest request,HttpServletResponse response) {
		String dti_id = (request.getParameter("dti_id")==null||request.getParameter("dti_id").equals(""))?"0":request.getParameter("dti_id");
		request.setAttribute("dishesTypeInfoBean", dishesTypeInfoService.getById(Integer.parseInt(dti_id)));
		return "pc/manage/editDishesTypeInfo";
	}
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		String dti_id = (request.getParameter("dti_id")==null||request.getParameter("dti_id").equals(""))?"0":request.getParameter("dti_id");
		dishesTypeInfoService.delete(dishesTypeInfoService.getById(Integer.parseInt(dti_id)));
		return "forward:/dishesTypeInfoAction.do?method=look";
	}
	
	public DishesTypeInfoService getDishesTypeInfoService() {
		return dishesTypeInfoService;
	}
	@Resource
	public void setDishesTypeInfoService(DishesTypeInfoService dishesTypeInfoService) {
		this.dishesTypeInfoService = dishesTypeInfoService;
	}
	
	
}
