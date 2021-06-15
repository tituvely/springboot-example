package org.example.springboot.web;

import org.example.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다
@RestController
public class HelloController {

    // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어준다
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // name과 amount는 API를 호출하는 곳에서 넘겨준 값들
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
