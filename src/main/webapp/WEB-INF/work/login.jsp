<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html class="login-bg">
<head>
	<title><c:out value="${pageTitle}" /></title>
    
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
    <c:url var="grotekcssUrl" value="/assets/css/grotek.css" />
    <link rel="stylesheet" type="text/css" href="${grotekcssUrl }" />   
    <c:url var="signupUrl" value="/assets/css/compiled/signup.css" />
    <link rel="stylesheet" href="${signupUrl }" type="text/css" media="screen" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
    <div class="header">
    	<c:url var="logoUrl" value="/assets/img/logo.png" />
        <a class="brand" href="#"><img src="${logoUrl }" /></a>
    </div>
    <div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6>员工登录</h6>
                <c:url value="/work/j_spring_security_check" var="nloginUrl" />
                <form id="login-form" action="${nloginUrl}" class="form-signin" method="post">
                <c:if test="${param.error != null}">
					<div class="alert alert-error">
						登录失败：
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
							<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</c:if>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<!-- <div class="alert alert-success">您已退出系统</div> -->
				</c:if>
                <input class="span12" type="text" id="username" name="username" placeholder="你的手机号码" required autofocus/>
                <input class="span12" type="password" id="password" name="password" placeholder="您的密码"  required/>
                <div class="checkbox">
				    <label class="checkbox" style="width:100px;"> <input type="checkbox" checked="checked" name="_spring_security_remember_me">下次自动登录 </label>
			    </div>
                <button class="btn-glow primary login" type="submit" style="margin-top:10px;">登录</button>              
            </div>
        </div>
    </div>

</body>
</html>