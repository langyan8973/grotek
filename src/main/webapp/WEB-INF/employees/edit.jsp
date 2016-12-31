<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="编辑员工信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
	<c:url var="cssUrlp" value="/assets/css/compiled/personal-info.css" />
    <link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
    <!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content">
        <div class="container-fluid">
        	<c:url var="editempUrl" value="editone" />
            <form id="editemp-form" class="form-horizontal" action="${editempUrl }" method="post" enctype="multipart/form-data">
            <fieldset>
            <div class="settings-wrapper" id="pad-wrapper">
                <div class="span3 avatar-box">
                <c:url var="noneUrl" value="/assets/img/no-img-gallery.png" />
                    <div class="personal-image">
                    	<c:if test="${employee.headimg!=null }">
                    		<c:url var="noneUrl" value="${imageUrl }/${employee.headimg }" />
                    	</c:if>
                        <img id="headimage" src="${noneUrl }" class="avatar img-circle" />
                        <input type="file" name="uploadheadimg" id="headimg_file_upload" />
                    </div>
                    <hr>
                    <div class="personal-image">
                    	<c:if test="${employee.regform!=null }">
                    		<c:url var="noneUrl" value="${imageUrl }/${employee.regform }" />
                    	</c:if>
                        <img id="regformimage" src="${noneUrl }"  />
                        <input type="file" name="uploadregform" id="regform_file_upload"/>
                    </div>
                </div>

                <div class="span7 personal-info">
                    <!-- <div class="alert alert-info">
                        <i class="icon-lightbulb"></i>
                        This page shows an example of an alternative layout with the main navbar on the top
                        <br /> using <strong>.content.wide-content</strong> for this main container
                    </div> -->
                    <h5 class="personal-title">员工信息</h5>

                        <div class="field-box">
                            <label>姓名:</label>
                            <input id="fullname" name="fullname" class="span5 inline-input" type="text" value="${employee.fullname }" />
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>编码:</label>
                            <input id="code" name="code" class="span5 inline-input" type="text" value="${employee.code }" />
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>性别:</label>
                            <div class="ui-select">
                                <select id="sex" name="sex">
                                <c:choose>
								   <c:when test="${employee.sex=='男' }">  
								    <option value="男" selected="">男</option>
                                    <option value="女">女</option>       
								   </c:when>
								   <c:otherwise> 
								    <option value="男">男</option>
                                    <option value="女" selected="">女</option>
								   </c:otherwise>
								</c:choose>   
                                </select>
                            </div>
                        </div>
                        <div class="field-box">
                            <label>地址:</label>
                            <input id="address" name="address" class="span5 inline-input" type="text" value="${employee.address }" />
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>手机号:</label>
                            <input id="mobile" name="mobile" class="span5 inline-input" type="text" value="${employee.mobile }" onkeyup="clearNoNum(this)"/>
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>邮编:</label>
                            <input id="postcode" name="postcode" class="span5 inline-input" type="text" value="${employee.postcode }" onkeyup="clearNoNum(this)"/>
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>固定电话:</label>
                            <input id="phone" name="phone" class="span5 inline-input" type="text" value="${employee.phone }" onkeyup="clearNoNum(this)"/>
                            <span class="help-inline"></span>
                        </div>
                        <%-- <div class="field-box">
                            <label>职位:</label>
                            <div class="ui-select">
                                <c:set var="selval" value=""/>
								<c:if test="${positions != null}">
									<c:set var="selval" value="${positions[0].id }"/>
								</c:if>
								<select class="form-control" name="position" id="position">
									<c:forEach items="${positions}" var="position">
										<c:choose>
										   <c:when test="${employee.position.id==position.id }">   
		                                    <option value="${position.id }" selected="">
												${position.name }
											</option>      
										   </c:when>
										   <c:otherwise> 
										    <option value="${position.id }">
												${position.name }
											</option>
										   </c:otherwise>
										</c:choose> 
									</c:forEach>
								</select>
                            </div>
                        </div>
                        <div class="field-box">
                            <label>部门:</label>
                            <div class="ui-select">
                                <c:set var="seldept" value=""/>
								<c:if test="${departments != null}">
									<c:set var="seldept" value="${departments[0].id }"/>
								</c:if>
								<select class="form-control" name="dept" id="dept"
									value="${employee.dept.id }">
									<c:forEach items="${departments}" var="dept">
										<option value="${dept.id }">
											${dept.name }
										</option>
										<c:choose>
										   <c:when test="${employee.dept.id==dept.id }">   
		                                    <option value="${dept.id }" selected="">
												${dept.name }
											</option>     
										   </c:when>
										   <c:otherwise> 
										    <option value="${dept.id }">
												${dept.name }
											</option>
										   </c:otherwise>
										</c:choose> 
									</c:forEach>
								</select>
                            </div>
                        </div> 
                        <div class="field-box">
                            <label>主管领导:</label>
                            <input id="sname" name="sname" class="span5 inline-input" type="text" value="${employee.sname }"/>
                            <span class="help-inline"></span>
                        </div>
                        <input type="hidden" name="superior" id="superior" value="${employee.superior }"/>--%>
                        <input type="hidden" name="headimg" id="headimg" value="${employee.headimg }"/>
                        <input type="hidden" name="regform" id="regform" value="${employee.regform }"/>
                        <input type="hidden" name="id" id="id" value="${employee.id }"/>
                        <div class="span6 field-box actions">
                            <input type="submit" class="btn-glow primary" value="保存" />
                        </div>
                </div>
            </div>
            </fieldset>
            </form>
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
						选择主管领导
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
    <!-- end main container -->
    <c:url var="cssUrl" value="/assets/uploadify-v3.1/uploadify.css" />
	<link rel="stylesheet" href="${cssUrl}" type="text/css"></link>
	<c:url var="jsUrl" value="/assets/uploadify-v3.1/jquery.uploadify-3.1.min.js" />
	<script type="text/javascript" src="${jsUrl}"></script>
	<script>
		var recipeUpload;
		$(document)
				.ready(
						function() {
							addFormValidate();
	
							$("#headimg_file_upload")
									.uploadify(
											{
												'removeTimeOut' : 0,
												'multi' : false,
												'buttonText' : '上传头像...',
												'swf' : '<c:url value="/assets/uploadify-v3.1/uploadify.swf"/>',
												'uploader' : '<c:url value="bash;jsessionid=${pageContext.session.id}"/>',
												'fileTypeExts' : '*.jpg;*.png;*.gif;*.bmp',
												'onDialogOpen' : function() {
													$("#addemp-form").attr("disabled", true);
												},
												'onUploadSuccess' : function(file, data, response) {
													if (data == "failure" || data == "") {
														bootbox.alert({
															buttons : {
																ok : {
																	label : '确定',
																	className : 'btn-myStyle'
																}
															},
															message : "抱歉，图片：" + file.name + "上传失败！",
															title : "提示",
														});
													} else {
														
														$("#headimage").attr('src','${imageUrl}/'+data);
														$("#headimg").val(data);
													}
	
												},
												'onQueueComplete' : function(stats) {
													$("#addemp-form").attr("disabled", false);
												}
	
											});
							$("#regform_file_upload")
							.uploadify(
									{
										'removeTimeOut' : 0,
										'multi' : false,
										'buttonText' : '上传员工登记表',
										'swf' : '<c:url value="/assets/uploadify-v3.1/uploadify.swf"/>',
										'uploader' : '<c:url value="bash;jsessionid=${pageContext.session.id}"/>',
										'fileTypeExts' : '*.jpg;*.png;*.gif;*.bmp',
										'onDialogOpen' : function() {
											$("#addemp-form").attr("disabled", true);
										},
										'onUploadSuccess' : function(file, data, response) {
											if (data == "failure" || data == "") {
												bootbox.alert({
													buttons : {
														ok : {
															label : '确定',
															className : 'btn-myStyle'
														}
													},
													message : "抱歉，图片：" + file.name + "上传失败！",
													title : "提示",
												});
											} else {
												
												$("#regformimage").attr('src','${imageUrl}/'+data);
												$("#regform").val(data);
											}

										},
										'onQueueComplete' : function(stats) {
											$("#addemp-form").attr("disabled", false);
										}

									});
							$('#sname').bind('click', function() {
								$('#myModal').modal( {
									keyboard : false
								})
							});
							$('#treeChooseTypeBoxId').load('superiors');
	
						});
	
		function addFormValidate() {
			$("#editemp-form").validate({
				debug : true,
				rules : {
					fullname : {
						required : true
					},
					code : {
						required : true
					},
					address : {
						required : true
					},
					postcode : {
						required : true
					},
					mobile : {
						required : true
					},
					phone : {
						required : true
					}
				},
	
				messages : {
					fullname : {
						required : "必填"
					},
					code : {
						required : "必填"
					},
					address : {
						required : "必填"
					},
					postcode : {
						required : "必填"
					},
					mobile : {
						required : "必填"
					},
					phone : {
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
		function clickDocType(id,sname) {
			$('#sname').val(sname);
			$('#superior').val(id);
			$('#myModal').modal('hide');
		}
	</script>
<jsp:include page="../includes/footer.jsp" />