<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="包材" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productpacks/index"></c:url>
			    <li><a href="${listUrl }">包材信息</a></li>
			    <c:url var="storeUrl" value="/manager/productpacks/store"></c:url>
			    <li><a href="${storeUrl }">包材库存</a></li>
			    <c:url var="inUrl" value="/manager/productpacks/in"></c:url>
			    <li><a href="${inUrl }">包材入库</a></li>
			    <c:url var="outUrl" value="/manager/productpacks/out"></c:url>
			    <li><a href="${outUrl }">包材出库</a></li>
			    <c:url var="typeUrl" value="/manager/productpacks/type"></c:url>
			    <li  class="active"><a href="${typeUrl }">包材类型</a></li>
			    <c:url var="referenceUrl" value="/manager/productpacks/references"></c:url>
			    <li><a href="${referenceUrl }">卷膜包材重量转换</a></li>
			</ul>  
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>包材类型</h3>
                    <div class="span10 pull-right">
						<a id="addOne" class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
                            <span>&#43;</span>
                            新增类型
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
                                <th class="span6 sortable">
                                    名称
                                </th>
                                <th class="span6 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${types}" var="type">
                        	<tr>
                        		<td>
	                                ${type.name }
	                            </td>
	                            <td>
	                               <a id="edittype" class="btn btn-mini btn-warning"  href="javascript:edit(${type.id },'${type.name }',${type.trans })">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${type.id})">删除</button></td> 
	                            </td>
                        	</tr>
                        </c:forEach>                        
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="addOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="addtypeUrl" value="addtype" />
		<form id="addtype-form" class="form-horizontal" action="${addtypeUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">创建包材类型</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">包材名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入包材名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
					<div class="control-group">
						<label class="control-label" for="trans">是否需要转换</label>
						<div class="controls">
							<select id="trans" name="trans" class="form-control">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
							<span class="help-inline"></span>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="edittypeUrl" value="edittype" />
		<form id="edittype-form" class="form-horizontal" action="${edittypeUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑包材信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">包材名称</label>
					<input type="hidden"  name="eid" id="eid" />
					<div class="controls">
						<input type="text" class="span3" name="ename" id="ename" placeholder="输入包材名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="etrans">是否需要转换</label>
					<div class="controls">
						<select id="etrans" name="etrans" class="form-control">
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
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
<script type="text/javascript">
$(document).ready(function() {	 
    addTypeFormValidate();
    addeditFormValidate();
});

function addTypeFormValidate() {
	$("#addtype-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					}
				},

				messages : {
					name : {
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

function deleteOne(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="deletetype" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
function edit(id,name,trans){
	$('#eid').val(id);
	$("#ename").val(name);
	$("#etrans").val(trans);
	$('#editOne-modal').modal('show');
}
function addeditFormValidate() {
	$("#edittype-form").validate(
			{
				debug : true,
				rules : {
					ename : {
						required : true
					}
				},

				messages : {
					ename : {
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

function popMessage(){
	alert("修改成功");
}
</script>

<jsp:include page="../includes/footer.jsp" />