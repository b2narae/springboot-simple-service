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
        /*
        Please note that I use assertThat of assertj instead of Junit
        It's because
        1) assertj doesn't need additional library such as CoreMatchers
        2) autocomplete is more comfortable in assertj
         */
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
