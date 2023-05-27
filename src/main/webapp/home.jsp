<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home Page</title>
	<link rel="stylesheet" href="assets/css/mystyle.css">	
</head>
<body>
	<!-- Header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- Body -->
	<div id="main-content">
		<div id="content-wrapper">
			<c:forEach items="${sessionScope.products}" var="o">
				<div class="item">
					<a href="InformationProductController?id=${o.id}"><img src="${o.src}" alt=""></a>
					<h4 class="product-type">${o.type}</h4>
					<h4 class="product-name">${o.name}</h4>
					<p class="product-price">$${o.price}</p>
				</div>
			</c:forEach>		
		</div>
		
		<div id="pages-nav">
			<c:forEach begin="1" end="${sessionScope.endP}" var="i">
				<a class="${sessionScope.page_active == i ? 'active' : '' }" href="home?page=${i}">${i}</a>
			</c:forEach>			
		</div>		
	</div>	
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
		
</body>
</html>