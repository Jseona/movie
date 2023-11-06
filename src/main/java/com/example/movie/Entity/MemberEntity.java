package com.example.movie.Entity;

import com.example.movie.Constant.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "member")
@SequenceGenerator(
        name = "member_SEQ",
        sequenceName = "member_SEQ",
        initialValue = 1,
        allocationSize = 1)
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "member_SEQ")
    private int id;         //일련번호
    @Column(length = 100, nullable = false)
    private String name;    //회원명
    @Column(length = 100, unique = true)
    private String email;   //이메일
    @Column
    private String password;//비밀번호
    @Enumerated(EnumType.STRING)
    private Role role;      //회원분류
}
