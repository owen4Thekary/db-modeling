package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.BookDTO;
import com.example.repository.BookDAO;
import com.example.repository.BookMyBatisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/registerPost")   // <-- 클라이언트로부터 요청정보(title, price, name, page)가 넘어온다.
public class BookRegisterPostController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           // 1. 클라이언트로부터 넘어온 폼 파라메터정보를 얻어오는 작업 => 파라메터 수집(DTO)
         req.setCharacterEncoding("utf-8");
         // 파라메터 수집(DTO)
         String title=req.getParameter("title"); // 한글
         int price=Integer.parseInt(req.getParameter("price")); // "5000"
         String name=req.getParameter("name"); // 한글
         int page=Integer.parseInt(req.getParameter("page")); // 800
         // DTO묶어서-->DAO 전송
        BookDTO dto=new BookDTO();
        dto.setTitle(title);
        dto.setPrice(price);
        dto.setName(name);
        dto.setPage(page);

        //BookDAO dao=new BookDAO();
        BookMyBatisDAO dao=new BookMyBatisDAO();
        int cnt=dao.bookInsert(dto); // 저장 후에 -> 다시 리스트 보기로 이동 -> /list : redirect(리다이렉트)
        // redirect(리다이렉트)
        // forward(포워드) + 객체바인딩
        resp.sendRedirect("/shopping/list"); // http://localhost:8081/shopping/list
    }
}
