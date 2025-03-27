package ru.job4j.media.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.Friend;
import ru.job4j.media.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    private User user = new User();

    @AfterEach
    void setUp() {
        friendRepository.deleteAll();
    }

    @AfterAll
    void deleteAll() {
        userRepository.deleteAll();
    }

    @BeforeAll
    void addUser() {
        user.setName("userName");
        user.setEmail("email@email.ru");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    public void whenSaveFriendThenFindById() {
        Friend friend = new Friend();
        friend.setExist(true);
        friend.setUser(user);
        friendRepository.save(friend);
        var foundFriend = friendRepository.findById(friend.getId());
        assertThat(foundFriend).isPresent();
        assertThat(foundFriend.get().getUser()).isEqualTo(user);
    }
}
