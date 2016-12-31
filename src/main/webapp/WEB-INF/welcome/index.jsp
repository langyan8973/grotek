<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="登录成功" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
        	
            <div class="container">
		      <div class="page-header" style="margin-top:100px;">
		        <h1>欢迎进入Grotek系统后台管理系统</h1>
		      </div>
		      <p class="lead">
		      	Grotek系统管理员共分为5个角色，分别为：超级管理员、费用审批管理员、申报项检查管理员、发货管理员、数据管理员。
		      	<br>
		      	超级管理员：具有除费用审批外的其他所有权限及所有管理员的管理权限
		      	<br>
		      	费用审批管理员：具有出差报销及市场开拓费用报销的最终审批权限
		      	<br>
		      	申报项检查管理员：具有出差报销及市场开拓费用报销的最初审批权限及个人项目工作周报的审批权限
		      	<br>
		      	发货管理员：具有发货申请的审核及最后发货权限及商品价格编辑权限
		      	<br>
		      	数据管理员：具有数据的录入编辑权限
		      </p>
		      
		    </div>
              
        </div>
    </div>

<jsp:include page="../includes/footer.jsp" />