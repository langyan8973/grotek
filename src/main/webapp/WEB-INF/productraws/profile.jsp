<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="原料信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
			    <c:url var="listUrl" value="/manager/productraws/index"></c:url>
			    <li class="active"><a href="${listUrl }" >原料信息</a></li>
			    <c:url var="priceUrl" value="/manager/productraws/price"></c:url>
			    <li><a href="${priceUrl }">原料价格</a></li>
			    <c:url var="storeUrl" value="/manager/productraws/store"></c:url>
			    <li><a href="${storeUrl }">原料库存</a></li>
			    <c:url var="inUrl" value="/manager/productraws/in"></c:url>
			    <li><a href="${inUrl }">原料入库</a></li>
			    <c:url var="outUrl" value="/manager/productraws/out"></c:url>
			    <li><a href="${outUrl }">原料出库</a></li>
			</ul> 
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span8">
                        <h3 class="name">
                        ${productraw.name }
                        <c:if test="${productraw.status ==-1}">
                        (已删除)
                        </c:if>
                        </h3>
                    </div>
                    <c:if test="${productraw.status ==0}">
	                    <a class="btn-flat icon pull-right  delete-user" href="#editOne-modal" data-toggle="modal">
	                        <i class="icon-edit"></i>
	                    </a>
	                    <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="删除" data-placement="top"
	                     href="javascript:deleteone(${productraw.id })">
	                        <i class="icon-trash"></i>
	                    </a>
                    </c:if>
                    
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
                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>编码：${productraw.code } </li>
	                            <li>单位：${productraw.unit.name } </li>
	                        </ul>
                        	<small>
                        		<ul class="infolist-inline">
		                            <li style="margin-right:10px;">说明：${productraw.description } </li>
		                        </ul>
                        	</small>
                        </h4>         
                    </div>
					<div class="bs-docs-setting">
                         <c:choose>
						   <c:when test="${price==null }">   
                                <div class="row-fluid">
				                    <div class="span6">
				                       <h6>尚无价格信息</h6> 
				                    </div>
				                    <c:if test="${productraw.status ==0}">
				                    <a class="btn-flat icon  delete-user" href="#editPrice-modal" data-toggle="modal">
				                        添加
				                    </a>
				                    </c:if>
				                </div>        
						   </c:when>
						   <c:otherwise> 
						    	<div class="row-fluid">
				                    <div class="span3">
				                       <h6>价格：${price.price }(元)</h6> 
				                    </div>
				                    <div class="span3">
				                       <h6>更新日期：<fmt:formatDate value='${price.date }'
														type='date' pattern='yyyy-MM-dd' />
				                       </h6> 
				                    </div>
				                    <c:if test="${productraw.status ==0}">
				                    <a class="btn-flat icon  delete-user" href="#editPrice-modal" data-toggle="modal">
				                        更新
				                    </a>
				                    </c:if>
				                </div>
						   </c:otherwise>
						</c:choose> 
						
						<div class="row-fluid">
							<c:choose>
							   <c:when test="${store==null }">   
	                                <div class="span6">
				                       <h6>库存：0</h6> 
				                    </div>      
							   </c:when>
							   <c:otherwise> 
							    	<div class="span6">
				                       <h6>库存：${store.count }(${productraw.unit.name })</h6> 
				                    </div> 
							   </c:otherwise>
							</c:choose> 
							<c:if test="${productraw.status ==0}">
		                    <a class="btn-flat icon  delete-user" href="#editStore-modal" data-toggle="modal">
		                        库存编辑
		                    </a>
							<c:url var = "newinUrl" value="/manager/productraws/newin?pid=${productraw.id }"></c:url>
		                    <a class="btn-flat icon  delete-user" href="${newinUrl }">
		                        入库
		                    </a>
		                    <c:url var = "newoutUrl" value="/manager/productraws/newout?pid=${productraw.id }"></c:url>
		                    <a class="btn-flat icon  delete-user" href="${newoutUrl }">
		                        出库
		                    </a>
		                    </c:if>
		                </div>      
                    </div>
                    <!-- side address column -->
                    <div class="span12 address">                 	
                        <div class="profile-box">
                            <h6>入库信息</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span5">
                                            时间
                                        </th>
                                        <th class="span4">
                                            数量(${productraw.unit.name })
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
                                        <th class="span2">
                                            时间
                                        </th>
                                        <th class="span2">
                                            数量(${productraw.unit.name })
                                        </th>
                                        <th class="span3">
                                            详情
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
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
				                                <c:if test="${out.boxinid!=null }">
				                                	<c:url var ="boxinUrl" value = "/manager/productboxes/boxin?id=${out.boxinid }"></c:url>
				                                	<a href="${boxinUrl }" class="name" target="_Blank">查看产品入库信息</a>
				                                </c:if>
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
		<c:url var="editprorawUrl" value="editone" />
		<form id="editproraw-form" class="form-horizontal" action="${editprorawUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑原料信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">原料名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入原料名称" 
						style="IME-MODE: disabled;"  maxlength="20" value="${productraw.name }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${productraw.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">原料编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入原料编码" 
						style="IME-MODE: disabled;"  maxlength="20" value="${productraw.code }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="unit">单位</label>
					<div class="controls">
						<select id="type" name="unit" class="form-control" value="${productraw.unit.id }">
							<c:forEach items="${units}" var="unit">
								<c:choose>
								   <c:when test="${productraw.unit.id==unit.id }">   
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
					<label class="control-label" for="description">说明</label>
					<div class="controls">
						<textarea rows="5" path="description" name='description' id="description" class="span3" placeholder="简要说明，50字以内" maxlength="50">${productraw.description }</textarea>
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
	
    <div class="modal hide fade" id="editPrice-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editpriceUrl" value="editprice" />
		<form id="editprice-form" class="form-horizontal" action="${editpriceUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑原料价格</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="price">原料价格(元/${productraw.unit.name })</label>
					<div class="controls">
						<input type="text" class="span3" name="price" id="price" placeholder="输入原料价格" 
						style="IME-MODE: disabled;"  maxlength="20" value="" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${productraw.id }">
						<c:if test="${price!=null }">
							<input type="hidden" name="priceid" id="priceid" value="${price.id }">
						</c:if>
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
				<h3>编辑原料库存</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="price">原料库存数量</label>
					<div class="controls">
						<input type="text" class="span3" name="storecount" id="storecount" placeholder="输入库存数量" 
						style="IME-MODE: disabled;"  maxlength="20" value="" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${productraw.id }">
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
			addPositionFormValidate();
			editpriceFormValidate();
			storeFormValidate();
		});
		
function addPositionFormValidate() {
	$("#editproraw-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					unit : {
						required : true
					},
					code: {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					unit : {
						required : "必填"
					},
					code : {
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

function editpriceFormValidate() {
	$("#editprice-form").validate(
			{
				debug : true,
				rules : {
					price : {
						required : true
					}
				},

				messages : {
					price : {
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