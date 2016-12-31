<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="新增员工奖励" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
	<c:url var="cssUrlp" value="/assets/css/compiled/empchange.css" />
    <link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
    <!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content">
        <div class="container-fluid">
        	<c:url var="editempUrl" value="rewardone" />
            <form id="editemp-form" class="form-horizontal" action="${editempUrl }" method="post" enctype="multipart/form-data">
            <fieldset>
            <div class="settings-wrapper" id="pad-wrapper">
				
                <div class="span7 personal-info">
                    <!-- <div class="alert alert-info">
                        <i class="icon-lightbulb"></i>
                        This page shows an example of an alternative layout with the main navbar on the top
                        <br /> using <strong>.content.wide-content</strong> for this main container
                    </div> -->
                    <h5 class="personal-title">${employee.fullname }的奖励</h5>

                        <div class="field-box">
                            <label>奖励项目:</label>
                            <input id="item" name="item" class="span5 inline-input" type="text" value="" />
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>奖励额度(元):</label>
                            <input id="amount" name="amount" class="span5 inline-input" type="text" value=""  onkeyup="clearNoNum(this)" />
                            <span class="help-inline"></span>
                        </div>
                        <div class="field-box">
                            <label>说明:</label>
                            <textarea rows="5" path="description" name='description' id="description" class="span5" placeholder="简要说明，50字以内" maxlength="50"></textarea>
                        </div>
                        <input type="hidden" name="id" id="id" value="${employee.id }"/>
                        <input type="hidden" name="reward" id="reward" value="${reward }"/>
                        <div class="span6 field-box actions">
                            <input type="submit" class="btn-glow primary" value="保存" />
                        </div>
                </div>
            </div>
            </fieldset>
            </form>
        </div>
    </div>
    <!-- end main container -->
    <script>
		$(document)
				.ready(
						function() {
							addFormValidate();	
						});
	
		function addFormValidate() {
			$("#").validate({
				debug : true,
				rules : {
					item : {
						required : true
					},
					amount : {
						required : true
					},
					description : {
						required : true
					}
				},
	
				messages : {
					item : {
						required : "必填"
					},
					amount : {
						required : "必填"
					},
					description : {
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