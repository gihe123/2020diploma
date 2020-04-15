/**
 * 
 */
$(function(){
	$("#userAdd").click(function(){
		$.ajax({
			url:"login",
			type:"POST",
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			data:{"action":"checkStatus"},
			success:function(result,status){
				if(result=="true"){ 
					window.location.href = "userAdd.jsp";
				}else{
					alert("请先登陆！");
			}
		}})
	
	});
	$("#userFeedback").click(function(){
		$.ajax({
			url:"login",
			type:"POST",
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			data:{"action":"checkStatus"},
			success:function(result,status){
				if(result=="true"){ 
					window.location.href = "userFeedback.jsp";
				}else{
					alert("请先登陆！");
			}
		}})
	
	});
	$("#exit").click(function(){
		$.ajax({
			url:"login",
			type:"POST",
			data:{"action":"userExit"},
			success:function(result,status){
				if(result=="true"){ 
					window.location.href = "index2.jsp";
				}else{
					alert("请先登陆！");
			}
		}})
	});
	$("#adminExit").click(function(){
		$.ajax({
			url:"login",
			type:"POST",
			data:{"action":"adminExit"},
			success:function(result,status){
				if(result=="true"){ 
					window.location.href = "login.jsp";
				}else{
					alert("请先登陆！");
			}
		}})
	});
	$("#changePwd").click(function(){
		//alert("修改密码");
		var oldPwd=$("#oldPwd").val();
		var newPwd=$("#newPwd").val();
		var pwd=$("#pwd").val();
		if(newPwd!=null||newPwd!=""){
			if(newPwd!=pwd){
				$("#different").css('display','inline');
				return;
			}
			$.ajax({
				url:"login",
				type:"POST",
				data:{"action":"modifyPwd","oldPwd":oldPwd,"newPwd":newPwd},
				success:function(result,status){
					if(result=="1"){ 
						alert("修改成功！");
						window.location.href = "index2.jsp";
					}else if(result=="2"){
						alert("原密码不正确！");
						
				}
			}});
			//$('#changePassword').modal('hide');
		}
		
	});
	$("#changePwdCancel").click(function(){
		//alert("修改密码");
		$("#oldPwd").val("");
		$("#newPwd").val("");
		$("#pwd").val("");
		$("#different").css('display','none');
	});
})