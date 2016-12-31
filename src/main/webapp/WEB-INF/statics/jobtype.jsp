<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="工时分类统计" /></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <c:url var="cssUrl" value="/assets/css/bootstrap/bootstrap.css" />
    <link href="${cssUrl }" rel="stylesheet" />
    <c:url var="cssUrl2" value="/assets/css/bootstrap/bootstrap-responsive.css" />
    <link href="${cssUrl2 }" rel="stylesheet" />
    <c:url var="cssUrl3" value="/assets/css/bootstrap/bootstrap-overrides.css" />
    <link href="${cssUrl3 }" type="text/css" rel="stylesheet" />
    <c:url var="cssUrl1" value="/assets/css/bootstrap/bootstrap-datetimepicker.min.css" />
	<link href="${cssUrl1}" rel="stylesheet" media="screen">

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
    
    
	<jsp:include page="../includes/js.jsp" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body style="background-color:#ffffff;">
	<div class="container-fluid">
        <div id="pad-wrapper" class="user-profile">
            <!-- header -->
            <div class="row-fluid header boxbottomline">
            	<c:if test="${employee != null}">
            		<div class="span4">
                     <h3 class="name">${employee.fullname }工时分类统计</h3>
                 	</div>
                 	<div calss="row-fluid" style="padding-top:20px;">
                 		客户：
                 		<select class="span2" name="dealer" id="dealer">
                 			<option value="0">
								全部客户
							</option>
							<c:forEach items="${dealers}" var="dealer">
								<option value="${dealer.id }">
									${dealer.name }
								</option>
							</c:forEach>
						</select>
                 		查询时段：
						<input path="starttime" name='starttime' id="starttime" class="input-large" class="form_datetime" />
						至
						<input path="endtime" name='endtime' id="endtime" class="input-large" class="form_datetime" />
						<button id="locationBtn" class="btn btn-info" type="button" onclick="search()">查找</button>
						<input id="eid" name="eid" type="hidden" value="${employee.id }" />
                 	</div>
                 	
            	</c:if>    
            	
            </div>
            <div class="row-fluid profile">
            	<div id="canvas-holder1" class="span3" style="text-align:center;">
            		<h4 id="title1" style="margin-bottom:30px;"></h4>
					<canvas id="chart-area1" height="300" />
				</div>
				<div class="span3">
					<h5 id="sum1" style="margin-bottom:10px; margin-left:20px;"></h5>
					<div class="chart-legend">
						<ul id="legend1" class="pie-legend"></ul>
					</div>
				</div>				
				<div id="canvas-holder2" class="span3" style="text-align:center;">
					<h4 id="title2" style="margin-bottom:30px;"></h4>
					<canvas id="chart-area2" height="300" />
				</div>
				<div class="span3">
					<h5 id="sum2" style="margin-bottom:10px; margin-left:20px;"></h5>
					<div class="chart-legend">
						<ul id="legend2" class="pie-legend"></ul>
					</div>
				</div>
            </div>
        </div>
    </div>
    
	<c:url var="jsUrl" value="/assets/uploadify-v3.1/jquery.uploadify-3.1.min.js" />
	<script type="text/javascript" src="${jsUrl}"></script>
	<c:url var="jsUrl1" value="/assets/js/moment.js" />
	<script type="text/javascript" src="${jsUrl1 }"></script>
	<c:url var="jsUrl2" value="/assets/js/bootstrap-datetimepicker.js" />
	<script type="text/javascript" src="${jsUrl2 }" charset="UTF-8"></script>
	<c:url var="jsUrl3" value="/assets/js/bootstrap-datetimepicker.zh-CN.js" />
	<script type="text/javascript" src="${jsUrl3 }" charset="UTF-8"></script>   
	<c:url var="jsUrl4" value="/assets/js/Chart.js" />
	<script type="text/javascript" src="${jsUrl4 }"></script> 
	<script>
		$(document).ready(function() {
			$('#starttime').val(moment(new Date()).subtract(183, 'day').format("YYYY-MM-DD"));
			$('#endtime').val(moment(new Date()).format("YYYY-MM-DD"));
			$('#starttime').datetimepicker({
				format : "yyyy-mm-dd",
				weekStart : 1,
				autoclose : true,
				minView : 2,
				todayHighlight : true,
				language : 'zh-CN'
			});
			$('#endtime').datetimepicker({
				format : "yyyy-mm-dd",
				weekStart : 1,
				autoclose : true,
				minView : 2,
				todayHighlight : true,
				language : 'zh-CN'
			});
	
			search();
		});
	
		var myPieChart1;
		var myPieChart2;
		function search() {
			var eid = $('#eid').val();
			var url = '<c:url value="jobtype.json" />';
			url = url + "?starttime=" + $('#starttime').val() + "&endtime=" + $('#endtime').val()+"&did="+$('#dealer').val() + "&eid=" + eid;
			$.get(url, function(result) {
				if (myPieChart1) {
					myPieChart1.destroy();
				}
				if (myPieChart2) {
					myPieChart2.destroy();
				}
				$('#title1').html('工时统计');
				$('#title2').html('工时费用统计');
				var ctx1 = document.getElementById("chart-area1").getContext("2d");
				myPieChart1 = new Chart(ctx1).Pie(result[0]);
				$("#legend1").html('');
				$("#sum1").html('');
				var sum1=0;
				$.each(result[0], function (n, value) {
					var html='<li><span style="background-color:'+ value.color + '"></span>'
					+ value.label
					 + ':'
					 + value.value
					 +'小时</li>'
					$("#legend1").append(html);
					sum1+=value.value;
				});
				$("#sum1").html('总计：'+sum1+"小时");
				var ctx2 = document.getElementById("chart-area2").getContext("2d");
				myPieChart2 = new Chart(ctx2).Pie(result[1]);
				$("#legend2").html('');
				$("#sum2").html('');
				var sum2=0;
				$.each(result[1], function (n, value) {
					var html='<li><span style="background-color:'+ value.color + '"></span>'
					+ value.label
					 + ':'
					 + value.value
					 +'元</li>'
					$("#legend2").append(html);
					sum2+=value.value;
				});
				$("#sum2").html('总计：'+sum2+"元");
			});
		}
	</script>
</body>
</html>