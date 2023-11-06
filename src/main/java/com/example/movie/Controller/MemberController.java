package com.example.movie.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping("/member/login")
    public String memberLoginForm(Model model) throws Exception {
        return "member/login";
    }

    @GetMapping("/member/login/error")
    public String memberLoginError(Model model) throws Exception {
        return "member/login";
    }

    @GetMapping("/member/register")
    public String memberRegisterForm(Model model) throws Exception {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String memberRegisterProc(Model model) throws Exception {
        return "redirect:/";
    }

    @GetMapping("/member/logout")
    public String memberLogoutProc(Model model) throws Exception {
        return "redirect:/";
    }
}
