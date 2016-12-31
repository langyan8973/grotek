<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="修改密码" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="signupUrl" value="/assets/css/compiled/signup.css" />
<link rel="stylesheet" href="${signupUrl }" type="text/css" media="screen" />
<div class="content">
<div class="container-fluid">
<div class="row-fluid login-wrapper">
    <div class="box" style="margin-top:100px;">
        <div class="content-wrap">
            <h6>修改密码</h6>
            <input class="span12" type="password" id="oldpass" name="oldpass" placeholder="您的旧密码" required autofocus/>
            <input class="span12" type="password" id="newpass" name="newpass" placeholder="您的新密码"  required/>
            <input class="span12" type="password" id="confirmpass" name="confirmpass" placeholder="确认新密码"  required/>
            <button class="btn-glow primary login" type="submit" style="margin-top:10px;" onclick="javascript:change();">确定</button>              
        </div>
    </div>
</div>
</div>
</div>
    <!-- end main container -->
<script type="text/javascript">
function change(){
	var oldpass=$('#oldpass').val();
	var newpass=$('#newpass').val();
	var confirmpass=$('#confirmpass').val();
	if(oldpass==null || oldpass==""){
		bootbox.alert("旧密码不能为空");
		return;
	}
	if(newpass==null || newpass==""){
		bootbox.alert("新密码不能为空");
		return;
	}
	if(confirmpass==null || confirmpass==""){
		bootbox.alert("请确认输入新密码");
		return;
	}
	if(newpass!=confirmpass){
		bootbox.alert("新密码确认错误！");
		return;
	}
	
	$.post('<c:url value="change" />', {
		old : oldpass,
		pass : newpass
	}).done(function(data) {
		if(data=='false'){
			bootbox.alert("旧密码输入错误，密码修改失败");
			return;
		}
		bootbox.alert('密码修改成功，请重新登录',function() {  
            	window.location.href = '<c:url value="/manager/j_spring_security_logout" />'; 
            });  
	}).fail(function() {
	});
}
</script>
<jsp:include page="../includes/footer.jsp" />