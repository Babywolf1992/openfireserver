package com.videolive.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.videolive.aop.SystemControllerLogAfterAspect;
import com.videolive.model.User;
import com.videolive.model.webrepdata.CmdTools;
import com.videolive.model.webrepdata.Cmdrep;
import com.videolive.model.webrepdata.ObjectFactory;
import com.videolive.model.webrepdata.Rooms;
import com.videolive.model.webrepdata.Webreps;
import com.videolive.service.VideoLiveService;
import com.videolive.util.Blowfish;
import com.videolive.util.BlowfishUtil;



/**
 * 
 * @author mxr
 *ClassName VideoLiveController 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright unknown
 */
@Controller
@RequestMapping("/videolive.do")
public class VideoLiveController {
	protected final transient Log log = LogFactory.getLog(VideoLiveController.class);
	@Autowired 
	VideoLiveService videoLiveService;
   
	public VideoLiveController(){}
	
	@RequestMapping(params="method=login")
    @SystemControllerLogAfterAspect(description = "用户登录")
    public String login (User user,HttpSession httpSession) {
    	String username = user.getUsername();
    	String plainPassword = user.getPlainPassword();
    	List<User> entities = videoLiveService.getUserInfo(username);
    	String passwordKey = videoLiveService.getPasswordKey();
    	//此系统后台操作管理员只有一名，即admin
    	 Blowfish cipher = BlowfishUtil.getCipher(passwordKey);//单例模式获得对象
    	for(int i=0;i<entities.size();i++){
	    		if (username.equalsIgnoreCase("admin")&&username.equalsIgnoreCase(entities.get(i).getUsername()) 
		    			&& plainPassword.equalsIgnoreCase(cipher.decrypt(entities.get(i).getEncryptedPassword()))){
	    			httpSession.setAttribute("user", entities.get(i));
		    		return "mainframe/mainframe";
	    	}

    	}
    	
    	return "mainframe/login";
    	
    }
	
	@RequestMapping(params="method=alluserinfo")
	public String alluserInfo(ModelMap modelMap){
		List<User> list = videoLiveService.getAllUserInfo();
		modelMap.put("userlist", list);
		return "mainframe/AllUserInfo";
	}
	
	//获取全部房间信息
	@RequestMapping(params="method=getroominfo")
	public String getroominfo(HttpServletRequest request,ModelMap modelMap,@RequestParam("kinds") String kinds,
			@RequestParam("channel") String channel){
		request.setAttribute("kinds", kinds);
		request.setAttribute("channel", channel);
		List<Rooms.Roominfo> list = videoLiveService.getAllRoomsInfo();
		request.setAttribute("roomslist", list);
		return "mainframe/getroominfo";
	}
	
	//对直播房间进行增删改查
	@RequestMapping(params="method=roomoper")
	public String doroomoper(HttpServletRequest request,ModelMap modelMap){
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String image = request.getParameter("image");
		ObjectFactory	cmdFactory = new ObjectFactory();
		// 创建基本指令数据
		Webreps  repdata = cmdFactory.createWebreps();	
		repdata.setErrorcode("1");	// 默认为有错误
		// 创建反馈指令
		Cmdrep	repcmd = cmdFactory.createCmdrep();
		// 生成RoomInfo
		Rooms.Roominfo  roomInfo = cmdFactory.createRoomsRoominfo();
		if(null!=oper){
			// 生成RoomInfo
			roomInfo.setId(id);
			roomInfo.setName(name);
			roomInfo.setType(type);
			roomInfo.setPeople("1");
			roomInfo.setImageurl(image);
			roomInfo.setIshot("no");
			boolean isOk = false;
			if("open".equalsIgnoreCase(oper)){
				// 打开
				isOk = videoLiveService.regroom(roomInfo,0);
				repdata.setErrorcode("0");
				repcmd.setRepcode("0");
				repcmd.setInfo("open");
				repcmd.setDesc("创建房间成功!");
			}
			else if("close".equalsIgnoreCase(oper)){
				isOk = videoLiveService.delroom(roomInfo,0);
				repdata.setErrorcode("0");
				repcmd.setRepcode("0");
				repcmd.setInfo("close");
				repcmd.setDesc("注销房间操作成功!");
			}
			else if("modify".equalsIgnoreCase(oper)){
				isOk = videoLiveService.modifyroom(roomInfo,0);
				repdata.setErrorcode("0");
				repcmd.setRepcode("0");
				repcmd.setInfo("modify");
				repcmd.setDesc("修改房间操作成功!");
			}
			else
			{
				repcmd.setRepcode("1");
				repcmd.setInfo("room oper");
				repcmd.setDesc("操作类型未指定！");
			}
		}
		repdata.setCmdrep(repcmd);
		// xml转换为字符串
		String  strXMLData = CmdTools.WriteWebreps(repdata, "utf-8") ;
		//out.println(strXMLData);	jsp 内置对象
		request.setAttribute("strXMLData", strXMLData);
		return "mainframe/roomoper";
		
	}

}
