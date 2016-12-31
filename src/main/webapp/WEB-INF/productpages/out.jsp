<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="宣传品出库" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productpages/index"></c:url>
			    <li><a href="${listUrl }">宣传品信息</a></li>
			    <c:url var="storeUrl" value="/manager/productpages/store"></c:url>
			    <li><a href="${storeUrl }">宣传品库存</a></li>
			    <c:url var="inUrl" value="/manager/productpages/in"></c:url>
			    <li><a href="${inUrl }">宣传品入库</a></li>
			    <c:url var="outUrl" value="/manager/productpages/out"></c:url>
			    <li  class="active"><a href="${outUrl }">宣传品出库</a></li>
			    <c:url var="typeUrl" value="/manager/productpages/type"></c:url>
			    <li><a href="${typeUrl }">宣传品类型</a></li>
			</ul>      
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>宣传品出库</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入宣传品名称"  onkeydown="onKeyDown(event)"/>
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
                                    出库时间
                                </th>
                                <th class="span3 sortable">
                                	宣传品名称
                                </th>
                                <th class="span3 sortable">
                                    出库数量(个)
                                </th>
                                <th class="span3 sortable">
                                    详情
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${outs}" var="out">
                        	<tr>
                       			<td>
	                                <fmt:formatDate value='${out.date }'
													type='date' pattern='yyyy-MM-dd HH:mm' />
	                            </td>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productpages/profile?id=${out.page.id }"></c:url>
	                                <a href="${proUrl }" class="name">${out.page.name }</a>
	                            </td>
	                            <td>
	                                ${out.count }
	                            </td>
	                            <td>
	                                <c:if test="${out.dealer!=null }">
	                                	${out.dealer.name }
	                                </c:if>
	                            </td>
	                            <td>
	                                <c:if test="${out.boxinid!=null }">
	                                	<c:url var ="boxinUrl" value = "/manager/productboxes/boxin?id=${out.boxinid }"></c:url>
	                                	<a href="${boxinUrl }" class="name" target="_Blank">查看产品入库信息</a>
	                                </c:if>
	                                <c:if test="${out.aid!=null }">
                                		<c:url var ="aUrl" value = "/manager/sendapplies/profile?id=${out.aid}"></c:url>
                                		<a href="${aUrl }" class="name">发货申请</a>
                                	</c:if>
                                	<c:if test="${out.psid!=null }">
                                		<c:url var ="psUrl" value = "/manager/sendapplies/order?id=${out.psid}"></c:url>
                                		<a href="${psUrl }" class="name">送货单</a>
                                	</c:if>
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
		hrefFormer : 'out',
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
		window.location.href = '<c:url value="out" />?context='+encodeURIComponent(text);
}

</script>

<jsp:include page="../includes/footer.jsp" />