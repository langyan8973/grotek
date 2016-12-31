<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="销售进度" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header boxbottomline">
                    <h3>销售进度信息</h3>
                    
                    <a class="btn-flat icon pull-right  delete-user" href="#editOne-modal" data-toggle="modal">
                        <i class="icon-edit"></i>
                    </a>
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
				<c:if test="${sale == null}">
					<div class="alert alert-danger" id="failMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="数据不存在或已经删除" /></strong>
					</div>
					<script>
						$("#failMessage").delay(4000).slideUp("slow");
					</script>
				</c:if>
                <!-- Users table -->
                <div class="row-fluid">
                	<div class="span3"></div>
                	<div class="span6">
                	<table class="table table-bordered">
                        <tbody>
                        	<tr>
                        		<td class="span2">经销商</td>
                        		<td class="span2">${sale.dealer.name }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">产品</td>
                        		<td class="span2">${sale.box.name }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">每件价格</td>
                        		<td class="span2">${sale.price }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">进货量(件)</td>
                        		<td class="span2">${sale.count }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">进货额(元)</td>
                        		<td class="span2">${sale.total }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">订购时间</td>
                        		<td class="span2"><fmt:formatDate value='${sale.ordertime }'
													type='date' pattern='yyyy-MM-dd HH:mm' /></td>
                        	</tr>
                        	<tr>
                        		<td class="span2">发货时间</td>
                        		<td class="span2"><fmt:formatDate value='${sale.posttime }'
													type='date' pattern='yyyy-MM-dd HH:mm' /></td>
                        	</tr>
                        	<tr>
                        		<td class="span2">付款状况</td>
                        		<td class="span2">${sale.paystatus }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">付款额</td>
                        		<td class="span2">${sale.amountpaid }</td>
                        	</tr>
                        	<tr>
                        		<td class="span2">出库信息</td>
                        		<td class="span2">
                        			<a href="#outModal" data-toggle="modal">查看</a>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                	</div>
                	<div class="span3"></div>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editdeptUrl" value="editone" />
		<form id="editdept-form" class="form-horizontal" action="${editdeptUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>销售进度编辑</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="paystatus">付款状况</label>
					<input type="hidden"  name="eid" id="eid" value="${sale.id }" />
					<div class="controls">
						<input type="text" class="span3" name="paystatus" id="paystatus" placeholder="输入付款状况" 
						style="IME-MODE: disabled;"  maxlength="20" value="${sale.paystatus }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="amountpaid">付款额</label>
					<div class="controls">
						<input type="text" class="span3" name="amountpaid" id="amountpaid" placeholder="输入付款额" 
						style="IME-MODE: disabled;" value="${sale.amountpaid }" onkeyup="clearNoNum(this)" maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="edit-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal fade" id="outModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						出库信息
					</h4>
				</div>
				<div class="modal-body" id="treeChoosedealerBoxId">
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
<script type="text/javascript">
$(document).ready(function() {	 
	addeditFormValidate();
	var outid = getUrlParam("outid");
	$('#treeChoosedealerBoxId').load('<c:url value="/manager/productboxes/boxout?id=" />'+outid);
});
function addeditFormValidate() {
	$("#editdept-form").validate(
			{
				debug : true,
				rules : {
					paystatus : {
						required : true
					},
					amountpaid : {
						required : true
					}
				},

				messages : {
					paystatus : {
						required : "必填"
					},
					amountpaid : {
						required : "必填"
					}
				},

				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}
function clearNoNum(obj) {
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g, "");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g, "");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
}
</script>

<jsp:include page="../includes/footer.jsp" />