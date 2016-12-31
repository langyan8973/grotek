<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="周项目工作" /></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <c:url var="cssUrl" value="/assets/css/bootstrap/bootstrap.css" />
    <link href="${cssUrl }" rel="stylesheet" />
    <c:url var="cssUrl2" value="/assets/css/bootstrap/bootstrap-responsive.css" />
    <link href="${cssUrl2 }" rel="stylesheet" />
   <%--  <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
    <link href="${cssUrl3 }" type="text/css" rel="stylesheet" /> --%>

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
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header">
            	<h3 class="name">${employee.fullname }周项目工作</h3>
            	<c:url var="exUrl" value="/manager/statics/exportworks?eid=${employee.id }"></c:url>
	            <a class="btn-flat icon pull-right delete-user" title="导出" data-placement="top"
                    href="${exUrl }">
                       <i class="icon-download">导出</i>
                   </a>
            </div>
            <div class="row-fluid table">
            	<label style="margin-left:30px;">时间段：<fmt:formatDate value='${weekly.stime }' type='date' pattern='yyyy/MM/dd' />--<fmt:formatDate value='${weekly.etime }' type='date' pattern='yyyy/MM/dd' />
			    </label>
                <table class="table table-bordered">
                      <thead>
                          <tr>
                              <th class="sortable">
                                  客户
                              </th>
                              <th class="sortable">
                                  工作类型
                              </th>
                              <th class="sortable">
                                  工作内容
                              </th>
                              <th class="tdweek">
                                  星期一
                              </th>
                              <th class="tdweek">
                                  星期二
                              </th>
                              <th class="tdweek">
                                  星期三
                              </th>
                               <th class="tdweek">
                                  星期四
                              </th>
                               <th class="tdweek">
                                  星期五
                              </th>
                               <th class="tdweek">
                                  星期六
                              </th>
                               <th class="tdweek">
                                  星期日
                              </th>
                              <th class="tdweek">
                                  合计
                              </th>
                          </tr>
                      </thead>
                      <tbody>
                      <!-- row -->
                      	<c:if test="${weekly.count==null }">
                      		<tr>
                      		<td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                      		</tr>
                      	</c:if>
                      	<c:if test="${weekly.count!=null }">
                      	<tr>
                     	   <td>
                      			${weekly.items[0].dealer.name }
                           </td>
                           <td>
                               ${weekly.items[0].type.name }
                           </td>
                           <td>
                               ${weekly.items[0].content }
                           </td>
                           <td>
                           	${weekly.items[0].mon }
                           </td>
                           <td>
                           	${weekly.items[0].tue }
                           </td>
                           <td>
                           	${weekly.items[0].wed }
                           </td>
                           <td>
                           	${weekly.items[0].thu }
                           </td>
                           <td>
                           	${weekly.items[0].fri }
                           </td>
                           <td>
                           	${weekly.items[0].sat }
                           </td>
                           <td>
                           	${weekly.items[0].sun }
                           </td>
                           <td>
                           	${weekly.items[0].total }
                           </td>
                     		</tr>
                      	<c:forEach items="${weekly.items }" var="item"  varStatus="status" begin="1" end="${weekly.count-1 }">
                      		<tr>
                      			<td>
                       			${item.dealer.name }
                            </td>
                            <td>
                                ${item.type.name }
                            </td>
                            <td>
                                ${item.content }
                            </td>
                            <td>
                            	${item.mon }
                            </td>
                            <td>
                            	${item.tue }
                            </td>
                            <td>
                            	${item.wed }
                            </td>
                            <td>
                            	${item.thu }
                            </td>
                            <td>
                            	${item.fri }
                            </td>
                            <td>
                            	${item.sat }
                            </td>
                            <td>
                            	${item.sun }
                            </td>
                            <td>
                            	${item.total }
                            </td>
                      		</tr>
                      	</c:forEach>
                      	</c:if>                    
                      </tbody>
                  </table>
            </div>
        </div>
    </div>
<script type="text/javascript">
$(document).ready(function() {
    var size = 1;
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
		hrefFormer : 'works',
		//链接尾部
		hrefLatter : '',
		getLink : function(n){
			var text = $("#searchtext").val();
			return this.hrefFormer + "?eid=${employee.id}&page=" + (n-1) + "&size=" + size;
		}
		
	});
});


</script>
</body>
</html>