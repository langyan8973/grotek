<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="pageTitle" value="新增客户" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/wheader.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/form-wizard.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <c:url var="addempUrl" value="addone" />
            <form id="addemp-form" class="form-horizontal" action="${addempUrl }" method="post" enctype="multipart/form-data">
            <fieldset>
            	<div id="pad-wrapper">
	                <div class="row-fluid">
	                    <div class="span12">
	                        <div id="fuelux-wizard" class="wizard row-fluid">
	                            <ul class="wizard-steps">
	                                <li data-target="#step1" class="active">
	                                    <span class="step">1</span>
	                                    <span class="title">基本信息</span>
	                                </li>
	                                <li data-target="#step2">
	                                    <span class="step">2</span>
	                                    <span class="title">经营概况</span>
	                                </li>
	                                <li data-target="#step3">
	                                    <span class="step">3</span>
	                                    <span class="title">实力信誉</span>
	                                </li>
	                                <li data-target="#step4">
	                                    <span class="step">4</span>
	                                    <span class="title">业务信息</span>
	                                </li>
	                                <li data-target="#step5">
	                                    <span class="step">5</span>
	                                    <span class="title">付款单位</span>
	                                </li>
	                            </ul>                            
	                        </div>
	                        <div class="step-content">
	                            <div class="step-pane active" id="step1">
	                                <div class="row-fluid form-wrapper">
	                                	<div class="span2">
	                                	</div>
	                                    <div class="span8">
	                                            <div class="field-box">
	                                                <label>名称:</label>
                            						<input id="name" name="name" class="span5" type="text" value="" onkeyup="clearerror(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>地址:</label>
                            						<input id="address" name="address" class="span5" type="text" value="" onkeyup="clearerror(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>省份:</label>
                            						<input id="shengname" name="shengname" class="span5" type="text" value="" onkeyup="clearerror(this)"/>
                            						<span class="alert-msg"></span>
                            						<input id="shengid" name="shengid" class="span5 inline-input" type="hidden" value=""/>
	                                            </div>
	                                            <div class="field-box">
						                            <label>地区:</label>
						                            <input id="diquname" name="diquname" class="span5" type="text" value=""/>
						                            <span class="alert-msg"></span>
						                            <input id="diquid" name="diquid" class="span5 inline-input" type="hidden" value=""/>
						                        </div>
						                        <div class="field-box">
						                            <label>县:</label>
						                            <input id="xianname" name="xianname" class="span5" type="text" value=""/>
						                            <span class="alert-msg"></span>
						                            <input id="xianid" name="xianid" class="span5 inline-input" type="hidden" value=""/>
						                        </div>
						                        <div class="field-box">
						                            <label>乡镇:</label>
						                            <input id="zhenname" name="zhenname" class="span5" type="text" value=""/>
						                            <span class="alert-msg"></span>
						                            <input id="zhenid" name="zhenid" class="span5 inline-input" type="hidden" value=""/>
						                        </div>
						                        <div class="field-box">
						                            <label>邮编:</label>
						                            <input id="postcode" name="postcode" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>固定电话:</label>
						                            <input id="phone" name="phone" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>所有者:</label>
						                            <input id="owner" name="owner" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>所属公司:</label>
						                            <input id="company" name="company" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系人:</label>
						                            <input id="contact" name="contact" class="span5" type="text" value="" onkeyup="clearerror(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>手机号:</label>
						                            <input id="mobile" name="mobile" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="step-pane" id="step2">
	                                <div class="row-fluid form-wrapper">
	                                	<div class="span2">
	                                	</div>
	                                    <div class="span8">
	                                            <div class="field-box">
	                                                <label>年营业额:</label>
                            						<input id="turnover" name="turnover" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>店铺面积:</label>
                            						<input id="area" name="area" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>员工总数:</label>
                            						<input id="population" name="population" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>分销店数:</label>
                            						<input id="fxd_count" name="fxd_count" class="span5" type="text" value="" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
						                            <label>主要经营范围:</label>
						                            <input id="business" name="business" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>主要销售区域:</label>
						                            <input id="territory" name="territory" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
	                                                <label>是否从经销商进货:</label>
	                                                <select id="sfcjxsjh" name="sfcjxsjh" class="span5">
	                                                    <option value="0">否</option>
	                                                    <option value="1">是</option>
	                                                </select>
	                                            </div>
						                        <div class="field-box">
						                            <label>进货经销商:</label>
						                            <input id="jhdname" name="hjdname" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                            <input id="jhdealer" name="jhdealer"type="hidden" value=""/>
						                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="step-pane" id="step3">
	                                <div class="row-fluid form-wrapper">
	                                	<div class="span2">
	                                	</div>
	                                    <div class="span8">
	                                            <div class="field-box">
						                            <label>竞争实力:</label>
						                            <c:set var="selval" value=""/>
													<c:if test="${strengths != null}">
														<c:set var="selval" value="${strengths[0].id }"/>
													</c:if>
													<select class="span5" name="strength" id="strength"
														val="${selval }">
														<c:forEach items="${strengths}" var="strength">
															<option value="${strength.id }">
																${strength.name }
															</option>
														</c:forEach>
													</select>
						                        </div>
						                        <div class="field-box">
						                            <label>商业信誉:</label>
						                            <c:set var="selrepu" value=""/>
													<c:if test="${reputations != null}">
														<c:set var="selrepu" value="${reputations[0].id }"/>
													</c:if>
													<select class="span5" name="reputation" id="reputation"
														val="${selrepu }">
														<c:forEach items="${reputations}" var="reputation">
															<option value="${reputation.id }">
																${reputation.name }
															</option>
														</c:forEach>
													</select>
						                        </div>
	                                            <div class="field-box">
						                            <label>店铺类型:</label>
						                            <c:set var="seltype" value=""/>
													<c:if test="${shoptypes != null}">
														<c:set var="seltype" value="${shoptypes[0].id }"/>
													</c:if>
													<select class="span5" name="shoptype" id="shoptype"
														val="${seltype }">
														<c:forEach items="${shoptypes}" var="shoptype">
															<option value="${shoptype.id }">
																${shoptype.name }
															</option>
														</c:forEach>
													</select>
						                        </div>
						                        <div class="field-box">
						                            <label>信誉等级:</label>
						                            <c:set var="selcr" value=""/>
													<c:if test="${creditratings != null}">
														<c:set var="selcr" value="${creditratings[0].id }"/>
													</c:if>
													<select class="span5" name="creditrating" id="creditrating"
														val="${selcr }">
														<c:forEach items="${creditratings}" var="creditrating">
															<option value="${creditrating.id }">
																${creditrating.name }
															</option>
														</c:forEach>
													</select>
						                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="step-pane" id="step4">
	                                <div class="row-fluid form-wrapper payment-info">
	                                	<div class="span2">
	                                	</div>
	                                    <div class="span8">
	                                            
	                                            <div class="field-box">
						                            <label>登记日期:</label>
						                            <input id="djdate" name="djdate" class="span5" type="text" data-date-format="yyyy-mm-dd" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>是否合作:</label>
	                                                <select id="sfhz" name="sfhz" class="span5">
	                                                	<option value="1">是</option>
	                                                    <option value="0">否</option>
	                                                </select>
						                        </div>
						                        <div class="field-box">
						                            <label>评语:</label>
						                            <textarea rows="5" path="description" name='description' id="description" class="span5" placeholder="简要评价，50字以内" maxlength="50"></textarea>
						                            <span class="alert-msg"></span>
						                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="step-pane" id="step5">
	                                <div class="row-fluid form-wrapper payment-info">
	                                	<div class="span2">
	                                	</div>
	                                    <div class="span8">
	                                            <div class="field-box">
						                            <label>单位名称:</label>
						                            <input id="pname" name="pname" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div> 
	                                            <div class="field-box">
						                            <label>地址:</label>
						                            <input id="paddress" name="paddress" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系人:</label>
						                            <input id="pcontact" name="pcontact" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系电话:</label>
						                            <input id="pphone" name="pphone" class="span5" type="text" value="" />
						                            <span class="alert-msg"></span>
						                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="wizard-actions">
	                            <button type="button" disabled="" class="btn-glow primary btn-prev"> 
	                                <i class="icon-chevron-left"></i>上一步
	                            </button>
	                            <button type="button" class="btn-glow primary btn-next" data-last="Finish">
	                                下一步<i class="icon-chevron-right"></i>
	                            </button>
	                            <!-- <button type="button" class="btn-glow success btn-finish">
	                                Setup your account!
	                            </button> -->
	                            <input type="submit" class="btn-glow primary btn-finish" value="保存" />
	                        </div>
	                    </div>
	                </div>
	            </div>
            </fieldset>
            </form>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="shengModal" tabindex="-2" role="dialog"
		aria-labelledby="myModalLabel2" aria-hidden="true" style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel1">
						选择区域
					</h4>
				</div>
				<div class="modal-body" id="treeChooseshengBoxId">
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
	<c:url var="cssUrl" value="/assets/uploadify-v3.1/uploadify.css" />
	<link rel="stylesheet" href="${cssUrl}" type="text/css"></link>
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
	<c:url var="jsUrl4" value="/assets/js/fuelux.wizard.js" />
	<script type="text/javascript" src="${jsUrl4 }" charset="UTF-8"></script>
<script type="text/javascript">
var recipeUpload;
var selectstatus=0;
$(document)
		.ready(
				function() {
					
					var $wizard = $('#fuelux-wizard'),
	                $btnPrev = $('.wizard-actions .btn-prev'),
	                $btnNext = $('.wizard-actions .btn-next'),
	                $btnFinish = $(".wizard-actions .btn-finish");

		            $wizard.wizard().on('finished', function(e) {
		                // wizard complete code
		            }).on("changed", function(e) {
		            	
		                var step = $wizard.wizard("selectedItem");
		                // reset states
		                $btnNext.removeAttr("disabled");
		                $btnPrev.removeAttr("disabled");
		                $btnNext.show();
		                $btnFinish.hide();

		                if (step.step === 1) {
		                    $btnPrev.attr("disabled", "disabled");
		                } else if (step.step === 5) {
		                    $btnNext.hide();
		                    $btnFinish.show();
		                }
		            }).on("change",function(e){
		            	var step = $wizard.wizard("selectedItem");
		            	if(step.step==1){
		            		return formbaseValidate();
		            	}
		            	else{
		            		return true;
		            	}
		            });

		            $btnPrev.on('click', function() {
		                $wizard.wizard('previous');
		            });
		            $btnNext.on('click', function() {
		                $wizard.wizard('next');
		            });
		            
					/* addFormValidate(); */
					$('#shengname').bind('click', function() {
						$('#shengModal').modal( {
							keyboard : false
						})
					});
					$('#diquname').bind('click', function() {
						$('#shengModal').modal( {
							keyboard : false
						})
					});
					$('#xianname').bind('click', function() {
						$('#shengModal').modal( {
							keyboard : false
						})
					});
					$('#zhenname').bind('click', function() {
						$('#shengModal').modal( {
							keyboard : false
						})
					});
					$('#treeChooseshengBoxId').load('<c:url value="/work/selects/districts" />');
					
					$('#jhdname').bind('click', function() {
						$('#dealerModal').modal( {
							keyboard : false
						})
					});
					$('#treeChoosedealerBoxId').load('<c:url value="/work/selects/dealers" />');
					
					
					
					$('#djdate').datetimepicker({
						language : 'zh-CN',
						minView: "month",
						autoclose:true
					});
				});
function formbaseValidate(){
	var name = $('#name').val();
	if(name==null || name==""){
		$('#name').nextAll(".alert-msg").html("必填");
		return false;
	}
	var address = $('#address').val();
	if(address==null || address==""){
		$('#address').nextAll(".alert-msg").html("必填");
		return false;
	}
	var shengname = $('#shengname').val();
	if(shengname==null || shengname==""){
		$('#shengname').nextAll(".alert-msg").html("必填");
		return false;
	}
	var contact = $('#contact').val();
	if(contact==null || contact==""){
		$('#contact').nextAll(".alert-msg").html("必填");
		return false;
	}
	var mobile = $('#mobile').val();
	if(mobile==null || mobile==""){
		$('#mobile').nextAll(".alert-msg").html("必填");
		return false;
	}
	return true;
}

function addFormValidate() {
	$("#addemp-form").validate({
		debug : true,
		rules : {
			agentname : {
				required : true
			}
		},

		messages : {
			agentname : {
				required : "必填"
			}
		},

		errorClass : 'invalid',
		validClass : 'invalid',
		errorPlacement : function(error, element) {
			element.nextAll(".alert-msg").html(error);

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
function clearerror(obj) {
	if(obj.value!=null && obj.value!=""){
		$('#'+obj.id).nextAll(".alert-msg").html("");
	}
}
function clickQuyuType(id,sname,level) {
	if(level==1){
		$('#shengname').val(sname);
		$('#shengid').val(id);
		
	}
	if(level==2){
		$('#diquname').val(sname);
		$('#diquid').val(id);
	}
	if(level==3){
		$('#xianname').val(sname);
		$('#xianid').val(id);
	}
	if(level==4){
		$('#zhenname').val(sname);
		$('#zhenid').val(id);
	}
	$('#shengModal').modal('hide');
	
}

function clickDocType(id,sname) {
	$('#jhdname').val(sname);
	$('#jhdealer').val(id);
	$('#dealerModal').modal('hide');

} 

</script>
<jsp:include page="../includes/footer.jsp" />