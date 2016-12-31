<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="入库方案" /></title>
    
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
            <!-- header -->
            <div class="row-fluid header boxbottomline">
            	<c:if test="${scheme != null}">
            		<div class="span8">
                     <h3 class="name">${scheme.box.name }入库方案</h3>
                 </div>
            	</c:if>    
            	<c:url var="newUrl" value="/manager/productboxes/newscheme?pid=${pid }"/>               
                <a class="btn-flat icon pull-right  delete-user" href="${newUrl }">
                    <i class="icon-edit"></i>
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
			<c:if test="${scheme == null}">
				<div class="alert alert-danger" id="failMessage">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong><c:out value="没有任何入库方案，请点击编辑按钮建立入库方案" /></strong>
				</div>
				<script>
					$("#failMessage").delay(2000).slideUp("slow");
				</script>
			</c:if>
            <div class="row-fluid profile">
            
            	<div class="bs-docs-baseinfo">
                    <h4> 
                    	<ul class="baseinfolist">
                         <li>产品名称：${scheme.box.name } </li>
                     </ul>
                     <small>
                     	<ul class="infolist-inline">                          
                          <li>每件单位数量：${scheme.count } </li>
                         <li>更新日期：<fmt:formatDate value='${scheme.date}'
														type='date' pattern='yyyy-MM-dd' /> </li>
                      </ul>
                     </small>
                    </h4>         
                </div>
                <div class="span12 address">						
                    <div class="profile-box">
                        <h6>所需原料</h6>
                        <br />
                        <!-- recent orders table -->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="span4">
                                        名称
                                    </th>
                                    <th class="span4">
                                        数量
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- row -->
                       <c:set var="i" value="0" />
                       <c:forEach items="${scheme.schemeRaws}" var="rawscheme">
                       		<tr>
                        		<td>
	                                ${rawscheme.raw.name }
	                            </td>
	                            <td>
	                                ${rawscheme.count}(${rawscheme.raw.unit.name})
	                            </td>	
                        	</tr>
                       </c:forEach> 
                            </tbody>
                        </table>

                        
                    </div>
                    
                    <div class="profile-box">
                        <h6>所需包材</h6>
                        <br />
                        <!-- recent orders table -->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="span4">
                                        名称
                                    </th>
                                    <th class="span4">
                                        数量
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- row -->
                       <c:set var="i" value="0" />
                       <c:forEach items="${scheme.schemePacks}" var="packscheme">
                       		<tr>
                        		<td>
	                                ${packscheme.pack.name }
	                            </td>
	                            <td>
	                                ${packscheme.count}
	                            </td>		
                        	</tr>
                       </c:forEach> 
                            </tbody>
                        </table>                            
                    </div>
                    
                    <div class="profile-box">
                        <h6>所需宣传品</h6>
                        <br />
                        <!-- recent orders table -->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="span4">
                                        名称
                                    </th>
                                    <th class="span4">
                                        数量
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- row -->
                       <c:set var="i" value="0" />
                       <c:forEach items="${scheme.schemePages}" var="pagescheme">
                       	   <tr>
                        		<td>
	                                ${pagescheme.page.name }
	                            </td>
	                            <td>
	                                ${pagescheme.count}
	                            </td>		
                        	</tr>
                       </c:forEach> 
                            </tbody>
                        </table>                            
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>