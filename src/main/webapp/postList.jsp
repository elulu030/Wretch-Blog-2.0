<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>貼文列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script>
        alert("<%= errorMessage %>");
        setTimeout(function () {
            window.location.href = "http://localhost:8080/jakartaee-hello-world/test_postList";
        }, 500);
    </script>
<%
    }
%>
<body>
<div class="container py-5">
    <h2 class="mb-4">貼文列表</h2>
    <a href="http://localhost:8080/jakartaee-hello-world/test_postForm" class="btn btn-primary mb-3">發表新文章</a>
    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>標題</th>
                <th>作者</th>
                <th>發表時間</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <% 
        List<Map<String, Object>> posts = (List<Map<String, Object>>) request.getAttribute("posts");
        if (posts != null) {
            for (Map<String, Object> post : posts) {
        %>
            <tr>
                <td><%= post.get("id") %></td>
                <td><%= post.get("title") %></td>
                <td><%= post.get("author") %></td>
                <td><%= post.get("Content") %></td>
                <td><%= post.get("created_at") %></td>
                <td>
                    <a href="http://localhost:8080/jakartaee-hello-world/EditPost?id=<%= post.get("id") %>" class="btn btn-sm btn-warning">編輯</a>
                    <a href="http://localhost:8080/jakartaee-hello-world/DeletePost?id=<%= post.get("id") %>" class="btn btn-sm btn-danger" onclick="return confirm('確定要刪除嗎？');">刪除</a>
                </td>
            </tr>
        <% 
            }
        } 
        %>
        </tbody>
    </table>
</div>
</body>
</html>
