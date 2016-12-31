<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="新增员工项目工作" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="workcontent">     
        <div class="container-fluid">   
            <c:url var="addUrl" value="addone" />
            <form id="add-form" class="form-horizontal" action="${addUrl }" method="post" enctype="multipart/form-data">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
	            	<div class="span2 avatar-box">
	                
	                </div>
	                <div class="span8 personal-info">
	                    <h5 class="personal-title">员工项目工作</h5>

	                        <div class="field-box">
	                            <label>客户:</label>
	                            <input id="dname" name="dname" class="span5" type="text" value="" />
	                            <span class="help-inline"></span>
	                            <input id="did" name="did" class="span5" type="hidden" value=""/>
	                        </div>
	                        <div class="field-box">
	                            <label>工作类型:</label>
	                            <c:set var="selval" value=""/>
								<c:if test="${types != null}">
									<c:set var="selval" value="${types[0].id }"/>
								</c:if>
								<select class="span5" name="type" id="type"
									val="${selval }">
									<c:forEach items="${types}" var="type">
										<option value="${type.id }">
											${type.name }
										</option>
									</c:forEach>
								</select>
	                        </div>
	                        <div class="field-box">
	                            <label>工作内容:</label>
	                            <input id="content" name="content" class="span5" type="text" value="" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>工作地点:</label>
	                            <input id="place" name="place" class="span5" type="text" value=""/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>时间段:</label>
	                            <input id="setime" name="setime" class="span5" type="text" value="" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>工时:</label>
	                            <input id="laborhour" name="laborhour" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>日期:</label>
	                            <input id="date" name="date" class="span5 form_datetime" type="text" data-date-format="yyyy-mm-dd" />
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
							选择经销商
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
			
			$('#dname').bind('click', function() {
				$('#dealerModal').modal( {
					keyboard : false
				})
			});
			$('#treeChoosedealerBoxId').load('<c:url value="/work/selects/dealers" />');
			
			$('#date').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
			
			addFormValidate();
		});

function addFormValidate() {
	$("#add-form").validate({
		debug : true,
		rules : {
			dname : {
				required : true
			},
			content : {
				required : true
			},
			place : {
				required : true
			},
			setime : {
				required : true
			},
			laborhour : {
				required : true
			},
			date : {
				required : true
			}
		},
		
		messages : {
			dname : {
				required : "必填"
			},
			content : {
				required : "必填"
			},
			place : {
				required : "必填"
			},
			setime : {
				required : "必填"
			},
			laborhour : {
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
function clickDocType(id,sname) {
	$('#dname').val(sname);
	$('#did').val(id);
	$('#dealerModal').modal('hide');

} 

</script>

<jsp:include page="../includes/footer.jsp" />