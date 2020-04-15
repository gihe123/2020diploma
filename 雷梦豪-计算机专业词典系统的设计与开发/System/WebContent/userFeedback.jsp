<%@ page language="java" import="com.bean.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>反馈</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.js"></script>
<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/checkLoginStatus.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-10">
			<ul class="nav nav-pills pull-right">
				<li><a href="index2.jsp">首页</a></li>
				<li id="userAdd"><a href="#">新增词汇</a></li>
				<li class="active" id="userFeedback"><a href="#">查无反馈</a></li>
				<c:if test="${empty sessionScope.currentUser }">
					<li id="login"><a href="login.jsp">登陆</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.currentUser }">
					<li id="user" data-toggle="modal" data-target="#changePassword"><a>欢迎您，${currentUser.username}</a></li>
					<li id="exit"><a href="#">退出</a></li>
				</c:if>
			</ul>
		</div>     
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
		<div style="margin-top:40px;">
				<h3>查不到？反馈给管理员吧！</h3>
		</div>
		</div>
	</div>
	<div class="row">	
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<p>查不到：</p>
			<div class="form-group">
				<label for="abbreviation">要查询的缩略词：</label>
				<input type="text" class="form-control" id="abbreviation1" placeholder="缩略词">
			</div>
			<div class="form-group">
				<label for="fullWords">完整英文词汇：</label>
				<input type="text" class="form-control" id="fullWords1" placeholder="英文词汇">
				
			</div>	
			
			<div class="form-group">
				<label for="specificMeanning">中文解释：</label>
				<input type="text" class="form-control" id="specificMeanning1" placeholder="中文解释">
			</div>
			
			<button class="btn btn-primary pull-right" id="postNotFoundBtn">提交</button>
			<button type="button" class="btn btn-default pull-right" id="reset" style="margin-right:10px;">重置</button>
		</div>
	</div>
</div>
<div class="modal fade" id="alertdiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
            	<button type="password" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            	<p>缩略词、英文词汇、中文解释不能同时为空！</p>
            </div>
        </div>
    </div>
</div>

<%@ include file="modal.jsp" %>
</body>
<script type="text/javascript">
$(function(){
	$("#reset").click(function(){
		$("#abbreviation1,#fullWords1,#specificMeanning1").val("");
		//$("#specificMeanning").css('border-color','red');
	});
	$("#postNotFoundBtn").click(function(){
		var abbreviation=$("#abbreviation1").val();
		var fullWords=$("#fullWords1").val();
		var specificMeanning=$("#specificMeanning1").val();
		if((abbreviation==""||abbreviation==null)&&(fullWords==""||fullWords==null)&&(specificMeanning==""||specificMeanning==null)){
			$('#alertdiv').modal('show');
			return;
		}
		$.ajax({
			url:"feedback",
			async:true,
			data:{
				"action":"notFound",
				"abbreviation":abbreviation,
				"fullWords":fullWords,
				"specificMeanning":specificMeanning
				},
			success:function(result,status){
				if(result=="1"){
					alert("提交成功!");
					//window.location.href = "index2.jsp";
					window.location.reload();
					}
				else{
					alert("提交失败!");
					window.location.reload();
				}
			}
		});
	});
})
</script>
</html>