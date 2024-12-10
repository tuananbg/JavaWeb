<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Thanh toán</title>

    <link rel="stylesheet" href="Frontend/css/checkout.css">
    <script src="js/client/accounting.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".mytable .donGia .check").each(function () {
                var value = accounting.formatMoney($(this).text()) + ' VND';
                $(this).html(value);
            });

            $(".mytable .total").each(function () {
                var value = accounting.formatMoney($(this).text()) + ' VND';
                $(this).html(value);
            });
        });
    </script>
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container my-5">
    <h2 class="text-center mb-4">Thanh toán</h2>
    <form method="POST" action="<%=request.getContextPath()%>/thankyou">
        <div>
            <h5 class="border-bottom pb-2"><b>Thông tin nhận hàng</b></h5>
            <div class="mb-3">
                <label>Họ tên người nhận hàng *</label>
                <input class="form-control" name="hoTenNguoiNhan" required>

                <label class="mt-3">Số điện thoại *</label>
                <input class="form-control" name="sdtNhanHang" required>

                <label class="mt-3">Địa chỉ (số nhà, đường, tỉnh thành) *</label>
                <textarea class="form-control" rows="5" name="diaChiNhan" required></textarea>

                <input type="hidden" id="tongGiaTri" name="tongGiaTri">
            </div>
        </div>

        <div>
            <h5 class="border-bottom pb-2"><b>Giỏ hàng</b></h5>
            <table class="table table-striped mytable">
                <thead>
                <tr>
                    <th>Ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Đơn giá</th>
                    <th>Tổng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart}" var="sanpham">
                    <tr>
                        <td>
                            <img src="/laptopshop/img/${sanpham.id}.png" alt="not found img" class="img-fluid">
                        </td>
                        <td style="color:green">${sanpham.tenSanPham}</td>
                        <td class="donGia">
                            <div class="check">${sanpham.donGia}</div>
                            <div> x ${quanity[sanpham.id]}</div>
                        </td>
                        <td>
                            <div class="total">${sanpham.donGia * quanity[sanpham.id]}</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <h6>Tổng giá trị đơn hàng: <b id="ordertotal"></b></h6>
            <div class="d-flex justify-content-between mt-3">
                <a href="<%=request.getContextPath()%>/cart" class="btn btn-outline-primary">Quay lại giỏ hàng</a>
                <button class="btn btn-danger" type="submit" id="submit">Gửi đơn hàng</button>
            </div>
        </div>
    </form>
</div>

<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->

<script src="<c:url value='/js/client/checkoutAjax.js'/>"></script>
</body>
</html>

