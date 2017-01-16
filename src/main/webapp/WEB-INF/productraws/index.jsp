<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="原料管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/productraws/index"></c:url>
			    <li class="active"><a href="${listUrl }">原料信息</a></li>
			    <c:url var="priceUrl" value="/manager/productraws/price"></c:url>
			    <li><a href="${priceUrl }">原料价格</a></li>
			    <c:url var="storeUrl" value="/manager/productraws/store"></c:url>
			    <li><a href="${storeUrl }">原料库存</a></li>
			    <c:url var="inUrl" value="/manager/productraws/in"></c:url>
			    <li><a href="${inUrl }">原料入库</a></li>
			    <c:url var="outUrl" value="/manager/productraws/out"></c:url>
			    <li><a href="${outUrl }">原料出库</a></li>
			</ul>  
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>原料列表</h3>
                    <div class="span10 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入原料名称"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
						<c:url var="addemUrl" value="/manager/productraws/new" />
                        <a class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
                            <span>&#43;</span>
                            新增原料
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
                                    编码
                                </th>
                                <th class="span1 sortable">
                                    单位
                                </th>
                                <th class="span5 sortable">
                                    说明
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:set var="i" value="0" />
                        <c:forEach items="${productraws}" var="productraw">
                        	<tr>
                        		<td>
                        			<c:url var ="proUrl" value = "/manager/productraws/profile?id=${productraw.id }"></c:url>
	                                <a href="${proUrl }" class="name">${productraw.name }</a>
	                            </td>
	                            <td>
	                                ${productraw.code }
	                            </td>
	                            <td>
	                                ${productraw.unit.name }
	                            </td>
	                            <td>
	                                ${productraw.description }
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
    <div class="modal hide fade" id="addOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="addproductrawUrl" value="addone" />
		<form id="addproductraw-form" class="form-horizontal" action="${addproductrawUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">新增原料</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">原料名称</label>
					<div class="controls">
						<input type="text" class="span3" name="name" id="name" placeholder="输入原料名称" 
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">原料编码</label>
					<div class="controls">
						<input type="text" class="span3" name="code" id="code" placeholder="输入编码" 
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
					<label class="control-label" for="description">说明</label>
					<div class="controls">
						<textarea rows="5" path="description" name='description' id="description" class="span3" placeholder="简要说明，50字以内" maxlength="50"></textarea>
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
<script type="text/javascript">
$(document).ready(function() {
	addPositionFormValidate();
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

function addPositionFormValidate() {
	$("#addproductraw-form").validate(
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
</script>

<jsp:include page="../includes/footer.jsp" />