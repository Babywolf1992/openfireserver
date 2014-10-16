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
    
   
}
