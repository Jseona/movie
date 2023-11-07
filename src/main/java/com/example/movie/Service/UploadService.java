package com.example.movie.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadService {
    @Value("${imgLocation}")
    private String imgLocation;

    private final FileService fileService;

    //파일저장
    public String saveImg(MultipartFile imgFile) throws Exception {
        //파일명 읽기(원본 이미지 파일명)
        String originalFileName = imgFile.getOriginalFilename();

        //파일 저장 후 uuid로 생성한 파일명을 저장할 변수 생성
        String newFileName = "";
        if (!StringUtils.isEmpty(originalFileName)) {
            newFileName = fileService.uploadFile(imgLocation, originalFileName, imgFile.getBytes());
        }

        //새로운 파일명을 데이터베이스에 전달
        return newFileName;
    }
}
