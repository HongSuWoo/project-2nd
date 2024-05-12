package com.ohgiraffers.dto;

public class BookDTO {

    private int bookNum;
    private String bookName; //1
    private String bookRent; //

    // BookDTO --------------------------------------------------------------------------------------------------------

    public BookDTO(int bookNum, String bookName, String bookRent) {
        this.bookNum = bookNum;
        this.bookName = bookName;
        this.bookRent = bookRent;
    }

    public BookDTO() {
    }

    // Getter & Setter ------------------------------------------------------------------------------------------------

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookRent() {
        return bookRent;
    }

    public void setBookRent(String bookRent) {
        this.bookRent = bookRent;
    }

    // toString -------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookNum=" + bookNum +
                ", bookName='" + bookName + '\'' +
                ", bookRent='" + bookRent + '\'' +
                '}';
    }
}
