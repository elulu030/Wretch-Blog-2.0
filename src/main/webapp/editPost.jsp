<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
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
    <form method="post" action="/jakartaee-hello-world/EditPost">
        <input type="hidden" name="id" value="<%= post != null ?  post.get("id") : "" %>">
        <div class="mb-3">
            <label for="title" class="form-label">標題</label>
            <input type="text" class="form-control" id="title" name="title" required maxlength="100" value="<%= post != null ? post.get("title") : "" %>">
        </div>
        <div class="mb-3">
            <label for="author" class="form-label">作者暱稱</label>
            <input type="text" class="form-control" id="author" name="author" required maxlength="30" value="<%= post != null ? post.get("author") : "" %>">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">內容</label>
            <textarea class="form-control" id="content" name="content" rows="7" required maxlength="2000"><%= post != null ? post.get("content") : "" %></textarea>
        </div>
        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-success btn-lg">儲存修改</button>
            <a href="http://localhost:8080/jakartaee-hello-world/test_postList" class="btn btn-secondary btn-lg">返回列表</a>
        </div>
    </form>
</div>
</body>
</html> 