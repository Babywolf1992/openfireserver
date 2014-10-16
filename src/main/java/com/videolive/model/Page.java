package com.videolive.model;

public class Page {
	 private int perNum;//每页多少条
	    private int currentPage;//第几页
	    public int getPerNum() {
	        return perNum;
	    }
	    public void setPerNum(int perNum) {
	        this.perNum = perNum;
	    }
	    public int getCurrentPage() {
	        return currentPage;
	    }
	    public void setCurrentPage(int currentPage) {
	        this.currentPage = currentPage;
	    }

 
}
