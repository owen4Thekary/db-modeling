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
@WebServlet("/quantity")
public class CartQuantityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int order_number = Integer.parseInt(req.getParameter("order_num"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int product_num = Integer.parseInt(req.getParameter("product_num"));

        CusPro dto = new CusPro();
        dto.setOrder_num(order_number);
        dto.setProduct_num(product_num);
        dto.setQuantity(quantity);

        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        int cnt = dao.updateQuantity(dto);

        PrintWriter out = resp.getWriter();
        out.println(cnt);
    }
}
