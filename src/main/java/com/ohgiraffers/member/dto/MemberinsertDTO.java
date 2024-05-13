package com.ohgiraffers.member.dto;


    public class MemberinsertDTO {

        private String memberId;
        private String memberName;
        private String memberRentalist;
        private String memberStatus;


        public MemberinsertDTO() {

        }

        public MemberinsertDTO(String memberId, String memberName) {
            this.memberId = memberId;
            this.memberName = memberName;
        }



        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getmemberName() {
            return memberName;
        }


        public String getMemberRentalist() {
            return memberRentalist;
        }

        public void setMemberRentalist(String memberRentalist) {
            this.memberRentalist = memberRentalist;
        }

        public String getMemberStatus() {
            return memberStatus;
        }

        public void setMemberStatus(String memberStatus) {
            this.memberStatus = memberStatus;
        }

        public void setmemberName(String memberName) {
            this.memberName = memberName;



    }

        @Override
        public String toString() {
            return "MemberinsertDTO{" +
                    "memberId='" + memberId + '\'' +
                    ", memberName='" + memberName + '\'' +
                    ", memberRentalist='" + memberRentalist + '\'' +
                    ", memberStatus='" + memberStatus + '\'' +
                    '}';
        }
    }



