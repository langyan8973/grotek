<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title><c:out value="${pageTitle}" /></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <c:url var="cssUrl" value="/assets/css/bootstrap/bootstrap.css" />
    <link href="${cssUrl }" rel="stylesheet" />
    <c:url var="cssUrl2" value="/assets/css/bootstrap/bootstrap-responsive.css" />
    <link href="${cssUrl2 }" rel="stylesheet" />
    <%-- <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
    <link href="${cssUrl3 }" type="text/css" rel="stylesheet" /> --%>

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
    <c:url var="grotekcssUrl" value="/assets/css/grotek.css" />
    <link rel="stylesheet" type="text/css" href="${grotekcssUrl }" />  
    
    <c:url var="kkpagercssUrl" value="/assets/css/kkpager_blue.css" />
    <link rel="stylesheet" type="text/css" href="${kkpagercssUrl }" /> 
    
    <jsp:include page="js.jsp" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <c:url var="logoUrl" value="/assets/img/logo.png" />
        	<a class="brand" href="#"><img src="${logoUrl }" /></a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                	<c:set var="timeCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/timesheet')}">
					    <c:set var="timeCss" value="active"></c:set>
					</c:if>  
					<c:url var="timeUrl" value="/work/timesheet/index"></c:url>
                    <li class="${timeCss }"><a href="${timeUrl }">项目工作</a></li>
                    
                    <c:set var="exCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/expenses')}">
					    <c:set var="exCss" value="active"></c:set>
					</c:if>  
					<c:url var="exUrl" value="/work/expenses/travels"></c:url>
                    <li class="${exCss }"><a href="${exUrl }">报销</a></li>
                    
                    <c:set var="asCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/applyforsend')}">
					    <c:set var="asCss" value="active"></c:set>
					</c:if>  
					<c:url var="asUrl" value="/work/applyforsend/index"></c:url>
                    <li class="${asCss }"><a href="${asUrl }">发货申请</a></li>
                    
                    <c:set var="auditCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/audititems')}">
					    <c:set var="auditCss" value="active"></c:set>
					</c:if>  
					<c:url var="auditUrl" value="/work/audititems/works"></c:url>
                    <li class="${auditCss } notification-dropdown"><a href="${auditUrl }">
                    	审核
                    	<c:if test="${notifycount>0 }">
                    		<span class="count" style="top:8px;left:70%; padding:2px 6px 0px 6px;">${notifycount }</span>
                    	</c:if>
                    </a></li>
                    
                    <c:set var="clientCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/clients')}">
					    <c:set var="clientCss" value="active"></c:set>
					</c:if>  
					<c:url var="clientUrl" value="/work/clients/index"></c:url>
                    <li class="${clientCss }"><a href="${clientUrl }">客户</a></li>
                    
                    <c:set var="memberCss" value=""></c:set>
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/members')}">
					    <c:set var="memberCss" value="active"></c:set>
					</c:if>  
					<c:url var="memberUrl" value="/work/members/index"></c:url>
                    <li class="${memberCss }"><a href="${memberUrl }">员工</a></li>
                </ul>
            </div>
            <div id="nav-account" class="nav-collapse pull-right">
				<ul class="nav">
					<sec:authorize access="authenticated" var="authenticated" />
					<c:choose>
						<c:when test="${authenticated}">
							<li  class="dropdown">
								<a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">你好:<sec:authentication property="principal.username" />
									<b class="caret"></b>
								</a>								
								<ul class="dropdown-menu">
									<c:url var="passUrl" value="/work/account/pass" />
			                        <li><a href="${passUrl }">修改密码</a></li>
			                        <c:url var="logoutUrl" value="/work/j_spring_security_logout" />
									<li><a id="navLogoutLink" href="${logoutUrl}">退出系统</a></li>
			                    </ul>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</div>
        </div>
    </div>
    <!-- end navbar -->