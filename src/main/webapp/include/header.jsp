<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>FakeTmall - 理想生活上假天猫</title>
	<link rel="stylesheet" href="/JEEFakeTmall/css/bootstrap.min.css">  
	<script src="/JEEFakeTmall/js/jquery.min.js"></script>
	<script src="/JEEFakeTmall/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar container-fluid navbar-inverse" role="navigation">
		<div class="navbar-header">
			<img class="navbar-brand" src="/JEEFakeTmall/img/logo.png">
		</div>
		
		<div>
			<ul class="nav navbar-nav">
				<li><a href="/JEEFakeTmall/admin/category_list">品类管理</a></li>
				<li><a href="/JEEFakeTmall/admin/user_list">用户管理</a></li>
				<li><a href="/JEEFakeTmall/admin/product_list">产品管理</a></li>
				<li><a href="/JEEFakeTmall/admin/order_list">订单管理</a></li>
				<li><a href="/JEEFakeTmall/admin/login?target=logout">退出登录</a></li>
			</ul>
		</div>
	</nav>
