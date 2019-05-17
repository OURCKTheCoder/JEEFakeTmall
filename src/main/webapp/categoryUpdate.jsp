<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">修改分类</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="category_update">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>分类id</td>
						<td><input id="id" name="id" type="text" class="form-control" value="${id}"></td>
					</tr>
					<tr>
						<td>分类名称</td>
						<td><input id="name" name="name" type="text" class="form-control" value="${oldName}"></td>
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