package ru.job4j.media.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.Subscriber;
import ru.job4j.media.model.User;
import ru.job4j.media.repository.subscriber.SubscriberRepository;
import ru.job4j.media.repository.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SubscriberRepositoryTest {
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private UserRepository userRepository;

    private User requestUser = new User();
    private User targetUser = new User();

    @AfterEach
    void setUp() {
        subscriberRepository.deleteAll();
    }

    @AfterAll
    void deleteAll() {
        userRepository.deleteAll();
    }

    @BeforeAll
    void addUser() {
        requestUser.setName("requestUserName");
        requestUser.setEmail("email1@email.ru");
        requestUser.setPassword("password");
        userRepository.save(requestUser);
        targetUser.setName("targetUserName");
        targetUser.setEmail("email2@email.ru");
        targetUser.setPassword("password");
        userRepository.save(targetUser);
    }

    @Test
    public void whenSaveSubscriberThenFindById() {
        Subscriber subscriber = new Subscriber();
        subscriber.setTargetUser(targetUser);
        subscriber.setRequestUser(requestUser);
        subscriberRepository.save(subscriber);
        var foundSub = subscriberRepository.findById(subscriber.getId());
        assertThat(foundSub).isPresent();
        assertThat(foundSub.get()).isEqualTo(subscriber);
    }
}
