<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Thông tin tài khoản</title>
    <link rel="stylesheet" href="Frontend/css/account.css">
    <script src="<c:url value='/js/client/information.js'/>"></script>
    <script src="<c:url value='/js/client/password.js'/>"></script>
    <style>
        /* Đặt chung cho trang */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc; /* Màu nền nhẹ nhàng */
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Container chính */
        .container {
            margin-top: 30px;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.1);
        }

        /* Tiêu đề thông tin tài khoản */
        h3.fw-bold {
            color: #007bff;
            font-size: 1.8em;
            margin-bottom: 15px;
            font-weight: 700;
        }

        /* Các phần thông tin tài khoản */
        p {
            font-size: 1.1em;
            margin-bottom: 10px;
        }

        /* Tạo điểm nhấn cho nhãn thông tin */
        .fw-bold {
            font-weight: 600;
            color: #007bff;
        }

        /* Các nút hành động */
        .btn {
            font-size: 16px;
            padding: 12px 25px;
            margin-bottom: 15px;
            border-radius: 6px;
            font-weight: 600;
            display: inline-block;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
            border: none;
        }

        .btn-danger:hover {
            background-color: #b02a37;
        }

        /* Bảng lịch sử mua hàng */
        .mytable {
            width: 100%;
            margin-top: 30px;
            border-collapse: collapse;
            font-size: 1rem;
        }

        .mytable th {
            background-color: #007bff;
            color: white;
            padding: 12px;
            text-align: center;
            border: 1px solid #dee2e6;
        }

        .mytable td {
            padding: 12px;
            text-align: center;
            border: 1px solid #dee2e6;
        }

        .mytable tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .tongGiaTri {
            font-weight: bold;
            color: #28a745;
        }

        /* Modal */
        .modal-content {
            border-radius: 10px;
            box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.15);
        }

        .modal-header {
            background-color: #007bff;
            color: white;
            border-radius: 8px 8px 0 0;
            padding: 20px;
        }

        .modal-title {
            font-weight: 700;
        }

        .modal-footer {
            padding: 20px;
            border-top: none;
        }

        .modal-body .form-group label {
            font-weight: 600;
        }

        .modal-body .form-group input,
        .modal-body .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #dee2e6;
            border-radius: 6px;
            margin-top: 10px;
        }

        .modal-body .form-group input:focus,
        .modal-body .form-group textarea:focus {
            border-color: #007bff;
            box-shadow: 0px 0px 8px rgba(0, 123, 255, 0.3);
        }

        /* Responsive */
        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            .btn {
                width: 100%;
                margin-bottom: 15px;
            }

            .mytable {
                font-size: 0.9rem;
            }
        }

    </style>
</head>
<body>

<jsp:include page="include/homeHeader.jsp"></jsp:include>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <br>
            <h3 class="fw-bold">Thông tin tài khoản:</h3>
            <br>
            <p><span class="fw-bold">Họ tên: </span>${user.getHoTen()}</p>
            <p><span class="fw-bold">Số điện thoại: </span>${user.getSoDienThoai()}</p>
            <p><span class="fw-bold">Email: </span>${user.getEmail()}</p>
            <p><span class="fw-bold">Địa chỉ: </span>${user.getDiaChi()}</p>
            <br>
            <a class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalInformation">Cập nhật thông tin cá
                nhân</a>
            <a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modalChangePassword">Đổi mật khẩu</a>
            <br><br>

            <h3 class="fw-bold">Lịch sử mua hàng:</h3>
            <br>
            <table class="table table-hover mytable">
                <thead>
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Ngày mua</th>
                    <%--                    <th>Ngày giao hàng</th>--%>
                    <th>Ngày nhận hàng</th>
                    <th>Sản phẩm</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái đơn hàng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="donHang" items="${list}" varStatus="loop">
                <tr style="text-align: center;">
                    <td>${donHang.id}</td>

                    <td>${donHang.ngayDatHang}</td>

                        <%--                    <td>${donHang.ngayGiaoHang}</td>--%>

                    <td>${donHang.ngayNhanHang}</td>

                    <td>
                        <c:forEach var="chiTiet" items="${donHang.danhSachChiTiet}">
                            <p>
                                <a href='<c:url value="/sp?id=${chiTiet.sanPham.id}" />'>${chiTiet.sanPham.tenSanPham}</a><br>
                            </p>

                            <c:choose>
                                <c:when
                                        test='${(donHang.trangThaiDonHang == "Đang chờ giao") || (donHang.trangThaiDonHang =="Đang giao")  }'>

                                    <p>Số lượng: ${chiTiet.soLuongDat }</p>

                                    <c:set var="tongGiaTri"
                                           value="${tongGiaTri + chiTiet.soLuongDat*chiTiet.donGia}"/>
                                </c:when>
                                <c:otherwise>
                                    <p>Số lượng: ${chiTiet.soLuongNhanHang }</p>

                                    <c:set var="tongGiaTri"
                                           value="${tongGiaTri + chiTiet.soLuongNhanHang*chiTiet.donGia}"/>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach></td>

                    <td class="tongGiaTri">${tongGiaTri}</td>
                    <td>${donHang.trangThaiDonHang}</td>
                </tr>
                </c:forEach>
            </table>

        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- Modal cập nhật thông tin -->
<div class="modal fade" id="modalInformation" tabindex="-1" aria-labelledby="modalInformationLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title fw-bold">Cập nhật thông tin tài khoản</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group mb-3">
                    <label for="name">Họ tên khách hàng*:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${user.getHoTen()}">
                </div>
                <div class="form-group mb-3">
                    <label for="phone">Số điện thoại*:</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${user.getSoDienThoai()}">
                </div>
                <div class="form-group">
                    <label for="address">Địa chỉ*:</label>
                    <textarea rows="3" class="form-control" id="address">${user.getDiaChi()}</textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary" onclick="changeInformation()">Cập nhật</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal đổi mật khẩu -->
<div class="modal fade" id="modalChangePassword" tabindex="-1" aria-labelledby="modalChangePasswordLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title fw-bold">Thay đổi mật khẩu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group mb-3">
                    <label for="old">Mật khẩu cũ*:</label>
                    <input type="password" class="form-control" id="old" name="old">
                </div>
                <div class="form-group mb-3">
                    <label for="new1">Mật khẩu mới*:</label>
                    <input type="password" class="form-control" id="new1" name="new1">
                </div>
                <div class="form-group">
                    <label for="new2">Xác nhận lại mật khẩu mới*:</label>
                    <input type="password" class="form-control" id="new2" name="new2">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary" onclick="changePass()">Xác nhận</button>
            </div>
        </div>
    </div>
</div>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
<script>
    $(document).ready(function () {
        $(".mytable .tongGiaTri").each(function () {
            const value = accounting.formatMoney($(this).text()) + ' VND';
            $(this).html(value);
        });
    });
</script>

</body>
</html>
