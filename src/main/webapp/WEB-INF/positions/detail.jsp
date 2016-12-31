<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="职位详情" scope="request" />
<!-- this page specific styles -->
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
<jsp:include page="../includes/header.jsp" />

    
	<!-- main container -->
    <div class="content">
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header  boxbottomline">
                    <h3>${position.name}职位信息</h3>
                </div>

                <div class="row-fluid form-wrapper">
                    <div class="span3">
                 	
                 	</div>
                    <div class="span6">
                    	<table class="table table-bordered">
                    		<tr>
                    			<td colspan="2">岗位责任</td>
                   				<td colspan="2">${position.objective }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">年销售目标</td>
                   				<td colspan="2">${position.salestargets }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">提成比例</td>
                   				<td colspan="2">${position.amortization }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">薪酬待遇</td>
                   				<td colspan="2">${position.salary }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">出差标准</td>
                   				<td colspan="2">${position.travelAllow }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">手机补助</td>
                   				<td colspan="2">${position.mobileAllow }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">每小时费用</td>
                   				<td colspan="2">${position.costhour }</td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">更新日期</td>
                   				<td colspan="2"><fmt:formatDate value='${position.date}'
										type='date' pattern='yyyy-MM-dd HH:mm:ss' /></td>
                    		</tr>
                    		<tr>
                    			<td colspan="2">说明</td>
                   				<td colspan="2">${position.description }</td>
                    		</tr>
                    	</table>
                        
                    </div>
					<div class="span3">
                 	
                 	</div>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->

<jsp:include page="../includes/footer.jsp" />