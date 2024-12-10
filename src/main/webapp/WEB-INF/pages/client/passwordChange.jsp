<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="Frontend/css/bootstrap.min.css"> <!-- Include Bootstrap CSS -->
    <script src="<c:url value='/js/client/password.js'/>"></script> <!-- Script URL -->
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container">
    <h2 class="text-center">Đổi mật khẩu</h2>
    <form id="changePasswordForm">
        <div class="col-md-6 offset-md-3">
            <br>
            <div class="form-group">
                <label for="old">Mật khẩu cũ *</label>
                <input type="password" class="form-control" id="old" aria-label="Mật khẩu cũ">
                <p class="text-danger" id="oldWarning"></p>
            </div>

            <div class="form-group">
                <label for="new1">Mật khẩu mới *</label>
                <input type="password" class="form-control" id="new1" aria-label="Mật khẩu mới">
                <p class="text-danger" id="new1Warning"></p>
            </div>

            <div class="form-group">
                <label for="new2">Xác nhận lại mật khẩu mới *</label>
                <input type="password" class="form-control" id="new2" aria-label="Xác nhận mật khẩu mới">
                <p class="text-danger" id="new2Warning"></p>
            </div>

            <br>
            <button type="button" onClick="changePass()" class="btn btn-primary">Đổi mật khẩu</button>
        </div>
    </form>
</div>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
</body>
</html>