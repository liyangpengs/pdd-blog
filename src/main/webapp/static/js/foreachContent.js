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
//热门
function foreachHot(json){
	for (var i = 0; i < json.length; i++) {
		var html="<li><a href=\""+json[i].url+"\" draggable=\"false\">"+
		"<span class=\"thumbnail\"><img class=\"thumb\" src=\""+json[i].imgUrl+"\" alt=\"\" draggable=\"false\"></span>"+
		"<span class=\"text\">"+json[i].title+"</span>"+
		"<span class=\"muted\"><i class=\"glyphicon glyphicon-time\"></i>"+json[i].publishtime+"</span>"+
		"<span class=\"muted\"><i class=\"glyphicon glyphicon-eye-open\"></i>"+json[i].seecount+"</span></a></li>";
		$("#hot").append(html);
	}
}
$(function(){
	var nameisok=false;
	var pwdisok=false;
	var nicknameisok=false;
	var phoneisok=false;
	var emailisok=false;
	/**
	 * ----------------------------------------------------以下为正则验证注册信息区域--------------------------------------------------
	 */
	//用户名
	$("#name").blur(function(){
		var val=$(this).val();
		var regex=/^[a-zA-Z0-9]{5,10}$/;
		if(!regex.test(val)){
			layer.msg('用户名格式有误',{time:2000,icon:5});
		}else{
			nameisok=true;
		}
	})
	//密码
	$("#pwd").blur(function(){
		var val=$(this).val();
		var regex=/^[a-zA-Z0-9]{6,16}$/;
		if(!regex.test(val)){
			layer.msg('密码格式有误',{time:2000,icon:5});
		}else{
			pwdisok=true;
		}
	})
	//昵称
	$("#nickname").blur(function(){
		var val=$(this).val();
		var regex=/^([a-zA-Z]|[\u4E00-\u9FA5]){1,20}$/;
		if(!regex.test(val)){
			layer.msg('昵称格式有误',{time:2000,icon:5});
		}else{
			nicknameisok=true;
		}
	})
	//手机号码
	$("#phone").blur(function(){
		var val=$(this).val();
		var regex=/^1[34578]\d{9}$/;
		if(!regex.test(val)){
			layer.msg('请确定手机号真实有效',{time:2000,icon:5});
		}else{
			phoneisok=true;
		}
	})
	//邮箱
	$("#email").blur(function(){
		var val=$(this).val();
		var regex=/^(\w|\d|_){1,25}@(\w|\d){2,6}\.(com|cn|com.cn)$/;
		if(!regex.test(val)){
			layer.msg('邮箱格式有误',{time:2000,icon:5});
		}else{
			emailisok=true;
		}
	})
	/**
	 * ------------------------------------------------以下为注册区域验证信息合法性---------------------------------------------
	 */
	$("#register").click(function(){
		if(!nameisok){
			$("#name").focus();
			layer.msg('用户名格式有误',{time:2000,icon:5});
			return nameisok;
		}
		if(!pwdisok){
			$("#pwd").focus();
			layer.msg('密码格式有误',{time:2000,icon:5});
			return pwdisok;
		}
		if(!nicknameisok){
			$("#nickname").focus();
			layer.msg('昵称格式有误',{time:2000,icon:5});
			return nicknameisok;
		}
		if(!phoneisok){
			$("#phone").focus();
			layer.msg('请确定手机号真实有效',{time:2000,icon:5});
			return phoneisok;
		}
		if(!emailisok){
			$("#email").focus();
			layer.msg('邮箱格式有误',{time:2000,icon:5});
			return emailisok;
		}
		loading = layer.load(1, {
            shade: [0.5, '#393D49']
        });
		//验证注册信息
		$.post('/register.do',{name:$("#name").val(),pwd:$("#pwd").val(),
			nickname:$("#nickname").val(),phone:$("#phone").val(),
			email:$("#email").val()},function(ret){
			layer.close(loading);
			//判断注册是否成功
			if(ret.code==200){
				//隐藏注册dialog模式
				$("#myregist").modal('hide');
				layer.msg(ret.msg,{time:2000,icon:1});
			}else if(ret.code==101){
				$("#name").focus();
				layer.msg(ret.msg,{time:2000,icon:5});
			}else if(ret.code==102){
				$("#email").focus();
				layer.msg(ret.msg,{time:2000,icon:5});
			}
		},'json')
	})
	/**
	 * ------------------------------------------以下为登录区域-----------------------------------------------
	 */
	$("#login").click(function(){
		$.post('/login.do',{name:$("#userName").val(),pwd:$("#password").val()},function(value){
			if(value.code!=200){
				layer.msg(value.msg,{time:2000,icon:5});
			}else{
				//刷新当前页
				location.reload(true);
			}
		},'json')
	})
})