package com.example.movie;

import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void insert() {
        for (int i=1; i<=5; i++) {
            MovieEntity movie = MovieEntity.builder()
                    .movie("테스트" + i)
                    .content("내용" + i)
                    .build();

            movieRepository.save(movie);
        }
    }
}
