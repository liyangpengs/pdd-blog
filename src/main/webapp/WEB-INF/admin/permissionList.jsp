<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pdd养成计划后台管理系统-权限列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
<link rel="stylesheet" href="/static/css/news.css" media="all" />
<style type="text/css">
	.per-btn{
		margin: 15px 5px 5px 20px;
	}
</style>
</head>
<body>
	<div class="per-btn">
		<button class="layui-btn layui-btn-normal">新增权限</button>
		<button class="layui-btn layui-btn-danger">全选删除</button>
	</div>
	<div class="content-table">
		<table class="layui-table">
			 <colgroup>
			 	    <col>
				    <col>
				    <col>
				    <col>
  			</colgroup>
  			 <thead>
			    <tr>
			      <th>id</th>
			      <th>权限</th>
			      <th>添加时间</th>
			      <th>操作</th>
			    </tr> 
 			 </thead>
 			 <tbody id="permission-content">
 			 </tbody>
		</table>
	</div>
</body>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/permissionList.js"></script>
</html>