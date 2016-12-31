<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="修改发货申请" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span8">
                        <h3 class="name">发货申请单修改</h3>
                    </div>
                </div>
                <div class="row-fluid profile">           
	                <div class="span2">						
     
	                    
	                </div>
	                <c:url var="addUrl" value="/manager/sendapplies/editone" />
		            <form id="applyforsend" action="${addUrl }" method="post">
			        <fieldset>
	                <div class="span8 table">
	                	
	                	<table class="table table-bordered">
	                        <tbody>
	                        	<tr>
	                        		<td class="span2">客户</td>
	                        		<td colspan="4">
		                            	${apply.dealer.name }
		                            	<input id="dname" name="dealer.name" type="hidden" value="${apply.dealer.name }" />
			                            <input id="did" name="dealer.id"  type="hidden" value="${apply.dealer.id }"/>
			                            <input id="id" name="id"  type="hidden" value="${apply.id }"/>
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
	                        		<td class="span2">价格</td>
	                        		<td class="span2">单位毛重(kg)</td>
	                        		<td class="span2">数量(件)</td>
		                        </tr>
		                        <c:set var="i" value="0"></c:set>
		                        <c:forEach items="${apply.boxitems}" var="boxitme">
			                        <tr>
		                        		<td class="span2">
		                        		${boxitme.box.name }
		                        		</td>
		                        		<td class="span2">
		                        		${boxitme.price }
		                        		</td>
		                        		<td class="span2">
		                        		<input id='boxitems[${i }].kg' name='boxitems[${i }].kg' type='text' class='span3 inline-input' value='${boxitme.kg }'/>
		                        		</td>
		                        		<td class="span2">
		                        		<input id='boxitems[${i }].count' name='boxitems[${i }].count' type='text' class='span3 inline-input' value='${boxitme.count }'/>
		                        		</td>
			                        </tr>
			                        <c:set var="i" value="${i+1 }"></c:set>
		                        </c:forEach>
		                        <c:if test="${apply.sampleitems[0]!=null }">
		                        	<tr>
			                            <td colspan="5" style="text-align:center;">
	                        			<h5>附带样品</h5>
		                            </td>
			                        </tr>
			                        <tr>
		                        		<td colspan="2">名称</td>
		                        		<td colspan="3">数量</td>
			                        </tr>
			                        <c:set var="m" value="0"></c:set>
			                        <c:forEach items="${apply.sampleitems}" var="sampleitme">
				                        <tr>
			                        		<td colspan="2">${sampleitme.box.name }</td>
		                        			<td colspan="3">
		                        			<input id='sampleitems[${m }].count' name='sampleitems[${m }].count' class='span3 inline-input' type='text' value='${sampleitme.count}' onkeyup='clearNoNum(this)'/>
		                        			</td>
				                        </tr>
				                        <c:set var="m" value="${m+1 }"></c:set>
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
			                        <c:set var="j" value="0"></c:set>
			                        <c:forEach items="${apply.pageitems}" var="pageitme">
				                        <tr>
			                        		<td colspan="2">${pageitme.page.name }</td>
		                        			<td colspan="3">
		                        			<input id='pageitems[${j }].count' name='pageitems[${j }].count' class='span3 inline-input' type='text' value='${pageitme.count}' onkeyup='clearNoNum(this)'/>
		                        			</td>
				                        </tr>
				                        <c:set var="j" value="${j+1 }"></c:set>
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
			                        <c:set var="k" value="0"></c:set>
			                        <c:forEach items="${apply.packitems}" var="packitme">
				                        <tr>
			                        		<td colspan="2">${packitme.pack.name }</td>
		                        			<td colspan="3">
		                        			<input id='packitems[${k }].count' name='packitems[${k }].count' class='span3 inline-input' type='text' value='${packitme.count}' onkeyup='clearNoNum(this)'/>
		                        			</td>
				                        </tr>
				                        <c:set var="k" value="${k+1 }"></c:set>
			                        </c:forEach>
		                        </c:if>
		                        
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
	                        </tbody>
	                    </table>
	                    <div class="span6 field-box actions">
                            <input type="submit" class="btn-glow primary" value="保存" />
                        </div>
	                </div>
	                </fieldset>
	            	</form>
	            </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    
<script type="text/javascript">
function clearNoNum(obj) {
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g, "");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g, "");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	//保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
}
</script>

<jsp:include page="../includes/footer.jsp" />