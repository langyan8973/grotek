<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="新增用工" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">   
            <c:url var="addUrl" value="addone" />
            <form id="add-form" class="form-horizontal" action="${addUrl }" method="post" enctype="multipart/form-data">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
					<div class="span2 avatar-box">
	                
	                </div>
	                <div class="span8 personal-info">
	                    <h5 class="personal-title">用工信息</h5>

	                        <div class="field-box">
	                            <label>工人:</label>
	                            <input id="lname" name="lname" class="span5 inline-input" type="text" value="${labor.name }" />
	                            <span class="help-inline"></span>
	                            <input id="lid" name="lid" class="span5 inline-input" type="hidden" value="${labor.id }"/>
	                        </div>
	                        <div class="field-box">
	                            <label>基本工资:</label>
	                            <input id="workhours" name="workhours" class="span5 inline-input" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                            <input id="path" name="path" type="hidden"  value="${path }"/>
	                        </div>
	                        <div class="field-box">
	                            <label>加班工资:</label>
	                            <input id="overhours" name="overhours" class="span5 inline-input" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>支付工资:</label>
	                            <input id="pay" name="pay" class="span5 inline-input" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>奖金:</label>
	                            <input id="bonus" name="bonus" class="span5 inline-input" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>工作日期:</label>
	                            <input id="date" name="date" class="span5 inline-input form_datetime" type="text" data-date-format="yyyy-mm-dd" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="span6 field-box actions">
	                            <input type="submit" class="btn-glow primary" value="保存" />
	                        </div>
	                </div>
	            </div>
	            </fieldset>
	            </form>
            </div>
        </div>
        <div class="modal fade" id="dealerModal" tabindex="-2" role="dialog"
			aria-labelledby="myModalLabel2" aria-hidden="true" style="display:none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel2">
							选择工人
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
			
			$('#lname').bind('click', function() {
				$('#dealerModal').modal( {
					keyboard : false
				})
			});
			$('#treeChoosedealerBoxId').load('<c:url value="/manager/productlabors/select" />');
			
			addFormValidate();
			
			$('#date').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
		});

function addFormValidate() {
	$("#add-form").validate({
		debug : true,
		rules : {
			lname : {
				required : true
			},
			workhours : {
				required : true
			},
			pay : {
				required : true
			},
			date : {
				required : true
			}
		},
		
		messages : {
			lname : {
				required : "必填"
			},
			workhours : {
				required : "必填"
			},
			pay : {
				required : "必填"
			},
			date : {
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
function clickLaborNote(id,sname) {
	$('#lname').val(sname);
	$('#lid').val(id);
	$('#dealerModal').modal('hide');

} 

</script>

<jsp:include page="../includes/footer.jsp" />