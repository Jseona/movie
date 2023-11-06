package com.example.movie.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "movie")
@SequenceGenerator(
        name = "movie_SEQ",
        sequenceName = "movie_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "movie_SEQ")
    private int id;         //일련번호
    @Column(length = 100, nullable = false)
    private String movie;   //영화명
    @Column
    private String content; //내용
    @Column
    private String img;     //이미지
}
