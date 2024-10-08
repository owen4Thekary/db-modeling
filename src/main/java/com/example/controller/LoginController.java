package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.Customer;
import com.example.repository.ShopMyBatisDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        String password = req.getParameter("password");

        Customer customer = new Customer();
        customer.setCustomer_id(customer_id);
        customer.setPassword(password);

        ShopMyBatisDAO dao = new ShopMyBatisDAO();
        Customer cusDto = dao.customer_login(customer);
        if(cusDto != null){
            HttpSession session = req.getSession();
            session.setAttribute("cusDto", cusDto);
        }

        resp.sendRedirect("/shopping/list");
    }
}
