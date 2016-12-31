<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.docquyuUl {
	font-size: 14px;
}

.chooseQuyuLableType {
	cursor: pointer;
}

.list-inline {
 /*  padding-left: 0;
  list-style: none; */
}

.list-inline > li {
  /* display: inline-block;
  padding-right: 5px;
  padding-left: 5px; */
}

.list-inline > li:first-child {
  /* padding-left: 0; */
}
</style>
<c:url var="cssstyle" value="/assets/css/style.css" />
<link rel="stylesheet" href="${cssstyle }" type="text/css" media="screen" />
<div class="tree well">
<ul class="docquyuUl">
<c:forEach var="node" items="${districts}">
	<li class="parent_li">
		<span id="${node.id}" level="1" sname="${node.name}" title="Expand this branch" style="color: #0E6390;font-weight: bold;"><i class="icon-folder-open icon-plus-sign"></i>${node.name}</span>
		<c:if test="${node.childs!=null}">
		<ul>
			<c:forEach var="node1" items="${node.childs}">
				<li  class="parent_li" style="display:none;">
					<span id="${node1.id}" level="2" sname="${node1.name}" title="Collapse this branch" style="color: #428BCA; font-weight: bold;">
							<i class="icon-plus-sign"></i>${node1.name}</span>
					<c:if test="${node1.childs!=null}">
					<ul>
						<c:forEach var="node2" items="${node1.childs}">
							<li  class="parent_li" style="line-height: 15px;display:none;">															
								<span id="${node2.id}" level="3" title="Collapse this branch" sname="${node2.name}" style="color: #D9534F;">
										<i class="icon-plus-sign"></i>${node2.name}</span>
								<c:if test="${node2.childs!=null}">
								<ul class="list-inline">
									<c:forEach var="node3" items="${node2.childs}">
										<li style="line-height: 15px;display:none;">
											
											<span id="${node3.id}" level="4" sname="${node3.name}" style="color: #5CB85C;">
													<i class="icon-leaf"></i>${node3.name}</span>
										</li>
									</c:forEach>
								</ul>
								</c:if>
							</li>
						</c:forEach>
					</ul>
					</c:if>
				</li>
			</c:forEach>
		</ul>
		</c:if>
	</li>
</c:forEach>
</ul>
</div>
<script type="text/javascript">
var timer = null; 
$(function() {
	$('.tree li.parent_li > span').on('click', function (e) {
    	var my = $(this);    	
    	clearTimeout(timer); 
    	timer = setTimeout(function () { //在单击事件中添加一个setTimeout()函数，设置单击事件触发的时间间隔 
    		if(my.parent('li').hasClass("parent_li")){
	    		var children = my.parent('li.parent_li').find(' > ul > li');
		        if (children.is(":visible")) {
		            children.hide('fast');
		            my.attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
		        } else {
		            children.show('fast');
		            my.attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
		        }
		        e.stopPropagation();
	    	} 
    	}, 300);  
        
    });
    $('.tree li > span').dblclick(function(){
    	clearTimeout(timer); 
    	try {
			clickQuyuType($(this).attr('id'), $.trim($(this).attr('sname')),$(this).attr('level'));
		} catch (e) {
			alert('请覆盖方法 clickQuyuType(id, text) -('
					+ $(this).attr('id') + ':'
					+ $.trim($(this).text()) + ')');
		}
    });
	/* $('.chooseQuyuLableType')
			.bind(
					'click',
					function() {
						try {
							clickQuyuType($(this).attr('id'), $.trim($(this).attr('sname')),$(this).attr('level'));
						} catch (e) {
							alert('请覆盖方法 clickQuyuType(id, text) -('
									+ $(this).attr('id') + ':'
									+ $.trim($(this).text()) + ')');
						}

					}); */
});
</script>