package com.example.repository;

import com.example.entity.BookDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class BookMyBatisDAO { // MyBatis API
      // DB연결 -> config.xml(환경설정파일) -> API read 연결작업을 대신 해주면 된다.
     private static SqlSessionFactory sqlSessionFactory; // Connection(SqlSession) Pool
      static{  // 초기화 블럭
           try{
               String resource = "mybatis-config/config.xml";
               InputStream inputStream = Resources.getResourceAsStream(resource);
               sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
           }catch(Exception e){
               e.printStackTrace();
           }
      }
      // CRUD Method
      public List<BookDTO> bookList(){
          SqlSession session=sqlSessionFactory.openSession();
          // select * from booktbl order by title desc
          List<BookDTO> list=session.selectList("bookList");
          session.close(); // 반납
          return list;
      }
    public int bookInsert(BookDTO book) {
         SqlSession session=sqlSessionFactory.openSession();
         // insert into booktbl(title, price, name, page) values(?, ?, ?, ?)
         int cnt=session.insert("bookInsert", book ) ;
         session.commit(); // 완료명령
         session.close(); // 반납
          return cnt;
    }
}
