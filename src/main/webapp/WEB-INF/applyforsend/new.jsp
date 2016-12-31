<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="新增发货申请" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrl" value="/assets/css/compiled/newdealer.css" />
<link rel="stylesheet" href="${cssUrl }" type="text/css" media="screen" />
	<!-- main container -->
    <div class="workcontent">     
        <div class="container-fluid">   
            <c:url var="addUrl" value="/work/applyforsend/addone" />
            <form id="applyforsend" action="${addUrl }" method="post">
	            <fieldset>
	            <div class="settings-wrapper" id="pad-wrapper">
	            	<div class="span3 avatar-box">
	                
	                </div>
	                <div class="span8 personal-info">
	                    <h5 class="personal-title">发货申请</h5>

	                        <div class="field-box">
	                            <label>客户:</label>
	                            <input id="dname" name="dealer.name" class="span5" type="text" value="" />
	                            <span class="help-inline"></span>
	                            <input id="did" name="dealer.id" class="span5" type="hidden" value=""/>
	                        </div>
	                        <div class="field-box">
	                            <label>送货地址:</label>
	                            <input id="address" name="address" class="span5" type="text" value="" />
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>联系人:</label>
	                            <input id="contact" name="contact" class="span5" type="text" value=""/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box">
	                            <label>联系电话:</label>
	                            <input id="phone" name="phone" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
	                            <span class="help-inline"></span>
	                        </div>
	                        <div class="field-box span6">
			                     <label>产品:</label>
			                     <a class="icon pull-right" href="#boxModal" data-toggle="modal">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="boxtable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                                 <th class="span1">
			                                     名称
			                                 </th>
			                                 <th class="span1">
			                                     规格
			                                 </th>
			                                 <th class="span1">
			                                     单价
			                                 </th>
			                                 <th class="span1">
			                                     数量
			                                 </th>
			                                 <th class="span1">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                             
			                         </tbody>
			                     </table>                            
			                 </div>
			                 <div class="field-box span6">
			                     <label>附带样品:</label>
			                     <a class="icon pull-right" href="#sampleModal" data-toggle="modal">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="samplestable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                                 <th class="span2">
			                                     名称
			                                 </th>
			                                 <th class="span2">
			                                     规格
			                                 </th>
			                                 <th class="span1">
			                                     数量
			                                 </th>
			                                 <th class="span2">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                             
			                         </tbody>
			                     </table>                               
			                 </div>
			                 <div class="field-box span6">
			                     <label>附带包材:</label>
			                     <a class="icon pull-right" href="#packModal" data-toggle="modal">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="packstable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                                 <th class="span3">
			                                     名称
			                                 </th>
			                                 <th class="span2">
			                                     数量
			                                 </th>
			                                 <th class="span2">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                             
			                         </tbody>
			                     </table>                               
			                 </div>
			                 <div class="field-box span6">
			                     <label>附带宣传品:</label>
			                     <a class="icon pull-right" href="#pageModal" data-toggle="modal">
				                    <i class="icon-plus"></i>添加
				                 </a>
			                     <!-- recent orders table -->
			                     <table id="pagestable" class="table table-bordered span6">
			                         <thead>
			                             <tr>
			                                 <th class="span3">
			                                     名称
			                                 </th>
			                                 <th class="span2">
			                                     数量
			                                 </th>
			                                 <th class="span2">
			                                 </th>
			                             </tr>
			                         </thead>
			                         <tbody>
			                             
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
		
		<div class="modal fade" id="boxModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel3">
						选择产品
					</h4>
				</div>
				<div class="modal-body" id="treeChooseBoxBoxId">
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
	
	<div class="modal fade" id="sampleModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel10" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel10">
						选择样品
					</h4>
				</div>
				<div class="modal-body" id="treeChooseSampleBoxId">
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
	
	<div class="modal fade" id="packModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel4" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel4">
						选择包材
					</h4>
				</div>
				<div class="modal-body" id="treeChoosePackBoxId">
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
	
	<div class="modal fade" id="pageModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel5" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel5">
						选择宣传品
					</h4>
				</div>
				<div class="modal-body" id="treeChoosePageBoxId">
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
			$('#treeChooseBoxBoxId').load('<c:url value="/work/selects/boxes" />');
			$('#treeChooseSampleBoxId').load('<c:url value="/work/selects/samples" />');
			$('#treeChoosePackBoxId').load('<c:url value="/work/selects/packs" />');
			$('#treeChoosePageBoxId').load('<c:url value="/work/selects/pages" />');
			
			$('#date').datetimepicker({
				language : 'zh-CN',
				minView: "month",
				autoclose:true
			});
			
			addFormValidate(); 
		});

function addFormValidate() {
	$("#applyforsend").validate({
		debug : true,
		rules : {
			dname : {
				required : true
			},
			address : {
				required : true
			},
			contact : {
				required : true
			},
			phone : {
				required : true
			}
		},
		
		messages : {
			dname : {
				required : "必填"
			},
			address : {
				required : "必填"
			},
			contact : {
				required : "必填"
			},
			phone : {
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
function clearNoNum1(obj){
	obj.value=obj.value.replace(/[^\-?\d.]/g,'');
}
function clickDocType(id,sname) {
	$('#dname').val(sname);
	$('#did').val(id);
	$('#dealerModal').modal('hide');
	$.post('<c:url value="/work/selects/dealerinfo" />', {
		id : id
	}).done(function(data) {
		
		$('#address').val(data.address);
		$('#contact').val(data.contact);
		$('#phone').val(data.mobile);
		
	}).fail(function() {
	});

} 

function clickPackNote(id,name){
	var table1 = $('#packstable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append(name);
	var hidden = $("<input id='packitems["+firstTr.length+"].pack.id' name='packitems["+firstTr.length+"].pack.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='packitems["+firstTr.length+"].count' name='packitems["+firstTr.length+"].count' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td1);
	var td2=$("<td></td>");
	td2.append($("<a id='packs"+firstTr.length+"' class='icon pull-right' href='javascript:delpack("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row); 
	$('#packModal').modal('hide');
}
function delpack(el){
	$("#packs"+el).parent().parent().remove();
}

function clickPageNote(id,name){
	var table1 = $('#pagestable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append(name);
	var hidden = $("<input id='pageitems["+firstTr.length+"].page.id' name='pageitems["+firstTr.length+"].page.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='pageitems["+firstTr.length+"].count' name='pageitems["+firstTr.length+"].count' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td1);
	var td2=$("<td></td>");
	td2.append($("<a id='pages"+firstTr.length+"' class='icon pull-right' href='javascript:delpage("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row); 
	$('#pageModal').modal('hide');
}

function clickSampleNote(id,name,kg,spec){
	var did = $('#did').val();
	$('#sampleModal').modal('hide');
	if(did==null || did==""){		
		bootbox.alert("请先填写客户信息");
	}
	else{
		$.post('<c:url value="/work/selects/dealerprice" />', {
			did : did,
			bid : id
		}).done(function(data) {
			if(data==""){
				alert("获取价格失败，请管理员配置产品价格");
				return;
			}
			var table1 = $('#samplestable'); 
			var firstTr = table1.find('tbody>tr'); 
			var row;
			row = $("<tr></tr>");
			var td = $("<td></td>"); 
			td.append(name);
			var hidden = $("<input id='sampleitems["+firstTr.length+"].box.id' name='sampleitems["+firstTr.length+"].box.id' type='hidden' value='"+id+"'/>");
			td.append(hidden);
			row.append(td);
			
			var td00 = $("<td></td>"); 
			td00.append(spec);
			row.append(td00);
			
			var td1 = $("<td></td>"); 
			td1.append($("<input id='sampleitems["+firstTr.length+"].count' name='sampleitems["+firstTr.length+"].count' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
			row.append(td1);
			var td2=$("<td></td>");
			td2.append($("<a id='samples"+firstTr.length+"' class='icon pull-right' href='javascript:delsample("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
			row.append(td2);	 
			table1.append(row); 
			$('#sampleModal').modal('hide');
			
		}).fail(function() {
		});
	}
	
}

function delpage(el){
	$("#pages"+el).parent().parent().remove();
}

function delsample(el){
	$("#samples"+el).parent().parent().remove();
}

function  clickBoxNote(id, text,kg,spec){
	var did = $('#did').val();
	$('#boxModal').modal('hide');
	if(did==null || did==""){
		
		bootbox.alert("请先填写客户信息");
	}
	else{
		$.post('<c:url value="/work/selects/dealerprice" />', {
			did : did,
			bid : id
		}).done(function(data) {
			if(data==""){
				alert("获取价格失败，请管理员配置产品价格");
				return;
			}
			var table1 = $('#boxtable'); 
			var firstTr = table1.find('tbody>tr'); 
			var row;
			row = $("<tr></tr>");
			var td = $("<td></td>"); 
			td.append(text);
			var hidden = $("<input id='boxitems["+firstTr.length+"].box.id' name='boxitems["+firstTr.length+"].box.id' type='hidden' value='"+id+"'/>");
			td.append(hidden);
			row.append(td);
			
			var td00 = $("<td></td>"); 
			td00.append(spec);
			row.append(td00);
			
			var td0 = $("<td></td>"); 
			td0.append($("<input id='boxitems["+firstTr.length+"].price' name='boxitems["+firstTr.length+"].price' class='span1 inline-input' type='text' value='"+data+"' onkeyup='clearNoNum(this)'/>") ); 
			td0.append($("<input id='boxitems["+firstTr.length+"].kg' name='boxitems["+firstTr.length+"].kg'  type='hidden' value='"+kg+"'/>") );
			row.append(td0);
			
			var td1 = $("<td></td>"); 
			td1.append($("<input id='boxitems["+firstTr.length+"].count' name='boxitems["+firstTr.length+"].count' class='span1 inline-input' type='text' value='' onkeyup='clearNoNum1(this)'/>") ); 
			row.append(td1);
			var td2=$("<td></td>");
			td2.append($("<a id='boxes"+firstTr.length+"' class='icon pull-right' href='javascript:delbox("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
			row.append(td2);	 
			table1.append(row); 
			
		}).fail(function() {
		});
	}
}

function delbox(el){
	$("#boxes"+el).parent().parent().remove();
}

</script>

<jsp:include page="../includes/footer.jsp" />