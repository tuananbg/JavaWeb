<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>Liên hệ</title>
</head>
<body>
<!----start-Header---->
<jsp:include page="include/homeHeader.jsp"></jsp:include>
<!----End-Header---->
<div class="container my-4">
    <br>
    <h1 class="display-5">Liên hệ với chúng tôi</h1>
    <hr>

    <form id="contactForm">
        <div class="mb-3">
            <label for="email" class="form-label">Địa chỉ email của bạn:</label>
            <p id="emailWarning" class="text-danger"></p>
            <input type="email" class="form-control" id="email" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Thông tin liên hệ:</label>
            <p id="contentWarning" class="text-danger"></p>
            <textarea class="form-control" id="content" rows="4" required></textarea>
        </div>
        <button type="button" onClick="sendContact()" class="btn btn-primary">Gửi liên hệ</button>
    </form>
</div>
<!----start-Footder---->
<jsp:include page="include/homeFooter.jsp"></jsp:include>
<!----End-Footder---->
<script src="<c:url value='/js/client/contactAjax.js'/>"></script>
</body>
</html>