<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	*{
  font-family:微軟正黑體;  
}

body{
  background-color: white;
}

#username, #password, h3, #fullname, #comfirm_password,#username2, #password2{
  width: 200px;
  height: 20px;
  margin: 10px;
  color: #df5334;
}

h5{
  margin: 20px;
  color: #a3a2a3;
}

h5:hover{
  color: black;
}

#container1, #container2{
  /* margin: 50px; */
  padding: 10px;
  width: 230px;
  height: 300px;
  background-color: white;
  border-radius: 5px;
  border-top: 10px solid #df5334;
  box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
  
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  //text-align:center;  
}

.system_name{
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  text-align:center; 
}

.submit{
  color: white;  
  background: #df5334;
  width: 200px;
  height: 30px;
  margin: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 0px;
}

.submit:hover{
  background: #db6937;
}

#container2{
  visibility: hidden;   /*剛開始消失*/
  height: 350px;
}


#copyright{
  text-align: center;
  color: #a3a2a3;
  margin: -200px 0px 0px 0px;
  font-size: 14px;
}

input{
  padding: 5px;
  border: none; 
  border:solid 1px #ccc;
  border-radius: 5px;
}
</style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String user_name = null;
    String user_pass = null;
    String login_user = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("user_name".equals(cookie.getName())) {
                user_name = cookie.getValue();
            }
        }
    }

    String session_user = (String) session.getAttribute("user_name");

    if (session_user != null) {
        login_user = session_user;
    }

    if (user_name == null) user_name = "";
    if (user_pass == null) user_pass = "";
    if (login_user == null) login_user = "";
%>
	<div class="system_name">
		<h2>○○系統</h2>
  	</div>
	  <div class="login_page">
		<div id="container1">
		  <div class="login">  
			<h3>登入 Login</h3>
	<form action="/jakartaee-hello-world/login" id="myform" method="post">
    	<input type="text" name="user" id="user"  placeholder="帳號" value="<%= user_name %>" required>
		<label style="font-size: 12px;">
  			<input type="checkbox" name="remember" id="remember" value="true"> 記住我
		</label><br>
		<div class="tab"></div>
    	<input type="password" name="login_password" id="login_password" placeholder="密碼" required><br>
		<div class="tab"></div>
    	<input type="submit" value="登入" class="submit">
	</form>
	
	<h5 onclick="show_hide()">註冊帳號</h5>
	<h5 onclick="goToForgetPwd()">忘記密碼</h5>
	<h5 id="msg" style="color:red;"></h5>

			</div><!-- login end-->
		</div><!-- container1 end-->
	</div><!-- login_page end-->
	
	<div class="signup_page">
      <div id="container2">
        <div class="signup">  
          
          <h3>註冊 Sign Up</h3>

          <form action="/jakartaee-hello-world/register" id="resiger_form" method="post">
            	<input type="text" id="user_mail" name="user" placeholder="使用者信箱" required>
            <div class="tab"></div>
            	<input type="text" id="register_password" name="register_password" placeholder="密碼" required>
            <div class="tab"></div>
            	<input type="radio" name="sex" value="male" id="sex_male"> Male
				<input type="radio" name="sex" value="female" id="sex_female"> Female
            <div class="tab"></div>
            <input type="text" id="phone" name="phone" placeholder="手機電話" required>
            <div class="tab"></div>            
            <input type="submit" value="註冊" class="submit">
          </form>  

          <h5 onclick="show_hide()">登入帳號</h5>
          
        </div><!-- signup end-->
      </div><!-- container2 end-->
    </div><!-- signup_page end--> 

<!--     <div id="copyright">
      <h4>Copyright © 2025 RoseWang All rights reserved</h4>因為js，會跑版 
    </div>  -->

	<form id="identify_form" method="post" action="/jakartaee-hello-world/identify">
    	<input type="hidden" id="identify_code" name="identify_code">
    	<input type="hidden" name="user_mail" value="<%= login_user %>">
	</form>
</body>
<script>
	function show_hide() {
    var login = document.getElementById("container1");
    var signup = document.getElementById("container2");
    var copyright = document.getElementById("copyright");
  
    if (login.style.display === "none") {
        login.style.display = "block";  //lonin出現
        document.getElementById("user").value="";
        document.getElementById("login_password").value="";
		document.getElementById("remember").value="";
        signup.style.display = "none";  //signup消失
        copyright.style.margin = "200px 0px 0px 0px";
    } else {
        login.style.display = "none";   //login消失
        signup.style.display = "block"; //signup出現
        signup.style.visibility="visible";
        copyright.style.margin = "200px 0px 0px 0px";
     
        document.getElementById("user_mail").value="";
        document.getElementById("register_password").value="";
        document.getElementById("sex").value="";
        document.getElementById("phone").value="";
    }
}

	function goToRegister() {
	    window.location.href = "register_Form.jsp";
	}
	
	function goToForgetPwd() {
	    window.location.href = "fgPwd.jsp";
	}
	
	document.getElementById("myform").addEventListener("submit",check);
	
	function check(event){
		var user = document.getElementById("user").value;
	    var password = document.getElementById("password").value;
	    var msg = document.getElementById("msg");
	
	    if (user == undefined || user === "") {
	        alert("帳號錯誤");
	        msg.innerHTML = "帳號錯誤";
	        event.preventDefault();
	    }else if (password == undefined || password === "") {
	        alert("密碼不可為空");
	        msg.innerHTML = "密碼不可為空";
	        event.preventDefault();
	    }else if (password === "admin" && user === "admin"){
	    	alert("進入開發者模式");
	    }
	}
	
	<%
    String errorMessage = (String) request.getAttribute("errorMessage");
	Boolean CodeInput = (Boolean) request.getAttribute("CodeInput");
	%>
	window.onload = function () {
        var msg = document.getElementById("msg");
        <% if (errorMessage != null) { %>
            msg.innerHTML = "<%= errorMessage %>";
        <% } %>
        <% if (CodeInput != null && CodeInput) { %>
            var identifyCode = prompt("請輸入識別碼：", "");

            if (identifyCode !== null && identifyCode !== "") {
            	document.getElementById("identify_code").value = identifyCode;
                document.getElementById("identify_form").submit(); 
            } else {
                alert("識別碼未輸入，請重新嘗試");
            }
        <% } %>
    };

</script>
</html>