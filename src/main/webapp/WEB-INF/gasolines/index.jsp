<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="员工汽油费" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>员工汽油费</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入员工姓名"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
                    </div>
                </div>
                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="span1 sortable">
                                    员工
                                </th>
                                <th class="span2 sortable">
									客户
                                </th>
                                <th class="span1 sortable">
                                    工作类型
                                </th>
                                <th class="span2 sortable">
                                    工作内容
                                </th>
                                <th class="span1 sortable">
                                    启点
                                </th>
                                <th class="span1 sortable">
                                    终点
                                </th>
                                <th class="span1 sortable">
                                    公里数
                                </th>
                                <th class="span1 sortable">
                                    汽油费(元)
                                </th>
                                <th class="span2 sortable">
                                    日期
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${costs}" var="cost">
                        	<tr>
                        		<td>
	                                ${cost.employee.fullname }
	                            </td>
	                            <td>
	                                ${cost.dealer.name }
	                            </td>
	                            <td>
	                                ${cost.type.name }
	                            </td>
	                            <td>
	                                ${cost.content }
	                            </td>
	                            <td>	                                
	                                ${cost.splace }
	                            </td>
	                            <td>	                                
	                                ${cost.eplace }
	                            </td>
	                            <td>	                                
	                                ${cost.kmcount }
	                            </td>
	                            <td>	                                
	                                ${cost.gasoline }
	                            </td>
	                            <td>	                                
	                                <fmt:formatDate value='${cost.date }'
													type='date' pattern='yyyy-MM-dd' />
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