/**
 * 
 */
	

$(function(){
	$("#getInfoBtn").click(function(){
		var info=$("#infoInput").val();
		if(info==null || info==""){
			alert("查询数据不能为空！");
			return;
		}
		$.post("vocabulary",
			    {"action":"getSearch","key":info},
			    function(data,status){
			    	var Data=jQuery.parseJSON(data);
			    	$("#resultTable").bootstrapTable('load', formatData(Data));
			    	$("#resultTable").data("data",Data);
				}
		);
		
		
	});
	$('#resultTable').bootstrapTable({
		pagination: true, 
		pageSize: 3,
		pageList: [3,5,7,10,25,50,100],
		columns: [
				{
				field: 'id',
				title: 'ID'}, 
				{
				field: 'abbreviation',
				title: 'Abbreviation'},
				{
				field: 'fullWords',
				title: 'fullWords'},
				{
				field: 'specificMeanning',
				title: 'SpecificMeanning'},
				{
				field: 'lemmaSummary',
				title: 'LemmaSummary'},
				{
				field: 'remark',
				title: 'Remark'},
				{
				field: 'edit',
				title: 'Edit'}
				]
	});
	var formatData = function (datas) {
		var dataList = [];
		for (var i = 0; i < datas.length; i++) {
			var m = datas[i]
			var data = {
				'id': m.id,
				'abbreviation': m.abbreviation,
				'fullWords': m.fullWords,
				'specificMeanning': m.specificMeanning,
				'lemmaSummary': m.lemmaSummary,
				'remark': m.remark,
				'edit':'<span class="glyphicon glyphicon-edit edit"></span><span class="glyphicon glyphicon-remove remove"style="margin-left:5px;"></span>',
			}
	
			dataList.push(data)
		}
		return dataList;
	};

	
	$("#resultTable").on("click",".edit",function(){
		var This = $(this).parent().parent().find("td");
		var id = This.eq(0).text();
		
		var abbreviation = This.eq(1).text();
		var fullWords = This.eq(2).text();
		var specificMeanning = This.eq(3).text();
		var lemmaSummary = This.eq(4).text();
		var remark=This.eq(5).text();
		$("#abbreviation").val(abbreviation);
		$("#fullWords").val(fullWords);
		$("#specificMeanning").val(specificMeanning);
		$("#lemmaSummary").val(lemmaSummary);
		$("#remark").val(remark);
		$("#modify").data("Id",id);
		$("#myModalLabel").html("修改");
		$("#modify").css("display","inline");
		$("#add").css("display","none");
		$("#editModal").modal("show");

	});
	$("#resultTable").on("click",".remove",function(){
		var This = $(this).parent().parent().find("td");
		var id = This.eq(0).text();
		var Data=$("#resultTable").data("data");
		$.each(Data,function(n,value) {  
            if (value.id==id){  
            	Data.splice(n,1);
            }
        });
		$.post("vocabulary",
			    {"action":"adminDelete","id":id},
			    function(data,status){
			    	if(data!="0"){
			    		alert("删除成功");
			    		$("#resultTable").bootstrapTable('load', formatData(Data));
			    	}else{
			    		alert("删除失败")
			    	}
			    	
				}
		);

	});
	$("#adminAdd").click(function(){
		$("#abbreviation").val("");
		$("#fullWords").val("");
		$("#specificMeanning").val("");
		$("#lemmaSummary").val("");
		$("#remark").val("");
		$("#myModalLabel").html("添加");
		$("#add").css("display","inline");
		$("#modify").css("display","none");
		$("#editModal").modal("show");
	
	});
	
	$("#add").click(function(){
		var abbreviation = $("#abbreviation").val();
		var fullWords = $("#fullWords").val();
		var specificMeanning = $("#specificMeanning").val();
		var lemmaSummary = $("#lemmaSummary").val();
		var remark = $("#remark").val();
		if(abbreviation==null||abbreviation==""||fullWords==null||fullWords==""||specificMeanning==null||specificMeanning==""){
			alert("缩略词、完整英文词汇、中文解释不能为空");
			return;
		}
		
		$.ajax({
			url:"vocabulary",
			data:{
				"action":"adminAdd",
				"abbreviation":abbreviation,
				"fullWords":fullWords,
				"specificMeanning":specificMeanning,
				"lemmaSummary":lemmaSummary,
				"remark":remark
			},
			success:function(result,status){
				alert("添加成功");
				//var d=jQuery.parseJSON(result);
				/*if(result=="1"){
					alert("添加成功");
					$("#editModal").modal("hide");
				}else{
					alert("添加成功");
				}
				*/
			}
		});
	});
	
	$("#modify").click(function(){
		var id=$(this).data("Id");
		var abbreviation = $("#abbreviation").val();
		var fullWords = $("#fullWords").val();
		var specificMeanning = $("#specificMeanning").val();
		var lemmaSummary = $("#lemmaSummary").val();
		var remark = $("#remark").val();
		if(id==null||id==""){
			alert("未选择要修改的信息");
			return;
		}
		$.ajax({
			url:"vocabulary",
			data:{
				"action":"adminModify",
				"id":id,
				"abbreviation":abbreviation,
				"fullWords":fullWords,
				"specificMeanning":specificMeanning,
				"lemmaSummary":lemmaSummary,
				"remark":remark
			},
			success:function(result,status){
				//var d=jQuery.parseJSON(result);
				if(result=="1"){
					alert("修改成功");
					$("#editModal").modal("hide");
				}else{
					alert("修改失败");
				}
				
			}
			 

		});


	});
	
	
	
	
});