<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pageTitle" value="经销商信息" scope="request" />
<!-- this page specific styles -->

<jsp:include page="../includes/header.jsp" />
<c:url var="cssUrlp" value="/assets/css/compiled/user-profile.css" />
<link rel="stylesheet" href="${cssUrlp }" type="text/css" media="screen" />
    
	<!-- main container -->
    <div class="content">
        <div class="container-fluid">
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header boxbottomline">
                    <div class="span6">
                        <h3 class="name">${dealer.name }</h3>
                        <c:if test="${dealer.agent != null}">
                    		业务代表：${dealer.agent.fullname }
                    	</c:if> &nbsp;&nbsp;
                    	登记日期：<fmt:formatDate value='${dealer.djdate }'
													type='date' pattern='yyyy-MM-dd HH:mm' />  
                    </div>
                    <div class="span6">
                    	<div class="row-fluid">
                    		<a class="btn-flat icon pull-right delete-user"  title="删除" data-placement="top"
		                     href="javascript:deleteone('${dealer.id }')">
		                        <i class="icon-trash"></i>
		                    </a>
                    		<c:url var="editUrl" value="/manager/dealers/edit?id=${dealer.id }"></c:url>
		                    <a class="btn-flat icon pull-right  delete-user" href="${editUrl }">
		                        <i class="icon-edit"></i>
		                    </a>
                    	</div>
	                    <div class="row-fluid" style="padding-left:30px; padding-top:10px;">
	                    	<c:url var="outUrl" value="/manager/dealers/boxin?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;" href="${outUrl }" target="_Blank">进货情况</a>
                    		<c:url var="feeUrl" value="/manager/dealers/employeefees?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;"  href="${feeUrl }" target="_Blank">市场开拓费用</a>
                    		<c:url var="saleUrl" value="/manager/dealers/expenses?did=${dealer.id }"></c:url>
                    		<a class="label label-info" style="margin-right:30px;"  href="${saleUrl }" target="_Blank">支持费用统计</a>
	                    </div>
                    </div>                   
                </div>
				<c:if test="${success != null}">
					<div class="alert alert-success" id="successMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="${success}" /></strong>
					</div>
					<script>
						$("#successMessage").delay(2000).slideUp("slow");
					</script>
				</c:if>
				<c:if test="${message != null}">
					<div class="alert alert-danger" id="failMessage">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong><c:out value="${message}" /></strong>
					</div>
					<script>
						$("#failMessage").delay(2000).slideUp("slow");
					</script>
				</c:if>
                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="bs-docs-baseinfo">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>名称：${dealer.name } </li>
	                            <li>联系人：${dealer.contact } </li>
	                            <li>手机号：${dealer.mobile } </li>
	                            <li>地址：${dealer.address } </li>
	                        </ul>
	                        <small>
	                        	<ul class="infolist-inline">                          
		                            <li>省份：${dealer.shengfen.name } </li>
		                            <li>地区：${dealer.diqu.name } </li>
		                            <li>县：${dealer.xian.name } </li>
		                            <li>乡镇：${dealer.zhen.name } </li>
		                            <li>邮编：${dealer.postcode } </li>
		                            <li>固定电话：${dealer.phone } </li>
		                            <li>所有者：${dealer.owner } </li>
		                            <li>所属公司：${dealer.company } </li>
		                        </ul>
		                        <c:if test="${dealer.pname!=null }">
		                        	<ul class="infolist-inline">                          
			                            <li>收款单位：${dealer.pname }</li>
			                            <li>地址：${dealer.paddress } </li>
			                            <li>联系人：${dealer.pcontact } </li>
			                            <li>联系电话：${dealer.pphone } </li>
			                        </ul>
		                        </c:if>
	                        </small>
                        </h4>         
                    </div>
                    <div class="bs-docs-operate">
                        <h4> 
                        	<ul class="baseinfolist">
	                            <li>竞争实力：${dealer.strength.name } </li>
	                            <li>商业信誉：${dealer.reputation.name } </li>
	                            <li>店铺类型：${dealer.shoptype.name } </li>
	                            <li>信誉等级：${dealer.creditrating.name } </li>
	                        </ul>
	                        <small>
	                        	<ul class="infolist-inline">                          
		                            <li>年营业额：${dealer.turnover } </li>
		                            <li>店铺面积：${dealer.area } </li>
		                            <li>员工总数：${dealer.population } </li>
		                            <li>分销店数：${dealer.fxdCount } </li>
		                            <li>主要经营范围：${dealer.business } </li>
		                            <li>主要销售区域：${dealer.territory } </li>
		                            <c:if test="${dealer.sfcjxsjh==1 }">
		                            	<li>进货经销商：${dealer.jhdea.name } </li>
		                            </c:if>		                             
		                        </ul>
		                        <c:if test="${dealer.description!=null }">
	                            	<ul class="infolist-inline">                          
			                            <li>评语：${dealer.description } </li>      
			                        </ul>
	                            </c:if>	
		                        
	                        </small>
                        </h4>  
                    </div>
                    
                    <div class="bs-docs-goal">
                        <div class="row-fluid" >
                       		<a class="btn-flat icon pull-right  delete-user" href="#editGoal-modal" style="margin-right:30px;" data-toggle="modal">
		                        <i class="icon-edit"></i>
		                    </a>		                    
                       	</div>
                       	<c:if test="${goal!=null }">
                       		<h4>
                       			<ul  class="baseinfolist">
                       				<li>完成目标：${goal.objective }
                       				<li>更新日期：<fmt:formatDate value='${goal.date }'
												type='date' pattern='yyyy-MM-dd' />
			                        </li>
                       			</ul>
                       			<small>
                       				<ul class="infolist-inline">
			                            <li>进货让利比例：${goal.promote } </li>
			                            <li>年终返点比例：${goal.rebate } </li>
			                            <li>支持额度：${goal.suportAmount } </li>
			                            <li>其他政策：${goal.other } </li>				                            
			                        </ul>
			                        <ul class="infolist-inline">
			                            <li>备注：${goal.remarks } </li>			                            
			                        </ul>
                       			</small>
                       		</h4>
	                    </c:if>
                    </div>
                    <!-- side address column -->
                    <div class="span12 address">               	
                        <div class="profile-box">
                        	<h6>特惠产品
                        		<small>
                        			<c:url var="moreprice" value="/manager/dealerprices/index?context=${dealer.name }"></c:url>
                        			<a class="icon pull-right" href="${moreprice }" style="margin:20px 10px 0 10px;">
					                    查看更多
					                </a>
                        			
                        			<c:url var="addprice" value="/manager/dealerprices/new?did=${dealer.id }"></c:url>
                        			<a class="icon pull-right" href="${addprice }" style="margin:20px 10px 0 10px;">
					                    <i class="icon-plus"></i>添加
					                </a>
                        		</small>
                        	</h6>
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span4">
                                            产品
                                        </th>
                                        <th class="span4">
                                            特惠价
                                        </th>
                                        <th class="span4">
                                            更新时间
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${dealerprices}" var="dealerprice">
			                        	<tr>
			                        		<td>
				                            	<c:url var ="boxinUrl" value = "/manager/productboxes/profile?id=${dealerprice.box.id }"></c:url>
	                                			<a href="${boxinUrl }" class="name" >${dealerprice.box.name }</a>
				                            </td>
				                            <td>
				                                ${dealerprice.price}
				                            </td>
			                        		<td>
				                                <fmt:formatDate value='${dealerprice.date}'
													type='date' pattern='yyyy-MM-dd' />
				                            </td>	         
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>

                            
                        </div>
                        
                        <div class="profile-box">
                        	<h6>支持费用和表现评价
                        		<small>
                        			<c:url var="morevalue" value="/manager/dealervalues/index?context=${dealer.name }"></c:url>
                        			<a class="icon pull-right" href="${morevalue }" style="margin:20px 10px 0 10px;">
					                    查看更多
					                </a>
                        			
                        			<c:url var="addprice" value="/manager/dealervalues/new?did=${dealer.id }"></c:url>
                        			<a class="icon pull-right" href="${addprice }" style="margin:20px 10px 0 10px;">
					                    <i class="icon-plus"></i>添加
					                </a>
                        		</small>
                        	</h6>
                            <!-- recent orders table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="span1 sortable">
		                                    让利金额
		                                </th>
		                                <th class="span1 sortable">
		                                    返点金额
		                                </th>
		                                <th class="span1 sortable">
		                                    奖励金额
		                                </th>
		                                <th class="span1 sortable">
		                                    样品赠送金额
		                                </th>
		                                <th class="span3 sortable">
		                                    表现评价
		                                </th>
		                                <th class="span2 sortable">
		                                    时间
		                                </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
			                        <c:set var="i" value="0" />
			                        <c:forEach items="${dealervalues}" var="dealervalue">
			                        	<tr>
			                        		<td>
				                            	${dealervalue.promote }
				                            </td>
				                            <td>
				                                ${dealervalue.rebate }
				                            </td>
				                            <td>
				                            	${dealervalue.reward }
				                            </td>
				                            <td>
				                                ${dealervalue.gift }
				                            </td>
				                            <td>
				                                ${dealervalue.description }
				                            </td>
				                            <td>
				                                <fmt:formatDate value='${dealervalue.date }'
																type='date' pattern='yyyy-MM-dd' />
				                            </td>	         
			                        	</tr>
			                        </c:forEach> 
                                </tbody>
                            </table>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->
    <div class="modal hide fade" id="editGoal-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editgoalUrl" value="editgoal" />
		<form id="editgoal-form" class="form-horizontal" action="${editgoalUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑基本任务</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="objective">完成目标</label>
					<div class="controls">
						<input type="text" class="span3" name="objective" id="objective" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${goal.objective }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="did" id="did" value="${dealer.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="promote">进货让利比例</label>
					<div class="controls">
						<input type="text" class="span3" name="promote" id="promote" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${goal.promote }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="rebate">年终返点比例</label>
					<div class="controls">
						<input type="text" class="span3" name="rebate" id="rebate" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${goal.rebate }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="suportAmount">支持额度</label>
					<div class="controls">
						<input type="text" class="span3" name="suportAmount" id="suportAmount" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${goal.suportAmount }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="other">其他政策</label>
					<div class="controls">
						<input type="text" class="span3" name="other" id="other" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${goal.other }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remarks">备注</label>
					<div class="controls">
						<textarea rows="3" path="remarks" name='remarks' id="remarks" class="span3" placeholder="" maxlength="50">${goal.remarks }</textarea>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	
	<div class="modal hide fade" id="editprice-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editpriceUrl" value="editprice" />
		<form id="editprice-form" class="form-horizontal" action="${editpriceUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑价格信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="unitprice">每单位产品出厂价格</label>
					<div class="controls">
						<input type="text" class="span3" name="unitprice" id="unitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.unitprice }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="boxid" id="boxid" value="${box.id }">
						<input type="hidden" name="pid" id="pid" value="${price.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="pieceprice">每件产品出厂价格</label>
					<div class="controls">
						<input type="text" class="span3" name="pieceprice" id="pieceprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.pieceprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="dealerUnitprice">每单位产品经销商价格</label>
					<div class="controls">
						<input type="text" class="span3" name="dealerUnitprice" id="dealerUnitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.dealerUnitprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="dealerPieceprice">每件产品经销商价格</label>
					<div class="controls">
						<input type="text" class="span3" name="dealerPieceprice" id="dealerPieceprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.dealerPieceprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="retailUnitprice">每单位产品零售价格</label>
					<div class="controls">
						<input type="text" class="span3" name="retailUnitprice" id="retailUnitprice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${price.retailUnitprice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
	
	<div class="modal hide fade" id="editlaborcost-modal" data-backdrop="static" data-keyboard="false">
		<c:url var="editlaborcostUrl" value="editlaborcost" />
		<form id="editlaborcost-form" class="form-horizontal" action="${editlaborcostUrl }" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>编辑生产用工信息</h3>
			</div>
			<br>
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="pielaborPrice">每件产品人工费用</label>
					<div class="controls">
						<input type="text" class="span3" name="pielaborPrice" id="pielaborPrice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${laborcost.pielaborPrice }"/>
						<span class="help-inline"></span>
						<input type="hidden" name="boxid" id="boxid" value="${box.id }">
						<input type="hidden" name="pid" id="pid" value="${laborcost.id }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="tonlaborPrice">每吨或千升产品人工费用</label>
					<div class="controls">
						<input type="text" class="span3" name="tonlaborPrice" id="tonlaborPrice" placeholder="" 
						style="IME-MODE: disabled;"  maxlength="20" onkeyup="clearNoNum(this)" value="${laborcost.tonlaborPrice }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label span2" for="login-btn"></label>
					<input type="submit" id="recipe-btn" value="确  定" class="btn btn-info span3">
				</div>
			</fieldset>
		</form>
	</div>
    
<script type="text/javascript">
$(document).ready(
		function() {			
			editboxFormValidate();
			editpriceFormValidate();
			editlaborcostFormValidate();
		});
		
function editboxFormValidate() {
	$("#editbox-form").validate(
			{
				debug : true,
				rules : {
					name : {
						required : true
					},
					code: {
						required : true
					},
					specification : {
						required : true
					},
					unit: {
						required : true
					},
					netcontent : {
						required : true
					},
					piececount: {
						required : true
					},
					piececontent : {
						required : true
					}
				},

				messages : {
					name : {
						required : "必填"
					},
					code : {
						required : "必填"
					},
					specification : {
						required : "必填"
					},
					unit : {
						required : "必填"
					},
					netcontent : {
						required : "必填"
					},
					piececount : {
						required : "必填"
					},
					piececontent : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}
function editpriceFormValidate() {
	$("#editprice-form").validate(
			{
				debug : true,
				rules : {
					unitprice : {
						required : true
					},
					pieceprice: {
						required : true
					},
					dealerUnitprice : {
						required : true
					},
					dealerPieceprice: {
						required : true
					},
					retailUnitprice : {
						required : true
					}
				},

				messages : {
					unitprice : {
						required : "必填"
					},
					pieceprice : {
						required : "必填"
					},
					dealerUnitprice : {
						required : "必填"
					},
					dealerPieceprice : {
						required : "必填"
					},
					retailUnitprice : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}

function editlaborcostFormValidate() {
	$("#editlaborcost-form").validate(
			{
				debug : true,
				rules : {
					pielaborPrice : {
						required : true
					},
					tonlaborPrice: {
						required : true
					}
				},

				messages : {
					pielaborPrice : {
						required : "必填"
					},
					tonlaborPrice : {
						required : "必填"
					}
				},
				errorClass : 'invalid',
				validClass : 'invalid',
				errorPlacement : function(error, element) {
					 element.nextAll(".help-inline").html(error);

				},
				success : function(label) {
					label.html("");
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
}

function deleteone(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = '<c:url value="index" />';
		}).fail(function() {
		});
	});
}

function newin(){
	var need = $("#need").val();
	if(need==1){
		bootbox.alert("卷膜包材需要配置质量转换信息");
		return;
	}
	window.location.href = '<c:url value="newin?pid=${productpack.id }" />';
}

function deleteone(id) {
	bootbox.confirm("确定要删除吗？", "取消", "确定", function(isOk) {
		if (!isOk) {
			return;
		}

		$.post('<c:url value="delete" />', {
			id : id
		}).done(function(data) {
			window.location.href = '<c:url value="index" />';
		}).fail(function() {
		});
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
</script>

<jsp:include page="../includes/footer.jsp" />