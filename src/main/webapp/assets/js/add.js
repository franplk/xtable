$(function() {
	var boardId = $("input[name='boardId']").val();
	var downUrl = "/download/" + boardId;
	var dataUrl = "/data/" + boardId;

	// 注册按钮事件
	$(".query-btns").on("click", "a", function(evt){
		var name = $(evt.currentTarget).attr("name");
		if (name == 'query') {
			$("input[name='pageNumber']").val("1");
			query();
		}
		return false;
	});

	// 查询与显示数据
	function query () {
		var params = getParams();
		
		$("#tableData").datagrid('loading');
		// Data Request
		$.ajax({
			type:"POST",data:params,
			dataType:"json", url:dataUrl,
			success:function(result) {
				if (!result.success) {
					$.messager.alert('ERROR',result.msg,'error');
					return;
				}
				showTable(result.data);
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('ERROR',"网络不佳，请稍后再试",'error');
			},complete:function(){
				$("#tableData").datagrid('loaded');
			}
		});
	}
	
	/****** Get Query Condition *****/
	function getParams () {
		var params = {};
		// Order
		params["orderBy"] = $("input[name='orderBy']").val();
		params["orderType"] = $("input[name='orderType']").val();
		
		// Parameters
		var queryPara = [];
		$(".con-filter").each(function() {
			var field = $(this).find('span.field').attr('name');
			var sign = $(this).find('span.sign').attr('name');
			var value = $(this).find('span.value').attr('name');
			queryPara.push(field + ':' + sign + ':' + value);
		});
		params['queryStr'] = queryPara.join(";");
		
		return params;
	}
});