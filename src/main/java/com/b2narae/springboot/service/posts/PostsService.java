package com.b2narae.springboot.service.posts;

import com.b2narae.springboot.domain.posts.Posts;
import com.b2narae.springboot.domain.posts.PostsRepository;
import com.b2narae.springboot.web.dto.PostsListResponseDto;
import com.b2narae.springboot.web.dto.PostsResponseDto;
import com.b2narae.springboot.web.dto.PostsSaveRequestDto;
import com.b2narae.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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
    // update에 DB에 쿼리를 날리는 부분이 없는 이유
    // JPA의 영속성 컨텍스트 (엔티티를 영구 저장하는 환경) 때문
    // JPA의 핵심 내용은 Entity가 영속성 컨텍스트에 포함되어 있느냐, 아니냐로 갈림

    // JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 DB에서 데이터를 가져오면,
    // 이 데이터는 영속성 컨텍스트가 유지된 상태임

    // 이 상태에서 해당 데이터의 값을 변경하면, 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함
    // Entity의 객체 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없음 (Dirty Checking 개념)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("There is no post of 'id : " + id));
        return new PostsResponseDto(entity);
    }

    // Transaction 범위는 유지하되 조회 기능만 남겨두어, 조회 속도가 개선됨
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
