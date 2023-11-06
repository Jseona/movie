package com.example.movie.Repository;

import com.example.movie.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository
        extends JpaRepository<CommentEntity, Integer> {
    @Query(value = "SELECT * FROM comment WHERE movieid = :movieid",
    nativeQuery = true)
    List<CommentEntity>findByMovieEntity(Integer movieid);
}
