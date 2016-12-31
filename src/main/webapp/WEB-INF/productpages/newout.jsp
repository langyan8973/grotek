<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="宣传品出库" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productpages/index"></c:url>
			    <li><a href="${listUrl }">宣传品信息</a></li>
			    <c:url var="storeUrl" value="/manager/productpages/store"></c:url>
			    <li><a href="${storeUrl }">宣传品库存</a></li>
			    <c:url var="inUrl" value="/manager/productpages/in"></c:url>
			    <li><a href="${inUrl }">宣传品入库</a></li>
			    <c:url var="outUrl" value="/manager/productpages/out"></c:url>
			    <li  class="active"><a href="${outUrl }">宣传品出库</a></li>
			    <c:url var="typeUrl" value="/manager/productpages/type"></c:url>
			    <li><a href="${typeUrl }">宣传品类型</a></li>
			</ul> 
            <c:url var="addoutUrl" value="outaddone" />
            <form id="addemp-form" class="form-horizontal" action="${addoutUrl }" method="post" enctype="multipart/form-data">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
					<div class="span2 avatar-box">
	                
	                </div>
	                <div class="span8 personal-info">
	                    <div class="alert alert-info">
	                        <i class="icon-lightbulb"></i>
	                        宣传品出库信息录入
	                    </div>
	                    <h5 class="personal-title">${page.name }</h5>

	                        <div class="field-box">
	                            <label>数量(个):</label>
	                            <input id=count name="count" class="span5 inline-input" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                            <input id="pid" name="pid" class="span5 inline-input" type="hidden" value="${page.id }"/>
	                        </div>
	                        <div class="field-box">
	                            <label>经销商:</label>
	                            <input id="dname" name="dname" class="span5 inline-input" type="text" value=""/>
	                            <span class="help-inline"></span>
	                            <input id="did" name="did" class="span5 inline-input" type="hidden" value=""/>
	                        </div>
	                        <div class="field-box">
	                            <label>出库时间:</label>
	                            <input id="date" name="date" class="span5 inline-input form_datetime" type="text" data-date-format="yyyy-mm-dd hh:ii" />
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
    </div>
    <div class="modal fade" id="dealerModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
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
			addFormValidate();
			$('#dname').bind('click', function() {
				$('#dealerModal').modal( {
					keyboard : false
				})
			});
			$('#treeChoosedealerBoxId').load('<c:url value="/manager/dealers/select" />');
			
			$('#date').datetimepicker({
				language : 'zh-CN'
			});

		});

function addFormValidate() {
	$("#addemp-form").validate({
		debug : true,
		rules : {
			count : {
				required : true
			},
			dname : {
				required : true
			},
			date : {
				required : true
			}
		},
		
		messages : {
			count : {
				required : "必填"
			},
			dname : {
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