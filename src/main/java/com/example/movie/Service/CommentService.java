package com.example.movie.Service;

import com.example.movie.DTO.CommentDTO;
import com.example.movie.Entity.CommentEntity;
import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.CommentRepository;
import com.example.movie.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //댓글 삭제
    public void remove(int id) throws Exception {
        commentRepository.deleteById(id);
    }

    //댓글 수정
    public void modify(CommentDTO commentDTO) throws Exception {
        int id = commentDTO.getId();

        Optional<CommentEntity> read = commentRepository.findById(id);
        CommentEntity commentEntity = read.orElseThrow();

        //댓글에 해당하는 부모 글 정보 확인
        Optional<MovieEntity> data = movieRepository.findById(commentDTO.getMovieid());
        MovieEntity movieEntity = data.orElseThrow();

        CommentEntity update = modelMapper.map(commentDTO, CommentEntity.class);
        update.setId(commentEntity.getId());
        update.setMovieEntity(movieEntity);

        commentRepository.save(update);
    }

    //삽입
    public void create(int id, CommentDTO commentDTO) throws Exception {
        //연관관계 부모게시글 정보
        Optional<MovieEntity> data = movieRepository.findById(id);
        MovieEntity movieEntity = data.orElseThrow();

        CommentEntity commentEntity = modelMapper.map(commentDTO, CommentEntity.class);
        commentEntity.setMovieEntity(movieEntity);

        commentRepository.save(commentEntity);
    }

    //댓글은 개별조회가 없음, 페이지 정보 없음
    //전체조회
    public List<CommentDTO> list(int movieid) throws Exception {
        List<CommentEntity> commentEntities = commentRepository.findByMovieEntity(movieid);

        List<CommentDTO> commentDTOS = Arrays.asList(modelMapper.map(
                commentEntities, CommentDTO[].class));

        return commentDTOS;
    }
}
