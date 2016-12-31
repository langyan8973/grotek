<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="出差报销单" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/exsforms/travels"></c:url>
			    <li class="active"><a href="${listUrl }">出差报销单</a></li>
			    <c:url var="priceUrl" value="/manager/exsforms/exses"></c:url>
			    <li><a href="${priceUrl }">费用报销单</a></li>
			</ul>
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span8">
                        <h3 class="name">出差报销单</h3>
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
	                	<table class="table table-bordered">
	                        <tbody>
	                        	<tr>
	                        		<td class="span4">客户</td>
	                        		<td class="span4">
		                            	${rbsm.dealer.name }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">工作</td>
	                        		<td class="span3">
	                        			${rbsm.type.name }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">出发时间</td>
	                        		<td class="span3">
	                        			<fmt:formatDate value='${rbsm.stime }'
														type='date' pattern='yyyy-MM-dd HH:mm' />
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">结束时间</td>
	                        		<td class="span3">
	                        			<fmt:formatDate value='${rbsm.etime }'
														type='date' pattern='yyyy-MM-dd HH:mm' />
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">出发地</td>
	                        		<td class="span3">
	                        			${rbsm.splace }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">目的地</td>
		                            <td class="span3">
		                                ${rbsm.eplace }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">长途车船费</td>
		                            <td class="span3">
		                                ${rbsm.longhaulexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">短途车船费</td>
		                            <td class="span3">
		                            	${rbsm.shorthaulexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">出差补助</td>
		                            <td class="span3">
		                            	${rbsm.travelallow }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">住宿费</td>
		                            <td class="span3">
		                            	${rbsm.hotelexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">餐费</td>
		                            <td class="span3">
		                            	${rbsm.mealexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">汽油费</td>
		                            <td class="span3">
		                            	${rbsm.gasexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">过桥费</td>
		                            <td class="span3">
		                            	${rbsm.etcexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">交通处罚款</td>
		                            <td class="span3">
		                            	${rbsm.departureexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">其他费用</td>
		                            <td class="span3">
		                            	${rbsm.otherexp }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">合计</td>
		                            <td class="span3">
		                            	${rbsm.sum }
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">申报日期</td>
		                            <td class="span3">
		                            	<fmt:formatDate value='${rbsm.date }'
														type='date' pattern='yyyy-MM-dd' />
		                            </td>
		                        </tr>
		                        <tr>
	                        		<td class="span3">审核</td>
		                            <td class="span3">
		                            	<c:if test="${rbsm.checked==0 }">
		                            		<button class="btn btn-mini btn-success" type="button" onclick="passOne(${rbsm.id})">批准</button>
			                            	<button class="btn btn-mini btn-danger" type="button" onclick="denyOne(${rbsm.id})">拒绝</button>
		                            	</c:if>
		                            	<c:if test="${rbsm.checked==1 }">
		                            		<span class="label label-success">通过</span>
		                            	</c:if>
		                            	<c:if test="${rbsm.checked==-1 }">
		                            		<span class="label label-important">未通过</span>
		                            	</c:if>
		                            </td>
		                         </tr>
		                        <tr>
	                        		<td class="span3">报销状态</td>
		                            <td class="span3">
		                            	<c:if test="${rbsm.checked==0 ||rbsm.checked==1 }">
		                            		<c:if test="${rbsm.paystatus==0 }">
			                            		<button class="btn btn-mini btn-info" type="button" onclick="payOne(${rbsm.id})">完成</button>
			                            	</c:if>
			                            	<c:if test="${rbsm.paystatus==1 }">
			                            		<span class="label label-success">完成</span>
			                            	</c:if>
		                            	</c:if>
		                            	
		                            </td>
		                         </tr>
		                        <tr>
	                        		<td class="span3">申报人</td>
		                            <td class="span3">
		                            	${rbsm.employee.fullname }
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


function passOne(id) {
	bootbox.confirm("确定核查无误了吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="passtravel" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}

function denyOne(id) {
	bootbox.confirm("确定要拒绝吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="denytravel" />', {
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

		$.post('<c:url value="paytravel" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
</script>

<jsp:include page="../includes/footer.jsp" />