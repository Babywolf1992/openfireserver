<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:directive.page import="java.util.* , java.sql.* ,  com.videolive.model.*"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    //设置上下文路径
			String ctxPath = request.getContextPath();
			request.setAttribute("ctxPath", ctxPath);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询结果</title>
<script type="text/javascript" src="${ctxPath}/mainframe/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxPath}/jquery/jquery-1.8.0.js"></script>
<script type="text/javascript" language="javascript"
	src="mainframe/script/script.js"></script>
<!--  <link href="css/back.css" rel="stylesheet" type="text/css" />  -->
<style >
  body {font-size:10pt}
  table{  border-collapse:collapse;  }   
  td{  border:1px solid #000000;  }   

</style>
<script>
//查询
//下一页
function donext(){
	var currentPage=document.getElementById("currentPage").value;
	document.getElementById("queryform").action="${ctxPath}/emp.do?method=queryad&next="+currentPage;
	document.getElementById("queryform").submit();
}
//上一页
function dolast(){
	var currentPage=document.getElementById("currentPage").value;
	document.getElementById("queryform").action="${ctxPath}/emp.do?method=queryad&last="+currentPage;
	document.getElementById("queryform").submit();
}
//查询-首页
function doQuery(){

	document.getElementById("queryform").action="${ctxPath}/emp.do?method=queryad";
	document.getElementById("queryform").submit();
}
function doEnd(){
	var currentPage=document.getElementById("currentPage").value;
	document.getElementById("queryform").action="${ctxPath}/emp.do?method=queryad&endPage="+currentPage;
	document.getElementById("queryform").submit();
}
//第几页
function doGo(){
	var current=document.getElementById("currentPage").value;
	document.getElementById("queryform").action="${ctxPath}/emp.do?method=queryad&current="+current;
	document.getElementById("queryform").submit();
}



function CheckQueryForm()
{
   if  (document.queryform.textqueryvalue.value==""
		   &&document.queryform.startdate.value==""
		   &&document.queryform.enddate.value=="")
	   { 
		    alert("查找信息不能为空");
	     	return (false);
	   }
   if  ((document.queryform.startdate.value!=""&&document.queryform.enddate.value=="")
	   ||(document.queryform.startdate.value==""&&document.queryform.enddate.value!=""))
   { 
	    alert("起止时间填完整");
     	return (false);
   }

   return (true);
}


function add(){   
    window.location.href="<%=request.getContextPath() %>/emp.do?method=add";   
}   
  
function del(id){   
$.ajax( {   
    type : "POST",   
    url : "<%=request.getContextPath()%>/emp.do?method=delad&id=" + id,   
    dataType: "json",   
    success : function(data) {   
        if(data.del == "true"){   
            alert("删除成功！");   
            $("#" + id).remove();   
        }   
        else{   
            alert("删除失败！");   
        }   
    },   
    error :function(){   
        alert("网络连接出错！");   
    }   
});   
}   
</script>
</head>
<body class=main>
<center>
<div align="center"><font color="#072d54" size="3"><strong>信息管理</strong></font>
</div>
	<hr width="95%" size="1" color="#DCEAF3">
	
	<form id="queryform" name="queryform" action="<%=request.getContextPath() %>/emp.do?method=queryad" method="post" onsubmit="return CheckQueryForm();">
		<table width="95%" border="0" height="36" cellspacing="0" cellpadding="0"  align="center">
			<tr  bgcolor="#DCEAF3">
				<td colospan="5"  align="center">
					 按值查询:<input type="text" name="textqueryvalue" class="BigInput" size="15" maxlength="30" value="${searchInfo.getTextqueryvalue() }">
				</td>			
				<td  align="center">
					开始时间：		
					<input type="text" name="startdate" class="BigInput" size="15" maxlength="30" value="${searchInfo.getStartdate() }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</td>
				<td  align="center">
					结束时间：		
					<input type="text" name="enddate" class="BigInput" size="15" maxlength="30" value="${searchInfo.getEnddate() }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</td>
				<td  align="center">
					&nbsp;
					<input type="submit" value="查询" class="BigButton" title="查询" >&nbsp; &nbsp; 
				</td>
			</tr>
		</table>

	
	<hr width="95%" size="1" color="#DCEAF3">


	<div class="pageContainer">
	<table id="tb1" width="1100" border="0"  cellspacing="0" cellpadding="0"  align="center">  
    <tr>  
    	<td align="center" bgcolor="#B9D5E8" width="4%">id </td>
      	<td align="center" bgcolor="#B9D5E8" width="5%">信息名字 </td>
      	<td align="center" bgcolor="#B9D5E8" width="5%">附件名 </td>
      	<td align="center" bgcolor="#B9D5E8" width="20%">附件地址 </td>
      	<td align="center" bgcolor="#B9D5E8" width="5%">创建者 </td>
	    <td align="center" bgcolor="#B9D5E8" width="10%">创建时间 </td>
	    <td align="center" bgcolor="#B9D5E8" width="5%">是否审核 </td>
        <td bgcolor="#B9D5E8" align="center" width="10%">操作</td>
    </tr>  

    <c:forEach items="${list}" var="releaseInfo">  
    <tr id="<c:out value="${releaseInfo.id}"/>">  
        <td align="center"><c:out value="${releaseInfo.id}"/></td> 
        <td align="center"><a href="<%=request.getContextPath() %>/videolive.do?method=viewinfo&id=${releaseInfo.id}"><c:out value="${releaseInfo.txtTitle}"/></a></td>  
        <td align="center"><c:out value="${releaseInfo.filename}"/></td> 
         <td align="center"><c:out value="${releaseInfo.fileurl}"/></td> 
        <td align="center"><c:out value="${releaseInfo.creator}"/></td> 
        <td align="center"><c:out value="${releaseInfo.createtime}"/></td>
        <c:if test="${releaseInfo.validate=='-1'}">
        <td align="center"><c:out value="未审核"/></td>
        </c:if>
        <c:if test="${releaseInfo.validate=='1'}">
        <td align="center"><c:out value="审核"/></td>
        </c:if>
          
        <td align="center">      
            <input type="button" onclick="del('<c:out value="${releaseInfo.id}"/>')" value="审核"/> 
            <input type="button" onclick="del('<c:out value="${releaseInfo.id}"/>')" value="删除"/>
            <input type="button" onclick="del('<c:out value="${releaseInfo.id}"/>')" value="修改"/>   
        </td>  
    </tr>  
    </c:forEach>  
       
</table>  
		
    <c:forEach items="${list}" var="emp" end="0">  
		<c:if test="${emp.id != ''}">
		<div align="center">
			 每页<input type="text" name="perNum" id="perNum" value="${perNum }" size="2" />条记录
			|共<span style="color:red" >${dataNum}</span>条记录
			<c:if test="${currentPage > 1}">
				|<a href=" #" onclick ="doQuery()" >首页</a>
				|<a href=" #" onclick ="dolast()" >上一页</a>
			</c:if>
			<c:if test="${ currentPage != pages}">
				|<a href=" #" onclick ="donext()" >下一页</a>
				|<a href=" #" onclick ="doEnd()" >尾页</a>
			</c:if>
			|第<input type="text" size="2"  value="${currentPage}" id="currentPage"/>页 
			 <input type="button" style="width:30px;height:24px;font:black 20px;" onclick="doGo()" value="go"/>
			|共<label  name="pages"   id="pages">${pages}</label>页
			<!-- <input type="button" onclick="trDel()" value="删除选中行"/> -->
			</div>
			</c:if>
		</c:forEach> 
	</div>
</form> 
</center>
</body>
</html>