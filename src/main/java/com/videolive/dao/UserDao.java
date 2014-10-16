package com.videolive.dao;

import java.util.List;
import com.videolive.model.User;

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
}
