<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page import="com.videolive.model.User"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="../css/admin.css" type="text/css" rel="stylesheet">
<SCRIPT language=javascript>
	function expand(el) {
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none') {
			childObj.style.display = 'block';
		} else {
			childObj.style.display = 'none';
		}
		return;
	}
</SCRIPT>
</HEAD>
<BODY>
<%
final int ADMIN_PRIORITY = 1;
boolean falg = false;
User strName_session = null;
strName_session = (User)session.getAttribute("user");
if(true){
	falg = true;
}
%>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width=170 
background=../images/menu_bg.jpg border=0>
  <TR>
    <TD vAlign=top align=middle>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        
        <TR>
          <TD height=10></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        
        <TR height=22>
			<TD style="PADDING-LEFT: 30px" background=../images/menu_bt.jpg>
			<A class=menuParent onclick=expand(1) href="javascript:void(0);">数据管理</A></TD></TR>
        <TR height=4>
          <TD></TD></TR></TABLE>
      				<TABLE id=child1 style="DISPLAY:" cellSpacing=0 cellPadding=0
					width=150 border=0>
					<%if(true == falg){ %>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="./addData.jsp" target=main>增加数据</A></TD>
					</TR>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=delPage" target=main>删除数据
					</TR>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=reltime" target=main>实时状态</A></TD>
					</TR>
					<%} %>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=searchPage" target=main>查询数据</A></TD>
					</TR>	
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=allIoData" target=main>全部数据</A></TD>
					</TR>	
					<TR height=4>
						<TD colSpan=2></TD>
					</TR>
				</TABLE>
       <TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
        
        <TR height=22>
			<TD style="PADDING-LEFT: 30px" background=../images/menu_bt.jpg>
			<A class=menuParent onclick=expand(2) href="javascript:void(0);">视频管理</A></TD></TR>
        <TR height=4>
          <TD></TD></TR></TABLE>
      				<TABLE id=child2 style="DISPLAY:" cellSpacing=0 cellPadding=0
					width=150 border=0>
					<%if(true == falg){ %>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=videossearch" target=main>视频删除</A></TD>
					</TR>
					<%} %>
					<TR height=20>
						<TD align="center" width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="./VideoManFrame.jsp" target=main>视频查看</A></TD>
					</TR>
					<TR height=4>
						<TD colSpan=2></TD>
					</TR>
				</TABLE>    
		<TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
	        <TR height=22>
				<TD style="PADDING-LEFT: 30px" background=../images/menu_bt.jpg>
				<A class=menuParent onclick=expand(3) href="javascript:void(0);">日志管理</A></TD></TR>
	        <TR height=4>
	          <TD></TD></TR>
        </TABLE> 
        <TABLE id=child3 style="DISPLAY:" cellSpacing=0 cellPadding=0
					width=150 border=0>
					<%if(true == falg){ %>
					<TR height=20>
						<TD align=middle width=30><IMG height=9
							src="../images/menu_icon.gif" width=9></TD>
						<TD><A class=menuChild href="<%=request.getContextPath() %>/emp.do?method=logmanage" target=main>日志管理</A></TD>
					</TR>
					<%} %>
					<TR height=4>
						<TD colSpan=2></TD>
					</TR>
		</TABLE>  
     </TD>
    <TD width=1 bgColor=#d1e6f7>
    </TD>
    </TR>
    </TABLE>
    </BODY>
</HTML>
