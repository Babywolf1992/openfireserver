package com.videolive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.videolive.aop.SystemServiceLog;
import com.videolive.dao.impl.LogDaoImpl;
import com.videolive.dao.impl.UserDaoImpl;
import com.videolive.model.ReleaseInfo;
import com.videolive.model.UploadFiles;
import com.videolive.model.User;
import com.videolive.model.webrepdata.Rooms;

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
	 
	 public List<User> getAllUserInfo(){
		 List<User> entities;
		 entities = userDaoImpl.getAllUser();
		 return entities;
	 }
	 
	 public List<Rooms.Roominfo> getAllRoomsInfo(){
		 List<Rooms.Roominfo> entities;
		 entities = userDaoImpl.getAllRooms();
		 return entities;
		 
	 }
	 
	 //通过id检查是否存在
	 public boolean isExistById(String id){
		 return userDaoImpl.findbyId(id);
	 }
	 
	 //注册房间
	 public boolean regroom(Rooms.Roominfo roomInfo,int inState){
		 boolean isOk = false;
		 if(!isExistById(roomInfo.getId())){ //注册的房间id不存在,可以进行房间注册
			 isOk = userDaoImpl.regVideoRoom(roomInfo);
		 }
		 
		 return isOk;
	 }
	 
	 //删除房间
	 public boolean delroom(Rooms.Roominfo roomInfo,int inState){
		 boolean isOk = false;
		 if(isExistById(roomInfo.getId())){ //要删除的id存在
			 isOk = userDaoImpl.delVideoRoom(roomInfo);
		 }
		 return isOk;
	 }
	 
	 //修改房间信息
	 public boolean modifyroom(Rooms.Roominfo roomInfo,int inState){
		 boolean isOk = false;
		 if(isExistById(roomInfo.getId())){ //要修改的id存在
			 isOk = userDaoImpl.modifyVideoRoom(roomInfo);
		 }
		 return isOk;
	 }
	
	 //存储发布内容
	 public boolean infosave(ReleaseInfo releaseInfo){
		 boolean isOk = false;
		 isOk = userDaoImpl.saveReleaseInfo(releaseInfo);
		 return isOk;
	 }
	 
	 //管理发布信息
	 public List<ReleaseInfo> getInfoRelease(){
		 List<ReleaseInfo> list = userDaoImpl.getInfoRelease();
		 return list;
		 
	 }
	 
	 //根据id获取单条信息
	 public ReleaseInfo getViewInfo(String id){
		 ReleaseInfo releaseInfo = userDaoImpl.getViewInfo(id);
		 return releaseInfo;
	 }
	 
	 //存储上传的文件
	 public boolean saveuploadfiles(ReleaseInfo uplf){
		 boolean isOk = false;
		 isOk = userDaoImpl.saveUploadFiles(uplf);
		 return isOk;
	 }

}
