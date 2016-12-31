<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="用工管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>用工管理</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入工人姓名查找" onkeydown="onKeyDown(event)" />
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
                        
                        <c:url var="addUrl" value="/manager/labortimes/new" />
                        <a class="btn btn-flat success pull-right" href="${addUrl }">
                            <span>&#43;</span>
                            新增
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
                                <th class="span2 sortable">
                                    工人
                                </th>
                                <th class="span2 sortable">
                                    基本工资
                                </th>
                                <th class="span1 sortable">
                                    加班工资
                                </th>
                                <th class="span2 sortable">
                                    支付工资
                                </th>
                                <th class="span1 sortable">
                                    奖金
                                </th>
                                <th class="span2 sortable">
                                    工作日期
                                </th>
                                <th class="span2 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:forEach items="${times}" var="time">
                        	<tr>
                        		<td>
                        			<c:url var="laborUrl" value="/manager/productlabors/profile?id=${time.labor.id }"></c:url>
	                                <a href="${laborUrl }" class="name">${time.labor.name }</a>
	                            </td>
	                            <td>
	                            	${time.workhours }
	                            </td>
	                            <td>
	                                ${time.overhours }
	                            </td>
	                            <td>
	                            	${time.pay }
	                            </td>
	                            <td>
	                            	${time.bonus }
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${time.date }'
													type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td>
	                            	<c:set var="over" value="0"/>
	                            	<c:if test="${time.overhours!=null }">
	                            		<c:set var="over" value="${time.overhours }"/>
	                            	</c:if>
	                                <a id="editdept" class="btn btn-mini btn-warning"  href="javascript:edit(${time.id},'${time.labor.name }',${time.workhours },${over },${time.pay },${time.bonus },'<fmt:formatDate value='${time.date }'
													type='date' pattern='yyyy-MM-dd' />');">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${time.id})">删除</button></td>
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
    <div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editUrl" value="editone" />
		<form id="edit-form" class="form-horizontal" action="${editUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="edittile"></h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="code">基本工资</label>
					<div class="controls">
						<input type="text" class="span3" name="workhours" id="workhours" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
						<input type="hidden" id="id" name="id"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">加班工资</label>
					<div class="controls">
						<input type="text" class="span3" name="overhours" id="overhours" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">支付工资</label>
					<div class="controls">
						<input type="text" class="span3" name="pay" id="pay" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="bonus">奖金</label>
					<div class="controls">
						<input type="text" class="span3" name="bonus" id="bonus" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="starttime">工作日期</label>
					<div class="controls">
						<input type="text" class="span3" name="date" id="date" data-date-format="yyyy-mm-dd" />
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="edit-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
    <!-- end main container -->
<c:url var="cssUrl1" value="/assets/css/bootstrap/bootstrap-datetimepicker.min.css" />
<link href="${cssUrl1}" rel="stylesheet" media="screen">
<c:url var="jsUrl" value="/assets/uploadify-v3.1/jquery.uploadify-3.1.min.js" />
<script type="text/javascript" src="${jsUrl}"></script>
<c:url var="jsUrl1" value="/assets/js/moment.js" />
<script type="text/javascript" src="${jsUrl1 }"></script>
<c:url var="jsUrl2" value="/assets/js/bootstrap-datetimepicker.js" />
<script type="text/javascript" src="${jsUrl2 }" charset="UTF-8"></script>
<c:url var="jsUrl3" value="/assets/js/bootstrap-datetimepicker.zh-CN.js" />
<script type="text/javascript" src="${jsUrl3 }" charset="UTF-8"></script>    
<script type="text/javascript">
$(document).ready(function() {
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
    
    addFormValidate();
    $('#date').datetimepicker({
		language : 'zh-CN',
		minView: "month",
		autoclose:true
	});
});

function search(){
	var text = $("#searchtext").val();
		window.location.href = '<c:url value="index" />?context='+encodeURIComponent(text);
}


function edit(id,name,workhours,overhours,pay,bonus,date){
	$('#id').val(id);
	$('#edittile').html(name);
	$("#workhours").val(workhours);
	if(overhours!=0){
		$("#overhours").val(overhours);
	}
	$("#pay").val(pay);
	if(bonus!=0){
		$("#bonus").val(bonus);
	}
	$("#date").val(date);
	$('#editOne-modal').modal('show');
}

function addFormValidate() {
	$("#edit-form").validate({
		debug : true,
		rules : {
			workhours : {
				required : true
			},
			pay : {
				required : true
			},
			date : {
				required : true
			}
		},
		
		messages : {
			workhours : {
				required : "必填"
			},
			pay : {
				required : "必填"
			},
			date : {
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

function deleteOne(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}
</script>

<jsp:include page="../includes/footer.jsp" />