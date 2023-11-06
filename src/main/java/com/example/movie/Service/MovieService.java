package com.example.movie.Service;

import com.example.movie.DTO.MovieDTO;
import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //삭제
    public void remove(int id) throws Exception {
        movieRepository.deleteById(id);
    }

    //수정
    public void modify(MovieDTO movieDTO) throws Exception {
        int id = movieDTO.getId();
        Optional<MovieEntity> read = movieRepository.findById(id);
        MovieEntity movieEntity = read.orElseThrow();

        MovieEntity update = modelMapper.map(movieDTO, MovieEntity.class);
        update.setId(movieEntity.getId());

        movieRepository.save(update);
    }

    //삽입
    public void create(MovieDTO movieDTO) throws Exception {
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
                Sort.by(Sort.Direction.DESC,"regDate"));

        Page<MovieEntity> movieEntities = movieRepository.findAll(pageable);

        Page<MovieDTO> movieDTOS = movieEntities.map(data->MovieDTO.builder()
                .id(data.getId()) .movie(data.getMovie())
                .content(data.getContent()) .img(data.getImg())
                .modDate(data.getModDate())
                .build());

        return movieDTOS;
    }
}

