<%@ page language="java" import="com.bean.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>反馈管理</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-table.min.css" rel="stylesheet">

<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="jquery/jquery-3.2.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="bootstrap/js/bootstrap-table.js"></script>
<script src="js/checkLoginStatus.js"></script>
<script src="js/admin01.js"></script>
<style>
tr>td:last-child{
	width:10%;
}

</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-10">
			<ul class="nav nav-pills pull-right">
				<li class="active" id="feedbackManege"><a href="#">用户反馈管理</a></li>
				<li><a href="admin02.jsp">数据管理</a></li>
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
			<h3 class="pull-left">用户反馈管理</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="feedbackList">
				<table id="feedbackTable"></table>
			</div>
			
		</div>
	</div>
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">编辑</h4>
            </div>
            <div class="modal-body">
            	<div class="form-group form-inline">
					<label for="v_id">v_id：</label>
					<input type="text" class="form-control" id="v_id" style="width: 52%;" readonly>
				</div>
            	<div class="form-group form-inline">
					<label for="abbreviation">Abbreviation：</label>
					<input type="text" class="form-control" id="abbreviation" style="width: 52%;">
				</div>
				<div class="form-group form-inline">
					<label for="fullWords">FullWords：</label>
					<input type="text" class="form-control" id="fullWords" style="width: 45%;">
				</div>	
				
				<div class="form-group form-inline">
					<label for="specificMeanning" >SpecificMeanning：</label>
					<input type="text" class="form-control" id="specificMeanning" style="width: 50%;">
				</div>
				
				<div class="form-group">
					<label for="specificMeanning">LemmaSummary：</label>
					<textarea class="form-control" rows="5" id="lemmaSummary" placeholder="" style="width: 90%;"></textarea> 
				</div>
				<div class="form-group form-inline">
					<label for="username" >Username：</label>
					<input type="text" class="form-control" id="username" style="width: 50%;" readonly>
				</div>
				<div class="form-group form-inline">
					<label for="remark" >Remark：</label>
					<input type="text" class="form-control" id="remark" style="width: 50%;" readonly>
				</div>
            
            
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel">取消</button>
                <button type="button" class="btn btn-primary" id="submit">确认</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript">

</script>
</html>