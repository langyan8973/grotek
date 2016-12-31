<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.choosePackType {
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
				<c:forEach var="pack" items="${node.packs}">
					<li>
						<h6 class="choosePackType" id="${pack.id}" sname="${pack.name}" style="line-height: 15px;">
							<span style="color: #5CB85C;">
								${pack.name}</span>
						</h6>
					</li>
				</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		$('.choosePackType')
				.bind(
						'click',
						function() {
							try {
								clickPackNote($(this).attr('id'), $.trim($(this).attr('sname')));
							} catch (e) {
								alert('请覆盖方法 clickPackNote(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>