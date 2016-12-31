<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="我的客户" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="workUrl" value="/assets/css/compiled/personal-info.css" />
<link rel="stylesheet" href="${workUrl }" type="text/css" media="screen" />

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>我的客户</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入客户名称" onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
                        <c:url var="addUrl" value="/work/clients/new" />
                        <a class="btn btn-flat success pull-right" href="${addUrl }">
                            添加
                        </a>
                    </div>
                </div>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="span2 sortable">
                                    名称
                                </th>
                                <th class="span3 sortable">
									地址
                                </th>
                                <th class="span2 sortable">
                                    联系人
                                </th>
                                <th class="span2 sortable">
                                    手机号
                                </th>
                                <th class="span2 sortable">
                                    业务代表
                                </th>
                                <th class="span1 sortable">
                                    是否合作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${dealers}" var="dealer">
                        	<tr>
                        		<td>
	                                <c:url var="profileUrl" value="/work/clients/profile?id=${dealer.id }"></c:url>
	                                <a href="${profileUrl }" class="name">${dealer.name }</a>
	                            </td>
	                            <td>
	                                ${dealer.address }
	                            </td>
	                            <td>
	                                ${dealer.contact }
	                            </td>
	                            <td>
	                                ${dealer.mobile }
	                            </td>
	                            <td>	                                
	                                ${dealer.agent.fullname }
	                            </td>
	                            <td>
	                                <c:if test="${dealer.sfhz==1 }">
	                                	是
	                                </c:if>
	                                <c:if test="${dealer.sfhz==0 }">
	                                	否
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


</script>
<jsp:include page="../includes/footer.jsp" />