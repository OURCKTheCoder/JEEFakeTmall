<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./include/header.jsp"%>

<!-- Attributes: product, pList, pvMap -->
<div class="container">
	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">
			<p>修改属性值</p>
			<p>
				您正在修改 产品"
				<a href="./product_edit?id=${product.id}" target="_blank">${product.name}</a>
				"的一个属性值
			</p>
		</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="propertyValue_update?id=${item.id}">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>产品ID</td>
						<td><input type="hidden" name="pid" value="${product.id}">${product.id}</td>
					</tr>

					<c:forEach items="${pList}" var="pt">
					<tr>
						<td>${pt.name}</td>
						<td><input name="pt_${pt.id}" type="text" value="${pvMap[pt.id].value}"></td>
					</tr>
					</c:forEach>
				
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