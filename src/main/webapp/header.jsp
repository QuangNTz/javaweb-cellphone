<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/mystyle.css">
<script src="https://kit.fontawesome.com/a4ec82e01d.js"	crossorigin="anonymous"></script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Smooch&display=swap');
</style>
</head>

<body>
	<div id="header">
		<h6>${welcomeToUser}</h6>
		<div class="header-left">
			<h1 class="header-item">PRJ321x - ASM3</h1>
			<p class="header-item">Welcome to my Website</p>
		</div>
		<div class="header-right">
			<form action="SearchController" method="get">
				<input class="header-search" name="search" value="${nameS}"
					placeholder="What are you looking for?">
			</form>
		</div>
	</div>

	<div id="topnav">
		<ul class="nav-list nav-list-left">
			<li><a href="home?page" style="color: orange">Home</a></li>
			<li><a href="#">Products</a></li>
			<li><a href="#">About us</a></li>
		</ul>

		<ul class="nav-list nav-list-right">		
			<c:if test="${sessionScope.login_user == null}">
				<!-- Servlet:home(ListController) -> (dlieu -> home.jsp) -->
				<li><a href="login">Login</a></li>			
			</c:if>
			<c:if test="${sessionScope.login_user != null}">
				<li><a href="logout">Logout</a></li>			
			</c:if>
			<c:if test="${sessionScope.login_user == null}">
				<li><a href="register.jsp">Register</a></li>
			</c:if>
			<li><a href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a></li>
			<c:if test="${sessionScope.login_user != null}">
				<!-- run OrderedController -> (dlieu -> ordered.jsp) -->
				<li><a href="OrderedController"><i class="fa-solid fa-bag-shopping"></i></a></li>			
			</c:if>			
			
		</ul>
	</div>
</body>

</html>