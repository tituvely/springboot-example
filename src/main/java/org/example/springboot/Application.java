package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 활성화
@EnableJpaAuditing
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에, 이 클래스는 항상 프로젝트의 최상단에 위치해야 함
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS를 실행 -> 톰캣을 설치할 필요가 없이, 스프링 부트로 만들어진 JAR파일을 실행하면 된다
        // WHY? 내장 WAS를 사용하는 이유? -> 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문
        // 외장 WAS를 쓴다면 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야 한다
        // HOW? 톰캣 없이 어떻게 배포하고 서비스를 할 수 있는가? ->
        SpringApplication.run(Application.class, args);
    }
}
