<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SDGs 學生作品集</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${baseURL}/jakartaee-hello-world/css/custom-theme.css" rel="stylesheet">
    <style>
        /* 您可以在這裡為作品集頁面添加特定樣式 */
        .work-card {
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }
        .work-card:hover {
            transform: translateY(-5px);
        }
        .work-card img {
            max-width: 100%;
            height: auto;
            border-bottom: 1px solid #eee;
            object-fit: cover;
        }
        .work-title {
            font-size: 1.25rem;
            margin-top: 10px;
            margin-bottom: 5px;
            color: var(--secondary-color);
        }
        .work-description {
            font-size: 0.9rem;
            color: #666;
        }
        .sdg-badge {
            display: inline-block;
            background-color: #A084CA; /* 深紫色 */
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 0.8rem;
            margin-top: 10px;
            margin-right: 5px;
        }
    </style>
</head>
<body>

    <!-- 導航欄 - 可以根據您的需要複製現有導航欄或簡化 -->
    <nav class="navbar navbar-expand-lg navbar-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="${baseURL}/jakartaee-hello-world/index">無名小站</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${baseURL}/jakartaee-hello-world/index">首頁</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${baseURL}/jakartaee-hello-world/sdgs_works.jsp">SDGs 作品集</a>
                    </li>
                    <c:if test="${not empty sessionScope.user_name}">
                        <li class="nav-item">
                            <a class="nav-link" href="${baseURL}/jakartaee-hello-world/test_postList">文章列表</a>
                        </li>
                    </c:if>
                </ul>
                <div class="d-flex">
                    <c:choose>
                        <c:when test="${empty sessionScope.user_name}">
                            <a href="${baseURL}/jakartaee-hello-world/login" class="btn btn-outline-light me-2">登入</a>
                        </c:when>
                        <c:otherwise>
                            <span class="text-white me-3">歡迎，${sessionScope.user_name}</span>
                            <a href="${baseURL}/jakartaee-hello-world/logout" class="btn btn-outline-light">登出</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>

    <!-- 主要內容區 -->
    <div class="container mt-4">
        <h1 class="mb-4 text-center">SDGs 學生作品集</h1>
        <p class="text-center mb-5">這裡展示學生們為永續發展目標 (SDGs) 所創作的優秀作品。</p>

        <div class="row">
            <!-- 作品卡片範例 1 -->
            <div class="col-md-4">
                <div class="card work-card">
                    <img src="https://via.placeholder.com/300x200?text=Work+1" class="card-img-top" alt="作品 1">
                    <div class="card-body">
                        <h5 class="card-title work-title">作品名稱：永續水資源利用</h5>
                        <p class="work-description">本作品探討如何有效節約水資源，並設計出智慧用水系統。</p>
                        <div class="sdg-badges">
                            <span class="sdg-badge">SDG 6 - 潔淨水與衛生</span>
                            <span class="sdg-badge">SDG 12 - 負責任的消費與生產</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 作品卡片範例 2 -->
            <div class="col-md-4">
                <div class="card work-card">
                    <img src="https://via.placeholder.com/300x200?text=Work+2" class="card-img-top" alt="作品 2">
                    <div class="card-body">
                        <h5 class="card-title work-title">作品名稱：社區綠色能源推廣</h5>
                        <p class="work-description">透過社區活動推廣太陽能與風力發電，提高居民對綠色能源的認識。</p>
                        <div class="sdg-badges">
                            <span class="sdg-badge">SDG 7 - 永續能源</span>
                            <span class="sdg-badge">SDG 11 - 永續城鄉</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 作品卡片範例 3 -->
            <div class="col-md-4">
                <div class="card work-card">
                    <img src="https://via.placeholder.com/300x200?text=Work+3" class="card-img-top" alt="作品 3">
                    <div class="card-body">
                        <h5 class="card-title work-title">作品名稱：海洋塑膠垃圾回收計畫</h5>
                        <p class="work-description">設計一套高效的海洋垃圾回收裝置，並呼籲大眾減少塑膠使用。</p>
                        <div class="sdg-badges">
                            <span class="sdg-badge">SDG 14 - 海洋生態</span>
                            <span class="sdg-badge">SDG 15 - 陸域生態</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 您可以在這裡添加更多作品卡片 -->

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 