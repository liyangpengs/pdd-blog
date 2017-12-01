var hasData=true;
//列表
function foreachList(json){
	if(json.length==0){
		$("div.content").append("<div class=\"alert alert-warning\" style='text-align:center;margin-top:5px;margin-bottom: 5px;'>" +
				"<a href=\"#\" class=\"alert-link\" style='font-weight:100;'>我靠,竟然没有数据了[%>_<%]!</a></div>");
		hasData=false;
	}else{
		for (var i = 0; i < json.length; i++) {
			var html="<article class=\"excerpt excerpt-1\"><a class=\"focus\" href='"+json[i].url+"' draggable=\"false\">"+
				"<img class=\"thumb\" data-original=\"\" src='"+json[i].imgUrl+"' alt=\"\" draggable=\"false\"></a>"+
				"<header><a class=\"cat\" href='"+json[i].url+"' draggable=\"false\">"+json[i].news_type.tname+"<i></i></a>"+
				"<h2><a href='"+json[i].url+"' title=\"\" draggable=\"false\">"+json[i].title+"</a></h2>"+
				"</header><p class=\"meta\"><time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>"+json[i].publishtime+"</time>"+
				"<span class=\"views\"><i class=\"glyphicon glyphicon-eye-open\"></i>共"+json[i].seecount+"人查看</span>"+
				"<a class=\"comment\" href=\"http://www.ice-breaker.cn/article.html#comment\" draggable=\"false\"></a></p>"+
				"<p class=\"note\">"+json[i].descs+"</p></article>";
			//为内容添加元素
			$("div.content").append(html);
		}	
	}
}
var pagenum=1;
var pagesize=5;
var url="";
$(function(){
	var ua = navigator.userAgent;
	//判断是否手机访问
	var ipad = ua.match(/(iPad).*OS\s([\d_]+)/),
	isIphone = !ipad && ua.match(/(iPhone\sOS)\s([\d_]+)/),
	isAndroid = ua.match(/(Android)\s+([\d.]+)/),
	isMobile = isIphone || isAndroid;
	if(isMobile) {
		pagesize=10;
    }
	//页面重新加载
	pagenum=1;
	var param=location.search;
	url="/getListNews"+param
	getNews(url,pagenum,pagesize);
	$(window).scroll(function(){
		var wScrollY = window.scrollY; // 当前滚动条位置    
	    var wInnerH = window.innerHeight; // 设备窗口的高度（不会变）    
	    var bScrollH = document.body.scrollHeight; // 滚动条总高度 
	    if (wScrollY + wInnerH >= bScrollH) {
	    	if(hasData){
	    		pagenum=pagenum+1;
		    	getNews(url,pagenum,pagesize)
	    	}
	    }  
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

function getNews(url,pagenum,pagesize){
	$.get(url,{pageNum:pagenum,pageSize:pagesize},function(data){
		if(data.code==200){
			var json=$.parseJSON(data.data);
			foreachList(json);//遍历节点
		}else{
			console.info(data.massage)
		}
	},'json')
}