<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/config.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>無名小站 - 部落格</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f5f5f5 0%, #e6e6fa 100%);
            min-height: 100vh;
        }
        .navbar {
            background: linear-gradient(135deg, #9370db 0%, #8a2be2 100%);
        }
        .navbar-brand {
            color: white !important;
            font-size: 1.5rem;
            font-weight: bold;
        }
        .nav-link {
            color: white !important;
        }
        .post-card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin: 20px auto;
            /* margin-bottom: 20px; */
            width: 60%;
            max-width: 800px;
            padding: 20px;
        }
        .post-title {
            color: #4a4a4a;
            font-size: 1.5rem;
            margin-bottom: 10px;
        }
        .post-meta {
            color: #666;
            font-size: 0.9rem;
            margin-bottom: 15px;
        }
        .post-content {
            color: #333;
            line-height: 1.6;
            margin-bottom: 15px;
        }
        .post-content.collapsed {
            max-height: 100px;
            overflow: hidden;
        }
        .read-more {
            color: #9370db;
            cursor: pointer;
            text-decoration: none;
        }
        .read-more:hover {
            text-decoration: underline;
        }
        .pagination {
            margin-top: 30px;
        }
        .page-link {
            color: #9370db;
        }
        .page-item.active .page-link {
            background-color: #9370db;
            border-color: #9370db;
        }
        .comment {
		    margin-bottom: 15px; /* 每條留言之間的間距 */
		    padding: 10px; /* 留言區塊的內邊距 */
		    background-color: #f9f9f9; /* 背景顏色 */
		    border-radius: 5px; /* 邊框圓角 */
		    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 輕微陰影效果 */
		}
		
		.comment p {
		    margin: 0; /* 移除段落的上下邊距 */
		}
		
		.comment .text-muted {
		    font-size: 0.8rem; /* 設定留言時間的字型大小 */
		}
    </style>
</head>
<body>

	<c:if test="${not empty errorMessage}">
    <script>
        alert("${errorMessage}");
    </script>
	</c:if>
    <!-- 導航欄 -->
    <nav class="navbar navbar-expand-lg navbar-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="${baseURL}/jakartaee-hello-world/index">無名小站</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <c:choose>
    					<c:when test="${not empty sessionScope.user_name}">
       						 <li class="nav-item">
           					 <a class="nav-link" href="${baseURL}/jakartaee-hello-world/test_postList">文章列表</a>
        					 </li>
   						 </c:when>
					</c:choose>
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
    <div class="container">
        <!-- 文章列表 -->
        <div class="row">
            <div class="col-12">
                <c:forEach items="${posts}" var="post">
                    <div class="post-card">
                        <h2 class="post-title">${post.title}</h2>
                        <div class="post-meta">
                            <span class="me-3">作者：${post.author}</span>
                            <span>發表時間：<fmt:formatDate value="${post.created_at}" pattern="yyyy-MM-dd HH:mm"/></span>
                        </div>
                        <div class="post-content collapsed" id="content-${post.id}">
                            <c:choose>
						        <c:when test="${not empty post.pwd}">
						            <div id="protected-${post.id}">
						                <input type="password" id="inputPwd-${post.id}" class="form-control mb-2" placeholder="請輸入密碼以查看內容" style="width: calc(50% - 8px); display: inline-block;">
    									<button class="btn btn-sm btn-primary mb-2" onclick="checkPwd('${post.pwd}', ${post.id})" style="display: inline-block;">提交</button>
						                <div id="error-${post.id}" class="text-danger mt-2" style="display:none;">密碼錯誤，請再試一次。</div>
						            </div>
						            <div id="realContent-${post.id}" style="display: none;">
						                ${post.Content}
						            </div>
						        </c:when>
						        <c:otherwise>
						            ${post.Content}
						        </c:otherwise>
						    </c:choose>
                        </div>
                        <c:choose>
						    <c:when test="${not empty post.pwd}">
						        <div id="buttons-${post.id}" style="display: none;">
						    </c:when>
						    <c:otherwise>
						        <div id="buttons-${post.id}">
						    </c:otherwise>
						</c:choose>
						    <a class="read-more" id="read-more-${post.id}" onclick="toggleContent(${post.id})">閱讀更多</a>
						    <a class="read-more" onclick="showCommentBox(${post.id})">留言</a>
						</div>
	                        <div id="comment-box-${post.id}" style="display: none; margin-top: 10px;">
								<textarea id="comment-text-${post.id}" rows="3" cols="60" placeholder="輸入留言內容..."></textarea><br>
								<a class="read-more" onclick="submitComment(${post.id})">送出留言</a>
							</div>
							
							<div class="comments-section" id="comments-${post.id}" style="display: none;">
						        <c:forEach items="${messageposts}" var="comment">
						            <c:if test="${comment.content_id == post.id}">
						                <div class="comment">
						                    <p><strong>${comment.user_name}:</strong><br> 
						                    ${comment.message}</p>
						                   <p class="text-muted">
							                    <small>
							                        留言時間：
							                        <fmt:formatDate value="${comment.time}" pattern="yyyy-MM-dd HH:mm" />
							                        <c:if test="${comment.user_id == sessionScope.user_id || sessionScope.user_id == 1 || post.user_id == sessionScope.user_id}">
							                            &nbsp;|&nbsp;
							                            <a href="${baseURL}/jakartaee-hello-world/deleteMs?id=${comment.id}" class="text-danger">刪除留言</a>
							                        </c:if>
							                    </small>
							                </p>
							            </div>
							        </c:if>
						        </c:forEach>
						    </div>
						    
                    	</div>
                </c:forEach>
            </div>
        </div>

        <!-- 分頁 -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    	const userId = '<%= session.getAttribute("user_id") %>';
    	
    
        function toggleContent(postId) {
        	if (!userId || userId === "null") {
            	alert("請先登入");
                window.location.href = "/jakartaee-hello-world/login_Form.jsp";
                return;
            }
            const content = document.getElementById('content-' + postId);
            const readMoreLink = document.getElementById('read-more-' + postId);
            const commentsSection = document.getElementById('comments-' + postId)
            
            if (content.classList.contains('collapsed')) {
                content.classList.remove('collapsed');
                if (readMoreLink) readMoreLink.textContent = '收起';
                commentsSection.style.display = 'block';
            } else {
                content.classList.add('collapsed');
                if (readMoreLink) readMoreLink.textContent = '閱讀更多';
                commentsSection.style.display = 'none';
            }
        }
        
        function checkPwd(correctPwd, postId) {
            const input = document.getElementById("inputPwd-" + postId).value;
            const contentDiv = document.getElementById("realContent-" + postId);
            const errorDiv = document.getElementById("error-" + postId);

            if (input === correctPwd) {
                contentDiv.style.display = "block";
                document.getElementById("protected-" + postId).style.display = "none";
                document.getElementById("buttons-" + postId).style.display = "block";
            } else {
                errorDiv.style.display = "block";
            }
        }
        
        function showCommentBox(postId) {
        	const box = document.getElementById("comment-box-" + postId);
            box.style.display = (box.style.display === 'none') ? 'block' : 'none';
        }
        
        function submitComment(postId) {
            const comment = document.getElementById("comment-text-" + postId).value.trim();

            if (!comment) {
                alert("請輸入留言內容！");
                return;
            }
            if (!userId || userId === "null") {
            	alert("請先登入");
                window.location.href = "/jakartaee-hello-world/login_Form.jsp";
                return;
            }

            const formData = new URLSearchParams();
            formData.append("content_id", postId);   
            formData.append("message", comment);     

            fetch('/jakartaee-hello-world/message', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formData.toString()
            })
            .then(response => {
                window.location.href = "/jakartaee-hello-world/index";
            })
            .catch(error => {
                alert("留言送出失敗");
                console.error(error);
            });
        }
        
    </script>
</body>
</html> 