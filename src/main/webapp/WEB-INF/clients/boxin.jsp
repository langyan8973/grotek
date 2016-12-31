<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="经销商进货情况" /></title>
    
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

    <c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
	<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
	
	<c:url var="grotekcssUrl" value="/assets/css/grotek.css" />
    <link rel="stylesheet" type="text/css" href="${grotekcssUrl }" />
    <c:url var="kkpagercssUrl" value="/assets/css/kkpager_blue.css" />
    <link rel="stylesheet" type="text/css" href="${kkpagercssUrl }" />
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<div class="container-fluid">
		<ul class="nav nav-tabs" style="margin-top:20px">
       		<c:url var="listUrl" value="/work/clients/boxin?did=${dealer.id }"></c:url>
		    <li class="active"><a href="${listUrl }">进货情况</a></li>
		    <c:url var="staticUrl" value="/work/clients/boxstatic?did=${dealer.id }"></c:url>
		    <li><a href="${staticUrl }">统计</a></li>
		</ul> 
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header">
            	<h3 class="name">${dealer.name }进货情况</h3>
            	<div class="span9 pull-right">
                     <input id="searchtext" type="text" class="span5 search" placeholder="输入产品查找"  onkeydown="onKeyDown(event)"/>
                     <button id="locationBtn" class="btn-flat default" type="button" onclick="search()">查找</button>					 
                 </div>  
            </div>
            <div class="row-fluid table">
                <table class="table table-bordered">
                    <thead>
                        <tr class="success">
                        	<th class="span3 sortable">
                                时间
                            </th>
                            <th class="span3 sortable">
                            	产品名称
                            </th>
                            <th class="span3 sortable">
                                数量
                            </th>
                            <th class="span3 sortable">
                                详细
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <!-- row -->
                    <c:forEach items="${outs}" var="out">
                    	<tr>
                   			<td>
                             <fmt:formatDate value='${out.date }'
									type='date' pattern='yyyy-MM-dd HH:mm' />
                         </td>
                    		<td>
                    			${out.box.name }
                         </td>
                         <td>
                             ${out.count }
                         </td>
                         <td>
                           	 <a href="javascript:sale(${out.id })" class="name">销售进度</a>
                           	 <c:if test="${out.aid!=null }">
                           		<c:url var ="aUrl" value = "/manager/sendapplies/profile?id=${out.aid}"></c:url>
                           		&nbsp;
                           		<a href="javascript:apply(${out.aid })" class="name">发货申请</a>
                           	 </c:if>
                           	 <c:if test="${out.psid!=null }">
                           		&nbsp;
                           		<a href="javascript:deliorder(${out.psid })" class="name">送货单</a>
                           	 </c:if>
                         </td>
                    	</tr>
                    </c:forEach>                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="modal hide fade" id="saleModal" tabindex="-2" role="dialog"
		aria-labelledby="myModalLabel1" aria-hidden="true" style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel1">
						详细信息
					</h4>
				</div>
				<div class="modal-body" id="treeChoosesaleBoxId">
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
		hrefFormer : 'boxin',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			var text = $("#searchtext").val();
			return this.hrefFormer + "?did=${dealer.id}&context="+ encodeURIComponent(text) + "&page=" + (n-1) + "&size=" + size;
		}
		
	});
});

function search(){
	var text = $("#searchtext").val();
		window.location.href = '<c:url value="boxin" />?did=${dealer.id}&context='+encodeURIComponent(text);
}

function sale(id){
	$('#treeChoosesaleBoxId').load('<c:url value="/work/clients/dealersale" />?outid='+id);
	
	$('#saleModal').modal( {
		keyboard : false
	});
}
function apply(id){
	$('#treeChoosesaleBoxId').load('<c:url value="/work/clients/sendapply" />?id='+id);
	
	$('#saleModal').modal( {
		keyboard : false
	});
}
function deliorder(id){
$('#treeChoosesaleBoxId').load('<c:url value="/work/clients/postorder" />?id='+id);
	
	$('#saleModal').modal( {
		keyboard : false
	});
}

</script>
</body>
</html>