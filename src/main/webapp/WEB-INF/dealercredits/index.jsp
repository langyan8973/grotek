<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="政策支持表" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container -->
    <div class="content">       
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>政策支持表</h3>
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
                                <th class="span2 sortable">
                                    经销商类型
                                </th>
                                <th class="span2 sortable">
                                    完成目标(万元)
                                </th>
                                <th class="span1 sortable">
                                    年终返利比例
                                </th>
                                <th class="span1 sortable">
                                    信用额度(万元)
                                </th>
                                <th class="span3 sortable">
                                    说明
                                </th>
                                <th class="span1 sortable">
                                    时间
                                </th>
                                <th class="span2 sortable">
                                    操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <c:forEach items="${credits}" var="credit">
                        	<tr>
                        		<td>
                        			${credit.type.name }
	                            </td>
	                            <td>
	                            	${credit.objective }
	                            </td>
	                            <td>
	                                ${credit.rebate }
	                            </td>
	                            <td>
	                            	${credit.credit }
	                            </td>
	                            <td>
	                                ${credit.description }
	                            </td>
	                            <td>
	                                <fmt:formatDate value='${credit.date }'
													type='date' pattern='yyyy-MM-dd' />
	                            </td>
	                            <td>
	                            	<a id="editdept" class="btn btn-mini btn-warning"  href="javascript:edit(${credit.id },'${credit.objective }',${credit.rebate },${credit.credit },'${credit.description }')">
										编辑
									</a>
									<button class="btn btn-mini btn-danger" type="button" onclick="deleteOne(${credit.id})">删除</button></td>
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
				<h3 id="myModalLabel">新增政策支持</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="type">经销商类型</label>
					<div class="controls">
						<select id="type" name="type" class="form-control span3">
							<c:forEach items="${types}" var="type">
								<option value="${type.id }">${type.name }</option>
							</c:forEach>
						</select>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="name">完成目标(万元)</label>
					<div class="controls">
						<input type="text" class="span3" name="objective" id="objective" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">年终返利比例</label>
					<div class="controls">
						<input type="text" class="span3" name="rebate" id="rebate" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">信用额度(万元)</label>
					<div class="controls">
						<input type="text" class="span3" name="credit" id="credit" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">说明</label>
					<div class="controls">
						<textarea rows="5" path="description" name='description' id="description" class="span3" placeholder="" maxlength="50"></textarea>
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
				<h3>编辑政策支持</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">完成目标</label>
					<div class="controls">
						<input type="text" class="span3" name="eobjective" id="eobjective" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" />
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">年终返利比例</label>
					<div class="controls">
						<input type="text" class="span3" name="erebate" id="erebate" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">信用额度</label>
					<div class="controls">
						<input type="text" class="span3" name="ecredit" id="ecredit" onkeyup="clearNoNum(this)"
						style="IME-MODE: disabled;"  maxlength="20"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="code">说明</label>
					<div class="controls">
						<textarea rows="5" path="edescription" name='edescription' id="edescription" class="span3" placeholder="" maxlength="50"></textarea>
                     	<span class="help-inline"></span>
					</div>
					<input type="hidden" id="eid" name="eid" />
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
		
	});});


function addFormValidate() {
	$("#addcredit-form").validate({
		debug : true,
		rules : {
			objective : {
				required : true
			},
			credit : {
				required : true
			},
			rebate : {
				required : true
			}
		},
		
		messages : {
			objective : {
				required : "必填"
			},
			credit : {
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
			eobjective : {
				required : true
			},
			ecredit : {
				required : true
			},
			erebate : {
				required : true
			}
		},
		
		messages : {
			eobjective : {
				required : "必填"
			},
			ecredit : {
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

function edit(id,objective,rebate,credit,description){
	$('#eid').val(id);
	$("#eobjective").val(objective);
	$("#erebate").val(rebate);
	$("#ecredit").val(credit);
	$("#edescription").val(description);
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