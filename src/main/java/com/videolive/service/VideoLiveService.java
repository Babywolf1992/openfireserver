package com.videolive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.videolive.aop.SystemServiceLog;
import com.videolive.dao.impl.LogDaoImpl;
import com.videolive.dao.impl.UserDaoImpl;
import com.videolive.model.User;

/**
 * 
 * @author mxr
 *ClassName VideoLiveService 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright unknown
 */
@Service
@Transactional
public class VideoLiveService {
	@Autowired
	UserDaoImpl userDaoImpl;

	
	@SystemServiceLog(description="登录获取用户信息出错")
	public List<User> getUserInfo(String strName) {
		 User user = null;
		 List<User> entities;
		 entities = userDaoImpl.findName(strName);
		 return entities;
		}
	 
	 @SystemServiceLog(description="获取passwordkey出错")
	 public String getPasswordKey(){
		 String passwordKey;
		 passwordKey = userDaoImpl.getPasswordKey();
		 return passwordKey;
	 }
	

}
