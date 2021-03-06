<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="baidu-site-verification" content="bYiyhqLNU8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<title>Pdd养成计划-${title}</title>
<script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
<link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="/static/css/style.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/index.css"/>
<script type="text/javascript" src="/static/js/bootstrap.js"></script>
<link rel="icon" href="/static/imgs/favicon.ico">
<meta name="keywords" content="${keyword}"/>
<meta name="description" content="${descs}"/>
<script type="text/javascript">
	$(function(){
		$("#user").hover(function(){
			$("#userMenu ul").show();
		},function(){
			$("#userMenu ul").hide();
		})
		$(".layui-hide").click(function(){
			$(".site-mobile-shade").show();
			$(".mobli-tab").animate({right:'0px'});
			$(".layui-hide").addClass("hide");
			$("#gotop").hide();
		})
		$(".site-mobile-shade").click(function(){
			$(".mobli-tab").animate({right:'-250px'});
			$(".layui-hide").removeClass("hide");
			$(this).hide();
		})
		$(window).scroll(function(){
		    if ($(window).scrollTop() > 100) {
		        $("#gotop").fadeIn();
		    } else {
		        $("#gotop").fadeOut();
		    }
		})
		$("#gotop").click(function () {
		    $('html,body').animate({
		        'scrollTop': 0
		    }, 500);
		});
	})
</script>
<script>
	var HtmlInfo=${selfInfo};
</script>
</head>
<body id="body">
	<div id="container">
		<div class="top">
			<a href="/"><div style="float: left;"><img src="/static/imgs/logo.jpg" width="90"  height="90" style="vertical-align: middle;"/><span style="color: green;">pdd养成计划</span></div></a>
			<div style="float: right;font-size: 12px;margin-top: 5px; margin-right: 15px;">
				<div id="user">
					<div id="userInfo"><img alt="" src="/static/imgs/logo.jpg" width="70" height="70">&nbsp;<a href="" style="color: black;">您好,请登录</a></div>
					<div id="userMenu">
						<ul>
							<li data-toggle="modal" data-target="#myModal"><a href="javascript:void(0)" style="color: black;" >登录</a></li>
							<li data-toggle="modal" data-target="#myregist"><a href="javascript:void(0)" style="color: black;" >注册</a></li>
						</ul>
					</div>
				</div>
			</div>
	</div>
	<section class="container">
    <div class="content-wrap">
        <div class="content">
            <header class="article-header">
            <h1 class="article-title">
                <a href="javascript:void(0);" draggable="false">${title}</a>
            </h1>
            <div class="article-meta">
                <span class="item article-meta-time">
	                <time class="time" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="时间：2017-03-22 12:46:51">
                        <i class="glyphicon glyphicon-time">${publishTime}</i>
                    </time>
	            </span>
                <span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="来源：pdd养成计划">
                    <i class="glyphicon glyphicon-globe"></i> pdd养成计划
                </span>
                <span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="栏目：${news_type}">
                    <i class="glyphicon glyphicon-list"></i>
                    <a href="###" title="" draggable="false">${news_type}</a>
                </span>
            </div>
        </header>

        <article class="article-content">
        	${content}
        </article>
        <div style="margin:5px 0px 5px 0px;font-size:12px;">
        	 <span>分享到:</span>
        	  <div class="bdsharebuttonbox" style="display: inline;">
				   <a href="#" class="bds_more" data-cmd="more" style="float: none;"></a>
				   <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间" style="float: none;"></a>
				   <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博" style="float: none;"></a>
				   <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博" style="float: none;"></a>
				   <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网" style="float: none;"></a>
				   <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信" style="float: none;"></a>
  				</div> 
  			<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":HtmlInfo.title,"bdDesc":HtmlInfo.desc,"bdMini":"2","bdMiniList":false,"bdPic":"https://pdd-java.top"+HtmlInfo.img,"bdStyle":"0","bdSize":"24"},"share":{},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
        </div>
        <div class="comment">
        	<div class="comment-top">
        		<span>评论:</span>
        		<textarea rows="5"  class="comment-input" maxlength="100" cols="40" placeholder="留下你的评论吧[最多100字]"></textarea>
        		<div class="publish-comment"><button id="publish-comment">发布评论</button></div>
        	</div>
        	<div class="comment-buttom">
        	
			</div>
        </div>
</div>
    <aside class="sidebar">
    <div class="fixed">
        <div class="widget widget_search">
            <form class="navbar-form">
                <div class="input-group">
                    <input id="keyword" class="form-control" size="10" placeholder="请输入关键字" maxlength="15" autocomplete="off" type="text">
                    <span class="input-group-btn">
            			<button class="btn btn-default btn-search" id="search" type="button">搜索</button>
            		</span>
            	</div>
            </form>
        </div>
    </div>
    <div class="widget widget_hot">
        <h3>热门文章</h3>
        <ul id="hot">
       </ul>
    </div>
    <div class="widget widget-tags">
    	<!-- 类似分类显示文章 -->
        <h3>我的标签</h3>
	       	 <ul id="news_tag">
                
			 </ul>
    </div>
</aside>
</section>
<div style="text-align: center; height: 30px;border: 1px solid #EAEAEA">
	<p style="font-size: 12px;margin-top: 8px;">Copyright © 2017<a href="http://pdd-java.top" draggable="false" style="color: black;">pdd养成计划</a> &amp; 版权所有   湘ICP备17020198号</p>
</div>
</div>
<div id="gotop">
	<a href="javascript:void(0)" class="gotop"></a>
</div>
<div class="layui-hide">
	<a href="javascript:void(0)" class="leftButtom"></a>
</div>
<div class="mobli-tab">
 	<div class="weather">
			    	<div id="tp-weather-widget"></div>
					<script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
					<script>tpwidget("init", {
					    "flavor": "slim",
					    "location": "WX4FBXXFKE4F",
					    "geolocation": "enabled",
					    "language": "zh-chs",
					    "unit": "c",
					    "theme": "chameleon",
					    "container": "tp-weather-widget",
					    "bubble": "disabled",
					    "alarmType": "badge",
					    "color": "#FFFFFF",
					    "uid": "U9EC08A15F",
					    "hash": "039da28f5581f4bcb5c799fb4cdfb673"
					});
					tpwidget("show");</script>
	</div>
	<div id="mobli-serach"><input type="text" id="moblie-keyWords"/><button id="moblie-search">搜索</button></span></div>
	<div class="widget widget-tags">
		<h3>我的标签:</h3>
		<ul id="moblinews_tag">
       </ul>
	</div>
</div>
<div class="site-mobile-shade"></div>
<script type="text/javascript" src="/static/js/foreachContent.js"></script>
<script type="text/javascript" src="/static/js/comment.js"></script>
<script type="text/javascript" src="/getHotNews?callback=foreachHot"></script>
<script type="text/javascript" src="/static/js/layer.js"></script>
<!--百度自动推送 -->
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
   	if (curProtocol === 'https'){
    	bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
  	}
  	else{
  		bp.src = 'http://push.zhanzhang.baidu.com/push.js';
  	}
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>
</body>
</html>