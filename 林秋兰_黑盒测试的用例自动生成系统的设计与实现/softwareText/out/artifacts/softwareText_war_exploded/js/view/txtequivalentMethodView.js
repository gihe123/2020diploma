/**
 * 
 */
var rowEditing=Ext.create('Ext.grid.plugin.CellEditing',{  
	clicksToEdit:2
})
var centerGirdPanel = Ext.create('Ext.grid.Panel', {
		region : "center",
			id : 'grid',
		 title : '<span style="font-size:15px">用例表</span>',
		layout : 'fit',
		 store : formStore,
		 forceFit: true, //列表宽度自适应 
	   columns : [// 配置表格列
	              Ext.create("Ext.grid.RowNumberer",{ id:"rownumber" }),
	              {
					header : "用例",
					width : 120,
					dataIndex : 'user',
					sortable : true,
					editor:{ allowBlank:false  }
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
	   forceFit: true, //列表宽度自适应 
	   plugins: [rowEditing],
	   listeners: {
		   'edit':function(obj){
			   var row = Ext.getCmp("grid").getSelectionModel().getSelection();
			   //标记没有保存
			   row[0].data.tab=0;
			//  Ext.Msg.alert("",+"=="+row[0].data.tab);
		   }
		   },
	tbar:[ {  
        xtype : 'button',
        width : 80,
		height : 25,
        text : '新增',  
        handler : add,
    	icon:''+contextPath+'/images/add.gif'
    	
    },'-',{  
        xtype : 'button', 
        width : 80,
		height : 25,
        text : '删除' ,
        handler: canel,
    	icon:''+contextPath+'/images/delete.gif'
    	
    },'-',{
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
					url : contextPath + '/EquivalentMethodAction!saveOrUpdateEquivalent.action',
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
						Ext.example.msg("提示","数据已经保存",true,500);
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
	    				var minlength =Ext.getCmp("minlength").value;
	    				var maxlength=Ext.getCmp("maxlength").value;
	    				var ischar =Ext.getCmp("ischar").value;
	    				var isnum =Ext.getCmp("isnum").value;
	    				var ismark =Ext.getCmp("ismark").value;
	    				var flag ="txtequivalent,"+store.getAt(0).data.flag;
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
	    						minlength : minlength,
	    						maxlength:maxlength,
	    						ischar:ischar,
	    						isnum:isnum,
	    						ismark:ismark,
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
				window.location.href=contextPath + '/EquivalentMethodAction!outputEquivalentExcel.action?record='+record.data.flag;
				
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
	  	   	//	Ext.example.msg(record[0].data.date,combo.value+"--"+combo.getValue(),true,50000);
	  	   		store.proxy.url=contextPath + '/EquivalentMethodAction!searchCase.action?flag='+combo.getValue(); 	   
	  	   	 minLength = 0;
	  		 maxLength = 0;
	  		 isMark =  0;
	  		 isChar =  0;
	  		 isNum = 0;
	  		 northformPanel.getForm().reset();
	  	   		store.load({});
				
  	   		}
  	   	}
	    	}],
	stripeRows : true,			
	columnLines: true,			
	enableColumnMove :true,			
	enableColumnResize : true,			
	trackMouseOver :true		
	
});

var northformPanel =  Ext.create("Ext.form.FormPanel",{
	region:"north",
	title:"等价类法文本框条件选择",
	collapsible:true,
	labelWidth:80,
	collapsed:false,
	frame:true,
	border:false,
	items:[
	{
        xtype:'fieldset',
        title: '用例条件设置',
        collapsible:true,
        collapsed:false,
        autoHeight:true,
        layout:'column',
        items :[
        {layout:'form',columnWidth:0.15,border:false,bodyStyle:'background-color: #dfe9f6;',items:[
        	{
	    		xtype:'textfield',	          
	            name: 'minLength',
	            id:'minlength',
	            emptyText:"最小长度",
	            selectOnFocus : true,
	            regex:/^\d+$/,    
	            regexText:'请输入正确的数字s'
	            
	        }
	      ]},{layout:'form',columnWidth:0.15,border:false,bodyStyle:'background-color: #dfe9f6;',items:
	    	  [
	    	   {
	    		   xtype:'textfield',	          
	    		   name: 'maxLength',
	    		   id:'maxlength',
	    		   emptyText:"最大长度",
	    		   selectOnFocus : true,
	    		   regex:/^\d+$/,    
		            regexText:'请输入正确的数字s'
		           
	    	   }
	      ]},{layout:'form',columnWidth:0.23, border:false, bodyStyle:'background-color: #dfe9f6;', items:
	    	  [ 	                                                                                                       {
				xtype : 'combo',
				name : 'isChar',
				id:'ischar',
				 editable: false,
				store : Ext.create('Ext.data.Store', {
					fields : [ 'text', 'value' ],
					data : [ {
						"text" : "允许字母",
						"value" : "1"
					}, {
						"text" : "不允许字母",
						"value" : "0"
					} ]
				}),// 数据
				queryMode : 'local',// 加载本地数据
				displayField : 'text',// 显示的字段，对应store中的text值
				valueField : 'value',
				emptyText : "是否允许字母"
			} ]
	      },
	      {layout:'form',columnWidth:0.23, border:false, bodyStyle:'background-color: #dfe9f6;', items:[
        	{
	        	xtype:'combo',
	        	name : 'isNum',
	        	id:"isnum",
	        	 editable: false,
	            store: Ext.create('Ext.data.Store',{       
			                fields:['text','value'],  
			                data:[{  
			                    "text":"允许数字",  
			                    "value":"1"  
			                },{  
			                    "text":"不允许数字",  
			                    "value":"0"  
			                }]  
			          }),// 数据
	            queryMode: 'local',// 加载本地数据
	            displayField: 'text',// 显示的字段，对应store中的text值
	            valueField: 'value',
	            emptyText : "是否允许数字"
	        }
	     ]
      },{layout:'form',columnWidth:0.23, border:false, bodyStyle:'background-color: #dfe9f6;', items:[
	    	{
	        	xtype:'combo',
	        	name : 'isMark',
	        	id:"ismark",
	        	 editable: false,
	        	store: Ext.create('Ext.data.Store',{       
			                fields:['text','value'],  
			                data:[{  
			                    "text":"允许特殊符号",  
			                    "value":"1"
			                },{  
			                    "text":"不允许特殊符号",  
			                    "value":"0"
			                }]  
			           }) ,// 数据
		      queryMode: 'local',// 加载本地数据
		      displayField: 'text',// 显示的字段，对应store中的text值
		      valueField: 'value',
		      emptyText : "是否允许特殊符号"
	        }
	     ]
	  }
	]
            
}
],
	buttons: [{
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

//加载数据
function loadData(){	
	 minLength = northformPanel.getForm().findField("minLength").getValue();
	 maxLength = northformPanel.getForm().findField("maxLength").getValue();
	 isMark =  northformPanel.getForm().findField("isMark").getValue();
	 isChar =  northformPanel.getForm().findField("isChar").getValue();
	 isNum = northformPanel.getForm().findField("isNum").getValue();
	if(minLength==null||maxLength==''||maxLength==null||minLength==''){
		Ext.example.msg("提示", "请先设置长度！", true, 500);
		return;		
	}
	var reg =/^\d+$/;
	
	if(reg.test(minLength)==false||reg.test(maxLength)==false){
		Ext.example.msg("提示", "输入正确数字！", true, 500);
		return;
		
	}
	formStore.proxy.url =contextPath + '/EquivalentMethodAction!txtEquivalentMethod.action';

	formStore.load({});
};
function add(){
	  rowEditing.cancelEdit();
	  var store =Ext.getCmp('grid').getStore();
	  Ext.getCmp('grid').getStore().insert(store.getCount(), new gridmodel());
    rowEditing.startEdit(store.getCount()-1, 1);
};
function canel() {
    Ext.MessageBox.confirm('提示', '确定删除该记录?', function(btn){
        if(btn!='yes') {
            return;
        }					            			
        var sm = Ext.getCmp('grid').getSelectionModel();
        var row =sm.getSelection();
       
        if(row[0].data.id!=null){
    	    Ext.Ajax.request({
    			url : contextPath + '/EquivalentMethodAction!deleteRow.action',
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
            Ext.example.msg("提示","已经删除了",true,500);
            
        }
     });
   
}

