<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Cửa hàng</title>
    <link rel="stylesheet" href="Frontend/css/searchResult.css">
    <script src="Frontend/js/jquery.min.js"></script>
    <script src="Frontend/js/responsiveslides.min.js"></script>
    <script src="js/client/accounting.js"></script>
    <script src="<c:url value='/js/client/store.js'/>"></script>
    <script type="text/javascript"></script>
    <style>
        .page-link.current {
            background-color: #007bff; /* Màu nền active */
            color: white; /* Màu chữ */
            border-color: #007bff; /* Viền */
            cursor: default;
            pointer-events: none; /* Ngăn không cho click */
        }

    </style>
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="row">
                <c:if test="${list.size() == 0}">
                    <div class="col-12">
                        <h4>Không tìm thấy kết quả nào</h4>
                    </div>
                </c:if>
                <c:forEach var="sanpham" items="${list}" varStatus="loop">
                    <div class="col-md-4 mb-4">
                        <div class="card h-150 text-center" style="height: 425px">
                            <a href="sp?id=${sanpham.id}">
                                <img class="card-img-top" style="height: 230px; object-fit: cover;"
                                     src="/laptopshop/img/${sanpham.id}.png" alt="${sanpham.tenSanPham}">
                            </a>
                            <div class="card-body">
                                <p class="card-title">${sanpham.tenSanPham}</p>
                                <h6 class="changeToVND text-muted" id="price-${sanpham.id}">${sanpham.donGia} VNĐ</h6>
                                <button onClick="addToCart(${sanpham.id})" class="btn btn-warning mt-2">
                                    <span class="bi bi-cart-plus"></span> Thêm vào giỏ hàng
                                </button>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
            <c:if test="${list.size() != 0}">
                <div class="pagination justify-content-center">
                    <!-- Nút Prev -->
                    <c:if test="${currentPage != 1}">
                        <a class="page-link"
                           href="/laptopshop/store?page=${currentPage-1}&range=${range}&brand=${brand}&manufactor=${manufactor}&os=${os}&ram=${ram}&pin=${pin}">
                            Prev
                        </a>
                    </c:if>

                    <!-- Trang đầu tiên -->
                    <c:if test="${currentPage == 1}">
                        <span class="page-link current">1</span>
                    </c:if>
                    <c:if test="${currentPage != 1}">
                        <a class="page-link"
                           href="/laptopshop/store?page=1&range=${range}&brand=${brand}&manufactor=${manufactor}&os=${os}&ram=${ram}&pin=${pin}">
                            1
                        </a>
                    </c:if>

                    <!-- Các trang giữa -->
                    <c:forEach var="pag" items="${pageList}" varStatus="loop">
                        <c:if test="${currentPage == pag}">
                            <span class="page-link current">${pag}</span>
                        </c:if>
                        <c:if test="${currentPage != pag}">
                            <a class="page-link"
                               href="/laptopshop/store?page=${pag}&range=${range}&brand=${brand}&manufactor=${manufactor}&os=${os}&ram=${ram}&pin=${pin}">
                                    ${pag}
                            </a>
                        </c:if>
                    </c:forEach>

                    <!-- Nút Next -->
                    <c:if test="${currentPage != totalPage}">
                        <a class="page-link"
                           href="/laptopshop/store?page=${currentPage+1}&range=${range}&brand=${brand}&manufactor=${manufactor}&os=${os}&ram=${ram}&pin=${pin}">
                            Next
                        </a>
                    </c:if>
                </div>
            </c:if>
            `
        </div>
        <div class="col-md-3">
            <h4>Lọc sản phẩm</h4>
            <br>
            <form>
                <input type="hidden" name="name" value="${name}">
                <div class="mb-3">
                    <label>Mức giá</label>
                    <select name="range" class="form-select">
                        <option value="">Tất cả giá</option>
                        <option value="duoi-2-trieu">Dưới 2 triệu</option>
                        <option value="2-trieu-den-4-trieu">2 triệu đến 4 triệu</option>
                        <option value="4-trieu-den-6-trieu">4 triệu - 6 triệu</option>
                        <option value="6-trieu-den-10-trieu">6 triệu - 10 triệu</option>
                        <option value="tren-10-trieu">Trên 10 triệu</option>
                    </select>
                </div>

                <%--				<c:if test="${brand == 'Laptop'}">--%>
                <div class="mb-3">
                    <label>Hệ điều hành</label>
                    <select name="os" class="form-select">
                        <option value="">Tất cả hệ điều hành</option>
                        <option value="ubuntu">Ubuntu</option>
                        <option value="dos">DOS</option>
                        <option value="mac">MAC</option>
                        <option value="linux">Linux</option>
                        <option value="windows 10">Window 10</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label>RAM</label>
                    <select name="ram" class="form-select">
                        <option value="">Tất cả ram</option>
                        <option value="2 GB">2 GB</option>
                        <option value="3 GB">3 GB</option>
                        <option value="4 GB">4 GB</option>
                        <option value="6 GB">6 GB</option>
                        <option value="8 GB">8 GB</option>
                        <option value="16 GB">16 GB</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label>Pin</label>
                    <select name="pin" class="form-select">
                        <option value="">Tất cả pin</option>
                        <c:forEach var="manufactor" items="${pinSet}" varStatus="loop">
                            <option value="${manufactor}">${manufactor}</option>
                        </c:forEach>
                    </select>
                </div>
                <%--				</c:if>--%>
                <input type="hidden" name="brand" value="${brand}">
                <button class="btn btn-primary" type="submit">Lọc sản phẩm</button>
            </form>
        </div>
    </div>
</div>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
<script>
    $(document).ready(function () {
        formatPrices();
    });

    function formatPrices() {
        $(".changeToVND").each(function () {
            let total = parseInt($(this).text());
            $(this).text(formatCurrency(total));
        });
    }

    function formatCurrency(amount) {
        return amount.toLocaleString("vi-VN") + " VNĐ";
    }

    function updateOrderTotal() {
        let total = 0;
        $(".changeToVND").each(function () {
            total += parseInt($(this).text().replace(/\D/g, ''));
        });
    }
</script>
</body>
</html>

