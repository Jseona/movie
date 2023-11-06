package com.example.movie.Service;

import com.example.movie.Constant.Role;
import com.example.movie.DTO.MemberDTO;
import com.example.movie.Entity.MemberEntity;
import com.example.movie.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //이메일로 회원을 조회
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        //보안 인증에서 해당 데이터로 로그인 처리
        return User.builder()
                .username(memberEntity.getName())
                .password(memberEntity.getPassword())
                .roles(memberEntity.getRole().toString())
                .build();
    }

    //회원가입 처리
    public void saveMember(MemberDTO memberDTO) throws Exception {
        //암호화 처리
        String password = passwordEncoder.encode(memberDTO.getPassword());

        //MemberEntity에 저장
        MemberEntity memberEntity = MemberEntity.builder()
                .email(memberDTO.getEmail())
                .name(memberDTO.getName())
                .password(password)
                .role(Role.USER)
                .build();

        validateDuplicateMember(memberEntity);
        memberRepository.save(memberEntity);
    }

    //이메일 중복 체크
    private void validateDuplicateMember(MemberEntity memberEntity) {
        //데이터베이스에서 조회
        MemberEntity findMember = memberRepository.findByEmail(memberEntity.getEmail());

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
