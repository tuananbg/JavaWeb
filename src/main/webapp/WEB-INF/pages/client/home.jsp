<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang chủ</title>
    <style>
        .card-body {

            display: flex;
            flex-direction: column;
            justify-content: space-between; /* Đảm bảo các phần tử được phân bố đều */
            height: 100%; /* Chiếm toàn bộ chiều cao của thẻ card-body */
        }

        .card {
            margin-top: 10px;
            min-height: 350px;
        }
    </style>
</head>
<body>
<jsp:include page="include/homeHeader.jsp"></jsp:include>

<div class="wrap" style="width: 100%; margin: 0; padding: 0;">
    <div class="container-fluid ">
        <div class="row">
            <div>
                <div class="content">
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <a href="#">
                                    <img src="Frontend/img/acer.jpg" class="card-img-top" alt="Acer">
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title">Thương hiệu nổi bật</h5>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <a href="#">
                                    <img src="Frontend/img/lenovo2.png" class="card-img-top" alt="Lenovo"
                                         style="background-color: white;">
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title">Thương hiệu nổi bật</h5>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <a href="#">
                                    <img src="Frontend/img/dell.png" class="card-img-top" alt="Dell"
                                         style="background-color: white;">
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title">Thương hiệu nổi bật</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="content-grids">
            <h4 class="mb-5">DANH SÁCH LAPTOP MỚI NHẤT</h4>
        </div>
    </div>
    <div class="content-sidebar">
        <h4 class="mb-5">Danh mục</h4>
        <ul id="danhmuc">

        </ul>
    </div>
</div>
<div class="clear"></div>
</div>

<jsp:include page="include/homeFooter.jsp"></jsp:include>

<script src="<c:url value='/js/client/homeAjax.js'/>"></script>
<!--start-image-slider---->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
