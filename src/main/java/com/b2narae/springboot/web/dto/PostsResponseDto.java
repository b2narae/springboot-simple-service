package com.b2narae.springboot.web.dto;

import com.b2narae.springboot.domain.posts.Posts;
import lombok.Getter;

// Entity를 직접 사용하지 않고 Dto를 둔다.
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
