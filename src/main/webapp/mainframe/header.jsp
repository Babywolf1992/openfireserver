<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page import="com.videolive.model.User"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="../css/admin.css" type="text/css" rel="stylesheet">
</HEAD>
<BODY>
	<%
	User strName_session = null;
			strName_session = (User)session.getAttribute("user");
	%>
	<TABLE cellSpacing=0 cellPadding=0 width="100%"
		background="../images/header_bg.jpg" border=0>
		<TR height=56>
			<!-- <TD width=260><IMG height=56 src="../images/header_left.jpg"
				width=260></TD> -->
			<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px" width=280><font size="5px">VideoLive后台管理系统</font> &nbsp;&nbsp; 
			</TD>	
			<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
				align=middle>当前用户：<%=strName_session.getUsername() %> &nbsp;&nbsp; <a style="COLOR: #fff" 
				onclick="window.parent.location.href='login.jsp'" href="login.jsp">更改登录</a> &nbsp;&nbsp; 
				<A style="COLOR: #fff" onclick="if (confirm('确定要退出吗？')) return true; else return false;"
				href="login.jsp" target=_top>退出系统</A>
			</TD>
			<!-- <TD align=right width=268><IMG height=56
				src="../images/header_right.jpg" width=268></TD>
		</TR> -->
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TR bgColor=#1c5db6 height=4>
			<TD></TD>
		</TR>
	</TABLE>

</BODY>
</HTML>