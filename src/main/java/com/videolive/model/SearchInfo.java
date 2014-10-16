package com.videolive.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchInfo {
	private String textqueryvalue;
	private String datatype;
	private String startdate;
	private String enddate;
	public SearchInfo(){}
	public SearchInfo(String textqueryvalue,String datatype,String startdate,String enddate){
		this.textqueryvalue = textqueryvalue;
		this.datatype = datatype;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	public String getTextqueryvalue() {
		return textqueryvalue;
	}
	public void setTextqueryvalue(String textqueryvalue) {
		this.textqueryvalue = textqueryvalue;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	

}
