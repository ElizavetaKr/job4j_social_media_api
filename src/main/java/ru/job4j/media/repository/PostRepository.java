package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.media.model.Post;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
