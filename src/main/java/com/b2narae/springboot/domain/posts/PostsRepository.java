package com.b2narae.springboot.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJpa 에서 제공하지 않는 메소드는 쿼리로 작성 가능
    @Query("SELECT p FROM posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

