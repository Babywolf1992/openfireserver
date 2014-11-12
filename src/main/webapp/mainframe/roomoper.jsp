<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.page import="com.videolive.external.room.*,com.videolive.model.webrepdata.*"/>    
<%
	String strXMLData = (String)request.getAttribute("strXMLData");

	out.println(strXMLData);	
%>
