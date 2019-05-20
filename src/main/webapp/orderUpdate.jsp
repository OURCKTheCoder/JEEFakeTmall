<%@page import="top.ourck.utils.TimeUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="./include/header.jsp"%>

<!DOCTYPE html>
<div class="container">

	<div class="panel panel-warning addDiv" style="“width: 550px;">
		<div class="panel-heading">订单细节：id = ${orderItem.id}</div>
		<div class="panel-body">
			<form method="post" id="addForm" action="order_update">
				<table class="addTable table table-responsive table-striped table-hover">
					<tr>
						<td><b>ID</b></td>
						<td>
							<input id="id" name="id" type="hidden" class="form-control" value="${orderItem.id}">
							<b>${orderItem.id}</b>
						</td>
					</tr>
					<tr>
						<td><b>订单细节ID</b></td>
						<td><b>${orderItem.order.id}</b></td>
					</tr>
					<tr>
						<td>订单细节信息</td>
						<td>
							<div>
								<table class="addTable table table-responsive table-striped table-hover">
									<tr>
										<td>用户ID</td>
										<td><input id="order_uid" name="order_uid" type="text" class="form-control" value="${orderItem.order.user.id}"></td>
									</tr>
									<tr>
										<td>订单号</td>
										<td><input id="order_orderCoded" name="order_orderCode" type="text" class="form-control" value="${orderItem.order.orderCode}"></td>
									</tr>
									<tr>
										<td>邮寄地址</td>
										<td><input id="order_address" name="order_address" type="text" class="form-control" value="${orderItem.order.address}"></td>
									</tr>
									<tr>
										<td>邮编</td>
										<td><input id="order_post" name="order_post" type="text" class="form-control" value="${orderItem.order.post}"></td>
									</tr>
									<tr>
										<td>收件人姓名</td>
										<td><input id="order_receiver" name="order_receiver" type="text" class="form-control" value="${orderItem.order.receiver}"></td>
									</tr>
									<tr>
										<td>收件人电话</td>
										<td><input id="order_mobile" name="order_mobile" type="text" class="form-control" value="${orderItem.order.mobile}"></td>
									</tr>
									<tr>
										<td>用户备注</td>
										<td><input id="order_userMessage" name="order_userMessage" type="text" class="form-control" value="${orderItem.order.userMessage}"></td>
									</tr>
									<tr>
										<td>创建日期</td>
										<td><input id="order_createDate" name="order_createDate" type="text" class="form-control" 
											value="<fmt:formatDate value='${orderItem.order.createDate}' pattern='<%=TimeUtils.DATE_PATTERN %>'/>"></td>
									</tr>
									<tr>
										<td>支付日期</td>
										<td><input id="order_payDate" name="order_payDate" type="text" class="form-control" 
											value="<fmt:formatDate value='${orderItem.order.payDate}' pattern='<%=TimeUtils.DATE_PATTERN %>'/>"></td>
									</tr>
									<tr>
										<td>发货日期</td>
										<td><input id="order_deliveryDate" name="order_deliveryDate" type="text" class="form-control" 
											value="<fmt:formatDate value='${orderItem.order.deliveryDate}' pattern='<%=TimeUtils.DATE_PATTERN %>'/>"></td>
									</tr>
									<tr>
										<td>确认收货日期</td>
										<td><input id="order_confirmDate" name="order_confirmDate" type="text" class="form-control" 
											value="<fmt:formatDate value='${orderItem.order.confirmDate}' pattern='<%=TimeUtils.DATE_PATTERN %>'/>"></td>
									</tr>
									<tr>
										<td>订单状态</td>
										<td><input id="order_status" name="order_status" type="text" class="form-control" value="${orderItem.order.status}"></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>订单产品ID</td>
						<td><input id="pid" name="pid" type="text" class="form-control" value="${orderItem.product.id}"></td>
					</tr>
					<tr>
						<td>用户ID</td>
						<td><input id="uid" name="uid" type="text" class="form-control" value="${orderItem.user.id}"></td>
					</tr>
					<tr>
						<td>数量</td>
						<td><input id="number" name="number" type="text" class="form-control" value="${orderItem.number}"></td>
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