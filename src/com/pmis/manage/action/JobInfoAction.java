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

import com.pmis.manage.model.JobInfo;
import com.pmis.manage.service.JobInfoService;
import com.pmis.util.PageConfig;

@Controller
@RequestMapping("/jobInfoAction")
public class JobInfoAction {
	
	private JobInfoService jobInfoService;
	
	@RequestMapping(params = "method=look")
	public String look(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page").toString());
		int totalpage = PageConfig.getTotalPage(jobInfoService.getJobInfoDao().pageQuery("from JobInfo", null).size());
		request.setAttribute("jobInfoList",jobInfoService.pageQuery(page,PageConfig.page_num,""));
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("page", page+"");
		return "pc/manage/lookJobInfo";
	}
	@RequestMapping(params = "method=add")
	public String add(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception {
		JobInfo jiBean = new JobInfo();
		String path=request.getRealPath("/upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload up = new ServletFileUpload(factory);
		List<FileItem> ls = up.parseRequest(request);
		for (FileItem fileItem : ls) {
			if (fileItem.isFormField()) {
				if(fileItem.getFieldName().equals("ji_id")){
					jiBean.setJi_id(Integer.parseInt(fileItem.getString("utf-8")));
				}else if(fileItem.getFieldName().equals("ji_name")){
					jiBean.setJi_name(fileItem.getString("utf-8"));
				}
			}
		}
		jobInfoService.saveOrUpdate(jiBean);
		return "forward:/jobInfoAction.do?method=look";
	}
	@RequestMapping(params = "method=edit")
	public String edit(HttpServletRequest request,HttpServletResponse response) {
		String ji_id = (request.getParameter("ji_id")==null||request.getParameter("ji_id").equals(""))?"0":request.getParameter("ji_id");
		request.setAttribute("jobInfoBean", jobInfoService.getById(Integer.parseInt(ji_id)));
		return "pc/manage/editJobInfo";
	}
	@RequestMapping(params = "method=delete")
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		String ji_id = (request.getParameter("ji_id")==null||request.getParameter("ji_id").equals(""))?"0":request.getParameter("ji_id");
		jobInfoService.delete(jobInfoService.getById(Integer.parseInt(ji_id)));
		return "forward:/jobInfoAction.do?method=look";
	}

	public JobInfoService getJobInfoService() {
		return jobInfoService;
	}
	@Resource
	public void setJobInfoService(JobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}
	
}
