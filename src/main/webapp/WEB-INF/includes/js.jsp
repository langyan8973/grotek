<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- scripts -->
<c:url var="jsUrl1" value="/assets/js/jquery-latest.js" />
<script src="${jsUrl1 }"></script>
<c:url var="jsUrl2" value="/assets/js/bootstrap.min.js" />
<script src="${jsUrl2 }"></script>
<c:url var="jsUrl3" value="/assets/js/jquery-ui-1.10.2.custom.min.js" />
<script src="${jsUrl3 }"></script>
<!-- knob -->
<c:url var="jsUrl4" value="/assets/js/jquery.knob.js" />
<script src="${jsUrl4 }"></script>
<!-- flot charts -->
<c:url var="jsUrl5" value="/assets/js/jquery.flot.js" />
<script src="${jsUrl5 }"></script>
<c:url var="jsUrl6" value="/assets/js/jquery.flot.stack.js" />
<script src="${jsUrl6 }"></script>
<c:url var="jsUrl7" value="/assets/js/jquery.flot.resize.js" />
<script src="${jsUrl7 }"></script>
<c:url var="jsUrl8" value="/assets/js/theme.js" />
<script src="${jsUrl8 }"></script>
<c:url var="jsUrl9" value="/assets/js/bootbox.js" />
<script src="${jsUrl9 }"></script>
<c:url var="jsUrl10" value="/assets/js/jquery.validate.js" />
<script src="${jsUrl10 }"></script>
<c:url var="jsUrl11" value="/assets/js/bootstrap-paginator.js" />
<script src="${jsUrl11 }"></script>
<%-- <c:url var="jsUrl111" value="/assets/js/jquery-1.10.2.min.js" />
<script type="text/javascript" src="${jsUrl111 }"></script> --%>
<c:url var="jsUrl112" value="/assets/js/kkpager.min.js" />
<script type="text/javascript" src="${jsUrl112 }"></script>

<script>
	function page() {
		var currentPage;
		var $table = $('table');
		var hidden = $('#read').val();
		//分页效果
		if (hidden != null) {
			if ($.cookie("current") != null) {
				var p = $.cookie("current");

				currentPage = parseInt(p);
			} else {
				currentPage = 0;
				$.cookie("current", currentPage);
			}
		} else {
			currentPage = 0;
			$.cookie("current", currentPage);
		}

		var pageSize = 10; //每页行数（不包括表头）
		//绑定分页事件
		$table.bind('repaginate', function() {
			$table.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
		});

		var numRows = $table.find('tbody tr').length; //记录宗条数
		if (numRows <= 10) {
			return;
		}
		var numPages = Math.ceil(numRows / pageSize); //总页数

		var $pager = $('<div class="page"></div>'); //分页div

		$pager.insertAfter($table);

		var options = {
			currentPage : currentPage + 1,
			totalPages : numPages,
			size : 'small',
			alignment : 'center',
			itemTexts : function(type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "前一页";
				case "next":
					return "后一页";
				case "last":
					return "末页";
				case "page":
					return "" + page;
				}
			},
			onPageChanged : function(e, oldPage, newPage) {
				currentPage = newPage - 1;
				$.cookie("current", currentPage);
				$table.trigger("repaginate");
			}
		};

		$pager.bootstrapPaginator(options);

		$table.trigger("repaginate"); //初始化 
	}

	function setplaceholderSupport() {
		if (!placeholderSupport()) { // 判断浏览器是否支持 placeholder
			$('[placeholder]').focus(function() {
				var input = $(this);
				if (input.val() == input.attr('placeholder')) {
					input.val('');
					input.removeClass('placeholder');
				}
			}).blur(function() {
				var input = $(this);
				if (input.val() == '' || input.val() == input.attr('placeholder')) {
					input.addClass('placeholder');
					input.val(input.attr('placeholder'));
				}
			}).blur();
		}
	}
	function placeholderSupport() {
		return 'placeholder' in document.createElement('input');
	}

	//获取url中的参数
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if (r != null)
			return decodeURIComponent(r[2]);
		return null; //返回参数值
	}
	
	function onKeyDown(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==27){ // 按 Esc 

		}
		if(e && e.keyCode==113){ // 按 F2 
		}            
		if(e && e.keyCode==13){ // enter 键
		     search();               
		}
	             
	}
</script>