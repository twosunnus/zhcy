<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.js"></script>
    <link href="<%=basePath%>css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>css/skin.css" rel="stylesheet" type="text/css">
    <title></title>
    
	<script type="text/javascript">
		function toSubmit(){
			var select_type = document.getElementsByName("select_type");
			for(var i = 0 ; i < select_type.length ; i++){
				if(select_type[i].checked){
					select_type = select_type[i].value;
					break;
				}
			}
			select_type = 1;
			if(select_type == 1){
				form1.submit();
			}else{
				var ui_username = $("#ui_username").val();
				var ui_password = $("#ui_password").val();
				window.open("<%=basePath %>loginAction.do?method=login&ui_username="+ui_username+"&ui_password="+ui_password+"&select_type="+select_type,"newwindow","height="+screen.height*0.8+",width="+screen.width*0.2+",top="+screen.height*0.1+",left="+screen.width*0.76+",toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no");    
				window.opener=null;
 				window.open('','_self','');  
    			window.close();  
			}
		}
	</script>

  </head>
  
  <body>
	<form name="form1" action="<%=basePath %>loginAction.do?method=login" method="post">
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top">LOGO</td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>
                     <!-- 1-->
                      </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>
                     <!-- 2-->
                      </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>
                     <!-- 3-->
                      </p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40"><img src="images/icon-demo.gif" width="16" height="16"><a href="#" target="_blank" class="left_txt3"> 
                      <!--  使用说明-->
                      </a> </td>
                      <td width="35%"><img src="images/icon-login-seaver.gif" width="16" height="16"><a href="#" class="left_txt3"> 
                     <!-- 在线客服-->
                      </a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt"><!--  pMis管理系统登录--></span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle"><form name="myform" action="index.html" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                          	<td></td>
                            <td height="38" width="80%" colspan="2"><input id="ui_username" name="ui_username"  class="form-control " placeholder="用户名" required autofocus>                            </td>
                          	<td></td>
                          </tr>
                          <tr>
                          	<td></td>
                            <td height="38" width="80%" colspan="2"><input id="ui_password" class="form-control" type="password" placeholder="密码" required name="ui_password">
                            <td></td>
                          </td>
                          </tr>
                          <!--<tr>
							<td>&nbsp;</td>
							<td colspan="2">
                            <input type="radio" name="select_type" value="1" checked="checked"><span class="login_txt">完整端
							 <input type="radio" name="select_type" value="2"><span class="login_txt">微端 
							</td>
                          </tr>
                          -->
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="40%" height="35" ><button  class="btn btn-lg btn-primary btn-block" name="Submit"  onclick="toSubmit()" class="button" id="Submit" >登 录</button>
                            </td>
                            <td width="40%">
                            <button  class="btn btn-lg btn-primary btn-block"   onclick="form1.reset()" >重 置</button>
                            </td>
                            <td width="67%" ></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2013-2019 </span></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
