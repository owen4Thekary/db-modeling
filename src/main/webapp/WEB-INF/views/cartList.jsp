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
<script>
    function goCancel(order_number, customer_id) {

        $.ajax({
            url: "/shopping/cancel",
            type: "post",
            data: {
                "customer_id": customer_id,
                "order_num": order_number
            },
            success: function (data) {
                alert('삭제되었습니다.');
                window.location.href = "/shopping/cartList?customer_id=" + customer_id;
            },
            error: function() {
                alert('삭제에 실패했습니다.');
            }
        })
    }

    function goQuantity(order_number, product_number) {
        var quantity = $("#quantity" + order_number).val();
        $.ajax({
            url: "/shopping/quantity",
            type: "post",
            data: {
                "order_num": order_number,
                "quantity": quantity,
                "product_num": product_number
            },
            success: function (cnt) {
                alert('수량이 변경되었습니다.');
                window.location.href = "/shopping/cartList?customer_id=${cusDto.customer_id}";
            },
            error: function() {
                alert('수량 변경에 실패했습니다.');
            }
        })
    }

</script>
</head>
<body>

    <div class="container pt-5">
        <h2>MVC 기반 온라인 쇼핑 카트 구현하기</h2>
        <div class="card">
            <div class="card-header">
                <div class="row">
                    <div class="col-4">
                        <c:if test="${!empty cusDto}">
                            <h5 class="text-right">Welcome, ${cusDto.name} Rewards:
                                <span class="badge badge-danger">${cusDto.reserves}</span>
                            </h5>
                        </c:if>
                        <c:if test="${empty cusDto}">
                            <h5 class="text-right">Welcome, Guest Rewards:
                                <span class="badge badge-danger">0</span>
                            </h5>
                        </c:if>
                    </div>
                    <div class="col-8">
                        <c:if test="${!empty cusDto}">
                            <form class="form-inline" action="/shopping/logout" method="post">
                                &nbsp;<button type="submit" class="btn btn-sm btn-warning">로그아웃</button>
                            </form>
                        </c:if>
                        <c:if test="${empty cusDto}">
                            <form class="form-inline" action="/shopping/login" method="post">
                                <label for="customer_id">아이디:</label>
                                <input type="text" class="form-control" placeholder="Enter customer_id" id="customer_id" name="customer_id">
                                &nbsp;<label for="password">패스워드:</label>
                                <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
                                &nbsp;<button type="submit" class="btn btn-sm btn-primary ml-2">로그인</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col text-right"><button type="button" onclick="goOrder('${cusDto.customer_id}')" class="btn btn-sm btn-danger">Order</button></div>
                </div>
                <h3>Cart List</h3>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>제품번호</th>
                            <th>제품명</th>
                            <th>수량</th>
                            <th>가격</th>
                            <th>금액</th>
                            <th class="text-center">취소</th>
                        </tr>
                    </thead>
                    <c:forEach var="cart" items="${list}">
                        <tr>
                            <td>${cart.product_num}</td>
                            <td>${cart.name}</td>
                            <td><input type="number" onchange="goQuantity(${cart.order_num}, ${cart.product_num})" name="quantity" id="quantity${cart.order_num}" min="1" max="5" class="form-control" value="${cart.quantity}"/></td>
                            <td>${cart.price}</td>
                            <td>${cart.amount}</td>
                            <td class="text-center"><button onclick="goCancel(${cart.order_num}, '${cusDto.customer_id}')" class="btn btn-sm btn-secondary">Cancel</button></td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="4" class="text-right">
                                Total Amount
                            </td>
                            <td colspan="1" class="text-center">
                                <span class="badge badge-danger">${totalAmount}</span>
                            </td>
                            <td colspan="1" class="text-center">
                                <span class="badge badge-primary">${totalPoint}</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col text-right">
                        <button type="button"  onclick="location.href='/shopping/list?customer_id=' + '${cusDto.customer_id}'" class="btn btn-sm btn-primary">Continue Shopping</button>
                    </div>
                </div>
            </div>
            <div class="card-footer text-center">[7일 완성]생각하는 데이터베이스 모델링_박매일</div>
        </div>
        <div class="row">
            <div class="col">
                <label>
                    <input type="checkbox" id="useRewards" value="true"> 적립금 사용
                </label>
            </div>
        </div>
    </div>

    <script>
        function goOrder(customer_id) {
            if(${!empty totalAmount}) {
                const useRewards = $("#useRewards").is(":checked");
                $.ajax({
                    url: "/shopping/order",
                    type: "get",
                    data: {
                        "customer_id": customer_id,
                        "totalAmount": ${totalAmount},
                        "totalPoint": ${totalPoint},
                        "useRewards": useRewards
                    },
                    success: function (data) {
                        alert('주문이 완료되었습니다.');
                        window.location.href = "/shopping/cartList?customer_id=" + customer_id;
                    },
                    error: function() {
                        alert('주문에 실패했습니다.');
                    }
                })
            }else{
                alert('주문할 제품이 없습니다.');
                return false;
            }
        }
    </script>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

