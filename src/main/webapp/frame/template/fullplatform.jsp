<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="/easyui/themes/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/model.css" />
	<script type="text/javascript" src="/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/easyui/easyui.min.js"></script>
	<script type="text/javascript" src="/easyui/datagrid-invertionView.js"></script>
	<script type="text/javascript" src="/easyui/easyui-lang-zh.js"></script>
</head>
<body>
<!-- Query Component -->
<header>
	<!-- Normal Query -->
	<div class="query-normal">
		<span class="query-date" id="query-date">
			<ul>
				<li><a class="selected" type="prev">昨天</a></li>
				<li><a type="week">最近七天</a></li>
				<li><a type="month">本月</a></li>
			</ul>
		</span>
		<span class="query-date">
			<input class="easyui-datebox" id="startDate" value="${ dateSpan.startDate }" />
			<label>至</label>
			<input class="easyui-datebox" id="endDate" value="${ dateSpan.endDate }" />
		</span>
		<span class="query-btns" id="btn_action">
			<a class="easyui-linkbutton" name="query" data-options="iconCls:'icon-search',plain:true">查询</a>
			<a class="easyui-menubutton" data-options="iconCls:'icon-redo',menu:'#downMenu'">导出</a>
		</span>
	</div>
	
</header>

<!-- Data Table And Chart -->
<section>
	<!-- webForm -->
	<form id="query-form">
		<!-- 查询条件筛选 -->
		<div class="query-con">
			<span class="con-label">查询条件：</span>
			<select class="easyui-combotree" id="con-field" style="width:160px;"></select>
			<select class="easyui-combobox" id="con-sign" style="width:100px"></select>
			<select class="easyui-combobox" id="con-value" style="width:160px;"></select>
			<span class="con-action" id="con-action">
				<a class="easyui-linkbutton" name="add" data-options="iconCls:'icon-add-blue',plain:true">添加</a>
				<a class="easyui-linkbutton" name="reset" data-options="iconCls:'icon-remove-blue',plain:true">重置</a>
			</span>
		</div>
		<!-- 添加条件 -->
		<ul class="con_area" id="con_area">
			<!-- 显示默认条件 -->
			<c:forEach var="filter" items="${ conFilter }">
				<li class="con-filter">
					<span class="field" name="${ filter.column.field }">${ filter.column.title }</span>
					<span class="sign" name="${ filter.sign }">${ filter.sign }</span>
					<span class="value" name="${ filter.value }">${ filter.value }</span>
				</li>
			</c:forEach>
		</ul>
	</form>
	<table id="tableData" style="width:100%;height:450px" >
		<thead>
			<tr class="info">
				<th data-options="field:'project_id',width:80,align:'left'" rowspan="2">项目ID</th>
				<th data-options="field:'project_name',width:90,align:'left'" rowspan="2">项目名称</th>
				<th data-options="field:'rtb_cost',width:80,align:'right',sortable:true,formatter:digitFormat" rowspan="2">竞价花费</th>
				
				<th colspan="3">投放花费</th>
				<th colspan="3">订单量</th>
				<th colspan="3">订单金额</th>
				<th colspan="3">ROI</th>
				
				<th data-options="field:'profit_rtb',width:80,align:'right',sortable:true,formatter:digitFormat" rowspan="2">RTB毛利</th>
			</tr>
			<tr class="info">
				<!-- 投放花费 -->
				<th data-options="field:'bid_cost',width:80,align:'right',sortable:true,formatter:digitFormat">总额</th>
				<th data-options="field:'bid_cost_rtb',width:80,align:'right',sortable:true,formatter:digitFormat">RTB</th>
				<th data-options="field:'bid_cost_nrtb',width:80,align:'right',sortable:true,formatter:digitFormat">非RTB</th>
				<!-- 订单量 -->
				<th data-options="field:'order_num',width:80,align:'right',sortable:true,formatter:intFormat">总值</th>
				<th data-options="field:'order_num_rtb',width:80,align:'right',sortable:true,formatter:intFormat">RTB</th>
				<th data-options="field:'order_num_nrtb',width:80,align:'right',sortable:true,formatter:intFormat">非RTB</th>
				<!-- 订单金额 -->
				<th data-options="field:'order_price',width:90,align:'right',sortable:true,formatter:digitFormat">总额</th>
				<th data-options="field:'order_price_rtb',width:90,align:'right',sortable:true,formatter:digitFormat">RTB</th>
				<th data-options="field:'order_price_nrtb',width:90,align:'right',sortable:true,formatter:digitFormat">非RTB</th>
				<!-- ROI -->
				<th data-options="field:'bid_roi',width:80,align:'right',sortable:true,formatter:digitFormat">总值</th>
				<th data-options="field:'bid_roi_rtb',width:80,align:'right',sortable:true,formatter:digitFormat">RTB</th>
				<th data-options="field:'bid_roi_nrtb',width:80,align:'right',sortable:true,formatter:digitFormat">非RTB</th>
			</tr>
		</thead>
	</table>
	
	<!-- Pagination -->
	<div id="pager" style="background:#efefef;border:1px solid #ccc;"></div>
	
	<!-- DownLoad Menu -->
	<div id="downMenu" class="easyui-menu" style="width:20px">
		<div data-options="name:'page'">本页</div>
		<div data-options="name:'all'">全部</div>
	</div>
</section>

<!-- Hidden Parameters -->
<input type="hidden" name="boardId" value="${ boardId }"/>
<input type="hidden" name="orderBy" value="rtb_cost"/>
<input type="hidden" name="orderType" value="DESC"/>
<input type="hidden" name="dimKey" value=""/>
<input type="hidden" name="pageNumber" value="1"/>
<input type="hidden" name="pageSize" value="20"/>
</body>
<script type="text/javascript" src="/assets/js/utils.js"></script>
<script>
	$(function(){
		
		// 设置时间框范围以及Selected事件
		$('.easyui-datebox').each(function() {
			$(this).datebox({
				width:105,
				onSelect:function(date){
					$("span#query-date").find('a.selected').each(function(){
						$(this).removeClass("selected");
					});
				}
			}).datebox('calendar').calendar({
				validator:function(date){
					return date < new Date (<%= System.currentTimeMillis()-24*60*60*1000%>);
				}
			});
		});
		
		// 时间选项
		$("span#query-date").on('click','a',function(e){
			$("span#query-date").find('a.selected').each(function(){
				$(this).removeClass("selected");
			});
			$this = $(e.target);
			$this.addClass('selected');
			var type = $this.attr('type');
			
			var sysDate = new Date (<%= System.currentTimeMillis()%>);
			
			var dateSpan = getDateSpan(sysDate,type);
			$('#startDate').datebox('setValue', dateSpan.start);
			$('#endDate').datebox('setValue', dateSpan.end);
		});
		
		// 条件事件--选择
		$('#con-field').combotree({
			url:'/filter/con/${ boardId }',
			onBeforeSelect: function(node) {
				if (!node.attributes) {
					return false;
				}
	            if (!$(this).tree('isLeaf', node.target)) {
	                return false;
	            }
	        },
			onSelect:function(node){
				$('#con-value').combobox('setValue','');
				// Condition Type
				var type = node.attributes['type'];
				if (!type) return;
				$('#con-sign').combobox({
					url:'/filter/type/' + type,
					onLoadSuccess: function (data) {
						data && ($('#con-sign').combobox('setValue',data[0].value));
					}
				});
				
				// Condition Value
				var value = node.attributes['value'];
				if (!value) return;
				$('#con-value').combobox({
					url:'/filter/value/' + value,
					onLoadSuccess: function (data) {
						data && ($('#con-value').combobox('setValue',data[0].value));
					}
				});
			}
		});
		
		// 条件事件--添加
		$('#con-action').on('click', 'a', function(e) {
			var act = $(e.currentTarget).attr('name');
			if ('add' == act) {
				var label = $('#con-field').combotree('getText');
				if (!label) {
					$.messager.alert('WARNING','请选择条件','error');
					return;
				}
				var field = $('#con-field').combotree('getValue');
				var sign = $('#con-sign').combobox('getValue');
				var signName = $('#con-sign').combobox('getText');
				var value = $('#con-value').combobox('getValue');
				if (!value) {
					$.messager.alert('WARNING','请填写条件值','error');
					return;
				}
				var tpl = getConTpl(field, label, sign, signName, value);
				$('#con_area').append(tpl);
			} else if ('reset' == act){
				$('#con_area').empty();
			}
		});
	});
</script>
<script type="text/javascript" src="/assets/js/fullplatform.js"></script>
</html>