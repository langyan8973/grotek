<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="" />发货申请单</title>
    
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
                      		<td class="span2">客户</td>
                      		<td colspan="3">
                           	${apply.dealer.name }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">公司</td>
                      		<td colspan="3">
                      			${apply.dcompany }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">收货地址</td>
                      		<td colspan="3">
                      			${apply.address }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">联系人</td>
                      		<td colspan="3">
                      			${apply.contact }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">联系电话</td>
                      		<td colspan="3">
                      			${apply.phone }
                           </td>
                       </tr>
                       <tr>
                      		<td colspan="4" style="text-align:center;">
                      			<h5>产品</h5>
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">名称</td>
                      		<td class="span2">价格</td>
                      		<td class="span2">数量</td>
                      		<td class="span2">金额</td>
                       </tr>
                       <c:forEach items="${apply.boxitems}" var="boxitme">
                        <tr>
                       		<td class="span2">${boxitme.box.name }</td>
                       		<td class="span2">${boxitme.price }</td>
                       		<td class="span2">${boxitme.count }</td>
                       		<td class="span2">${boxitme.total }</td>
                        </tr>
                       </c:forEach>
                       <c:if test="${apply.pageitems[0]!=null }">
                       	<tr>
                            <td colspan="4" style="text-align:center;">
                      			<h5>附带宣传品</h5>
                           </td>
                        </tr>
                        <tr>
                       		<td colspan="2">名称</td>
                       		<td colspan="2">数量</td>
                        </tr>
                        <c:forEach items="${apply.pageitems}" var="pageitme">
	                        <tr>
                        		<td colspan="2">${pageitme.page.name }</td>
                       			<td colspan="2">${pageitme.count }</td>
	                        </tr>
                        </c:forEach>
                       </c:if>
                       <c:if test="${apply.packitems[0]!=null }">
                       	<tr>
                       		
                            <td colspan="4" style="text-align:center;">
                      				<h5>附带包材</h5>
                      			</td>
                           </td>
                        </tr>
                        <tr>
                       		<td colspan="2">名称</td>
                       		<td colspan="2">数量</td>
                        </tr>
                        <c:forEach items="${apply.packitems}" var="packitme">
	                        <tr>
                        		<td colspan="2">${packitme.pack.name }</td>
                       			<td colspan="2">${packitme.count }</td>
	                        </tr>
                        </c:forEach>
                       </c:if>
                       <tr>
                      		<td class="span2">合计</td>
                      		<td colspan="3">
                      			${apply.total }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">申请时间</td>
                      		<td colspan="3">
                      			<fmt:formatDate value='${apply.date }'
											type='date' pattern='yyyy-MM-dd HH:mm' />
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">申请人</td>
                      		<td colspan="3">
                      			${apply.employee.fullname }
                           </td>
                       </tr>
                       <tr>
                      		<td class="span2">状态</td>
                      		<td colspan="3">
                      		<c:if test="${apply.opstatus==0 }">
                      			<span class="label label-info">未发货</span>
                           	</c:if>
                           	<c:if test="${apply.opstatus==1 }">
                           		<span class="label label-success">已发货</span>
                           	</c:if>
                           </td>
                       </tr>         
                      </tbody>
                  </table>
            </div>
        </div>
    </div>
</body>
</html>