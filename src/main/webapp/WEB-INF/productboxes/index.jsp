<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="产品管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
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
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>产品列表</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入产品名称"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
						<c:url var="addemUrl" value="/manager/productraws/new" />
                        <a class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
                            <span>&#43;</span>
                            新增产品
                        </a>
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
                <div class="row-fluid table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="span3 sortable">
                                    名称
                                </th>
                                <th class="span3 sortable">
                                    产品规格
                                </th>
                                <th class="span2 sortable">
                                    编码
                                </th>                                
                                <th class="span2 sortable">
                                    产品单位
                                </th>
                                <th class="span2 sortable">
                                    肥料类型
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${boxes}" var="box">
                        	<tr>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productboxes/profile?id=${box.id }"></c:url>
	                                <a href="${proUrl }" class="name">${box.name }</a>
	                            </td>
	                            <td>
	                                ${box.specification }
	                            </td>
	                            <td>
	                                ${box.code }
	                            </td>
	                            <td>
	                            	${box.gu.name }
	                            </td>
	                            <td>
	                            	${box.type.name }
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
    <div class="modal hide fade" id="addOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="addproductboxUrl" value="addone" />
		<form id="addproductbox-form" class="form-horizontal" action="${addproductboxUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">新增产品</h3>
			</div>
			<br>
			<div class="modal-body">				
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">产品名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入产品名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">产品编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入编码" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="specification">产品规格</label>
					<div class="controls">
						<input type="text" class="span3" name="specification" id="specification" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="unit">单位</label>
					<div class="controls">
						<select id="unit" name="unit" class="form-control">
							<c:forEach items="${units}" var="unit">
								<option value="${unit.id }">${unit.name }</option>
							</c:forEach>
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="type">肥料类型</label>
					<div class="controls">
						<select id="type" name="type" class="form-control">
							<c:forEach items="${types}" var="type">
								<option value="${type.id }">${type.name }</option>
							</c:forEach>
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="netcontent">单位产品净量</label>
					<div class="controls">
						<input type="text" class="span3" name="netcontent" id="netcontent" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="piececount">每件单位数量</label>
					<div class="controls">
						<input type="text" class="span3" name="piececount" id="piececount" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="piececontent">每件产品净量</label>
					<div class="controls">
						<input type="text" class="span3" name="piececontent" id="piececontent" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="kg">每件产品毛重</label>
					<div class="controls">
						<input type="text" class="span3" name="kg" id="kg" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
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
<script type="text/javascript">
$(document).ready(function() {
	addProductBoxFormValidate();
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

function addProductBoxFormValidate() {
	$("#addproductbox-form").validate(
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