<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title><c:out value="工厂用工统计" /></title>
    
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
            	<div class="span4">
                     <h3 class="name">工厂用工统计</h3>
                 	</div>
            	<div calss="row-fluid" style="padding-top:20px;">
               		查询时段
					<input path="starttime" name='starttime' id="starttime" class="input-large" class="form_datetime" />
					至
					<input path="endtime" name='endtime' id="endtime" class="input-large" class="form_datetime" />
					<button id="locationBtn" class="btn btn-info" type="button" onclick="search()">查找</button>
               	</div> 
            	
            </div>
            <div class="row-fluid profile">
            	<h4 id="title1" style="margin-left:100px;"></h4>
            </div>
            <div class="row-fluid profile">
            	<div id="canvas-holder" style="text-align:center;">
					<canvas id="chart-area" width="1000" height="450" />
				</div>
            </div>
            <div class="row-fluid profile">
            	<h4 id="title2" style="margin-left:100px; margin-top:50px;"></h4>
            </div>
            <div class="row-fluid profile">
            	<div id="canvas-holder1" style="text-align:center;">
					<canvas id="chart-area1" width="1000" height="450" />
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
		$('#starttime').val(moment(new Date()).subtract(183, 'day').format("YYYY-MM"));
		$('#endtime').val(moment(new Date()).format("YYYY-MM"));
		$('#starttime').datetimepicker({
			 format: 'yyyy-mm',  
	         weekStart: 1,  
	         autoclose: true,  
	         startView: 3,  
	         minView: 3,  
	         forceParse: false,  
	         language: 'zh-CN' 
		});
		$('#endtime').datetimepicker({
			format: 'yyyy-mm',  
	         weekStart: 1,  
	         autoclose: true,  
	         startView: 3,  
	         minView: 3,  
	         forceParse: false,  
	         language: 'zh-CN' 
		});
		search();
	});

	var myLineChart;
	var myLineChart1;
	function search() {
		var url = '<c:url value="labortimes.json" />';
		url = url + "?starttime=" + $('#starttime').val() + "&endtime=" + $('#endtime').val();
		$.get(url, function(result) {
			if (myLineChart) {
				myLineChart.destroy();
			}
			if (myLineChart1) {
				myLineChart1.destroy();
			}
			var data = {
				labels : result[0],
				datasets : [ {
					label : "",
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					data : result[1]
				} ]
			};
			
			var data1 = {
					labels : result[0],
					datasets : [ {
						label : "",
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,1)",
						data : result[2]
					} ]
				};

			var ctx = document.getElementById("chart-area").getContext("2d");
			myLineChart = new Chart(ctx).Bar(data, {
				bezierCurve : false
			});
			$('#title1').html('用工工时统计：总计：'+result[3][0]+"人小时");
			var ctx1 = document.getElementById("chart-area1").getContext("2d");
			myLineChart1 = new Chart(ctx1).Bar(data1, {
				bezierCurve : false
			});
			$('#title2').html('用工费用统计：总计：'+result[3][1]+'万元');
		});
	}
	</script>
</body>
</html>