package com.videolive.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.videolive.dao.LogDao;
import com.videolive.model.Log;
import com.videolive.model.Page;
import com.videolive.model.SearchInfo;

/**
 * logDaoImpl.java
 * @author mxr
 *ClassName LogDaoImpl 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright RSDSYST
 */
@Repository
public class LogDaoImpl implements LogDao {
	/* 封装一个JdbcTemplate的模板对象 */ 
	private JdbcTemplate jdbcTemplate;
	 /* 通过set方法注入进来即可 */  
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    } 

	@Override
	public boolean insert(Log log){
		// 定义返回结果  
        boolean flag = false;  
        /* 插入实现 */  
        int i = 0;
        try{
        	i = jdbcTemplate.update("insert into logdata(description,method,type,requestIp,exceptionCode,"
        			+ "exceptionDetail,params,createBy,createDate) values(?,?,?,?,?,?,?,?,?)", new Object[]{
        					log.getDescription(),log.getMethod(),log.getType(),log.getRequestIp(),log.getExceptionCode(),
        					log.getExceptionDetail(),log.getParams(),log.getCreateBy(),log.getCreateDate()
        			});
        }catch(Exception e){
        	e.printStackTrace();
        	if(i<=0){
        		System.out.println("log insert fail");
        	}
        }
        if(i>0){
        	flag = true;
        }
		return flag;
	}
	
	 /**
	    * 
	    * @param page
	    * @param sql
	    * @return
	    */
	    //分页操作
	    @SuppressWarnings("unchecked")
		public List<Log> pages(Page page,String sql)
	    { 
	        int perNum=page.getPerNum();//每页显示条数
	        int current=page.getCurrentPage();//当前页

	 	    sql=sql+" LIMIT "+((current-1)*perNum)+" , "+perNum;
	 	    System.out.println("DaoImpl-query: "+sql);
	 	    
	 	   List<Log> entities =new ArrayList<Log>();
	    	 entities = (List<Log>) jdbcTemplate.query(  
	                 sql,
	                 new RowMapper() {  	
	                     @Override  
	                     public Object mapRow(ResultSet rs, int rowNum)  
	                             throws SQLException {  
	                         Log log = new Log();  
	                         log.setId(String.valueOf( rs.getInt("id")));
	                         log.setCreateBy(rs.getString("createBy"));  
	                         log.setCreateDate(rs.getDate("createDate").toString());
	                         log.setDescription(rs.getString("description")); 
	                         log.setExceptionCode(rs.getString("exceptionCode"));
	                         log.setExceptionDetail(rs.getString("exceptionDetail"));
	                         log.setMethod(rs.getString("method"));
	                         log.setParams(rs.getString("params"));
	                         log.setRequestIp(rs.getString("requestIp"));
	                         log.setType(rs.getString("type"));
	   
	                         return log;  
	                     }  
	                 });  
	    	
	    	return entities;
	    }
	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> query(SearchInfo searchInfo,Page page){ 
		String sql = "SELECT * FROM logdata WHERE 1=1 ";
		String textqueryvalue;
		textqueryvalue = searchInfo.getTextqueryvalue() ;
		String startdate = searchInfo.getStartdate();
		String enddate = searchInfo.getEnddate();
		
	    if (null != textqueryvalue && !textqueryvalue.equalsIgnoreCase("")) {
			sql = sql + "  AND description like " + "'%" + textqueryvalue + "%'";
			
		    }
	    if (StringUtils.isNotEmpty(startdate)) {
		sql = sql + " AND  createDate between " +"'"+ startdate+"'";
		
	    }
	    if (StringUtils.isNotEmpty(enddate)) {
		sql = sql + " AND  "  +"'"+enddate+"'";
		
	    }
	    // TODO Auto-generated method stub
	
	    return (List<Log>)pages(page,sql);
	    
    	
	}
	
	 /**
     * 分页相关代码
     * @return
     */
  //总页数
    public int pages(SearchInfo searchInfo){  
        String sql = "SELECT  COUNT(*)  FROM logdata where 1=1 ";
        if(null != searchInfo){
        	String textqueryvalue;
    		textqueryvalue = searchInfo.getTextqueryvalue() ;
    		String startdate = searchInfo.getStartdate();
    		String enddate = searchInfo.getEnddate();
    		
    	    if (null != textqueryvalue && !textqueryvalue.equalsIgnoreCase("")) {
    			sql = sql + "  AND description like " + "'%" + textqueryvalue + "%'";
    			
    		    }
    	    if (StringUtils.isNotEmpty(startdate)) {
    		sql = sql + " AND  createDate between " +"'"+ startdate+"'";
    		
    	    }
    	    if (StringUtils.isNotEmpty(enddate)) {
    		sql = sql + " AND  "  +"'"+enddate+"'";
    		
    	    }
        }
        int dataNum= jdbcTemplate.queryForInt(sql);
       System.out.println(sql+dataNum);
        return dataNum;
    }
}
