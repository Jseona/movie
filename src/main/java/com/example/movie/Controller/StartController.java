package com.example.movie.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StartController {
    @GetMapping("/")
    public String start(RedirectAttributes redirectAttributes) {

        return "index";
    }
}
