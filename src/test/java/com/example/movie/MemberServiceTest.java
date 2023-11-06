package com.example.movie;

import com.example.movie.DTO.MemberDTO;
import com.example.movie.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void saveMemberTest() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("홍길동");
        memberDTO.setEmail("test2@gmail.com");
        memberDTO.setPassword("1111");

        memberService.saveMember(memberDTO);
    }
}
