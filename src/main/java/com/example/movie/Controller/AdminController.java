package com.example.movie.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/list")
    public String adminListForm(Model model) throws Exception {
        return "admin/list";
    }

    @GetMapping("/admin/register")
    public String adminRegisterForm(Model model) throws Exception {
        return "admin/register";
    }

    @PostMapping("/admin/register")
    public String adminRegisterProc(Model model) throws Exception {
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/detail")
    public String adminDetailForm(Model model) throws Exception {
        return "admin/detail";
    }

    @GetMapping("/admin/update")
    public String adminUpdateForm(Model model) throws Exception {
        return "admin/update";
    }

    @PostMapping("/admin/update")
    public String adminUpdateProc(Model model) throws Exception {
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/delete")
    public String adminDelete(Model model) throws Exception {
        return "redirect:/admin/list";
    }
}
