var baseURL = "";

// 文件下载
function downLoad(url, params) {
	var realURL = baseURL + url;
	window.location.href = realURL + "?" + $.param(params);
}

/*日期Utils*/
Date.prototype.format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份   
		"d+": this.getDate(), //日   
		"h+": this.getHours(), //小时   
		"m+": this.getMinutes(), //分   
		"s+": this.getSeconds(), //秒     
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

/**
 * 根据不同选项生成不同的时间区间
 */
function getDateSpan(nowDate, type) {
	var dataSpan = {};
	if(type == "curr"){
		dataSpan.start = nowDate.format("yyyy-MM-dd");
		dataSpan.end = nowDate.format("yyyy-MM-dd");
	} else if(type == 'prev') {
		nowDate = getDateBefore(nowDate, 1);
		dataSpan.start = nowDate.format("yyyy-MM-dd");
		dataSpan.end = nowDate.format("yyyy-MM-dd");
	} else if(type == 'week'){
		dataSpan.end = nowDate.format("yyyy-MM-dd");
		nowDate = getDateBefore(nowDate, 6);
		dataSpan.start = nowDate.format("yyyy-MM-dd");
	} else if(type == 'month'){
		dataSpan.end = nowDate.format("yyyy-MM-dd");
		nowDate.setDate(1);
		dataSpan.start = nowDate.format("yyyy-MM-dd");
	}
	return dataSpan;
}

/**
 * 获取当前时间的前n天的日期
 */
function getDateBefore(nowDate, n) {
	if(n){
		nowDate.setDate(nowDate.getDate() - n);
	}
	return nowDate;
}

function formatNumber(num, cent) {
	isNaN(cent) || (cent = 2);
	// 把指定的小数位先转换成整数.多余的小数位四舍五入.
	var numLevel = Math.pow(10, cent);
	num = Math.round(num * numLevel);
	// 把小数位转换成字符串,以便求小数位长度.
	var cents = num % numLevel + '';
	// 补足小数位到指定的位数.
	while (cents.length < cent) {
		cents = "0" + cents;
	}
	// 求出整数位数值.
	num = Math.floor(num / numLevel).toString();
	if (num.length > 3) {
		num = num.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	return num + '.' + cents;
}

function ruleFormat (value,row,index) {
	if (value == undefined) {
		return '--';
	}
	return value;
}

function digitFormat (value,row,index) {
	value = ruleFormat(value);
	if (isNaN(value)) {
		return value;
	}
	var text = formatNumber(Math.abs(value),2);
	if (value < 0) {
		text = "-" + text;
		return "<span class='minus'>" + text + "</span>";
	}
	return text;
}

function intFormat (value,row,index) {
	value = ruleFormat(value);
	if (isNaN(value)) {
		return value;
	}
	var num = value.toString();
	if (num.length > 3) {
		num = num.replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	return num;
}

function getConTpl (field, fieldName, sign, signName, value, valueName) {
	
	var tpl = '';
	tpl += '<li class="con-filter">';
	tpl += '<span class="field" name="' + field + '">' + fieldName + '</span>';
	tpl += '<span class="sign" name="' + sign + '">' + signName + '</span>';
	if (!valueName) {
		valueName = value;
	}
	tpl += '<span class="value" name="' + value + '">' + valueName + '</span>';
	tpl += '<span class="icon-empty action" onclick="$(this).parent().remove()"></span>';
	tpl += '</li>';
	
	return tpl;
}