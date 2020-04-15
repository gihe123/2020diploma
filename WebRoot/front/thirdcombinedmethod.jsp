<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'thirdcombinedmethod.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<jsp:include page="base.jsp"/>
	<script type="text/javascript" src ="./js/model/thirdcombinedMethodModel.js"></script>
	<script type="text/javascript" src ="./js/store/thirdcombinedMethodStore.js"></script>	
	<script type="text/javascript" src ="./js/view/thirdcombinedMethodView.js"></script>
  <body>

  </body>
</html>
