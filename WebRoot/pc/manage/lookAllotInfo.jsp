<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.JobInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<JobInfo> jobInfoList = (List)request.getAttribute("jobInfoList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<link href="<%=basePath%>css/Style_css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath %>css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>css/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
		function saveChecked(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var treeNodes = zTree.getCheckedNodes(true);
			var mi_id = "";
			for(var i = 0 ; i < treeNodes.length; i ++){
				mi_id += mi_id==""?treeNodes[i].id:","+treeNodes[i].id;
			}
			var ji_id = document.getElementById("ji_id").value;
			$.ajax({
				type:"post",
				url:"allotInfoAction.do?method=saveCheckMenu",
				data:"mi_id="+mi_id+"&ji_id="+ji_id,
				dataType:"json",
				success:function(msg){
					alert("保存成功");
				},
				error:function(){
				}
			});
		}
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<input type="hidden" id="ji_id"  />
  <tr>
    <td width="17" valign="top" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=basePath %>pc/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">职位菜单配置</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="<%=basePath %>pc/images/mail_rightbg.gif"><img src="<%=basePath %>pc/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
	<td valign="middle" background="<%=basePath %>pc/images/mail_leftbg.gif">&nbsp;</td>
	<td valign="top" bgcolor="#F7F8F9">
		
	  <table width="90%" align="center" border="0" cellpadding="0" cellspacing="0" class="table_bordel0">
		<tr>
          <td width="35%" align="center" >
			<select size="20" onclick="showMenu(this)" style="width: 180px">
				<% for(JobInfo jiBean : jobInfoList){ %>
					<option value="<%=jiBean.getJi_id() %>"><%=jiBean.getJi_name() %></option>
				<%} %>
			</select>
		  </td>
          <td width="60%" align="left" valign="top" style="padding: 20px">
				<ul id="treeDemo" class="ztree" style="height: 280px"></ul>
				<input type="button" value="保存" onclick="saveChecked()" />	 
</td>
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

<SCRIPT type="text/javascript">
		var setting = {	
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			
			}};

		var zNodes ="";
		var zTree, rMenu;
		function showMenu(obj){
			$.ajax({
				type:"post",
				url:"allotInfoAction.do?method=showMenu",
				data:"ji_id="+obj.value,
				dataType:"json",
				success:function(msg){
					document.getElementById("ji_id").value = obj.value;
					$.fn.zTree.init($("#treeDemo"), setting, msg.checkedMenuList);
				},
				error:function(){
				}
			});
			
		}
	</SCRIPT>
</body>
