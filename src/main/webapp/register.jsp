<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="assets/css/register.css">
</head>

<body>
	<div class="register">
		<form class="form" id="form-2" name="f2" action="register" onsubmit="return validate()" method="post">
			<div class="form-group">
				<h1>Sign up</h1>
				<p class="form-error">${mess}</p>
				<input class="ip" id="user" type="text" name="usr" value="${usr}" placeholder="Enter email">
				<input class="ip" id="pass" type="password" name="pwd" value="${pwd}" placeholder="Enter PassWord">				
				<input class="ip" id="accrole" type="number" name="role" value="${role}" placeholder="Enter Role">
				<input class="ip" id="username" type="text" name="name" value="${name}" placeholder="Enter Name">
				<input class="ip" id="useradress" type="text" name="address" value="${address}" placeholder="Enter Adress">
				<input class="ip" id="userphone" type="number" name="phone" value="${phone}" placeholder="Enter Phone"><br><br>
				
				<input type="submit" class="ip" id="register-btn" value="Register"><br><br>
				
				<a href="login"><button id="login-btn" type="button" onclick="">Go to Login</button></a><br>
				<a href="home?page"><button id="backtohome-btn" type="button" onclick="">Back to Homepage</button></a><br>				
			</div>
		</form>

		<div class="welcome">
			<h1>Welcome</h1>
			<p>Please SignUp to connected with us</p>
		</div>
	</div>

	<script type="text/javascript">
		function validate(){
			let us = f2.usr.value;
			let pw = f2.pwd.value;
			let ro = f2.role.value;
			let na = f2.name.value;
			let ad = f2.address.value;
			let ph = f2.phone.value;
			
			if(us == ""){
				alert("Please type Usermail")
				return false;
			}if(pw == ""){
				alert("Please type Password")
				return false;
			}if(ro == ""){
				alert("Please type Account Role")
				return false;
			}if(na == ""){
				alert("Please type UserName")
				return false;
			}if(ad == ""){
				alert("Please type Address")
				return false;
			}if(ph == ""){
				alert("Please type PhoneNumber")
				return false;
			}
		}	
	</script>	

</body>

</html>