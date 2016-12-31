<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.chooseLableType {
	cursor: pointer;
}

.list-inline {
  /* padding-left: 0;
  list-style: none; */
}

.list-inline > li {
  /* display: inline-block;
  padding-right: 5px;
  padding-left: 5px; */
}

.list-inline > li:first-child {
 /*  padding-left: 0; */
}
-->
</style>
<c:set var="pageTitle" value="区域管理" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
<c:url var="cssstyle" value="/assets/css/style.css" />
<link rel="stylesheet" href="${cssstyle }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header boxbottomline">
                    <h3>行政区域</h3>
                    <div class="span10 pull-right">
                        <a href="javascript:popwindow()" class="btn-flat success pull-right">
                            <span>&#43;</span>
                            新增省份
                        </a>
                    </div>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="tree well span12"> <!-- span9 with-sidebar bio -->
                        <ul class="doctypeUl">
							<c:forEach var="node" items="${districts}">
								<li class="parent_li">
									<span id="${node.id}" sname="${node.name}" title="Expand this branch" style="color: #0E6390;font-weight: bold;"><i class="icon-folder-open icon-plus-sign"></i>${node.name}</span>
									<c:if test="${node.childs!=null}">
									<ul>
										<c:forEach var="node1" items="${node.childs}">
											<li  class="parent_li" style="display:none;">
												<span id="${node1.id}" sname="${node1.name}" title="Collapse this branch" style="color: #428BCA; font-weight: bold;">
														<i class="icon-plus-sign"></i>${node1.name}</span>
												<c:if test="${node1.childs!=null}">
												<ul>
													<c:forEach var="node2" items="${node1.childs}">
														<li  class="parent_li" style="line-height: 15px;display:none;">															
															<span id="${node2.id}" title="Collapse this branch" sname="${node2.name}" style="color: #D9534F;">
																	<i class="icon-plus-sign"></i>${node2.name}</span>
															<c:if test="${node2.childs!=null}">
															<ul class="list-inline">
																<c:forEach var="node3" items="${node2.childs}">
																	<li style="line-height: 15px;display:none;">
																		
																		<span id="${node3.id}" sname="${node3.name}" style="color: #5CB85C;">
																				<i class="icon-leaf"></i>${node3.name}</span>
																	</li>
																</c:forEach>
															</ul>
															</c:if>
														</li>
													</c:forEach>
												</ul>
												</c:if>
											</li>
										</c:forEach>
									</ul>
									</c:if>
								</li>
							</c:forEach>
						</ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    
    <div class="modal hide fade" id="add-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="addUrl" value="addone" />
		<form id="add-form" class="form-horizontal" action="${addUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>区域编辑</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
				<label class="control-label" for="code">选中的区域：</label>
				<div class="input-append">
					<input id="parentarea" name="parentarea" path="parentarea" cssClass="input-xlarge" style="line-height:28px;" />
					<button id="deleteBtn" class="btn btn-info" type="button" onclick="deleteOne()">删除</button>
					<input id="parentid" name="parentid" type="hidden"/>
				</div>
				</div>
				<div class="control-group">
				<label class="control-label" for="code">添加新的区域：</label>
				<div class="input-append">
					<input id="area" name="area" path="area" cssClass="input-xlarge" style="line-height:28px;" />
					<input id="saveform" type="submit" class="btn btn-info" value="保存" />
					<span class="help-inline"></span>
				</div>
				</div>
			</fieldset>
		</form>
	</div>
	
<%-- <c:url var="jsUrl" value="/assets/js/jquery-1.7.2.min.js" />
<script type="text/javascript" src="${jsUrl}"></script> --%>
<script type="text/javascript">
	var timer = null; 
	
	$(document)
	.ready(function() {
		/* $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');		
		var child = $('.tree li.parent_li > span').parent('li.parent_li').find(' > ul > li');
		if (child.is(":visible")) {
			child.hide('fast');
            $('.tree li.parent_li > span').attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        }  */
		
	    $('.tree li.parent_li > span').on('click', function (e) {
	    	var my = $(this);    	
	    	clearTimeout(timer); 
	    	timer = setTimeout(function () { //在单击事件中添加一个setTimeout()函数，设置单击事件触发的时间间隔 
	    		if(my.parent('li').hasClass("parent_li")){
		    		var children = my.parent('li.parent_li').find(' > ul > li');
			        if (children.is(":visible")) {
			            children.hide('fast');
			            my.attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
			        } else {
			            children.show('fast');
			            my.attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
			        }
			        e.stopPropagation();
		    	} 
	    	}, 300);  
	        
	    });
	    $('.tree li > span').dblclick(function(){
	    	clearTimeout(timer); 
	    	$("#parentarea").val($(this).attr('sname'));
			$("#parentid").val($(this).attr('id'));
			$('#add-modal').modal('show');
	    });
		
		/* $('.chooseLableType')
				.bind(
						'click',
						function() {
							try {

								$("#parentarea").val($(this).attr('sname'));
								$("#parentid").val($(this).attr('id'));
							} catch (e) {
								alert('请覆盖方法 clickDocType(id, text) -('
										+ $(this).attr('id') + ':'
										+ $.trim($(this).text()) + ')');
							}

						}); */
		//addFormValidate();
	});
	/* function addFormValidate() {
		$("#areaform").validate({
			debug : true,
			rules : {
				area : {
					required : true
				}
			},

			messages : {
				area : {
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
	}  */
	function deleteOne() {
		var id = $('#parentid').val();
		if(id==""){
			alert("请选择要删除的区域");
			return;
		}
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
	
	function popwindow(){
		$("#parentarea").val("");
		$("#parentid").val("");
		$('#add-modal').modal('show');
	}
</script>

<jsp:include page="../includes/footer.jsp" />