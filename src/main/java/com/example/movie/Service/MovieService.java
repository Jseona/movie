package com.example.movie.Service;

import com.example.movie.DTO.MovieDTO;
import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    @Value("${imgLocation}")
    private String imgUploadLocation;

    private final MovieRepository movieRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper = new ModelMapper();

    //삭제
    public void remove(int id) throws Exception {
        MovieEntity read = movieRepository.findById(id).orElseThrow();
        fileService.deleteFile(imgUploadLocation, read.getImg());

        movieRepository.deleteById(id);
    }

    //수정
    public void modify(MovieDTO movieDTO, MultipartFile imgFile) throws Exception {
        //기존파일 삭제
        MovieEntity read = movieRepository.findById(movieDTO.getId()).orElseThrow();
        String deleteFile = read.getImg();

        //삽입
        String originalFileName = imgFile.getOriginalFilename();
        String newFileName = "";

        if (originalFileName.length() != 0) {  //파일이 존재하면
            if (deleteFile.length() != 0) {
                //기존 파일 삭제
                fileService.deleteFile(imgUploadLocation, deleteFile);
            }
            //새로운 파일 업로드
            newFileName = fileService.uploadFile(imgUploadLocation, originalFileName,
                    imgFile.getBytes());
            movieDTO.setImg(newFileName);
        }
        movieDTO.setId(read.getId());

        MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);

        movieRepository.save(movieEntity);
    }

    //삽입
    public void create(MovieDTO movieDTO, MultipartFile imgFile) throws Exception {
        String originalFileName = imgFile.getOriginalFilename();
        String newFileName = "";
        if (originalFileName != null) {
            newFileName = fileService.uploadFile(imgUploadLocation, originalFileName,
                    imgFile.getBytes());
        }
        movieDTO.setImg(newFileName);

        MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);

        movieRepository.save(movieEntity);
    }

    //개별조회
    public MovieDTO read(int id) throws Exception {
        Optional<MovieEntity> read = movieRepository.findById(id);
        MovieDTO movieDTO = modelMapper.map(read, MovieDTO.class);
        return movieDTO;
    }

    //전체조회(페이징처리)
    public Page<MovieDTO> list(Pageable page) throws Exception {
        int curPage = page.getPageNumber()-1;
        int pageLimit = 10;

        Pageable pageable = PageRequest.of(curPage, pageLimit,
                Sort.by(Sort.Direction.DESC,"modDate"));

        Page<MovieEntity> movieEntities = movieRepository.findAll(pageable);

        Page<MovieDTO> movieDTOS = movieEntities.map(data->MovieDTO.builder()
                .id(data.getId()) .movie(data.getMovie())
                .content(data.getContent()) .img(data.getImg())
                .modDate(data.getModDate()) .regDate(data.getRegDate())
                .build());

        return movieDTOS;
    }
}

