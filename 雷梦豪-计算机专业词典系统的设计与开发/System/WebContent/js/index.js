/**
 * 
 */
	

$(function(){
	var alldata=null;
	//查询按钮
	$("#searchBtn").click(function search(){
		var key=$("#searchInput").val(); 
		
		if(key==null ||key==""){
			alert("请输入查询的数据！");
			return;
		}
		$.post("vocabulary",
			    {"action":"getSearch","key":key},
			    function(data,status){
			       var d=jQuery.parseJSON(data);
			       alldata=d;
			       $("#result").html("");
			       for(var i=0;i<d.length;i++){
			       		$("#result").append("<li id='id"+d[i].id+"' style='position:relative;'><a>"+d[i].abbreviation+"</a><a style='position:absolute;left:100px;text-overflow:ellipsis;white-space:nowrap'>"+d[i].fullWords+"</a></li>");
			       		$("#id"+d[i].id).data("abbreviation",d[i].abbreviation);
			       		$("#id"+d[i].id).data("fullWords",d[i].fullWords);
			       		$("#id"+d[i].id).data("specificMeanning",d[i].specificMeanning);
			       		$("#id"+d[i].id).data("lemmaSummary",d[i].lemmaSummary); 
			       		$("#id"+d[i].id).data("remark",d[i].remark);
			       		$("#id"+d[i].id).data("data",d[i]);
			       }
			       if(d.length!=0){
			    	   $("#lemma-Summary").html("");
			    	   $("#lemma-Summary").append("<h3>"+d[0].abbreviation+"</h3><h4>"+d[0].fullWords+"</h4><h4>"+d[0].specificMeanning+"</h4><p style='text-indent: 2em;'>"+d[0].lemmaSummary+"</p>");
			    	   if(d[0].remark){
			    		   $("#remark").html("注："+d[0].remark);
			    	   }else{
			    		   $("#remark").html("");
			    	   }
			    	   
			       }else{
			    	   $("#result").html("<p class='text-error'><strong>查无结果</strong></p>");
			    	   $("#lemma-Summary").html("<p class='text-error'><strong>查无结果</strong></p>");
			       }
			    });
	});
	//a标签
	$("#result").on("click","li",function(){
		var abbreviation=$(this).data("abbreviation");
		var fullWords=$(this).data("fullWords");
		var specificMeanning=$(this).data("specificMeanning");
		var lemmaSummary=$(this).data("lemmaSummary");
		var remark=$(this).data("remark");
		var data=$(this).data("data");
		$("#lemma-Summary").html("");
		$("#lemma-Summary").append("<h3>"+abbreviation+"</h3><h4>"+fullWords+"</h4><h4>"+specificMeanning+"</h4><p style='text-indent: 2em;'>"+lemmaSummary+"</p>");
		if(remark){
			$("#remark").html("注："+remark);
		}else{
			$("#remark").html("");
		}
		
		$("#error").data("errorData",data);
	
	});
	
	$("#error").click(function(){
		var errorData=$(this).data("errorData");
		if(errorData!=null){
			$("#abbreviation").val(errorData.abbreviation);
			$("#fullWords").val(errorData.fullWords);
			$("#specificMeanning").val(errorData.specificMeanning);
			$("#lemmaSummary").val(errorData.lemmaSummary);
		}	
	});
	
	$("#postError").click(function(){
		//var v_id=$("#error").data("errorData").id;
		var abbreviation=$("#abbreviation").val();
		var fullWords=$("#fullWords").val();
		var specificMeanning=$("#specificMeanning").val();
		var lemmaSummary=$("#lemmaSummary").val();
		
		$.post("feedback",
				{"action":"postError",/*"v_id":v_id,*/"abbreviation":abbreviation,
			"fullWords":fullWords,"specificMeanning":specificMeanning,"lemmaSummary":lemmaSummary},
				function(data,status){

				if(data=="1"){
					alert("提交成功!");
					$('#myModal').modal('hide');
				}
				else if(data=="10"){
					alert("请先登录！")
				}
				else{
					alert("提交失败!");
					$('#myModal').modal('hide');
				}
		})
		
		
	});
	
}
);