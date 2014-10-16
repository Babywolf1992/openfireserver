package com.videolive.dao;

import java.util.List;

import com.videolive.model.Log;
import com.videolive.model.Page;
import com.videolive.model.SearchInfo;

/**
 * 
 * @author mxr
 *ClassName LogDao 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright unknown
 */

public interface LogDao {
	
	/*插入新增日志 */
	public boolean insert(Log log);
	/*查询操作*/
	public List<Log> query(SearchInfo searchInfo,Page page);

}
