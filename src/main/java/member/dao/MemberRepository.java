package member.dao;

import member.dto.MemberDTO;
import member.dto.MemberInsertDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import static common.JDBCTemplate.*;

public class MemberRepository
{
    private Properties pros =new Properties();
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rset = null;

    public MemberRepository() {
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/member/mapper/member-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList memberViewAll()
    {
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("memberAll");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()){
                MemberDTO member = new MemberDTO();
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));
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
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));

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
        String query = pros.getProperty("memberFindById");
        con = getConnection();
        MemberDTO member = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, index);
            rset = pstmt.executeQuery();
            if(rset.next()){
                member = new MemberDTO();
                member.setMemberNum(rset.getString("memberNum"));
                member.setMemberName(rset.getString("memberName"));

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

    public int memberInsert(MemberInsertDTO member) {
        // 값을 추가
        // 쿼리 가져옴
        String query = pros.getProperty("memberInsert");
        // connection
        con = getConnection();
        int result = 0;
        // 쿼리를 사용하기 위함
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,member.getMemberNum());
            pstmt.setString(2,member.getMemberName());


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
        // 쿼리불러오기
        String query = pros.getProperty("memberModify");
        con = getConnection();
        int result = 0;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, index);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;



    }

    public int memberDelete(String name, String index)
    {
        String query = pros.getProperty("memberDelete");
        con = getConnection();
        int result = 0;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, index);
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

