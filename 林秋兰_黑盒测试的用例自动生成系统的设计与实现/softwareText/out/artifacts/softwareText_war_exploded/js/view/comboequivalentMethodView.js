/**
 * 
 */
/**
 * 
 */

var centerGirdPanel = Ext.create('Ext.grid.Panel', {
		region : "center",
			id : 'grid',
		 title : '<span style="font-size:15px">用例表</span>',
		layout : 'fit',
		 store : formStore,
		 forceFit: true, //列表宽度自适应 
	   columns : [// 配置表格列
	              Ext.create("Ext.grid.RowNumberer",{}),
	              {
					header : "用例",
					width : 120,
					dataIndex : 'user',
					sortable : true,
					editor:{ allowBlank:true  }
				},{
					header:"flag",
					width:200,
					dataIndex:"flag",
					 hidden: true,
					 hideable: false
				},{
					header:"id",
					width:200,
					dataIndex:"id",
					 hidden: true,
					 hideable: false
				},{
					header:"tab",
					width:200,
					dataIndex:"tab",
					 hidden: true,
					 hideable: false
				}],
	   selType: 'cellmodel',
	   selModel:'rowmodel',
	   listeners: {
		   'edit':function(obj){
			   var row = Ext.getCmp("grid").getSelectionModel().getSelection();
			   row[0].data.tab=0;
			//  Ext.Msg.alert("",+"=="+row[0].data.tab);
		   }
		   },
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
						record.commit();  
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
		//		window.location.href=contextPath + '/EquivalentMethodAction!outputEquivalentExcel.action'
				Ext.Ajax.request({
					url : contextPath + '/ComboEquivalentMethodAction!saveOrUpdateEquivalent.action',
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
						Ext.example.msg("提示","数据已经保存了",true,500);
					}
				})
			}
		}
	    	},'-',{
	    		xtype : 'button',
	    		id : 'mark',
	    		width : 80,
	    		height : 25,
	    		text : '标记',
	    		icon:''+contextPath+'/images/tip.png',
	    		listeners : {
	    			click : function() {
	    				var store = this.up("grid").getStore();		
	    				var count =store.getCount();
	    				var flag ="comboequivalent,"+store.getAt(count-1).data.flag;
	    				
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
				window.location.href=contextPath + '/ComboEquivalentMethodAction!outputEquivalentExcel.action?record='+record.data.flag;
				
			}
		}
	    	},'-',{
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
	    	  	   		var store = this.up("grid").getStore();	
	    	  	   	 northformPanel.getForm().reset();
	    	  	   	//	Ext.example.msg(record[0].data.date,combo.value+"--"+combo.getValue(),true,50000);
	    	  	   		store.proxy.url=contextPath + '/ComboEquivalentMethodAction!searchCase.action?flag='+combo.getValue(); 	   
	    		  		 msg = null;
	    		  	
	    		  		
	    	  	   		store.load({});
	    				
	      	   		}
	      	   	}
	    	    	} ],
	stripeRows : true,			
	columnLines: true,			
	enableColumnMove :true,			
	enableColumnResize : true,			
	trackMouseOver :true		
	
});

var northformPanel =  Ext.create("Ext.form.FormPanel",{
	region:"north",
	title:"下拉框等价类法数据设置",
	collapsible:true,
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
					emptyText : "例：\n 酸、甜、苦、辣（按顺序输入）",
					grow : true,
					width: '100%'
					
				
        	       }]
			  } ]

	}],
	buttons:[{
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



Ext.onReady(function(){
	Ext.create("Ext.container.Viewport",{
		defaults : {
			bodyStyle : "background-color: #FFFFFF;",
			frame : true
		},
		layout : "border",
		items:[northformPanel,centerGirdPanel]
	})
})
//
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
						url : contextPath+ '/ComboEquivalentMethodAction!impDataExcel.action',
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

// 加载数据
function loadData(){	
	 msg = northformPanel.getForm().findField("msg").getValue();
	if(msg==null||msg==''){
		Ext.example.msg("提示", "请先输入数据！", true, 500);
		return;		
	}

	formStore.proxy.url =contextPath + '/ComboEquivalentMethodAction!comboEquivalentMethod.action';

	formStore.load({});
};
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
    			url : contextPath + '/ComboEquivalentMethodAction!deleteRow.action',
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
   
}




/**
 * 
 */