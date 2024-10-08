package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.CusPro;
import com.example.repository.ShopMyBatisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/cartAdd")
public class CartAddController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        int product_number = Integer.parseInt(req.getParameter("product_num"));

        CusPro dto = new CusPro();
        dto.setCustomer_id(customer_id);
        dto.setProduct_num(product_number);

        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        int cnt = dao.cartAdd(dto);
        PrintWriter out = resp.getWriter();
        out.println(cnt);
    }
}
