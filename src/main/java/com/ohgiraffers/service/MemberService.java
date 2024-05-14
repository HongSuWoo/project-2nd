package com.ohgiraffers.service;

import com.ohgiraffers.member.dao.MemberRepository;
import com.ohgiraffers.member.dto.MemberDTO;
import com.ohgiraffers.member.dto.MemberinsertDTO;

import java.util.ArrayList;

public class MemberService {

    private MemberRepository MemberRepository;

    public MemberService() {
        this.MemberRepository = new MemberRepository();
    }

    public ArrayList memberViewAll() throws Exception {
        ArrayList members = MemberRepository.memberViewAll();

        if (members == null) {
            throw new Exception("사원정보 조회실패");
        }

        return members;
    }

    public MemberDTO memberFindByName(String name) throws Exception {

        if (name == null && name.equals("")) {
            return null;
        }
        MemberDTO member = MemberRepository.memberFindByName(name);
        if (member == null) {
            throw new Exception("사원정보 조회실패");
        }

        return member;
    }

    public String memberInsert(MemberinsertDTO member) throws Exception {

        MemberDTO findEmp = MemberRepository.memberFindById(member.getMemberId());

        if (findEmp != null) {
            throw new Exception("중복회원");
        }
        int result = MemberRepository.memberInsert(member);

        if (result < 0) {
            throw new Exception("등록실패");
        }
        return (result > 0) ? "등록성공" : "등록실패";
    }

    public MemberDTO memberFindById(String index) {
        MemberDTO findmember = MemberRepository.memberFindById(index);
        if (findmember != null) {
            return findmember;
        } else {
            return null;


        }

    }

    public MemberDTO memberModify(String name, String index)throws Exception {
        if(name.equals("") || name == null){
            throw new Exception("반값 입력");
        }
        int result = MemberRepository.memberModify(name,index);
        if (result < 0) {
            throw new Exception("변경실패");
        }
        MemberDTO modifyEmp = MemberRepository.memberFindById(index);

        return modifyEmp;
    }
}