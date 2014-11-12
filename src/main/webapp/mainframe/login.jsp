<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>登陆界面</title>  
  
</head>  
<style type="text/css">
   #d1{
   background-color: #0066CC;
   position:fixed; 
   left:400px;
   top:200px;   
   width: 30%;
   height: 25%
   }
   </style>
<body>  


<form method="post" action="<%=request.getContextPath() %>/videolive.do?method=login">  
<div><c:out value="${addstate}"></c:out></div>  
<div id="d1" align="center">
<table >  
    <tr><td width="20%">用户名</td><td width="60%"><input id="username" name="username" type="text" /></td></tr>  
    <tr><td>密码</td><td><input id="plainPassword" name="plainPassword"  type="password"/></td></tr>  
    <tr><td colSpan="2" align="center"><input type="submit" value="提交"/><input type="button" onclick="turnback()" value="返回" /> </td></tr>  
</table>  
 </div>
</form>  


</body>  
</html>  