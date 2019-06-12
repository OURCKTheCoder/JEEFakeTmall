<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>

<div class="container">
	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">
			<p>修改属性</p>
			<p>您正在修改 品类"<a href="./category_edit?id=${item.category.id}">${item.category.name}</a>"的一个属性</p>
		</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="property_update?id=${item.id}">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>ID</td>
						<td>
							<input id="id" name="id" type="hidden" class="form-control" value="${item.id}">
							<b>${item.id}</b>
						</td>
					</tr>
					<tr>
						<td>属性ID</td>
						<td>
							<input id="cid" name="cid" type="hidden" class="form-control" value="${item.category.id}">
							<b>${item.category.id}</b>
						</td>
					</tr>
					<tr>
						<td>属性名称</td>
						<td><input id="name" name="name" type="text"
							class="form-control" value="${item.name}"></td>
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

<%@ include file="../include/footer.jsp"%>