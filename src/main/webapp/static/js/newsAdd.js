$.get('../../getNews_type',{},function(data){
		$(".newsLook").empty();
		if(data.code==200){
			var array=$.parseJSON(data.data);
			for (var i = 0; i < array.length; i++) {
				var option="<option value='"+array[i].tid+"-"+array[i].tname+"'>"+array[i].tname+"</option>";
				$(".newsLook").append(option);
			}
		}else{
			alert('后端加载文章类型失败!');
		}
	},"json")
var e;
layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate','upload'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		layui.upload({
			  url: '../../uploadImg'
			  ,title: '上传封面'
			  ,ext: 'jpg'
			  ,type: 'file'
			  ,success: function(res, input){
				  e=res.url;
			  }
		}); 
 	form.on("submit(addNews)",function(data){
 		var index = parent.parent.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		if(e==undefined){
 			setTimeout(function(){
 				parent.parent.layer.close(index);
 	 			parent.parent.layer.msg("请选择文章封面",{icon:5,time:2000});
 	 			return false;
 			}, 1000)
 			return false;
 		}
 		var title=$("input[name=title]").val();
 		var show=$("input[name=show]").is(":checked")==true?1:0;
 		var top=$("input[name=top]").is(":checked")==true?1:0;
 		var publishTime=$("input[name=publishTime]").val();
 		var news_type=$("select[name=news_type]").val();
 		var keyword=$("input[name=keyword]").val();
 		var describes=$("textarea[name=describe]").val();
 		var content=UE.getEditor('editor').getContent();
 		var tname="";
 		var imgUrl=e;
 		var param={"title":title,"visible":show,"publishtime":publishTime,"istop":top,"keyword":keyword,"descs":describes,"news_type.tid":news_type.split("-")[0],"imgUrl":imgUrl,"content":content,"news_type.tname":news_type.split("-")[1]};
 		$.post("../../generate",param,function(data){
 			setTimeout(function(){
 				layer.close(index);
 				if(data.code==200){
 					parent.parent.layer.msg("发布文章成功",{icon:1,time:2000});
 				}else{
 					parent.parent.layer.msg("发布文章失败",{icon:5,time:2000});
 				}
 			}, 1000)
 		},'json')
        return false;
 	})
})
