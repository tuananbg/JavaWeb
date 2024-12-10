<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Đổi thông tin tài khoản</title>
    <link rel="stylesheet" href="Frontend/css/bootstrap.min.css"> <!-- Add Bootstrap CSS -->
    <script src="<c:url value='/js/client/information.js'/>"></script> <!-- Script URL -->
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container">
    <h2 class="text-center">Thay đổi thông tin tài khoản</h2>
    <form id="changeInfoForm">
        <div class="col-md-6 offset-md-3">
            <br>
            <p class="border-p" style="line-height:1.5;"><b>Thông tin khách hàng</b></p>

            <div class="form-group">
                <label for="name">Họ tên Quý khách *</label>
                <input type="text" class="form-control" id="name" value="${user.hoTen}" aria-label="Họ tên">
                <p class="text-danger" id="nameWarning"></p>
            </div>

            <div class="form-group">
                <label for="phone">Số điện thoại *</label>
                <input type="tel" class="form-control" id="phone" value="${user.soDienThoai}"
                       aria-label="Số điện thoại">
                <p class="text-danger" id="phoneWarning"></p>
            </div>

            <div class="form-group">
                <label for="address">Địa chỉ (số nhà, đường, tỉnh thành) *</label>
                <textarea class="form-control" id="address" rows="5" aria-label="Địa chỉ">${user.diaChi}</textarea>
                <p class="text-danger" id="addressWarning"></p>
            </div>

            <button type="button" onClick="changeInformation()" class="btn btn-primary">Đổi thông tin</button>
        </div>
    </form>
</div>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
</body>
</html>