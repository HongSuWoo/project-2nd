package com.ohgiraffers.dao;


import com.ohgiraffers.repository.MainRepository;

public class MainService {
    MainRepository mainRepository = new MainRepository();

    // 도서 대출 메서드
    public String memberNameValid(String memberName) {
        return String.valueOf(mainRepository.memberNameValid(memberName));
    }

    public String rentABook(int memberNum) {
        return mainRepository.rentABook(memberNum);
    }

    // 도서 반납 메서드
    public void returnABook(int bookNum) {
        mainRepository.returnABook(bookNum);
    }
}
