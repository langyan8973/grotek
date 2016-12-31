<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="发货申请单" scope="request" />
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
                        <h3 class="name">发货申请单</h3>
                    </div>
                    <c:url var="downUrl" value="/manager/sendapplies/applydown?id=${apply.id }"></c:url>
                    <a class="btn-flat icon pull-right delete-user" title="导出" data-placement="top"
                     href="${downUrl }">
                        <i class="icon-download">导出</i>
                    </a>
                    <c:url var="ypUrl" value="/manager/dealervalues/new?did=${apply.dealer.id }"></c:url>
                    <a class="btn-flat icon pull-right delete-user" title="样品申请" data-placement="top"
                     href="${ypUrl }">
                        样品申请
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
	                <div class="span2">						
     
	                    
	                </div>
	                <div class="span8 table">
	                	<table class="table table-bordered">
	                        <tbody>
	                        	<tr>
	                        		<td class="span2">客户</td>
	                        		<td colspan="4">
		                            	${apply.dealer.name }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">公司</td>
	                        		<td colspan="4">
	                        			${apply.dcompany }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">收货地址</td>
	                        		<td colspan="4">
	                        			${apply.address }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">联系人</td>
	                        		<td colspan="4">
	                        			${apply.contact }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">联系电话</td>
	                        		<td colspan="4">
	                        			${apply.phone }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="5" style="text-align:center;">
	                        			<h5>产品</h5>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">名称</td>
	                        		<td class="span2">规格</td>
	                        		<td class="span1">价格</td>
                        			<td class="span1">数量(件)</td>
	                        		<td class="span2">金额</td>
		                        </tr>
		                        <c:set var="totalkg" value="0"></c:set>
		                        <c:forEach items="${apply.boxitems}" var="boxitme">
			                        <tr>
		                        		<td class="span2">${boxitme.box.name }</td>
		                        		<td class="span2">${boxitme.box.specification }</td>
		                        		<td class="span1">${boxitme.price }</td>
		                        		<td class="span1">${boxitme.count }</td>
		                        		<td class="span2">${boxitme.total }</td>
			                        </tr>
			                        <c:set var="totalkg" value="${totalkg+boxitme.kg*boxitme.count }"></c:set>
		                        </c:forEach>
		                        <c:if test="${apply.sampleitems[0]!=null }">
		                        	<tr>
			                            <td colspan="5" style="text-align:center;">
	                        			<h5>附带样品</h5>
		                            </td>
			                        </tr>
			                        <tr>
		                        		<td colspan="1">名称</td>
	                        			<td colspan="1">规格</td>
	                        			<td colspan="3">数量</td>
			                        </tr>
			                        <c:forEach items="${apply.sampleitems}" var="sampleitme">
				                        <tr>
			                        		<td colspan="1">${sampleitme.box.name }</td>
			                        		<td colspan="1">${sampleitme.box.specification }</td>
		                        			<td colspan="3">${sampleitme.count }</td>
				                        </tr>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${apply.pageitems[0]!=null }">
		                        	<tr>
			                            <td colspan="5" style="text-align:center;">
	                        			<h5>附带宣传品</h5>
		                            </td>
			                        </tr>
			                        <tr>
		                        		<td colspan="2">名称</td>
		                        		<td colspan="3">数量</td>
			                        </tr>
			                        <c:forEach items="${apply.pageitems}" var="pageitme">
				                        <tr>
			                        		<td colspan="2">${pageitme.page.name }</td>
		                        			<td colspan="3">${pageitme.count }</td>
				                        </tr>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${apply.packitems[0]!=null }">
		                        	<tr>
		                        		
			                            <td colspan="5" style="text-align:center;">
	                        				<h5>附带包材</h5>
	                        			</td>
		                            </td>
			                        </tr>
			                        <tr>
		                        		<td colspan="2">名称</td>
		                        		<td colspan="3">数量</td>
			                        </tr>
			                        <c:forEach items="${apply.packitems}" var="packitme">
				                        <tr>
			                        		<td colspan="2">${packitme.pack.name }</td>
		                        			<td colspan="3">${packitme.count }</td>
				                        </tr>
			                        </c:forEach>
		                        </c:if>
		                        <tr>
	                        		<td class="span2">合计</td>
	                        		<td colspan="4">
	                        			${apply.total }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">合计毛重(kg)</td>
	                        		<td colspan="4">
	                        			
	                        			<fmt:parseNumber value="${totalkg }" integerOnly="true" ></fmt:parseNumber>
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">申请时间</td>
	                        		<td colspan="4">
	                        			<fmt:formatDate value='${apply.date }'
														type='date' pattern='yyyy-MM-dd HH:mm' />
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">申请人</td>
	                        		<td colspan="4">
	                        			${apply.employee.fullname }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span2">状态</td>
	                        		<td colspan="4">
	                        			<c:if test="${apply.opstatus==0 }">
	                        				<c:url var="editUrl" value="/manager/sendapplies/edit?id=${apply.id }"></c:url>
		                            		<a id="editbtn" class="btn btn-mini btn-warning" type="button" href="${editUrl }">修改</a>
	                        				<button class="btn btn-mini btn-warning" type="button" onclick="checkOne(${apply.id})">出库检测</button>
	                        				<c:url var="newUrl" value="/manager/sendapplies/newpost?id=${apply.id }"></c:url>
		                            		<a id="postbtn" class="btn btn-mini btn-success" type="button" href="${newUrl }" style="display:none;">发货</a>
		                            	</c:if>
		                            	<c:if test="${apply.opstatus==1 }">
		                            		<span class="label label-success">已发货</span>
		                            		<c:url var="orderUrl" value="/manager/sendapplies/order?aid=${apply.id }"></c:url>
		                            		<a  href="${orderUrl }">查看送货单</a>
		                            	</c:if>
		                            </td>
		                        </tr>         
	                        </tbody>
	                    </table>
	                </div>
	            </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    
<script type="text/javascript">


function checkOne(id) {
	
		$.post('<c:url value="check" />', {
			id : id
		}).done(function(data) {
			if(data.status==1){
				$("#postbtn").show();
			}
			else{
				bootbox.alert(data.message);
			}
		}).fail(function() {
		});
}

function denyOne(id) {
	bootbox.confirm("确定要拒绝吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="denyexs" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}

function payOne(id) {
	bootbox.confirm("确定已经支付完成了吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="payexs" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
</script>

<jsp:include page="../includes/footer.jsp" />