package com.videolive.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.videolive.dao.UserDao;
import com.videolive.model.User;
import com.videolive.model.webrepdata.Rooms;


@Repository
public class UserDaoImpl implements UserDao{
	/* 封装一个JdbcTemplate的模板对象 */   
    private JdbcTemplate jdbcTemplate;
    
    /* 通过set方法注入进来即可 */    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    } 
    @SuppressWarnings("unchecked")
    @Override
	public List<User> findName(String name) {
        User entity = null;  
        List<User> entities = new ArrayList<User>();
        entities = (List<User>) jdbcTemplate.query(  
                "select * from ofuser where username=?",  
                new Object[] { name }, new RowMapper() {  
                    @Override  
                    public Object mapRow(ResultSet rs, int rowNum)  
                            throws SQLException {  
                            User user = new User();  
                        	user.setUsername(rs.getString("username"));  
                            user.setEncryptedPassword(rs.getString("encryptedPassword"));
                        return user;  
                    }  
                });  
        return entities;  
	}
    
    @SuppressWarnings("unchecked")
	@Override
    public String getPasswordKey(){
    	String passwordKey;
    	passwordKey = (String)jdbcTemplate.queryForObject("select propValue from ofproperty where name = 'passwordKey' ",
    			new RowMapper() {  
            @Override  
            public Object mapRow(ResultSet rs, int rowNum)  
                    throws SQLException {  
                    String passwordKey = rs.getString("propValue");
                return passwordKey;  
            }  
        });
    	
    	return passwordKey;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<User> getAllUser(){
    	 List<User> entities = new ArrayList<User>();
         entities = (List<User>) jdbcTemplate.query(  
                 "select * from ofuser ",  
                 new Object[] { }, new RowMapper() {  
                     @Override  
                     public Object mapRow(ResultSet rs, int rowNum)  
                             throws SQLException {  
                             User user = new User();  
                         	 user.setUsername(rs.getString("username"));  
                         	 user.setPlainPassword(rs.getString("plainPassword"));                        	
                             user.setEncryptedPassword(rs.getString("encryptedPassword"));
                             user.setName(rs.getString("name"));
                             user.setEmail(rs.getString("email"));                           
                             user.setCreationDate(rs.getString("creationDate"));
                             user.setModificationDate(rs.getString("modificationDate"));
                         return user;  
                     }  
                 });  
         return entities;  
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Rooms.Roominfo> getAllRooms(){
    	List<Rooms.Roominfo> entities = new ArrayList<Rooms.Roominfo>();
        entities = (List<Rooms.Roominfo>) jdbcTemplate.query(  
                "select * from rooms ",  
                new Object[] { }, new RowMapper() {  
                    @Override  
                    public Object mapRow(ResultSet rs, int rowNum)  
                            throws SQLException {  
                            Rooms.Roominfo roominfo = new Rooms.Roominfo();
                            roominfo.setId(rs.getString("id"));
                            roominfo.setImageurl(rs.getString("imageurl"));
                            roominfo.setIshot(rs.getString("ishot"));
                            roominfo.setName(rs.getString("name"));
                            roominfo.setPeople(rs.getString("people"));
                            roominfo.setType(rs.getString("type"));
                        	
                        return roominfo;  
                    }  
                });  
        return entities;  
    }
    
    @Override
    public boolean findbyId(String id){
    	String sql = "select count(*) from rooms where id =" +id;
    	int isExist = jdbcTemplate.queryForInt(sql);
    	return isExist>0?true:false;
    }
    
    @Override
    public boolean regVideoRoom(Rooms.Roominfo roomInfo){
    	String sql = "insert into rooms(id,name,type,people,imageurl,ishot) values(?,?,?,?,?,?)";
    	int i = 0;
    	i = jdbcTemplate.update(sql, new Object[]{roomInfo.getId(),roomInfo.getName(),roomInfo.getType(),
    			roomInfo.getPeople(),roomInfo.getImageurl(),roomInfo.getIshot()});
    	return i>0?true:false;
    }
    
    @Override
    public boolean delVideoRoom(Rooms.Roominfo roomInfo){
    	String sql = "delete from rooms where id = "+roomInfo.getId();
    	int i = 0;
    	i = jdbcTemplate.update(sql);
    	return i>0?true:false;
    }
    
    @Override
    public boolean modifyVideoRoom(Rooms.Roominfo roomInfo){
    	int i = jdbcTemplate.update("update rooms set id=?,name=?,type=?,people=?,imageurl=?,ishot=? where id=?", 
    			new Object[] { roomInfo.getId(),roomInfo.getName(),roomInfo.getType(),roomInfo.getPeople(),
    			roomInfo.getImageurl(),roomInfo.getIshot(),roomInfo.getId()});
    	return i>0?true:false;
    }
   
}
