<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="员工职务调整" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
	<c:url var="cssUrlp" value="/assets/css/compiled/empchange.css" />
    <link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
  
    <!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content">
        <div class="container-fluid">
        	<c:url var="editempUrl" value="changeone" />
            <form id="editemp-form" class="form-horizontal" action="${editempUrl }" method="post" enctype="multipart/form-data">
            <fieldset>
            <div class="settings-wrapper" id="pad-wrapper">
				
                <div class="span7 personal-info">
                    <!-- <div class="alert alert-info">
                        <i class="icon-lightbulb"></i>
                        This page shows an example of an alternative layout with the main navbar on the top
                        <br /> using <strong>.content.wide-content</strong> for this main container
                    </div> -->
                    <h5 class="personal-title">${employee.fullname }的职务信息</h5>

                        <div class="field-box">
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
                        <div class="field-box">
                            <label>领导评价:</label>
                            <textarea rows="5" path="description" name='description' id="description" class="span5" placeholder="简要评价，50字以内" maxlength="50"></textarea>
                        </div>
                        <input type="hidden" name="prom" id="prom" value="${prom }"/>
                        <input type="hidden" name="superior" id="superior" value="${employee.superior }"/>
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
							$('#sname').bind('click', function() {
								$('#myModal').modal( {
									keyboard : false
								})
							});
							$('#treeChooseTypeBoxId').load('superiors'); 
	
						});
	
		
		function clickEmployeeType(id,sname) {
			$('#sname').val(sname);
			$('#superior').val(id);
			$('#myModal').modal('hide');
		}
	</script>
<jsp:include page="../includes/footer.jsp" />