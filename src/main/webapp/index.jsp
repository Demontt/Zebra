<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
  </head>
  <body>
	<a href="<%=basePath%>/app01.jsp">应用数据</a>
	<a href="<%=basePath%>/host01.jsp">站点数据</a>
	<a href="<%=basePath%>/cell01.jsp">小区数据</a>
	<a href="<%=basePath%>/monitor.jsp">集群监控</a>
  </body>
</html>
