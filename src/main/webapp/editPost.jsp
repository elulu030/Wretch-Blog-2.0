<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ include file="/WEB-INF/config.jsp" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>編輯貼文</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
    <h2 class="mb-4">編輯貼文</h2>
    <%
	Map<String, Object> post = (Map<String, Object>) request.getAttribute("post");
	%>
    <form method="post" action="/jakartaee-hello-world/EditPost" onsubmit="return validateForm()">
        <input type="hidden" name="id" value="<%= post != null ?  post.get("id") : "" %>">
        <div class="mb-3">
            <label for="title" class="form-label">標題</label>
            <input type="text" class="form-control" id="title" name="title" required maxlength="30" value="<%= post != null ? post.get("title") : "" %>">
        </div>
        <div class="mb-3">
            <label for="author" class="form-label">作者暱稱</label>
            <input type="text" class="form-control" id="author" name="author" required maxlength="30" value="<%= post != null ? post.get("author") : "" %>">
        </div>
         <div class="mb-3"> 
				    <input class="form-check-input" type="checkbox" id="needPwd" onclick="togglePwdInput()">
				    <label class="form-check-label" for="needPwd">需要密碼</label>
				</div>
                <div class="mb-3" id="pwdGroup" style="display: none;">
                    <label for="pwd" class="form-label">密碼(限數字)</label>
                    <input type="text" class="form-control" id="pwd" name="pwd" maxlength="10" inputmode="numeric">
                </div>
        <div class="mb-3">
            <label for="content" class="form-label">內容</label>
            <textarea class="form-control" id="content" name="content" rows="7" required maxlength="2000"><%= post != null ? post.get("Content") : "" %></textarea>
        </div>
        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-success btn-lg">儲存修改</button>
            <a href="${baseURL}/jakartaee-hello-world/test_postList" class="btn btn-secondary btn-lg">返回列表</a>
        </div>
    </form>
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
        return true; // 驗證通過允許送出
    }
</script>
</body>
</html> 