package ru.job4j.media.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.media.model.Post;

import java.util.Date;
import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {

    List<Post> findAll();

    List<Post> findByCreated(Date created);

    Page<Post> findByOrderByCreated(Pageable pageable);
}
