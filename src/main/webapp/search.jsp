<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Search Page</title>	
	<link rel="stylesheet" href="assets/css/mystyle.css">
</head>
<body>
	<!-- Header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- Body -->
	<div id="main-content">
		<div id="content-wrapper">
		<h6 class="error">${mess}</h6>
			<c:forEach items="${products_search}" var="o">
				<div class="item">
					<a href="InformationProductController?id=${o.id}"><img src="${o.src}" alt=""></a>
					<h4 class="product-type">${o.type}</h4>
					<h4 class="product-name">${o.name}</h4>
					<p class="product-price">$${o.price}</p>
				</div>
			</c:forEach>
		</div>
		
		<div id="pages-nav">
			<c:forEach begin="1" end="${endP_search}" var="i">
				<a class="${page_search_active == i ? 'active' : '' }" href="SearchController?search=${nameS}&page=${i}">${i}</a>
			</c:forEach>	
		</div>
	</div>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>
		
</body>
</html>