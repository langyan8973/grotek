<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="产品入库信息" /></title>
    
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
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<div class="container-fluid">
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header boxbottomline">
            	<c:if test="${in != null}">
            		<div class="span8">
                     <h3 class="name">${in.box.name }入库信息</h3>
                 </div>
            	</c:if>    
            </div>
            <div class="row-fluid profile">
            	<div class="span3">
                 	
                 </div>
                <div class="span6">						
                   <table class="table table-bordered">
                   		<tr>
                   			<td colspan="2">产品</td>
                   			<td colspan="2">${in.box.name }</td>
                   		</tr>
                   		<tr>
                   			<td colspan="2">数量</td>
                   			<td colspan="2">${in.count }件</td>
                   		</tr>
                   		<tr>
                   			<td colspan="2">日期</td>
                   			<td colspan="2"><fmt:formatDate value='${in.date}' type='date' pattern='yyyy-MM-dd' /></td>
                   		</tr>
                   		<tr>
                   			<td colspan="4" style="text-align:center;">消耗原料</td>
                   		</tr>
                   		<c:if test="${rawouts == null}">
                   			<td colspan="4">无</td>
                   		</c:if>
                   		<c:forEach items="${rawouts}" var="rawout">
                   			<tr>
                   				<td colspan="2">${rawout.productRaw.name }</td>
                   				<td colspan="2">${rawout.count }</td>
                   			</tr>
                   		</c:forEach>
                   		<tr>
                   			<td colspan="4" style="text-align:center;">消耗包材</td>
                   		</tr>
                   		<c:if test="${packouts == null}">
                   			<td colspan="4">无</td>
                   		</c:if>
                   		<c:forEach items="${packouts}" var="packout">
                   			<tr>
                   				<td colspan="2">${packout.pack.name }</td>
                   				<td colspan="2">${packout.count }</td>
                   			</tr>
                   		</c:forEach>
                   		<tr>
                   			<td colspan="4" style="text-align:center;">消耗宣传品</td>
                   		</tr>
                   		<c:if test="${pageouts == null}">
                   			<td colspan="4">无</td>
                   		</c:if>
                   		<c:forEach items="${pageouts}" var="pageout">
                   			<tr>
                   				<td colspan="2">${pageout.page.name }</td>
                   				<td colspan="2">${pageout.count }</td>
                   			</tr>
                   		</c:forEach>
                   </table>
                </div>
                <div class="span3">
                 	
                 </div>
            </div>
        </div>
    </div>
</body>
</html>