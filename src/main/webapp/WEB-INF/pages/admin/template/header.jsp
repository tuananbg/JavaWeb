<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
    <link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.3/animate.min.css'>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="<c:url value='/css/admin/admin.css' />"/>
    <link rel="stylesheet" href="<c:url value='/css/admin/sidebar.css' />"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>

<body>

<nav class="navbar navbar-fixed-top">
    <div class="">
      <div class="navbar-header">
        <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value='/admin'/>">Laptop Shop - Trang Quản Trị</a>
      </div>
        <div class=" navbar-collapse navbar-right navbar-nav"
             style="margin-right: 20px; font-size: 18px; margin-top: 10px; ">
            <a href="<c:url value='/admin/profile' />">
              <span class="glyphicon glyphicon-user">&nbsp;</span>Xin chào: ${loggedInUser.hoTen }</a>
      </div>
    </div>
</nav>