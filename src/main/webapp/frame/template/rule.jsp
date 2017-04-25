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

<section>

	<!-- webForm -->
	<form id="query-form">
		<!-- 查询条件筛选 -->
		<div class="query-con">
			<span class="con-label">查询条件：</span>
			<select id="con-field" style="width:160px;"></select>
			<select id="con-sign" style="width:100px"></select>
			<select id="con-value" style="width:160px;"></select>
			<span class="con-action" id="con-action">
				<a class="easyui-linkbutton" name="add" data-options="iconCls:'icon-add-blue',plain:true">添加</a>
				<a class="easyui-linkbutton" name="reset" data-options="iconCls:'icon-remove-blue',plain:true">重置</a>
			</span>
		</div>
		<!-- 添加条件 -->
		<ul class="con_area" id="con_area"></ul>
	</form>

	<!-- Table Data -->
	<table id="tableData" style="width:100%;height:450px" >
		<thead>
			<tr class="info">
				<c:forEach var="column" items="${ columnList }">
				<th data-options="field:'${ column.field }',width:120,align:'right'">
					${ column.title }
				</th>
				</c:forEach>
			</tr>
		</thead>
	</table>
	
	<!-- Pagination -->
	<div id="pager" style="background:#efefef;border:1px solid #ccc;"></div>
	
	<!-- DownLoad Menu -->
	<div id="downMenu" class="easyui-menu" style="width:50px">
		<div data-options="name:'page'">本页数据</div>
		<div data-options="name:'all'">全部数据</div>
	</div>
</section>

<!-- Hidden Parameters -->
<input type="hidden" name="bidtype" value="${ bidtype }"/>
<input type="hidden" name="orderBy" value=""/>
<input type="hidden" name="orderType" value="asc"/>
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
					var nowDate = new Date (<%= System.currentTimeMillis()-24*60*60*1000%>);
					return date < nowDate;
				}
			});
		});
		
		// 时间选项
		$("span#query-date").on('click','a',function(e) {
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
			url:'/filter/con/${conid}',
			onBeforeSelect: function(node) {
				if (!node.attributes) {
					return false;
				}
	            if (!$(this).tree('isLeaf', node.target)) {
	                return false;
	            }
	        },
			onSelect:function(node){
				var type = node.attributes['type'];
				if (type) {
					$('#con-sign').combobox({url:'/filter/type/' + type});
				}
				// Condition Value
				var value = node.attributes['value'];
				if (value) {
					$('#con-value').combobox({url:'/filter/value/' + value,});
				} else {
					$('#con-value').combobox({url:null,data:[]});
				}
			}
		});
		
		$('#con-sign').combobox({
			onLoadSuccess: function (data) {
				if (data && data.length > 0) {
					$('#con-sign').combobox('setValue',data[0].value)
				}
			}
		});
		
		$('#con-value').combobox({
			onLoadSuccess: function (data) {
				if (data && data.length > 0) {
					$('#con-value').combobox('setValue',data[0].value);
				}
			}
		});
		
		// 条件事件--添加
		$('#con-action').on('click', 'a', function(e) {
			var act = $(e.currentTarget).attr('name');
			if ('add' == act) {
				var field = $('#con-field').combotree('getValue');
				if (!field) {
					$.messager.alert('WARNING','请选择条件','error');
					return;
				}
				var fieldName = $('#con-field').combotree('getText');
				var sign = $('#con-sign').combobox('getValue');
				var signName = $('#con-sign').combobox('getText');
				var value = $('#con-value').combobox('getValue');
				if (!value) {
					$.messager.alert('WARNING','请填写条件值','error');
					return;
				}
				var valueName = $('#con-value').combobox('getText');
				var tpl = getConTpl(field, fieldName, sign, signName, value, valueName);
				$('#con_area').append(tpl);
			} else if ('reset' == act){
				$('#con_area').empty();
			}
		});
	});
</script>
<script type="text/javascript" src="/assets/js/rule.js"></script>
</html>