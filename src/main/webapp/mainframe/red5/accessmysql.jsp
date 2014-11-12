<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="java.util.*, java.sql.*, java.text.*,com.mysql.jdbc.*"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test Mysql</title>
</head>
<body>
<%
	// 测试mysql数据库 
	java.sql.Connection  conn = null;
	try {
		// 驱动的名称   
		Class.forName("com.mysql.jdbc.Driver");
		String user = "openfire";
		String passwd = "openfire";
		String strDBname = "openfire";
		
		/*
		conn = DriverManager.getConnection("jdbc:mysql://192.168.0.5/"
				+ strDBname + "?user=" + user + "&password=" + passwd
				+ "");
		*/
		conn = DriverManager.getConnection("jdbc:mysql://192.168.0.5:3306/openfire?user=root1&password=11");
		// 访问的数据库的帐号密码  
		out.println("连接数据库成功！");
        java.sql.Statement sql=conn.createStatement();
        java.sql.ResultSet rs=sql.executeQuery("select * from openfire.ofgroup");        
        while(rs.next())
        {
        	String  strGName = rs.getString(1);
        	out.println(strGName);
        }
		
	} 
	catch (Exception e) {
		System.out.println("OpenConnection:" + e.getMessage());
	}
	
	if (null != conn)
	{
		System.out.println("链接数据库成功！");
	}
%>
</body>
</html>