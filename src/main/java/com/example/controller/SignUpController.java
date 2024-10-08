package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.Product;
import com.example.entity.Region;
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
@WebServlet("/signup")
public class SignUpController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        List<Region> list = dao.regionList();
        req.setAttribute("regionList", list);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/signup.jsp");
        rd.forward(req, resp);
    }
}
