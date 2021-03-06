<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="进货让利政策表" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>进货让利政策表</h3>
                    <div class="span9 pull-right">
                        <a class="btn btn-flat success pull-right" href="#addOne-modal" data-toggle="modal">
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
                                <th class="span3 sortable">
                                    进货额(万元)
                                </th>
                                <th class="span3 sortable">
                                    返利比例
                                </th>
                                <th class="span3 sortable">
                                    更新日期
                                </th>
                                <th class="span3 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:forEach items="${dics}" var="dic">
                        	<tr>
                        		<td>
                        			${dic.purchases }
	                            </td>
	                            <td>
	                            	${dic.rebate }
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${dic.date }'
													type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td>
	                            	<a id="editdept" class="btn btn-mini btn-warning"  href="javascript:edit(${dic.id },${dic.purchases },${dic.rebate })">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${dic.id})">删除</button></td>
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
		<c:url var="addcreditUrl" value="addone" />
		<form id="addcredit-form" class="form-horizontal" action="${addcreditUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">新增进货让利</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="code">进货额(万元)</label>
					<div class="controls">
						<input type="text" class="span3" name="purchases" id="purchases" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">返利比例</label>
					<div class="controls">
						<input type="text" class="span3" name="rebate" id="rebate" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="recipe-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal hide fade" id="editOne-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editcreditUrl" value="editone" />
		<form id="editcredit-form" class="form-horizontal" action="${editcreditUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑进货让利</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="code">进货额(万元)</label>
					<div class="controls">
						<input type="text" class="span3" name="epurchases" id="epurchases" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
						<input type="hidden" id="eid" name="eid"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">返利比例</label>
					<div class="controls">
						<input type="text" class="span3" name="erebate" id="erebate" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
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
<script type="text/javascript">
$(document).ready(function() {
	addFormValidate();
	editFormValidate();
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
			return this.hrefFormer + "?page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function addFormValidate() {
	$("#addcredit-form").validate({
		debug : true,
		rules : {
			purchases : {
				required : true
			},
			rebate : {
				required : true
			}
		},
		
		messages : {
			purchases : {
				required : "必填"
			},
			rebate : {
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

function editFormValidate() {
	$("#editcredit-form").validate({
		debug : true,
		rules : {
			epurchases : {
				required : true
			},
			erebate : {
				required : true
			}
		},
		
		messages : {
			epurchases : {
				required : "必填"
			},
			erebate : {
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

function edit(id,purchases,rebate){
	$('#eid').val(id);
	$("#epurchases").val(purchases);
	$("#erebate").val(rebate);
	$('#editOne-modal').modal('show');
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