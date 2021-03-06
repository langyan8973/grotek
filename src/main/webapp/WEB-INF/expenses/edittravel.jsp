<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="编辑出差报销" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="workcontent">     
        <div class="container-fluid">   
        	<ul class="nav nav-tabs" style="margin-top:20px">
        		<c:url var="travelUrl" value="/work/expenses/travels"></c:url>
			    <li class="active"><a href="${travelUrl }">出差报销</a></li>
			    <c:url var="exsUrl" value="/work/expenses/exses"></c:url>
			    <li><a href="${exsUrl }">市场开拓费用报销</a></li>
			</ul> 
            <c:url var="addUrl" value="updatetravel" />
            <form id="travelrbsm" class="form-horizontal" action="${addUrl }" method="post">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
	            	<div class="span2 avatar-box">
	                
	                </div>
	                <div class="span8 personal-info">
	                    <h5 class="personal-title">出差报销单</h5>

	                        <div class="field-box">
	                            <label>客户:</label>
	                            <input id="dname" name="dealer.name" class="span5" type="text" value="${rbsm.dealer.name }" />
	                            <span class="help-inline"></span>
	                            <input id="did" name="dealer.id" class="span5" type="hidden" value="${rbsm.dealer.id }"/>
	                            <input id="id" name="id" class="span5" type="hidden" value="${rbsm.id }"/>
	                        </div>
	                        <div class="field-box">
	                            <label>工作类型:</label>
								<select class="span5" name="type.id" id="type.id">
									<c:forEach items="${types}" var="type">
										<c:choose>
										   <c:when test="${rbsm.type.id==type.id }">   
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
	                        </div>
	                        <div class="field-box">
	                            <label>开始时间:</label>
	                            <input id="stime" name="stime" class="span5 form_datetime" type="text" data-date-format="yyyy-mm-dd hh:ii"
	                             value="<fmt:formatDate value='${rbsm.stime }'
													type='date' pattern='yyyy-MM-dd HH:mm' />" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>结束时间时间:</label>
	                            <input id="etime" name="etime" class="span5 form_datetime" type="text" data-date-format="yyyy-mm-dd hh:ii" 
	                            value="<fmt:formatDate value='${rbsm.etime }'
													type='date' pattern='yyyy-MM-dd HH:mm' />" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>出发地:</label>
	                            <input id="splace" name="splace" class="span5" type="text" value="${rbsm.splace }" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>目的地:</label>
	                            <input id="eplace" name="eplace" class="span5" type="text" value="${rbsm.eplace }" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box span6">
			                     <label>报销内容:</label>
			                     <a class="icon pull-right" href="#typeModal" data-toggle="modal">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="contenttable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                                 <th class="span2">
			                                     项目
			                                 </th>
			                                 
			                                 <th class="span1">
			                                     金额
			                                 </th>
			                                 <th class="span2">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                         	<c:set var="i" value="0"></c:set>
			                            <c:forEach items="${rbsm.items }" var="item" varStatus="status" begin="0" end="${rbsm.count-1 }">
			                        	<tr>
			                            	<td>
				                                ${item.type.name }
				                                <input id='items[${i+100 }].type.id' name='items[${i+100 }].type.id' type='hidden' value='${item.type.id }'/>
				                            </td>
				                            
				                            <td>
				                            	<input id='items[${i+100 }].amount' name='items[${i+100 }].amount' class='span1 inline-input' type='text' value='${item.amount }' onkeyup='clearNoNum(this)' />
				                            </td>
				                            <td>
				                            	<a id='items${i+100 }' class='icon pull-right' href='javascript:delitem(${i+100 })'><i class='icon-remove'></i>删除</a>
				                            </td>
				                         </tr>
				                         <c:set var="i" value="${i+1 }"></c:set>
			                            </c:forEach>
			                         </tbody>
			                     </table>                            
			                 </div>
			                 <div class="field-box span6">
			                     <label>报销汽油费:</label>
			                     <a class="icon pull-right" href="javascript:addline();">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="gasotable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                             	<th class="span2">
			                                     日期
			                                 </th>
			                                 <th class="span1">
			                                     出发地
			                                 </th>
			                                 <th class="span1">
			                                     到达地
			                                 </th>
			                                 <th class="span1">
			                                     公里数
			                                 </th>
			                                 <th class="span1">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                         	<c:if test="${rbsm.ucount>0 }">
			                         	<c:set var="j" value="0"></c:set>
			                            <c:forEach items="${rbsm.usedCars }" var="item" varStatus="status" begin="0" end="${rbsm.ucount-1 }">
			                        	<tr>
			                            	<td>
				                                <input id='usedCars[${j+100 }].date' name='usedCars[${j+100 }].date' class='span2 inline-input mydate' type='text'
				                                	value="<fmt:formatDate value='${item.date }' type='date' pattern='yyyy-MM-dd' />"/>
				                            </td>
				                            <td>
				                            	<input id='usedCars[${j+100 }].splace' name='usedCars[${j+100 }].splace' class='span1 inline-input' type='text' value='${item.splace }'/>
				                            </td>
				                            <td>
				                            	<input id='usedCars[${j+100 }].eplace' name='usedCars[${j+100 }].eplace' class='span1 inline-input' type='text' value='${item.eplace }'/>
				                            </td>
				                            <td>
				                            	<input id='usedCars[${j+100 }].kmcount' name='usedCars[${j+100 }].kmcount' class='span1 inline-input' type='text' value='${item.kmcount }' onkeyup='clearNoNum(this)' />
				                            </td>
				                            <td>
				                            	<a id='usedCars${j+100 }' class='icon pull-right' href='javascript:delUsed(${j+100 })'><i class='icon-remove'></i>删除</a>
				                            </td>
				                         </tr>
				                         <c:set var="j" value="${j+1 }"></c:set>
			                            </c:forEach>
			                            </c:if>
			                         </tbody>
			                     </table>                            
			                 </div>
	                        <div class="span6 field-box actions">
	                            <input type="submit" class="btn-glow primary" value="保存" />
	                        </div>
	                </div>
	            </div>
	            </fieldset>
	            </form>
            </div>
        </div>
        <div class="modal fade" id="dealerModal" tabindex="-2" role="dialog"
			aria-labelledby="myModalLabel2" aria-hidden="true" style="display:none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel2">
							选择经销商
						</h4>
					</div>
					<div class="modal-body" id="treeChoosedealerBoxId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="typeModal" tabindex="-2" role="dialog"
			aria-labelledby="myModalLabel3" aria-hidden="true" style="display:none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel3">
							选择报销类型
						</h4>
					</div>
					<div class="modal-body" id="treeChooseTravelTypeId">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
    
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
$(document).ready(
		function() {
			
			$('#dname').bind('click', function() {
				$('#dealerModal').modal( {
					keyboard : false
				})
			});
			$('#treeChoosedealerBoxId').load('<c:url value="/work/selects/dealers" />');
			
			$('#treeChooseTravelTypeId').load('<c:url value="/work/selects/traveltypes" />');
			
			$('#stime').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
			$('#etime').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
			
			$('.mydate').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
			
			addFormValidate();
		});

function addFormValidate() {
	$("#travelrbsm").validate({
		debug : true,
		rules : {
			dname : {
				required : true
			},
			stime : {
				required : true
			},
			etime : {
				required : true
			},
			splace : {
				required : true
			},
			eplace : {
				required : true
			}
		},
		
		messages : {
			dname : {
				required : "必填"
			},
			stime : {
				required : "必填"
			},
			etime : {
				required : "必填"
			},
			splace : {
				required : "必填"
			},
			eplace : {
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
function clickDocType(id,sname) {
	$('#dname').val(sname);
	$('#did').val(id);
	$('#dealerModal').modal('hide');

} 
function clickTravelTypeNote(id,sname){
	var table1 = $('#contenttable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append(sname);
	var hidden = $("<input id='items["+firstTr.length+"].type.id' name='items["+firstTr.length+"].type.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	
	var td1 = $("<td></td>"); 
	td1.append($("<input id='items["+firstTr.length+"].amount' name='items["+firstTr.length+"].amount' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum(this)' />") ); 
	row.append(td1);
	var td2=$("<td></td>");
	td2.append($("<a id='items"+firstTr.length+"' class='icon pull-right' href='javascript:delitem("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row); 
	$('#typeModal').modal('hide');
}

function addline(){
	var table1 = $('#gasotable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append($("<input id='usedCars["+firstTr.length+"].date' name='usedCars["+firstTr.length+"].date' class='span2 inline-input' type='text'/>").datetimepicker({
		language : 'zh-CN',
		minView: "month",
		autoclose:true
	}) );
	row.append(td);
	var td0 = $("<td></td>"); 
	td0.append($("<input id='usedCars["+firstTr.length+"].splace' name='usedCars["+firstTr.length+"].splace' class='span1 inline-input' type='text'/>")); 
	row.append(td0);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='usedCars["+firstTr.length+"].eplace' name='usedCars["+firstTr.length+"].eplace' class='span1 inline-input' type='text'/>") ); 
	row.append(td1);
	var td11 = $("<td></td>"); 
	td11.append($("<input id='usedCars["+firstTr.length+"].kmcount' name='usedCars["+firstTr.length+"].kmcount' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum(this)' />") ); 
	row.append(td11);
	var td2=$("<td></td>");
	td2.append($("<a id='usedCars"+firstTr.length+"' class='icon pull-right' href='javascript:delUsed("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row); 
}

function delitem(el){
	$("#items"+el).parent().parent().remove();
}
function delUsed(el){
	$("#usedCars"+el).parent().parent().remove();
}
</script>

<jsp:include page="../includes/footer.jsp" />