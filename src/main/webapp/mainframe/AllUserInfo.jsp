<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //设置上下文路径
			String ctxPath = request.getContextPath();
			request.setAttribute("ctxPath", ctxPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery/jquery-1.8.0.js"></script>
<title>所有用户信息</title>
</head>
<style >
  body {font-size:10pt}
  table{  border-collapse:collapse;  }   
  td{  border:1px solid #000000;  }   

</style>
<body>
<center>
<div align="center"><font color="#072d54" size="3"><strong>所有用户信息</strong></font>
</div>
	<hr width="95%" size="1" color="#DCEAF3">
	
<form id="queryform" name="queryform" method="post" >
<div class="pageContainer">
<table width="95%" border="0"  cellspacing="0" cellpadding="0"  align="center">  
    <tr>  
        <td bgcolor="#B9D5E8" align="center" width="18%">用户名</td>  
        <td bgcolor="#B9D5E8" align="center" width="18%">明文密码</td>  
        <td bgcolor="#B9D5E8" align="center" width="18%">加密密码</td>  
        <td bgcolor="#B9D5E8" align="center" width="18%">名字</td>
        <td bgcolor="#B9D5E8" align="center" width="18%">email</td>
        <td bgcolor="#B9D5E8" align="center" width="18%">创建日期</td>
        <td bgcolor="#B9D5E8" align="center" width="18%">最近修改日期</td>
    </tr>  

    <c:forEach items="${userlist}" var="user">  
    <tr id="<c:out value="${user.username}"/>">  
    <td align="center"><c:out value="${user.username}"/></td>
        <td align="center"><c:out value="${user.plainPassword}"/></td>  
        <td align="center"><c:out value="${user.encryptedPassword}"/></td>  
        <td align="center"><c:out value="${user.name}"/></td>  
        <td align="center"><c:out value="${user.email}"/></td>
        <td align="center"><c:out value="${user.creationDate}"/></td>
        <td align="center"><c:out value="${user.modificationDate}"/></td>
    </tr>  
    </c:forEach>  
       
</table>  

	
</div>
</form>
</center>
</body>
</html>