<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title></title>
</head>
<script type="text/javascript">
</script>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="<%=basePath %>pc/top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
    <frameset cols="200,9,*"  rows="560,*" id="frame">
	<frame src="<%=basePath %>menuTreeAction.do?method=getMenuTree" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="<%=basePath %>pc/barMenu.jsp" name="barFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="<%=basePath %>pc/right.jsp" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
<noframes>
  <body></body>
    </noframes>
</html>
