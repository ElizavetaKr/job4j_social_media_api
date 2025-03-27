package ru.job4j.media.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.media.model.Subscriber;
import ru.job4j.media.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SubscriberRepositoryTest {
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private UserRepository userRepository;

    private User user = new User();

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
        user.setName("userName");
        user.setEmail("email@email.ru");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    public void whenSaveSubscriberThenFindById() {
        Subscriber subscriber = new Subscriber();
        subscriber.setExist(true);
        subscriber.setUser(user);
        subscriberRepository.save(subscriber);
        var foundSub = subscriberRepository.findById(subscriber.getId());
        assertThat(foundSub).isPresent();
        assertThat(foundSub.get().getUser()).isEqualTo(user);
    }
}
