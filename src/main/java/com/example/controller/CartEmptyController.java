package com.example.controller;
// Servlet의 기본 골격

import com.example.entity.Customer;
import com.example.entity.Payment;
import com.example.repository.ShopMyBatisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8081/shopping/main -----> /WEB-INF/views/template.jsp
@WebServlet("/order")
public class CartEmptyController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        int totalAmount = Integer.parseInt(req.getParameter("totalAmount"));
        float point = Integer.parseInt(req.getParameter("totalPoint"));
        boolean useRewards = Boolean.parseBoolean(req.getParameter("useRewards"));
        ShopMyBatisDAO dao = new ShopMyBatisDAO();

        Customer cus = new Customer();
        cus.setReserves((int) point);

        Payment pay = new Payment();

        pay.setTotal_price(totalAmount);
        pay.setCustomer_id(customer_id);
        if(useRewards){
            int totalPoint = dao.customer(customer_id).getReserves();
            int actualAmount = 0;
            if(totalPoint > totalAmount){
                actualAmount = 0;
                totalPoint -= totalAmount;
            }else{
                actualAmount = totalAmount - totalPoint;
            }
            pay.setDiscount(totalPoint);
            cus.setReserves(-totalPoint);
            pay.setActual_price(actualAmount);
        }

        cus.setCustomer_id(customer_id);
        dao.pointSave(cus);

        int cnt = dao.cartOrder(customer_id);
        dao.payment(pay);

        PrintWriter out = resp.getWriter();
        out.println(cnt);
    }
}
