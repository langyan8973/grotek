<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="经销商信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span5">
                        <h3 class="name">${dealer.name }</h3>
                        <c:if test="${dealer.agent != null}">
                    		业务代表：${dealer.agent.fullname }
                    	</c:if> &nbsp;&nbsp;
                    	登记日期：<fmt:formatDate value='${dealer.djdate }'
													type='date' pattern='yyyy-MM-dd HH:mm' />  
                    </div>
                    <div class="span7">
                    	<div class="row-fluid">
                    		<c:url var="editUrl" value="/work/clients/edit?id=${dealer.id }"></c:url>
		                    <a class="btn-flat icon pull-right  delete-user" href="${editUrl }">
		                        <i class="icon-edit"></i>
		                    </a>
                    	</div>
	                    <div class="row-fluid" style="padding-left:30px; padding-top:10px;margin-top:50px;">
	                    	<c:url var="priceUrl" value="/work/clients/prices?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;" href="${priceUrl }" target="_Blank">特惠产品</a>
                    		<c:url var="valueUrl" value="/work/clients/values?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;" href="${valueUrl }" target="_Blank">支持费用和表现评价</a>
	                    	<c:url var="outUrl" value="/work/clients/boxin?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;" href="${outUrl }" target="_Blank">进货情况</a>
                    		<c:url var="feeUrl" value="/work/clients/employeefees?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;"  href="${feeUrl }" target="_Blank">市场开拓费用</a>
                    		<c:url var="saleUrl" value="/work/clients/expenses?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;"  href="${saleUrl }" target="_Blank">支持费用统计</a>
	                    </div>
                    </div>                   
                </div>
                <div class="row-fluid profile span8">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>名称：${dealer.name } </li>
	                            <li>联系人：${dealer.contact } </li>
	                            <li>手机号：${dealer.mobile } </li>
	                            <li>地址：${dealer.address } </li>
	                        </ul>
	                        <small>
	                        	<ul class="infolist-inline">                          
		                            <li>省份：${dealer.shengfen.name } </li>
		                            <li>地区：${dealer.diqu.name } </li>
		                            <li>县：${dealer.xian.name } </li>
		                            <li>乡镇：${dealer.zhen.name } </li>
		                            <li>邮编：${dealer.postcode } </li>
		                            <li>固定电话：${dealer.phone } </li>
		                            <li>所有者：${dealer.owner } </li>
		                            <li>所属公司：${dealer.company } </li>
		                        </ul>
		                        <c:if test="${dealer.pname!=null }">
		                        	<ul class="infolist-inline">                          
			                            <li>收款单位：${dealer.pname }</li>
			                            <li>地址：${dealer.paddress } </li>
			                            <li>联系人：${dealer.pcontact } </li>
			                            <li>联系电话：${dealer.pphone } </li>
			                        </ul>
		                        </c:if>
	                        </small>
                        </h4>         
                    </div>
                    <div class="bs-docs-operate">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>竞争实力：${dealer.strength.name } </li>
	                            <li>商业信誉：${dealer.reputation.name } </li>
	                            <li>店铺类型：${dealer.shoptype.name } </li>
	                            <li>信誉等级：${dealer.creditrating.name } </li>
	                        </ul>
	                        <small>
	                        	<ul class="infolist-inline">                          
		                            <li>年营业额：${dealer.turnover } </li>
		                            <li>店铺面积：${dealer.area } </li>
		                            <li>员工总数：${dealer.population } </li>
		                            <li>分销店数：${dealer.fxdCount } </li>
		                            <li>主要经营范围：${dealer.business } </li>
		                            <li>主要销售区域：${dealer.territory } </li>
		                            <c:if test="${dealer.sfcjxsjh==1 }">
		                            	<li>进货经销商：${dealer.jhdea.name } </li>
		                            </c:if>		                             
		                        </ul>
		                        <c:if test="${dealer.description!=null }">
	                            	<ul class="infolist-inline">                          
			                            <li>评语：${dealer.description } </li>      
			                        </ul>
	                            </c:if>	
		                        
	                        </small>
                        </h4>  
                    </div>
                    
                    
	                <c:if test="${goal!=null }">
	                    <div class="bs-docs-goal">
                     		<h4>
                     			<ul  class="baseinfolist">
                     				<li>完成目标：${goal.objective }
                     				<li>更新日期：<fmt:formatDate value='${goal.date }'
										type='date' pattern='yyyy-MM-dd' />
	                        </li>
                     			</ul>
                     			<small>
                     				<ul class="infolist-inline">
	                            <li>进货让利比例：${goal.promote } </li>
	                            <li>年终返点比例：${goal.rebate } </li>
	                            <li>支持额度：${goal.suportAmount } </li>
	                            <li>其他政策：${goal.other } </li>				                            
	                        </ul>
	                        <ul class="infolist-inline">
	                            <li>备注：${goal.remarks } </li>			                            
	                        </ul>
                     			</small>
                     		</h4>
	                     </div>
	                </c:if>
                    
                    <!-- side address column -->
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
<script type="text/javascript">

</script>
<jsp:include page="../includes/footer.jsp" />