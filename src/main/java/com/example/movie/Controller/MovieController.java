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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final CommentService commentService;

    @GetMapping("/movie/list")
    public String movieListForm(@PageableDefault(page=1) Pageable pageable, Model model) throws Exception {
        Page<MovieDTO> movieDTOS = movieService.list(pageable);

        //페이지 정보
        int blockLimit = 5;
        //시작페이지
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        //끝페이지
        int endPage = Math.min(startPage+blockLimit-1,movieDTOS.getTotalPages());

        //페이지버튼 정보
        int prevPage = movieDTOS.getNumber();  //이전페이지
        int curPage = movieDTOS.getNumber()+1;  //현재페이지
        int nextPage = movieDTOS.getNumber()+2;  //다음페이지
        int lastPage = movieDTOS.getTotalPages();  //마지막페이지

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("lastPage", lastPage);

        model.addAttribute("movieDTOS", movieDTOS);

        return "movie/list";
    }

    @GetMapping("/movie/detail")
    public String movieDetailForm(int id, Model model) throws Exception {
        //해당 게시글 조회
        MovieDTO movieDTO = movieService.read(id);
        //댓글 조회
        List<CommentDTO> commentDTOS = commentService.list(id);

        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("commentDTOS", commentDTOS);

        return "movie/detail";
    }

    @PostMapping("/movie/detail")
    public String movieDetailProc(int no, CommentDTO commentDTO,
                                  RedirectAttributes redirectAttributes) throws Exception {
        commentService.create(no, commentDTO);

        redirectAttributes.addAttribute("id", no);

        return "redirect:/movie/detail";
    }
}
