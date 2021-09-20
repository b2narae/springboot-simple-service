package com.b2narae.springboot.service.posts;

import com.b2narae.springboot.domain.posts.PostsRepository;
import com.b2narae.springboot.web.dto.PostsSaveRequestDto;
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
}
