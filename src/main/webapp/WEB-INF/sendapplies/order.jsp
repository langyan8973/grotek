<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="送货单" scope="request" />
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
                        <h3 class="name">送货单</h3>
                    </div>
                    <c:url var="downUrl" value="/manager/sendapplies/orderdown?id=${order.id }"></c:url>
                    <a class="btn-flat icon pull-right delete-user" title="导出" data-placement="top"
                     href="${downUrl }">
                        <i class="icon-download">导出</i>
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
				<c:if test="${order == null}">
					<div class="alert alert-danger" id="failMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="数据不存在或已经删除" /></strong>
					</div>
					<script>
						$("#failMessage").delay(4000).slideUp("slow");
					</script>
				</c:if>
                <div class="row-fluid profile">           
	                <div class="span2">						
     
	                    
	                </div>
	                <div class="span8 table">
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
	                        			${order.payName }
		                            </td>
		                            <td class="span1">名称</td>
	                        		<td colspan="3">
	                        			${order.receiveName }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">地址</td>
	                        		<td colspan="2">
	                        			${order.payAddress }
		                            </td>
		                            <td class="span1">地址</td>
	                        		<td colspan="3">
	                        			${order.receiveAddress }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">联系人</td>
	                        		<td colspan="2">
	                        			${order.payContact }
		                            </td>
		                            <td class="span1">联系人</td>
	                        		<td colspan="3">
	                        			${order.receiveContact }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">电话</td>
	                        		<td colspan="2">
	                        			${order.payPhone }
		                            </td>
		                            <td class="span1">电话</td>
	                        		<td colspan="3">
	                        			${order.receivePhone }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">发单人</td>
	                        		<td colspan="2">
	                        			${employee.fullname }
		                            </td>
		                            <td class="span1">客户</td>
	                        		<td colspan="3">
	                        			${dealer.name }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">发货日期</td>
	                        		<td colspan="2">
	                        			<fmt:formatDate value='${order.date }'
													type='date' pattern='yyyy-MM-dd HH:mm' />
		                            </td>
		                            <td class="span1">账户类型</td>
	                        		<td colspan="3">
	                        			${dealer.creditrating.name }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1">手机</td>
	                        		<td colspan="2">
	                        			${order.mobile }
		                            </td>
		                            <td class="span1">购单号码</td>
	                        		<td colspan="3">
	                        			${order.gdnum }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span1"></td>
	                        		<td colspan="2">
	                        			
		                            </td>
		                            <td class="span1">合同编号</td>
	                        		<td colspan="3">
	                        			${order.htnum }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="8" style="text-align:center;">
	                        			<h5>产品</h5>
		                            </td>
		                        </tr>
		                        <tr>
		                        	<td class="span1">编号</td>
	                        		<td class="span2">名称</td>
	                        		<td class="span2">规格</td>
	                        		<td class="span1">每箱数量</td>
	                        		<td class="span1">箱数</td>
	                        		<td class="span1">净重(kg)</td>	                        		
	                        		<td class="span2">总重</td>
		                        </tr>
		                        <c:set var="totalkg" value="0"></c:set>
		                        <c:set var="count" value="0"></c:set>
		                        <c:set var="sum" value="0"></c:set>
		                        <c:forEach items="${order.boxitems}" var="boxitme">
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
			                    <c:if test="${order.sampleitems[0]!=null }">
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
			                        <c:set var="i" value="1"></c:set>
			                        <c:forEach items="${order.sampleitems}" var="sampleitem">
				                        <tr>
				                        	<td colspan="1">${i }</td>
			                        		<td colspan="1">${sampleitem.box.name }</td>
			                        		<td colspan="1">${sampleitem.box.specification }</td>
		                        			<td colspan="4">${sampleitem.count }</td>
				                        </tr>
				                        <c:set var="i" value="${i+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${order.pageitems[0]!=null }">
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
			                        <c:forEach items="${order.pageitems}" var="pageitme">
				                        <tr>
				                        	<td colspan="1">${i }</td>
			                        		<td colspan="1">${pageitme.page.name }</td>
		                        			<td colspan="5">${pageitme.count }</td>
				                        </tr>
				                        <c:set var="i" value="${i+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
		                        <c:if test="${order.packitems[0]!=null }">
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
			                        <c:forEach items="${order.packitems}" var="packitme">
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

	});
</script>

<jsp:include page="../includes/footer.jsp" />