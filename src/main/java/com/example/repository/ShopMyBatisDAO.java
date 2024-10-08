package com.example.repository;

import com.example.entity.CusPro;
import com.example.entity.CusProProduct;
import com.example.entity.Customer;
import com.example.entity.Payment;
import com.example.entity.Product;
import com.example.entity.Region;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ShopMyBatisDAO { // MyBatis API
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

      public Customer customer(String customer_id){
          SqlSession session = sqlSessionFactory.openSession();
          Customer cus = session.selectOne("customer", customer_id);
          session.close();
          return cus;
      }

      public int registerCustomer(Customer customer){
          int result = -1;
          SqlSession session = sqlSessionFactory.openSession();
          result = session.insert("registerCustomer", customer);
          session.commit();
          session.close();
          return result;
      }

      public List<Region> regionList(){
          SqlSession session = sqlSessionFactory.openSession();
          List<Region> list = session.selectList("regionList");
          session.close();
          return list;
      }

      public List<Product> productList(){
          SqlSession session = sqlSessionFactory.openSession();
          List<Product> list = session.selectList("productList");
          session.close();
          return list;
      }

      public Product productDetail(int product_num){
          SqlSession session = sqlSessionFactory.openSession();
          Product product = session.selectOne("productDetail", product_num);
          session.close();
          return product;
      }

      public Customer customer_login(Customer cus) {
          SqlSession session = sqlSessionFactory.openSession();
          Customer cusDto = session.selectOne("customer_login", cus);
          session.close();
          return cusDto;
      }

      public int cartAdd(CusPro dto) {
          SqlSession session = sqlSessionFactory.openSession();

          CusPro checkDto = session.selectOne("checkAdd", dto);

          int cnt = -1;
          if(checkDto!=null){
              cnt = session.update("cartUpdate", dto);
          }else{
              cnt=session.insert("cartAdd", dto);
          }
          session.commit();
          session.close();
          return cnt;
      }

      public List<CusProProduct> cartList(String customer_id) {
          SqlSession session = sqlSessionFactory.openSession();
          List<CusProProduct> list = session.selectList("cartList", customer_id);
          session.close();
          return list;
      }

      public int totalAmount(String customer_id){
            SqlSession session = sqlSessionFactory.openSession();
            int totalAmount = session.selectOne("totalAmount", customer_id);
            session.close();
            return totalAmount;
      }

      public int cartCancel(int order_number){
            SqlSession session = sqlSessionFactory.openSession();
            int cnt = session.delete("cartCancel", order_number);
            session.commit();
            session.close();
            return cnt;
      }

      public int cartOrder(String customer_id){
            SqlSession session = sqlSessionFactory.openSession();
            int cnt = session.update("updateStock", customer_id);
            cnt = session.delete("cartOrder", customer_id);
            session.commit();
            session.close();
            return cnt;
      }

      public int pointSave(Customer cus){
            SqlSession session = sqlSessionFactory.openSession();
            int cnt = session.update("pointSave", cus);
            session.commit();
            session.close();
            return cnt;
      }

      public int updateQuantity(CusPro dto){
            SqlSession session = sqlSessionFactory.openSession();
            int cnt = session.update("updateQuantity", dto);
            session.commit();
            session.close();
            return cnt;
      }

      public void payment(Payment pay){
            SqlSession session = sqlSessionFactory.openSession();
            session.insert("payment", pay);
            session.commit();
            session.close();
      }

}
