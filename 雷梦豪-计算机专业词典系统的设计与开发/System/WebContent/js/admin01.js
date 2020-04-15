/**
 * 
 */
$(function(){
	$.post("feedback",
	    {"action":"getFeedbackData"},
	    function(data,status){
	       var Data=jQuery.parseJSON(data);
	       $('#feedbackTable').bootstrapTable({
				pagination: true, 
				pageSize: 5,
				columns: [{
					field: 'f_id',
					title: 'ID'}, 
					{
					field: 'v_id',
					title: 'V_ID'}, 
					{
					field: 'f_abbreviation',
					title: 'Abbreviation'},
					{
					field: 'f_fullWords',
					title: 'fullWords'},
					{
					field: 'f_specificMeanning',
					title: 'SpecificMeanning'},
					{
					field: 'f_lemmaSummary',
					title: 'LemmaSummary'},
					{
					field: 'f_username',
					title: 'Username'},
					{
					field: 'f_remark',
					title: 'Remark'},
					{
						field: 'edit',
						title: 'edit'}
					],
	    		   data: formatData(Data)
		});
	    });
	var formatData = function (data) {
		var l = [];
		for (var i = 0; i < data.length; i++) {
			var m = data[i]
			var d = {
				'f_id': m.f_id,
				'v_id': m.v_id,
				'f_abbreviation': m.f_abbreviation,
				'f_fullWords': m.f_fullWords,
				'f_specificMeanning': m.f_specificMeanning,
				'f_lemmaSummary': m.f_lemmaSummary,
				'f_username': m.f_username,
				'f_remark': m.f_remark,
				'edit':"<a><span class='glyphicon glyphicon-edit' id='edit"+m.f_id+"'></span></a>&nbsp;&nbsp;<a><span class='glyphicon glyphicon-ok' id='pass"+m.f_id+"'></span></a>&nbsp;&nbsp;<a><span class='glyphicon glyphicon-remove' id='delete"+m.f_id+"'></span></a>"
			}

			l.push(d)
		}
		return l
	};
	$("#feedbackList").on("click","span[id^='edit']",function(){
		var This=$(this).parent().parent().parent().find("td");
		var f_id=This.eq(0).text();
		var v_id=This.eq(1).text();
		var f_abbreviation=This.eq(2).text();
		var f_fullWords=This.eq(3).text();
		var f_specificMeanning=This.eq(4).text();
		var f_lemmaSummary=This.eq(5).text();
		var f_username=This.eq(6).text();
		var f_remark=This.eq(7).text();
		$("#v_id").val(v_id);
		$("#v_id").data("f_id",f_id);
		$("#abbreviation").val(f_abbreviation);
		$("#fullWords").val(f_fullWords);
		$("#specificMeanning").val(f_specificMeanning);
		$("#lemmaSummary").val(f_lemmaSummary);
		$("#username").val(f_username);
		$("#remark").val(f_remark);
		$("#editModal").modal("show");  
		//alert(v_id);
	});
	
	$("#submit").click(function(){
		var f_id=$("#v_id").data("f_id");
		var v_id=$("#v_id").val();
		var f_abbreviation=$("#abbreviation").val();
		var f_fullWords=$("#fullWords").val();
		var f_specificMeanning=$("#specificMeanning").val();
		var f_lemmaSummary=$("#lemmaSummary").val();
		var f_username=$("#username").val();
		var f_remark=$("#remark").val();//判断是add还是notfound或error
		
		$.post("feedback",
			    {
					"action":"addToVocabulary",
					"f_id":f_id,
					"v_id":v_id,
					"abbreviation":f_abbreviation,
					"fullWords":f_fullWords,
					"specificMeanning":f_specificMeanning,
					"lemmaSummary":f_lemmaSummary,
					"remark":f_username,
					"f_remark":f_remark
				},
			    function(data,status){
					if(data!="0"){
						alert("成功")
						$("#editModal").modal("hide");
						window.location.reload();
					}
			    });
		
		
		
	});
	$("#feedbackList").on("click","span[id^='delete']",function(){
		var This=$(this).parent().parent().parent().find("td");
		var f_id=This.eq(0).text();
		$.post("feedback",
			    {
					"action":"delete",
					"f_id":f_id,
				},
			    function(data,status){
					if(data!="0"){
						alert("删除成功")
						window.location.reload();
					}
			    });
	});
	
	$("#feedbackList").on("click","span[id^='pass']",function(){
		var This=$(this).parent().parent().parent().find("td");
		var f_id=This.eq(0).text();
		var v_id=This.eq(1).text();
		var f_abbreviation=This.eq(2).text();
		var f_fullWords=This.eq(3).text();
		var f_specificMeanning=This.eq(4).text();
		var f_lemmaSummary=This.eq(5).text();
		var f_username=This.eq(6).text();
		var f_remark=This.eq(7).text();
		$.post("feedback",
			    {
					"action":"addToVocabulary",
					"f_id":f_id,
					"v_id":v_id,
					"abbreviation":f_abbreviation,
					"fullWords":f_fullWords,
					"specificMeanning":f_specificMeanning,
					"lemmaSummary":f_lemmaSummary,
					"remark":f_username,
					"f_remark":f_remark
				},
			    function(data,status){
					if(data!="0"){
						alert("成功")
						window.location.reload();
					}
			    });
		
	});
	
});