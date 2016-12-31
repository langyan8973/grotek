<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="管理员账号" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>管理员账号</h3>
                    <div class="span10 pull-right">
                        <a id="addOne" class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
                            <span>&#43;</span>
                            新增管理员账号
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
                                <th class="span4 sortable">
                                    用户名
                                </th>
                                <th class="span3 sortable">
                                    角色
                                </th>
                                <th class="span4 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="i" value="0"></c:set>
                        <c:forEach items="${admins}" var="admin">
                        	<tr>
	                             <td>
	                                ${admin.name}
	                            </td>
	                            <td>
	                                ${admin.role.name}
	                            </td>
	                            <td>
	                            <c:if test="${admin.role.id!=1 }">
	                                <a id="editdept" class="btn btn-mini btn-warning"  href="javascript:edit(${admin.id },${admin.role.id })">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${admin.id})">删除</button></td>
								</c:if>
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
		<c:url var="adddeptUrl" value="addone" />
		<form id="adddept-form" class="form-horizontal" action="${adddeptUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">创建管理员账号</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">用户名</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入用户名" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">角色</label>
					<div class="controls">
						<c:set var="selval" value=""/>
						<c:if test="${roles != null}">
							<c:set var="selval" value="${roles[0].id }"/>
						</c:if>
						<select class="span3" name="role" id="role"
							val="${selval }">
							<c:forEach items="${roles}" var="role">
								<option value="${role.id }">
									${role.name }
								</option>
							</c:forEach>
						</select>
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
		<c:url var="editdeptUrl" value="editone" />
		<form id="editdept-form" class="form-horizontal" action="${editdeptUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑管理员角色</h3>
			</div>
			<br>
			<fieldset>
				<input id="eid" name="eid" type="hidden" value=""  />
				<div class="control-group">
					<label class="control-label" for="code">角色</label>
					<div class="controls">
						<c:set var="selval" value=""/>
						<c:if test="${roles != null}">
							<c:set var="selval" value="${roles[0].id }"/>
						</c:if>
						<select class="span3" name="erole" id="erole"
							val="${selval }">
							<c:forEach items="${roles}" var="role">
								<option value="${role.id }">
									${role.name }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="edit-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
<script>

$(document).ready(function() {	 
    addDeptFormValidate();
});

function addDeptFormValidate() {
	$("#addadmin-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					role : {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					role : {
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

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
function edit(id,role){
	$('#eid').val(id);
	$("#erole").val(role);
	$('#editOne-modal').modal('show');
}

function popMessage(){
	alert("修改成功");
}


</script>
<jsp:include page="../includes/footer.jsp" />