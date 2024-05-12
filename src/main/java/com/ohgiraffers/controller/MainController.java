package com.ohgiraffers.controller;

import com.ohgiraffers.dao.MainService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {
    Scanner scanner = new Scanner(System.in);
    MainService mainService = new MainService();
    private boolean valid;
    private String result;
    private int bookNum;

    // 대출관련 메서드 작성 ------------------------------------------------------------------------------------------------
    public String rentABook() {
        valid = true;
        System.out.println("================================= 도서 대출 메뉴 =================================");
        while (valid) {
            // 대출받을 회원을 번저 검증한다.
            System.out.print("회원의 이름을 입력해 주세요: ");
            String memberName = scanner.nextLine();
            int value = Integer.parseInt(mainService.memberNameValid(memberName));

            // 검증받은 값을 반환 받아 등록된 회원인지 출력.
            if (value == -1) {
                System.out.println("등록된 회원이 없습니다.");
            } else {
                try {
                    System.out.print("대출할 책의 번호를 입력해 주세요: ");
                    bookNum = scanner.nextInt();
                    scanner.nextLine();
                    valid = false; // 반복문 종료.
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.");
                    scanner.nextLine();
                }
            }
            result = mainService.rentABook(bookNum);
        }
        return result;
    }

    // 반납관련 메서드 작성 ------------------------------------------------------------------------------------------------
    public void returnABook() {
        valid = true;
        System.out.println("================================= 도서 반납 메뉴 =================================");
        while (valid) {
            try {
                System.out.print("반납할 책의 번호를 입력해 주세요: ");
                bookNum = scanner.nextInt();
                scanner.nextLine();
                valid = false; // 반복문 종료.
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                scanner.nextLine();
            }
        }
        mainService.returnABook(bookNum);
    }
}
