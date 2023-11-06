package com.example.movie.Controller;

import com.example.movie.DTO.MemberDTO;
import com.example.movie.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/login")
    public String memberLoginForm(Model model) throws Exception {
        return "member/login";
    }

    @GetMapping("/member/login/error")
    public String memberLoginError(Model model) throws Exception {
        model.addAttribute("loginErrorMsg","아이디나 비밀번호를 확인해 주세요.");
        return "member/login";
    }

    @GetMapping("/member/register")
    public String memberRegisterForm(Model model) throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "member/register";
    }

    @PostMapping("/member/register")
    public String memberRegisterProc(@Valid MemberDTO memberDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/member/register";
        }
        try {  //회원가입 성공 시
            memberService.saveMember(memberDTO);
            redirectAttributes.addAttribute("errorMessage", "가입을 축하드립니다.");
        } catch (IllegalStateException e) {  //회원가입 실패 시 서비스에서 등록한 에러메세지 띄우기
            redirectAttributes.addAttribute("errorMessage", e.getMessage());
            return "/member/register";
        }

        return "redirect:/";
    }

    @GetMapping("/member/logout")
    public String memberLogoutProc(Model model) throws Exception {
        return "redirect:/";
    }
}
