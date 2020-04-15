<%@ page language="java" import="com.bean.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据管理</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-table.min.css" rel="stylesheet">

<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="jquery/jquery-3.2.1.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>


<script src="bootstrap/js/bootstrap-table.js"></script>
<script src="js/checkLoginStatus.js"></script>
<script src="js/admin02.js"></script>
<style type="text/css">
.btn{
margin-left:20px;
}
tr>td:nth-last-child(2){
	width:100px;
	word-break:keep-all; 
	overflow:hidden;
	text-overflow:ellipsis;
}
tr>td:last-child{
	width:5%;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-10">
			<ul class="nav nav-pills pull-right">
				
				
				<li id="feedbackManege"><a href="admin01.jsp">用户反馈管理</a></li>
				<li class="active"><a href="admin02.jsp">数据管理</a></li>
				<c:if test="${empty sessionScope.currentAdmin }">
					<li><a href="login.jsp">登陆</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentAdmin }">
					<li><a>${currentAdmin.username}</a></li>
					<li id="adminExit"><a>退出</a></li>
				</c:if>
			</ul>
		</div>  
		  
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<h3 class="pull-left">数据管理</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<div class="form-group form-inline">
				<label for="infoInput">输入数据：</label>
				<input type="text" class="form-control" id="infoInput" placeholder="请输入查询的信息">
				<button type="submit" class="btn btn-default" id="getInfoBtn">查询</button>
				<button type="submit" class="btn btn-default" id="adminAdd">添加</button>
				
			</div>	
		</div>
	</div>
	<hr>
	<div class="row">
		<div  class="col-md-12">
			<table id="resultTable"></table>
		</div>
	</div>
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">修改</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="form-group form-inline">
						<label for="abbreviation">缩略词：</label>
						<input type="text" class="form-control" id="abbreviation" placeholder="必填" style="width: 65%;">
					</div>
					<div class="form-group form-inline">	
						<label for="fullWords">完整英文词汇：</label>
						<input type="text" class="form-control" id="fullWords" placeholder="必填" style="width: 58%;">
					</div>
					<div class="form-group form-inline">
						<label for="specificMeanning">中文解释：</label>
						<input type="text" class="form-control" id="specificMeanning" placeholder="必填" style="width: 63%;">
					</div>
					<div class="form-group form-inline">	
						<label for="remark">备注：</label>
						<input type="text" class="form-control" id="remark" placeholder="" style="width: 68%;">
					</div>
					<div class="form-group">
						<label for="specificMeanning">专业名词含义：</label>
						<textarea class="form-control" rows="6" id="lemmaSummary" placeholder="" style="width: 80%;"></textarea> 
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="add" style="display:none;">确定</button>
	                <button type="button" class="btn btn-primary" id="modify">确定</button>
	            </div>
	        </div>
    	</div>
	</div>
</div>

</body>
</html>