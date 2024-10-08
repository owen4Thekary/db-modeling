package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ShopMyBatisDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/*")
public class ProductDetailController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // URL 경로 정보
        int productNum = Integer.parseInt(pathInfo.substring(1)); // 상품 번호 추출

        // 상품 정보 조회
        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        Product product = dao.productDetail(productNum);

        // customer_id를 요청 파라미터에서 가져오기
        String customerId = req.getParameter("customer_id");

        if (product != null) {
            req.setAttribute("product", product); // 상품 정보 설정
            req.setAttribute("customer_id", customerId); // customer_id 설정

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
            rd.forward(req, resp); // JSP로 포워드
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 상품이 없을 경우 404 오류
        }
    }
}
