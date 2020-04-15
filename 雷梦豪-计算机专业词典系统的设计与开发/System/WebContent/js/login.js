/**
 * 
 */
$(function(){
	$("#admin").click(function(){
		var checkAdmin=$("#admin").prop("checked");
		if(checkAdmin){
		    var checkUser=$("#user").prop("checked");
		    if(checkUser){
		    	$('#user').prop("checked", false)
		    }
		 };
	})
	$("#user").click(function(){
		var checkUser=$("#user").prop("checked");
		if(checkUser){
		    var checkAdmin=$("#admin").prop("checked");
		    if(checkAdmin){
		    	$('#admin').prop("checked", false)
		    }
		 };
	})

	$("#login").click(function(){
		var username=$("#inputUsername").val(); 
		var password=$("#inputPassword").val(); 
		var checkAdmin=$("#admin").prop("checked");
		var action;
		if(username==""||username==null){
			$(".usern").css('display','inline');
			return
		}
		if(password==""||password==null){
			$(".pwd").css('display','inline');
			return
		}
		if(checkAdmin){
			action="admin";
			$.ajax({
				url:"login",
				//async:true,
				type:"POST",
				data:{"action":action,"username":username,"password":password},
				success:function(result,status){
					if(result=="true"){ 
						window.location.href = "admin01.jsp";
					}else{
						alert("用户名或密码错误");
					}
				}
			})
		}else{
			action="user";
			$.ajax({
				url:"login",
				//async:true,
				type:"POST",
				data:{"action":action,"username":username,"password":password},
				success:function(result,status){
					if(result=="true"){ 
						window.location.href = "index2.jsp";
					}else{
						alert("用户名或密码错误");
					}
				}
			})
		}
		
	})
})




