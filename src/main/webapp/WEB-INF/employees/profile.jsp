<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="员工信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span5">
                        <img src="${simageUrl }/${employee.headimg }" class="avatar img-circle" style="width:100px;height:100px;" />
                        <h3 class="name">${employee.fullname }</h3>
                        <span class="area">${employee.code }</span>
                    </div>
                    <div class="span7">
                    	<div class="row-fluid">
	                    	<a class="btn-flat icon pull-right delete-user"  title="删除" data-placement="top"
		                     href="javascript:deleteone('${employee.id }')">
		                        <i class="icon-trash"></i>
		                    </a>
                    		<c:url var="editUrl" value="/manager/employees/edit?id=${employee.id }"></c:url>
		                    <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="编辑员工信息" data-placement="top"
		                     href="${editUrl }">
		                        <i class="icon-edit"></i>
		                    </a>
		                    <a class="btn-flat icon pull-right delete-user" data-toggle="modal" title="修改员工密码" data-placement="top"
		                     href="#pass-modal">
		                        修改密码
		                    </a>
		                    <c:url var="changeUrl" value="/manager/employees/change?id=${employee.id }"></c:url>
		                    <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="调整员工部门或职务" data-placement="top"
		                     href="${changeUrl }">
		                        职务调整
		                    </a>
		                    <c:url var="rewardUrl" value="/manager/employees/reward?id=${employee.id }"></c:url>
		                    <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="创建员工奖励" data-placement="top"
		                     href="${rewardUrl }">
		                        新增奖励
		                    </a>
                    	</div>
                    	<div class="row-fluid" style="padding-left:30px; padding-top:40px;">
                    		<c:url var="workUrl" value="/manager/statics/works?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;" href="${workUrl }" target="_Blank">周项目工作</a>
                    		<c:url var="laborUrl" value="/manager/statics/laborhours?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;" href="${laborUrl }" target="_Blank">工作时间</a>
                    		<c:url var="saleUrl" value="/manager/statics/sales?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;"  href="${saleUrl }" target="_Blank">工作业绩</a>
                    		<c:url var="incomeUrl" value="/manager/statics/totalincome?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;"  href="${incomeUrl }" target="_Blank">收入总和</a>
                    		<c:url var="serUrl" value="/manager/statics/service?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;"  href="${serUrl }" target="_Blank">工时费用统计</a>
                    		<c:url var="typeUrl" value="/manager/statics/jobtype?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;"  href="${typeUrl }" target="_Blank">工时分类统计</a>
                    		<c:url var="deaUrl" value="/manager/statics/totalcost?eid=${employee.id }"></c:url>
                    		<a class="label label-info" style="margin-right:20px;"  href="${deaUrl }" target="_Blank">总费用统计</a>
                    	</div>
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
                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>部门：${employee.dept.name } </li>
	                            <li>职务：${employee.position.name } </li>
	                        </ul>
                        	<small>
                        		<ul class="infolist-inline">
		                            <li>性别：${employee.sex } </li>
		                            <li>手机号：${employee.mobile } </li>
		                            <li>固定电话：${employee.phone } </li>
		                            <li>地址：${employee.address }</li>
		                            <li>邮编：${employee.postcode }</li>
		                            <li><a href="${bimageUrl }/${employee.regform }">登记表下载</a></li>
		                        </ul>
		                        <ul class="infolist-inline">
			                        <c:choose>
									   <c:when test="${gasoline==null }">   
			                                <li>每公里汽油费：未设置 </li>   
									   </c:when>
									   <c:otherwise> 
									    	<li>每公里汽油费：${gasoline.exs }元 </li> 
									    	<li>更新日期：<fmt:formatDate value='${gasoline.date }'
															type='date' pattern='yyyy-MM-dd' /> 
											</li> 
									   </c:otherwise>
									</c:choose> 		                            
		                            <li><a href="#editGaso-modal"  data-toggle="modal">设置</a></li>
		                        </ul>
                        	</small>
                        </h4>                        
                    </div>

                    <!-- side address column -->
                    <div class="span12 address">
                        <div class="profile-box">
                            <h6>晋升过程</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span2">
                                            时间
                                        </th>
                                        <th class="span2">
                                            职务
                                        </th>
                                        <th class="span2">
                                            主管领导
                                        </th>
                                        <th class="span4">
                                            评价
                                        </th>
                                        <th class="span2">
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${proms}" var="prom">
			                        	<tr>
			                        		<td>
				                                <fmt:formatDate value='${prom.date}'
													type='date' pattern='yyyy-MM-dd' />
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
				                            <td>
				                            <button class="btn btn-mini btn-danger" type="button" onclick="deleteProm(${prom.id})">删除</button></td>
				                            </td>
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>

                            
                        </div>
                        
                        <div class="profile-box">
                            <h6>奖励情况</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span2">
                                            时间
                                        </th>
                                        <th class="span2">
                                            项目
                                        </th>
                                        <th class="span2">
                                            额度(元)
                                        </th>
                                        <th class="span4">
                                            说明
                                        </th>
                                        <th class="span2">
                                            操作
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${rewards}" var="reward">
			                        	<tr>
			                        		<td>
				                                <fmt:formatDate value='${reward.date}'
													type='date' pattern='yyyy-MM-dd' />
				                            </td>
				                            <td>
				                                ${reward.item}
				                            </td>
				                            <td>
				                                ${reward.amount }
				                            </td>
				                            <td>
				                                ${reward.description}
				                            </td>
				                            <td>
				                            <button class="btn btn-mini btn-danger" type="button" onclick="deleteReward(${reward.id})">删除</button></td>
				                            </td>
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="editGaso-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editgasoUrl" value="editgaso" />
		<form id="editgaso-form" class="form-horizontal" action="${editgasoUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑员工汽油费</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="exs">每公里汽油费(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="exs" id="exs" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${gasoline.exs }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="gid" id="gid" value="${gasoline.id }">
						<input type="hidden" name="eid" id="eid" value="${employee.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	
	<div class="modal hide fade" id="pass-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="passUrl" value="resetpass" />
		<form id="pass-form" class="form-horizontal" action="${passUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>修改员工密码</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="pass">新密码</label>
					<div class="controls">
						<input type="password" class="span3" name="pass" id="pass" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value=""/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="cpass">确认新密码</label>
					<div class="controls">
						<input type="password" class="span3" name="cpass" id="cpass" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value=""/>
						<span class="help-inline"></span>
						<input type="hidden" name="eid" id="eid" value="${employee.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="reset-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
    
<script type="text/javascript">
$(document).ready(
		function() {			
			editgasoFormValidate();
			passFormValidate();
		});
function editgasoFormValidate() {
	$("#editgaso-form").validate(
			{
				debug : true,
				rules : {
					exs : {
						required : true
					}
				},

				messages : {
					exs : {
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

function passFormValidate() {
	$("#pass-form").validate(
			{
				debug : true,
				rules : {
					pass : {
						required : true
					},
					cpass : {
						required : true
					}
				},

				messages : {
					pass : {
						required : "必填"
					},
					cpass : {
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

function deleteone(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = '<c:url value="index" />';
		}).fail(function() {
		});
	});
}
function deleteProm(id){
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="deleteprom" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
function deleteReward(id){
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="deletereward" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
</script>

<jsp:include page="../includes/footer.jsp" />