<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Outside - Minimalist Ecommerce HTML Template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="/JEEFakeTmall/img/favicon.ico">
    
    <!-- All CSS Files -->
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="/JEEFakeTmall/css/bootstrap.min.css">
    <!-- Icon Font -->
    <link rel="stylesheet" href="/JEEFakeTmall/css/font-awesome.min.css">
    <link rel="stylesheet" href="/JEEFakeTmall/css/pe-icon-7-stroke.css">
    <!-- Plugins css file -->
    <link rel="stylesheet" href="/JEEFakeTmall/css/plugins.css">
    <!-- Theme main style -->
    <link rel="stylesheet" href="/JEEFakeTmall/style.css">
    <!-- Responsive css -->
    <link rel="stylesheet" href="/JEEFakeTmall/css/responsive.css">

    <!-- Modernizr JS -->
    <script src="/JEEFakeTmall/js/vendor/modernizr-2.8.3.min.js"></script>
    
</head>

<body>

     


<!-- Body main wrapper start -->
<div class="wrapper">

<!-- START HEADER SECTION -->
<header class="header-section section sticker">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <!-- logo -->
                <div class="header-logo float-left">
                    <a href="/JEEFakeTmall/index.html">
                    	<img src="/JEEFakeTmall/img/logo.png" alt="main logo">
                    </a>
                </div>
                
                <!-- header-search & total-cart -->
                <div class="float-right">
                    
                    <!-- some button -->
                    <nav class="main-menu menu-right float-right">
                        <ul>
                        	<!-- 跳转主页面 -->
                            <li><a href="/JEEFakeTmall/index.html">Home</a></li>                       
                            <!-- 跳转购物车页面 -->
							<li><a href="/JEEFakeTmall/cart.html">cart</a></li>
							<!-- 跳转个人信息主页 -->
							 <li><a href="/JEEFakeTmall/detail.html">personal</a>
                                <ul class="sub-menu">
                                    <li><a href="/JEEFakeTmall/detail.html">details</a></li>
                                    <li><a href="/JEEFakeTmall/order.html">My order</a></li>
                                </ul>
                            </li>
                            <!-- 跳转登录页面 -->
  							<li><a id="user_name" href="/JEEFakeTmall/login.html">login</a></li>  
                           
                        </ul>
                    </nav>
                </div>
                <div class="mobile-menu"></div>
            </div>
        </div>
    </div>
</header>

<!-- PAGE BANNER SECTION -->
<div class="page-banner-section section">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-banner-content">
                    <h1>Product Details</h1>
                    <ul class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Product Details</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END PAGE BANNER SECTION -->
    
<!-- PAGE SECTION START -->
<div class="page-section section pt-120 pb-120">
    <div class="container">
        <div class="row mb-40">
            <!-- Single Product Images -->
            <div class="col-md-5 col-sm-6 col-xs-12 mb-40">	
                <!-- Tab panes -->
                <div class="tab-content mb-10">
                    <div class="pro-large-img tab-pane active" id="pro-large-img-1">
                        <img src="/JEEFakeTmall/img/product/10.jpg" alt="" />
                    </div>
                    <div class="pro-large-img tab-pane" id="pro-large-img-2">
                        <img src="/JEEFakeTmall/img/product/11.jpg" alt="" />
                    </div>
                    <div class="pro-large-img tab-pane" id="pro-large-img-3">
                        <img src="/JEEFakeTmall/img/product/12.jpg" alt="" />
                    </div>
                    <div class="pro-large-img tab-pane" id="pro-large-img-4">
                        <img src="/JEEFakeTmall/img/product/13.jpg" alt="" />					
                    </div>
                    <div class="pro-large-img tab-pane" id="pro-large-img-5">
                        <img src="/JEEFakeTmall/img/product/14.jpg" alt="" />
                    </div>
                </div>
                <!-- Single Product Thumbnail Slider -->
                <div class="pro-thumb-img-slider">
                    <div><a href="#pro-large-img-1" data-toggle="tab"><img src="/JEEFakeTmall/img/product/10.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-2" data-toggle="tab"><img src="/JEEFakeTmall/img/product/11.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-3" data-toggle="tab"><img src="/JEEFakeTmall/img/product/12.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-4" data-toggle="tab"><img src="/JEEFakeTmall/img/product/13.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-5" data-toggle="tab"><img src="/JEEFakeTmall/img/product/14.jpg" alt="" /></a></div>
                </div>
            </div>
            <!-- Single Product Details -->
            <div class="col-md-7 col-sm-6 col-xs-12 mb-40">
                <div class="product-details section">
                    <!-- Title -->
                    <h1 class="title">${p.name}</h1>
                    <!-- Price Ratting -->
                    <div class="price-ratting section">
                        <!-- Price -->
                        <span class="price float-left"><span class="new">$ ${p.originalPrice}</span></span>
                        <!-- Ratting -->
                        <span class="ratting float-right">
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                        </span>
                    </div>
                    <!-- Short Description -->
                    <div class="short-desc section">
                        <h5 class="pd-sub-title">Quick Overview</h5>
                        <p>产品名称：${p.name}</p>
                        <p>产品描述：${p.subTitle}</p>
                        <p>火热促销中！</p>
                    </div>
                    <!-- Quantity Cart -->
                    <div class="quantity-cart section">
                        <button class="add-to-cart" onclick="addToCart(${p.id}, 1)">add to cart</button>
                    </div>
                    <!-- Usefull Link -->
                    <ul class="usefull-link section">
                        <li><a href="mailto:2016900667@chd.edu.cn"><i class="pe-7s-mail"></i> Email to a Friend</a></li>
                    </ul>
                    <!-- Share -->
                    <div class="share-icons section">
                        <span>share :</span>
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <!-- Nav tabs -->
            <div class="col-xs-12">
                <ul class="pro-info-tab-list section">
                    <li class="active"><a href="#data-sheet" data-toggle="tab">Data sheet</a></li>
                    <li><a href="#reviews" data-toggle="tab">Reviews</a></li>
                </ul>
            </div>
            <!-- Tab panes -->
            <div class="tab-content col-xs-12">
                <div class="pro-info-tab tab-pane active" id="data-sheet">
                    <table class="table-data-sheet">
                        <tbody>
							<c:forEach items="${pvList}" var="pv">
                        		<tr>
                        			<td>${pv.property.name}</td>
                        			<td>${pv.value}</td>
                        		</tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pro-info-tab tab-pane" id="reviews">
                    <a href="#">Be the first to write your review!</a>
                </div>
            </div>		
        </div>
    </div>
</div>
<!-- PAGE SECTION END -->

<!-- FOOTER TOP SECTION START -->
<div class="footer-top-section section pt-100 pb-60">
	<div class="container">
		<div class="row">
			<!-- Footer Widget -->
			<div
				class="footer-widget text-center col-md-12 col-sm-6 col-xs-12 mb-40">
				<h5 class="widget-title">ABOUT THE STORE</h5>
				<p>You can find many fantastic goods here.However you can get
					them in no way.</p>
			</div>
		</div>
	</div>
</div>
<!-- FOOTER TOP SECTION END -->

<!-- FOOTER BOTTOM SECTION START -->
<div class="footer-bottom-section section">
	<div class="container">
		<div class="row">

			<!-- Copyright -->
			<div class="copyright  col-sm-6 col-xs-12 ">
				<p>Powered by Cytra. Cobbom Ourck.top</p>

			</div>
		</div>
	</div>
</div>
<!-- FOOTER BOTTOM SECTION END -->

</div>
<!-- Body main wrapper end -->


<!-- Placed JS at the end of the document so the pages load faster -->

<!-- jQuery latest version -->
<script src="/JEEFakeTmall/js/vendor/jquery-3.1.1.min.js"></script>
<!-- Bootstrap js -->
<script src="/JEEFakeTmall/js/bootstrap.min.js"></script>
<!-- Plugins js -->
<script src="/JEEFakeTmall/js/plugins.js"></script>
<!-- Ajax Mail js -->
<script src="/JEEFakeTmall/js/ajax-mail.js"></script>
<!-- Main js -->
<script src="/JEEFakeTmall/js/main.js"></script>

<script type="text/javascript">
var addToCart = function(pid, num){
	 $.ajax({ 
	     url: "http://localhost:8080/JEEFakeTmall/cart/update?op=add", 
	     type: 'get',  
	     data:"pid=" + pid + "&num=" + num,
	     success: function(data){
	     }
	 });
}

$(document).ready(function(){
	$.ajax({ 
	     url: "http://localhost:8080/JEEFakeTmall/user/islogin", 
	     type: 'get',  
	     success: function(data){
	    	 if(data.success == 'true') {
		    	 var userArea = document.getElementById('user_name');
		    	 userArea.href='/JEEFakeTmall/user/logout';
		    	 userArea.innerHTML='Welcome, ' + data.user + '! : )';
	    	 }
	     }              
	 });
})
</script>

</body>

</html>
