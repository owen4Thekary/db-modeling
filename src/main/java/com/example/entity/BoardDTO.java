package com.example.entity;

import java.util.Date;

public class BoardDTO {
    private int num;
    private String title;
    private String content;
    private String writer;
    private Date indate;
    private int count;
    // 기본생성자, 오버로딩 ~~~, setter, getter -> Lombock API
   public  BoardDTO(){        }
    public BoardDTO(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public BoardDTO(int num, String title, String content, String writer, Date indate, int count) {
        this.num = num;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.indate = indate;
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", indate=" + indate +
                ", count=" + count +
                '}';
    }
}
