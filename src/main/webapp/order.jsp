<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">新增订单</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="product_add">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>OrderItem id</td>
						<td><input id="name" name="category" type="text" class="form-control"></td>
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

	<div class="listDataTableDiv">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>产品名</th>
					<th>产品描述</th>
					<th>原价</th>
					<th>促销价</th>
					<th>库存</th>
					<th>品类</th>
					<th>入库时间</th>
					<th>编辑</th>
					<th>删除</th>
					<!-- 图片上传！ -->
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.subTitle}</td>
						<td>${item.originalPrice}</td>
						<td>${item.promotePrice}</td>
						<td>${item.stock}</td>
						<td>${item.category.name}</td>
						<td>${item.createDate}</td>
						<td>
							<a href="product_edit?id=${item.id}">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
						</td>
						<td>
							<a href="product_delete?id=${item.id}">
								<span class="glyphicon glyphicon-trash"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="./include/footer.jsp"%>