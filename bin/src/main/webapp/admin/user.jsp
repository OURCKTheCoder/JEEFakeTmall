<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">新增用户</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="user_add">
				<table class="addTable table table-responsive table-striped table-hoverS">
					<tr>
						<td>用户名称</td>
						<td><input id="name" name="name" type="text" class="form-control" value="${oldName}"></td>
					</tr>
					<tr>
						<td>用户密码</td>
						<td><input id="passwd" name="passwd" type="text" class="form-control" value="${oldPwd}"></td>
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
					<th>用户名</th>
					<th>密码</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.password}</td>
						<td>
							<a href="user_edit?id=${item.id}">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
						</td>
						<td>
							<a href="user_delete?id=${item.id}">
								<span class="glyphicon glyphicon-trash"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>