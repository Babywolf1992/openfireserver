<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.page import="com.videolive.external.room.*,com.videolive.model.webrepdata.*"/>    
<%
	// 用于测试各项 web指令返馈， 用于测试数据的
	String strCmdType = request.getParameter("cmd");		// 分类
	
	if (null == strCmdType)
	{
		strCmdType = "room";//"default";
	}

	// 字符集转换处理
	strCmdType = new String(strCmdType.getBytes("ISO8859_1"),"utf-8");
		
	System.out.println("cmd type:" + strCmdType);
	
	// 采用新的访问输出结果
	if (0 < strCmdType.length())	
	{
		ObjectFactory	cmdFactory = new ObjectFactory();
		// 创建基本指令数据
		Webreps  repdata = cmdFactory.createWebreps();
		
		repdata.setErrorcode("0");
		
		// 房间查询指令
		if (strCmdType.equalsIgnoreCase("room"))
		{
			Rooms  	rooms = cmdFactory.createRooms();
			Rooms.Roominfo  roomInfo = cmdFactory.createRoomsRoominfo();
			
			repdata.setRooms(rooms);
			
			roomInfo.setId("001");
			roomInfo.setName("name");
			roomInfo.setImageurl("./img/456.png");
			roomInfo.setPeople("3416");
			roomInfo.setType("game");
			roomInfo.setIshot("yes");
			
			rooms.getRoominfo().add(roomInfo);

			Rooms.Roominfo  roomInfo2 = cmdFactory.createRoomsRoominfo();
			
			roomInfo2.setId("002");
			roomInfo2.setName("name2");
			roomInfo2.setImageurl("./img/113.png");
			roomInfo2.setPeople("416");
			roomInfo2.setType("game");
			roomInfo2.setIshot("yes");
			
			rooms.getRoominfo().add(roomInfo2);
		}
		
		// 操作反馈指令
		if (strCmdType.equalsIgnoreCase("rep"))
		{
			Cmdrep	repcmd = cmdFactory.createCmdrep();
			
			repcmd.setRepcode("0");
			repcmd.setInfo("cmd info!");
			repcmd.setDesc("rep desc data!");
			
			repdata.setCmdrep(repcmd);
		}
		
		// 返馈消息数据 msg
		if (strCmdType.equalsIgnoreCase("msg"))
		{
			Msg   msg = cmdFactory.createMsg();
			
			msg.setType("type0");
			msg.setText("msg text content!");
			msg.setSource("source client!");
			
			repdata.setMsg(msg);
		}
		
		// 道具相关指令
		if (strCmdType.equalsIgnoreCase("weapons"))
		{
			// 
			Webreps.Weapons  weapons = cmdFactory.createWebrepsWeapons();
			Weapon	 		 weapon = cmdFactory.createWeapon();
			
			weapon.setId("002");
			weapon.setName("name");
			weapon.setDesc("道具描述");
			weapon.setPrice("100");
			weapon.setState("有效");
			weapon.setCount("数量");
			
			weapons.getWeapon().add(weapon);
			
			repdata.setWeapons(weapons);
		}
		
		// 个人信息设置指令
		if (strCmdType.equalsIgnoreCase("info"))
		{
			Cmdrep	repcmd = cmdFactory.createCmdrep();
			
			repcmd.setRepcode("0");
			repcmd.setInfo("cmd info!");
			repcmd.setDesc("rep desc data!");
			
			repdata.setCmdrep(repcmd);			
		}
		
		// xml转换为字符串
		String  strXMLData = CmdTools.WriteWebreps(repdata, "utf-8") ;

		out.println(strXMLData);
	
	}
	
%>