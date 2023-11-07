package com.example.movie.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {
    //파일 업로드
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        //UUID 로 파일이름으로 쓸 랜덤 문자열 생성
        UUID uuid = UUID.randomUUID();
        //확장자 추출
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        //저장할 파일명 만들기
        String saveFileName = uuid.toString() + extension;
        //저장할 위치와 파일을 합쳐서 작업파일 만들기
        String fileUPloadFullUrl = uploadPath + saveFileName;

        //지정된 위치에 지정된 이름으로 저장 처리
        FileOutputStream fos = new FileOutputStream(fileUPloadFullUrl);
        //읽어온 바이트를 하드에 저장
        fos.write(fileData);
        //파일 닫아주기
        fos.close();

        //저장한 파일명을 결과값으로 전달
        return saveFileName;
    }

    //파일 삭제(변경)
    public void deleteFile(String filePath, String fileName) throws Exception {
        String deleteFileName = filePath + fileName;

        File deleteFile = new File(deleteFileName);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
