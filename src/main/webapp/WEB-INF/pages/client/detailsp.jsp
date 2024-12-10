<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="Frontend/css/detailsp.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- Font Awesome -->
    <script src="<c:url value='/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/js/accounting.min.js'/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // Giả sử sp.donGia có giá trị là một số
            var price = ${sp.donGia};  // Thay ${sp.donGia} bằng giá trị thực tế

            // Dùng accounting.js để định dạng số
            var priceConvert = accounting.formatMoney(price, {
                symbol: " VNĐ",    // Thêm đơn vị tiền tệ
                thousand: ".",     // Dấu phân cách nghìn là dấu chấm
                decimal: "",       // Không hiển thị dấu thập phân
                format: "%v%s"      // Định dạng số là "Giá trị VND"
            });

            document.getElementById("priceConvert").innerHTML = priceConvert;
        });

    </script>
</head>
<body>
<!-- Header -->
<jsp:include page="include/homeHeader.jsp"></jsp:include>

<!-- Product Details Section -->
<div id="notification"></div>
<div class="container">
    <div class="card" style="padding-bottom: 40px">
        <div class="container-fluid">
            <div class="wrapper row">
                <!-- Product Image Section -->
                <div class="preview col-md-6">
                    <div class="preview-pic tab-content mt-4">
                        <div class="tab-pane active" id="pic-1">
                            <img style="height: 450px" src="/laptopshop/img/${sp.getId()}.png"
                                 alt="${sp.getTenSanPham()}"/>
                        </div>
                    </div>
                </div>

                <!-- Product Details Section -->
                <div class="details col-md-6">
                    <p style="display:none" id="spid">${sp.getId()}</p>

                    <h4 class="price">Mô tả sản phẩm</h4>

                    <p class="product-description">Tên sản phẩm: ${sp.getTenSanPham()}</p>

                    <!-- CPU, RAM, etc. -->
                    <c:if test="${not empty sp.getCpu()}">
                        <p class="product-description">CPU: ${sp.getCpu()}</p>
                    </c:if>
                    <c:if test="${not empty sp.getRam()}">
                        <p class="product-description">RAM: ${sp.getRam()}</p>
                    </c:if>
                    <c:if test="${not empty sp.getThietKe()}">
                        <p class="product-description">Thiết kế: ${sp.getThietKe()}</p>
                    </c:if>
                    <c:if test="${not empty sp.getHeDieuHanh()}">
                        <p class="product-description">Hệ điều hành: ${sp.getHeDieuHanh()}</p>
                    </c:if>
                    <c:if test="${not empty sp.getManHinh()}">
                        <p class="product-description">Màn hình: ${sp.getManHinh()}</p>
                    </c:if>
                    <c:if test="${not empty sp.getDungLuongPin()}">
                        <p class="product-description">Dung lượng pin: ${sp.getDungLuongPin()}</p>
                    </c:if>
                    <p class="product-description">Hãng sản xuất: ${sp.hangSanXuat.tenHangSanXuat}</p>
                    <p class="product-description"><span
                            class="important">THÔNG TIN CHUNG:</span> ${sp.getThongTinChung()}</p>
                    <p class="product-description"><span class="important">BẢO HÀNH:</span> ${sp.getThongTinBaoHanh()}
                    </p>

                    <!-- Price Section -->
                    <h4 class="price " id="blabla">Giá bán: <span id="priceConvert"
                                                                  class="changeToVND">${sp.donGia} VNĐ</span></h4>


                    <div class="action text-center p pt-5">
                        <button class="add-to-cart btn btn-warning" type="button">
                            <i class="bi bi-cart-plus"></i> Thêm vào giỏ hàng
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Custom JS File -->
<script src="<c:url value='/js/client/detailspAjax.js'/>"></script>

<!-- Footer -->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
</body>
</html>
