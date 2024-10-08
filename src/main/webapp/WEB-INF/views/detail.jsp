<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${product.name} 상세 보기</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container pt-5">
    <h2>${product.name}</h2>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <img src="" alt="상품 이미지" class="img-fluid">
                </div>
                <div class="col-md-6">
                    <h3>가격: ${product.price}원</h3>
                    <p>제조업체: ${product.manufaturer}</p>
                    <p>재고: ${product.stock}</p>
                    <p>${product.brand}</p>

                    <button onclick="goCart(${product.product_num})" class="btn btn-success">구매하기</button>

                </div>
            </div>
        </div>
        <div class="card-footer text-muted">
            <p>배송비: 2,500원 (5만원 이상 무료)</p>
        </div>
    </div>
    <a href="/shopping/list" class="btn btn-secondary mt-3">쇼핑 계속하기</a>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

<script>
    function cartAdd(cnt) {
        if(cnt>0){
            console.log(cnt);
            alert("Added to Cart");
        }else{
            console.log(cnt);
            alert("Failed to add to Cart");
        }
    }

    function goCart(product_number) {
        if(${empty customer_id}) {
            alert('로그인이 필요합니다.');
            return false;
        }
        //location.href = "/shopping/cartAdd";

        $.ajax({
            url: "/shopping/cartAdd",
            type: "post",
            data: {
                "customer_id": '${customer_id}',
                "product_num": product_number
            },
            success: cartAdd,
            error: function() {
                alert('장바구니 추가에 실패했습니다.');
            }
        })
    }
</script>
</html>

