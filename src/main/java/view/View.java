package view;

import member.dto.*;
import member.service.MemberService;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private static MemberService memberService = new MemberService();
    private static boolean state = true;

    public static void run() {
        while (state) {
            System.out.println("화면 번호를 입력해주세요 : ");
            System.out.println("1. 화면 전체보기");
            System.out.println("2. 멤버 이름으로 조회하기 ");
            System.out.println("3. 멤버 정보 등록하기");
            System.out.println("4. 멤버 정보 수정하기");
            System.out.println("5. 멤버 정보 삭제 하기");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine());

            switch (index) {
                case 1:
                    memberViewAll();
                    break;
                case 2:
                    memberFindByName();
                    break;
                case 3:
                    memberInsert();
                    break;
                case 4:
                    memberUpdate();
                    break;
                case 5:
                    memberDelete();
            }
            System.out.print("종료를 하시겠습니까? 말해 (yes Or no) 오타x 소문자만 : ");
            String result = sc.nextLine();

            if (result.equals("yes")) {
                state = false;
                sc.close();
            }
        }

    }

    private static void memberDelete()
    {

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제할 회원을 입력하세요");
            String index = sc.nextLine();
            MemberDTO member = memberService.memberFindById(index);

            if (member == null) {
                System.out.println("삭제할 회원이 존재하지 않습니다.");
                return;
            }
            System.out.println(member);
            System.out.println("삭제할 이름을 입력해주세요");
            String name = sc.nextLine();
            try {
                MemberDTO modifyMember = memberService.memeberDelete(name, index);
                System.out.println(modifyMember);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }




    }

    // 현재 html의 화면을 암시하고 만든 것이다.
    // view는 사용자에게 데이터를 입력받고 서버에 전달하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.
    public static void memberViewAll() {
        System.out.println("멤 정보 전체 조회");

        try {
            ArrayList member = memberService.memberViewAll();
            System.out.println(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void memberFindByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 이름을 입력하세요 : ");
        String name = sc.nextLine();
        MemberDTO member = null;

        try {
            member = memberService.memberFindByName(name);
            System.out.println(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void memberInsert() {
        Scanner sc = new Scanner(System.in);
        MemberInsertDTO member = new MemberInsertDTO();

        System.out.println("등록할 회원의 정보를 입력해주세요 ");
        System.out.print("회원의 번호를 입력해주세요 : ");
        member.setMemberNum(sc.nextLine());
        System.out.print("회원의 이름을 입력해주세요 : ");
        member.setMemberName(sc.nextLine());

        try {
            String result = memberService.memberInsert(member);

            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void memberUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 회원을 입력하세요");
        String index = sc.nextLine();
        MemberDTO member = memberService.memberFindById(index);

        if (member == null) {
            System.out.println("변경할 회원이 존재하지 않습니다.");
            return;
        }
        System.out.println(member);
        System.out.println("변경할 이름을 입력해주세요");
        String name = sc.nextLine();
        try {
            MemberDTO modifyMember = memberService.memeberModify(name, index);
            System.out.println(modifyMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
