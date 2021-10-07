package com.b2narae.springboot.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// (과거 Dao) JpaRepository로 부르며, 인터페이스로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa 에서 제공하지 않는 메소드는 쿼리로 작성 가능
    // 여기서 Posts를 posts로 적기만해도 에러 발생
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}

