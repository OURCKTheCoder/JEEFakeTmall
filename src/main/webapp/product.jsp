<%@page import="top.ourck.utils.TimeUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">新增商品</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="product_add">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>商品名称</td>
						<td><input id="name" name="name" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>商品描述</td>
						<td><input id="name" name="subTitle" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>商品原价</td>
						<td><input id="name" name="originalPrice" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>商品促销价</td>
						<td><input id="name" name="promotePrice" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>商品库存</td>
						<td><input id="name" name="stock" type="text" class="form-control"></td>
					</tr>
					<tr>
						<!-- 这里应该是一个下拉框 以保证参照完整性 -->
						<td>商品品类id</td>
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
					<th>属性修改</th>
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
						<td><a target="_blank" href="./category_edit?id=${item.category.id}">${item.category.name}</a></td>
						<td>
							<fmt:formatDate value="${item.createDate}" pattern="<%=TimeUtils.DATE_PATTERN %>"/>
						</td>
						<td>
							<a href="propertyValue_edit?pid=${item.id}">
								<span class="glyphicon glyphicon-list"></span>
							</a>
						</td>
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