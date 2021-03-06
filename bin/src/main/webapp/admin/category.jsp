<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>

<div class="container">

	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>分类名称</th>
					<th>属性管理</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>
							<a href="./property_list?categoryId=${item.id}">
								<span class="glyphicon glyphicon-list"></span>
							</a>
						</td>
						<td>
							<a href="category_edit?id=${item.id}">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
						</td>
						<td>
							<a href="category_delete?id=${item.id}">
								<span class="glyphicon glyphicon-trash"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">新增分类</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="category_add">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>分类名称</td>
						<td><input id="name" name="name" type="text"
							class="form-control"></td>
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