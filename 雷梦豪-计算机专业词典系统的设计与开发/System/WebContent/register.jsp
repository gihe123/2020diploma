<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
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
					<li class="active"><a href="login.jsp">登陆</a></li>
					<li id="userAdd"><a href="#">新增词汇</a></li>
					<li><a href="userFeedback.jsp">查无反馈</a></li>
				</ul>
			</div>     
		</div>
	    <div class="row">
	        <div class="col-md-6 col-md-offset-3 form">
	            
	                <span class="heading">用户注册</span>
	                <div class="form-group">
	                    <input type="text" class="form-control" id="inputUsername" placeholder="用户名或电子邮箱">
	                    <span class="text-muted usern">*用户名不能为空</span>
	                </div>
	                <div class="form-group help">
	                    <input type="password" class="form-control" id="inputPassword" placeholder="密　码">  
	                    <span class="text-muted pwd">*密码不能为空</span>
	                </div>
	                <div class="form-group form-inline">
   						<input type = "text" class="form-control" id ="input" style="width:60%;" placeholder="验证码"> 
   						<input type = "button" class="form-control" id="code" style="width:30%;color:blue; margin-left:9%;">
    				</div>
	                <div class="form-group form-login">
	 	            	<button type="submit" class="btn btn-primary" id="register">注册</button>
	 	            	
						<button type="submit" class="btn btn-default" id="cancel">取消</button>
	                </div>
	            	<a href="login.jsp" class="pull-right">已有账号？去登录</a>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	function change() {
		code = $("#code");
		// 验证码组成库
		var arrays = new Array('1', '2', '3', '4', '5', '6', '7', '8', '9','0', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w','x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U','V', 'W', 'X', 'Y', 'Z');
		codes = '';// 重新初始化验证码
		for (var i = 0; i < 4; i++) {
			// 随机获取一个数组的下标
			var r = parseInt(Math.random() * arrays.length);
			codes += arrays[r];
		}
		code.val(codes);
	}
	
	change();
	code.click(change);
	//单击验证
	$("#register").click(function() {
		var inputCode = $("#input").val().toUpperCase(); //取得输入的验证码并转化为大写 
		console.log(inputCode);
		if (inputCode.length == 0) { //若输入的验证码长度为0
			alert("请输入验证码！"); //则弹出请输入验证码
		} else if (inputCode != codes.toUpperCase()) { //若输入的验证码与产生的验证码不一致时
			alert("验证码错误!请重新输入"); //则弹出验证码输入错误
			change();//刷新验证码
			$("#input").val("");//清空文本框
		} else { //输入正确时
			//document.getElementById("myform").submit();
			
			var username=$("#inputUsername").val();
			var password=$("#inputPassword").val();
			if(username==null||username==""||password==null||password==""){
				alert("用户名和密码不能为空");
				change();
				$("#input").val("");
				return;
			}
			else{
				$.ajax({
					url:"login",
					type:"POST",
					data:{"action":"register","username":username,"password":password},
					success:function(result,status){
						if(result == "false")
							alert("系统错误")
						else if(result=="true"){ 
							alert("注册成功！")
							window.location.href = "login.jsp";
						}
					}
				})
				
			}
		}
	});
	
})



</script>
</html>