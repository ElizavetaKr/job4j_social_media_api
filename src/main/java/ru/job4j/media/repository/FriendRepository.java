package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.media.model.Friend;

public interface FriendRepository  extends ListCrudRepository<Friend, Integer> {
}
