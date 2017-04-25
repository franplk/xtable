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
</section>
</body>
<script type="text/javascript" src="/assets/js/utils.js"></script>
<script>
$(function(){
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
	$('#con-action').on('click','a',function(e) {
		var act = $(e.currentTarget).attr('name');
		if ('reset' == act) {
			$('#con_area').empty();
			return;
		}
		var field = $('#con-field').combotree('getValue');
		if (!field) {
			$.messager.alert('WARNING', '请选择条件', 'error');
			return;
		}
		var fieldName = $('#con-field').combotree('getText');
		var sign = $('#con-sign').combobox('getValue');
		var signName = $('#con-sign').combobox('getText');
		var value = $('#con-value').combobox('getValue');
		if (!value) {
			$.messager.alert('WARNING', '请填写条件值', 'error');
			return;
		}
		var valueName = $('#con-value').combobox('getText');
		var tpl = getConTpl(field, fieldName, sign, signName, value, valueName);
		$('#con_area').append(tpl);
	});
});
</script>
<script type="text/javascript" src="/assets/js/add.js"></script>
</html>