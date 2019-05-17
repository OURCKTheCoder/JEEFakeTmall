<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">修改商品 id = ${p.id}</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="product_update">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>商品id</td>
						<td><input id="id" name="id" type="text" class="form-control" value="${p.id}"></td>
					</tr>
					<tr>
						<td>商品名称</td>
						<td><input id="name" name="name" type="text" class="form-control" value="${p.name}"></td>
					</tr>
					<tr>
						<td>商品描述</td>
						<td><input id="name" name="subTitle" type="text" class="form-control" value="${p.subTitle}"></td>
					</tr>
					<tr>
						<td>商品原价</td>
						<td><input id="name" name="originalPrice" type="text" class="form-control" value="${p.originalPrice}"></td>
					</tr>
					<tr>
						<td>商品促销价</td>
						<td><input id="name" name="promotePrice" type="text" class="form-control" value="${p.promotePrice}"></td>
					</tr>
					<tr>
						<td>商品库存</td>
						<td><input id="name" name="stock" type="text" class="form-control" value="${p.stock}"></td>
					</tr>
					<tr>
						<!-- 这里应该是一个下拉框 以保证参照完整性 -->
						<td>商品品类id</td>
						<td><input id="name" name="category" type="text" class="form-control" value="${p.category.id}"></td>
					</tr>
					<tr>
						<!-- 这里应该是一个下拉框 以保证参照完整性 -->
						<td>创建时间</td>
						<td><p>${p.createDate}</p></td>
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