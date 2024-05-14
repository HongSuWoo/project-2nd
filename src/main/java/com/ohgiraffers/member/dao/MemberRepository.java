package com.ohgiraffers.member.dao;

import com.ohgiraffers.member.dto.MemberDTO;
import com.ohgiraffers.member.dto.MemberinsertDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class MemberRepository {

    private Properties pros =new Properties(); // 아이디와 비밀번호 명령어(쿼리) 를 가져온다
    private Connection con = null; // 내가 연결하려는 주소
    private PreparedStatement pstmt = null; // 공간
    private ResultSet rset = null; //

    public MemberRepository() {
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public ArrayList memberViewAll(){
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("memberAll"); //pros 아이디 비밀번호 명령어 (쿼리)
        con = getConnection(); //연결하는 주소
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery(); // 쿼리 발송
            while (rset.next()){
                MemberDTO member = new MemberDTO();
                member.setmemberId(rset.getString("memberNum"));
                member.setmemberName(rset.getString("memberName"));
                arrayList.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return arrayList;
    }

    public MemberDTO memberFindByName(String name) {
        String query = pros.getProperty("memberFindByName");
        con = getConnection();
        MemberDTO member = new MemberDTO();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rset = pstmt.executeQuery();
            if(rset.next()){
                member.setmemberId(rset.getString("memberNum"));
                member.setmemberName(rset.getString("memberName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return member;

    }
    public MemberDTO memberFindById(String index){


        String query = pros.getProperty("memberFindById"); //xml에서 가져옴
        con = getConnection(); // db랑 연결
        MemberDTO member = null;    //  트라이 스코프 밖에서 반환하게해줘야함 빈값인지 아닌지 확인하기위해 null로 바꿈


        try {
            pstmt = con.prepareStatement(query); // my sql 말하는 방법 을 알려달라고 하는거임 위에 커리를 가져
            pstmt.setString(1, index);   //sql이랑 연결된 에러 커서올리고 more 트라이 케치 눌러서 말하는방법을 알게됨 번역기를 돌린거랑같은개념

            rset = pstmt.executeQuery();

            if(rset.next()){
                member = new MemberDTO(); // rset 을 확인하기위해 다시 안으로 넣음
                member.setmemberId(rset.getString("memberNum"));
                member.setmemberName(rset.getString("memberName"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return member;
    }

    public int memberInsert(MemberinsertDTO member) {
        // 값을 추가
        //쿼리 가져옴
        String query = pros.getProperty("memberInsert");
        //connection
        con = getConnection();
        int result = 0;
        // 쿼리를 사용하기 위함
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getmemberName());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;
    }

    public int memberModify(String name, String index) {
        //쿼리 불러오기
        String query = pros.getProperty("memberModify");
        con = getConnection();
        int result = 0;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, index);
            pstmt.executeUpdate();
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
        return result;
    }
}