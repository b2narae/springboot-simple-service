package com.b2narae.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity // Table과 링크될 클래스를 표현, BaseTimeEntity = base_time_entity
public class Posts extends BaseTimeEntity {

    @Id // PK Field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SpringBoot 2.0 vs 1.5
    private Long id;

    @Column(length = 500, nullable = false) // 변경이 필요한 경우 Column을 줌
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
