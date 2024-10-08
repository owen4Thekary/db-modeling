package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.repository.ShopMyBatisDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/list/*")
public class ProductListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // URL 경로 정보
        String customer_id = null;
        // 경로에서 customer_id를 추출
        if (pathInfo != null && pathInfo.length() > 1) {
            customer_id = pathInfo.substring(1); // "/customer_id" 형식일 경우
        } else {
            customer_id = req.getParameter("customer_id"); // 쿼리 파라미터로 가져오기
        }

        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        if(customer_id != null) {
            Customer cusDto = dao.customer(customer_id);
            req.setAttribute("cusDto", cusDto);
        }

        List<Product> list = dao.productList();
        req.setAttribute("list", list);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        rd.forward(req, resp);
    }
}
