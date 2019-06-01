<%@page import="top.ourck.utils.TimeUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./include/header.jsp"%>

<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">新增订单</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="order_add">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td>订单细节信息</td>
						<td>
							<div>
								<table class="addTable table table-responsive table-striped table-hover">
									<tr>
										<td>用户ID</td>
										<td><input id="order_uid" name="order_uid" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>订单号</td>
										<td><input id="order_orderCode" name="order_orderCode" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>邮寄地址</td>
										<td><input id="order_address" name="order_address" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>邮编</td>
										<td><input id="order_post" name="order_post" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>收件人姓名</td>
										<td><input id="order_receiver" name="order_receiver" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>收件人电话</td>
										<td><input id="order_mobile" name="order_mobile" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>用户备注</td>
										<td><input id="order_userMessage" name="order_userMessage" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>创建日期（日期格式<%=TimeUtils.DATE_PATTERN %>，下同）</td>
										<td><input id="order_createDate" name="order_createDate" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>支付日期</td>
										<td><input id="order_payDate" name="order_payDate" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>发货日期</td>
										<td><input id="order_deliveryDate" name="order_deliveryDate" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>确认收货日期</td>
										<td><input id="order_confirmDate" name="order_confirmDate" type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>订单状态</td>
										<td><input id="order_status" name="order_status" type="text" class="form-control"></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>订单产品ID</td>
						<td><input id="pid" name="pid" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>用户ID</td>
						<td><input id="uid" name="uid" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>数量</td>
						<td><input id="number" name="number" type="text" class="form-control"></td>
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
					<th>产品ID</th>
					<th>订单细节</th>
					<th>用户ID</th>
					<th>数量</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td><a target="_blank" href="./product_edit?id=${item.product.id}">${item.product.name}</a></td>
						<td><a target="_blank" href="./order_edit?id=${item.id}">查看详情...</a></td>
						<td><a target="_blank" href="./user_edit?id=${item.user.id}">${item.user.name}</a></td>
						<td>${item.number}</td>
						<td>
							<a href="order_edit?id=${item.id}">
								<span class="glyphicon glyphicon-edit"></span>
							</a>
						</td>
						<td>
							<a href="order_delete?id=${item.id}">
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