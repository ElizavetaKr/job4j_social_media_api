package ru.job4j.media.repository.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
    @Query("""
            select user from User as user
                        where user.email = ?1 and user.password = ?2
                    """)
    Optional<User> findByLoginAndPassword(String login, String password);
}
