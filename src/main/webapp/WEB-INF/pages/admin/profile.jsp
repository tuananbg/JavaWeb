<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style>
        .form-profile input {
            border: 0;
        }

        /* Phong cách chính cho trang profile */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h3.page-header {
            color: #007bff;
            margin-bottom: 20px;
        }

        .col-md-9 {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
        }

        /* Nút hành động */
        button {
            font-size: 16px;
            padding: 10px 20px;
            margin-bottom: 15px;
            border-radius: 5px;
            font-weight: bold;
        }

        .btnCapNhatThongTin {
            background-color: #28a745;
            border: none;
            color: white;
        }

        .btnCapNhatThongTin:hover {
            background-color: #218838;
        }

        .btnDoiMatKhau {
            background-color: #007bff;
            border: none;
            color: white;
        }

        .btnDoiMatKhau:hover {
            background-color: #0056b3;
        }

        /* Bố cục form chính */
        .form-profile {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .form-group.row {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .form-group.row label {
            flex: 1;
            font-weight: bold;
            margin: 0;
        }

        .form-group.row .col-sm-10 {
            flex: 2;
        }

        /* Input chỉnh sửa */
        .form-control-plaintext {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 8px 10px;
            border-radius: 5px;
            width: 100%;
        }

        /* Modal */
        .modal-content {
            border-radius: 8px;
        }

        .modal-header {
            background-color: #007bff;
            color: white;
            border-radius: 8px 8px 0 0;
            padding: 15px;
        }

        .modal-footer {
            padding: 15px;
            border-top: none;
        }

        /* Form trong modal */
        .modal-body .form-group label {
            font-weight: bold;
        }

        .modal-body .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .modal-body .form-group input:focus {
            border-color: #007bff;
            box-shadow: 0px 0px 5px rgba(0, 123, 255, 0.5);
        }

        /* Responsive */
        @media (max-width: 768px) {
            .form-group.row {
                flex-direction: column;
                align-items: stretch;
            }

            .form-group.row label {
                margin-bottom: 5px;
            }

            button {
                width: 100%;
            }
        }

    </style>

</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-10 animated bounce">
    <h3 class="page-header">Thông tin cá nhân</h3>

    <button class="btn btn-success btnCapNhatThongTin">Cập nhật
        thông tin
    </button>
    &nbsp;&nbsp;&nbsp;
    <button class="btn btn-primary btnDoiMatKhau">Đổi mật khẩu</button>
    <hr/>

    <form class="form-profile">

        <div class="form-group row">
            <div class="col-sm-10">
                <input type="hidden" class="form-control-plaintext nguoiDungId"
                       value="${user.id }">
            </div>
        </div>
        <div class="form-group row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Họ
                Tên</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${user.hoTen }">
            </div>
        </div>

        <div class="form-group row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Email
                đăng ký</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${user.email }">
            </div>
        </div>

        <div class="form-group row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Số
                điện thoại</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${user.soDienThoai }">
            </div>
        </div>

        <div class="form-group row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Địa
                chỉ</label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       value="${user.diaChi }">
            </div>
        </div>
    </form>
</div>

<div class="row col-md-6">
    <form class="formCapNhat"
          action="${contextPath }/admin/profile/update" method="post">
        <div>
            <div class="modal fade" id="capNhatModal" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Cập nhật
                                thông tin cá nhân</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="id"
                                       value="${user.id }"/>
                            </div>
                            <div class="form-group">
                                <label for="name">Họ tên</label> <input type="text"
                                                                        class="form-control" name="hoTen"
                                                                        required="required"/>
                            </div>
                            <div class="form-group">
                                <label for="name">Số điện thoại</label> <input type="text"
                                                                               class="form-control" name="soDienThoai"
                                                                               required="required"/>
                            </div>
                            <div class="form-group">
                                <label for="name">Địa chỉ</label> <input type="text"
                                                                         class="form-control" name="diaChi"
                                                                         required="required"/>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy
                                </button>
                                <input class="btn btn-primary" id="btnSubmit" type="submit"
                                       value="Xác nhận"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <form class="formDoiMatKhau">
        <div>
            <div class="modal fade" id="doiMKModal" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true"
                 data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Đổi mật khẩu</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </div>

                            <div class="form-group">
                                <label for="name">Mật khẩu cũ</label> <input type="password"
                                                                             class="form-control" name="oldPassword"
                                                                             required="required"/>
                            </div>
                            <div class="form-group">
                                <label for="name">Mật khẩu mới</label> <input type="password"
                                                                              class="form-control" name="newPassword"
                                                                              required="required"/>
                            </div>
                            <div class="form-group">
                                <label for="name">Nhắc lại mật khẩu mới</label> <input
                                    type="password" class="form-control" name="confirmNewPassword"
                                    required="required"/>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Hủy
                                </button>
                                <input class="btn btn-primary" type="button"
                                       id="btnXacNhanDoiMK" value="Xác nhận"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</div>
<jsp:include page="template/footer.jsp"></jsp:include>
<script src="<c:url value='/js/profileAdminAjax.js'/>"></script>
</body>
</html>