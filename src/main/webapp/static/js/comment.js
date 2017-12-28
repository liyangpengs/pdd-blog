$(function(){
	getComment();
	$("#publish-comment").click(function(){
		var content=$(".comment-input").val();
		if(content.length<1||content.replace(" ","").length<1){
			layer.msg('评论内容不能为空或全为空格',{time:2000,icon:5});
		}else{
			$.post("/addComment",{"nid":HtmlInfo.nid,"content":content},function(value){
				if(value.code==200){
					$(".comment-input").val("");
					layer.msg('发布成功',{time:2000,icon:1});
					getComment();
				}else{
					layer.msg(value.massage,{time:2000,icon:5});
				}
			},'json')
		}
	})
})

function ck(data){
	var replayContent=$("#reply-content").val();
	$.post("/reply",{"ids":data,"content":replayContent},function(value){
		if(value.code==200){
			layer.msg('回复成功',{time:2000,icon:1});
			getComment();
			$(".reply-buttom").remove();
		}else{
			layer.msg(value.massage,{time:2000,icon:5});
		}
	},'json')
}

function hf(a){
	$(".reply-buttom").remove();
	var replyuser=$(a).attr("reply-user");
	var data=$(a).attr("data-id");
	var html="<div class=\"reply-buttom\"><textarea rows=\"\" id=\"reply-content\" style=\"font-size:12px;padding-left: 0.4em;;width:100%;margin-left: 2px;height: 80px;\" maxlength=\"100\" cols=\"\" placeholder=\""+replyuser+"\"></textarea>";
		html+="<div class=\"comment-reply\"><button onclick=\"ck('"+data+"')\">回复</button></div><div>";
	$(a).parent().append(html);
}

function getComment(){
	$(".comment-buttom").empty();
	$.get('/getComment',{"nid":HtmlInfo.nid},function(value){
		addCommentElement(value);
	},'json')
}

function addCommentElement(data){
	if(data.code==200){
		for (var i = 0; i < data.data.length; i++) {
			var html="<div class=\"comments\">";
			html+="<div class=\"comments-left\">";
			html+="<img alt=\"\" src=\""+data.data[i].author.userHead+"\">";
			html+="</div><div class=\"comments-rigth\">";
			html+="<div class=\"comments-top\"><font>"+data.data[i].author.snickName+"</font> ：</div>";
			html+="<div class=\"comments-content\"><p>"+data.data[i].content+"</p></div>";
			html+="<div class=\"comments-publishTime\">发表时间："+data.data[i].publishTime+" <a href=\"javascript:void(0)\" onclick=\"hf(this)\" data-id=\""+data.data[i].author.sid+"-"+data.data[i].cid+"\" class=\"Reply\" reply-user=\"@"+data.data[i].author.snickName+"\">回复</a></div></div></div>";
			$(".comment-buttom").append(html);
			for (var j = 0; j < data.data[i].comments.length; j++) {
				var rehtml="<div class=\"comments\">";
				rehtml+="<div class=\"comments-left\">";
				rehtml+="<img alt=\"\" src=\""+data.data[i].comments[j].replyAuthor.userHead+"\">";
				rehtml+="</div><div class=\"comments-rigth\">";
				rehtml+="<div class=\"comments-top\"><font>"+data.data[i].comments[j].replyAuthor.snickName+"</font> <span>回复</span>  <font>"+data.data[i].comments[j].bereplyAuthor.snickName+"</font> ：</div>";
				rehtml+="<div class=\"comments-content\"><p>"+data.data[i].comments[j].content+"</p></div>";
				rehtml+="<div class=\"comments-publishTime\">发表时间："+data.data[i].comments[j].publishTime+" <a href=\"javascript:void(0)\" onclick=\"hf(this)\" data-id=\""+data.data[i].comments[j].replyAuthor.sid+"-"+data.data[i].cid+"\" class=\"Reply\" reply-user=\"@"+data.data[i].comments[j].replyAuthor.snickName+"\">回复</a></div></div></div>";
				$(".comment-buttom").append(rehtml);
			}
		}
	}else{
		layer.msg("加载评论异常",{time:2000,icon:5});
	}
}
