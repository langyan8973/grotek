<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="员工信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
	       		<c:url var="workUrl" value="/work/members/works?eid=${employee.id }"></c:url>
			    <li><a href="${workUrl }">周项目工作</a></li>
			    <c:url var="travelUrl" value="/work/members/travels?eid=${employee.id }"></c:url>
			    <li class="active"><a href="${travelUrl }">出差费用报销</a></li>
			    <c:url var="exsUrl" value="/work/members/exs?eid=${employee.id }"></c:url>
			    <li><a href="${exsUrl }">市场开拓费用报销</a></li>
			    <c:url var="applyUrl" value="/work/members/sends?eid=${employee.id }"></c:url>
			    <li><a href="${applyUrl }">发货申请</a></li>
			</ul> 
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>${employee.fullname }出差报销</h3>
                    
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
                	<c:forEach items="${rbsms}" var="rbsm">
                	<c:if test="${rbsm.count!=0 }">
                	<label style="margin-left:30px;">
                	申报人：${rbsm.employee.fullname }&nbsp;&nbsp;
                	申报时间：<fmt:formatDate value='${rbsm.date }' type='date' pattern='yyyy/MM/dd' />
			        </label>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                            	<th class="sortable">
                                    客户
                                </th>
                                <th class="sortable">
                                    工作
                                </th>
                                <th class="sortable">
                                    开始时间
                                </th>
                                <th class="sortable">
                                    结束时间
                                </th>
                                <th class="sortable">
                                    出发地
                                </th>
                                <th class="sortable">
                                    目的地
                                </th>
                                <th colspan="2" class="span4">
                                    报销内容
                                </th>
                                <th class="sortable">
                                    合计
                                </th>
                                <th class="sortable">
                                    审核状态
                                </th>
                                <th class="sortable">
                                    报销状态
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        	<tr>
                        		<td rowspan="${rbsm.count }">
	                            	${rbsm.dealer.name }
	                            </td>
                        		<td rowspan="${rbsm.count }">
                        			${rbsm.type.name }
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                                <fmt:formatDate value='${rbsm.stime }'
														type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                                <fmt:formatDate value='${rbsm.etime }'
														type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                                ${rbsm.splace }
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                                ${rbsm.eplace }
	                            </td>
	                            <td class="span2">
	                                ${rbsm.items[0].type.name }
	                            </td>
	                            
	                            <td class="span2">
	                            	${rbsm.items[0].amount }
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                            	${rbsm.sum }
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                            	<c:if test="${rbsm.checked==0 }">
	                            		<span class="label label-info">未审核</span>
	                            	</c:if>
	                            	<c:if test="${rbsm.checked==1 }">
	                            		<span class="label label-success">通过</span>
	                            	</c:if>
	                            	<c:if test="${rbsm.checked==-1 }">
	                            		<span class="label label-important">未通过</span>
	                            	</c:if>
	                            </td>
	                            <td rowspan="${rbsm.count }">
	                            	<c:if test="${rbsm.paystatus==0 }">
	                            		<span class="label label-info">未完成</span>
	                            	</c:if>
	                            	<c:if test="${rbsm.paystatus==1 }">
	                            		<span class="label label-success">完成</span>
	                            	</c:if>
	                            </td>
                        	</tr>
                        	<c:forEach items="${rbsm.items }" var="item" varStatus="status" begin="1" end="${rbsm.count-1 }">
                        	<tr>
                            	<td class="span2">
	                                ${item.type.name }
	                            </td>
	                            
	                            <td class="span2">
	                            	${item.amount }
	                            </td>
	                         </tr>
                            </c:forEach>                       
                        </tbody>
                    </table>
                    </c:if>
                    </c:forEach> 
                    <div id="kkpager" class="span10"></div>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<script type="text/javascript">
$(document).ready(function() {
    var size = 4;
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
		hrefFormer : 'travels',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			return this.hrefFormer + "?eid=${employee.id}&page=" + (n-1) + "&size=" + size;
		}
		
	});
});


</script>
<jsp:include page="../includes/footer.jsp" />