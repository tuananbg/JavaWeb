<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <title>Quản lý giỏ hàng</title>
    <link rel="stylesheet" href="Frontend/css/cartTable.css">
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container my-5">
    <h2 class="text-center">Quản lý giỏ hàng</h2>
    <div class="table-responsive">
        <table class="table table-bordered table-hover table-cart-checkout">
            <thead class="table-primary text-center">
            <tr>
                <th>STT</th>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Đơn giá</th>
                <th>Số lượng</th>
                <th>Tổng</th>
                <th>Xóa</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sanpham" items="${cart}" varStatus="loop">
                <tr class="cart_line" id="item${sanpham.id}">
                    <td class="text-center">${loop.index + 1}</td>
                    <td class="text-center">
                        <img src="/laptopshop/img/${sanpham.id}.png" class="img-fluid cart-img" alt="Product Image">
                    </td>
                    <td>
                        <p class="cart_ten"><a href="<c:url value='/sp?id=${sanpham.id}' />">${sanpham.tenSanPham}</a>
                        </p>
                        <p>Bảo hành: ${sanpham.thongTinBaoHanh}</p>
                    </td>
                    <td class="text-center covertPriceProduct text-muted">${sanpham.donGia}</td>
                    <td class="text-center">
                        <input class="form-control input_num_cart" type="number" value="${quanity[sanpham.id]}" min="1"
                               onChange="changeQuanity(${sanpham.id}, this.value, ${sanpham.donGia})">
                    </td>
                    <td class="text-center"><span class="total"
                                                  id="item${sanpham.id}_total">${sanpham.donGia * quanity[sanpham.id]}</span>
                    </td>
                    <td class="text-center">
                        <button class="btn btn-danger btn-sm" onClick="deleteFromCart(${sanpham.id})">
                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="d-flex justify-content-between align-items-center mt-3">
        <c:choose>
            <c:when test="${checkEmpty != 0}">
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/">
                    <i class="bi bi-cart-plus"></i> Mua thêm sản phẩm khác
                </a>
                <a class="btn btn-warning" href="<%=request.getContextPath()%>/checkout">
                    <i class="bi bi-check-circle"></i> Thanh toán
                </a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/">Mua thêm sản phẩm khác</a>
            </c:otherwise>
        </c:choose>
        <p class="cart_tongdonhang">Tổng giá trị đơn hàng: <span id="ordertotal">0 VNĐ</span></p>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/accounting.js/0.4.1/accounting.min.js"></script>
<script src="<c:url value="/js/client/cartAjax.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        formatPrices();
        updateOrderTotal();
    });

    function formatPrices() {
        $(".covertPriceProduct").each(function () {
            let price = parseInt($(this).text());
            $(this).text(formatCurrency(price));
        });
        $(".total").each(function () {
            let total = parseInt($(this).text());
            $(this).text(formatCurrency(total));
        });
    }

    function formatCurrency(amount) {
        return amount.toLocaleString("vi-VN") + " VNĐ";
    }

    function updateOrderTotal() {
        let total = 0;
        $(".total").each(function () {
            total += parseInt($(this).text().replace(/\D/g, ''));
        });
        $("#ordertotal").text(formatCurrency(total));
    }
</script>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
</body>
</html>

