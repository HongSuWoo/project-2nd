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

    private Properties pros =new Properties();
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;

    public MemberRepository() {
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList employeeViewAll(){
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("memberAll");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()){
                MemberDTO emp = new MemberDTO();
                emp.setmemberId(rset.getString("memberNum"));
                emp.setmemberName(rset.getString("memberName"));
                arrayList.add(emp);
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

    public MemberDTO employeeFindByName(String name) {
        String query = pros.getProperty("memberFindByName");
        con = getConnection();
        MemberDTO emp = new MemberDTO();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            rset = pstmt.executeQuery();
            if(rset.next()){
                emp.setmemberId(rset.getString("memberNum"));
                emp.setmemberName(rset.getString("memberName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return emp;

    }
    public MemberDTO empFindById(String index){
        String query = pros.getProperty("memberFindById"); //xml에서 가져옴
        con = getConnection(); // db랑 연결
        MemberDTO emp = null;    //  트라이 스코프 밖에서 반환하게해줘야함 빈값인지 아닌지 확인하기위해 null로 바꿈
        try {
            pstmt = con.prepareStatement(query); // my sql 말하는 방법 을 알려달라고 하는거임 위에 커리를 가져
            pstmt.setString(1, index);   //sql이랑 연결된 에러 커서올리고 more 트라이 케치 눌러서 말하는방법을 알게됨 번역기를 돌린거랑같은개념

            rset = pstmt.executeQuery();

            if(rset.next()){
                emp = new MemberDTO(); // rset 을 확인하기위해 다시 안으로 넣음
                emp.setmemberId(rset.getString("memberNum"));
                emp.setmemberName(rset.getString("memberName"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return emp;
    }

    public int empInsert(MemberinsertDTO emp) {
        // 값을 추가
        //쿼리 가져옴
        String query = pros.getProperty("memberInsert");
        //connection
        con = getConnection();
        int result = 0;
        // 쿼리를 사용하기 위함
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, emp.getMemberId());
            pstmt.setString(2, emp.getmemberName());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;
    }

    public int empModify(String name, String index) {
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