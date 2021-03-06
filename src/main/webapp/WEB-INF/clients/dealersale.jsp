<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="" />销售进度信息</title>
    
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
                     		<td class="span2">经销商</td>
                     		<td class="span2">${sale.dealer.name }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">产品</td>
                     		<td class="span2">${sale.box.name }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">每件价格</td>
                     		<td class="span2">${sale.price }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">进货量</td>
                     		<td class="span2">${sale.count }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">进货额</td>
                     		<td class="span2">${sale.total }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">订购时间</td>
                     		<td class="span2"><fmt:formatDate value='${sale.ordertime }'
										type='date' pattern='yyyy-MM-dd HH:mm' /></td>
                     	</tr>
                     	<tr>
                     		<td class="span2">发货时间</td>
                     		<td class="span2"><fmt:formatDate value='${sale.posttime }'
										type='date' pattern='yyyy-MM-dd HH:mm' /></td>
                     	</tr>
                     	<tr>
                     		<td class="span2">付款状况</td>
                     		<td class="span2">${sale.paystatus }</td>
                     	</tr>
                     	<tr>
                     		<td class="span2">付款额</td>
                     		<td class="span2">${sale.amountpaid }</td>
                     	</tr>
                     </tbody>
                 </table>
            </div>
        </div>
    </div>
</body>
</html>