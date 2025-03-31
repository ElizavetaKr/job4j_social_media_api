package ru.job4j.media.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Post;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer> {

    List<Post> findAll();

    List<Post> findByCreated(Date created);

    Page<Post> findByOrderByCreated(Pageable pageable);
}
