<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="入库方案" /></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <c:url var="cssUrl" value="/assets/css/bootstrap/bootstrap.css" />
    <link href="${cssUrl }" rel="stylesheet" />
    <c:url var="cssUrl2" value="/assets/css/bootstrap/bootstrap-responsive.css" />
    <link href="${cssUrl2 }" rel="stylesheet" />
    <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
    <link href="${cssUrl3 }" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <c:url var="cssUrl4" value="/assets/css/lib/jquery-ui-1.10.2.custom.css" />
    <link href="${cssUrl4 }" rel="stylesheet" type="text/css" />
    <c:url var="cssUrl5" value="/assets/css/lib/font-awesome.css" />
    <link href="${cssUrl5 }" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <c:url var="cssUrl6" value="/assets/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl6 }" />
    <c:url var="cssUrl7" value="/assets/css/elements.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl7 }" />
    <c:url var="cssUrl8" value="/assets/css/icons.css" />
    <link rel="stylesheet" type="text/css" href="${cssUrl8 }" /> 

    <c:url var="cssUrl9" value="/assets/css/compiled/newdealer.css" />
	<link rel="stylesheet" href="${cssUrl9 }" type="text/css" media="screen" />
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<c:url var="addschemeUrl" value="/manager/productboxes/addscheme"></c:url>
	<form id="addemp-form" class="form-horizontal" action="${addschemeUrl }" method="post">
        <fieldset>
        <div class="settings-wrapper" id="pad-wrapper">
			<div class="span3 avatar-box">
            
            </div>
            <div class="span6 personal-info">
                
                <h5 class="personal-title">${box.name }</h5>

                 <div class="field-box">
                     <label class="span2">每件单位数量:</label>
                     <input id=count name="count" class="span4 inline-input" type="text" value="" onkeyup="clearNoNum(this)"
                     		placeholder=""/>
                     <span class="help-inline"></span>
                     <input id="box.id" name="box.id" class="span4 inline-input" type="hidden" value="${box.id }"/>
                 </div>
                 <div class="field-box span6">
                     <label>所需原料:</label>
                     <a class="icon pull-right" href="#rawModal" data-toggle="modal">
	                    <i class="icon-plus"></i>添加
	                 </a>
                     <!-- recent orders table -->
                     <table id="rawstable" class="table table-bordered span6">
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
                     <label>所需包材:</label>
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
                     <label>所需宣传品:</label>
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
         <div class="span3 avatar-box">
            
         </div>
     </div>
     </fieldset>
     </form>
     
     <div class="modal fade" id="rawModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						选择原料
					</h4>
				</div>
				<div class="modal-body" id="treeChooseRawBoxId">
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
		aria-labelledby="myModalLabel2" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel2">
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
		aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel3">
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
<script type="text/javascript">
$(document).ready(
		function() {
			$('#treeChooseRawBoxId').load('<c:url value="/manager/productraws/selectraws" />');
			$('#treeChoosePackBoxId').load('<c:url value="/manager/productpacks/selectpacks" />');
			$('#treeChoosePageBoxId').load('<c:url value="/manager/productpages/selectpages" />');
		});

function clickRawNote(id,name,unit){
	var table1 = $('#rawstable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append(name+"("+unit+")");
	var hidden = $("<input id='schemeRaws["+firstTr.length+"].raw.id' name='schemeRaws["+firstTr.length+"].raw.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='schemeRaws["+firstTr.length+"].count' name='schemeRaws["+firstTr.length+"].count' class='span3 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") );
	row.append(td1);
	var td2=$("<td></td>");
	td2.append($("<a id='raws"+firstTr.length+"' class='icon pull-right' href='javascript:delrow("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row);  
	$('#rawModal').modal('hide');
}
function delrow(el){
	$("#raws"+el).parent().parent().remove();
}

function clickPackNote(id,name){
	var table1 = $('#packstable'); 
	var firstTr = table1.find('tbody>tr'); 
	var row;
	row = $("<tr></tr>");
	var td = $("<td></td>"); 
	td.append(name);
	var hidden = $("<input id='schemePacks["+firstTr.length+"].pack.id' name='schemePacks["+firstTr.length+"].pack.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='schemePacks["+firstTr.length+"].count' name='schemePacks["+firstTr.length+"].count' class='span4 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
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
	var hidden = $("<input id='schemePages["+firstTr.length+"].page.id' name='schemePages["+firstTr.length+"].page.id' type='hidden' value='"+id+"'/>");
	td.append(hidden);
	row.append(td);
	var td1 = $("<td></td>"); 
	td1.append($("<input id='schemePages["+firstTr.length+"].count' name='schemePages["+firstTr.length+"].count' class='span4 inline-input' type='text' value='' onkeyup='clearNoNum(this)'/>") ); 
	row.append(td1);
	var td2=$("<td></td>");
	td2.append($("<a id='pages"+firstTr.length+"' class='icon pull-right' href='javascript:delpage("+firstTr.length+")'><i class='icon-remove'></i>删除</a>"));
	row.append(td2);	 
	table1.append(row); 
	$('#pageModal').modal('hide');
}
function delpage(el){
	$("#pages"+el).parent().parent().remove();
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
</body>
</html>