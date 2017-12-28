<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>pdd养成计划-文章添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="/static/imgs/favicon.ico">
	<link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
	<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/static/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">文章标题:</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input newsName" lay-verify="required" name="title" placeholder="请输入文章标题">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">自定义属性:</label>
				<div class="layui-input-block">
					<input type="checkbox" name="show" class="isShow" title="是否展示">
					<input type="checkbox" name="top" class="tuijian" title="是否置顶">
				</div>
			</div>
			<div class="layui-inline">		
				<label class="layui-form-label">发布时间:</label>
				<div class="layui-input-inline">
					<input type="text" name="publishTime" readonly="readonly" class="layui-input newsTime" value="<%=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date())%>">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">文章类型:</label>
				<div class="layui-input-inline" style="z-index: 1000;">
					<select name="news_type" class="newsLook" lay-filter="browseLook">
				    </select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文章封面</label>
			<input type="file" name="upfile" class="layui-upload-file">
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">关键字</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" name="keyword" placeholder="请输入文章关键字">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文章描述:</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入文章描述" name="describe" class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">文章内容:</label>
			<div class="layui-input-block">
				<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
				<script type="text/javascript">
				var item = {
					        autoHeightEnabled: true, //是否自动长高，默认true  
					        autoFloatEnabled: false, //是否保持toolbar的位置不动，默认true  
					        wordCount: true, //是否开启字数统计 默认true  
					        maximumWords: 100000, //允许的最大字符数 默认值：10000  
					        wordOverFlowMsg: "<span style='color:red'>超出范围了！！！！！！！！</span>", //超出字数限制提示  
					        elementPathEnabled: false, //是否启用元素路径  
					        padding: 0,  
					        saveInterval: 5000000, // 将其设置大点，模拟去掉自动保存功能  
					        allowDivTransToP: false
					};
					   //传参生成实例  
				var ue = UE.getEditor('editor',item);
				UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
			    UE.Editor.prototype.getActionUrl = function(action) {  
			        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {  
			            return '../../uploadImg';  
			        } else {  
			            return this._bkGetActionUrl.call(this, action);  
			        }  
			    }  
				</script>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="/static/layui/layui.js"></script>
	<script type="text/javascript" src="/static/js/newsAdd.js"></script>
</body>
</html>