<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả tìm kiếm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="Frontend/css/searchResult.css">
    <script src="Frontend/js/jquery.min.js"></script>
    <script src="Frontend/js/responsiveslides.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/accounting@0.4.2/accounting.min.js"></script>
    <script src="<c:url value='/js/client/search.js'/>"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            // Format each product price on page load
            $(".changeToVND").each(function () {
                var rawPrice = $(this).data("price");  // Get raw price from data-price attribute
                var formattedPrice = accounting.formatMoney(rawPrice, {
                    symbol: " VNĐ",   // Currency symbol
                    thousand: ".",    // Thousand separator
                    decimal: "",      // No decimal places
                    format: "%v%s"     // Format: value first, then symbol
                });
                $(this).text(formattedPrice);  // Update the displayed price
            });
        });
    </script>
</head>
<body style="background-color: #e9f1f9">
<div class="container my-4">
    <div class="row">
        <div class="col-12">
            <h4>Kết quả tìm kiếm</h4>
        </div>
    </div>

    <!-- Filter Section -->
    <div class="row mb-3">
        <div class="col-12">
            <div class="d-flex justify-content-between">
                <form class="d-flex align-items-center" action="">
                    <input type="hidden" name="name" value="${name}">
                    <input type="hidden" name="range" value="${range}">
                    <input type="hidden" name="brand" value="${brand}">
                    <input type="hidden" name="manufactor" value="${manufactor}">

                    <select name="sort" class="form-select me-2" style="max-width: 200px;">
                        <option value="">Sắp xếp theo</option>
                        <option value="newest">Mới nhất</option>
                        <option value="priceAsc">Giá tăng dần</option>
                        <option value="priceDes">Giá giảm dần</option>
                    </select>

                    <button style="width: 200px;" type="submit" class="btn btn-primary ms-2">Sắp xếp</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Products Display -->
    <div class="row">
        <c:if test="${list.size() == 0}">
            <div class="alert alert-info">
                Không tìm thấy kết quả nào.
            </div>
        </c:if>

        <!-- Products List (9 columns) -->
        <div class="col-md-9">
            <div class="row">
                <c:forEach var="sanpham" items="${list}" varStatus="loop">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <a href="sp?id=${sanpham.id}">
                                <img class="card-img-top" src="/laptopshop/img/${sanpham.id}.png"
                                     alt="${sanpham.tenSanPham}" style="height: 238px; object-fit: cover;">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${sanpham.tenSanPham}</h5>
                                <!-- Using data-price to store raw price -->
                                <h6 class="changeToVND text-muted" data-price="${sanpham.donGia}">${sanpham.donGia}</h6>
                                <button class="add-to-cart btn btn-warning" type="button">
                                    <i class="bi bi-cart-plus"></i> Thêm vào giỏ hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination -->
            <c:if test="${list.size() != 0}">
                <div class="d-flex justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <a href="/laptopshop/search?name=${name}&page=${currentPage-1}&sort=${sort}&range=${range}&brand=${brand}&manufactor=${manufactor}"
                           class="btn btn-secondary me-2">Back</a>
                    </c:if>
                    <c:forEach var="pag" items="${pageList}">
                        <c:if test="${currentPage == pag}">
                            <span class="btn btn-primary me-2">${pag}</span>
                        </c:if>
                        <c:if test="${currentPage != pag}">
                            <a href="/laptopshop/search?name=${name}&page=${pag}&sort=${sort}&range=${range}&brand=${brand}&manufactor=${manufactor}"
                               class="btn btn-outline-primary me-2">${pag}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${currentPage != totalPage}">
                        <a href="/laptopshop/search?name=${name}&page=${currentPage+1}&sort=${sort}&range=${range}&brand=${brand}&manufactor=${manufactor}"
                           class="btn btn-secondary">Next</a>
                    </c:if>
                </div>
            </c:if>
        </div>

        <!-- Sidebar for Filtering (3 columns) -->
        <div class="col-md-3">
            <h4>Lọc sản phẩm</h4>
            <form>
                <input type="hidden" name="name" value="${name}">
                <div class="mb-3">
                    <label for="range" class="form-label">Mức giá</label>
                    <select name="range" class="form-select">
                        <option value="">Tất cả giá</option>
                        <option value="duoi-2-trieu">Dưới 2 triệu</option>
                        <option value="2-trieu-den-4-trieu">2 triệu đến 4 triệu</option>
                        <option value="4-trieu-den-6-trieu">4 triệu - 6 triệu</option>
                        <option value="6-trieu-den-10-trieu">6 triệu - 10 triệu</option>
                        <option value="tren-10-trieu">Trên 10 triệu</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="brand" class="form-label">Danh mục</label>
                    <select name="brand" class="form-select">
                        <option value="">Tất cả danh mục</option>
                        <c:forEach var="brand" items="${danhmuc}">
                            <option value="${brand}">${brand}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="manufactor" class="form-label">Hãng sản xuất</label>
                    <select name="manufactor" class="form-select">
                        <option value="">Tất cả nhà sản xuất</option>
                        <c:forEach var="manufactor" items="${hangsx}">
                            <option value="${manufactor}">${manufactor}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary w-100">Lọc sản phẩm</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
