package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 책(Object) -> 제목, 가격, 지은이, 페이지수 : 속성, 멤버, 상태(O)
@Data   //Lombok API
@AllArgsConstructor // 오버로딩된 생성자
@NoArgsConstructor // 기본생성자
public class BookDTO { // DTO -> 어떻게 설계하는 것이 잘 설계하는 것인가? , VO
    // 1. 모든 멤버를 정보은닉시킨다.(private)
    private int num; // setter, getter
    private String title;
    private int price;
    private String name;
    private int page;

}
