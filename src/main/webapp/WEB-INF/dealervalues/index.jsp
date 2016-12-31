<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="支持费用和表现评价" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>支持费用和表现评价</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入经销商名称查找"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
                        <c:url var="addUrl" value="/manager/dealervalues/new" />
                        <a class="btn btn-flat success pull-right" href="${addUrl }">
                            <span>&#43;</span>
                            新增
                        </a>
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
                                <th class="span1 sortable">
                                    让利金额(元)
                                </th>
                                <th class="span1 sortable">
                                    返点金额(元)
                                </th>
                                <th class="span1 sortable">
                                    奖励金额(元)
                                </th>
                                <th class="span3 sortable">
                                    表现评价
                                </th>
                                <th class="span2 sortable">
                                    时间
                                </th>
                                <th class="span2 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:forEach items="${values}" var="dvalue">
                        	<tr>
                        		<td>
                        			<c:url var="dealerUrl" value="/manager/dealers/profile?id=${dvalue.dealer.id }"></c:url>
	                                <a href="${dealerUrl }" class="name">${dvalue.dealer.name }</a>
	                            </td>
	                            <td>
	                            	${dvalue.promote }
	                            </td>
	                            <td>
	                                ${dvalue.rebate }
	                            </td>
	                            <td>
	                            	${dvalue.reward }
	                            </td>
	                            <td>
	                                ${dvalue.description }
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${dvalue.date }'
													type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td>
	                            	<c:url var="editUrl" value="edit?id=${dvalue.id }"></c:url>
	                                <a id="editdept" class="btn btn-mini btn-warning"  href="${editUrl }">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${dvalue.id})">删除</button></td>
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

function deleteOne(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
</script>

<jsp:include page="../includes/footer.jsp" />