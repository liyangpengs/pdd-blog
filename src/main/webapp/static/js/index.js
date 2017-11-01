//列表
function foreachList(json){
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