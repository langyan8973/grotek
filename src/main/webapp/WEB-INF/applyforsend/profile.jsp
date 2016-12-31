<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="发货申请单" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="workUrl" value="/assets/css/compiled/personal-info.css" />
<link rel="stylesheet" href="${workUrl }" type="text/css" media="screen" />

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>发货申请单</h3>
                    <div class="span10 pull-right">
                    	<c:if test="${apply.opstatus==0}">
                    		<c:url var="editUrl" value="/work/applyforsend/edit?id=${apply.id }" />
	                        <a class="btn-flat icon pull-right  delete-user" href="${editUrl }">
		                        <i class="icon-edit"></i>
		                    </a>
                    	</c:if>
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
                <div class="span3">
                </div>
                <div class="span8 table">
                    <table class="table table-bordered">
                        <tbody>
                        	<tr>
                        		<td class="span2">客户</td>
                        		<td colspan="4">
	                            	${apply.dealer.name }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">公司</td>
                        		<td colspan="4">
                        			${apply.dcompany }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">收货地址</td>
                        		<td colspan="4">
                        			${apply.address }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">联系人</td>
                        		<td colspan="4">
                        			${apply.contact }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">联系电话</td>
                        		<td colspan="4">
                        			${apply.phone }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td colspan="5" style="text-align:center;">
                        			<h5>产品</h5>
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">名称</td>
                        		<td class="span2">规格</td>
                        		<td class="span1">价格</td>
                        		<td class="span1">数量(件)</td>
                        		<td class="span2">金额</td>
	                        </tr>
	                        <c:forEach items="${apply.boxitems}" var="boxitme">
		                        <tr>
	                        		<td class="span2">${boxitme.box.name }</td>
	                        		<td class="span2">${boxitme.box.specification }</td>
	                        		<td class="span1">${boxitme.price }</td>
	                        		<td class="span1">${boxitme.count }</td>
	                        		<td class="span2">${boxitme.total }</td>
		                        </tr>
	                        </c:forEach>
	                        <c:if test="${apply.sampleitems[0]!=null }">
	                        	<tr>
	                        		
		                            <td colspan="5" style="text-align:center;">
                        				<h5>附带样品</h5>
                        			</td>
	                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="1">名称</td>
	                        		<td colspan="1">规格</td>
	                        		<td colspan="3">数量</td>
		                        </tr>
		                        <c:forEach items="${apply.sampleitems}" var="sampleitme">
			                        <tr>
		                        		<td colspan="1">${sampleitme.box.name }</td>
		                        		<td colspan="1">${sampleitme.box.specification }</td>
	                        			<td colspan="3">${sampleitme.count }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if>
	                        
	                        <c:if test="${apply.packitems[0]!=null }">
	                        	<tr>
	                        		
		                            <td colspan="5" style="text-align:center;">
                        				<h5>附带包材</h5>
                        			</td>
	                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="2">名称</td>
	                        		<td colspan="3">数量</td>
		                        </tr>
		                        <c:forEach items="${apply.packitems}" var="packitme">
			                        <tr>
		                        		<td colspan="2">${packitme.pack.name }</td>
	                        			<td colspan="3">${packitme.count }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if>
	                        <c:if test="${apply.pageitems[0]!=null }">
	                        	<tr>
		                            <td colspan="5" style="text-align:center;">
                        			<h5>附带宣传品</h5>
	                            </td>
		                        </tr>
		                        <tr>
	                        		<td colspan="2">名称</td>
	                        		<td colspan="3">数量</td>
		                        </tr>
		                        <c:forEach items="${apply.pageitems}" var="pageitme">
			                        <tr>
		                        		<td colspan="2">${pageitme.page.name }</td>
	                        			<td colspan="3">${pageitme.count }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if>
	                        <tr>
                        		<td class="span2">合计</td>
                        		<td colspan="4">
                        			${apply.total }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">申请时间</td>
                        		<td colspan="4">
                        			<fmt:formatDate value='${apply.date }'
													type='date' pattern='yyyy-MM-dd HH:mm' />
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">申请人</td>
                        		<td colspan="4">
                        			${apply.employee.fullname }
	                            </td>
	                        </tr>
	                        <tr>
                        		<td class="span2">状态</td>
                        		<td colspan="4">
                        			<c:if test="${apply.opstatus==0 }">
	                            		<span class="label label-info">未发货</span>
	                            	</c:if>
	                            	<c:if test="${apply.opstatus==1 }">
	                            		<span class="label label-success">已发货</span>
	                            	</c:if>
	                            </td>
	                        </tr>         
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->
<script type="text/javascript">

</script>
<jsp:include page="../includes/footer.jsp" />