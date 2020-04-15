/**
 * 
 */

var center_tab =Ext.create("Ext.tab.Panel",{
		region:"center",
		activeTab : 1,
		 cls:"ui-tab-bar",
		// bodyCls:"ui-tab-body",
		items:[
		      {
		    	title:"首页",
		    	html:"<body><img src='images/2.jpg' width='100%' height='100%'/></body>"
		      }]		
})
var west_treepanel = Ext.create('Ext.tree.Panel',{
	title : '方法列表',
	region : 'west',
	split : true,
	collapsible : true,
	width : 200,
	height : 150,
	store : treestore,
	rootVisible : false,
	listeners : {
		itemclick : function(view, record, item) {
			// 从record item index 等参数中我们可以获得大部分满足我们应用的信息
			var n = center_tab.getComponent(record.raw.id)// 获取组件
			if (record.get('leaf') == false) {
				return;
			};
			if (!n) {
				n = center_tab
				.add({
					title : record.raw.text,
					id : record.raw.id,
					closable : true,
					html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'
						+ record.raw.description
						+ '"></iframe>'
				})
			}
			center_tab.setActiveTab(n);
		}
	}
})
Ext.onReady(function(){
	Ext.create("Ext.container.Viewport",{
		defaults : {
			bodyStyle : "background-color: #FFFFFF;",
			frame : true
		},
		layout : "border",
		items:[west_treepanel,center_tab]
	})
})
	