package com.ohgiraffers.member.dto;


    public class MemberDTO {
        private String memberId;
        private String memberName;
        private String memberRentalist;
        private String memberStatus;


        public MemberDTO() {
        }

        public MemberDTO(String memberId, String memberName) {
            this.memberId = memberId;
            this.memberName = memberName;

        }

        public String getmemberId() {
            return memberId;
        }

        public void setmemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getmemberName() {
            return memberName;
        }

        public void setmemberName(String memberName) {
            this.memberName = memberName;
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

        @Override
        public String toString() {
            return "MemberDTO{" +
                    "memberId='" + memberId + '\'' +
                    ", memberName='" + memberName + '\'' +
                    ", memberRentalist='" + memberRentalist + '\'' +
                    ", memberStatus='" + memberStatus + '\'' +
                    '}';
        }
    }

