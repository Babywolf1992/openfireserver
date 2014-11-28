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
<div align="center"><font color="#072d54" size="3"><strong>信息预览</strong></font>
</div>
	<hr width="95%" size="1" color="#DCEAF3">
	<form id="infoform" name="infoform" action="<%=request.getContextPath() %>/videolive.do?method=infosave" method="post" onsubmit="return CheckQueryForm();">
	<div id="article_details" class="details">
	<h1>
        <span class="link_title">
        ${releaseInfo.txtTitle}
        </span>
    </h1>
    </div>
    
    <div class="article_manage">
        <span class="link_categories">
                            作者：${releaseInfo.creator}
        </span>
    <span class="link_postdate">时间：${releaseInfo.createtime}</span>
    <span class="link_view" title="是否审核">
        <c:if test="${releaseInfo.validate=='-1'}"> 未审核</c:if>
        <c:if test="${releaseInfo.validate=='1'}"> 审核</c:if>
    </span>
   </div>
    
	<div id="article_content" class="article_content">
	<p>
	  <span style="font-size:18px">
	    ${releaseInfo.editor }
	  </span>
	</p>
	</div>
	
	</form>
</center>

</body>
</html>