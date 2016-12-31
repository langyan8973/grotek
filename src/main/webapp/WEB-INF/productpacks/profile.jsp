<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="包材信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
			    <c:url var="listUrl" value="/manager/productpacks/index"></c:url>
			    <li class="active"><a href="${listUrl }">包材信息</a></li>
			    <c:url var="storeUrl" value="/manager/productpacks/store"></c:url>
			    <li><a href="${storeUrl }">包材库存</a></li>
			    <c:url var="inUrl" value="/manager/productpacks/in"></c:url>
			    <li><a href="${inUrl }">包材入库</a></li>
			    <c:url var="outUrl" value="/manager/productpacks/out"></c:url>
			    <li><a href="${outUrl }">包材出库</a></li>
			    <c:url var="typeUrl" value="/manager/productpacks/type"></c:url>
			    <li><a href="${typeUrl }">包材类型</a></li>
			    <c:url var="referenceUrl" value="/manager/productpacks/references"></c:url>
			    <li><a href="${referenceUrl }">卷膜包材重量转换</a></li>
			</ul> 
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span8">
                        <h3 class="name">
                        ${productpack.name }
                        <c:if test="${productpack.status ==-1}">
                        (已删除)
                        </c:if>
                        </h3>
                    </div>
                    <c:if test="${productpack.status ==0}">
                    <a class="btn-flat icon pull-right  delete-user" href="#editOne-modal" data-toggle="modal">
                        <i class="icon-edit"></i>
                    </a>
                    <a class="btn-flat icon pull-right delete-user" data-toggle="tooltip" title="删除" data-placement="top"
                     href="javascript:deleteone(${productpack.id })">
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
	                            <li>编码：${productpack.code } </li>
                            	<li>类型：${productpack.type.name } </li>
	                        </ul>
                        </h4>         
                    </div>
                    <c:if test="${productpack.type.trans==1 }">
                    	<div class="bs-docs-convert">
	                        <div class="row-fluid" >
                        		<input type="hidden" id='need' name='need' value="${need }"/>
                        		<c:if test="${productpack.status ==0}">
                        		<a class="btn-flat icon pull-right  delete-user" href="#editreferen-modal" style="margin-right:30px;" data-toggle="modal">
			                        <i class="icon-edit"></i>
			                    </a>	
			                    </c:if>		                    
                        	</div>
                        	<c:if test="${referen!=null }">
		                    	<ul class="infolist-inline">
		                            <li>材质：${referen.material } </li>
		                            <li>卷膜单位价格(元/公斤)：${referen.uprice } </li>
		                            <li>包材单位重量(克/个)：${referen.uweight2 } </li>
		                            <li>更新日期：<fmt:formatDate value='${referen.updatedate }'
											type='date' pattern='yyyy-MM-dd' />
		                            </li>
		                        </ul>
		                    </c:if>        
	                    </div>
                    </c:if>
                    <div class="bs-docs-setting">
                    	<div class="row-fluid">
		                    <div class="span3">
		                       <h6>价格：${productpack.price }</h6> 
		                    </div>
		                    <div class="span3">
		                       <h6>更新日期：<fmt:formatDate value='${productpack.uadateDate }'
												type='date' pattern='yyyy-MM-dd' />
		                       </h6> 
		                    </div>
		                </div>
						
						<div class="row-fluid">
							<c:choose>
							   <c:when test="${store==null }">   
	                                <div class="span6">
				                       <h6>库存：0</h6> 
				                    </div>      
							   </c:when>
							   <c:otherwise> 
							    	<div class="span6">
				                       <h6>库存：${store.count }</h6> 
				                    </div> 
							   </c:otherwise>
							</c:choose> 
							<c:if test="${productpack.status ==0}">
							<a class="btn-flat icon  delete-user" href="#editStore-modal" data-toggle="modal">
		                        库存编辑
		                    </a>
		                    <a class="btn-flat icon  delete-user" href="javascript:newin();">
		                        入库
		                    </a>
		                    <c:url var = "newoutUrl" value="/manager/productpacks/newout?pid=${productpack.id }"></c:url>
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
                                        <th class="span5">
                                            数量(个)
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
                                        <th class="span3">
                                            数量(个)
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
		<c:url var="editpackUrl" value="editone" />
		<form id="editpack-form" class="form-horizontal" action="${editpackUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑包材信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">包材名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入名称" 
						style="IME-MODE: disabled;"  maxlength="20" value="${productpack.name }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${productpack.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">包材编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入编码" 
						style="IME-MODE: disabled;"  maxlength="20" value="${productpack.code }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="type">类型</label>
					<div class="controls">
						<select id="type" name="type" class="form-control" value="${productpack.type.id }">
							<c:forEach items="${types}" var="type">
								<c:choose>
								   <c:when test="${productpack.type.id==type.id }">   
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
					<label class="control-label" for="price">价格</label>
					<div class="controls">
						<input type="text" class="span3" name="price" id="price" placeholder="输入价格" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${productpack.price }"/>
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
	
	<div class="modal hide fade" id="editreferen-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editreferenUrl" value="editreferen" />
		<form id="editreferen-form" class="form-horizontal" action="${editreferenUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>质量转换信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="material">材质</label>
					<div class="controls">
						<input type="text" class="span3" name="material" id="material" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value="${referen.material }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="packid" id="packid" value="${productpack.id }">
						<input type="hidden" name="rid" id="rid" value="${referen.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="uprice">卷膜单位价格(元/公斤)</label>
					<div class="controls">
						<input type="text" class="span3" name="uprice" id="uprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value="${referen.uprice }" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="uweight2">包材单位重量(克/个)</label>
					<div class="controls">
						<input type="text" class="span3" name="uweight2" id="uweight2" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" value="${referen.uweight2 }" onkeyup="clearNoNum(this)"/>
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
				<h3>编辑包材库存</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="price">包材库存数量</label>
					<div class="controls">
						<input type="text" class="span3" name="storecount" id="storecount" placeholder="输入库存数量" 
						style="IME-MODE: disabled;"  maxlength="20" value="" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
						<input type="hidden" name="id" id="id" value="${productpack.id }">
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
			editpackFormValidate();
			storeFormValidate();
		});
		
function editpackFormValidate() {
	$("#editpack-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					code: {
						required : true
					},
					price: {
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