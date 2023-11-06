package com.example.movie.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDTO {
    private int id;         //일련번호
    private String name;    //회원명
    private String email;   //이메일
    private String password;//비밀번호
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
