<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.chooseSampleType {
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
				<c:forEach var="box" items="${node.boxs}">
					<li>
						<h6 class="chooseSampleType" id="${box.id}" sname="${box.name}" skg="${box.kg }" spec="${box.specification}" style="line-height: 15px;">
							<span style="color: #5CB85C;">
								${box.name}</span><span style="color: #0e6390;font-size: 10px;">(${box.specification})</span>
						</h6>
					</li>
				</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		$('.chooseSampleType')
				.bind(
						'click',
						function() {
							try {
								clickSampleNote($(this).attr('id'), $.trim($(this).attr('sname')),$.trim($(this).attr('skg')),$.trim($(this).attr('spec')));
							} catch (e) {
								alert('请覆盖方法 clickSampleNote(id, text,text,spec) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						});
	});
</script>