$(function() {
	var boardId = $("input[name='boardId']").val();
	var downUrl = "/download/" + boardId;
	var dataUrl = "/data/" + boardId;
	var tepUrl = "/data/save/" + boardId;
	var idxSaveUrl = "/index/save";

	// 注册按钮事件
	$(".query-btns").on("click", "a", function(evt){
		var name = $(evt.currentTarget).attr("name");
		if (name == "index") {
			$("#idxDialog").dialog("open").dialog('resize',{top:evt.pageY,left:evt.pageX});
		} else if (name == 'query') {
			$("input[name='pageNumber']").val("1");
			query();
		} else if (name == "template") {
			console.log("Template Save...");
			$.messager.prompt('模板保存', '请输入模版名称:', function(name){
				saveTemplate(name);
			});
		}
		return false;
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
	
	// DownLoad Action
	$('#downMenu').menu({
		onClick:function(item){
			var params = getParams();
			params['downType'] = item.name;
			downLoad(downUrl, params);
		}
	});
	
	// 初始化指标弹出框
	$("#idxDialog").dialog({
		closable:false,closed:true,modal:true,
		buttons:[{
			text:'保存指标',iconCls:'icon-save',
			handler:function() {
				var idxs = $("#idxList").tree("getChecked");
				if (idxs.length == 0) {
					$.messager.alert('警告','请至少选择一个指标!','warning');
					return;
				}
				saveIndex();
			}
		},{
			text:'确定',iconCls:'icon-ok',
			handler:function() {
				var idxs = $("#idxList").tree("getChecked");
				if (idxs.length == 0) {
					$.messager.alert('警告','请至少选择一个指标!','warning');
					return;
				}
				$("input[name='pageNumber']").val("1");
				$("input[name='orderType']").val("desc");
				$("#idxDialog").dialog("close");
				
				query();
			}
		},{
			text:'关闭',iconCls:'icon-cancel',
			handler:function() {
				$("#idxDialog").dialog("close");
			}
		}]
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

	function saveIndex () {
		$.messager.progress({title:'请稍候...',text:"正在保存..."});
		// Data Request
		var idxes = getIndex();
		$.ajax({
			type:"POST",dataType:"json", url:idxSaveUrl,
			data:{boardId : boardId, idxes : idxes},
			success:function(result) {
				if (result.success) {
					$.messager.alert('指标定制',"恭喜您，保存成功!",'info');
				} else {
					$.messager.alert('指标定制',result.msg,'error');
				}
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('ERROR',"网络不佳，保存失败！",'error');
			},complete:function(){
				$.messager.progress('close');
			}
		});
	}
	
	function saveTemplate (name) {
		var params = getParams();
		params.name = name || "我的模板";
		
		$.messager.progress({title:'请稍候...',text:"正在保存..."});
		// Data Request
		$.ajax({
			type:"POST",data:params,dataType:"json", url:tepUrl,
			success:function(result) {
				if (result.success) {
					$.messager.alert('模板保存',"恭喜您，模版保存成功!",'info');
				} else {
					$.messager.alert('模板保存',result.msg,'error');
				}
			},error:function(XMLHttpRequest, textStatus) {
				$.messager.alert('ERROR',"网络不佳，保存失败！",'error');
			},complete:function(){
				$.messager.progress('close');
			}
		});
	}
	
	// 查询与显示数据
	function query () {
		var params = getParams();
		
		$("#tableData").datagrid('loading');
		// Data Request
		$.ajax({
			type:"POST",data:params,dataType:"json", url:dataUrl,
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
		
		var tableData = result['tableData'];
		// Set Column
		var columns = [];
		$.each(tableData['columns'], function(i,column){
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
		
		// 设置表格动态列, 加载表格数据
		$('#tableData').datagrid({
			columns:[columns], data:tableData
		});
		
		// 更新分页控件
		var curPage = $("input[name='pageNumber']").val();
		$("#pager").pagination("refresh", {total:tableData.total,pageNumber:curPage});
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
		
		params['queryIdx'] = getIndex();// Dynamic Indicator
		params['queryStr'] = getCondition();// Parameters
		
		return params;
	}
	
	function getIndex () {
		var queryIdx = [];
		var idxRows = $("#idxList").tree("getChecked");
		$.each(idxRows, function(idx, row) {
			row.id && queryIdx.push(row.id);
		});
		return queryIdx.join(";");
	}
	
	function getCondition () {
		var queryPara = [];
		// 钻入带入的条件
		var drillCon = $("input[name='drillCon']").val();
		if (drillCon) {
			queryPara.push(drillCon);
		}
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