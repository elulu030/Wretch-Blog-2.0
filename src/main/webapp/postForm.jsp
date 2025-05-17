<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>發表新文章 - 無名致敬</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f8fafc 0%, #e0c3fc 100%);
            min-height: 100vh;
        }
        .nameless-header {
            font-family: 'Comic Sans MS', '微軟正黑體', cursive;
            color: #6c3483;
            letter-spacing: 2px;
            text-shadow: 1px 1px 0 #fff, 2px 2px 0 #d2b4de;
        }
        .post-form {
            background: #fff;
            border-radius: 1rem;
            box-shadow: 0 4px 24px rgba(108,52,131,0.08);
            padding: 2rem;
        }
        .btn-nameless {
            background: linear-gradient(90deg, #a18cd1 0%, #fbc2eb 100%);
            color: #fff;
            font-weight: bold;
            border: none;
        }
        .btn-nameless:hover {
            background: linear-gradient(90deg, #fbc2eb 0%, #a18cd1 100%);
            color: #6c3483;
        }
    </style>
</head>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script>
        alert("<%= errorMessage %>"); 
    </script>
<%
    }
%>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-6">
            <h2 class="text-center mb-4 nameless-header">無名小站致敬 - 發表新文章</h2>
            <form class="post-form" method="post" action="/jakartaee-hello-world/test_postForm" onsubmit="return validateForm()">
                <div class="mb-3">
                    <label for="title" class="form-label">標題</label>
                    <input type="text" class="form-control" id="title" name="title" required maxlength="30">
                </div>
                <%
				    String userName = (String) session.getAttribute("user_name");
				    if (userName == null) {
				        userName = "";
				    }
				%>
                <div class="mb-3">
                    <label for="author" class="form-label">作者暱稱</label>
                    <input type="text" class="form-control" id="author" name="author" required maxlength="30" value="<%= userName %>">
                </div>
                <div class="mb-3"> 
				    <input class="form-check-input" type="checkbox" id="needPwd" onclick="togglePwdInput()">
				    <label class="form-check-label" for="needPwd">需要密碼</label>
				</div>
                <div class="mb-3" id="pwdGroup" style="display: none;">
                    <label for="pwd" class="form-label">密碼(數字)</label>
                     <input type="text" class="form-control" id="pwd" name="pwd" maxlength="10" inputmode="numeric">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">內容</label>
                    <textarea class="form-control" id="content" name="content" rows="7" required maxlength="2000"></textarea>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-nameless btn-lg">送出文章</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function togglePwdInput() {
        const checkbox = document.getElementById("needPwd");
        const pwdGroup = document.getElementById("pwdGroup");
        pwdGroup.style.display = checkbox.checked ? "block" : "none";
    }
    
    function validateForm() {
        const needPwdChecked = document.getElementById("needPwd").checked;
        if (needPwdChecked) {
            const pwd = document.getElementById("pwd").value;
            if (pwd.length > 10) {
                alert("密碼長度不可超過10位");
                return false; // 阻止送出
            }
            if (!/^\d*$/.test(pwd)) {
                alert("密碼必須是純數字");
                return false; // 阻止送出
            }
        }
        return true; // 通過驗證，允許送出
    }
</script>
</body>
</html> 

