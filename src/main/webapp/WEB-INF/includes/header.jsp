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
   <%--  <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
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

    <!-- open sans font -->
    <!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' /> -->

    <!-- <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css' />  -->
    
    
	<jsp:include page="js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:url var="logoUrl" value="/assets/img/logo.png" />
            <a class="brand" href="index.html"><img src="${logoUrl }" /></a>
			<div id="nav-account" class="nav-collapse pull-right">
            <ul class="nav">                
                <sec:authorize access="authenticated" var="authenticated" />
				<c:choose>
					<c:when test="${authenticated}">
						<li class="dropdown">
							<a  href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown">你好:<sec:authentication property="principal.username" /><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:url var="passUrl" value="/manager/admin/pass" />
		                        <li><a href="${passUrl }">修改密码</a></li>
		                        <c:url var="logoutUrl" value="/manager/j_spring_security_logout" />
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
    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
        	<c:set var="homeCss" value=""></c:set>
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/home')}">
			    <c:set var="homeCss" value="active"></c:set>
			</c:if>     
            <li class="${homeCss }">  
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/home')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>  
				<c:url var="homeUrl" value="/home" />             
                <a href="${homeUrl }">
                    <i class="icon-home"></i>
                    <span>主页</span>
                </a>
            </li>  
            <c:if test="${role.id==4 || role.id==1 }">
            <c:set var="fhCss" value=""></c:set>
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/sendapplies')}">
			    <c:set var="fhCss" value="active"></c:set>
			</c:if>     
            <li class="${fhCss }">  
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/sendapplies')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>  
				<c:url var="fhUrl" value="/manager/sendapplies/index" />             
                <a href="${fhUrl }">
                    <i class="icon-list-alt"></i>
                    <span>发货申请</span>
                </a>
            </li>  
            </c:if>
            <c:if test="${role.id==3 || role.id==1 }">
            <c:set var="wkCss" value=""></c:set>
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/workload')}">
			    <c:set var="wkCss" value="active"></c:set>
			</c:if>     
            <li class="${wkCss }">  
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/workload')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>  
				<c:url var="wkUrl" value="/manager/workload/works" />             
                <a href="${wkUrl }">
                    <i class="icon-list-alt"></i>
                    <span>项目工作</span>
                </a>
            </li>  
            </c:if>
            <c:if test="${role.id==2}">
            <c:set var="fyCss" value=""></c:set>
            <c:set var="fyulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/aexsforms')
							||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/atravels')}">
			    <c:set var="fyCss" value="active"></c:set>
				<c:set var="fyulCss" value="active submenu" />
			</c:if>         
            <li class="${fyCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/aexsforms')
            				||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/atravels')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>                
                <a class="dropdown-toggle" href="#">
                    <i class="icon-list-alt"></i>
                    <span>费用审批</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${fyulCss }">
                	<c:set var="accCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/atravels/')}">
						<c:set var="accCss" value="active" />
					</c:if>
                    <c:url var="accUrl" value="/manager/atravels/checkpending" />
                    <li><a href="${accUrl }"class="${accCss }">出差报销单</a></li>
                	
                	<c:set var="abxCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/aexsforms/')}">
						<c:set var="abxCss" value="active" />
					</c:if>
                    <c:url var="abxUrl" value="/manager/aexsforms/checkpending" />
                    <li><a href="${abxUrl }"class="${abxCss }">市场开拓费用报销单</a></li>
                    
                </ul>
            </li> 
            </c:if>
            <c:if test="${role.id==3 || role.id==1 }">
            <c:set var="sbCss" value=""></c:set>
            <c:set var="sbulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/exsforms')
							||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/travels')}">
			    <c:set var="sbCss" value="active"></c:set>
				<c:set var="sbulCss" value="active submenu" />
			</c:if>         
            <li class="${sbCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/exsforms')
            				||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/travels')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>                
                <a class="dropdown-toggle" href="#">
                    <i class="icon-list-alt"></i>
                    <span>申报单</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${sbulCss }">
                	<c:set var="ccCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/travels/')}">
						<c:set var="ccCss" value="active" />
					</c:if>
                    <c:url var="ccUrl" value="/manager/travels/checkpending" />
                    <li><a href="${ccUrl }"class="${ccCss }">出差报销单</a></li>
                	
                	<c:set var="bxCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/exsforms/')}">
						<c:set var="bxCss" value="active" />
					</c:if>
                    <c:url var="bxUrl" value="/manager/exsforms/checkpending" />
                    <li><a href="${bxUrl }"class="${bxCss }">市场开拓费用报销单</a></li>
                    
                </ul>
            </li> 
            </c:if>
            <c:if test="${role.id==5 || role.id==1}">
            <c:set var="ygCss" value=""></c:set>
            <c:set var="ygulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/employees')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/rewards')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/departments')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/positions')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/promote')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/monthlyincomes')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/gasolines')}">
			    <c:set var="ygCss" value="active"></c:set>
				<c:set var="ygulCss" value="active submenu" />
			</c:if>         
            <li class="${ygCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/employees')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/rewards')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/departments')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/positions')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/promote')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/monthlyincomes')
			               || fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/gasolines')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>                
                <a class="dropdown-toggle" href="#">
                    <i class="icon-group"></i>
                    <span>员工管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${ygulCss }">
                	<c:set var="empCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/employees/')}">
						<c:set var="empCss" value="active" />
					</c:if>
                    <c:url var="employeeUrl" value="/manager/employees/index" />
                    <li><a href="${employeeUrl }"class="${empCss }">员工列表</a></li>
                    
                    <c:set var="promCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/promote/')}">
						<c:set var="promCss" value="active" />
					</c:if>
                    <c:url var="promUrl" value="/manager/promote/index" />
                    <li><a href="${promUrl }" class="${promCss }">晋升管理</a></li>
                    
                    <c:set var="rawCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/rewards/')}">
						<c:set var="rawCss" value="active" />
					</c:if>
                    <c:url var="rewardUrl" value="/manager/rewards/index" />
                    <li><a href="${rewardUrl }" class="${rawCss }">奖励情况</a></li>
                    
                    <c:set var="monCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/monthlyincomes/')}">
						<c:set var="monCss" value="active" />
					</c:if>
                    <c:url var="monUrl" value="/manager/monthlyincomes/index" />
                    <li><a href="${monUrl }" class="${monCss }">月收入情况</a></li>
                    
                    <c:set var="gasCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/gasolines/')}">
						<c:set var="gasCss" value="active" />
					</c:if>
                    <c:url var="gasUrl" value="/manager/gasolines/index" />
                    <li><a href="${gasUrl }" class="${gasCss }">员工用车情况</a></li>
                    
                    <c:set var="deptCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/departments/')}">
						<c:set var="deptCss" value="active" />
					</c:if>
                    <c:url var="deptUrl" value="/manager/departments/index" />
                    <li><a href="${deptUrl }"  class="${deptCss }">部门管理</a></li>
                    
                    <c:set var="posCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/positions/')}">
						<c:set var="posCss" value="active" />
					</c:if>
                    <c:url var="positionUrl" value="/manager/positions/index" />
                    <li><a href="${positionUrl }" class="${posCss }">职务管理</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${role.id==5 || role.id==1 || role.id==4}">
            <c:set var="deCss" value="" />
            <c:set var="deulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealers')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealersales')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerprices')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealervalues')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealercredits')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerreturns')}">
				<c:set var="deCss" value="active" />
				<c:set var="deulCss" value="active submenu" />
			</c:if>
            <li class="${deCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealers')
            	         ||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealersales')
            	         ||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerprices')
            	         ||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealervalues')
            	         ||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealercredits')
            	         ||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerreturns')}">
					<div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-user"></i>
                    <span>经销商管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${deulCss }">
                
                	<c:if test="${role.id==1 || role.id==5}">
                	<c:set var="dealerCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealers/')}">
						<c:set var="dealerCss" value="active" />
					</c:if>
                    <c:url var="dealersUrl" value="/manager/dealers/index" />
                    <li><a href="${dealersUrl }"class="${dealerCss }">经销商</a></li>
                    </c:if>
                    
                    <c:if test="${role.id==1 || role.id==4}">
                    <c:set var="priceCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerprices/')}">
						<c:set var="priceCss" value="active" />
					</c:if>
                    <c:url var="priceUrl" value="/manager/dealerprices/index" />
                    <li><a href="${priceUrl }"class="${priceCss }">经销商产品价格</a></li>
                    </c:if>
                    
                    <c:if test="${role.id==1 || role.id==5}">
                    <c:set var="valueCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealervalues/')}">
						<c:set var="valueCss" value="active" />
					</c:if>
                    <c:url var="valueUrl" value="/manager/dealervalues/index" />
                    <li><a href="${valueUrl }"class="${valueCss }">支持费用和表现评价</a></li>
                    
                    <c:set var="saleCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealersales/')}">
						<c:set var="saleCss" value="active" />
					</c:if>
                    <c:url var="saleUrl" value="/manager/dealersales/index" />
                    <li><a href="${saleUrl }"class="${saleCss }">销售进度</a></li>
                    
                    <c:set var="creditCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealercredits/')}">
						<c:set var="creditCss" value="active" />
					</c:if>
                    <c:url var="creditUrl" value="/manager/dealercredits/index" />
                    <li><a href="${creditUrl }"class="${creditCss }">政策支持表</a></li>
                    
                    <c:set var="returnCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dealerreturns/')}">
						<c:set var="returnCss" value="active" />
					</c:if>
                    <c:url var="returnUrl" value="/manager/dealerreturns/index" />
                    <li><a href="${returnUrl }"class="${returnCss }">进货让利政策表</a></li>
                    </c:if>
                </ul>
            </li>
            </c:if>
            <c:if test="${role.id==4 || role.id==1 || role.id==5}">
            <c:set var="cpCss" value="" />
            <c:set var="cpulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productraws')
							||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpacks')
							||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpages')
							||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productboxes')}">
				<c:set var="cpCss" value="active" />
				<c:set var="cpulCss" value="active submenu" />
			</c:if>
            <li class="${cpCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productraws')
            					||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpacks')
            					||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpages')
            					||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productboxes')}">
					<div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-th"></i>
                    <span>产品管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                
                <ul class="${cpulCss }">
                
                	<c:if test="${role.id==1 || role.id==5}">
                	<c:set var="prorawCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productraws/')}">
						<c:set var="prorawCss" value="active" />
					</c:if>
                    <c:url var="prorawUrl" value="/manager/productraws/index" />
                    <li><a href="${prorawUrl }"class="${prorawCss }">原料管理</a></li>
                    
                    <c:set var="propackCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpacks/')}">
						<c:set var="propackCss" value="active" />
					</c:if>
                    <c:url var="propackUrl" value="/manager/productpacks/index" />
                    <li><a href="${propackUrl }"class="${propackCss }">包材管理</a></li>
                    
                    <c:set var="propageCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productpages/')}">
						<c:set var="propageCss" value="active" />
					</c:if>
                    <c:url var="propageUrl" value="/manager/productpages/index" />
                    <li><a href="${propageUrl }"class="${propageCss }">宣传品管理</a></li>
                    </c:if>
                    
                    <c:set var="proboxCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productboxes/')}">
						<c:set var="proboxCss" value="active" />
					</c:if>
                    <c:url var="proboxUrl" value="/manager/productboxes/index" />
                    <li><a href="${proboxUrl }"class="${proboxCss }">产品管理</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${role.id==1 || role.id==5}">
            <c:set var="plCss" value="" />
            <c:set var="plulCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productlabors')
						||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/labortimes')}">
				<c:set var="plCss" value="active" />
				<c:set var="plulCss" value="active submenu" />
			</c:if>
            <li class="${plCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productlabors')
            				||fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/labortimes')}">
					<div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-calendar-empty"></i>
                    <span>用工管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${plulCss }">
                	<c:set var="laborCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/productlabors/')}">
						<c:set var="laborCss" value="active" />
					</c:if>
                    <c:url var="laborsUrl" value="/manager/productlabors/index" />
                    <li><a href="${laborsUrl }"class="${laborCss }">工人登记</a></li>
                    
                    <c:set var="timeCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/labortimes/')}">
						<c:set var="timeCss" value="active" />
					</c:if>
                    <c:url var="timeUrl" value="/manager/labortimes/index" />
                    <li><a href="${timeUrl }"class="${timeCss }">用工管理</a></li>
                </ul>
            </li>
            <c:set var="qyCss" value="" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/districts')}">
				<c:set var="qyCss" value="active" />
			</c:if>
            <li class="${qyCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/districts')}">
					<div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>
            	<c:url var="districtUrl" value="/manager/districts/index" />
                <a href="${districtUrl }">
                    <i class="icon-indent-left"></i>
                    <span>区域管理</span>
                </a>
            </li>
            
            <c:set var="dicCss" value="" />
            <c:set var="diculCss" value="submenu" />
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics')}">
				<c:set var="dicCss" value="active" />
				<c:set var="diculCss" value="active submenu" />
			</c:if>
            <li class="${dicCss }">
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics')}">
					<div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-wrench"></i>
                    <span>业务参数</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="${diculCss }">
                	<c:set var="jtCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics/jobtypes')}">
						<c:set var="jtCss" value="active" />
					</c:if>
                    <c:url var="dicsUrl" value="/manager/dics/jobtypes" />
                    <li><a href="${dicsUrl }"class="${jtCss }">工作类型</a></li>
                    
                    <c:set var="tetCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics/travelexstypes')}">
						<c:set var="tetCss" value="active" />
					</c:if>
                    <c:url var="tetsUrl" value="/manager/dics/travelexstypes" />
                    <li><a href="${tetsUrl }"class="${tetCss }">出差报销类型</a></li>
                    
                    <c:set var="exCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics/exstypes')}">
						<c:set var="exCss" value="active" />
					</c:if>
                    <c:url var="exsUrl" value="/manager/dics/exstypes" />
                    <li><a href="${exsUrl }"class="${exCss }">市场开拓费用报销类型</a></li>
                    
                    <c:set var="unitCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics/units')}">
						<c:set var="unitCss" value="active" />
					</c:if>
                    <c:url var="unitUrl" value="/manager/dics/units" />
                    <li><a href="${unitUrl }"class="${unitCss }">计量单位</a></li>
                    
                    <c:set var="typeCss" value="" />
					<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/dics/manuretypes')}">
						<c:set var="typeCss" value="active" />
					</c:if>
                    <c:url var="typeUrl" value="/manager/dics/manuretypes" />
                    <li><a href="${typeUrl }"class="${typeCss }">肥料类型</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${role.id==1 }">
            <c:set var="adminCss" value=""></c:set>
			<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/admin')}">
			    <c:set var="adminCss" value="active"></c:set>
			</c:if>     
            <li class="${adminCss }">  
            	<c:if test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/manager/admin')}">
				   <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
				</c:if>  
				<c:url var="adminUrl" value="/manager/admin/index" />             
                <a href="${adminUrl }">
                    <i class="icon-list-alt"></i>
                    <span>管理员账号</span>
                </a>
            </li> 
            </c:if>
        </ul>
    </div>
    <!-- end sidebar -->