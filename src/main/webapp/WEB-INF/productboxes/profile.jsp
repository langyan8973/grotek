<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="产品信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productboxes/index"></c:url>
			    <li class="active"><a href="${listUrl }">产品信息</a></li>
			    <c:url var="priceUrl" value="/manager/productboxes/price"></c:url>
			    <li><a href="${priceUrl }">产品价格</a></li>
			    <c:url var="storeUrl" value="/manager/productboxes/store"></c:url>
			    <li><a href="${storeUrl }">产品库存</a></li>
			    <c:url var="inUrl" value="/manager/productboxes/in"></c:url>
			    <li><a href="${inUrl }">产品入库</a></li>
			    <c:url var="outUrl" value="/manager/productboxes/out"></c:url>
			    <li><a href="${outUrl }">产品出库</a></li>
			    <c:url var="laborUrl" value="/manager/productboxes/laborcost"></c:url>
			    <li><a href="${laborUrl }">生产用工</a></li>
			</ul> 
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span8">
                        <h3 class="name">${box.name }</h3>
                    </div>
                    <a class="btn-flat icon pull-right delete-user"  title="删除" data-placement="top"
		                     href="javascript:deleteone('${box.id }')">
		                        <i class="icon-trash"></i>
		                    </a>
                    <a class="btn-flat icon pull-right  delete-user" href="#editOne-modal" data-toggle="modal">
                        <i class="icon-edit"></i>
                    </a>
                    <%-- <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="删除" data-placement="top"
                     href="javascript:deleteone(${box.id })">
                        <i class="icon-trash"></i>
                    </a> --%>
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
				<c:if test="${price==null }">
                     <div class="alert alert-error">
					  请立即设置产品价格，否则产品将不能用于销售用途。
					</div>
                </c:if>
                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>编码：${box.code } </li>
	                        </ul>
	                        <small>
	                        	<ul class="infolist-inline">                          
		                            <li>产品规格：${box.specification } </li>
		                            <li>产品单位：${box.gu.name } </li>
		                            <li>肥料类型：${box.type.name } </li>
		                            <li>单位产品净量：${box.netcontent } </li>
		                            <li>每件单位数量：${box.piececount } </li>
		                            <li>每件产品净量：${box.piececontent } </li>
		                            <li>每件产品毛重：${box.kg }(kg) </li>
		                        </ul>
	                        </small>
                        </h4>         
                    </div>
                    <div class="bs-docs-price">
                        <div class="row-fluid" >
                        	<c:if test="${role.id==1 || role.id==4}">
                       		<a class="btn-flat icon pull-right  delete-user" href="#editprice-modal" style="margin-right:30px;" data-toggle="modal">
		                        <i class="icon-edit"></i>
		                    </a>
		                    </c:if>		                    
                       	</div>
                       	
                        <c:if test="${price!=null }">
	                    	<ul class="infolist-inline">
	                            <li>每单位产品出厂价格：${price.unitprice }(元) </li>
	                            <li>每件产品出厂价格：${price.pieceprice }(元) </li>
	                            <li>每单位产品经销商价格：${price.dealerUnitprice }(元) </li>
	                            <li>每件产品经销商价格：${price.dealerPieceprice }(元) </li>
	                            <li>每单位产品零售价格：${price.retailUnitprice }(元) </li>
	                            <li>更新日期：<fmt:formatDate value='${price.updateDate }'
										type='date' pattern='yyyy-MM-dd' />
	                            </li>
	                        </ul>
	                     </c:if> 
                    </div>
                    
                    <div class="bs-docs-labor">
                        <div class="row-fluid" >
                       		<a class="btn-flat icon pull-right  delete-user" href="#editlaborcost-modal" style="margin-right:30px;" data-toggle="modal">
		                        <i class="icon-edit"></i>
		                    </a>		                    
                       	</div>
                       	<c:if test="${laborcost!=null }">
	                    	<ul class="infolist-inline">
	                            <li>每件人工费用：${laborcost.pielaborPrice }(元) </li>
	                            <li>每吨或千升人工费用：${laborcost.tonlaborPrice }(元) </li>
	                            <li>更新日期：<fmt:formatDate value='${laborcost.date }'
										type='date' pattern='yyyy-MM-dd' />
	                            </li>
	                        </ul>
	                    </c:if>
                    </div>
                    
                    <div class="bs-docs-setting">
                        <div class="row-fluid">
							<c:choose>
							   <c:when test="${store==null }">   
	                                <div class="span8">
				                       <h6>库存：0</h6> 
				                    </div>      
							   </c:when>
							   <c:otherwise> 
							    	<div class="span8">
				                       <h6>库存：${store.count }(件)</h6> 
				                    </div> 
							   </c:otherwise>
							</c:choose> 
							<a class="btn-flat icon  delete-user" href="#editStore-modal" data-toggle="modal">
		                        库存编辑
		                    </a>
							<c:url var = "schemeUrl" value="/manager/productboxes/scheme?pid=${box.id }"></c:url>
		                    <a class="btn-flat icon  delete-user" href="${schemeUrl }" target="_Blank">
		                        入库方案
		                    </a>
		                    <c:url var = "newinUrl" value="/manager/productboxes/newin?pid=${box.id }"></c:url>
		                    <a class="btn-flat icon  delete-user" href="${newinUrl }">
		                        入库
		                    </a>
		                    <c:url var = "newoutUrl" value="/manager/productboxes/newout?pid=${box.id }"></c:url>
		                    <a class="btn-flat icon  delete-user" href="${newoutUrl }">
		                        出库
		                    </a>
		                </div>
                    </div>
                    <!-- side address column -->
                    <div class="span12 address"> 
                    	<div class="profile-box">
                        	<h6>特惠经销商
                        		<small>
                        			
                        			<c:url var="moreprice" value="/manager/dealerprices/index?context=${box.name }"></c:url>
                        			<a class="icon pull-right" href="${moreprice }" style="margin:20px 10px 0 10px;">
					                    查看更多
					                </a>
					                
                        			<c:url var="addprice" value="/manager/dealerprices/new?pid=${box.id }"></c:url>
                        			<a class="icon pull-right" href="${addprice }" style="margin:20px 10px 0 10px;">
					                    <i class="icon-plus"></i>添加
					                </a>
					                
                        		</small>
                        	</h6>
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span4">
                                            经销商
                                        </th>
                                        <th class="span4">
                                            特惠价(元)
                                        </th>
                                        <th class="span4">
                                            更新时间
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${dealerprices}" var="dealerprice">
			                        	<tr>
			                        		<td>
				                            	<c:url var ="deaUrl" value = "/manager/dealers/profile?id=${dealerprice.dealer.id }"></c:url>
	                                			<a href="${deaUrl }" class="name" >${dealerprice.dealer.name }</a>
				                            </td>
				                            <td>
				                                ${dealerprice.price}
				                            </td>
			                        		<td>
				                                <fmt:formatDate value='${dealerprice.date}'
													type='date' pattern='yyyy-MM-dd' />
				                            </td>	         
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>                           
                        </div>              	
                        <div class="profile-box">
                            <h6>入库信息</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span3">
                                            时间
                                        </th>
                                        <th class="span3">
                                            数量(件)
                                        </th>
                                        <th class="span3">
                                            详情
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${ins}" var="in">
			                        	<tr>
			                        		<td>
				                                <fmt:formatDate value='${in.date}'
													type='date' pattern='yyyy-MM-dd' />
				                            </td>
				                            <td>
				                                ${in.count}
				                            </td>	
				                            <td>
				                            	<c:url var ="boxinUrl" value = "/manager/productboxes/boxin?id=${in.id }"></c:url>
	                                			<a href="${boxinUrl }" class="name" target="_Blank">查看</a>
				                            </td>			         
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>

                            
                        </div>
                        
                        <div class="profile-box">
                            <h6>出库信息</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span3">
                                            时间
                                        </th>
                                        <th class="span2">
                                            数量(件)
                                        </th>
                                        <th class="span3">
                                            经销商
                                        </th>
                                        <th class="span2">
                                            销售进度
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:forEach items="${outs}" var="out">
			                        	<tr>
			                        		<td>
				                                <fmt:formatDate value='${out.date}'
													type='date' pattern='yyyy-MM-dd' />
				                            </td>
				                            <td>
				                                ${out.count}
				                            </td>
				                            <td>
				                                ${out.dealer.name }
				                            </td>	
				                            <td>
				                                <c:url var ="boxinUrl" value = "/manager/dealersales/profile?outid=${out.id}"></c:url>
			                                	<a href="${boxinUrl }" class="name">查看</a>
				                            </td>				         
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editboxUrl" value="editone" />
		<form id="editbox-form" class="form-horizontal" action="${editboxUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑产品信息</h3>
			</div>
			<br>
			<div class="modal-body">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">产品名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入产品名称" 
						style="IME-MODE: disabled;"  maxlength="20" value="${box.name }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="bid" id="bid" value="${box.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">产品编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入编码" 
						style="IME-MODE: disabled;"  maxlength="20" value="${box.code }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="specification">产品规格</label>
					<div class="controls">
						<input type="text" class="span3" name="specification" id="specification" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value="${box.specification }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="unit">单位</label>
					<div class="controls">
						<select id="type" name="unit" class="form-control" value="${box.gu.id }">
							<c:forEach items="${units}" var="unit">
								<c:choose>
								   <c:when test="${box.gu.id==unit.id }">   
                                    <option value="${unit.id }" selected="">
										${unit.name }
									</option>     
								   </c:when>
								   <c:otherwise> 
								    <option value="${unit.id }">
										${unit.name }
									</option>
								   </c:otherwise>
								</c:choose> 
							</c:forEach>
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="type">肥料类型</label>
					<div class="controls">
						<select id="type" name="type" class="form-control" value="${box.type.id }">
							<c:forEach items="${types}" var="type">
								<c:choose>
								   <c:when test="${box.type.id==type.id }">   
                                    <option value="${type.id }" selected="">
										${type.name }
									</option>     
								   </c:when>
								   <c:otherwise> 
								    <option value="${type.id }">
										${type.name }
									</option>
								   </c:otherwise>
								</c:choose> 
							</c:forEach>
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="netcontent">单位产品净量</label>
					<div class="controls">
						<input type="text" class="span3" name="netcontent" id="netcontent" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${box.netcontent }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="piececount">每件单位数量</label>
					<div class="controls">
						<input type="text" class="span3" name="piececount" id="piececount" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${box.piececount }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="piececontent">每件产品净量</label>
					<div class="controls">
						<input type="text" class="span3" name="piececontent" id="piececontent" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${box.piececontent }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="kg">每件产品毛重</label>
					<div class="controls">
						<input type="text" class="span3" name="kg" id="kg" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${box.kg }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
			</div>
		</form>
	</div>
	
	<div class="modal hide fade" id="editprice-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editpriceUrl" value="editprice" />
		<form id="editprice-form" class="form-horizontal" action="${editpriceUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑价格信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="unitprice">每单位产品出厂价格(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="unitprice" id="unitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.unitprice }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="boxid" id="boxid" value="${box.id }">
						<input type="hidden" name="pid" id="pid" value="${price.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="pieceprice">每件产品出厂价格(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="pieceprice" id="pieceprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.pieceprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="dealerUnitprice">每单位产品经销商价格(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="dealerUnitprice" id="dealerUnitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.dealerUnitprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="dealerPieceprice">每件产品经销商价格(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="dealerPieceprice" id="dealerPieceprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.dealerPieceprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="retailUnitprice">每单位产品零售价格(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="retailUnitprice" id="retailUnitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.retailUnitprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	
	<div class="modal hide fade" id="editlaborcost-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editlaborcostUrl" value="editlaborcost" />
		<form id="editlaborcost-form" class="form-horizontal" action="${editlaborcostUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑生产用工信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="pielaborPrice">每件产品人工费用(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="pielaborPrice" id="pielaborPrice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${laborcost.pielaborPrice }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="boxid" id="boxid" value="${box.id }">
						<input type="hidden" name="pid" id="pid" value="${laborcost.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="tonlaborPrice">每吨或千升产品人工费用(元)</label>
					<div class="controls">
						<input type="text" class="span3" name="tonlaborPrice" id="tonlaborPrice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${laborcost.tonlaborPrice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
			
		</form>
	</div>
	
	<div class="modal hide fade" id="editStore-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editstoreUrl" value="editstore" />
		<form id="editstore-form" class="form-horizontal" action="${editstoreUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑产品库存</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="price">产品库存数量</label>
					<div class="controls">
						<input type="text" class="span3" name="storecount" id="storecount" placeholder="输入库存数量" 
						style="IME-MODE: disabled;"  maxlength="20" value="" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${box.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
    
<script type="text/javascript">
$(document).ready(
		function() {			
			editboxFormValidate();
			editpriceFormValidate();
			editlaborcostFormValidate();
			storeFormValidate();
		});
		
function editboxFormValidate() {
	$("#editbox-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					code: {
						required : true
					},
					specification : {
						required : true
					},
					unit: {
						required : true
					},
					type: {
						required : true
					},
					netcontent : {
						required : true
					},
					piececount: {
						required : true
					},
					piececontent : {
						required : true
					},
					kg : {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					code : {
						required : "必填"
					},
					specification : {
						required : "必填"
					},
					unit : {
						required : "必填"
					},
					type : {
						required : "必填"
					},
					netcontent : {
						required : "必填"
					},
					piececount : {
						required : "必填"
					},
					piececontent : {
						required : "必填"
					},
					kg : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}
function editpriceFormValidate() {
	$("#editprice-form").validate(
			{
				debug : true,
				rules : {
					unitprice : {
						required : true
					},
					pieceprice: {
						required : true
					},
					dealerUnitprice : {
						required : true
					},
					dealerPieceprice: {
						required : true
					},
					retailUnitprice : {
						required : true
					}
				},

				messages : {
					unitprice : {
						required : "必填"
					},
					pieceprice : {
						required : "必填"
					},
					dealerUnitprice : {
						required : "必填"
					},
					dealerPieceprice : {
						required : "必填"
					},
					retailUnitprice : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}

function editlaborcostFormValidate() {
	$("#editlaborcost-form").validate(
			{
				debug : true,
				rules : {
					pielaborPrice : {
						required : true
					},
					tonlaborPrice: {
						required : true
					}
				},

				messages : {
					pielaborPrice : {
						required : "必填"
					},
					tonlaborPrice : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}

function storeFormValidate() {
	$("#editstore-form").validate(
			{
				debug : true,
				rules : {
					storecount : {
						required : true
					}
				},

				messages : {
					storecount : {
						required : "必填"
					}
				},

				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}

function deleteone(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = '<c:url value="index" />';
		}).fail(function() {
		});
	});
}

function newin(){
	var need = $("#need").val();
	if(need==1){
		bootbox.alert("卷膜包材需要配置质量转换信息");
		return;
	}
	window.location.href = '<c:url value="newin?pid=${productpack.id }" />';
}

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