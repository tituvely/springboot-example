package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함.
@RunWith(SpringRunner.class)
// 여러 스프링 노테이션 중, Web(Spring MVC)어에 집중할 수 있는 어노테이션.
@WebMvcTest
public class HelloControllerTest {

    // 스프링이 관리하는 빈을 주입받음.
    @Autowired
    // 웹 API를 테스트할 때 사용함.
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함.
        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증. 여기선 상태를 검증.
                .andExpect(status().isOk())
                // 응답 본문의 내용을 검증.
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
