<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <jsp:directive.page import="com.videolive.model.*"/>
<%
    //设置上下文路径
			String ctxPath = request.getContextPath();
			request.setAttribute("ctxPath", ctxPath);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息发布页面</title>
<script type="text/javascript" src="${ctxPath}/mainframe/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxPath}/jquery/jquery-1.8.0.js"></script>
<style >
  body {font-size:10pt}
  table{  border-collapse:collapse;  }   
  td{  border:1px solid #000000;  }   

</style>
</head>
<body class=main>
<center>
<div align="center"><font color="#072d54" size="3"><strong>发布信息</strong></font>
</div>
	<hr width="95%" size="1" color="#DCEAF3">
	<form id="infoform" name="infoform" action="<%=request.getContextPath() %>/videolive.do?method=infosave" method="post" onsubmit="return CheckQueryForm();">
	<div align="left">
	<label >文章标题  </label><input type="text" id="txtTitle" name="txtTitle" style="width:550px; height:20px; " maxlength="100" /> <br/>
	</div>
	<br/>
	<label style="height:20px; float:left;">文章内容  </label>
	<div class="section" align="left">
      <textarea id="editor" name="editor" rows="15" style="width:95%;"></textarea>
    </div>
    <br/>
    <div align="left">
    <input type="text" id="filename" name="filename" style="width:550px; height:20px; " maxlength="100" />  
    <input type="button" value="选择文件" class="BigButton" title="选择文件"   /> 
    </div>
     <%
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      java.util.Date currentTime = new java.util.Date();//得到当前系统时间
      User user = (User)session.getAttribute("user");
      if(user!=null){//判断是否登录过时
     %>
     <input type="hidden" id="createtime" name="createtime" value="<%=sdf.format(currentTime) %>">
     <input type="hidden" id="creator" name="creator" value="<%=user.getUsername() %>">
   <% 	  
      }else{
    %>
    <script>window.parent.location.href="mainframe/login.jsp"; </script>
    <%
      }
    %>
   
    <br/>
	<input type="submit" value="保存" class="BigButton" title="保存" >
	</form>
</center>

</body>
</html>