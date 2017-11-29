<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link rel="icon" href="/static/imgs/favicon.ico">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/static/css/login.css" media="all" />
	<script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
	<script src="/static/js/rainyday.js" type="text/javascript"></script>
        <script>
            function run() {
                var image = document.getElementById('background');
                image.onload = function() {
                    var engine = new RainyDay({
                        image: this
                    });
                    engine.rain([ [1, 2, 8000] ]);
                    engine.rain([ [3, 3, 0.88], [5, 5, 0.9], [6, 2, 1] ], 100);
                };
                image.crossOrigin = 'anonymous';
                image.src = '/static/imgs/backimg.jpg';
            }
        </script>
</head>
<body onload="run()">
	<img id="background" alt="background" src="/static/imgs/backimg.jpg" crossorigin="anonymous">
	<canvas style="position: absolute; top: 0px; left: 0px;"></canvas>
	<div class="video_mask"></div>
	<div class="login">
	    <h1>pdd养成计划-管理登录</h1>
    	<div>
    		<div class="layui-form-item">
				<input class="layui-input" name="username" id="userName" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
	    	</div>
		    <div class="layui-form-item">
				<input class="layui-input" name="password" id="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
		    </div>
			<button class="layui-btn login_btn" id="login">登录</button>
    	</div>
	</div>
	<div style="z-index: 99;text-align: center;height: 30px;color: white;position: absolute;left: 50%;top: 50%;margin: 274px 0 0 -167px;">
			<p style="font-size: 12px;margin-top: 8px;">Copyright © 2017<a href="http://pdd-java.top" draggable="false" style="color: white;">pdd养成计划</a> &amp; 版权所有   湘ICP备17020198号</p>
	</div>
<script type="text/javascript" src="/static/js/layer.js"></script>
<script type="text/javascript">
$("#login").click(function(){
	$.post('/login.do',{name:$("#userName").val(),pwd:$("#password").val()},function(value){
		if(value.code!=200){
			layer.msg(value.massage,{time:2000,icon:5});
		}else{
			sessionStorage.setItem("userInfo", JSON.stringify(value.data));
			//刷新当前页
			location.href="/view/admin/index";//(线上开启此行代码)
		}
	},'json')
})
</script>
</body>
</html>