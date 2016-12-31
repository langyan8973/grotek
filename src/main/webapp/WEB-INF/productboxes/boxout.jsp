<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table class="table table-bordered">
  <tr>
    <td class="span2">出库时间</td>
    <td class="span2"><fmt:formatDate value='${out.date }' type='date' pattern='yyyy-MM-dd HH:mm' /></td>
  </tr>
  <tr>
    <td class="span2">出库产品</td>
    <td class="span2">${out.box.name }</td>
  </tr>
  <tr>
    <td class="span2">出库数量</td>
    <td class="span2">${out.count }件</td>
  </tr>
  <tr>
    <td class="span2">经销商</td>
    <td class="span2">${out.dealer.name }件</td>
  </tr>
</table>

