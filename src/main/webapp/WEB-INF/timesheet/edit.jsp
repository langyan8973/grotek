<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="员工项目工作编辑" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="workcontent">     
        <div class="container-fluid">   
            <c:url var="addUrl" value="editone" />
            <form id="weekly" class="form-horizontal" action="${addUrl }" method="post">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
	            	<div class="span12 personal-info">
	                    <h5 class="personal-title">周项目工作</h5>
	                        <div class="field-box span12">
			                     <label style="margin-left:30px;">时间段：<fmt:formatDate value='${weekly.stime }' type='date' pattern='yyyy/MM/dd' />--<fmt:formatDate value='${weekly.etime }' type='date' pattern='yyyy/MM/dd' />
			                     </label>
			                     <a class="icon pull-right" href="javascript:clickaddone();">
				                    <i class="icon-plus"></i>添加
				                 </a>
				                 <input id='id' name='id' type='hidden' value='${weekly.id }'/>
			                     <!-- recent orders table -->
			                     <table id="itemtable" class="table table-bordered span12">
			                         <thead>
			                             <tr>
			                                 <th class="span2">
			                                     客户
			                                 </th>
			                                 <th class="span1">
			                                     工作类型
			                                 </th>
			                                 <th class="span2">
			                                     工作内容
			                                 </th>
			                                 <th class="span1">
			                                     地点
			                                 </th>
			                                 <th>
			                                 	星期一
			                                 </th>
			                                 <th>
			                                 	星期二
			                                 </th>
			                                 <th>
			                                 	星期三
			                                 </th>
			                                 <th>
			                                 	星期四
			                                 </th>
			                                 <th>
			                                 	星期五
			                                 </th>
			                                 <th>
			                                 	星期六
			                                 </th>
			                                 <th>
			                                 	星期日
			                                 </th>
			                                 <th>
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                         	 <c:set var="i" value="0"></c:set>
			                             <c:forEach items="${weekly.items}" var="item">
					                        <tr>
				                        		<td>
				                        			<input id='dname${i }' name='items[${i }].dealer.name' type='text' class='span2 inline-input' value='${item.dealer.name }' onclick='dealerclick(${i})'/>
				                        			<input id='did${i }' name='items[${i }].dealer.id' type='hidden' value='${item.dealer.id }'/>
				                        		</td>
				                        		<td>
				                        			<input id='tname${i }' name='items[${i }].type.name' type='text' class='span1 inline-input' value='${item.type.name }' onclick='typeclick(${i})'/>
				                        			<input id='tid${i }' name='items[${i }].type.id' type='hidden' value='${item.type.id }'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].content' name='items[${i }].content' type='text' class='span2 inline-input' value='${item.content }'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].place' name='items[${i }].place' type='text' class='span1 inline-input' value='${item.place }'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].mon' name='items[${i }].mon' type='text' class='inline-input mininput' value='${item.mon }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].tue' name='items[${i }].tue' type='text' class='inline-input mininput' value='${item.tue }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].wed' name='items[${i }].wed' type='text' class='inline-input mininput' value='${item.wed }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].thu' name='items[${i }].thu' type='text' class='inline-input mininput' value='${item.thu }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].fri' name='items[${i }].fri' type='text' class='inline-input mininput' value='${item.fri }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].sat' name='items[${i }].sat' type='text' class='inline-input mininput' value='${item.sat }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<input id='items[${i }].sun' name='items[${i }].sun' type='text' class='inline-input mininput' value='${item.sun }' onkeyup='clearNoNum(this)'/>
				                        		</td>
				                        		<td>
				                        			<a id='items${i }' class='icon' href='javascript:delitem(${i })'><i class='icon-remove'></i>删除</a>
				                        		</td>
					                        </tr>
					                        <c:set var="i" value="${i+1 }"></c:set>
				                        </c:forEach>
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
							选择工作类型
						</h4>
					</div>
					<div class="modal-body" id="treeChoosetypeBoxId">
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
var di;
var ti;
$(document).ready(
		function() {
			$('#treeChoosedealerBoxId').load('<c:url value="/work/selects/dealers" />');
			$('#treeChoosetypeBoxId').load('<c:url value="/work/selects/jobtypes" />');
});

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

function clickaddone(){
	var table1 = $('#itemtable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	var dname = $("<input id='dname"+firstTr.length+"' name='items["+firstTr.length+"].dealer.name' type='text' class='span2 inline-input' value='' onclick='dealerclick("+firstTr.length+")'/>");
	td.append(dname);
	var did = $("<input id='did"+firstTr.length+"' name='items["+firstTr.length+"].dealer.id' type='hidden' value=''/>");
	td.append(did);
	row.append(td);
	var td0 = $("<td></td>"); 
	var tname = $("<input id='tname"+firstTr.length+"' name='items["+firstTr.length+"].type.name' type='text' class='span1 inline-input' value='' onclick='typeclick("+firstTr.length+")'/>");
	td0.append(tname);
	var tid = $("<input id='tid"+firstTr.length+"' name='items["+firstTr.length+"].type.id' type='hidden' value=''/>");
	td0.append(tid);
	row.append(td0);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='items["+firstTr.length+"].content' name='items["+firstTr.length+"].content' type='text' class='span2 inline-input' value=''/>") ); 
	row.append(td1);
	var td11 = $("<td></td>"); 
	td11.append($("<input id='items["+firstTr.length+"].place' name='items["+firstTr.length+"].place' type='text' class='span1 inline-input' value=''/>") ); 
	row.append(td11);
	var td2 = $("<td></td>"); 
	td2.append($("<input id='items["+firstTr.length+"].mon' name='items["+firstTr.length+"].mon' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td2);
	var td3 = $("<td></td>"); 
	td3.append($("<input id='items["+firstTr.length+"].tue' name='items["+firstTr.length+"].tue' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td3);
	var td4 = $("<td></td>"); 
	td4.append($("<input id='items["+firstTr.length+"].wed' name='items["+firstTr.length+"].wed' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td4);
	var td5 = $("<td></td>"); 
	td5.append($("<input id='items["+firstTr.length+"].thu' name='items["+firstTr.length+"].thu' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td5);
	var td6 = $("<td></td>"); 
	td6.append($("<input id='items["+firstTr.length+"].fri' name='items["+firstTr.length+"].fri' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td6);
	var td7 = $("<td></td>"); 
	td7.append($("<input id='items["+firstTr.length+"].sat' name='items["+firstTr.length+"].sat' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td7);
	var td8 = $("<td></td>"); 
	td8.append($("<input id='items["+firstTr.length+"].sun' name='items["+firstTr.length+"].sun' class='inline-input mininput' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td8);
	var td9 = $("<td></td>"); 
	td9.append($("<a id='items"+firstTr.length+"' class='icon' href='javascript:delitem("+firstTr.length+")'><i class='icon-remove'></i>删除</a>") ); 
	row.append(td9);
	table1.append(row); 
}

function dealerclick(i){
	di=i;
	$('#dealerModal').modal( {
		keyboard : false
	});
}

function typeclick(i){
	ti=i;
	$('#typeModal').modal( {
		keyboard : false
	});
}
function clickDocType(id,sname) {
	$('#dname'+di).val(sname);
	$('#did'+di).val(id);
	$('#dealerModal').modal('hide');

} 

function clickJobNote(id,sname) {
	$('#tname'+ti).val(sname);
	$('#tid'+ti).val(id);
	$('#typeModal').modal('hide');

} 

function delitem(el){
	$("#items"+el).parent().parent().remove();
}

</script>

<jsp:include page="../includes/footer.jsp" />