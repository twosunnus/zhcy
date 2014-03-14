<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.UserInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserInfo uesr = request.getSession().getAttribute("userBean")==null?new UserInfo():(UserInfo)request.getSession().getAttribute("userBean");
%>
<html>
<head>
<title> </title>
<link href="<%=basePath %>css/skin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/GetCNDate.js"></script>
<script type="text/javascript">
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
function logout(){
	parent.window.location.href = "<%=basePath %>loginAction.do?method=logout";
}
</script>
<script type="text/javascript">
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<base target="main">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="74%" height="38" class="admin_txt"><b>&nbsp;&nbsp;&nbsp;欢迎您：<%=uesr.getUi_username() %>&nbsp;&nbsp;&nbsp;&nbsp;</b>  
        <script type="text/javascript">
			document.write(GetCNDate());
		</script>
		</td>
        <td width="22%"><a href="#" target="_self" onClick="logout();"><img src="images/out.gif" alt="°²È«ÍË³ö" width="46" height="20" border="0"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
