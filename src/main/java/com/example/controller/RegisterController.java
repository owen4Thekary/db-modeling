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

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customer_id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String regionCode = req.getParameter("region_code");
        String email = req.getParameter("email");

        // 비밀번호 해시 처리 (보안 고려)
//        String hashedPassword = hashPassword(password);
        String grade = "normal";
        int reserves = 0;

        Customer customer = new Customer(customerId, password, name, email, grade, reserves, regionCode);
        ShopMyBatisDAO customerDAO = new ShopMyBatisDAO();
        int success = customerDAO.registerCustomer(customer);

        if (success == 1) {
            resp.sendRedirect("/shopping/list");
        } else {
            req.setAttribute("errorMessage", "회원가입 실패");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/signup.jsp");;
            rd.forward(req, resp);
        }
    }
}
