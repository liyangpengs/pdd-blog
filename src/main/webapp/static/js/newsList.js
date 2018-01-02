layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	var pageSize=5;
	var pages=0;
	var keyword="";
	
	//加载数据
	function loadData(pagenum,pageSize){
		var index = layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
		var param={pageNum:pagenum,pageSize:pageSize,keyword:keyword};
		request("/getAllNews",param,"POST","json",false,'application/x-www-form-urlencoded',function(data){
			layer.close(index)
			pages=data.pageInfo.totalPages;
			var htm="";
			if(data.code==200&&data.data.length>0){
				htm=generateElement(data);
			}else{
				htm = '<tr><td colspan="8">暂无数据</td></tr>';
			}
			$(".news_content").html(htm);
			form.render();
		});
	}
	
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
	
	//显示请求数据第一页数据
	loadData(1, pageSize);
	//设置可见
	form.on('switch(isShow)',function(data){
		var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
		var param={nid:$(data.elem).attr("data-id"),open:data.elem.checked};
		request('/NewsVisible',param,'POST','json',true,'application/x-www-form-urlencoded',function(Value){
			layer.close(index);
			if(Value.code<100){
				layer.msg("状态修改失败！");
			}else{
				layer.msg("状态修改成功！");
			}
		});
	})
	//设置置顶
	form.on('switch(isTop)',function(data){
		var index = layer.msg('置顶中，请稍候',{icon: 16,time:false,shade:0.8});
		var param={nid:$(data.elem).attr("data-id"),open:data.elem.checked};
		request('/updateNewsIstop',param,'POST','json',true,'application/x-www-form-urlencoded',function(Value){
			layer.close(index);
			if(Value.code<100){
				layer.msg("状态修改失败！");
			}else{
				layer.msg("状态修改成功！");
			}
		});
	})
	//删除数据
	$("body").on("click",".news_del",function(){
		var del=$(this);
		layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, function(index){
			if(index>=1){
				var ms = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
				var delIdArr=[];
				delIdArr.push(parseInt($(del).attr("data-id")));
				request('/deleteNews',JSON.stringify(delIdArr),'POST','json',true,"application/json",function(Value){
					layer.close(ms);
	            	  if(Value.code>100){
	            		  layer.msg("删除成功！");
	            	  }else{
	            		  layer.msg("删除失败！");
	            	  }   
				});
			}
		});
	})
	//拼接html
	function generateElement(data){
		var htm="";
		for (var i = 0; i < data.data.length; i++) {
			htm+="<tr>";
			htm+="<td><input type=\"checkbox\" name=\"checked\" lay-skin=\"primary\" data-id="+data.data[i].nid+" lay-filter=\"choose\"></td>"
			htm+="<td>"+data.data[i].title+"</td>";
			htm+="<td>"+data.data[i].author.snickName+"</td>";
			var istop=data.data[i].istop==1?"checked":"";
			htm+="<td><input type=\"checkbox\" name=\"top\" data-id="+data.data[i].nid+" lay-skin=\"switch\" lay-text=\"是|否\" lay-filter=\"isTop\" "+istop+"></td>";
			var visible=data.data[i].visible==1?"checked":"";
			htm+="<td><input type=\"checkbox\" name=\"show\" data-id="+data.data[i].nid+" lay-skin=\"switch\" lay-text=\"是|否\" lay-filter=\"isShow\" "+visible+"></td>";
			htm+="<td>"+data.data[i].publishtime+"</td>";
			htm+="<td><a class=\"layui-btn layui-btn-mini news_edit\"><i class=\"iconfont icon-edit\"></i> 编辑</a>"
				+"<a class=\"layui-btn layui-btn-danger layui-btn-mini news_del\" data-id="+data.data[i].nid+"><i class=\"layui-icon\">&#xe640;</i> 删除</a></td>";
		}
		return htm;
	}
	//分页插件
	laypage({
		cont : "page",
		pages : pages,
		jump : function(obj,first){
			if(!first){
				loadData(obj.curr, pageSize,keyword)
			}
		}
	})
	//搜索按钮
	$(".search_btn").click(function(){
		keyword=$(".search_input").val();
		loadData(1, pageSize,keyword);
	})
	//点击添加文件按钮
	$(window).one("resize",function(){
		$(".newsAdd_btn").click(function(){
			var index = layui.layer.open({
				title : "添加文章",
				type : 2,
				content : "newsAdd",
				success : function(layero, index){
					setTimeout(function(){
						layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					},500)
				}
			})			
			layui.layer.full(index);
		})
	}).resize();
	//全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):not([name="top"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});
	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.news_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.news_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				if(index>0){
					var delIdArr=[];
					for(var j=0;j<$checked.length;j++){
						delIdArr.push(parseInt($($checkbox[j]).attr("data-id")))
	            	}
					var ms = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
					request('/deleteNews',JSON.stringify(delIdArr),'POST','json',true,"application/json",function(Value){
						layer.close(ms);
		            	  if(Value.code>100){
		            		  layer.msg("删除成功！");
		            	  }else{
		            		  layer.msg("删除失败！");
		            	  }   
					});
				}
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})
})