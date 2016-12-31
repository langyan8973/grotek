<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="职位管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>职位列表</h3>
                    <div class="span10 pull-right">
                        <a id="addOne" class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
                            <span>&#43;</span>
                            新增职位
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
                                <th class="span2 sortable">
                                    名称
                                </th>
                                <th class="span2 sortable">
                                    年销售目标
                                </th>
                                <th class="span1 sortable">
                                    提成比例
                                </th>
                                <th class="span2 sortable">
                                    薪酬待遇
                                </th>
                                <th class="span2 sortable">
                                    更新日期
                                </th>
                                <th class="span2 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="i" value="0"></c:set>
                        <c:forEach items="${positions}" var="position">
                        	<tr>
	                             <td>
	                                ${position.name}
	                            </td>
	                            <td>
	                                ${position.salestargets}
	                            </td>
	                            <td>
	                                ${position.amortization}
	                            </td>
	                            <td>
	                                ${position.salary}
	                            </td>
	                            <td>	                                
	                                <fmt:formatDate value='${position.date}'
										type='date' pattern='yyyy-MM-dd HH:mm:ss' />
	                            </td>
	                            <td>
	                            	<c:url var ="infoUrl" value="/manager/positions/detail?id=${position.id }"/>
	                            	<a id="positioninfo" class="btn btn-mini btn-info"  href="${infoUrl }">
										详细
									</a>
	                                <a id="editdept" class="btn btn-mini btn-warning"  
						href="javascript:edit(${position.id},'${position.name}','${position.salestargets}','${position.objective}',${position.amortization},
						${position.mobileAllow},${position.travelAllow},${position.salary},'${position.description}',${position.costhour })">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${position.id})">删除</button></td>
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
		<c:url var="addpositionUrl" value="addone" />
		<form id="addposition-form" class="form-horizontal" action="${addpositionUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">创建职位</h3>
			</div>
			<br>
			<div class="modal-body">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">职位名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入职位名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="objective">岗位责任</label>
					<div class="controls">
						<textarea rows="3" path="objective" name='objective' id="objective" class="span3" placeholder="输入岗位目标，50字以内" maxlength="50"></textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="salestargets">年销售目标</label>
					<div class="controls">
						<input type="text" class="span3" name="salestargets" id="salestargets" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="amortization">提成比例</label>
					<div class="controls">
						<input type="text" class="span3" name="amortization" id="amortization" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="salary">薪酬待遇</label>
					<div class="controls">
						<input type="text" class="span3" name="salary" id="salary" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="travelAllow">出差标准</label>
					<div class="controls">
						<input type="text" class="span3" name="travelAllow" id="travelAllow" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="mobileAllow">手机补助</label>
					<div class="controls">
						<input type="text" class="span3" name="mobileAllow" id="mobileAllow" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="costhour">每小时费用</label>
					<div class="controls">
						<input type="text" class="span3" name="costhour" id="costhour" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="description">说明</label>
					<div class="controls">
						<textarea rows="3" path="description" name='description' id="description" class="span3" placeholder="简要说明，50字以内" maxlength="50"></textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
			</div>
		</form>
	</div>
	<div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editpositionUrl" value="editone" />
		<form id="editposition-form" class="form-horizontal" action="${editpositionUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑职位</h3>
			</div>
			<br>
			<div class="modal-body">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="ename">职位名称</label>
					<div class="controls">
						<input type="text" class="span3" name="ename" id="ename" placeholder="输入职位名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<input type="hidden" name="eid" id="eid">
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="eobjective">岗位责任</label>
					<div class="controls">
						<textarea rows="3" path="eobjective" name='eobjective' id="eobjective" class="span3" placeholder="输入岗位目标，50字以内" maxlength="50"></textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="esalestargets">年销售目标</label>
					<div class="controls">
						<input type="text" class="span3" name="esalestargets" id="esalestargets" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="eamortization">提成比例</label>
					<div class="controls">
						<input type="text" class="span3" name="eamortization" id="eamortization" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="esalary">薪酬待遇</label>
					<div class="controls">
						<input type="text" class="span3" name="esalary" id="esalary" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="etravelAllow">出差标准</label>
					<div class="controls">
						<input type="text" class="span3" name="etravelAllow" id="etravelAllow" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="emobileAllow">手机补助</label>
					<div class="controls">
						<input type="text" class="span3" name="emobileAllow" id="emobileAllow" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="ecosthour">每小时费用</label>
					<div class="controls">
						<input type="text" class="span3" name="ecosthour" id="ecosthour" placeholder="输入数字" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="edescription">说明</label>
					<div class="controls">
						<textarea rows="3" path="edescription" name='edescription' id="edescription" class="span3" placeholder="简要说明，50字以内" maxlength="50"></textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
			</div>
		</form>
	</div>
<script>

$(document).ready(function() {	 
	addPositionFormValidate();
	addeditFormValidate();
});

function addPositionFormValidate() {
	$("#addposition-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					salestargets : {
						required : true
					},
					amortization: {
						required : true
					},
					salary: {
						required : true
					},
					travelAllow: {
						required : true
					},
					mobileAllow: {
						required : true
					},
					costhour: {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					salestargets : {
						required : "必填"
					},
					amortization : {
						required : "必填"
					},
					salary : {
						required : "必填"
					},
					travelAllow : {
						required : "必填"
					},
					mobileAllow : {
						required : "必填"
					},
					costhour : {
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
function edit(id,name,salestargets,objective,amortization,mobileAllow,travelAllow,salary,description,costhour){
	$('#eid').val(id);
	$("#ename").val(name);
	$("#esalestargets").val(salestargets);
	$("#eobjective").val(objective);
	$("#eamortization").val(amortization);
	$("#emobileAllow").val(mobileAllow);
	$("#etravelAllow").val(travelAllow);
	$("#esalary").val(salary);
	$("#edescription").val(description);
	$("#ecosthour").val(costhour);
	$('#editOne-modal').modal('show');
}
function addeditFormValidate() {
	$("#editposition-form").validate(
			{
				debug : true,
				rules : {
					ename : {
						required : true
					},
					esalestargets : {
						required : true
					},
					eamortization: {
						required : true
					},
					esalary: {
						required : true
					},
					etravelAllow: {
						required : true
					},
					emobileAllow: {
						required : true
					},
					ecosthour: {
						required : true
					}
				},

				messages : {
					ename : {
						required : "必填"
					},
					esalestargets : {
						required : "必填"
					},
					eamortization : {
						required : "必填"
					},
					esalary : {
						required : "必填"
					},
					etravelAllow : {
						required : "必填"
					},
					emobileAllow : {
						required : "必填"
					},
					ecosthour : {
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

function clearNoNum(obj)
{
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}


</script>
<jsp:include page="../includes/footer.jsp" />