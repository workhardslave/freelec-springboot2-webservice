/* 1. @Runwith(SpringRunnerclass)
 * 테스트를 진행할 때 junit에 내장된 실행자 외에 다른 실행자를 실행한다.
 * 이 코드에서는 SpringRunnerClass 라는 스프링 실행자를 사용
 * 스프링 부트 테스트와 junit 사이에 연결자 역할을 한다.
 *
 * 2. @WebMvcTest
 * 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
 * 선언시 @Controller, @ControllerAdvice 등을 사용할 수 있다
 * JPA 기능이 작동하지 않음 (외부 연동과 관련된 부분만 활성화)
 *
 * 3. @Autowired
 * 스프링이 관리하는 빈(Bean)을 주입 받는다.
 *
 * 4. private MockMvc mvc
 * 웹 API를 테스트할 때 사용
 * 스프링 MVC 테스트의 시작점
 * HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
 *
 * 5. mvc.perform(get("/hello"))
 * MockMvc를 통해 /hello 주소로 HTTP GET 요청
 * 체이닝이 지원되어 여러 검증 기능을 이어서 선언할 수 있다.
 *
 * 6. .andExpect(status().isOk())
 * mvc.perform의 결과를 검증
 * HTTP Header의 Status를 검증 ex) 200, 404, 500 등
 * 성공인지 확인하므로 200을 검증
 *
 * 7. .andExpect(content().string(hello))
 * mvc.perform의 결과를 검증
 * 응답 본문의 내용을 검증
 * Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
 *
 * 8. param
 * API 테스트할 때 사용될 요청 파라미터를 설정
 * 값은 String만 허용한다. (숫자, 날짜 등의 데이터 등록 시 문자열로 변환)
 *
 * 9. jsonPath
 * JSON 응답값을 필드별로 검증할 수 있는 메소드
 * $를 기준으로 필드명을 명시한다.
 */
package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

    @Test
    public void helloDto_가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
