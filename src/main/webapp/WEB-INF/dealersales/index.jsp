<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="销售进度" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>销售进度列表</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入经销商名称查找"  onkeydown="onKeyDown(event)"/>
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
                                <th class="span2 sortable">
                                    经销商
                                </th>
                                <th class="span2 sortable">
                                    产品
                                </th>
                                <th class="span1 sortable">
                                    进货量(件)
                                </th>
                                <th class="span2 sortable">
                                    进货额(元)
                                </th>
                                <th class="span2 sortable">
                                    发货时间
                                </th>
                                <th class="span2 sortable">
                                    付款状况
                                </th>
                                <th class="span1 sortable">
                                    
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:forEach items="${sales}" var="sale">
                        	<tr>
                        		<td>
	                                ${sale.dealer.name }
	                            </td>
	                            <td>
	                                ${sale.box.name }
	                            </td>
	                            <td>
	                                ${sale.count }
	                            </td>
	                            <td>
	                                ${sale.total }
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${sale.posttime }'
													type='date' pattern='yyyy-MM-dd HH:mm' />
	                            </td>
	                            <td>
	                                ${sale.paystatus }
	                            </td>
	                            <td>
	                                <c:url var ="boxinUrl" value = "/manager/dealersales/profile?id=${sale.id}"></c:url>
                                	<a href="${boxinUrl }" class="name">查看</a>
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
		hrefFormer : 'index',
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
		window.location.href = '<c:url value="index" />?context='+encodeURIComponent(text);
}
</script>

<jsp:include page="../includes/footer.jsp" />