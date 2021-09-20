package com.b2narae.springboot.web.dto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_feature_test() {
        // given
        String name = "test";
        int amount = 1000;

        // when - lombok 생성자 테스트
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then - getter setter 테스트
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
