<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="员工信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="workUrl" value="/assets/css/compiled/personal-info.css" />
<link rel="stylesheet" href="${workUrl }" type="text/css" media="screen" />
<div class="content wide-content">
	<div class="container-fluid">
		<ul class="nav nav-tabs" style="margin-top:20px">
       		<c:url var="workUrl" value="/work/members/works?eid=${employee.id }"></c:url>
		    <li class="active"><a href="${workUrl }">周项目工作</a></li>
		    <c:url var="travelUrl" value="/work/members/travels?eid=${employee.id }"></c:url>
		    <li><a href="${travelUrl }">出差费用报销</a></li>
		    <c:url var="exsUrl" value="/work/members/exs?eid=${employee.id }"></c:url>
		    <li><a href="${exsUrl }">市场开拓费用报销</a></li>
		    <c:url var="applyUrl" value="/work/members/sends?eid=${employee.id }"></c:url>
			<li><a href="${applyUrl }">发货申请</a></li>
		</ul>
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header">
            	<h3 class="name">${employee.fullname }周项目工作</h3>
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
                              <th class="sortable">
                                  地点
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
                               ${weekly.items[0].place }
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
                                ${item.place }
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
<jsp:include page="../includes/footer.jsp" />