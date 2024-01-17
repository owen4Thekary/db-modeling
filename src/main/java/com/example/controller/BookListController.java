package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.BoardDTO;
import com.example.entity.BookDTO;
import com.example.repository.BookDAO;
import com.example.repository.BookMyBatisDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// http://localhost:8081/shopping/list -----> /WEB-INF/views/list.jsp
@WebServlet("/list")
public class BookListController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // 요청을 받은 컨트롤러가 어떻게 view로 이동을 시켜줄까?
           // View와 연동하기  -> 포워딩(forward), 요청의뢰(RequestDispatcher)
           //  /WEB-INF/views/template.jsp
        // View를 선택하는 부분                                     /WEB-INF/views/list.jsp                          ViewResolver API
        //게시판 리스트 데이터 (BoardDTO) -> List<BoardDTO>
       // BookDAO dao=new BookDAO();
        BookMyBatisDAO dao=new BookMyBatisDAO();
        List<BookDTO> list=dao.bookList();
        //List<BoardDTO> list=new ArrayList<>();
        //list.add(new BoardDTO(1, "게시판 연습1", "게시판 연습1","관리자", new Date(), 0));
        //list.add(new BoardDTO(2, "게시판 연습2", "게시판 연습2","박매일", new Date(), 0));
        //list.add(new BoardDTO(3, "게시판 연습3", "게시판 연습3","홍길동", new Date(), 0));
       // 특정메모리에 객체를 바인딩하기(속성을 추가)
        req.setAttribute("list",list ); // list속성이름에<--실제 데이터가 위치한 번지가 저장
        RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/views/list.jsp"); // View의 논리적인 이름->View의 물리적인 이름
        //  선택한 View에 요청을 보내는 것
        // Controller가 View로 데이터(Object + DTO, List<DTO>)를 보내는 방법 -> 객체 바인딩(Object binding)
        rd.forward(req, resp);
    }
}
