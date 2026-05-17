<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm bài viết</title>
</head>
<body>

    <h2>Tìm kiếm bài viết</h2>

    <form action="${pageContext.request.contextPath}/search" method="get">
        <input type="text" name="keyword" value="${keyword}" placeholder="Nhập từ khóa" />
        <button type="submit">Tìm kiếm</button>
    </form>

    <hr>

    <!-- Nếu có keyword -->
    <c:if test="${not empty keyword}">
        <p>Kết quả cho: <b><c:out value="${keyword}"/></b></p>
    </c:if>

    <c:if test="${not empty posts}">
        <c:forEach var="post" items="${posts}">
            <div style="margin-bottom: 15px;">
                <h3><c:out value="${post.title}"/></h3>
                <p><c:out value="${post.body}"/></p>
                <p><i>Đăng bởi: <c:out value="${post.author}"/> vào <c:out value="${post.createdAt}"/></i></p>
            </div>
        </c:forEach>
    </c:if>

    <!-- Nếu không có kết quả -->
    <c:if test="${empty posts and not empty keyword}">
        <p>Không tìm thấy kết quả</p>
    </c:if>

</body>
</html>