<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Laptop Shop - Đăng nhập</title>
    <link rel="stylesheet" href="Frontend/css/login.css"> <!-- Liên kết tới file CSS tùy chỉnh -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <!-- Liên kết tới Bootstrap 5 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Liên kết tới JavaScript của Bootstrap 5 -->
</head>

<body style="background-color: #98cbfc">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="col-md-4">
        <div class="card p-4 shadow-sm">
            <h2 class="form-signin-heading text-center">LaptopShop - Đăng nhập</h2>
            <hr>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <p>Tên đăng nhập hoặc mật khẩu không đúng.</p>
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    <p>Bạn đã đăng xuất thành công.</p>
                </div>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <div class="alert alert-danger">
                    <p>Bạn không có quyền truy cập vào trang này</p>
                </div>
            </c:if>

            <form method="POST" action="${contextPath}/login">
                <div class="mb-3">
                    <input type="text" placeholder="Email" name="email" required="required" class="form-control"/>
                </div>
                <div class="mb-3">
                    <input type="password" placeholder="Mật khẩu" name="password" required="required"
                           class="form-control"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" name="remember-me" id="rememberMe">
                    <label class="form-check-label" for="rememberMe">Duy trì đăng nhập</label>
                </div>
                <button id="submit" type="submit" class="btn btn-primary w-100">ĐĂNG NHẬP</button>
                <p class="message text-center mt-3">Chưa có tài khoản? <a href="<c:url value='/register'/>">Tạo tài
                    khoản mới</a></p>
            </form>
        </div>
    </div>
</div>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>
