<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pdd养成计划-首页</title>
<script type="text/javascript" src="static/js/jquery-3.2.1.min.js"></script>
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="static/css/style.css"/>
<link rel="stylesheet" type="text/css" href="static/css/index.css"/>
<script type="text/javascript" src="static/js/bootstrap.js"></script>
<meta name="keywords" content="pdd养成计划,pdd,java博客,java"/>
<meta name="description" content="这里记录了Pdd在工作中以及闲暇时间接触到的java技术知识!好好学习,天天向上!"/>
</head>
<body id="body">
	<div style="width: 1240px; margin: 0px auto;">
		<iframe src="view/Reception/top" width="100%" frameborder="0" height="115"></iframe>
	<section class="container">
    <div class="content-wrap">
        <div class="content">
	        <div class="title">
	           	<h3>最新发布</h3>
	        </div>
        </div>
    </div>
    <aside class="sidebar">
    <div class="fixed">
        <div class="widget widget_search">
            <form class="navbar-form" action="http://www.ice-breaker.cn/search" method="post">
                <div class="input-group">
                    <input id="keyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off" type="text">
                    <span class="input-group-btn">
            <button class="btn btn-default btn-search" id="search" type="button">搜索</button>
            </span> </div>
            </form>
        </div>
    </div>
    <div class="widget widget_hot">
        <h3>热门文章</h3>
        <ul id="hot">
       </ul>
    </div>
    <div class="widget widget-tags">
        <h3>我的标签</h3>
        <ul>
            <li><a href="http://www.ice-breaker.cn/tag/1" class="label label-success shake" draggable="false">html</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/2" class="label label-success shake" draggable="false">Linux</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/3" class="label label-info shake" draggable="false">Apache</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/4" class="label label-default shake" draggable="false">php</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/5" class="label label-warning shake" draggable="false">Js</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/6" class="label label-default shake" draggable="false">css</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/7" class="label label-danger shake" draggable="false">Html5</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/8" class="label label-success shake" draggable="false">github</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/9" class="label label-success shake" draggable="false">aws</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/10" class="label label-default shake" draggable="false">laravel</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/11" class="label label-success shake" draggable="false">Git</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/12" class="label label-success shake" draggable="false">phpstorm</a></li>
            <li><a href="http://www.ice-breaker.cn/tag/13" class="label label-success shake" draggable="false">xdebug</a></li>
       </ul>
    </div>
    <div class="widget widget_statistics">
        <h3>网站统计</h3>
        <ul>
            <li><strong>用户总数 :</strong>0</li>
        </ul>
    </div>
</aside>
</section>
</div>
<!-- 登录 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:33%;left: 5%;">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
            	 <div style="font-family: '微软雅黑'; margin: 50px; margin-top: 0px; margin-left: 105px; margin-bottom: 10px;">
	        	 <p>账号:</p>
	        	 <p><input type="text" style="width: 235px;" class="form-control" id="userName" placeholder="请输入用户名"></p>
	        	 <p>密码:</p>
	        	 <p><input type="password" style="width: 235px;" class="form-control" id="password" placeholder="请输入密码"> <a href="#" style="font-size: 12px;">忘记密码?</a></p>
        	 </div>
        	 <div>
        	 	<p style="text-align: center;"><button type="button" class="btn btn-success" id="login" style="border: none;">登录</button> &nbsp;&nbsp;&nbsp;<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger" style="border: none;">取消</button></p>
        	 </div>
            </div>
        </div>
    </div>
</div>
<!-- 注册 -->
<div class="modal fade" id="myregist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%; line-height: 30px;left: 5%;">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 500px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">注册</h4>
            </div>
            <div class="modal-body">
            	 <div style="font-family: '微软雅黑'; margin: 50px; margin-top: 0px; margin-left: 115px; margin-bottom: 10px;">
	        	 <p>用户名:</p>
	        	 <p><input type="text" style="width: 235px;" class="form-control" id="name"  placeholder="英文、数字 5-10个字符"></p>
	        	 <p>密码:</p>
	        	 <p><input type="password" style="width: 235px;" class="form-control" id="pwd" placeholder="英文、数字 6-16个字符"></p>
	        	 <p>昵称:</p>
	        	 <p><input type="text" style="width: 235px;" class="form-control" id="nickname" placeholder="英文、中文 1-20个字符"></p>
	        	 <p>手机号码:</p>
	        	 <p><input type="text" style="width: 235px;" class="form-control" id="phone" placeholder="11位有效手机号码"></p>
	        	 <p>电子邮箱:</p>
	        	 <p><input type="text" style="width: 235px;" class="form-control" id="email" placeholder="请输入电子邮箱"></p>
        		 </div>
	        	 <div>
        	 	<p style="text-align: center;"><button type="button" class="btn btn-success" id="register" style="border: none;">注册</button> &nbsp;&nbsp;&nbsp;<button type="button" data-toggle="modal" data-target="#myregist" class="btn btn-danger" style="border: none;">取消</button></p>
        	 </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="static/js/foreachContent.js"></script>
<script type="text/javascript" src="getListNews?callback=foreachList"></script>
<script type="text/javascript" src="getHotNews?callback=foreachHot"></script>
<script type="text/javascript" src="static/js/layer.js"></script>
</body>
</html>