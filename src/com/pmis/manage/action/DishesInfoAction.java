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

import com.pmis.manage.model.DishesInfo;
import com.pmis.manage.service.DishesInfoService;
import com.pmis.manage.service.DishesTypeInfoService;
import com.pmis.manage.service.DishesTypeRelationService;
import com.pmis.util.PageConfig;
import com.pmis.util.SessionUtil;
@Controller
@RequestMapping("/dishesInfoAction")
public class DishesInfoAction {
	private DishesInfoService dishesInfoService;
	private DishesTypeInfoService dishesTypeInfoService;
	private DishesTypeRelationService dishesTypeRelationService;
	
	@RequestMapping(params = "method=look")
	public String look(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page").toString());
		int totalpage = PageConfig.getTotalPage(dishesInfoService.pageQuery(SessionUtil.getUi_id(request)).size());
		request.setAttribute("dishesInfoList",dishesInfoService.pageQuery(SessionUtil.getUi_id(request),page,PageConfig.page_num));
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("page", page+"");
		return "pc/manage/lookDishesInfo";
	}
	
	@RequestMapping(params = "method=toAdd")
	public String toAdd(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("dishesTypeInfoList",dishesTypeInfoService.pageQuery(" where ui_id = "+SessionUtil.getUi_id(request)+""));
		return "pc/manage/addDishesInfo";
	}
	
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		String dti_id = "";
		DishesInfo diBean = new DishesInfo();
		String path=request.getRealPath("/upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload up = new ServletFileUpload(factory);
		List<FileItem> ls = up.parseRequest(request);
		for (FileItem fileItem : ls) {
			if (fileItem.isFormField()) {
				if(fileItem.getFieldName().equals("di_id")){
					diBean.setDi_id(Integer.parseInt(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("di_name")){
					diBean.setDi_name(fileItem.getString("utf-8"));
				}else if(fileItem.getFieldName().equals("di_num")){
					diBean.setDi_num(Integer.parseInt(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("di_flag")){
					diBean.setDi_flag(Integer.parseInt(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("di_lastprice")){
					diBean.setDi_lastprice(Double.parseDouble(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("di_nowprice")){
					diBean.setDi_nowprice(Double.parseDouble(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("dti_id")){
					dti_id = fileItem.getString("utf-8");
				}
			}
		}
		diBean.setUi_id(SessionUtil.getUi_id(request));
		dishesInfoService.saveOrUpdate(diBean);
		dishesTypeRelationService.saveOrUpdate(diBean,dti_id);
		return "forward:/dishesInfoAction.do?method=look";
	}
	public DishesInfoService getDishesInfoService() {
		return dishesInfoService;
	}
	@Resource
	public void setDishesInfoService(DishesInfoService dishesInfoService) {
		this.dishesInfoService = dishesInfoService;
	}
	public DishesTypeInfoService getDishesTypeInfoService() {
		return dishesTypeInfoService;
	}
	@Resource
	public void setDishesTypeInfoService(DishesTypeInfoService dishesTypeInfoService) {
		this.dishesTypeInfoService = dishesTypeInfoService;
	}

	public DishesTypeRelationService getDishesTypeRelationService() {
		return dishesTypeRelationService;
	}
	@Resource
	public void setDishesTypeRelationService(
			DishesTypeRelationService dishesTypeRelationService) {
		this.dishesTypeRelationService = dishesTypeRelationService;
	}
	
}
