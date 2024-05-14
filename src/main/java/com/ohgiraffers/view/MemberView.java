package com.ohgiraffers.view;

import com.ohgiraffers.member.dto.MemberDTO;
import com.ohgiraffers.member.dto.MemberinsertDTO;
import com.ohgiraffers.service.MemberService;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberView {

        private static boolean state = true;
        private static MemberService memberService = new MemberService();

        public static void run(){
            while (state){
                System.out.println("1. 회면 전체보기");
                System.out.println("2. 회원 이름으로 조회하기 ");
                System.out.println("3. 회원 정보 등록하기");
                System.out.println("4. 회원 정보 수정하기");
                System.out.print("화면 번호를 입력해주세요 : ");

                Scanner sc = new Scanner(System.in);
                int index = Integer.parseInt(sc.nextLine());

                switch (index){
                    case 1 :
                        memberViewAll();
                        break;
                    case 2 :
                        memberFindByName();
                        break;
                    case 3 :
                        memberInsert();
                        break;
                    case 4 :
                        memberUpdate();
                        break;
                }
                System.out.print("종료를 하시겠습니까? 말해 (yes Or no) 오타x 소문자만 : ");
                String result = sc.nextLine();

                if(result.equals("yes")){
                    state = false;
                    sc.close();
                }
            }

        }

        // 현재 html의 화면을 암시하고 만든 것이다.
        // view는 사용자에게 데이터를 입력받고 서버에 전달하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.
        public static void memberViewAll() {
            System.out.println("~~~~~~~~~~~~~~~~~~~");
            System.out.println("☆회원 정보 전체 조회☆");

            try {
                ArrayList members = memberService.memberViewAll();
                System.out.println(members);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static void memberFindByName(){
            Scanner sc = new Scanner(System.in);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("조회할 회원의 이름을 입력하세요 : ");
            String name = sc.nextLine();
            MemberDTO member = null;
            try {
                member = memberService.memberFindByName(name);
                System.out.println(member);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        public static void memberInsert(){
            Scanner sc = new Scanner(System.in);
            MemberinsertDTO member = new MemberinsertDTO();
            System.out.println("~~~~~~~~~~~~~~~~~~~");
            System.out.println("☆등록할 사원의 정보를 입력해주세요☆");
            System.out.print("회원의 번호를 입력해주세요 : ");
            member.setMemberId(sc.nextLine());
            System.out.println("회원의 이름을 입력해 주세요 : ");
            member.setmemberName(sc.nextLine());

            try {
                String result = memberService.memberInsert(member);
                System.out.println(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        public static void memberUpdate(){
            Scanner sc = new Scanner(System.in);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("☆변경할 사원번호를 입력하세요☆");
            String index = sc.nextLine();
            MemberDTO member = memberService.memberFindById(index);

            if(member == null){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("☆변경할 회원이 존재하지 않습니다☆");
                return;
            }
            System.out.println(member);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("☆변경할 이름을 입력해주세요☆");
            String name = sc.nextLine();
            try {
                MemberDTO modifymember = memberService.memberModify(name, index);
                System.out.println(modifymember);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
}
