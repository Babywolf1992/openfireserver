package com.videolive.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.videolive.aop.SystemControllerLogAfterAspect;
import com.videolive.model.User;
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
@RequestMapping("/emp.do")
public class VideoLiveController {
	protected final transient Log log = LogFactory.getLog(VideoLiveController.class);
	@Autowired 
	VideoLiveService videoLiveService;
   
	
	@RequestMapping("method=login")
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
	    			httpSession.setAttribute("user", user);
		    		return "mainframe/mainframe";
	    	}

    	}
    	
    	return "mainframe/login";
    	
    }

}
