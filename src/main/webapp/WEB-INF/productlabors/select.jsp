<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.chooseLaborType {
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
	<li>
		<h4>
			<span style="color: #0E6390;font-weight: bold;">所有工人</span>
		</h4>
		<ul class="list-inline">
		<c:forEach var="labor" items="${labors}">
			<li>
				<h6 class="chooseLaborType" id="${labor.id}" sname="${labor.name}" style="line-height: 15px;">
					<span style="color: #5CB85C;">
						${labor.name}</span>
				</h6>
			</li>
		</c:forEach>
		</ul>
	</li>
</ul>
<script type="text/javascript">
	$(function() {
		$('.chooseLaborType')
				.bind(
						'click',
						function() {
							try {
								clickLaborNote($(this).attr('id'), $.trim($(this).attr('sname')));
							} catch (e) {
								alert('请覆盖方法 clickLaborNote(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>