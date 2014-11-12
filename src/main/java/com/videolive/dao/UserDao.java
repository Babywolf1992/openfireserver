package com.videolive.dao;

import java.util.List;

import com.videolive.model.User;
import com.videolive.model.webrepdata.Rooms;

/**
 * 
 * @author mxr
 *ClassName UserDao 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright unknown
 */


public interface UserDao {
    /*查询所有*/  
    public List<User> findName(String name); 
    /*获取passwordkey*/
    public String getPasswordKey();
    /*获取所有用户*/
    public List<User> getAllUser();
    /*获取所有视频直播房间信息*/
    public List<Rooms.Roominfo> getAllRooms();
    /*通过id判断房间是否存在*/
    public boolean findbyId(String id);
    /*添加视频房间*/
    public boolean regVideoRoom(Rooms.Roominfo roomInfo);
    /*删除视频房间*/
    public boolean delVideoRoom(Rooms.Roominfo roomInfo);
    /*修改视频房间信息*/
    public boolean modifyVideoRoom(Rooms.Roominfo roomInfo);
}
