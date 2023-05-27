<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
	
	<div class="login">
		<form class="form" id="form-1" name="f1" action="login" onsubmit="return validate()" method="post">
			<div class="form-group">
				<h1>Sign in</h1>
				<p class="error">${mess}</p>
				<input class="ip" id="user" value="${user_cookie}" type="text" name="usermail" placeholder="Enter email">
				<input class="ip" id="pass" type="password" name="password" placeholder="Enter PassWord">
				<label><input type="checkbox" name="remember">Remember me</label><br><br>
				
				<input type="submit" class="ip" id="login-btn" value="Login"><br><br>
				
				<a href="register.jsp"><button id="register-btn" type="button" onclick="">Go to Register</button></a><br>
				<a href="home?page"><button id="backtohome-btn" type="button" onclick="">Back to Homepage</button></a><br>
			</div>			
		</form>
		
		<div class="welcome">
			<h1>Welcome Back</h1>
			<p>To keep connected with us please login with your peronal info</p>
		</div>
	</div>
	
	<script type="text/javascript">
		function validate(){
			let us = f1.usermail.value;
			let pw = f1.password.value;
			
			if(us == ""){
				alert("Please type Username")
				return false;
			}if(pw == ""){
				alert("Please type Password")
				return false;
			}
		}	
	</script>
			
</body>
</html>