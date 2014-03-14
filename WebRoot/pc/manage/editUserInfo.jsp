<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.JobInfo"%>
<%@ page language="java" import="com.pmis.manage.model.UserInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<JobInfo> jobInfoList = (List)request.getAttribute("jobInfoList");
UserInfo userInfoBean = (UserInfo)request.getAttribute("userInfoBean");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="<%=basePath%>css/Style_css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="example" method="post" action="<%=basePath %>userInfoAction.do?method=add"  enctype="multipart/form-data"> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=basePath %>pc/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">用户修改</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="<%=basePath %>pc/images/mail_rightbg.gif"><img src="<%=basePath %>pc/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
	<td valign="middle" background="<%=basePath %>pc/images/mail_leftbg.gif">&nbsp;</td>
	<td valign="top" bgcolor="#F7F8F9">
		<input type="hidden" name="ui_id" value="<%=userInfoBean.getUi_id() %>">
	  <table width="40%" align="center" border="0" cellpadding="0" cellspacing="0" class="table_bordel0">
		<tr align="center">
			<td height="30px">
				用户名：
			</td>
			<td>
				<input type="text" name="ui_username" value="<%=userInfoBean.getUi_username() %>">
			</td>
		</tr>
        <tr align="center">
			<td height="30px">
				密码：
			</td>
			<td>
				<input type="text" name="ui_password">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				职位：
			</td>
			<td>
				<select name="ui_ji_id">
					<% for(JobInfo jiBean : jobInfoList){ %>
					  <% if(userInfoBean.getUi_ji_id().equals(jiBean.getJi_id()+"")){ %>
						<option value="<%=jiBean.getJi_id() %>" selected="selected"><%=jiBean.getJi_name() %></option>
					  <%}else{ %>
						<option value="<%=jiBean.getJi_id() %>" ><%=jiBean.getJi_name() %></option>
					  <%} %>
					<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="提交">
			</td>
		</tr>
      </table>
	 
	</td>
	<td background="<%=basePath %>pc/images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="<%=basePath %>pc/images/buttom_bgs.gif"><img src="<%=basePath %>pc/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="<%=basePath %>pc/images/mail_rightbg.gif"><img src="<%=basePath %>pc/images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</form>
</body>
