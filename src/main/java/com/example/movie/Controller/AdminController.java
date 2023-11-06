package com.example.movie.Controller;

import com.example.movie.DTO.CommentDTO;
import com.example.movie.DTO.MovieDTO;
import com.example.movie.Service.CommentService;
import com.example.movie.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MovieService movieService;
    private final CommentService commentService;

    @GetMapping("/admin/list")
    public String adminListForm(@PageableDefault(page=1) Pageable pageable,
                                Model model) throws Exception {
        Page<MovieDTO> movieDTOS = movieService.list(pageable);

        //페이지 정보
        int blockLimit = 5;
        //시작페이지
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        //끝페이지
        int endPage = Math.min(startPage+blockLimit-1, movieDTOS.getTotalPages());

        //페이지버튼 정보(HTML에서 작성도 가능, [첫,이전, 페이지번호, 다음, 끝]
        int prevPage = movieDTOS.getNumber(); //이전페이지
        int curPage = movieDTOS.getNumber()+1;//현재페이지
        int nextPage = movieDTOS.getNumber()+2;//다음페이지
        int lastPage = movieDTOS.getTotalPages(); //마지막페이지

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        model.addAttribute("movieDTOS", movieDTOS);

        return "admin/list";
    }

    @GetMapping("/admin/register")
    public String adminRegisterForm(Model model) throws Exception {
        MovieDTO movieDTO = new MovieDTO();

        model.addAttribute("movieDTO",movieDTO);
        return "admin/register";
    }

    @PostMapping("/admin/register")
    public String adminRegisterProc(@Valid MovieDTO movieDTO,
                                    BindingResult bindingResult,
                                    Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "admin/register";
        }
        movieService.create(movieDTO);
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/detail")
    public String adminDetailForm(int id, Model model) throws Exception {
        //해당게시글 조회
        MovieDTO movieDTO = movieService.read(id);
        //댓글 조회
        List<CommentDTO> commentDTOS = commentService.list(id);

        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("commentDTOS", commentDTOS);

        return "admin/detail";
    }

    @GetMapping("/admin/update")
    public String adminUpdateForm(int id, Model model) throws Exception {
        MovieDTO movieDTO = movieService.read(id);

        model.addAttribute("movieDTO", movieDTO);
        return "admin/update";
    }

    @PostMapping("/admin/update")
    public String adminUpdateProc(@Valid MovieDTO movieDTO,
                                  BindingResult bindingResult,
                                  Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "movie/update";
        }
        movieService.modify(movieDTO);
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/delete")
    public String adminDelete(int id, Model model) throws Exception {
        movieService.remove(id);

        return "redirect:/admin/list";
    }
}
