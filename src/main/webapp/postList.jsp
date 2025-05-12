<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ include file="/WEB-INF/config.jsp" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>貼文列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<c:if test="${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
        setTimeout(function () {
            window.location.href = "${baseURL}/jakartaee-hello-world/test_postList";
        }, 500);
    </script>
</c:if>

<div class="container py-5">
    <h2 class="mb-4">貼文列表</h2>
    <a href="${baseURL}/jakartaee-hello-world/test_postForm" class="btn btn-primary mb-3">發表新文章</a>
    <a href="${baseURL}/jakartaee-hello-world/index" class="btn btn-primary mb-3">返回首頁</a>
    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>標題</th>
                <th>作者</th>
                <th>密碼</th>
                <th>內容</th>
                <th>發表時間</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.title}</td>
                <td>${post.author}</td>
                <td>${post.pwd}</td>
                <td>${post.Content}</td>
                <td>${post.created_at}</td>
                <td>
                    <a href="${baseURL}/jakartaee-hello-world/EditPost?id=${post.id}" class="btn btn-sm btn-warning">編輯</a>
                    <a href="${baseURL}/jakartaee-hello-world/DeletePost?id=${post.id}" class="btn btn-sm btn-danger" onclick="return confirm('確定要刪除嗎？');">刪除</a>
                </td>
            </tr>
           </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
