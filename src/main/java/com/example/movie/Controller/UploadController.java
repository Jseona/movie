package com.example.movie.Controller;

import com.example.movie.Service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UploadController {
    private final UploadService uploadService;

    @PostMapping("/upload/upload")
    public String uploadProc(String title, MultipartFile file, Model model) throws Exception {
        log.info("받은 파일명 : " + file.getOriginalFilename());
        String newFileName = uploadService.saveImg(file);
        log.info("저장 시 사용한 파일명 : " + newFileName);

        model.addAttribute("img", "/imges/item/" + newFileName);
        model.addAttribute("imgFile", newFileName);

        return "redirect:/admin/list";
    }
}
