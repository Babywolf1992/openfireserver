<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="com.videolive.external.room.*"/>    

<%
	//  创建直播厅
	
	// 访问参数
	String	strRoomName = request.getParameter("roomname"); 	// 房间名称
	String	strRoomTitle = request.getParameter("roomtitle"); 	// 房间标题
	String	strUserName = request.getParameter("username"); 	// 用户名称
	String	strRoomDesc = request.getParameter("roomdesc"); 	// 房间描述
	String  strRoomType = request.getParameter("roomtype"); 	// 房间类型
	
	String  strOperType = request.getParameter("opertype");		// 操作类型
	
	// 字符集转换
	
	if (null != strRoomName)
	{
		strRoomName = new String(strRoomName.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strRoomName = "";
	}
	
	if (null != strRoomTitle)
	{
		strRoomTitle = new String(strRoomTitle.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strRoomTitle = "";
	}
	
	if (null != strUserName)
	{
		strUserName = new String(strUserName.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strUserName = "";
	}

	if (null != strRoomDesc)
	{
		strRoomDesc = new String(strRoomDesc.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strRoomDesc = "";
	}

	if (null != strRoomType)
	{
		strRoomType = new String(strRoomType.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strRoomType = "";
	}
	
	if (null != strOperType)
	{
		strOperType = new String(strOperType.getBytes("ISO8859_1"),"utf-8");
	}
	else
	{
		strOperType = "";
	}
	
	// 访问数据库表
	if (0 < strOperType.length())
	{
		boolean  bOperOk = false;
		// 处理相关功能
		if (strOperType.equalsIgnoreCase("open"))
		{
			// 开播
			String  strSqlCode = "insert into ";
		}
		
		if (strOperType.equalsIgnoreCase("close"))
		{
			// 停播
			String  strSqlCode = "update set ";
		}
		
		if (strOperType.equalsIgnoreCase("delete"))
		{
			// 停播
			String  strSqlCode = "delete t ";
		}
		
		// 输出结果数据
		
		// xml 结果封装方法
		
	}
	
%>
