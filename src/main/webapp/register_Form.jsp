<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>

	<h1>Register</h1>
	<form action="/jakartaee-hello-world/register" id="resiger_form" method="post">
	<label for="user">User Mail:</label>
	<input type="text" name="user" id="user_mail">
	<br>
	<label for="passwd">PassWord:</label>
	<input type="text" name="passwd" id="password">
	<br>
	<label for="sex">Sex:</label>
	<input type="radio" name="sex" value="male" id="sex_male"> Male
	<input type="radio" name="sex" value="female" id="sex_female"> Female
	<br>
	<label for="phone">Phone:</label>
	<input type="text" name="phone" id="phone">
	<br>
	<input type="hidden" name="baseurl" id="baseurl" value="${baseURL}">
	<input type="submit" value="send">
	</form>
	<button onclick="goToLogin()">返回登入</button>
	<p id="msg"></p>
</body>
<script>
	function goToLogin() {
	    window.location.href = "login_Form.jsp";
	}
	
	document.getElementById("resiger_form").addEventListener("submit",insert);
	
	function insert(event){
		var user = document.getElementById("user_mail").value;
	    var password = document.getElementById("password").value;
	    var phone = document.getElementById("phone").value;
	    var msg = document.getElementById("msg");
	
	    if (user == undefined || user === "") {
	        alert("帳號錯誤");
	        msg.innerHTML = "帳號錯誤";
	        event.preventDefault();
	    }
	
	    else if (password == undefined || password === "") {
	        alert("密碼不可為空");
	        msg.innerHTML = "密碼不可為空";
	        event.preventDefault();
	    }
	    
	    else if (phone == undefined || phone === "") {
	        alert("名稱錯誤");
	        msg.innerHTML = "名稱錯誤";
	        event.preventDefault();
	    }
	    
	    else if (email == undefined || email === "") {
	        alert("信箱錯誤");
	        msg.innerHTML = "信箱錯誤";
	        event.preventDefault();
	    }
	}
	
	<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
	%>
        msg.innerHTML = "<%= errorMessage %>";
    <%
    }
	%>

</script>
</html>