<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="发货申请" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="workUrl" value="/assets/css/compiled/personal-info.css" />
<link rel="stylesheet" href="${workUrl }" type="text/css" media="screen" />

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>发货申请</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入客户名称"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
						<c:url var="addemUrl" value="/work/applyforsend/new" />
                        <a class="btn btn-flat success pull-right" href="${addemUrl }">
                            <span>&#43;</span>
                            添加
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
	                            	<c:if test="${apply.opstatus==0 }">
	                            		<span class="label label-info">未发货</span>
	                            	</c:if>
	                            	<c:if test="${apply.opstatus==1 }">
	                            		<span class="label label-success">已发货</span>
	                            	</c:if>
	                            </td>
	                            <td>
	                            	<c:url var="editUrl" value="/work/applyforsend/profile?id=${apply.id }"></c:url>
                            		<a id="editwork" class="btn btn-mini btn-info"  href="${editUrl }">
										详细
									</a>
	                            	<c:if test="${apply.opstatus==0}">	                            		
										<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${apply.id})">删除</button></td>
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