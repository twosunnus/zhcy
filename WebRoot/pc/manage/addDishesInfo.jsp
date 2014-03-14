<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.pmis.manage.model.DishesTypeInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<DishesTypeInfo> dishesTypeInfoList = (List)request.getAttribute("dishesTypeInfoList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.js" ></script>
<script type="text/javascript" src="<%=basePath %>js/popup_layer.js" ></script>
<link href="<%=basePath%>css/core.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/Style_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function() {
	new PopupLayer({trigger:"#dti_name",popupBlk:"#blk3",closeBtn:"#close3",useFx:true});
	});
	var id_array = new Array();
	var name_array = new Array();
	function addDishesType(type_id,type_name){
		for(var i = 0 ; i < id_array.length; i++){
			if(id_array[i]==type_id)return;
		}
		id_array.push(type_id);
		name_array.push(type_name);
		updateDishesType();
	}
	function updateDishesType(){
	var dti_id="",dti_name="";
		for(var i = 0 ; i < id_array.length ; i++){
			if(dti_id==""){
				dti_id = id_array[i];
				dti_name = name_array[i];
			}else{
				dti_id += ","+id_array[i];
				dti_name += ","+name_array[i];
			}
		}
		document.getElementById("dti_id").value = dti_id;
		document.getElementById("dti_name").value = dti_name;
	}
	function resetDishesType(){
		document.getElementById("dti_id").value = "";
		document.getElementById("dti_name").value = "";
		id_array = new Array();
		name_array = new Array();
	}
</script>
</head>
<body>
 
<form name="example" method="post" action="<%=basePath %>dishesInfoAction.do?method=add"  enctype="multipart/form-data"> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=basePath %>pc/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">添加菜品</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="<%=basePath %>pc/images/mail_rightbg.gif"><img src="<%=basePath %>pc/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
	<td valign="middle" background="<%=basePath %>pc/images/mail_leftbg.gif">&nbsp;</td>
	<td valign="top" bgcolor="#F7F8F9">
		
	  <table width="40%" align="center" border="0" cellpadding="0" cellspacing="0" class="table_bordel0">
		<tr align="center">
			<td height="30px">
				菜品类别：
			</td>
			<td>
				<input type="hidden" value="" name="dti_id" id="dti_id" >
				<input type="text" readonly="readonly" onclick="resetDishesType()" id="dti_name" class="tigger">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				菜品名称：
			</td>
			<td>
				<input type="text" name="di_name">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				菜品数量：
			</td>
			<td>
				<input type="text" name="di_num">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				菜品状态：
			</td>
			<td>
				<input type="text" name="di_flag">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				原价格：
			</td>
			<td>
				<input type="text" name="di_lastprice">
			</td>
		</tr>
		<tr align="center">
			<td height="30px">
				现价格：
			</td>
			<td>
				<input type="text" name="di_nowprice">
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

<div id="blk3" class="blk" style="display:none;">
            <div class="head"><div class="head-right"></div></div>
            <div class="main">
                <a href="javascript:void(0)" id="close3" class="closeBtn">关闭</a>
                <ul>
                <% for(DishesTypeInfo dtibean : dishesTypeInfoList){ %>
                    <li><a onclick="addDishesType('<%=dtibean.getDti_id() %>','<%=dtibean.getDti_name() %>')" href="#"><%=dtibean.getDti_name() %></a></li>
                <% } %>
                </ul>
            </div>
            <div class="foot"><div class="foot-right"></div></div>
        </div>
</body>
