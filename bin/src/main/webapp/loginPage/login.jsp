<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0080)http://ids.chd.edu.cn/authserver/login?service=http%3A%2F%2Fportal.chd.edu.cn%2F -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta content="width=device-width,initial-scale=0.8, minimum-scale=0.8, maximum-scale=3" name="viewport">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta property="qc:admins" content="754034015366713545637571540352652">
    <meta property="wb:webmaster" content="1ad39047f32b5b6b">
    <title>统一身份认证</title>
	<link href="./loginPage/CHDCASPortalStatic/login.css" rel="stylesheet">
	<link href="./loginPage/CHDCASPortalStatic/custom.css" rel="stylesheet">
</head>


<body>
<div class="auth_page_wrapper">
<div class="auth_logo">
    <img class="logoimg" src="./loginPage/CHDCASPortalStatic/logos.png" alt="logo">
	<div class="top-bar"><a href="http://www.chd.edu.cn/" target="_blank">长安大学主页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://bkjw.chd.edu.cn/eams/home.action" target="_blank">本科教务</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://123.chd.edu.cn/" target="_blank">长大导航</a></div>
</div>
<div class="auth_login_content">
    <div class="auth_login_left">
    </div>
    
        
        
            
        
        
    
    <div class="auth_login_right">
	    <img class="mainbg" src="./loginPage/CHDCASPortalStatic/web-bg1.png" alt="">
        <div class="auth_tab">
            <div class="auth_tab_links">
                <ul>
                    <li id="accountLogin" style="width:100%;" class="selected" tabid="01">
					    <img src="./loginPage/CHDCASPortalStatic/log-title.png" alt="">
					</li>
                    
		    

                </ul>
            </div>
            <div class="clearfloat"></div>
            <div class="auth_tab_content">
                <div tabid="01" class="auth_tab_content_item" style="display: block;">
                	<!-- 这里是真正的表单 -->
                    <form id="casLoginForm" class="fm-v clearfix amp-login-form" role="form" action="login" method="post">
                        
						<input id="target" name="target" type="hidden" value="${target}"> 
                        <p>
                            <span class="auth_icon">用户名</span>
                            <input id="username" name="username" placeholder="学号/职工号" class="auth_input" type="text" value="">
                            <span id="usernameError" style="display:none;" class="auth_error">请输入用户名</span>
                        </p>

                        <p>
                            <span class="auth_icon">密码</span>
                            <input id="password" name="password" placeholder="密码" class="auth_input" type="password" value="" autocomplete="off">
                            <span id="passwordError" style="display:none;" class="auth_error">请输入密码</span>
                        </p>

                        <p id="cpatchaDiv">

                        </p>

						 <p style="z-index: 1000;">
							<span class="session-area"><input type="checkbox" class="chkbox">记住我的密码</span>
							<a id="getBackPasswordMainPage" href="https://www.baidu.com/s?wd=%E5%BF%98%E8%AE%B0%E5%AF%86%E7%A0%81%E6%80%8E%E4%B9%88%E5%8A%9E"
								class="auth_login_forgetp" target="_blank">
                                <small>忘记密码？</small>
                            </a>
                        </p>
                        

                        <p>
                            <button type="submit" class="auth_login_btn primary full_width">登录
                            </button>
                        </p>
                        
						
						<p style="text-align: center;margin-top: 10px;"><strong>当前登录: <font color="red">录入系统 ${target}</font></strong></p>
                    </form>
                </div>
                
		
                
                
            </div>
        </div>
    </div>
</div>

<div class="clearfloat"></div>
<div class="auth_login_footer">
    <img class="footbg" src="./loginPage/CHDCASPortalStatic/chd_mobile.jpg" alt="">
	<div class="foottext">
	    <p><a href="http://ids.chd.edu.cn/authserver/warning/warning.htm" style="text-decoration: none;color:red;" target="_blank">统一身份认证防诈骗提醒</a></p>
		<p><a href="http://app.chd.edu.cn/" style="text-decoration: none;color:#00AEAE;" target="_blank">长安大学移动门户APP</a></p>
		<p>版权所有©长安大学 | 信息与网络管理处维护</p>
	</div>
</div>
</div>

<script type="text/javascript" src="./loginPage/CHDCASPortalStatic/jquery.min.js"></script>
<script type="text/javascript" src="./loginPage/CHDCASPortalStatic/icheck.min.js"></script>

</body></html>
