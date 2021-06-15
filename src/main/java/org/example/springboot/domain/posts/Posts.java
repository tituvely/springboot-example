package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter
// 기본 생성자 자동 추가. public Posts(){}와 같은 효과.
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타냄.
// 기본값로 클래스의 카멜케이으스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭함.
@Entity
public class Posts extends BaseTimeEntity {

    // 해당 테이블의 PK 필드를 나타냄.
    @Id
    // PK의 생성 규칙을 나타냄.타
    // GenerationType.IDENTITY을 추가하면 auto_increment가 됨.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼을 나타냄.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성함
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
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
