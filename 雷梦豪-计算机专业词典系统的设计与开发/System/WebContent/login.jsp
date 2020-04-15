<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script src="js/login.js"></script>
<script src="js/checkLoginStatus.js"></script>
</head>
<body>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="img/1.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="img/2.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<ul class="nav nav-pills pull-right">
					<li ><a href="index2.jsp">首页</a></li>
					
					<li id="userAdd"><a href="#">新增词汇</a></li>
					<li><a href="userFeedback.jsp">查无反馈</a></li>
					<li class="active"><a href="login.jsp">登陆</a></li>
				</ul>
			</div>     
		</div>
	    <div class="row">
	        <div class="col-md-6 col-md-offset-3 form">
	            
	                <span class="heading">用户登录</span>
	                <div class="form-group">
	                    <input type="text" class="form-control" id="inputUsername" placeholder="用户名或电子邮箱">
	                    <span class="text-muted usern">*用户名不能为空</span>
	                </div>
	                <div class="form-group help">
	                    <input type="password" class="form-control" id="inputPassword" placeholder="密　码">  
	                    <span class="text-muted pwd">*密码不能为空</span>
	                </div>
	                <div class="form-group form-login">
	                    <div class="main-checkbox">
	                        <input type="checkbox" value="None" id="admin" name="check"/>
	                        <label for="admin"></label>
	                    </div>
	                    <span class="text">管理员</span> 
	                    <div class="main-checkbox">
	                        <input type="checkbox" value="None" id="user" name="check" checked/>
	                        <label for="user"></label>
	                    </div>
	                    <span class="text">用户</span>
	                    <button type="submit" class="btn btn-primary" id="login">登录</button>
	                </div>
	                <a href="register.jsp" class="pull-right">没有账号？去注册</a>
	            
	        </div>
	    </div>
	</div>
</body>

</html>