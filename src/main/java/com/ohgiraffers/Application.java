package com.ohgiraffers;

import com.ohgiraffers.controller.MainController;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        // 대출관련 기능을 만들어야함
        // 도서관리 메뉴를 만들어야함
        // 회원관리 메뉴를 만들어야함
        MainController mainController = new MainController();
        Scanner scanner = new Scanner(System.in);

        // 4가지 기능을 사용할 메뉴만들기
        Boolean isOn = true;
        while (isOn) {
            // 메뉴를 출력한다.
            System.out.println("================================= 도서 대여 프로그램 =================================");
            System.out.println("1. 대출메뉴");
            System.out.println("2. 도서관리메뉴");
            System.out.println("3. 회원관리메뉴");
            System.out.println("4. 반납메뉴");
            System.out.println("9. 프로그램 종료");
            System.out.print("원하는 메뉴를 입력해주세요: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1 -> {
                    // 대출메뉴 접속
                    mainController.rentABook();
                }
                case 2 -> {
                    // 도서관리메뉴 접속

                }
                case 3 -> {
                    // 회원관리메뉴 접속

                }
                case 4 -> {
                    // 반납메뉴 접속
                    mainController.returnABook();
                }
                case 9 -> {
                    // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    isOn = false;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
