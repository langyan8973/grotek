<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="填写送货单" scope="request" />
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
                        <h3 class="name">填写送货单</h3>
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
	                <div class="span2">						
     
	                    
	                </div>
	                <div class="span8 table">
	                	<c:url var="addUrl" value="/manager/sendapplies/addpost" />
			            <form id="deliveryOrder" action="${addUrl }" method="post">
				            <fieldset>
	                	<table class="table table-bordered">
	                        <tbody>
	                        	<tr>
	                        		<td colspan="3">
	                        			付款单位
	                        		</td>
	                        		<td colspan="4">
		                            	接收单位
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">名称</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="payName" name="payName" type="text" value="${apply.dealer.name }" />
				                            <span class="help-inline"></span>
				                            <input id="aid" name="aid" type="hidden" value="${apply.id}"/>
				                        </div>
		                            </td>
		                            <td class="span1">名称</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="receiveName" name="receiveName" type="text" value="${dealer.pname }" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">地址</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="payAddress" name="payAddress" type="text" value="${dealer.address }" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                            <td class="span1">地址</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="receiveAddress" name="receiveAddress" type="text" value="${dealer.paddress }" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">联系人</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="payContact" name="payContact" type="text" value="${dealer.contact }" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                            <td class="span1">联系人</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="receiveContact" name="receiveContact" type="text" value="${dealer.pcontact }" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">电话</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="payPhone" name="payPhone" type="text" value="${dealer.mobile }"  onkeyup="clearNoNum(this)" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                            <td class="span1">电话</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="receivePhone" name="receivePhone" type="text" value="${dealer.pphone }"  onkeyup="clearNoNum(this)" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">发单人</td>
	                        		<td colspan="2">
	                        			${employee.fullname }
	                        			<div class="field-box">
				                            <span class="help-inline"></span>
				                            <input id="employee.id" name="employee.id" type="hidden" value="${employee.id }">
				                        </div>
		                            </td>
		                            <td class="span1">客户</td>
	                        		<td colspan="3">
	                        			${dealer.name }
	                        			<div class="field-box">
				                            <span class="help-inline"></span>
				                            <input id="dealer.id" name="dealer.id" type="hidden" value="${dealer.id }">
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">发货日期</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="date" name="date"  type="text" data-date-format="yyyy-mm-dd hh:ii" class="form_datetime" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                            <td class="span1">账户类型</td>
	                        		<td colspan="3">
	                        			${dealer.creditrating.name }
	                        			<%-- <div class="field-box">
				                            <span class="help-inline"></span>
				                            <input id="dealer.creditrating.id" name="dealer.creditrating.id" type="hidden" value="${dealer.creditrating.id }">
				                        </div> --%>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">手机</td>
	                        		<td colspan="2">
	                        			<div class="field-box">
				                            <input id="mobile" name="mobile" type="text" value="${employee.mobile }"  onkeyup="clearNoNum(this)"/>
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                            <td class="span1">购单号码</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="gdnum" name="gdnum" type="text" value="" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1"></td>
	                        		<td colspan="2">
	                        			
		                            </td>
		                            <td class="span1">合同编号</td>
	                        		<td colspan="3">
	                        			<div class="field-box">
				                            <input id="htnum" name="htnum" type="text" value="" />
				                            <span class="help-inline"></span>
				                        </div>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="7" style="text-align:center;">
	                        			<h5>产品</h5>
		                            </td>
		                        </tr>
		                        <tr>
		                        	<td class="span1">编号</td>
	                        		<td class="span2">名称</td>
	                        		<td class="span2">规格</td>
	                        		<td class="span1">每箱数量</td>
	                        		<td class="span1">箱数</td>
	                        		<td class="span1">净重</td>
	                        		<td class="span2">总重</td>
		                        </tr>
		                        <c:set var="totalkg" value="0"></c:set>
		                        <c:set var="count" value="0"></c:set>
		                        <c:set var="sum" value="0"></c:set>
		                        <c:forEach items="${apply.boxitems}" var="boxitme">
			                        <tr>
		                        		<td class="span1">${boxitme.box.code }</td>
		                        		<td class="span1">${boxitme.box.name }</td>
		                        		<td class="span2">${boxitme.box.specification }</td>
		                        		<td class="span1">${boxitme.box.piececount }</td>
		                        		<td class="span1">${boxitme.count }</td>
		                        		<td class="span1">
		                        		<fmt:parseNumber value="${boxitme.box.piececontent*boxitme.count }" integerOnly="true" ></fmt:parseNumber>
		                        		</td>
		                        		<td class="span2">
		                        		<fmt:parseNumber value="${boxitme.count*boxitme.kg }" integerOnly="true" ></fmt:parseNumber>
		                        		</td>
			                        </tr>
			                        <c:set var="totalkg" value="${totalkg+boxitme.kg*boxitme.count }"></c:set>
			                        <c:set var="count" value="${count+boxitme.count }"></c:set>
			                        <c:set var="sum" value="${sum+boxitme.total }"></c:set>
		                        </c:forEach>
		                        <tr>
			                        	<td colspan="4">合计：</td>
		                        		<td class="span1">${count }</td>
		                        		<td class="span1"></td>
		                        		<td class="span2">
		                        		<fmt:parseNumber value="${totalkg }" integerOnly="true" ></fmt:parseNumber>
		                        		</td>
			                    </tr>
			                    <c:if test="${apply.sampleitems[0]!=null }">
		                        	<tr>
			                            <td colspan="7" style="text-align:center;">
	                        			<h5>附带样品</h5>
		                            </td>
			                        </tr>
			                        <tr>
			                        	<td colspan="1">序号</td>
		                        		<td colspan="1">名称</td>
		                        		<td colspan="1">规格</td>
		                        		<td colspan="4">数量</td>
			                        </tr>
			                        <c:set var="m" value="1"></c:set>
			                        <c:forEach items="${apply.sampleitems}" var="sampleitme">
				                        <tr>
				                        	<td colspan="1">${m }</td>
			                        		<td colspan="1">${sampleitme.box.name }</td>
			                        		<td colspan="1">${sampleitme.box.specification }</td>
		                        			<td colspan="4">${sampleitme.count }</td>
				                        </tr>
				                        <c:set var="m" value="${m+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${apply.pageitems[0]!=null }">
		                        	<tr>
			                            <td colspan="7" style="text-align:center;">
	                        			<h5>附带宣传品</h5>
		                            </td>
			                        </tr>
			                        <tr>
			                        	<td colspan="1">序号</td>
		                        		<td colspan="1">名称</td>
		                        		<td colspan="5">数量</td>
			                        </tr>
			                        <c:set var="i" value="1"></c:set>
			                        <c:forEach items="${apply.pageitems}" var="pageitme">
				                        <tr>
				                        	<td colspan="1">${i }</td>
			                        		<td colspan="1">${pageitme.page.name }</td>
		                        			<td colspan="5">${pageitme.count }</td>
				                        </tr>
				                        <c:set var="i" value="${i+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${apply.packitems[0]!=null }">
		                        	<tr>
		                        		
			                            <td colspan="7" style="text-align:center;">
	                        				<h5>附带包材</h5>
	                        			</td>
		                            </td>
			                        </tr>
			                        <tr>
			                        	<td colspan="1">序号</td>
		                        		<td colspan="1">名称</td>
		                        		<td colspan="5">数量</td>
			                        </tr>
			                        <c:set var="i" value="1"></c:set>
			                        <c:forEach items="${apply.packitems}" var="packitme">
				                        <tr>
				                        	<td colspan="1">${i }</td>
			                        		<td colspan="1">${packitme.pack.name }</td>
		                        			<td colspan="5">${packitme.count }</td>
				                        </tr>
				                        <c:set var="i" value="${i+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
	                        </tbody>
	                    </table>
	                    <div class="span6 field-box actions">
                            <input type="submit" class="btn-glow primary pull-right" value="保存" />
                        </div>
	                        </fieldset>
		            </form>
	                </div>
	            </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
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
		
		$('#date').datetimepicker({
			language : 'zh-CN',
			minView: "month",
			autoclose:true
		});
		
		addFormValidate(); 
	});

function addFormValidate() {
	$("#deliveryOrder").validate({
		debug : true,
		rules : {
			payName : {
				required : true
			},
			payAddress : {
				required : true
			},
			payContact : {
				required : true
			},
			payPhone : {
				required : true
			},
			receiveName : {
				required : true
			},
			receiveAddress : {
				required : true
			},
			receiveContact : {
				required : true
			},
			receivePhone : {
				required : true
			},
			date : {
				required : true
			},
			mobile : {
				required : true
			},
			gdnum : {
				required : true
			},
			htnum : {
				required : true
			}
		},
		
		messages : {
			payName : {
				required : "必填"
			},
			payAddress : {
				required : "必填"
			},
			payContact : {
				required : "必填"
			},
			payPhone : {
				required : "必填"
			},
			receiveName : {
				required : "必填"
			},
			receiveAddress : {
				required : "必填"
			},
			receiveContact : {
				required : "必填"
			},
			receivePhone : {
				required : "必填"
			},
			date : {
				required : "必填"
			},
			mobile : {
				required : "必填"
			},
			gdnum : {
				required : "必填"
			},
			htnum : {
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