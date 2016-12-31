<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="员工" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-list.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
<style>
<!--
.doctypeUl {
	font-size: 14px;
}

.chooseBoxType {
	cursor: pointer;
}
.list-inline {
  padding-left: 10px;
}

.list-inline > li {
  padding-right: 5px;
  padding-left: 5px;
}

.list-inline > li:first-child {
  padding-left: 0;
}
-->
</style>
<section class="content-wrap">
	<div class="container">
		<div class="row">
			<div class="column span4">
				<div class="well">
				<input id="suid" type="hidden" value="${sid }" >
					<ul id="rootNote" class="doctypeUl">	
						
					</ul>
				</div>
			</div>
			<div class="column span8">
				<div class="well">
					<div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul id="baseul" class="baseinfolist">
	                            <li>部门：${employee.dept.name } </li>
	                            <li>职务：${employee.position.name } </li>
	                        </ul>
                        	<small>
                        		<ul id="infoul" class="infolist-inline">
		                            <li>性别：${employee.sex } </li>
		                            <li>手机号：${employee.mobile } </li>
		                            <li>固定电话：${employee.phone } </li>
		                            <li>地址：${employee.address }</li>
		                            <li>邮编：${employee.postcode }</li>
		                        </ul>
		                        <ul id="moreul" class="infolist-inline">
		                           
		                        </ul>
                        	</small>
                        </h4>                        
                    </div>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
	$(document).ready(function() {
		search();
	});
	function search() {
		var sid = $('#suid').val();
		var url = '<c:url value="index.json" />';
		url = url + "?&sid=" + sid;
		$.get(url, function(result) {
			var root = $('#rootNote');
			parsenotes(root,result);
			if(result[0]!=null){
				display(result[0]);
			}
		});
	}
	
	function parsenotes(parent,notes){
		$.each(notes, function (n, note) {
			var li;
			li = $("<li></li>");
			var h6 = $('<h6 class="chooseBoxType" id="'+note.id+'" style="line-height: 15px;"></h6>');
			h6.bind('click',
					function() {
						display(note);
					});			
			var span = $('<span style="color: #5CB85C;"></span>');
			span.append(note.fullname);
			h6.append(span);
			li.append(h6);
			if(note.notes.length>0){
				var ul = $('<ul class="list-inline"></ul>');
				li.append(ul);
				parsenotes(ul,note.notes);
			}
			parent.append(li)
		});
	}
	function display(note){
		var baseul = $('#baseul');
		baseul.html('');
		var nameli = $("<li></li>");
		nameli.append("姓名："+note.fullname);
		baseul.append(nameli);
		var deptli = $("<li></li>");
		deptli.append("部门："+note.dept.name);
		baseul.append(deptli);
		var poli = $("<li></li>");
		poli.append("职务："+note.position.name);
		baseul.append(poli);
		var infoul = $('#infoul');
		infoul.html('');
		
		var sexli=$("<li></li>");
		sexli.append("性别："+note.sex);
		infoul.append(sexli);
		
		sexli=$("<li></li>");
		sexli.append("手机号："+note.mobile);
		infoul.append(sexli);
		
		sexli=$("<li></li>");
		sexli.append("固定电话："+note.phone);
		infoul.append(sexli);
		
		sexli=$("<li></li>");
		sexli.append("地址："+note.address);
		infoul.append(sexli);
		
		sexli=$("<li></li>");
		sexli.append("邮编："+note.postcode);
		infoul.append(sexli);
		
		var moreul=$('#moreul');
		moreul.html('');
		sexli=$("<li></li>");
		var url = '<c:url value="works" />?eid='+note.id;
		var a = $("<a href='"+url+"'></a>");
		a.append("查看更多");
		sexli.append(a);
		moreul.append(sexli);
	}
</script>

<jsp:include page="../includes/footer.jsp" />