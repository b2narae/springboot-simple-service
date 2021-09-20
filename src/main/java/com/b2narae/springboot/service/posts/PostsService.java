package com.b2narae.springboot.service.posts;

import com.b2narae.springboot.domain.posts.Posts;
import com.b2narae.springboot.domain.posts.PostsRepository;
import com.b2narae.springboot.web.dto.PostsResponseDto;
import com.b2narae.springboot.web.dto.PostsSaveRequestDto;
import com.b2narae.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    // 이 부분에 @Autowired 대신 @RequiredArgsConstructor로 대체
    private final PostsRepository postsRepository;
    
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("There is no posts of 'id : " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("There is no post of 'id : " + id));
        return new PostsResponseDto(entity);
    }
}
