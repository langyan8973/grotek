<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="员工月收入" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>员工月收入情况</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入员工姓名查找"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
						
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
                                <th class="span2 sortable">
                                    姓名
                                </th>
                                <th class="span2 sortable">
                                    月份
                                </th>
                                <th class="span1 sortable">
                                    工资(元)
                                </th>
                                <th class="span1 sortable">
                                    提成(元)
                                </th>
                                <th class="span1 sortable">
                                    补助(元)
                                </th>
                                <th class="span1 sortable">
                                    报销(元)
                                </th>
                                <th class="span1 sortable">
                                    奖励(元)
                                </th>
                                <th class="span2 sortable">
                                    每小时费用(元)
                                </th>
                                <th class="span1 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${incomes}" var="income">
                        	<tr>
                        		<td>
	                                <c:url var="profileUrl" value="/manager/employees/profile?id=${income.employee.id }"></c:url>
	                                <a href="${profileUrl }" class="name">${income.employee.fullname }</a>
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${income.date}'
														type='date' pattern='yyyy-MM' />
	                            </td>
	                            <td>
	                                ${income.wages }
	                            </td>
	                            <td>
	                                ${income.divident }
	                            </td>
	                            <td>
	                                ${income.allowance }
	                            </td>
	                            <td>
	                                ${income.travel }
	                            </td>
	                            <td>
	                                ${income.award }
	                            </td>
	                            <td>
	                                ${income.costhour }
	                            </td>
	                            <td>
	                                <a id="editdept" class="btn btn-mini btn-warning"  
	                                href="javascript:edit('${income.id }','${income.wages }','${income.divident }','${income.allowance }','${income.travel }','${income.award }','${income.costhour }','${income.employee.fullname }')">
										编辑
									</a>
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
    <!-- end main container -->
    <div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editpositionUrl" value="editone" />
		<form id="edit-form" class="form-horizontal" action="${editpositionUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="edittitle"></h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="wages">工资</label>
					<div class="controls">
						<input type="text" class="span3" name="wages" id="wages" placeholder="" 
							style="IME-MODE: disabled;"    maxlength="20" onkeyup="clearNoNum(this)"/>
						<input type="hidden" name="eid" id="eid">
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="eobjective">提成</label>
					<div class="controls">
						<input type="text" class="span3" name="divident" id="divident" placeholder="" 
							style="IME-MODE: disabled;"    maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="allowance">补助</label>
					<div class="controls">
						<input type="text" class="span3" name="allowance" id="allowance" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="travel">出差报销</label>
					<div class="controls">
						<input type="text" class="span3" name="travel" id="travel" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="award">奖励</label>
					<div class="controls">
						<input type="text" class="span3" name="award" id="award" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="costhour">每小时费用</label>
					<div class="controls">
						<input type="text" class="span3" name="costhour" id="costhour" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#searchtext").val(getUrlParam("context"));
	 
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
			var text = $("#searchtext").val();
			return this.hrefFormer + "?context="+ encodeURIComponent(text) + "&page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function search(){
	var text = $("#searchtext").val();
	window.location.href = '<c:url value="index" />?context='+encodeURIComponent(text);
}
function edit(id,wages,divident,allowance,travel,award,costhour,name){
	$('#eid').val(id);
	$("#edittitle").html(name+"的月收入情况");
	$("#wages").val(wages);
	$("#divident").val(divident);
	$("#allowance").val(allowance);
	$("#travel").val(travel);
	$("#award").val(award);
	$("#costhour").val(costhour);
	$('#editOne-modal').modal('show');
}
</script>

<jsp:include page="../includes/footer.jsp" />