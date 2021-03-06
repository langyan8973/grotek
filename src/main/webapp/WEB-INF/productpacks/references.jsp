<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="包材" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productpacks/index"></c:url>
			    <li><a href="${listUrl }">包材信息</a></li>
			    <c:url var="storeUrl" value="/manager/productpacks/store"></c:url>
			    <li><a href="${storeUrl }">包材库存</a></li>
			    <c:url var="inUrl" value="/manager/productpacks/in"></c:url>
			    <li><a href="${inUrl }">包材入库</a></li>
			    <c:url var="outUrl" value="/manager/productpacks/out"></c:url>
			    <li><a href="${outUrl }">包材出库</a></li>
			    <c:url var="typeUrl" value="/manager/productpacks/type"></c:url>
			    <li><a href="${typeUrl }">包材类型</a></li>
			    <c:url var="referenceUrl" value="/manager/productpacks/references"></c:url>
			    <li class="active"><a href="${referenceUrl }">卷膜包材重量转换</a></li>
			</ul>  
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>卷膜包材转换列表</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入卷膜包材名称" />
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
                                    名称
                                </th>
                                <th class="span3 sortable">
                                    材质
                                </th>
                                <th class="span3 sortable">
                                    卷膜单位价格(元/公斤)
                                </th>
                                <th class="span3 sortable">
                                    包材单位重量(克/个)
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${referens}" var="ref">
                        	<tr>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productpacks/profile?id=${ref.pack.id }"></c:url>
	                                <a href="${proUrl }" class="name">${ref.pack.name }</a>
	                            </td>
	                            <td>
	                                ${ref.material }
	                            </td>
	                            <td>
	                                ${ref.uprice }
	                            </td>
	                            <td>
	                                ${ref.uweight2 }
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
    <!-- end main container -->
    
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
		hrefFormer : 'references',
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
		window.location.href = '<c:url value="references" />?context='+encodeURIComponent(text);
}


</script>

<jsp:include page="../includes/footer.jsp" />