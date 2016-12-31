<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="项目工作审核" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="workUrl" value="/assets/css/compiled/personal-info.css" />
<link rel="stylesheet" href="${workUrl }" type="text/css" media="screen" />
    <div class="content wide-content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="workUrl" value="/work/audititems/works"></c:url>
			    <li><a href="${workUrl }">待审核项目工作</a></li>
			    <c:url var="checkedUrl" value="/work/audititems/checkeds"></c:url>
			    <li class="active"><a href="${checkedUrl }">已审核项目工作</a></li>
			</ul>
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>已审项目工作</h3>
                    <div class="span10 pull-right">
                        
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
                <c:if test="${weekly!=nulll }">
                <div class="row-fluid table">
                	<label style="margin-left:30px;">时间段：<fmt:formatDate value='${weekly.stime }' type='date' pattern='yyyy/MM/dd' />--<fmt:formatDate value='${weekly.etime }' type='date' pattern='yyyy/MM/dd' />
			        </label>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                            	<th class="sortable">
                                    员工
                                </th>
                                <th class="sortable">
                                    客户
                                </th>
                                <th class="sortable">
                                    工作类型
                                </th>
                                <th class="sortable">
                                    工作内容
                                </th>
                                <th class="sortable">
                                    地点
                                </th>
                                <th class="tdweek">
                                    星期一
                                    <br>
									<c:if test="${shortdates[0] != null}">
										${shortdates[0]}
                                    </c:if>
                                </th>
                                <th class="tdweek">
                                    星期二
                                    <br>
									<c:if test="${shortdates[1] != null}">
										${shortdates[1]}
                                    </c:if>
                                </th>
                                <th class="tdweek">
                                    星期三
                                    <br>
									<c:if test="${shortdates[2] != null}">
										${shortdates[2]}
                                    </c:if>
                                </th>
                                 <th class="tdweek">
                                    星期四
                                    <br>
									<c:if test="${shortdates[3] != null}">
										${shortdates[3]}
                                    </c:if>
                                </th>
                                 <th class="tdweek">
                                    星期五
                                    <br>
									<c:if test="${shortdates[4] != null}">
										${shortdates[4]}
                                    </c:if>
                                </th>
                                 <th class="tdweek">
                                    星期六
                                    <br>
									<c:if test="${shortdates[5] != null}">
										${shortdates[5]}
                                    </c:if>
                                </th>
                                 <th class="tdweek">
                                    星期日
                                    <br>
									<c:if test="${shortdates[6] != null}">
										${shortdates[6]}
                                    </c:if>
                                </th>
                                <th class="tdweek">
                                    合计
                                </th>
                                <th class="sortable">
                                    审核
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${weekly.count==null }">
                        		<tr>
                        			<td>
		                            	${weekly.employee.fullname }
		                            </td>
                        			<td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            </td>
		                            <td>
		                            	<c:if test="${weekly.checked==-1 }">
		                            		<span class="label label-important">审核未通过</span>
		                            		<br>${weekly.remark }
		                            	</c:if>
		                            	<c:if test="${weekly.checked==1 }">
		                            		<span class="label label-warning">待管理员审核</span>
		                            	</c:if>
		                            	<c:if test="${weekly.checked==2 }">
		                            		<span class="label label-success">管理员审核通过</span>
		                            	</c:if>
		                            	<c:if test="${weekly.checked==3 }">
		                            		<span class="label label-important">管理员审核未通过</span>
		                            		<br>${weekly.remark }
		                            	</c:if>
	                        		</td>
                        		</tr>
                        	</c:if>
                        	<c:if test="${weekly.count!=null }">
                        	<tr>
                        		<td rowspan="${weekly.count }">
		                            ${weekly.employee.fullname }
		                        </td>
                       			<td>
                        			${weekly.items[0].dealer.name }
	                            </td>
	                            <td>
	                                ${weekly.items[0].type.name }
	                            </td>
	                            <td>
	                                ${weekly.items[0].content }
	                            </td>
	                            <td>
	                                ${weekly.items[0].place }
	                            </td>
	                            <td>
	                            	${weekly.items[0].mon }
	                            </td>
	                            <td>
	                            	${weekly.items[0].tue }
	                            </td>
	                            <td>
	                            	${weekly.items[0].wed }
	                            </td>
	                            <td>
	                            	${weekly.items[0].thu }
	                            </td>
	                            <td>
	                            	${weekly.items[0].fri }
	                            </td>
	                            <td>
	                            	${weekly.items[0].sat }
	                            </td>
	                            <td>
	                            	${weekly.items[0].sun }
	                            </td>
	                            <td>
	                            	${weekly.items[0].total }
	                            </td>
	                            <td rowspan="${weekly.count }">
                       				<c:if test="${weekly.checked==-1 }">
	                            		<span class="label label-important">审核未通过</span>
	                            		<br>${weekly.remark }
	                            		
	                            	</c:if>
	                            	<c:if test="${weekly.checked==1 }">
	                            		<span class="label label-warning">待管理员审核</span>
	                            	</c:if>
	                            	<c:if test="${weekly.checked==2 }">
	                            		<span class="label label-success">管理员审核通过</span>
	                            	</c:if>
	                            	<c:if test="${weekly.checked==3 }">
	                            		<span class="label label-important">管理员审核未通过</span>
	                            		<br>${weekly.remark }
	                            	</c:if>
                        		</td>
                       		</tr>
                        	<c:forEach items="${weekly.items }" var="item"  varStatus="status" begin="1" end="${weekly.count-1 }">
                        		<tr>
                        			<td>
	                        			${item.dealer.name }
		                            </td>
		                            <td>
		                                ${item.type.name }
		                            </td>
		                            <td>
		                                ${item.content }
		                            </td>
		                            <td>
		                                ${item.place }
		                            </td>
		                            <td>
		                            	${item.mon }
		                            </td>
		                            <td>
		                            	${item.tue }
		                            </td>
		                            <td>
		                            	${item.wed }
		                            </td>
		                            <td>
		                            	${item.thu }
		                            </td>
		                            <td>
		                            	${item.fri }
		                            </td>
		                            <td>
		                            	${item.sat }
		                            </td>
		                            <td>
		                            	${item.sun }
		                            </td>
		                            <td>
		                            	${item.total }
		                            </td>
                        		</tr>
                        	</c:forEach>
                        	</c:if>                      
                        </tbody>
                    </table>
                </div>
                </c:if>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<script type="text/javascript">
$(document).ready(function() {
    var size = 1;
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
		hrefFormer : 'checkeds',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			return this.hrefFormer + "?page=" + (n-1) + "&size=" + size;
		}
		
	});
});

</script>
<jsp:include page="../includes/footer.jsp" />