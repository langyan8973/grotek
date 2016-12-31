<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="支持费用和表现评价" /></title>
    
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
    <c:url var="kkpagercssUrl" value="/assets/css/kkpager_blue.css" />
    <link rel="stylesheet" type="text/css" href="${kkpagercssUrl }" />
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<div class="container-fluid"> 
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header">
            	<h3 class="name">${dealer.name }支持费用和表现评价</h3> 
            </div>
            <div class="row-fluid table">
                <table class="table table-bordered">
                     <thead>
                         <tr>
                             <th class="span1 sortable">
                           让利金额
                       </th>
                       <th class="span1 sortable">
                           返点金额
                       </th>
                       <th class="span1 sortable">
                           奖励金额
                       </th>
                       <th class="span3 sortable">
                           表现评价
                       </th>
                       <th class="span2 sortable">
                           时间
                       </th>
                         </tr>
                     </thead>
                     <tbody>
                         <!-- row -->
                <c:set var="i" value="0" />
                <c:forEach items="${dealervalues}" var="dealervalue">
                	<tr>
                		<td>
                     	${dealervalue.promote }
                     </td>
                     <td>
                         ${dealervalue.rebate }
                     </td>
                     <td>
                     	${dealervalue.reward }
                     </td>

                     <td>
                         ${dealervalue.description }
                     </td>
                     <td>
                         <fmt:formatDate value='${dealervalue.date }'
					type='date' pattern='yyyy-MM-dd' />
                     </td>	         
                	</tr>
                </c:forEach> 
                     </tbody>
                 </table>
            </div>
        </div>
    </div>
<script type="text/javascript">
$(document).ready(function() {
	 
    var size = 20;
	var sumcount = parseInt(${sumcount}); 
	if(sumcount ==0){return false}
	
    var numPages = Math.ceil(sumcount/size);
    var currentPage = getUrlParam("page");
    currentPage = currentPage ? parseInt(currentPage) + 1 : 1;
    var $pager = $('<div id="kkpager" class="span10"></div>'); 
    $pager.insertAfter($('table'));
    //生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : currentPage,
		//总页码
		total : numPages,
		//总数据条数
		totalRecords : sumcount,
		//链接前部
		hrefFormer : 'values',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			return this.hrefFormer + "?did=${dealer.id}&page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function search(){
	var text = $("#searchtext").val();
		window.location.href = '<c:url value="values" />?did=${dealer.id}';
}

</script>
</body>
</html>