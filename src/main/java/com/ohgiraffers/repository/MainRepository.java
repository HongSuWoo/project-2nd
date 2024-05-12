package com.ohgiraffers.repository;

import com.ohgiraffers.dto.BookDTO;
import com.ohgiraffers.dto.MemberDTO;

import java.util.ArrayList;

public class MainRepository {
    private final ArrayList<MemberDTO> memberDTO = new ArrayList<>();
    private final ArrayList<BookDTO> bookDTO = new ArrayList<>();

    // 도서 대출 메서드
    public boolean memberNameValid(String memberName) {
        for (MemberDTO member : this.memberDTO)
            if (member.getMemberName().equals(memberName)) {
                return true;
            }
        return false;
    }

    public String rentABook(int memberNum) {
        if (memberNum >= bookDTO.size()) {
            return "해당하는 도서가 없습니다.";
        }
        BookDTO books = bookDTO.get(memberNum);

        if (books.getBookRent() != null) {
            return books.getBookRent() + " 님이 대여중입니다.";
        }
        MemberDTO members = memberDTO.get(memberNum);
        books.setBookRent(members.getMemberName());
        if (members.getMemberRentalList() == null) {
            members.setMemberRentalList(books.getBookName());
        } else {
            members.setMemberRentalList(members.getMemberRentalList() + " " + books.getBookName());
        }
        return members.getMemberName() + " 님에게 " + books.getBookName() + " 이 대여 되었습니다.";
    }

    // 도서 반납 메서드
    public String returnABook(int bookNum) {
        if (bookNum >= bookDTO.size()) {
            return "해당하는 도서가 없습니다.";
        }
        BookDTO books = bookDTO.get(bookNum);

        if (books.getBookRent() == null) {
            return "해당 책은 대여되지 않았습니다.";
        }
        books.setBookRent(null);
        return books.getBookName() + " 이 반납되었습니다.";
    }
}
