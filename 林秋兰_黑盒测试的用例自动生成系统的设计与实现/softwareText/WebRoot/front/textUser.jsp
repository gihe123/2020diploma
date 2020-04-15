<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试用例生成工具</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<jsp:include page="base.jsp"/>
	<script type="text/javascript" src ="./js/model/textUserModel.js"></script>
	<script type="text/javascript" src ="./js/store/textUserStore.js"></script>	
	<script type="text/javascript" src ="./js/view/textUserView.js"></script>
	
  </head>
  
  <body>
  </body>
</html>
