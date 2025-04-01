package ru.job4j.media.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.File;
import ru.job4j.media.model.Post;
import ru.job4j.media.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;
    private File file = new File();
    private User user = new User();

    @AfterEach
    void setUp() {
        postRepository.deleteAll();
    }

    @AfterAll
    void deleteAll() {
        fileRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeAll
    void addFileAndUser() {
        file.setName("fileName");
        file.setPath("filePath");
        fileRepository.save(file);
        user.setName("userName");
        user.setEmail("email@email.ru");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    void whenSavePostThenFindById() {
        Post post = new Post();
        post.setName("postName");
        post.setFile(file);
        post.setUser(user);
        post.setCreated(LocalDateTime.now());
        postRepository.save(post);
        Optional<Post> foundPost = postRepository.findById(post.getId());
        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getName()).isEqualTo("postName");
    }

    @Test
    void whenFindAllThenReturnAllPosts() {
        Post post1 = new Post();
        post1.setName("postName1");
        post1.setFile(file);
        post1.setUser(user);
        post1.setCreated(LocalDateTime.now());
        Post post2 = new Post();
        post2.setName("postName2");
        post2.setFile(file);
        post2.setUser(user);
        post2.setCreated(LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(2);
        assertThat(posts).extracting(Post::getName).contains("postName1", "postName2");
    }
}
