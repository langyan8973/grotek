<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="编辑经销商" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
	<c:url var="cssUrlp" value="/assets/css/compiled/form-wizard.css" />
    <link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
   
    <!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content">
        <div class="container-fluid">
        	<c:url var="editempUrl" value="editone" />
            <form id="editemp-form" class="form-horizontal" action="${editempUrl }" method="post" enctype="multipart/form-data">
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
	                                    <span class="title">接收单位</span>
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
                            						<input id="name" name="name" class="span5" type="text" onkeyup="clearerror(this)" value="${dealer.name }"/>
                            						<span class="alert-msg"></span>
                            						<input id="id" name="id" type="hidden" value="${dealer.id }"/>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>地址:</label>
                            						<input id="address" name="address" class="span5" type="text" value="${dealer.address }"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>省份:</label>
                            						<input id="shengname" name="shengname" class="span5" type="text" onkeyup="clearerror(this)" value="${dealer.shengfen.name }"/>
                            						<span class="alert-msg"></span>
                            						<input id="shengid" name="shengid" class="span5 inline-input" type="hidden" value="${dealer.shengfen.id }"/>
	                                            </div>
	                                            <div class="field-box">
						                            <label>地区:</label>
						                            <input id="diquname" name="diquname" class="span5" type="text" value="${dealer.diqu.name }"/>
						                            <span class="alert-msg"></span>
						                            <input id="diquid" name="diquid" class="span5 inline-input" type="hidden" value="${dealer.diqu.id }"/>
						                        </div>
						                        <div class="field-box">
						                            <label>县:</label>
						                            <input id="xianname" name="xianname" class="span5" type="text" value="${dealer.xian.name }"/>
						                            <span class="alert-msg"></span>
						                            <input id="xianid" name="xianid" class="span5 inline-input" type="hidden" value="${dealer.xian.id }"/>
						                        </div>
						                        <div class="field-box">
						                            <label>乡镇:</label>
						                            <input id="zhenname" name="zhenname" class="span5" type="text" value="${dealer.zhen.name }"/>
						                            <span class="alert-msg"></span>
						                            <input id="zhenid" name="zhenid" class="span5 inline-input" type="hidden" value="${dealer.zhen.id }"/>
						                        </div>
						                        <div class="field-box">
						                            <label>邮编:</label>
						                            <input id="postcode" name="postcode" class="span5" type="text" value="${dealer.postcode }" onkeyup="clearNoNum(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>固定电话:</label>
						                            <input id="phone" name="phone" class="span5" type="text" value="${dealer.phone }" onkeyup="clearNoNum(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>所有者:</label>
						                            <input id="owner" name="owner" class="span5" type="text" value="${dealer.owner }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>所属公司:</label>
						                            <input id="company" name="company" class="span5" type="text" value="${dealer.company }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系人:</label>
						                            <input id="contact" name="contact" class="span5" type="text" value="${dealer.contact }" onkeyup="clearerror(this)"/>
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>手机号:</label>
						                            <input id="mobile" name="mobile" class="span5" type="text" value="${dealer.mobile }" onkeyup="clearNoNum(this)"/>
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
                            						<input id="turnover" name="turnover" class="span5" type="text" value="${dealer.turnover }" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>店铺面积:</label>
                            						<input id="area" name="area" class="span5" type="text" value="${dealer.area }" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>员工总数:</label>
                            						<input id="population" name="population" class="span5" type="text" value="${dealer.population }" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
	                                                <label>分销店数:</label>
                            						<input id="fxd_count" name="fxd_count" class="span5" type="text" value="${dealer.fxdCount }" onkeyup="clearNoNum(this)"/>
                            						<span class="alert-msg"></span>
	                                            </div>
	                                            <div class="field-box">
						                            <label>主要经营范围:</label>
						                            <input id="business" name="business" class="span5" type="text" value="${dealer.business }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>主要销售区域:</label>
						                            <input id="territory" name="territory" class="span5" type="text" value="${dealer.territory }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
	                                                <label>是否从经销商进货:</label>
	                                                <select id="sfcjxsjh" name="sfcjxsjh" class="span5">	                                                    
	                                                    <c:choose>
														   <c:when test="${dealer.sfcjxsjh==1 }">   
															<option value="0">否</option>
	                                                    	<option value="1" selected="">是</option>     
														   </c:when>
														   <c:otherwise> 
														    <option value="0" selected="">否</option>
	                                                    	<option value="1">是</option>
														   </c:otherwise>
														</c:choose> 
	                                                </select>
	                                            </div>
						                        <div class="field-box">
						                            <label>进货经销商:</label>
						                            <input id="jhdname" name="hjdname" class="span5" type="text" value="${dealer.jhdea.name }" />
						                            <span class="alert-msg"></span>
						                            <input id="jhdealer" name="jhdealer"type="hidden" value="${dealer.jhdea.id }"/>
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
													<select class="span5" name="strength" id="strength">
														<c:forEach items="${strengths}" var="strength">
															<c:choose>
															   <c:when test="${dealer.strength.id==strength.id }">   
							                                    <option value="${strength.id }" selected="">
																	${strength.name }
																</option>      
															   </c:when>
															   <c:otherwise> 
															    <option value="${strength.id }">
																	${strength.name }
																</option>
															   </c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
						                        </div>
						                        <div class="field-box">
						                            <label>商业信誉:</label>
													<select class="span5" name="reputation" id="reputation">
														<c:forEach items="${reputations}" var="reputation">
															<c:choose>
															   <c:when test="${dealer.reputation.id==reputation.id }">   
							                                    <option value="${reputation.id }" selected="">
																	${reputation.name }
																</option>      
															   </c:when>
															   <c:otherwise> 
															    <option value="${reputation.id }">
																	${reputation.name }
																</option>
															   </c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
						                        </div>
	                                            <div class="field-box">
						                            <label>店铺类型:</label>
													<select class="span5" name="shoptype" id="shoptype">
														<c:forEach items="${shoptypes}" var="shoptype">
															<c:choose>
															   <c:when test="${dealer.shoptype.id==shoptype.id }">   
							                                    <option value="${shoptype.id }" selected="">
																	${shoptype.name }
																</option>      
															   </c:when>
															   <c:otherwise> 
															    <option value="${shoptype.id }">
																	${shoptype.name }
																</option>
															   </c:otherwise>
															</c:choose> 
														</c:forEach>
													</select>
						                        </div>
						                        <div class="field-box">
						                            <label>信誉等级:</label>
													<select class="span5" name="creditrating" id="creditrating">
														<c:forEach items="${creditratings}" var="creditrating">
															<c:choose>
															   <c:when test="${dealer.creditrating.id==creditrating.id }">   
							                                    <option value="${creditrating.id }" selected="">
																	${creditrating.name }
																</option>      
															   </c:when>
															   <c:otherwise> 
															    <option value="${creditrating.id }">
																	${creditrating.name }
																</option>
															   </c:otherwise>
															</c:choose> 
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
						                            <label>业务代表:</label>
						                            <input id="agentname" name="agentname" class="span5" type="text" value="${dealer.agent.fullname }" />
						                            <span class="alert-msg"></span>
						                            <input id="agentid" name="agentid"type="hidden" value="${dealer.agent.id }"/>
						                        </div> 
	                                            <div class="field-box">
						                            <label>登记日期:</label>
						                            <input id="djdate" name="djdate" class="span5" type="text" data-date-format="yyyy-mm-dd" value="<fmt:formatDate value='${dealer.djdate }'
													type='date' pattern='yyyy-MM-dd' />" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>是否合作:</label>
	                                                <select id="sfhz" name="sfhz" class="span5">
	                                                    <c:choose>
														   <c:when test="${dealer.sfhz==1 }"> 
														   	<option value="1" selected="">是</option>  
															<option value="0">否</option>	                                                    	     
														   </c:when>
														   <c:otherwise> 
	                                                    	<option value="1">是</option>
	                                                    	<option value="0" selected="">否</option>
														   </c:otherwise>
														</c:choose> 
	                                                </select>
						                        </div>
						                        <div class="field-box">
						                            <label>评语:</label>
						                            <textarea rows="5" path="description" name='description' id="description" class="span5" value="${dealer.description }" placeholder="简要评价，50字以内" maxlength="50"></textarea>
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
						                            <input id="pname" name="pname" class="span5" type="text" value="${dealer.pname }" />
						                            <span class="alert-msg"></span>
						                        </div> 
	                                            <div class="field-box">
						                            <label>地址:</label>
						                            <input id="paddress" name="paddress" class="span5" type="text" value="${dealer.paddress }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系人:</label>
						                            <input id="pcontact" name="pcontact" class="span5" type="text" value="${dealer.pcontact }" />
						                            <span class="alert-msg"></span>
						                        </div>
						                        <div class="field-box">
						                            <label>联系电话:</label>
						                            <input id="pphone" name="pphone" class="span5" type="text" value="${dealer.pphone }" />
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
    <!-- end main container -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel3" aria-hidden="true" style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel3">
						选择业务代表
					</h4>
				</div>
				<div class="modal-body" id="treeChooseTypeBoxId">
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
	<script>
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
				            
							addFormValidate();
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
							$('#treeChooseshengBoxId').load('<c:url value="/manager/districts/select" />');
							
							$('#jhdname').bind('click', function() {
								$('#dealerModal').modal( {
									keyboard : false
								})
							});
							$('#treeChoosedealerBoxId').load('<c:url value="/manager/dealers/select" />');
							
							$('#agentname').bind('click', function() {
								$('#myModal').modal( {
									keyboard : false
								})
							});
							$('#treeChooseTypeBoxId').load('<c:url value="/manager/employees/superiors" />');
							
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
			$("#editemp-form").validate({
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
		function clickEmployeeType(id,sname){
			$('#agentname').val(sname);
			$('#agentid').val(id);
			$('#myModal').modal('hide');
		}
	</script>
<jsp:include page="../includes/footer.jsp" />