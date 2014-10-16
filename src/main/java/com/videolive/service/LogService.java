package com.videolive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.videolive.dao.impl.LogDaoImpl;
import com.videolive.model.Log;
import com.videolive.model.Page;
import com.videolive.model.SearchInfo;

/**
 * 
 * @author mxr
 *ClassName LogService 
 *@Version 1.0
 *@ModifiedBy 修改人
 *@Copyright unknown
 */
@Service
public class LogService {
@Autowired
private LogDaoImpl logDaoImpl;

@Transactional
public void add(Log log){
	logDaoImpl.insert(log);
}

public int pages(SearchInfo searchInfo)
{
    return logDaoImpl.pages(searchInfo);
}

public List<Log> query(SearchInfo searchInfo,Page page) {
	// TODO Auto-generated method stub
	return logDaoImpl.query(searchInfo,page);
    }

}
