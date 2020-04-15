

var centerGirdPanel = Ext.create('Ext.grid.Panel', {
		region : "center",
		layout : "fit",
			id : 'centergrid',		
		 store : formStore,
		 forceFit: true, //列表宽度自适应 
	   columns : [// 配置表格列
	              Ext.create("Ext.grid.RowNumberer",{}),
	              {
						header : "用例方法",
						width : 120,
						dataIndex : 'method',
						sortable : true,
						editor:{ allowBlank:true  }
					},{
						header : "用例生成时间",
						width : 200,
						dataIndex : 'dates',
						sortable : true,
						editor:{ allowBlank:true  }
					},{
						header : "用例使用条件",
						width : 300,
						dataIndex : 'conditions',
						sortable : true,
						editor:{ allowBlank:true  }
					},{
						header:"flag",						
						dataIndex:"flag",
						 hidden: true,
						 hideable: false
					},{
						header:"id",
						
						dataIndex:"id",
						 hidden: true,
						 hideable: false
					}],	 	
	   listeners: {
		   'itemdblclick':function(thisGrid, record,item, index){
			   //选中行信息
			   var row = formStore.getAt(index);
			   var flag =row.get("flag");
			   //创建窗口Gridpanel
			   Ext.define("gridmodel",{
					extend : 'Ext.data.Model',
					fields : [ 
					   {name : "id"},
					  {name : "user"},
					  {name:"flag"},
					  {name:"tab"}
					]
				});
			   var gridStore = Ext.create('Ext.data.JsonStore', {
					autoLoad:false,
				    autoDestroy: true,
				    proxy: {
				        type: 'ajax',
				        url :contextPath + '/UsefulCaseAction!showUsefulcaseDetail.action',
				        actionMethods : {
							read : 'POST' // Store设置请求的方法，与Ajax请求有区别  
						},
				        reader: {
				            type: 'json',        
				            root: 'root'
				        }
				    },
				    model:'gridmodel'
				});
			   var GridPanel=Ext.create('Ext.grid.Panel', {					
					id : 'grid',
					title : '<span style="font-size:15px">用例表</span>',
					region:"center",
					forceFit: true, //列表宽度自适应 
					store : gridStore,
					columns : [// 配置表格列
		            Ext.create("Ext.grid.RowNumberer",{}),
					{
						header : "用例",
						width : 200,
						dataIndex : 'user',
						sortable : true,
						editor : {
							allowBlank : true
						}
					}, {
						header : "flag",
						width : 200,
						dataIndex : "flag",
						hidden : true,
						hideable : false
					}, {
						header : "id",
						width : 200,
						dataIndex : "id",
						hidden : true,
						hideable : false
					}, {
						header : "tab",
						width : 200,
						dataIndex : "tab",
						hidden : true,
						hideable : false
					} ],
					selType : 'cellmodel',
					selModel : 'rowmodel',

					stripeRows : true,
					columnLines : true,
					enableColumnMove : true,
					enableColumnResize : true,
					trackMouseOver : true

				});
			   var Panel =Ext.create("Ext.panel.Panel",{
				   frame: true,
				   layout: 'fit',
				   items:[GridPanel]
			   });
			//  Ext.Msg.alert("",+"=="+row[0].data.tab);
			   var   win =  Ext.create("Ext.window.Window",{
	               //renderTo:'hello-tabs',
	              //创建新窗口是一定要写这个，不然可能二次开启就会grid清空报错addcls null
				 			 
	               title: '用例详细',
	               layout:'fit',
	               width:400,
	               height:500,
	               draggable : true, 
	               modal:true,	             
	               resizable : true, 
	               maximizable:true,
	               maximized :false,	             
	               modal:true,
	               plain: true,
	               items:Panel
	               
	          });	
			   win.show();
			   gridStore.on("beforeload",function(){
				   Ext.apply(this.proxy.extraParams,  
					{ 
					   flag:flag
					}
				   );
			   });
			   gridStore.load({});	
			  
			  		 
			  

	     //store最好在页面弹出来后加载，不然ie8会错位
	      }},
	tbar:[{  
        xtype : 'button', 
        width : 80,
		height : 25,
        text : '删除' ,
        handler: canel,
    	icon:''+contextPath+'/images/delete.gif'
    	
    },"-",{
		xtype : 'button',
		id : 'outputexcel',
		width : 80,
		height : 25,
		icon:''+contextPath+'/images/export_excel.png',
		text : '导出excel',
		listeners : {
			click : function() {
				var store = this.up("grid").getStore();	
			    var sm = Ext.getCmp('centergrid').getSelectionModel();
		        var row =sm.getSelection();
		     
				// 可以把对象存入数组中并且识别到
					// 后台不会再识别到[objcet,object]这样的
					// 而是具体数值			
				window.location.href=contextPath + '/UsefulCaseAction!outputUsefulcaseExcel.action?record='+row[0].data.flag;
				
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
	title:"组合测试法条件数据选择",
//	collapsible:true,
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
        			xtype:"combo",
        			id:"data",
        			store:combostore,
        			valueField : "id",// 设置下拉选择框的值
					displayField : "msg",// 设置下拉选择框的显示文本
					triggerAction : "all",// 请设置为"all",否则默认为"query"的情况下，你选择某个值后，再此下拉时，只出现匹配选项，如果设为"all"的话，每次下拉均显示全部选项
					editable : true,// 可以编辑
					loadingText : "正在加载···",
					minChars : 1,// 下拉列表框自动搜索用户需要输入的最小字符数量。mode='remote'默认为4，mode='local'默认为0
					forceSelection : false, // 输入值是否严格为待选列表中存在的值。如果输入不存在的值，会自动选择第一个最接近的值
					emptyText : "",
					hideLabel : true,				
					allowBlank : false,
					queryDelay : 1000,
					mode : 'local',
					hideTrigger:true,  //右边的可点击三角按钮取消掉				
					emptyText : "输入搜索字段",					
					width: '100%'					
        	       }]
		} ]

	}],

	buttons:[{
		   text: '搜索条件',
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
		items:[centerGirdPanel,northformPanel]
	});
});





// 加载数据
function loadData(){	
	//获取combo displayField
	 msg = Ext.getCmp("data").getRawValue();
		 //northformPanel.getForm().findField("msg").getValue();
	   formStore.on("beforeload",function(){
		   	Ext.apply(this.proxy.extraParams,  
			{ 
			   msg:msg
			}
		   );
	   });
	   formStore.load({});	


};

function canel() {
    Ext.MessageBox.confirm('提示', '确定删除该记录?', function(btn){
        if(btn!='yes') {
            return;
        }					            			
        var sm = Ext.getCmp('centergrid').getSelectionModel();
        var row =sm.getSelection();
       
        if(row[0].data.id!=null){
    	    Ext.Ajax.request({
    			url : contextPath + '/UsefulCaseAction!deleteRow.action',
    			method : 'POST',
    			timeout : 30000,
    			type : 'ajax',	    					// form:"ajaxform"
    			params : {
    				id : row[0].data.id  				
    			},
    			success:function(resp){
    			
    		        var store=Ext.getCmp('centergrid').getStore();
    		        store.remove(sm.getSelection());
    		        if (store.getCount() > 0) {
    		            sm.select(0);
    		        }
    		        Ext.example.msg("提示","已经删除了",true,500);
    			},
    			failure:function(resp){
    				Ext.example.msg("提示","删除失败，请重试",true,500);
    			}
    		});
        }
     });
   
}

