<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.chooseDealerLableType {
	cursor: pointer;
}
-->
</style>
<ul class="doctypeUl">
	<c:forEach var="node" items="${notes}">
			<li>
				<h5 id="${node.sid}">
					<span style="color: #0E6390;font-weight: bold;">${node.sname}</span>
				</h5>
				<ul>
					<c:forEach var="dealer" items="${node.dealers}">
							<li>
								<h6 class="chooseDealerLableType" id="${dealer.id}" sname="${dealer.name}">
									<span style="color: #428BCA; font-weight: bold;">
										${dealer.name}</span>
								</h6>
							</li>
					</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		$('.chooseDealerLableType')
				.bind(
						'click',
						function() {
							try {
								clickDocType($(this).attr('id'), $.trim($(this).attr('sname')));
							} catch (e) {
								alert('请覆盖方法 clickDocType(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>