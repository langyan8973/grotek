<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.choosePageType {
	cursor: pointer;
}
.list-inline {
  padding-left: 0;
  list-style: none;
}

.list-inline > li {
  display: inline-block;
  padding-right: 5px;
  padding-left: 5px;
}

.list-inline > li:first-child {
  padding-left: 0;
}
-->
</style>
<ul class="doctypeUl">
	<c:forEach var="node" items="${notes}">
			<li>
				<h5 id="${node.typeid}">
					<span style="color: #0E6390;font-weight: bold;">${node.type}</span>
				</h5>
				<ul class="list-inline">
				<c:forEach var="page" items="${node.pages}">
					<li>
						<h6 class="choosePageType" id="${page.id}" sname="${page.name}" style="line-height: 15px;">
							<span style="color: #5CB85C;">
								${page.name}</span>
						</h6>
					</li>
				</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		$('.choosePageType')
				.bind(
						'click',
						function() {
							try {
								clickPageNote($(this).attr('id'), $.trim($(this).attr('sname')));
							} catch (e) {
								alert('请覆盖方法 clickPageNote(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>