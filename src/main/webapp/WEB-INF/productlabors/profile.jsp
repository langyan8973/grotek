<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="工人信息" scope="request" />
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
                    <div class="span8">
                        <h3 class="name">${labor.name }</h3>
                    </div>
                    <a class="btn-flat icon pull-right delete-user" href="#addOne-modal" data-toggle="modal">
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
                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>编码：${labor.code } </li>
	                            <li>性别：${labor.sex } </li>
	                            <li>年龄：${labor.age } </li>
	                            
	                        </ul>
                        	<small>
                        		<ul class="infolist-inline">
		                            <li>开始工作时间：<fmt:formatDate value='${labor.starttime}'
													type='date' pattern='yyyy-MM-dd' /> </li>
		                            <li>每小时工资：${labor.hourprice }(元) </li>
		                            <li>联系电话：${labor.phone } </li>
		                        </ul>
		                        <ul class="infolist-inline">
		                            <li>籍贯：${labor.nativeplace } </li>
		                        </ul>
		                        <ul class="infolist-inline">
		                            <li>住址：${labor.address } </li>
		                        </ul>
		                        <ul class="infolist-inline">
		                            <li>备注：${labor.remarks } </li>
		                        </ul>
                        	</small>
                        </h4>                        
                    </div>

                    <!-- side address column -->
                    <div class="span12 address">
                        <div class="profile-box">
                        	<h6>用工记录
                        		<small>
                        			<c:url var="moreprice" value="/manager/labortimes/index?context=${labor.name }"></c:url>
                        			<a class="icon pull-right" href="${moreprice }" style="margin:20px 10px 0 10px;">
					                    查看更多
					                </a>
                        			
                        			<c:url var="addprice" value="/manager/labortimes/new?pid=${labor.id }"></c:url>
                        			<a class="icon pull-right" href="${addprice }" style="margin:20px 10px 0 10px;">
					                    <i class="icon-plus"></i>添加
					                </a>
                        		</small>
                        	</h6>
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span3 sortable">
		                                    工作时间
		                                </th>
		                                <th class="span3 sortable">
		                                    加班时间
		                                </th>
		                                <th class="span3 sortable">
		                                    支付工资(元)
		                                </th>
		                                <th class="span3 sortable">
		                                    工作日期
		                                </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${times}" var="time">
			                        	<tr>
			                        		<td>
				                            	${time.workhours }
				                            </td>
				                            <td>
				                                ${time.overhours }
				                            </td>
				                            <td>
				                            	${time.pay }
				                            </td>
				                            <td>
				                                <fmt:formatDate value='${time.date }'
																type='date' pattern='yyyy-MM-dd' />
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
    <div class="modal hide fade" id="addOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="addproductboxUrl" value="editone" />
		<form id="addproductbox-form" class="form-horizontal" action="${addproductboxUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">工人信息编辑</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">姓名</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入工人姓名" 
						style="IME-MODE: disabled;"  maxlength="20" value="${labor.name }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${labor.id }"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入编码" 
						style="IME-MODE: disabled;"  maxlength="20" value="${labor.code }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="sex">性别</label>
					<div class="controls">
						<select id="sex" name="sex" class="form-control span3">
							<c:choose>
							   <c:when test="${labor.sex=='男' }">  
							    <option value="男" selected="">男</option>
                                   <option value="女">女</option>       
							   </c:when>
							   <c:otherwise> 
							    <option value="男">男</option>
                                   <option value="女" selected="">女</option>
							   </c:otherwise>
							</c:choose>  
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="age">年龄</label>
					<div class="controls">
						<input type="text" class="span3" name="age" id="age" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${labor.age }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="phone">联系电话</label>
					<div class="controls">
						<input type="text" class="span3" name="phone" id="phone" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${labor.phone }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="nativeplace">籍贯</label>
					<div class="controls">
						<input type="text" class="span3" name="nativeplace" id="nativeplace" placeholder="" 
						style="IME-MODE: disabled;"   value="${labor.nativeplace }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address">住址</label>
					<div class="controls">
						<input type="text" class="span3" name="address" id="address" placeholder="" 
						style="IME-MODE: disabled;" value="${labor.address }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="starttime">开始工作时间</label>
					<div class="controls">
						<input type="text" class="span3" name="starttime" id="starttime" data-date-format="yyyy-mm-dd hh:ii"  value="<fmt:formatDate value='${labor.starttime}'
													type='date' pattern='yyyy-MM-dd' />"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="hourprice">每小时工资(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="hourprice" id="hourprice" placeholder="" 
						style="IME-MODE: disabled;" value="${labor.hourprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remarks">备注</label>
					<div class="controls">
						<textarea rows="2" path="remarks" name='remarks' id="remarks" class="span3" placeholder="" maxlength="50">${labor.remarks }</textarea>
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
<c:url var="cssUrl1" value="/assets/css/bootstrap/bootstrap-datetimepicker.min.css" />
<link href="${cssUrl1}" rel="stylesheet" media="screen">
<c:url var="jsUrl" value="/assets/uploadify-v3.1/jquery.uploadify-3.1.min.js" />
<script type="text/javascript" src="${jsUrl}"></script>
<c:url var="jsUrl1" value="/assets/js/moment.js" />
<script type="text/javascript" src="${jsUrl1 }"></script>
<c:url var="jsUrl2" value="/assets/js/bootstrap-datetimepicker.js" />
<script type="text/javascript" src="${jsUrl2 }" charset="UTF-8"></script>
<c:url var="jsUrl3" value="/assets/js/bootstrap-datetimepicker.zh-CN.js" />
<script type="text/javascript" src="${jsUrl3 }" charset="UTF-8"></script>        
<script type="text/javascript">
$(document).ready(
		function() {			
			addProductBoxFormValidate();
			
			$('#starttime').datetimepicker({
				language : 'zh-CN'
			}); 
		});
function addProductBoxFormValidate() {
	$("#addproductbox-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					code: {
						required : true
					},
					sex : {
						required : true
					},
					age: {
						required : true
					},
					phone : {
						required : true
					},
					address: {
						required : true
					},
					starttime : {
						required : true
					},
					hourprice : {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					code : {
						required : "必填"
					},
					sex : {
						required : "必填"
					},
					age : {
						required : "必填"
					},
					phone : {
						required : "必填"
					},
					address : {
						required : "必填"
					},
					starttime : {
						required : "必填"
					},
					hourprice : {
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