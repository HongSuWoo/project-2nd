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

    public ArrayList employeeViewAll() throws Exception {
        ArrayList employees = MemberRepository.employeeViewAll();

        if (employees == null) {
            throw new Exception("사원정보 조회실패");
        }

        return employees;
    }

    public MemberDTO employeeFindByName(String name) throws Exception {

        if (name == null && name.equals("")) {
            return null;
        }
        MemberDTO emp = MemberRepository.employeeFindByName(name);
        if (emp == null) {
            throw new Exception("사원정보 조회실패");
        }

        return emp;
    }

    public String empInsert(MemberinsertDTO emp) throws Exception {

        MemberDTO findEmp = MemberRepository.empFindById(emp.getMemberId());

        if (findEmp != null) {
            throw new Exception("중복회원");
        }
        int result = MemberRepository.empInsert(emp);

        if (result < 0) {
            throw new Exception("등록실패");
        }
        return (result > 0) ? "등록성공" : "등록실패";
    }

    public MemberDTO empFindById(String index) {
        MemberDTO findEmp = MemberRepository.empFindById(index);
        if (findEmp != null) {
            return findEmp;
        } else {
            return null;


        }

    }

    public MemberDTO empModify(String name, String index)throws Exception {
        if(name.equals("") || name == null){
            throw new Exception("반값 입력");
        }
        int result = MemberRepository.empModify(name,index);
        if (result < 0) {
            throw new Exception("변경실패");
        }
        MemberDTO modifyEmp = MemberRepository.empFindById(index);

        return modifyEmp;
    }
}