$(function() {
	var boardId = $("input[name='boardId']").val();
	var downUrl = "/download/" + boardId;
	var dataUrl = "/data/" + boardId;

	// 注册按钮事件
	$("#btn_action").on("click", "a", function(evt){
		var name = $(evt.currentTarget).attr("name");
		if (name == 'query') {
			$("input[name='pageNumber']").val("1");
			query();
		} else if ('excel' == name) {
			downLoad(downUrl, getQueryCondition());
		}
		return false;
	});
	
	// DownLoad Action
	$('#downMenu').menu({
		onClick:function(item){
			var params = getQueryCondition();
			params['downType'] = item.name;
			downLoad(downUrl, params);
		}
	});
	
	// 初始化 EasyUI DataGrid
	$('#tableData').datagrid({
		title:"",remoteSort:true,
		striped:true,singleSelect:true,
	    showHeader:true,showFooter:true,
		onSortColumn:function(sort,order){
			$("input[name='pageNumber']").val("1");
			$("input[name='orderBy']").val(sort);
			$("input[name='orderType']").val(order);
			query();
		}
	});
	
	//设置分页组件
	$("#pager").pagination({
		pageSize:20,pageList:[10,20,30,50,100],
		layout:['first','sep','links','sep','last','sep','list'],
		onSelectPage:function (pageNumber, pageSize) {
			$("input[name='pageNumber']").val(pageNumber);
			$("input[name='pageSize']").val(pageSize);
			query();
		}
	});

	// 查询与显示数据
	function query () {
		var params = getQueryCondition();
		
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
				$.messager.alert('ERROR',textStatus,'error');
			},complete:function(){
				$("#tableData").datagrid('loaded');
			}
		});
	}
	
	// 加载数据表格
	function showTable (result) {
		
		var tableData = result['tableData'];
		
		// 设置表格动态列, 加载表格数据
		$('#tableData').datagrid({
			data:tableData
		});
		
		// 更新分页控件
		var curPage = $("input[name='pageNumber']").val();
		$("#pager").pagination("refresh", {
			total:tableData.total,pageNumber:curPage
		});
	}
	
	/****** Get Query Condition *****/
	function getQueryCondition () {
		var params = {};
		
		// DateSpan
		params["startDate"] = $('#startDate').datebox('getValue');
		params["endDate"] = $('#endDate').datebox('getValue');
		
		// Order
		params["orderBy"] = $("input[name='orderBy']").val();
		params["orderType"] = $("input[name='orderType']").val();
		
		// Pagination
		params["pageNumber"] = $("input[name='pageNumber']").val();
		params["pageSize"] = $("input[name='pageSize']").val();
		
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
	// 初始化
	query();
});