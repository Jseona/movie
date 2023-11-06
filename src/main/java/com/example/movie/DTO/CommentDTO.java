package com.example.movie.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private int id;         //일련번호
    private String comment; //댓글
    private int grade;      //평점
    private int movieid;    //영화번호
    private String email;   //이메일
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
