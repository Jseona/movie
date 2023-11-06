package com.example.movie.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {
    @GetMapping("/movie/list")
    public String movieListForm(Model model) throws Exception {
        return "movie/list";
    }

    @GetMapping("/movie/detail")
    public String movieDetailForm(Model model) throws Exception {
        return "movie/detail";
    }

    @PostMapping("/movie/detail")
    public String movieDetailProc(Model model) throws Exception {
        return "movie/detail";
    }
}
