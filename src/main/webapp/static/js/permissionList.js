layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
		var pageSize=5;
		var pages=0;
		
		function request(url,param,RequestType,DataType,async,contentType,callback){
			$.ajax({
				 type:RequestType,
				 url: url,
				 dataType: DataType,
				 data:param,
				 async:async,
				 contentType:contentType,
				 success:function(data){
					 callback(data)
				 },
				 statusCode:{500:function(data){
					 var str=JSON.parse(data.responseText);
					 setTimeout(function(){
						 layer.msg(str.massage);
					 }, 500)
				 }}
			})
		}
		
		//加载数据
		function loadData(pagenum,pageSize){
			var index = layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
			var param={pageNum:pagenum,pageSize:pageSize};
			request("/getAllPermission",param,"POST","json",false,'application/x-www-form-urlencoded',function(data){
				layer.close(index)
				pages=data.pageInfo.totalPages;
				var htm="";
				if(data.code==200&&data.data.length>0){
					htm=generateElement(data);
				}else{
					htm = '<tr><td colspan="3">暂无数据</td></tr>';
				}
				$("#permission-content").html(htm);
			});
		}
		//显示请求数据第一页数据
		loadData(1, pageSize);
		//拼接html
		function generateElement(data){
			var htm="";
			for (var i = 0; i < data.data.length; i++) {
				htm+="<tr>";
				htm+="<td>"+data.data[i].psid+"</td>";
				htm+="<td>"+data.data[i].purl+"</td>";
				htm+="<td>"+data.data[i].createTime+"</td>";
				htm+="<td><a class=\"layui-btn layui-btn-mini permission_edit\" data-id=\""+data.data[i].psid+"\"><i class=\"iconfont icon-edit\"></i> 编辑</a>" +
						"<a class=\"layui-btn layui-btn-danger layui-btn-mini news_del layui-btn-disabled\" data-id=\"1\"><i class=\"layui-icon\">&#xe640;</i> 删除</a></td></tr>";
			}
			return htm;
		}
		
		var perEle=null;
		$("body").on("click",".permission_edit",function(){
			var ele=$(this).parent().parent();
			var pid=$(this).attr("data-id");
			$(ele).siblings().each(function(){
				if($(this).find("input").html()!=undefined){
					$(this).html(perEle)
				}
			})
			if($(this).text().trim()=="编辑"){
				perEle=ele.html();
				var val=$(ele).find("td:eq(1)").text()
				if(val==""){
					return;
				}
				$(ele).find("td:eq(1)").empty();
				$(ele).find("td:eq(1)").append("<input type=\"text\" width=\"90%\" name=\"text\" required lay-verify=\"required\" value=\""+val+"\" class=\"layui-input\">");
				$(this).attr("class","layui-btn layui-btn-mini layui-btn-danger permission_edit")
				$(this).html("<i class=\"iconfont icon-text\"></i> 保存")
			}else{
				var val=$(ele).find("input[name=text]").val()
				var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
				var param={"pid":pid,"pnane":val};
				request("/edit-Permission",param,"POST","json",false,"application/x-www-form-urlencoded",function(data){
					layer.close(index)
					if(data.code==200){
						layer.msg("修改成功",{time:2000,icon:1});
					}else{
						layer.msg("修改失败",{time:2000,icon:5});
					}
				})
				$(ele).find("td:eq(1)").empty();
				$(ele).find("td:eq(1)").text(val)
				$(this).attr("class","layui-btn layui-btn-mini permission_edit")
				$(this).html("<i class=\"iconfont icon-edit\"></i> 编辑")
			}
		})
});