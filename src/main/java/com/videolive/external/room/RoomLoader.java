package com.videolive.external.room;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.videolive.model.webrepdata.ObjectFactory;
import com.videolive.model.webrepdata.Rooms;
import com.videolive.model.webrepdata.Webreps;

import java.util.ArrayList;
import java.util.List;


public class RoomLoader 
{
	public static boolean				sbRoomManing;	// 是否在维护房间信息			
	public static List<Rooms.Roominfo>  sRoomArray;		// 全局的房间列表对象
	
	
	/**
	 * 注册开播房间
	 * @param iOpenRoomInfo 房间信息
	 * @param inState 状态功能，待扩展
	 * @return
	 */
	public static boolean RegRoom(Rooms.Roominfo  iOpenRoomInfo, int inState)
	{
		boolean	bOk = false;
		
		if (null == sRoomArray)
		{
			sRoomArray = new ArrayList<Rooms.Roominfo>();
			sbRoomManing = false;
		}
		
		if (null != iOpenRoomInfo)
		{
			// 检测房间是否存在
			int  nRoomIndex = GetRoomIndexById(iOpenRoomInfo.getId());
			
			if (0 <= nRoomIndex)
			{
				sRoomArray.set(nRoomIndex, iOpenRoomInfo);
			}
			else
			{
				sRoomArray.add(iOpenRoomInfo);
			}
			
			bOk = true;
			
		}//if (null != iOpenRoomInfo)
		
		return bOk;
	}
	
	/**
	 * 注销房间
	 * @param iRoomInfo 房间信息
	 * @param inStatee 状态功能，待扩展
	 * @return
	 */
	public static boolean UnRegRoom(Rooms.Roominfo  iRoomInfo, int inState)
	{
		boolean	bOk = false;
		
		if ((null != sRoomArray)
				&& (null != iRoomInfo) )
		{
			int  nRoomIndex = GetRoomIndexById(iRoomInfo.getId());
			
			if (0 <= nRoomIndex)
			{
				sRoomArray.remove(nRoomIndex);
				bOk = true;
			}
		}
		
		return bOk;
	}
	
	/**
	 * 通过id获取房间序号
	 * @param istrRoomId 房间id
	 * @return
	 */
	public static int GetRoomIndexById(String istrRoomId)
	{
		if ((null != sRoomArray)
				&& (null != istrRoomId) )
		{
			for (int i = 0; i < sRoomArray.size(); i++)
			{
				if (istrRoomId.equalsIgnoreCase(sRoomArray.get(i).getId()))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 加载房间的xml 
	 * @return
	 */
	public String loadRoomsXml()
	{
		try
		{
			ObjectFactory		objFactory = new ObjectFactory();
			
			Webreps				repDatas = objFactory.createWebreps();
			//Cmdrep				repDatas = objFactory.createCmdrep();
			
			// 设置房间信息
			Rooms				rooms = objFactory.createRooms();
			
			Rooms.Roominfo 		roominfo = objFactory.createRoomsRoominfo();
	
			roominfo.setId("001");
			roominfo.setName("房间名称");
			roominfo.setPeople("341");
			roominfo.setType("live");
			
			roominfo.setImageurl("/image4.png");
			
			roominfo.setIshot("no");
			
			rooms.getRoominfo().add(roominfo);
	
			Rooms.Roominfo 		roominfo1 = objFactory.createRoomsRoominfo();
	
			roominfo1.setId("000");
			roominfo1.setName("直播间0号");
			roominfo1.setPeople("200");
			roominfo1.setType("live");
			
			roominfo1.setImageurl("/image4.png");
			
			roominfo1.setIshot("no");
			
			rooms.getRoominfo().add(roominfo1);
			
			repDatas.setRooms(rooms);
			
			String  strWebRepData = WriteWebReps(repDatas, "utf-8");
			
			return strWebRepData;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
    /*
     * 存贮数据到字符串中
     */
    public String WriteWebReps(Webreps iWebrep, String istrEncoding)
    {

        try 
        {  
        	JAXBContext context = JAXBContext.newInstance(Webreps.class); 
        	Marshaller m = context.createMarshaller();
        	
        	m.setProperty(Marshaller.JAXB_ENCODING, istrEncoding);
        	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        	
        	StringWriter writer = new StringWriter();
        	
            //FileOutputStream os = new FileOutputStream(istrFileName);
            
            m.marshal(iWebrep, writer);
            
           
            return  writer.toString();
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
            return  null;
        }  	
        
    }  	
}
