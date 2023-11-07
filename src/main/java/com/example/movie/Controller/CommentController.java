package com.example.movie.Controller;

import com.example.movie.DTO.CommentDTO;
import com.example.movie.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/register")
    public String registerProc(int no, CommentDTO commentDTO, RedirectAttributes redirectAttributes) throws Exception {
        commentService.create(no, commentDTO);

        redirectAttributes.addAttribute("id", no);
        return "redirect:/movie/detail";
    }

    @GetMapping("/comment/remove")
    public String removeProc(int no, int id, RedirectAttributes redirectAttributes) throws Exception {
        commentService.remove(id);

        redirectAttributes.addAttribute("id", no);
        return "redirect:/movie/detail";
    }
}
