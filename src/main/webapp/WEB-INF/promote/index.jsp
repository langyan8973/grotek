<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="晋升管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>员工晋升情况</h3>
                    <div class="span10 pull-right">
                        <a id="addOne" class="btn btn-flat success pull-right" href="javascript:selectEmployee()">
                            <span>&#43;</span>
                            新增员工晋升
                        </a>
                    </div>
                </div>
				<c:if test="${success != null}">
					<div class="alert alert-success" id="successMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="${success}" /></strong>
					</div>
					<script>
						$("#successMessage").delay(2000).slideUp("slow");
					</script>
				</c:if>
				<c:if test="${message != null}">
					<div class="alert alert-danger" id="failMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="${message}" /></strong>
					</div>
					<script>
						$("#failMessage").delay(2000).slideUp("slow");
					</script>
				</c:if>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="span2">
                                     时间
                                 </th>
                                 <th class="span1">
                                     员工
                                 </th>
                                 <th class="span2">
                                     职务
                                 </th>
                                 <th class="span2">
                                     主管领导
                                 </th>
                                 <th class="span5">
                                     评价
                                 </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="i" value="0"></c:set>
                        <c:forEach items="${proms}" var="prom">
                        	<tr>
	                             <td>
	                                <fmt:formatDate value='${prom.date}'
														type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                             <td>
	                                ${prom.employee.fullname}
	                            </td>
	                            <td>
	                                ${prom.position.name}
	                            </td>
	                            <td>
	                                ${prom.superior }
	                            </td>
	                            <td>
	                                ${prom.evaluate}
	                            </td>
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div id="kkpager" class="span10"></div>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						选择员工
					</h4>
				</div>
				<div class="modal-body" id="treeChooseTypeBoxId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<script>

$(document).ready(function() {
	
    var size = 10;
	var sumcount = parseInt(${sumcount}); 
	if(sumcount ==0){return false}
	
    var numPages = Math.ceil(sumcount/size);
    var currentPage = getUrlParam("page");
    currentPage = currentPage ? parseInt(currentPage) + 1 : 1;
  //生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : currentPage,
		//总页码
		total : numPages,
		//总数据条数
		totalRecords : sumcount,
		//链接前部
		hrefFormer : 'index',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			return this.hrefFormer + "?page=" + (n-1) + "&size=" + size;
		}
		
	});
    
	$('#treeChooseTypeBoxId').load('<c:url value="/manager/employees/superiors" />');
});
function selectEmployee(){
	$('#myModal').modal( {
		keyboard : false
	})
}
function clickEmployeeType(id,sname) {
	$('#sname').val(sname);
	$('#superior').val(id);
	$('#myModal').modal('hide');
	window.location.href = '<c:url value="/manager/employees/change" />?id=' + id + "&prom=1";
}
</script>
<jsp:include page="../includes/footer.jsp" />