package com.example.repository;
import com.example.entity.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// WEB : MVC Framework -> 변형 -> Spring Web MVC -> Spring boot
public class BookDAO {  // JDBC ->  Framework 도입(MyBatis) -> Hibernate -> JPA
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs; // SQL실행 결과(select)를 담는 객체

    public Connection getConnect(){
        String url="jdbc:mysql://localhost:3306/books"; // ip,port,db이름
        String username="root";
        String password="12345";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    // update 동작
    public int bookUpdate(BookDTO book){
        String SQL="update booktbl set price=?, page=? where num=?";
        conn=getConnect();
        int cnt=-1;
        try{
          ps=conn.prepareStatement(SQL);
          ps.setInt(1, book.getPrice());
          ps.setInt(2, book.getPage());
          ps.setInt(3, book.getNum());
          cnt=ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally { // 예외와 상관 없이 무조건 처리되는 블럭
            // 접속정보를 close
            dbClose();
        }
        return cnt;
    }
    // delete메서드
    public int bookDelete(int num){
        String SQL="delete from booktbl where num=?"; // 단일 트랜젝션->단위작업
        // 트랜젝션-> All or Nothing : commit() / rollback()
        conn=getConnect();
        //conn.commit();
        //conn.rollback();
        int cnt=-1;
        try{
           ps=conn.prepareStatement(SQL);
           ps.setInt(1, num);
           cnt=ps.executeUpdate();
        }catch(Exception e){
           e.printStackTrace();
        }finally {
           dbClose();
        }
        return cnt;
    }
    // select All
    public List<BookDTO> bookList(){
        List<BookDTO> list=new ArrayList<>();
        String SQL="select * from booktbl order by title desc"; // ?
        conn=getConnect(); // ->재활용(ConnectionPool) - HikariCP API
        try{
            ps=conn.prepareStatement(SQL); // ?
            rs=ps.executeQuery();
            while(rs.next()){
                int num=rs.getInt("num");
                String title=rs.getString("title");
                int price=rs.getInt("price");
                String name=rs.getString("name");
                int page=rs.getInt("page");
                // 묶고(DTO)->담고(List)
                BookDTO dto=new BookDTO(num, title, price, name, page);
                list.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
         return list;
    }
    // insert 동작
    public int bookInsert(BookDTO book) {
        String SQL = "insert into booktbl(title, price, name, page) values(?, ?, ?, ?)";
        conn = getConnect();
        int cnt = -1;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPrice());
            ps.setString(3, book.getName());
            ps.setInt(4, book.getPage());
            cnt = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }
        return cnt; // 1  or -1
    }
    public void dbClose(){
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // CRUD Method
}
