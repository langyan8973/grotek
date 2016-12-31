<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.docemUl {
	font-size: 14px;
}

.chooseEmLableType {
	cursor: pointer;
}
</style>
<ul class="docemUl">
	<c:forEach var="node" items="${superiors}">
			<li>
				<h5 id="${node.positionid}">
					<span style="color: #0E6390;font-weight: bold;">${node.positionname}</span>
				</h5>
				<ul>
					<c:forEach var="employee" items="${node.items}">
							<li>
								<h6 class="chooseEmLableType" id="${employee.id}" sname="${employee.fullname}">
									<span style="color: #428BCA; font-weight: bold;">
										${employee.fullname}</span>
								</h6>
							</li>
					</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		$('.chooseEmLableType')
				.bind(
						'click',
						function() {
							try {
								clickEmployeeType($(this).attr('id'), $.trim($(this).attr('sname')));
							} catch (e) {
								alert('请覆盖方法 clickEmployeeType(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>