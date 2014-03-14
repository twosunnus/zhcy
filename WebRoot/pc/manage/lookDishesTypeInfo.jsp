<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.DishesTypeInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<DishesTypeInfo> dishesTypeInfoList = (List)request.getAttribute("dishesTypeInfoList");
int nowpage = Integer.parseInt(request.getAttribute("page")==null?"1":request.getAttribute("page").toString());
int totalpage = Integer.parseInt(request.getAttribute("totalpage")==null?"1":request.getAttribute("totalpage").toString());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="<%=basePath%>css/Style_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		function deleteById(dti_id){
			if(window.confirm("是否确定删除")){
				window.location.href = "<%=basePath%>dishesTypeInfoAction.do?method=delete&dti_id="+dti_id;
			}
		}
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=basePath %>pc/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">菜品类别</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="<%=basePath %>pc/images/mail_rightbg.gif"><img src="<%=basePath %>pc/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
	<td valign="middle" background="<%=basePath %>pc/images/mail_leftbg.gif">&nbsp;</td>
	<td valign="top" bgcolor="#F7F8F9">
		<table width="90%"  align="center" >
			<tr>
				<td align="right"><A href="<%=basePath %>pc/manage/addDishesTypeInfo.jsp">新增</A></td>
			</tr>
		</table>
	  <table width="90%" align="center" border="0" cellpadding="0" cellspacing="0" class="table_bordel0">
		<tr>
          <td width="11%" height="35" align="center" style="background-image:url(<%=basePath%>images/table0_top.jpg); background-repeat:repeat-x; border:#CDC9C9 solid 1px;"><span class="grat14a"><strong>序号</strong></span></td>
          <td width="17%" nowrap align="center" style="background-image:url(<%=basePath%>images/table0_top.jpg); background-repeat:repeat-x; border:#CDC9C9 solid 1px;"><span class="grat14a"><strong>菜品类别</strong></span></td>
          <td width="10%" nowrap align="center" style="background-image:url(<%=basePath%>images/table0_top.jpg); background-repeat:repeat-x; border:#CDC9C9 solid 1px;"><span class="grat14a"><strong>操作</strong></span></td>
        </tr>
        <% for(int i = 0 ; i < dishesTypeInfoList.size() ; i++){ %>
		 	<tr class="list_a01">
		 	   <td height="35" align="center" class="table_bordel023" nowrap="true"><span class="grat14a"><%=(i+1) %></span></td>
		 	   <td height="35" align="center" class="table_bordel023" nowrap="true"><span class="grat14a"><%=dishesTypeInfoList.get(i).getDti_name() %>&nbsp;</span></td>
		 	   <td height="35" align="center" class="table_bordel023" nowrap="true"><a class="sunti12_blue" href="<%=basePath %>dishesTypeInfoAction.do?method=edit&dti_id=<%=dishesTypeInfoList.get(i).getDti_id() %>" ><span class="en14">编辑</span></a>&nbsp;<a class="sunti12_blue" href="javascript:deleteById('<%=dishesTypeInfoList.get(i).getDti_id() %>')" ><span class="en14">删除</span></a></td>
             </tr>
        <%} %>
      </table>
	  <table border="0" align="center" style="padding: 10px" width="90%" cellspacing="0" cellpadding="0">
		         <tr>
		           <td nowrap="true">
					<div style="text-align:right; width:100%;font-size: 12px" class="st12_HB">
						<% if(totalpage==0||totalpage==1){ %>
							首页 上一页 下一页 末页
						<%}else if(totalpage==nowpage){ %>
							<a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=1">首页</a> <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=(nowpage-1) %>">上一页</a> 下一页 末页
						<%}else if(nowpage==1){ %>
							首页 上一页 <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=(nowpage+1) %>">下一页</a> <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=totalpage %>">末页</a>
						<%}else{ %>
						<a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=1">首页</a> <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=(nowpage-1) %>">上一页</a> <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=(nowpage+1) %>">下一页</a> <a href="<%=basePath %>dishesTypeInfoAction.do?method=look&page=<%=totalpage %>">末页</a>
						<%} %>
					</div>
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
</body>
