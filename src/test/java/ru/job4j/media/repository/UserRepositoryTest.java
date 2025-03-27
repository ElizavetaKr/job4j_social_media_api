package ru.job4j.media.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveUserThenFindById() {
        User user = new User();
        user.setName("UserName");
        user.setEmail("user@example.ru");
        user.setPassword("password");
        userRepository.save(user);
        var foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("UserName");
    }

    @Test
    public void whenFindAllThenReturnAllUsers() {
        User user1 = new User();
        user1.setName("UserName");
        user1.setEmail("user@example.ru");
        user1.setPassword("password");
        User user2 = new User();
        user2.setName("User2Name");
        user2.setEmail("user2@example.ru");
        user2.setPassword("password");
        userRepository.save(user1);
        userRepository.save(user2);
        var users = userRepository.findAll();
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getName).contains("UserName", "User2Name");
    }
}
