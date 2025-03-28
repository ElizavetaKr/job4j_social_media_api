package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Post;

@Repository
public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
