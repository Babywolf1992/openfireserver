<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.page import="com.videolive.external.room.*,com.videolive.model.webrepdata.*,java.util.* "/> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%
    //设置上下文路径
			String ctxPath = request.getContextPath();
			request.setAttribute("ctxPath", ctxPath);
%>
<%
	String strRoomType = (String)request.getAttribute("kinds");		// 分类
	String strChannelType = (String)request.getAttribute("channel");	// 频道
	List<Rooms.Roominfo> roomslist = (List<Rooms.Roominfo>)request.getAttribute("roomslist");

	if (null == strRoomType)
	{
		strRoomType = "all";
	}
	
	if (null == strChannelType)
	{
		strChannelType = "";
	}

	// 字符集转换处理
	//strChannelType = new String(strChannelType.getBytes("ISO8859_1"),"utf-8");
	//strRoomType = new String(strRoomType.getBytes("ISO8859_1"),"utf-8");
		
	System.out.println("room type:" + strRoomType + "  channel type:" + strChannelType);
	
	
	ObjectFactory	cmdFactory = new ObjectFactory();
	// 创建基本指令数据
	Webreps  repdata = cmdFactory.createWebreps();
	
	repdata.setErrorcode("0");
		
	//RoomLoader	roomLoader = new RoomLoader();
	
	// ...

	if (null != roomslist)
	{
		// 返回结果
		Rooms  	rooms = cmdFactory.createRooms();		
		repdata.setRooms(rooms);
		
		for (int i = 0; i < roomslist.size(); i++)
		{
			rooms.getRoominfo().add( roomslist.get(i));
		}
	}
	
	// xml转换为字符串
	String  strXMLData = CmdTools.WriteWebreps(repdata, "utf-8") ;

	out.println(strXMLData);	
	
	
	/*	
	if (0 < strRoomType.length())
	{
		// 分类内容
		if (strRoomType.equalsIgnoreCase("all")) 
		{
			// 返回全部直播厅
			out.println("roominfo, 直播厅1, 543, /room1.png,");
			out.println("roominfo, 美女心情, 102, /room2.png,");
			out.println("roominfo, 球星飞翔, 28, /room3.png,");
			out.println("roominfo, 娱乐中心, 168, /room4.png,");
		}
		else if (strRoomType.equalsIgnoreCase("fun")) 
		{
			out.println("roominfo, 娱乐圈2, 543, /room1.png,");
			out.println("roominfo, 娱乐圈3, 102, /room2.png,");
			out.println("roominfo, 娱乐圈4, 28, /room3.png,");
			out.println("roominfo, 娱乐中心, 168, /room4.png,");
		}
		else if (strRoomType.equalsIgnoreCase("paly")) 
		{
			out.println("roominfo, 小说G, 543, /room1.png,");
			out.println("roominfo, 鬼故事B, 102, /room2.png,");
			out.println("roominfo, 猫扑, 28, /room3.png,");
			out.println("roominfo, 天涯, 168, /room4.png,");
		}
		else if (strRoomType.equalsIgnoreCase("game")) 
		{
			out.println("roominfo, 手游, 543, /room1.png,");
			out.println("roominfo, 页游, 102, /room2.png,");
			out.println("roominfo, 端游, 28, /room3.png,");
			out.println("roominfo, 聊天, 168, /room4.png,");
		}
		else if (strRoomType.equalsIgnoreCase("money")) 
		{
			out.println("roominfo, 炒股, 543, /room1.png,");
			out.println("roominfo, 期货, 102, /room2.png,");
		}
	}
	else
	{
		// 推荐的内容	
		// 返回全部直播厅
		out.println("roominfo, 推荐1, 543, /room1.png,");
		out.println("roominfo, 推荐2, 102, /room2.png,");
		out.println("roominfo, 推荐3, 28, /room3.png,");
		out.println("roominfo, 推荐4, 168, /room4.png,");
	}*/
	
%>
