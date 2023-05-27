<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Ordered</title>
</head>

<body>
	<!-- Header -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- Body -->
	<div id="cart-wrapper">
		<%-- Bảng dữ liệu cart --%>
		<table id="product-cart"  style="width: 100%">
			<thead>
				<tr class="item">
					<th class="item-orderid">Order ID</th>
					<th class="item-name" style="width: 40%">Product name</th>
					<th class="item-img">Product image</th>
					<th class="item-price">Price</th>
					<th class="item-quatity">Quantity</th>
					<th class="item-amount" style="width: 10%">Amount</th>
					<th class="item-total" style="width: 10%"></th>
				
				</tr>
			</thead>
			<tbody>				
				<c:forEach items="${listOrdered}" var="o">		
					<tr class="item">				
						<td>${o.orderId}</td>
						<td>${o.nameProduct}</td>
						<td><img alt="" src="${o.src}"></td>
						<td>$${o.price}</td>
						<td>${o.quatity}</td>												
						<td>$${Math.round(o.price * o.quatity * 100.0) / 100.0}</td>
						<td><a href=""></a></td>
					</tr>
				</c:forEach>				
				<%-- Total --%>
				<tr class="item">					
					<td colspan="6"></td>
					<td></td>					
				</tr>
				<tr class="submit-order">					
					<td colspan="6"></td>
					<td></td>					
				</tr>
			</tbody>
		</table><br>		
		<%-- Bảng dữ liệu khách hàng --%>
		<c:if test="${sessionScope.login_user != null}">
			<table id="customer-info" style="width: 30%">		
				<thead>
					<tr>
						<th style="width: 20%">Customer name</th>
						<th style="width: 10%">Customer address</th>
					</tr>		
				</thead>					
				<tbody>
					<tr>
						<th style="width: 20%">Customer name</th>
						<th style="width: 10%">Customer address</th>
						<td>${sessionScope.login_user.name}</td>
						<td>${sessionScope.login_user.address}</td>			
					</tr>					
				</tbody>
			</table>				
		</c:if>		
		
		<div>
			<c:if test="${sessionScope.login_user == null}">
				<p style="color:red">Please login to create the order now</p>			
			</c:if>				
		</div>
				
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>