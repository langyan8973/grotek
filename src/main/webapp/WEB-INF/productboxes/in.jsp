<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="产品入库" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productboxes/index"></c:url>
			    <li><a href="${listUrl }">产品信息</a></li>
			    <c:url var="priceUrl" value="/manager/productboxes/price"></c:url>
			    <li><a href="${priceUrl }">产品价格</a></li>
			    <c:url var="storeUrl" value="/manager/productboxes/store"></c:url>
			    <li><a href="${storeUrl }">产品库存</a></li>
			    <c:url var="inUrl" value="/manager/productboxes/in"></c:url>
			    <li class="active"><a href="${inUrl }">产品入库</a></li>
			    <c:url var="outUrl" value="/manager/productboxes/out"></c:url>
			    <li><a href="${outUrl }">产品出库</a></li>
			    <c:url var="laborUrl" value="/manager/productboxes/laborcost"></c:url>
			    <li><a href="${laborUrl }">生产用工</a></li>
			</ul>   
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>产品入库</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入产品名称"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
                    </div>
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
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                            	<th class="span3 sortable">
                                    入库时间
                                </th>
                                <th class="span3 sortable">
                                	产品名称
                                </th>
                                <th class="span2 sortable">
                                	规格
                                </th>
                                <th class="span2 sortable">
                                    入库数量(件)
                                </th>
                                <th class="span2 sortable">
                                    详情
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${ins}" var="in">
                        	<tr>
                        		<td>
	                                <fmt:formatDate value='${in.date }'
													type='date' pattern='yyyy-MM-dd HH:mm' />
	                            </td>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productboxes/profile?id=${in.box.id }"></c:url>
	                                <a href="${proUrl }" class="name">${in.box.name }</a>
	                            </td>
	                            <td>
	                                ${in.box.specification }
	                            </td>
	                            <td>
	                                ${in.count }
	                            </td>
	                            <td>
                        			<c:url var ="boxinUrl" value = "/manager/productboxes/boxin?id=${in.id }"></c:url>
	                                <a href="${boxinUrl }" class="name" target="_Blank">查看</a>
	                            </td>
                        	</tr>
                        </c:forEach>                        
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    
<script type="text/javascript">
$(document).ready(function() {
	$("#searchtext").val(getUrlParam("context"));
	 
    var size = 10;
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
		hrefFormer : 'in',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			var text = $("#searchtext").val();
			return this.hrefFormer + "?context="+ encodeURIComponent(text) + "&page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function search(){
	var text = $("#searchtext").val();
		window.location.href = '<c:url value="in" />?context='+encodeURIComponent(text);
}

</script>

<jsp:include page="../includes/footer.jsp" />