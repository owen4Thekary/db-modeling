<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container pt-5">
    <h2>회원가입</h2>
    <form action="/shopping/register" method="post">
        <div class="form-group">
            <label for="customer_id">아이디:</label>
            <input type="text" class="form-control" id="customer_id" name="customer_id" required>
        </div>
        <div class="form-group">
            <label for="name">이름:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="password">패스워드:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="region_code">지역 코드:</label>
            <select class="form-control" id="region_code" name="region_code" required>
                <c:forEach var="region" items="${regionList}">
                    <option value="${region.region_code}">${region.region}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <button type="submit" class="btn btn-primary">회원가입</button>
        <a href="/shopping/login" class="btn btn-secondary ml-2">로그인 페이지로 이동</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

