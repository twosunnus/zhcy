<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<script type="text/javascript">
	
		function toChangeBar(){
			if(document.getElementById("menuBarImage").src.indexOf("menuBarleft.gif")!=-1){
				parent.document.getElementById("frame").cols = "0,9,*";
				document.getElementById("menuBarImage").src = "<%=basePath %>pc/images/menuBarright.gif";
			}else{
				parent.document.getElementById("frame").cols = "200,9,*";
				document.getElementById("menuBarImage").src = "<%=basePath %>pc/images/menuBarleft.gif";
			}
			
		}
		
		
	</script>
	<style type="text/css">
div#wrap{
	width:6px;
	height:100%;
	position:relative;
}
div#subwrap{
	position:absolute;
	top:45%;
}
div#content{
	position:relative;
	top:-55%;
}
	</style>
  </head>
  
  <body  bgcolor="BFE2FF" >
	<center>
	<div id="wrap">
	<div id="subwrap">
	<div id="content" style="vertical-align: middle;display: table-cell;">
		<img onclick="toChangeBar()"  style="cursor: hand;width: 7px" id="menuBarImage" src="<%=basePath%>pc/images/menuBarleft.gif" >
	</div>
	</div>
	</div>
	</center>
  </body>
</html>
