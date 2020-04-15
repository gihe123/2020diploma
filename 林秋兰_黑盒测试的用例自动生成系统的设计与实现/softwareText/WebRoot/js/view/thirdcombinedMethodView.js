var viewPort ;

var Panel =Ext.create("Ext.panel.Panel",{
	id:"panel",
	autoScroll :true ,
	region:"center",
	title:'<span style="font-size:15px">用例表</span>',
	tbar:[{ //历史查询方法重写
		    xtype:"combobox",
		    id:"comboid",
		    store:comboStore,
		    fieldLabel:"选择历史用例",
		    editable: false,	
		    valueField:"date",//这个是隐藏的变量
	  	   	displayField:"flag",//这个是显示的变量
	  	   	emptyText:"--选择历史用例--",
	  	    queryMode: "local",
	  	    listeners:{
	  	   		select:function(combo,record,index){		  	   	
		  	   	//	Ext.example.msg(record[0].data.date,combo.value+"--"+combo.getValue(),true,50000);
	  	   		detailColumn = [];
	  	   		detailModel=[];
	  	   			Ext.Ajax.request({
					url : contextPath + '/ThirdCombinedMethodAction!searchCaseColumn.action',
					method : 'POST',
					timeout : 30000,
					type : 'ajax',	    					// form:"ajaxform"
					params : {
						flag : combo.getValue()
					},
					success:function(resp){
						var text = resp.responseText;   
						detailColumn = Ext.JSON.decode(text).column;
					    detailModel  = Ext.JSON.decode(text).model;	        					  
						showhistoryGrid(combo.getValue());
						 northformPanel.getForm().reset();
						Ext.example.msg("提示","数据显示成功",true,500);
			//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
					},
					failure:function(resp)
					{
						Ext.example.msg("提示","数据显示失败",true,500);
					}
				})
	  	   		}
	  	   	}
		}],
	items:[detailPanel]
})


var detailPanel = Ext.create('Ext.grid.Panel', {
	id : 'grid',
layout : 'fit',
 store : detailStore,
 forceFit: true, //列表宽度自适应 
columns : detailColumn,
selType: 'cellmodel',
selModel:'rowmodel',
autoScroll :true ,
tbar:[{
	xtype : 'button',
	width : 80,
	height : 25,
	id : 'save',
	width : 80,
	enableToggle:false,
	height : 25,
	//icon:''+contextPath+'/images/export_excel.png',
	text : '保存',
	icon:''+contextPath+'/images/save.gif',

	listeners : {
		click : function() {
			var store = this.up("grid").getStore();
			var count = store.getCount();
			var count1 = store.getCount();
			for(var i=0;i<count1;i++){
				if(store.getAt(i).data.tab==1){
					count--;
				}
			}
			var records = new Array(count);
			var record;
			var index=0;
			for (var i = 0; i < count1; i++) {
				//tab =1 为保存了
				
				// 获取前台数据存储到后台去
				record = store.getAt(i);
				if(record.data.flag==''||record.data.flag==null)
				{
					record.data.flag=store.getAt(0).data.flag;
				}
				if(record.data.tab==null||record.data.tab==0||record.data.tab==''){
					record.data.tab=1;
				
					var obj = {
							'id':record.data.id,
							'user' : record.data.user,
							'flag':record.data.flag,
							'tab':record.data.tab
					}
					records[index++] = JSON.stringify(obj);
					if(index>=count){
						break;
					}
				}
				// 可以把对象存入数组中并且识别到
				// 后台不会再识别到[objcet,object]这样的
				// 而是具体数值
				
			}
//			window.location.href=contextPath + '/BoundaryMethodAction!outputEquivalentExcel.action'
			Ext.Ajax.request({
				url : contextPath + '/ThirdCombinedMethodAction!saveOrUpdateCombined.action',
				method : 'POST',
				timeout : 30000,
				type : 'ajax',	    					// form:"ajaxform"
				params : {
					record : records
				},
				success:function(resp){
					var text = Ext.decode(resp.responseText);   				
					var store = Ext.getCmp("grid").getStore();
					var count = store.getCount();
					var index =0;
					for(var i=0;i<count;i++){
						if(store.getAt(i).data.id!=null||store.getAt(i).id!=""){
						//	store.getAt
							var record =store.getAt(i);
							record.data.id=text.root[index++].id;
							if(index>=text.totalcount){
								break;
							}								
						}
					}
					comboStore.reload();	
					Ext.example.msg("提示","数据正在保存",true,500);
		//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
				},
				failure:function(resp)
				{
					Ext.example.msg("提示","数据已经保存了或者未生成数据",true,500);
				}
			})
		}
	}
		},'-',{
			xtype : 'button',
			id : 'mark',
			width : 80,
			height : 25,
			icon:''+contextPath+'/images/tip.png',
			text : '标记',
			listeners : {
				click : function() {
					var store = this.up("grid").getStore();	
			
					var flag ="thirdcombined,"+store.getAt(0).data.flag;
					var count =store.getCount();
					for(var i=0;i<count;i++){
						if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
							Ext.example.msg("提示","请先保存再标记",true,500);
							return ;
						}
					}
					Ext.Ajax.request({
						url : contextPath + '/UsefulCaseAction!saveOrUpdateUsefulCase.action',
						method : 'POST',
						timeout : 30000,
						type : 'ajax',	    					// form:"ajaxform"
						params : {
							
							flag:flag
						},
						success:function(resp){
							
						
							//
							var data =Ext.decode(resp.responseText);
							if(data.msg==""){	    								    						
								Ext.example.msg("提示","数据标记成功",true,500);
							}else{
								Ext.example.msg("提示","数据已经标记过",true);
							}
				//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
						},
						failure:function(resp)
						{
							Ext.example.msg("提示","数据标记失败",true,500);
						}
					})
						// 可以把对象存入数组中并且识别到
						// 后台不会再识别到[objcet,object]这样的
						// 而是具体数值			
			
					//		window.location.href=contextPath + '/BoundaryMethodAction!outputBoundaryExcel.action?record='+record.data.flag;
					
				}
			}
		    	},'-',{
	xtype : 'button',
	id : 'outputexcel',
	width : 80,
	height : 25,
	icon:''+contextPath+'/images/export_excel.png',
	text : '导出excel',
	listeners : {
		click : function() {
			var store = this.up("grid").getStore();	
			var record =store.getAt(0);
			var count =store.getCount();
			for(var i=0;i<count;i++){
				if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
					Ext.example.msg("提示","请先保存再下载",true,500);
					return ;
				}
			}
				// 可以把对象存入数组中并且识别到
				// 后台不会再识别到[objcet,object]这样的
				// 而是具体数值			
			window.location.href=contextPath + '/ThirdCombinedMethodAction!outputCombinedExcel.action?record='+record.data.flag;
			
		}
	}
		} ],
stripeRows : true,			
columnLines: true,
autoScroll :true ,
enableColumnMove :true,			
enableColumnResize : true,			
trackMouseOver :true		

})

var northformPanel =  Ext.create("Ext.form.FormPanel",{
	region:"north",
	title:"组合测试法条件数据选择",
	//collapsible:true,
	labelWidth:80,
	collapsed:false,
	frame:true,
	border:false,
	items:[{
        xtype:'fieldset',
        title: '用例数据设置',
        collapsible:true,
        collapsed:false,
        autoHeight:true,
        layout:'column',
        items :[{layout:'form',columnWidth:0.6,border:false,bodyStyle:'background-color: #dfe9f6;',
        	items:[{
        	    	labelAlign:'right',
					xtype : 'textarea',
					name : 'msg',
					id:'data',
					emptyText : "例：\n0：红，绿，蓝\n1:酸，甜，苦\n2:大，中， 小",
					grow : true,
					width: '100%'
					
				
        	       }]
			  } ]

	},{
        xtype:'fieldset',
        title: '用例条件设置',
        collapsible:true,
        collapsed:false,
        autoHeight:true,
        layout:'column',
        items :[{layout:'form',columnWidth:0.5,border:false,bodyStyle:'background-color: #dfe9f6;',
        	items:[{
        	    	labelAlign:'right',
					xtype : 'textarea',
					id:"condition",
					name : 'selection',
					emptyText : "例：\nif [0] in {红,绿} then [1] not in{甜}\nif [0] notin {蓝} then [1] in {甜}\nif [1] notin {苦} then [2] not in{大,中}\nif [0] in {蓝} then [2] not in {小}",
					grow : true,
					width: '100%'
				
        	       }]
			  } ]

	}],
	buttons:[{
		   text:'导入条件',
		   handler:importConditionTxt
	   },{
		   text:'导入数据',
		   handler:importDataTxt
	   },{
		   text: '生成',
		   handler:loadData
	   },{
		   text:'重置',
		   handler:function(){
			   northformPanel.getForm().reset();
	   }
   }]
});





function importDataTxt(){
	uploadPanel =  Ext.create( "Ext.form.FormPanel",{
		labelWidth : 30,
		height : 120,
		border : false,
		bodyStyle : 'padding:30px 5px;',
		buttonAlign : 'center',
		buttons : [ {
			id : 'impFile',
			text : '导入',
			width : 90,
			height : 25,
			handler : btnClick
		}, {
			id : 'cal',
			text : '取消',
			width : 90,
			height : 25,
			handler : btnClick
		} ],
		fileUpload : true,
		items : [{
			xtype : 'fileuploadfield',
			emptyText : '选择文件',
			fieldLabel : '',
			anchor : '80%',
			name : 'upload',
			regex : /\.txt$/i,
			regexText : '请选择文本文件',
			buttonText : '浏览文件'
		}]
	});
	win =  Ext.create("Ext.Window",{
		height : 160,
		width : 320,
		title : '导入窗口',
		modal : true
	});
	win.add(uploadPanel);
	win.show();
};
var win;
function importConditionTxt(){
	uploadPanel =  Ext.create( "Ext.form.FormPanel",{
		labelWidth : 30,
		height : 120,
		border : false,
		bodyStyle : 'padding:30px 5px;',
		buttonAlign : 'center',
		buttons : [ {
			id : 'impFile',
			text : '导入',
			width : 90,
			height : 25,
			handler : btnClick1
		}, {
			id : 'cal',
			text : '取消',
			width : 90,
			height : 25,
			handler : btnClick1
		} ],
		fileUpload : true,
		items : [{
			xtype : 'fileuploadfield',
			emptyText : '选择文件',
			fieldLabel : '',
			anchor : '80%',
			name : 'upload',
			regex : /\.txt$/i,
			regexText : '请选择文本文件',
			buttonText : '浏览文件'
		}]
	});
	win =  Ext.create("Ext.Window",{
		height : 160,
		width : 320,
		title : '导入窗口',
		modal : true
	});
	win.add(uploadPanel);
	win.show();
};
function btnClick(btn){
	var bid = btn.id;
	if(bid == 'impFile'){
		var form = uploadPanel.form;
		if (form.isValid()) {
			Ext.Msg.confirm('系统提示','该操作会强制覆盖当前数据，操作不可逆。是否继续？',function(btn){
				if(btn == 'yes') {
					form.submit({
						clientValidation : true,
						waitMsg : '正在上传文件...',// 提示信息
						waitTitle : '信息提示',
						url : contextPath+ '/ThirdCombinedMethodAction!impDataExcel.action',
						params : {},
						method : 'post',
						success : function(form, action) {
							win.close();
							var text =Ext.getCmp("data");
							text.setValue(action.result.msg);
							Ext.example.msg('提示', 'txt导入成功!',true,500);						
						},
						failure : function(form, action) {
							var errorInfo = "";
							errorInfo = Ext.encode(action.result.msg);
							Ext.example.msg('错误提示', 'txt导入失败！',true,500);
						}
					});
				}
			});
		}
	}else if (bid == 'cal') {
		win.close();
	}
};
function btnClick1(btn){
	var bid = btn.id;
	if(bid == 'impFile'){
		var form = uploadPanel.form;
		if (form.isValid()) {
			Ext.Msg.confirm('系统提示','该操作会强制覆盖当前数据，操作不可逆。是否继续？',function(btn){
				if(btn == 'yes') {
					form.submit({
						clientValidation : true,
						waitMsg : '正在上传文件...',// 提示信息
						waitTitle : '信息提示',
						url : contextPath+ '/ThirdCombinedMethodAction!impDataExcel.action',
						params : {},
						method : 'post',
						success : function(form, action) {
							win.close();
							var text =Ext.getCmp("condition");
							text.setValue(action.result.msg);
							Ext.example.msg('提示', 'txt导入成功!',true,500);						
						},
						failure : function(form, action) {
							var errorInfo = "";
							errorInfo = Ext.encode(action.result.msg);
							Ext.example.msg('错误提示', 'txt导入失败！',true,500);
						}
					});
				}
			});
		}
	}else if (bid == 'cal') {
		win.close();
	}
};
// 加载数据
function loadData(){	
	 msg = northformPanel.getForm().findField("msg").getValue();
	 selection = northformPanel.getForm().findField("selection").getValue();
	if(msg==null||msg==''){
		Ext.example.msg("提示", "请先输入数据！", true, 500);
		return;		
	}
	selectChange(msg,selection);
	
};
function selectChange(msg,selection){
	detailColumn = [];
	detailModel=[];
	//获取报表列信息
	Ext.Ajax.request({
		url: contextPath + '/ThirdCombinedMethodAction!getColumnForGirdpanel.action',
	    params: {
	    	msg:msg
	    	
	    },
	    success: function(response){
	        var text = response.responseText;
	        //重载COLUMN
	        detailColumn = Ext.JSON.decode(text).column;
	        detailModel  = Ext.JSON.decode(text).model;	        
	        showGrid(msg,selection);
	    }
	});
}


function showhistoryGrid(msg){
	Ext.define('detailModel', {
		extend : 'Ext.data.Model',
		fields :   detailModel
		           
	});
	detailStore = Ext.create('Ext.data.Store', {
		autoLoad:true,
		autoDestory:true,
		model: 'detailModel',
		proxy: {
			type: 'ajax',
			url: contextPath + '/ThirdCombinedMethodAction!searchCase.action',
			reader: {
				type: 'json',
				root: 'root'
			},
			 actionMethods : {
					read : 'POST' // Store设置请求的方法，与Ajax请求有区别  
				},
			extraParams: {  
				flag:msg
				
			}
		}
	});

	// viewPort.remove(detailPanel);
	//详细报表列表
	var detailPanel = Ext.create("Ext.grid.Panel",{
		//id					: 'configGrid',
		autoScroll :true ,
	//	id : 'grid',
	layout : 'fit',
	 store : detailStore,
	 forceFit: true, //列表宽度自适应 
	columns : detailColumn,
	selType: 'cellmodel',
	selModel:'rowmodel',
	tbar:[{
		xtype : 'button',
		width : 80,
		height : 25,
	//	id : 'save',
		width : 80,
		enableToggle:false,
		height : 25,
		//icon:''+contextPath+'/images/export_excel.png',
		text : '保存',
		icon:''+contextPath+'/images/save.gif',

		listeners : {
			click : function() {
				var store = this.up("grid").getStore();
				var count = store.getCount();
				var count1 = store.getCount();
				for(var i=0;i<count1;i++){
					if(store.getAt(i).data.tab==1){
						count--;
					}
				}
				var records = new Array(count);
				var record;
				var index=0;
				for (var i = 0; i < count1; i++) {
					//tab =1 为保存了
					
					// 获取前台数据存储到后台去
					record = store.getAt(i);
					if(record.data.flag==''||record.data.flag==null)
					{
						record.data.flag=store.getAt(0).data.flag;
					}
					if(record.data.tab==null||record.data.tab==0||record.data.tab==''){
						record.data.tab=1;
					
						var obj = {
								'id':record.data.id,
								'user' : record.data.user,
								'flag':record.data.flag,
								'tab':record.data.tab
						}
						records[index++] = JSON.stringify(obj);
						if(index>=count){
							break;
						}
					}
					// 可以把对象存入数组中并且识别到
					// 后台不会再识别到[objcet,object]这样的
					// 而是具体数值
					
				}
//				window.location.href=contextPath + '/BoundaryMethodAction!outputEquivalentExcel.action'
				Ext.Ajax.request({
					url : contextPath + '/ThirdCombinedMethodAction!saveOrUpdateCombined.action',
					method : 'POST',
					timeout : 30000,
					type : 'ajax',	    					// form:"ajaxform"
					params : {
						record : records
					},
					success:function(resp){
						var text = Ext.decode(resp.responseText);   				
						var store = Ext.getCmp("grid").getStore();
						var count = store.getCount();
						var index =0;
						for(var i=0;i<count;i++){
							if(store.getAt(i).data.id!=null||store.getAt(i).id!=""){
							//	store.getAt
								var record =store.getAt(i);
								record.data.id=text.root[index++].id;
								if(index>=text.totalcount){
									break;
								}								
							}
						}
						comboStore.reload();	
						Ext.example.msg("提示","数据正在保存",true,500);
			//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
					},
					failure:function(resp)
					{
						Ext.example.msg("提示","数据已经保存了或者未生成数据",true,500);
					}
				})
			}
		}
			},'-',{
				xtype : 'button',
			//	id : 'mark',
				width : 80,
				height : 25,
			//	icon:''+contextPath+'/images/export_excel.png',
				text : '标记',
				icon:''+contextPath+'/images/tip.png',
				listeners : {
					click : function() {
						var store = this.up("grid").getStore();	
				
						var flag ="thirdcombined,"+store.getAt(0).data.flag;
						var count =store.getCount();
						for(var i=0;i<count;i++){
							if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
								Ext.example.msg("提示","请先保存再标记",true,500);
								return ;
							}
						}
						Ext.Ajax.request({
							url : contextPath + '/UsefulCaseAction!saveOrUpdateUsefulCase.action',
							method : 'POST',
							timeout : 30000,
							type : 'ajax',	    					// form:"ajaxform"
							params : {
								
								flag:flag
							},
							success:function(resp){
								
							
								//
								var data =Ext.decode(resp.responseText);
								if(data.msg==""){	    								    						
									Ext.example.msg("提示","数据标记成功",true,500);
								}else{
									Ext.example.msg("提示","数据已经标记过",true);
								}
					//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
							},
							failure:function(resp)
							{
								Ext.example.msg("提示","数据标记失败",true,500);
							}
						})
							// 可以把对象存入数组中并且识别到
							// 后台不会再识别到[objcet,object]这样的
							// 而是具体数值			
				
						//		window.location.href=contextPath + '/BoundaryMethodAction!outputBoundaryExcel.action?record='+record.data.flag;
						
					}
				}
			    	},'-',{
		xtype : 'button',
	//	id : 'outputexcel',
		width : 80,
		height : 25,
		icon:''+contextPath+'/images/export_excel.png',
		text : '导出excel',
		listeners : {
			click : function() {
				var store = this.up("grid").getStore();	
				var record =store.getAt(0);
				var count =store.getCount();
				for(var i=0;i<count;i++){
					if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
						Ext.example.msg("提示","请先保存再下载",true,500);
						return ;
					}
				}
					// 可以把对象存入数组中并且识别到
					// 后台不会再识别到[objcet,object]这样的
					// 而是具体数值			
				window.location.href=contextPath + '/ThirdCombinedMethodAction!outputCombinedExcel.action?record='+record.data.flag;
				
			}
		}
			} ],
	stripeRows : true,			
	columnLines: true,			
	enableColumnMove :true,			
	enableColumnResize : true,			
	trackMouseOver :true		
	})		
	var grid =Panel.items.get(0);
	if(grid!=null){
		Panel.remove(grid);
	}
	Panel.add(detailPanel);
	detailStore.reload();
	


}

//生成grid表

function showGrid(msg,selection){

	Ext.define('detailModel', {
		extend : 'Ext.data.Model',
		fields :   detailModel
		           
	});
	detailStore = Ext.create('Ext.data.Store', {
		autoLoad:true,
		autoDestory:true,
		model: 'detailModel',
		proxy: {
			type: 'ajax',
			url: contextPath + '/ThirdCombinedMethodAction!combinedMethod.action',
			reader: {
				type: 'json',
				root: 'root'
			},
			 actionMethods : {
					read : 'POST' // Store设置请求的方法，与Ajax请求有区别  
				},
			extraParams: {  
				msg:msg,
				selection:selection
			}
		}
	});

	// viewPort.remove(detailPanel);
	//详细报表列表
	var detailPanel = Ext.create("Ext.grid.Panel",{
		//id					: 'configGrid',
		autoScroll :true ,
	//	id : 'grid',
	layout : 'fit',
	 store : detailStore,
	 forceFit: true, //列表宽度自适应 
	columns : detailColumn,
	selType: 'cellmodel',
	selModel:'rowmodel',
	tbar:[{
		xtype : 'button',
		width : 80,
		height : 25,
	//	id : 'save',
		width : 80,
		enableToggle:false,
		height : 25,
		//icon:''+contextPath+'/images/export_excel.png',
		text : '保存',
		icon:''+contextPath+'/images/save.gif',

		listeners : {
			click : function() {
				var store = this.up("grid").getStore();
				var count = store.getCount();
				var count1 = store.getCount();
				for(var i=0;i<count1;i++){
					if(store.getAt(i).data.tab==1){
						count--;
					}
				}
				var records = new Array(count);
				var record;
				var index=0;
				for (var i = 0; i < count1; i++) {
					//tab =1 为保存了
					
					// 获取前台数据存储到后台去
					record = store.getAt(i);
					if(record.data.flag==''||record.data.flag==null)
					{
						record.data.flag=store.getAt(0).data.flag;
					}
					if(record.data.tab==null||record.data.tab==0||record.data.tab==''){
						record.data.tab=1;
						
						var obj = {
								'id':record.data.id,
								'user' : record.data.user,
								'flag':record.data.flag,
								'tab':record.data.tab
						}
						records[index++] = JSON.stringify(obj);
						if(index>=count){
							break;
						}
					}
					// 可以把对象存入数组中并且识别到
					// 后台不会再识别到[objcet,object]这样的
					// 而是具体数值
					
				}
//				window.location.href=contextPath + '/BoundaryMethodAction!outputEquivalentExcel.action'
				Ext.Ajax.request({
					url : contextPath + '/ThirdCombinedMethodAction!saveOrUpdateCombined.action',
					method : 'POST',
					timeout : 30000,
					type : 'ajax',	    					// form:"ajaxform"
					params : {
						record : records
					},
					success:function(resp){
						var text = Ext.decode(resp.responseText);   				
						var store = Ext.getCmp("grid").getStore();
						var count = store.getCount();
						var index =0;
						for(var i=0;i<count;i++){
							if(store.getAt(i).data.id!=null||store.getAt(i).id!=""){
							//	store.getAt
								var record =store.getAt(i);
								record.data.id=text.root[index++].id;
								if(index>=text.totalcount){
									break;
								}								
							}
						}
						comboStore.reload();	
						Ext.example.msg("提示","数据正在保存",true,500);
			//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
					},
					failure:function(resp)
					{
						Ext.example.msg("提示","数据已经保存了或者未生成数据",true,500);
					}
				})
			}
		}
			},'-',{
				xtype : 'button',
			//	id : 'mark',
				width : 80,
				height : 25,
			//	icon:''+contextPath+'/images/export_excel.png',
				text : '标记',
				icon:''+contextPath+'/images/tip.png',
				listeners : {
					click : function() {
						var store = this.up("grid").getStore();	
				
						var flag ="thirdcombined,"+store.getAt(0).data.flag;
						var count =store.getCount();
						for(var i=0;i<count;i++){
							if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
								Ext.example.msg("提示","请先保存再标记",true,500);
								return ;
							}
						}
						Ext.Ajax.request({
							url : contextPath + '/UsefulCaseAction!saveOrUpdateUsefulCase.action',
							method : 'POST',
							timeout : 30000,
							type : 'ajax',	    					// form:"ajaxform"
							params : {
								
								flag:flag
							},
							success:function(resp){
								
							
								//
								var data =Ext.decode(resp.responseText);
								if(data.msg==""){	    								    						
									Ext.example.msg("提示","数据标记成功",true,500);
								}else{
									Ext.example.msg("提示","数据已经标记过",true);
								}
					//			 Ext.Msg.alert("1",store.getAt(0).data.id+"=="+store.getAt(1).data.id);
							},
							failure:function(resp)
							{
								Ext.example.msg("提示","数据标记失败",true,500);
							}
						})
							// 可以把对象存入数组中并且识别到
							// 后台不会再识别到[objcet,object]这样的
							// 而是具体数值			
				
						//		window.location.href=contextPath + '/BoundaryMethodAction!outputBoundaryExcel.action?record='+record.data.flag;
						
					}
				}
			    	},'-',{
		xtype : 'button',
	//	id : 'outputexcel',
		width : 80,
		height : 25,
		icon:''+contextPath+'/images/export_excel.png',
		text : '导出excel',
		listeners : {
			click : function() {
				var store = this.up("grid").getStore();	
				var record =store.getAt(0);
				var count =store.getCount();
				for(var i=0;i<count;i++){
					if(store.getAt(i).data.tab==0||store.getAt(i).data.tab==""||store.getAt(i).data.tab==null){
						Ext.example.msg("提示","请先保存再下载",true,500);
						return ;
					}
				}
					// 可以把对象存入数组中并且识别到
					// 后台不会再识别到[objcet,object]这样的
					// 而是具体数值			
				window.location.href=contextPath + '/ThirdCombinedMethodAction!outputCombinedExcel.action?record='+record.data.flag;
				
			}
		}
			} ],
	stripeRows : true,			
	columnLines: true,			
	enableColumnMove :true,			
	enableColumnResize : true,			
	trackMouseOver :true		
	})		
	var grid =Panel.items.get(0);
	Panel.remove(grid);
	Panel.add(detailPanel);
	detailStore.reload();
	

}
//

function add(){
	  rowEditing.cancelEdit();
	  var store =Ext.getCmp('grid').getStore();
	  Ext.getCmp('grid').getStore().insert(store.getCount(), new gridmodel());
    rowEditing.startEdit(store.getCount()-1, 0);
};
function canel() {
    Ext.MessageBox.confirm('提示', '确定删除该记录?', function(btn){
        if(btn!='yes') {
            return;
        }					            			
        var sm = Ext.getCmp('grid').getSelectionModel();
        var row =sm.getSelection();
       
        if(row[0].data.id!=null||row[0].data.id!=""){
    	    Ext.Ajax.request({
    			url : contextPath + '/ThirdCombinedMethodAction!deleteRow.action',
    			method : 'POST',
    			timeout : 30000,
    			type : 'ajax',	    					// form:"ajaxform"
    			params : {
    				id : row[0].data.id  				
    			},
    			success:function(resp){
    				rowEditing.cancelEdit();
    		        var store=Ext.getCmp('grid').getStore();
    		        store.remove(sm.getSelection());
    		        if (store.getCount() > 0) {
    		            sm.select(0);
    		        }
    		        Ext.example.msg("提示","已经删除了",true,500);
    			},
    			failure:function(resp){
    				Ext.example.msg("提示","删除失败，请重试",true,500);
    			}
    		})
        }else{
        	rowEditing.cancelEdit();
            var store=Ext.getCmp('grid').getStore();
            store.remove(sm.getSelection());
            if (store.getCount() > 0) {
                sm.select(0);
            }
            Ext.example.msg("提示","已经删除了",true,500);
        }
     });
   
};

Ext.onReady(function (){
	viewPort= Ext.create("Ext.container.Viewport",{
		defaults : {
			bodyStyle : "background-color: #FFFFFF;",
			frame : true
		},
		layout : "border",
		items:[Panel,northformPanel]
	})
})