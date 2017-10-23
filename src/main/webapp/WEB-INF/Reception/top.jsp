<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pdd养成计划-头部</title>
<script type="text/javascript" src="../..//static/js/jquery-3.2.1.min.js"></script>
<link href="../../static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="../../static/css/index.css"/>
</head>
<body>
	<div class="top">
			<div style="float: left;"><img src="../../static/imgs/logo.jpg" width="90"  height="90" style="vertical-align: middle;"/><span style="color: green;">pdd养成计划</span></div>
			<div style="float: right; margin-top: 35px;font-size: 12px;"><c:choose>
				<c:when test="${sessionScope.userKey eq null}">
					<a href="javascript:void(0)" id="loginButton">您好,请登录</a> &nbsp;<a href="javascript:void(0)" id="redistButton">注册</a>
				</c:when>
				<c:otherwise>
					欢迎,${sessionScope.userKey.snickName}
				</c:otherwise>	
			</c:choose>&nbsp;&nbsp;</div>
		</div>
		
</body>
<script type="text/javascript">
	$('#loginButton').click(function(){
		parent.$("#myModal").modal();
	})
	$('#redistButton').click(function(){
		parent.$("#myregist").modal();
	})
	
</script>
</html>