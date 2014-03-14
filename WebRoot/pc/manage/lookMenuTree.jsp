<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String jsonTreeStr = request.getAttribute("jsonTreeStr")==null?"":request.getAttribute("jsonTreeStr").toString();
String max_id = request.getAttribute("max_id")==null?"0":request.getAttribute("max_id").toString();
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
	<script type="text/javascript" src="<%=basePath %>js/jquery.ztree.exedit-3.5.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			view: {
				dblClickExpand: false
			},
			check: {
				enable: true
			},
			callback: {
				onRightClick: OnRightClick,
				onClick: onClick
			}
		};

		var zNodes = <%=jsonTreeStr %>;

		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			showDetail(treeNode);
			zTree.updateNode(treeNode);
		}
		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = <%=max_id %>;
		addCount = addCount + 1;
		function addTreeNode() {
			hideRMenu();
			if (zTree.getSelectedNodes()[0]) {
				var parid = zTree.getSelectedNodes()[0].id;
				var newNode = { id:addCount,name:"增加" + (addCount++)};
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
				save(addCount,'','','',parid);
			} else {
				var newNode = {id:addCount, name:"增加" + (addCount++)};
				zTree.addNodes(null, newNode);
				save(addCount,'','','',0);
			}
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
					if (confirm(msg)==true){
						zTree.removeNode(nodes[0]);
					}
				} else {
					zTree.removeNode(nodes[0]);
					remove(nodes[0].id);
				}
			}
		}
		function checkTreeNode(checked) {
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				zTree.checkNode(nodes[0], checked, true);
			}
			hideRMenu();
		}
		function resetTree() {
			hideRMenu();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}

		var zTree, rMenu;
			$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		//-->
		function showDetail(treeNode){
			document.getElementById("menuData").style.display = "block";
			document.getElementById("mi_name").value = treeNode.name;
			$.ajax({
				type:"post",
				url:"menuTreeAction.do?method=getById",
				data:"mi_id="+treeNode.id,
				dataType:'json',
				success:function(msg){
					document.getElementById("mi_url").value = "";
					document.getElementById("mi_order").value = "";
					document.getElementById("mi_url").value = msg.menuInfoBean.mi_url;
					document.getElementById("mi_order").value = msg.menuInfoBean.mi_order;
				},
				error:function(){
				}
			});
		}
		function getSaveData(){
			var nodes = zTree.getSelectedNodes();
			var mi_id = nodes[0].id;
			var mi_name = document.getElementById("mi_name").value;
			var mi_url = document.getElementById("mi_url").value;
			var mi_order = document.getElementById("mi_order").value;
			save(mi_id,mi_name,mi_url,mi_order,'');
			nodes[0].name = mi_name;
			zTree.updateNode(nodes[0]);
		}
		function save(mi_id,mi_name,mi_url,mi_order,mi_parentid){
			$.ajax({
				type:"post",
				url:"menuTreeAction.do?method=saveorupdate",
				data:"mi_id="+mi_id+"&mi_url="+mi_url+"&mi_name="+mi_name+"&mi_order="+mi_order+"&mi_parentid="+mi_parentid,
				dataType:"json",
				success:function(msg){
				},
				error:function(){
				}
			});
		}
		function remove(mi_id){
			$.ajax({
				type:"post",
				url:"menuTreeAction.do?method=remove",
				data:"mi_id="+mi_id,
				dataType:"json",
				success:function(msg){
				},
				error:function(){
				}
			});
		}
	</SCRIPT>
	<style type="text/css">
		div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
		div#rMenu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: #DFDFDF;
		}
	</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="<%=basePath %>pc/images/mail_leftbg.gif"><img src="<%=basePath %>pc/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="<%=basePath %>pc/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">菜单配置</div></td>
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
			<ul id="treeDemo" class="ztree"></ul>
		  </td>
          <td width="60%" align="center" valign="top" style="padding: 20px">
			<table id="menuData" style="display: none">
					<tr>
						<td>
							菜单名称：
						</td>
						<td>
							<input type="text" name="mi_name" id="mi_name">
						</td>
					</tr>
					<tr>
						<td>
							菜单URL：
						</td>
						<td>
							<input type="text" name="mi_url" id="mi_url">
						</td>
					</tr>	
					<tr>
						<td>
							菜单排序：
						</td>
						<td>
							<input type="text" name="mi_order" id="mi_order">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="保存" onclick="getSaveData()" >
						</td>
					</tr>
				</table>
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
<div id="rMenu" style="position: absolute;z-index: 99">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
	</ul>
</div>
</body>
