package com.example.controller;
// Servlet의 기본 골격

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
 @WebServlet("/register")
public class BookRegisterController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              // 책 등록 JSP로 forward
             // 객체바인딩은 필요없다.
            RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/views/register.jsp");
            rd.forward(req, resp);
    }
}
