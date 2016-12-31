<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="出差报销单" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">     
        <div class="container-fluid">
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="listUrl" value="/manager/travels/checkpending"></c:url>
			    <li class="active"><a href="${listUrl }">待审核的出差报销单</a></li>
			    <c:url var="priceUrl" value="/manager/travels/checked"></c:url>
			    <li><a href="${priceUrl }">已审核的出差报销单</a></li>
			</ul>  
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>待审核的出差报销单</h3>
                    <div class="span9 pull-right">
                        <input id="searchtext" type="text" class="span5 search" placeholder="输入员工姓名"  onkeydown="onKeyDown(event)"/>
                        <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>
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
                                <th class="span2 sortable">
                                    操作
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
	                            <td rowspan="${rbsm.count }">
	                            	<c:if test="${rbsm.checked==0 }">
	                            		<button class="btn btn-mini btn-success" type="button" onclick="passOne(${rbsm.id})">批准</button>
		                            	<button class="btn btn-mini btn-danger" type="button" onclick="denyOne(${rbsm.id})">拒绝</button>
		                            </c:if>
		                            <c:if test="${rbsm.checked==1 && rbsm.paystatus==0 }">
		                            	<button class="btn btn-mini btn-info" type="button" onclick="payOne(${rbsm.id})">完成</button>
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
    <div class="modal hide fade" id="deny-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="denyUrl" value="denytravel" />
		<form id="deny-form" class="form-horizontal" action="${denyUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">拒绝理由</h3>
			</div>
			<br>
			<div class="modal-body">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="description">说明</label>
					<div class="controls">
						<textarea rows="3" path="remark" name='remark' id="remark" class="span3" placeholder="简要说明，50字以内" maxlength="50"></textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="hidden" id="tid" path="tid" name="tid"  >
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
			</div>
		</form>
	</div>
    
<script type="text/javascript">
$(document).ready(function() {
	addFormValidate();
	$("#searchtext").val(getUrlParam("context"));
	 
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
		hrefFormer : 'checkpending',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			var text = $("#searchtext").val();
			return this.hrefFormer + "?context="+ encodeURIComponent(text) + "&page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function addFormValidate() {
	$("#deny-form").validate(
			{
				debug : true,
				rules : {
					remark : {
						required : true
					}
				},

				messages : {
					remark : {
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

function search(){
	var text = $("#searchtext").val();
		window.location.href = '<c:url value="checkpending" />?context='+encodeURIComponent(text);
}

function passOne(id) {
	bootbox.confirm("确定核查无误了吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="passtravel" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}

function denyOne(id) {
	/* bootbox.confirm("确定要拒绝吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="denytravel" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	}); */
	$("#tid").val(id);
	$('#deny-modal').modal('show');
}

function payOne(id) {
	bootbox.confirm("确定已经支付完成了吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="paytravel" />', {
			id : id
		}).done(function(data) {
			window.location.href = window.location.href;
		}).fail(function() {
		});
	});
}

</script>

<jsp:include page="../includes/footer.jsp" />