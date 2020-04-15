/**
 * 
 */
Ext.define("gridmodel",{
	extend : 'Ext.data.Model',
	fields : [ 
	   {name : "id"},
	  {name : "user"},
	  {name:"flag"},
	  {name:"tab"}
	]
});
Ext.define("combomodel",{
	extend:'Ext.data.Model',
	fields:[{name:"date"},{name:"flag"}]
})
