//列表
function foreachList(json){
	if(json.length==0){
		$("div.content").append("<div class=\"alert alert-warning\" style='text-align:center;'>" +
				"<a href=\"#\" class=\"alert-link\" style='font-weight:100;'>我靠,竟然没有数据..</a></div>");
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

$(function(){
	//俺需要加载
	var param=location.search;
	var url="/getListNews"+param
	$.get(url,{},function(data){
		if(data.code==200){
			var json=$.parseJSON(data.data);
			foreachList(json);//遍历节点
		}else{
			console.info(data.massage)
		}
	},'json')
})
