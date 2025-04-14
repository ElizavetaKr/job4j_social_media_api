package ru.job4j.media.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.media.model.User;
import ru.job4j.media.repository.user.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    public void delete(long userId) {
        userRepository.deleteById(userId);
    }
}
