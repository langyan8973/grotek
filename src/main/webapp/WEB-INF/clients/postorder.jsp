<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="" />送货单</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <c:url var="cssUrl" value="/assets/css/bootstrap/bootstrap.css" />
    <link href="${cssUrl }" rel="stylesheet" />
    <c:url var="cssUrl2" value="/assets/css/bootstrap/bootstrap-responsive.css" />
    <link href="${cssUrl2 }" rel="stylesheet" />
    <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
    <link href="${cssUrl3 }" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <c:url var="cssUrl4" value="/assets/css/lib/jquery-ui-1.10.2.custom.css" />
    <link href="${cssUrl4 }" rel="stylesheet" type="text/css" />
    <c:url var="cssUrl5" value="/assets/css/lib/font-awesome.css" />
    <link href="${cssUrl5 }" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <c:url var="cssUrl6" value="/assets/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl6 }" />
    <c:url var="cssUrl7" value="/assets/css/elements.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl7 }" />
    <c:url var="cssUrl8" value="/assets/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl8 }" /> 

    <c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
	<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
	
	<c:url var="grotekcssUrl" value="/assets/css/grotek.css" />
    <link rel="stylesheet" type="text/css" href="${grotekcssUrl }" />
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<div class="container-fluid">
        <div id="pad-wrapper" class="user-profile">
            <div class="row-fluid table">
                <table class="table table-bordered">
                      <tbody>
                      	<tr>
                      		<td colspan="3">
                      			付款单位
                      		</td>
                      		<td colspan="3">
                           	接收单位
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">名称</td>
                      		<td colspan="2">
                      			${order.payName }
                           </td>
                           <td class="span1">名称</td>
                      		<td colspan="2">
                      			${order.receiveName }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">地址</td>
                      		<td colspan="2">
                      			${order.payAddress }
                           </td>
                           <td class="span1">地址</td>
                      		<td colspan="2">
                      			${order.receiveAddress }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">联系人</td>
                      		<td colspan="2">
                      			${order.payContact }
                           </td>
                           <td class="span1">联系人</td>
                      		<td colspan="2">
                      			${order.receiveContact }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">电话</td>
                      		<td colspan="2">
                      			${order.payPhone }
                           </td>
                           <td class="span1">电话</td>
                      		<td colspan="2">
                      			${order.receivePhone }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">发单人</td>
                      		<td colspan="2">
                      			${employee.fullname }
                           </td>
                           <td class="span1">客户</td>
                      		<td colspan="2">
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
                      		<td colspan="2">
                      			${dealer.creditrating.name }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">手机</td>
                      		<td colspan="2">
                      			${order.mobile }
                           </td>
                           <td class="span1">购单号码</td>
                      		<td colspan="2">
                      			${order.gdnum }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span1">座机</td>
                      		<td colspan="2">
                      			${order.phone }
                           </td>
                           <td class="span1">合同编号</td>
                      		<td colspan="2">
                      			${order.htnum }
                           </td>
                       </tr>
                       <tr>
                      		<td colspan="6" style="text-align:center;">
                      			<h5>产品</h5>
                           </td>
                       </tr>
                       <tr>
                       	<td class="span2">序号</td>
                      		<td class="span2">名称</td>
                      		<td class="span2">编码</td>
                      		<td class="span2">规格</td>
                      		<td class="span2">数量</td>
                      		<td class="span2">总量</td>
                       </tr>
                       <c:set var="i" value="1"></c:set>
                       <c:forEach items="${order.boxitems}" var="boxitme">
                        <tr>
                        	<td class="span2">${i }</td>
                       		<td class="span2">${boxitme.box.name }</td>
                       		<td class="span2">${boxitme.box.code }</td>
                       		<td class="span2">${boxitme.box.specification }</td>
                       		<td class="span2">${boxitme.count }</td>
                       		<td class="span2"></td>
                        </tr>
                        <c:set var="i" value="${i+1 }"></c:set>
                       </c:forEach>
                       <c:if test="${order.pageitems[0]!=null }">
                       	<tr>
                            <td colspan="6" style="text-align:center;">
                      			<h5>附带宣传品</h5>
                           </td>
                        </tr>
                        <tr>
                        	<td colspan="2">序号</td>
                       		<td colspan="2">名称</td>
                       		<td colspan="2">数量</td>
                        </tr>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${order.pageitems}" var="pageitme">
	                        <tr>
	                        	<td colspan="2">${i }</td>
                        		<td colspan="2">${pageitme.page.name }</td>
                       			<td colspan="2">${pageitme.count }</td>
	                        </tr>
	                        <c:set var="i" value="${i+1 }"></c:set>
                        </c:forEach>
                       </c:if>
                       <c:if test="${order.packitems[0]!=null }">
                       	<tr>
                       		
                            <td colspan="6" style="text-align:center;">
                      				<h5>附带包材</h5>
                      			</td>
                           </td>
                        </tr>
                        <tr>
                        	<td colspan="2">序号</td>
                       		<td colspan="2">名称</td>
                       		<td colspan="2">数量</td>
                        </tr>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${order.packitems}" var="packitme">
	                        <tr>
	                        	<td colspan="2">${i }</td>
                        		<td colspan="2">${packitme.pack.name }</td>
                       			<td colspan="2">${packitme.count }</td>
	                        </tr>
	                        <c:set var="i" value="${i+1 }"></c:set>
                        </c:forEach>
                       </c:if>
                      </tbody>
                  </table>
            </div>
        </div>
    </div>
</body>
</html>