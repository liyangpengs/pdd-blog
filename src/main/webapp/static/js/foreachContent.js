var StyleClass=[];
StyleClass.push("label label-success shake");
StyleClass.push("label label-default shake");
StyleClass.push("label label-primary shake");
StyleClass.push("label label-info shake");
StyleClass.push("label label-warning shake");
StyleClass.push("label label-danger shake");
var element="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"top:33%;left: 5%;z-index:999999;\">     <div class=\"modal-dialog\">         <div class=\"modal-content\">             <div class=\"modal-header\">                 <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>                 <h4 class=\"modal-title\" id=\"myModalLabel\">登录</h4>             </div>             <div class=\"modal-body\">             	 <div id=\"loginFrom\"> 	        	 <p>账号:</p> 	        	 <p><input type=\"text\" style=\"width: 235px;\" class=\"form-control\" id=\"userName\" placeholder=\"请输入用户名\"></p> 	        	 <p>密码:</p> 	        	 <p><input type=\"password\" style=\"width: 235px;\" class=\"form-control\" id=\"password\" placeholder=\"请输入密码\"> <a href=\"#\" style=\"font-size: 12px;\">忘记密码?</a></p>         	 </div>         	 <div>         	 	<p style=\"text-align: center;\"><button type=\"button\" class=\"btn btn-success\" id=\"login\" style=\"border: none;\">登录</button> &nbsp;&nbsp;&nbsp;<button type=\"button\" data-toggle=\"modal\" data-target=\"#myModal\" class=\"btn btn-danger\" style=\"border: none;\">取消</button></p>         	 </div>             </div>         </div>     </div> </div><div class=\"modal fade\" id=\"myregist\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:999999;top:20%; line-height: 30px;left: 5%;\">     <div class=\"modal-dialog\">         <div class=\"modal-content\">             <div class=\"modal-header\">                 <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>                 <h4 class=\"modal-title\" id=\"myModalLabel\">注册</h4>             </div>             <div class=\"modal-body\">             	 <div id=\"loginFrom\"> 	        	 <p>用户名:</p> 	        	 <p><input type=\"text\" style=\"width: 235px;\" class=\"form-control\" id=\"name\"  placeholder=\"英文、数字 5-10个字符\"></p> 	        	 <p>密码:</p> 	        	 <p><input type=\"password\" style=\"width: 235px;\" class=\"form-control\" id=\"pwd\" placeholder=\"英文、数字 6-16个字符\"></p> 	        	 <p>昵称:</p> 	        	 <p><input type=\"text\" style=\"width: 235px;\" class=\"form-control\" id=\"nickname\" placeholder=\"英文、中文 1-20个字符\"></p> 	        	 <p>手机号码:</p> 	        	 <p><input type=\"text\" style=\"width: 235px;\" class=\"form-control\" id=\"phone\" placeholder=\"11位有效手机号码\"></p> 	        	 <p>电子邮箱:</p> 	        	 <p><input type=\"text\" style=\"width: 235px;\" class=\"form-control\" id=\"email\" placeholder=\"请输入电子邮箱\"></p>         		 </div> 	        	 <div>         	 	<p style=\"text-align: center;\"><button type=\"button\" class=\"btn btn-success\" id=\"register\" style=\"border: none;\">注册</button> &nbsp;&nbsp;&nbsp;<button type=\"button\" data-toggle=\"modal\" data-target=\"#myregist\" class=\"btn btn-danger\" style=\"border: none;\">取消</button></p>         	 </div>             </div>         </div>     </div> </div>";
//tag
function foreachTag(){
	$.get('/getNews_type',{},function(data){
		if(data.code==200){
			var json=$.parseJSON(data.data);
			for (var i = 0; i < json.length; i++) {
				var classStr=StyleClass[Math.floor(Math.random()*StyleClass.length)];
				var baseUrl=location.host;
				var html="<li><a href=\"http://"+baseUrl+"/?type="+json[i].tname+"\" class=\""+classStr+"\" draggable=\"false\">"+json[i].tname+"</a></li>";
				$("#news_tag").append(html);
				$("#moblinews_tag").append(html);
			}
		}else{
			console.info('加载Tag异常...');
		}
	},'json')
}
//热门
function foreachHot(json){
	for (var i = 0; i < json.length; i++) {
		var html="<li><a href=\""+json[i].url+"\" draggable=\"false\">"+
		"<span class=\"thumbnail\" style=\"background-color:#f8f8ee;\"><img class=\"thumb\" src=\""+json[i].imgUrl+"\" alt=\"\" draggable=\"false\"></span>"+
		"<span class=\"text\">"+json[i].title+"</span>"+
		"<span class=\"muted\"><i class=\"glyphicon glyphicon-time\"></i>"+json[i].publishtime+"</span>"+
		"<span class=\"muted\"><i class=\"glyphicon glyphicon-eye-open\"></i>"+json[i].seecount+"</span></a></li>";
		$("#hot").append(html);
	}
}
$(function(){
	foreachTag();
	$("body").append(element);
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
		console.info($("#nickname").val())
		//验证注册信息
		$.post('/register.do',{sname:$("#name").val(),spwd:$("#pwd").val(),
			snickName:$("#nickname").val(),sphone:$("#phone").val(),
			semail:$("#email").val()},function(ret){
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
	 * ------------------------------------------以下为前台登录区域-----------------------------------------------
	 */
	$("#login").click(function(){
		$.post('/login.do',{name:$("#userName").val(),pwd:$("#password").val()},function(value){
			if(value.code!=200){
				layer.msg(value.massage,{time:2000,icon:5});
			}else{
				sessionStorage.setItem("userInfo", JSON.stringify(value.data));
				//刷新当前页
				location.reload(true);//(线上开启此行代码)
			}
		},'json')
	})
	//验证登录
	var info=sessionStorage.getItem("userInfo");
	if(info==null){
		$("#userInfo img").attr("src","/static/imgs/logo.jpg");
		$("#userInfo a").text("您好,请登录");
	}else{
		var a=$.parseJSON(info);
		$("#userInfo img").attr("src",a.userHead);
		$("#userInfo a").text("您好,"+a.snickName);
		editList();
	}
	//登录成功显示列表
	function editList(){
		var html="<li><a href=\"javascript:void(0)\" style=\"color: black;\" >个人中心</a></li>"+
				"<li><a href=\"javascript:void(0)\" style=\"color: black;\" >修改密码</a></li>"+
				"<li><a href=\"javascript:void(0)\" style=\"color: black;\" >退出</a></li>";
		$("#userMenu ul").empty();
		$("#userMenu ul").append(html);
	}
})