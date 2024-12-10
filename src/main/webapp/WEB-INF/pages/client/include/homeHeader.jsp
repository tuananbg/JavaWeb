<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="Frontend/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='//fonts.googleapis.com/css?family=Londrina+Solid|Coda+Caption:800|Open+Sans' rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet" href="Frontend/css/responsiveslides.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="Frontend/js/responsiveslides.min.js"></script>
    <script src="js/client/accounting.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="icon" href="<c:url value='/resources/Frontend/images/logo.png'/>" type="image/png">

</head>

<body>

<div class="mb-5">
    <!-- Start Header -->
    <header class="">
        <div class="row align-items-center">
            <!-- Logo -->

            <div class="col-2"></div>
            <!-- Image Slider -->
            <div class="col-8">
                <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel"
                     data-bs-interval="5000">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img src="Frontend/img/laptop1.jpg" class="img-fluid" alt="Slide 1">
                        </div>
                        <div class="carousel-item">
                            <img src="Frontend/img/laptop2.png" class="img-fluid" alt="Slide 2">
                        </div>
                        <div class="carousel-item">
                            <img src="Frontend/img/laptop3.png" class="img-fluid" alt="Slide 3">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Header Top Nav -->
            <div class="col-2">
                <div class="header-top-nav">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <ul class="nav">
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/logout" class="nav-link">
                                    <i class="bi bi-box-arrow-right"></i> Đăng xuất
                                </a>
                            </li>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </header>

    <div class="top-header">
        <div class="d-flex justify-content-start align-items-center">
            <!-- Navigation -->
            <nav class="top-nav">
                <ul class="nav d-flex justify-content-start align-items-center w-100">
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/" class="nav-link1">
                            <img src="Frontend/img/logo3.png" title="logo" class="img-fluid1"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/" class="nav-link">
                            <i class="bi bi-house"></i> Trang chủ
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/store" class="nav-link">
                            <i class="bi bi-cart"></i> Sản phẩm
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/cart" class="nav-link">
                            <i class="bi bi-basket"></i> Giỏ hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/contact" class="nav-link">
                            <i class="bi bi-envelope"></i> Phản hồi
                        </a>
                    </li>

                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a href="<%=request.getContextPath()%>/login" class="nav-link">
                                <i class="bi bi-person-fill"></i> Đăng nhập
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a href="<%=request.getContextPath()%>/account" class="nav-link">
                                <i class="bi bi-person-circle"></i> ${loggedInUser.hoTen}
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>

        </div>

    </div>
    <!-- Tìm kiếm -->
    <div class="d-flex justify-content-center mt-3">
        <form action="/laptopshop/search" class="d-flex">
            <input type="search" name="name" class="form-control me-2" placeholder="Tìm kiếm" aria-label="Tìm kiếm">
            <button type="submit" class="btn btn-primary">
                <i class="bi bi-search"></i> <!-- Biểu tượng tìm kiếm từ Bootstrap Icons -->
            </button>
        </form>
    </div>

    <!-- Tạo container để căn giữa biểu mẫu -->

</div>

<div id="overlay" class="overlay"
     style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); z-index: 999;"></div>

<script src="<c:url value='/js/client/header.js'/>"></script>
</body>

</html>
