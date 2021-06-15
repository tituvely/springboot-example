package org.example.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함.
@RunWith(SpringRunner.class)
// 별다른 설정 없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행 해줌.
@SpringBootTest
public class PostsRepositoryTest {

    // 스프링이 관리하는 빈을 주입받음.
    @Autowired
    PostsRepository postsRepository;

    // Junit에서 단위 테스트가 끝날때마다 수행되는 메소드를 지정
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막가 위해 사용함
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있음
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update 쿼리를 실행함.
        // id가 있다면 update가, 없다면 insert 쿼리가 실행됨.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("titu@gmail.com")
                .build());

        // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate()+", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
