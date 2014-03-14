<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.MenuInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<MenuInfo> menuTreeList = (List)request.getAttribute("menuTreeList");
%>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 20px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
.ttt {
	background-color: #F8F9FA;
}
</style>
<link href="<%=basePath %>css/skin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.excheck-3.5.js"></script>
<link rel="stylesheet" href="<%=basePath %>css/zTreeStyle.css" type="text/css"></link>

<script type="text/javascript">
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	<% String str = ""; %> 
	<% for(int i = 0 ; i < menuTreeList.size(); i++){ %>
		<%
			MenuInfo menuBean = menuTreeList.get(i);
			if(str.equals("")){
				str = "{id:"+menuBean.getMi_id()+",pId:"+menuBean.getMi_parentid()+",name:'"+menuBean.getMi_name()+"',url:'"+menuBean.getMi_url()+"',target:'main'}";
			}else{
				str += ",{id:"+menuBean.getMi_id()+",pId:"+menuBean.getMi_parentid()+",name:'"+menuBean.getMi_name()+"',url:'"+menuBean.getMi_url()+"',target:'main'}";
			}
		 %>
	<%}%>
	<% 
	if(!str.equals("")){
		str = "[" + str + "];";
	}
	%>
	var zNodes =<%=str %>;
	$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>

<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr width="100%">
  <td width="99%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" background="">
    <table width="100%" height="26" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="26">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
<script type="text/javascript" >
	document.write("<tr  class=ttt height="+(document.body.clientHeight - 43)+" valign=top>")
</script>
	
		<td >
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<div id="treeDemo" class="ztree"></div>
	</div>
</div>
</td>
	</tr>
  <tr >
    <td background="<%=basePath %>pc/images/buttom_bgs.gif"><img src="<%=basePath %>pc/images/buttom_bgs.gif" width="196" height="17"></td>
  </tr>
  
</table>
</td>
<td width="1%">
</td>
</tr>
</table>
</body>
<script type="text/javascript">
</script>