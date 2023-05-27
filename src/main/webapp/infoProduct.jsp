<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Product Info</title>
</head>

<body>
	<!-- Header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- Body -->
	<div id="product-wrapper">
		<h3 class="product-name">${productInfo.name}</h3>

		<div class="product-info">
			<div class="product-image">
				<a href=""><img src="${productInfo.src}" alt=""></a>
			</div>
			<div class="product-detail">
				<h1 class="product-price">$${productInfo.price}</h1>
				<p class="product-des">${productInfo.description}</p>
				<p class="product-des">Lorem ipsum dolor sit, amet consectetur
					adipisicing elit. Exercitationem voluptatem, laborum error dolor
					illum quisquam facere earum harum, unde quae libero delectus
					possimus odit culpa numquam. Deleniti dolorem ad dicta. Lorem ipsum
					dolor sit amet consectetur adipisicing elit. Amet nulla possimus
					consequuntur explicabo! Asperiores alias dolores similique laborum
					voluptatem accusantium qui culpa maiores distinctio rerum autem,
					minima corrupti nesciunt ab? Lorem, ipsum dolor sit amet
					consectetur adipisicing elit. Laboriosam in repellat voluptatibus
					corrupti quod temporibus soluta earum? Eligendi dolorem neque
					corporis fugiat ipsa et blanditiis officiis deleniti, cumque hic
					est.</p>

<%-- 				<form action="addtocart?id=${productInfo.getId()}" method="get"> --%>
					
<!-- 					<input type="submit" value="Add to cart"> -->
<!-- 				</form> -->
				<a href="addtocart?action=add&id=${productInfo.id}">Add to cart</a>				
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>