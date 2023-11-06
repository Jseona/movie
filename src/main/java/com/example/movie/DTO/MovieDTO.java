package com.example.movie.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class MovieDTO {
    private int id;         //일련번호
    private String movie;   //영화명
    private String content; //내용
    private String img;     //이미지
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
