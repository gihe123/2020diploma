/**
 * 
 */

Ext.define("casemodel",{
	extend : 'Ext.data.Model',
	fields : [ 
	   {name : "id"},
	  {name : "method"},
	  {name:"flag"},
	  {name:"dates"},
	  {name:"conditions"}
	]
})
Ext.define("combomodel",{
	extend:"Ext.data.Model",
	fields:[
	        {name:"msg"},
	        {name:"id"}
	        ]
})


 