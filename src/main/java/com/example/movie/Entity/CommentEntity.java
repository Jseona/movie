package com.example.movie.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment")
@SequenceGenerator(
        name = "comment_SEQ",
        sequenceName = "comment_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "comment_SEQ")
    private int id;         //일련번호
    @Column(length = 100, nullable = false)
    private String comment; //댓글
    @Column
    private int grade;      //평점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieid")
    private MovieEntity movieEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private MemberEntity memberEntity;
}
