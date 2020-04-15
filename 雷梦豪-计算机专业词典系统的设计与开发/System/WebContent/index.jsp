<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>计算机词典</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) --><!--
<script src="jquery/jquery-3.2.1.js"></script>
 包括所有已编译的插件 
<script src="bootstrap/js/bootstrap.min.js"></script>-->
<script src="js/ajax.js"></script>
<script src="js/json.js"></script>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="#">首页</a></li>
				<li><a href="login.jsp">登陆</a></li>
				<li><a href="#">反馈报错</a></li>
			</ul>
		</div>     
	</div>
	<div class="row">
		<!--<div class="col-md-4"></div>-->
		<div class="col-md-12">
			<form role="form">
				<div class="form-group form-inline">
					<label for="searchInput">名称：</label>
					<input type="text" class="form-control" id="searchInput" placeholder="请输入查询的信息">
					<button type="submit" class="btn btn-default" onclick=search()>搜索</button>
				</div>	
			</form>
		</div>     
	</div>
	<div class="row">
		<div class="col-md-5">
			<div style="border:1px solid #ccc;height:200px" id="result">搜索结果区</div>
		</div>
		<div class="col-md-7"><div style="border:1px solid #ccc;height:200px" id="info">详细信息区</div></div>
	</div>

</div>
</body>
<script type="text/javascript">
function search(){
	var xhr=createXHR();
	var url="index";
	var key=document.getElementById("searchInput").value;
	if(key==null|| key==""){
		alert("输入为空！");
		return;
	}
	//var playload = "key="+key;
	xhr.onreadystatechange=function(){
		processResponse(xhr);
	}
	xhr.open("POST",url,"true");
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send("key="+key);
	
}
function processResponse(xhr){
	if(xhr.readyState==4 && xhr.status==200){
		var vocabulary=xhr.responseText.parseJSON();
		//console.log(vocabulary);
		alert(vocabulary);
		var result=document.getElementById("result");
		result.innerHTML="";
		for(var i=0;i<vocabulary.length;i++){
			result.innerHTML+="<li><a onclick=clickLi(this) id='id"+vocabulary[i].id+"'>"+vocabulary[i].abbreviation+"</a></li>";
			var a=document.getElementById('id'+vocabulary[i].id)
			a.setAttribute("fullWords",vocabulary[i].fullWords);
			a.setAttribute("specificMeanning",vocabulary[i].specificMeanning);
			a.setAttribute("lemmaSummary",vocabulary[i].lemmaSummary); 
		}
		
	}
}
function clickLi(obj){
	//alert(obj.id);
	var fullWords=obj.getAttribute("fullWords");
	var specificMeanning=obj.getAttribute("specificMeanning");
	var lemmaSummary=obj.getAttribute("lemmaSummary"); 
	//document.getElementById("lemma-Summary").innerHTML("");
	var info=document.getElementById("info")
	var infos="<p>"+fullWords+"&nbsp;&nbsp;&nbsp;"+specificMeanning+"<br>"+lemmaSummary+"</p>";
	console.log(infos);
	info.innerHTML=infos;
	
}
</script>
</html>