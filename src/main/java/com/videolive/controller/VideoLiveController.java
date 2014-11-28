package com.videolive.controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.videolive.aop.SystemControllerLogAfterAspect;
import com.videolive.model.ReleaseInfo;
import com.videolive.model.UploadFiles;
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
	
	@RequestMapping(params="method=inforelease")
	String infoRelease(HttpServletRequest request,ModelMap modelMap){
		
		return "mainframe/InfoRelease";
	}
	
	@RequestMapping(params="method=infosave")
	String infosave(HttpServletRequest request,ModelMap modelMap,ReleaseInfo releaseInfo){
		videoLiveService.infosave(releaseInfo);
		return "mainframe/InfoRelease";

	}
	
	@RequestMapping(params="method=infomanage")
	String infomanage(HttpServletRequest request,ModelMap modelMap){
		List<ReleaseInfo> list = videoLiveService.getInfoRelease();
		request.setAttribute("list", list);
		return "mainframe/InfoManage";
	}
	
	@RequestMapping(params="method=viewinfo")
	String viewinfo(HttpServletRequest request,ModelMap modelMap){
		String id = request.getParameter("id");
		ReleaseInfo releaseInfo = videoLiveService.getViewInfo(id);
		request.setAttribute("releaseInfo", releaseInfo);
		return "mainframe/ViewInfo";
	}
	
	//保存从手机传来的信息和文件
	@RequestMapping(params="method=uploadfiles")
	String uploadfiles(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)throws IOException{
		  request.setCharacterEncoding("utf-8");
		  response.setContentType("application/octet-stream");
		  InputStream is;
		  is = request.getInputStream();
		  try {
		  int size = 0;
		  byte[] tmp = new byte[100000];
		  Date currentDate = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String createBy = request.getParameter("currentUser");
		  String realPath=request.getParameter("realPath");
		  System.out.println("FMSFS-->realPath:"+realPath);
		  String fileName = realPath;
		  String fileType = request.getParameter("flag");
		  String txtTitle = java.net.URLDecoder.decode( request.getParameter("txtTitle"),"UTF-8");
		  String editor = java.net.URLDecoder.decode(request.getParameter("editor"),"UTF-8");
		  String validate = "-1";//默认不合法
		  //java.net.URLDecoder.decode(request.getParameter("title"),"UTF-8");
		  System.out.println("test: "+txtTitle+" "+editor);
		  //将文件信息存入数据库
		  ReleaseInfo releaseInfo = new ReleaseInfo();
		  releaseInfo.setTxtTitle(txtTitle);
		  releaseInfo.setEditor(editor);
		  releaseInfo.setValidate(validate);
		  releaseInfo.setCreatetime(sdf.format(currentDate));
		  releaseInfo.setFilename(fileName);
		  releaseInfo.setFiletype(fileType);
		  releaseInfo.setFileurl("http://203.195.152.150:8081/uploadfiles/"+fileName);
		  releaseInfo.setCreator(createBy);
		  videoLiveService.saveuploadfiles(releaseInfo);
		  int len = 0;
		  // 创建一个文件夹用来保存发过来的图片；
		  File f = new File("E:/uploadfiles/"+fileName);
		  DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
		  while ((len = is.read(tmp)) != -1) {
		  dos.write(tmp, 0, len);
		  size += len;
		  }
		  dos.flush();
		  dos.close();
		  } catch (IOException e) {
		  e.printStackTrace();
		  }
		return "mainframe/UploadFiles";
	}
	

}
