<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="原料库存" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productraws/index"></c:url>
			    <li><a href="${listUrl }">原料信息</a></li>
			    <c:url var="priceUrl" value="/manager/productraws/price"></c:url>
			    <li><a href="${priceUrl }">原料价格</a></li>
			    <c:url var="storeUrl" value="/manager/productraws/store"></c:url>
			    <li class="active"><a href="${storeUrl }">原料库存</a></li>
			    <c:url var="inUrl" value="/manager/productraws/in"></c:url>
			    <li><a href="${inUrl }">原料入库</a></li>
			    <c:url var="outUrl" value="/manager/productraws/out"></c:url>
			    <li><a href="${outUrl }">原料出库</a></li>
			</ul>  
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>原料库存</h3>
                      <div class="span10 pull-right">
                        <input id="searchKc" type="text" class="span5 search" placeholder="输入原料名称"  onkeydown="onKeyDown(event)"/>
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
                                    原料名称
                                </th>
                                <th class="span3 sortable">
                                    库存数量
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${stores}" var="store">
                        	<tr>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productraws/profile?id=${store.productRaw.id }"></c:url>
	                                <a href="${proUrl }" class="name" id="productRaw_name">${store.productRaw.name }</a>
	                            </td>
	                            <td>
	                                ${store.count }(${store.productRaw.unit.name })
	                            </td>
                        	</tr>
                        </c:forEach>                        
                        </tbody>
                    </table>
                    <div id="kkpager" class="span10"></div>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    
<script type="text/javascript">
$(document).ready(function() {
	$("#searchKc").val(getUrlParam("context"));
    var size = 10;
	var sumcount = parseInt(${sumcount}); 
	if(sumcount ==0){return false}
	
    var numPages = Math.ceil(sumcount/size);
    var currentPage = getUrlParam("page");
    currentPage = currentPage ? parseInt(currentPage) + 1 : 1;
  //生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : currentPage,
		//总页码
		total : numPages,
		//总数据条数
		totalRecords : sumcount,
		//链接前部
		hrefFormer : 'store',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			var text = $("#searchKc").val();
			return this.hrefFormer + "?page=" + (n-1) + "&size=" + size;
	    $("#productRaw_name").html(text);
		}
		
	});
});
//LWX
function search(){
	var text = $("#searchKc").val();
		window.location.href = '<c:url value="store" />?context='+encodeURIComponent(text);
}
</script>

<jsp:include page="../includes/footer.jsp" />