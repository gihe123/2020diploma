/**
 * 
 */
$(function(){
	
	function createXHR(){
		if(window.XMLHttpRequest){
			return new XMLHttpRequest();
		}else if(window.ActiveObject){
			try{
				return new ActiveObject("Msxml2.XMLHTTP");
			}catch(e){
				try{
					return new ActiveObject("Microsoft.XMLHTTP");
				}catch(e){
					alert("xhr对象创建失败！");
					return;
				}
			}
		}
	}
	$("#searchBtn").click(function search(){
		var xhr=createXHR();
		var url="index";
		var key=$("#searchInput").val(); 
		if(key==null ||key==""){
			alert("请输入查询的数据！");
			return;
		}
		var payload="key="+key;
		xhr.open("POST",url,"true");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.onreadystatechange=function(){
			processResponse(xhr);
		};
		xhr.send(payload);
	})
	
	$("#Btn").click(function searchall(){
		
		var xhr=createXHR();
		var url="index";
		var key=$("#searchInput").val(); 
		if(key==null ||key==""){
			alert("请输入查询的数据！");
			return;
		}
		var payload="key="+key+"&select=all";
		xhr.open("POST",url,"true");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.onreadystatechange=function(){
			processResponse(xhr);
		};
		xhr.send(payload);
	})
	
	
	
	
	function processResponse(xhr){
		if(xhr.readyState==4&&xhr.status==200){
			//console.log(xhr.responseText);
			var d=xhr.responseText.parseJSON();
			console.log(d);
			$("#result").html("");
			for(var i=0;i<d.length;i++){
				$("#result").append("<li><a href='javascript:void(0)' id='id"+d[i].id+"'>"+d[i].abbreviation+"</a>&nbsp;&nbsp;&nbsp;"+d[i].fullWords+"</li>");
				//$("#id"+d[i].id).attr("fullWords",d[i].fullWords);
				//$("#id"+d[i].id).attr("specificMeanning",d[i].specificMeanning);
				//$("#id"+d[i].id).attr("lemmaSummary",d[i].lemmaSummary); 
				$("#id"+d[i].id).data("fullWords",d[i].fullWords);
				$("#id"+d[i].id).data("specificMeanning",d[i].specificMeanning);
				$("#id"+d[i].id).data("lemmaSummary",d[i].lemmaSummary); 
		   }
			
		}
	}
})

