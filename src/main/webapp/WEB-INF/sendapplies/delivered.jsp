<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="发货申请" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/sendapplies/index"></c:url>
			    <li><a href="${listUrl }">未完成发货申请</a></li>
			    <c:url var="priceUrl" value="/manager/sendapplies/delivered"></c:url>
			    <li class="active"><a href="${priceUrl }">已完成发货申请</a></li>
			</ul> 
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>已完成发货申请</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入员工姓名"  onkeydown="onKeyDown(event)"/>
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
               <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="sortable">
                                    客户
                                </th>
                                <th class="sortable">
                                    送货地址
                                </th>
                                <th class="sortable">
                                    联系人
                                </th>
                                <th class="sortable">
                                    联系电话
                                </th>
                                <th class="sortable">
                                    产品总价
                                </th>
                                <th class="sortable">
                                    申报日期
                                </th>
                                <th class="sortable">
                                    申报人
                                </th>
                                <th class="sortable">
                                    状态
                                </th>
                                <th class="span2 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${applies}" var="apply">
                        	<tr>
                        		<td>
                        			${apply.dealer.name }
	                            </td>
	                            <td>
	                                ${apply.address }
	                            </td>
	                            <td>
	                                ${apply.contact }
	                            </td>
	                            <td>
	                            	${apply.phone }
	                            </td>
	                            <td>
	                            	${apply.total }
	                            </td>
	                            <td>
	                            	<fmt:formatDate value='${apply.date }'
													type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td>
	                            	${apply.employee.fullname }
	                            </td>
	                            <td>
	                            	<c:if test="${apply.opstatus==0 }">
	                            		<span class="label label-info">未发货</span>
	                            	</c:if>
	                            	<c:if test="${apply.opstatus==1 }">
	                            		<span class="label label-success">已发货</span>
	                            	</c:if>
	                            </td>
	                            <td>
	                            	<c:url var="editUrl" value="/manager/sendapplies/profile?id=${apply.id }"></c:url>
                            		<a id="editwork" class="btn btn-mini btn-info"  href="${editUrl }">
										详细
									</a>
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
    
  	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : currentPage,
		//总页码
		total : numPages,
		//总数据条数
		totalRecords : sumcount,
		//链接前部
		hrefFormer : 'delivered',
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
		window.location.href = '<c:url value="delivered" />?context='+encodeURIComponent(text);
}

</script>

<jsp:include page="../includes/footer.jsp" />