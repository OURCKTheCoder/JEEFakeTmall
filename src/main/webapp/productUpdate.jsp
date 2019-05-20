<%@page import="top.ourck.utils.TimeUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">修改商品 id = ${p.id}</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="product_update">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td><b>商品id</b></td>
						<td>
							<input id="id" name="id" type="hidden" class="form-control" value="${p.id}">
							<b>${p.id}</b>
						</td>
					</tr>
					<tr>
						<td>商品名称</td>
						<td><input id="name" name="name" type="text" class="form-control" value="${p.name}"></td>
					</tr>
					<tr>
						<td>商品描述</td>
						<td><input id="subTitle" name="subTitle" type="text" class="form-control" value="${p.subTitle}"></td>
					</tr>
					<tr>
						<td>商品原价</td>
						<td><input id="originalPrice" name="originalPrice" type="text" class="form-control" value="${p.originalPrice}"></td>
					</tr>
					<tr>
						<td>商品促销价</td>
						<td><input id="promotePrice" name="promotePrice" type="text" class="form-control" value="${p.promotePrice}"></td>
					</tr>
					<tr>
						<td>商品库存</td>
						<td><input id="stock" name="stock" type="text" class="form-control" value="${p.stock}"></td>
					</tr>
					<tr>
						<!-- 这里应该是一个下拉框 以保证参照完整性 -->
						<td>商品品类id</td>
						<td><input id="category" name="category" type="text" class="form-control" value="${p.category.id}"></td>
					</tr>
					<tr>
						<!-- 这里应该是一个下拉框 以保证参照完整性 -->
						<td>创建时间</td>
						<td><input id="createDate" name="createDate" type="text" class="form-control" 
									value="<fmt:formatDate value='${p.createDate}' pattern='<%=TimeUtils.DATE_PATTERN %>'/>"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="btn btn-success">提 交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</div>



<%@ include file="./include/footer.jsp"%>