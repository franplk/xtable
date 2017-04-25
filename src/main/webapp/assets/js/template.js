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

	// DownLoad Action
	$('#downMenu').menu({
		onClick:function(item){
			var params = getParams();
			params['downType'] = item.name;
			downLoad(downUrl, params);
		}
	});
	
	// Drill Action
	$('#drillMenu').menu({
		onClick:function(item){
			var params = {};
            var drillCon = getCondition();
            
            var key = $('input[name="dimName"]').val();
            var value = $('input[name="dimValue"]').val();
            var values = value.split('|');
            var dateReg = new RegExp("date");
            if (dateReg.test(key)) {
            	params["startDate"] = values[1];
            	params["endDate"] = values[1];
            } else {
                params["startDate"] = $('#startDate').datebox('getValue');
                params["endDate"] = $('#endDate').datebox('getValue');
                if (drillCon) {
                	drillCon += ';' + key + ':eq:' + values[1];
                } else {
                	drillCon = key + ':eq:' + values[1];
                }
            }
            params['drillCon'] = drillCon;
            var url = '/board/' + item.href + '?' + $.param(params);
            window.parent.addDrillTab(url,item.name, values[0]);
		}
	});
	
	// 初始化 EasyUI DataGrid
	$('#tableData').datagrid({
		title:"",remoteSort:true,
		striped:true,singleSelect:true,
	    showHeader:true,showFooter:true,
	    onRowContextMenu:function(e,index,data) {
			e.preventDefault();
			var key = $('input[name="dimName"]').val();
			var dimValue = data[key];
			
			var mapValue = data['dimCode'];
			if (mapValue == undefined) {
				dimValue += "|" + dimValue;
			} else {
				dimValue += "|" + mapValue;
			}
			$('input[name="dimValue"]').val(dimValue);
			$('#drillMenu').menu('show', {left:e.pageX + 1,top:e.pageY + 1});
        },
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
	
	// 加载数据表格
	function showTable (result) {
		// Set Column
		var columns = [];
		$.each(result['columns'], function(i,column){
			var colItem = {
				field:column.field,title:column.title,width:120,
				align:column.align,sortable:column.sortable,
				formatter:(function(column) {
					var dataType = column.dataType;
					if ('digit' == dataType) {
						return digitFormat
					} else if ('int' == dataType) {
						return intFormat;
					}
					return ruleFormat;
				})(column)	
			};
			columns.push(colItem);
		});
		
		var tableData = result['tableData'];
		// 设置表格动态列, 加载表格数据
		$('#tableData').datagrid({
			columns:[columns], data:tableData
		});
		
		// 更新分页控件
		var curPage = $("input[name='pageNumber']").val();
		$("#pager").pagination("refresh", {
			total:tableData.total,pageNumber:curPage
		});
	}
	
	/****** Get Query Condition *****/
	function getParams () {
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
		params['queryStr'] = getCondition();
		
		return params;
	}
	
	function getCondition () {
		var queryPara = [];
		// 本页选择的条件
		$(".con-filter").each(function() {
			var field = $(this).find('span.field').attr('name');
			var sign = $(this).find('span.sign').attr('name');
			var value = $(this).find('span.value').attr('name');
			queryPara.push(field + ':' + sign + ':' + value);
		});
		return queryPara.join(";");
	}
	// 初始化
	query();
});