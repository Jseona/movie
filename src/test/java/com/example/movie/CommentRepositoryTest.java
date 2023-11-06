package com.example.movie;

import com.example.movie.Entity.CommentEntity;
import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void insert() {
        for (int i=1; i<=25; i++) {
            MovieEntity movieEntity = MovieEntity.builder()
                    .id(i%5+1)
                    .build();
            CommentEntity commentEntity = CommentEntity.builder()
                    .comment("댓글" + i)
                    .grade(i%5+1)
                    .movieEntity(movieEntity)
                    .build();

            commentRepository.save(commentEntity);
        }
    }

    /*@Test
    public void findByMovieEntityTest() {
        int movieId = 3;

        List<CommentEntity> commentEntities = commentRepository.findByMovieEntity(movieId);
        System.out.println(commentEntities.toString());
    }*/
}
