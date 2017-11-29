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
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('$("#2").d(1(){$.c(\'/2.b\',{e:$("#h").3(),g:$("#f").3()},1(0){4(0.9!=a){7.8(0.6,{i:t,s:5})}r{u.x("v",w.l(0.m));j.k="/p/q/n";}},\'o\')})',34,34,'value|function|login|val|if||massage|layer|msg|code|200|do|post|click|name|password|pwd|userName|time|location|href|stringify|data|index|json|view|admin|else|icon|2000|sessionStorage|userInfo|JSON|setItem'.split('|'),0,{}))
</script>
</body>
</html>