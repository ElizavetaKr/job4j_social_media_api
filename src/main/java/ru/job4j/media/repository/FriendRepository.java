package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Friend;

@Repository
public interface FriendRepository  extends ListCrudRepository<Friend, Integer> {
}
