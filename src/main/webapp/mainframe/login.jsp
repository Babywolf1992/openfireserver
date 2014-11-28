<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<%
    //设置上下文路径
			String ctxPath = request.getContextPath();
			request.setAttribute("ctxPath", ctxPath);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>登陆界面</title>  
<script type="text/javascript" src="${ctxPath}/jquery/jquery-1.8.0.js"></script>
<script>
function clearblank(){
	$("#username").attr("value","");
	$("#plainPassword").attr("value","");
	
}

</script>
<link rel="stylesheet" href="${ctxPath}/css/Login.css" type="text/css">
  
</head>  

<body>  


<form method="post" action="<%=request.getContextPath() %>/videolive.do?method=login">  
<div align="center"> 
   <div id="jive-loginBox">
     <div id="jive-loginTable" align="center">
           <span id="jive-login-header" style="background: transparent url(images/videolivelogo.png) no-repeat left; padding: 29px 0 10px 110px;">
            <label>视频直播后台管理</label>
            </span>
           <div style="text-align: center; width: 380px;">
			<table cellpadding="0" cellspacing="0" border="0" align="center">  
			  
                <tr>
                 <td align="right" class="loginFormTable">
                 <table cellpadding="2" cellspacing="0" border="0">
                 
                        <noscript>
                            <tr>
                                <td colspan="3">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                    <tr valign="top">
                                        <td><img src="images/error-16x16.gif" width="16" height="16" border="0" alt="" vspace="2"></td>
                                        <td><div class="jive-error-text" style="padding-left:5px; color:#cc0000;"><fmt:message key="login.error" /></div></td>
                                    </tr>
                                    </table>
                                </td>
                            </tr>
                        </noscript>
                        
                      <%  if (false) { %>
                            <tr>
                                <td colspan="3">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <% for (int i=0;i<1;i++) { %>
                                    <tr valign="top">
                                        <td><img src="images/error-16x16.gif" width="16" height="16" border="0" alt="" vspace="2"></td>
                                        <td><div class="jive-error-text" style="padding-left:5px; color:#cc0000;"><%= 1%></div></td>
                                    </tr>
                                        <% } %>
                                    </table>
                                </td>
                            </tr>
                        <%  } %>
                 
				    <tr><td width="25%" >用户名</td><td width="60%"><input id="username" name="username" size="30" maxlength="20"  style="width:170px"  type="text" /></td></tr>  
				    <tr><td width="25%">密码</td><td width="60%"><input id="plainPassword" name="plainPassword" size="30" maxlength="20" style="width:170px"  type="password"/></td></tr>  
				    <tr><td colSpan="2" align="center">&nbsp;&nbsp;&nbsp;<input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="clearblank()" value="清空" /> </td></tr>  	   
			    </table>
			    </td>
			  </tr>
			</table>  
          </div> 
       </div>
   </div>
 </div>

</form>  


</body>  
</html>  