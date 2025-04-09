package ru.job4j.media.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.media.model.Post;
import ru.job4j.media.repository.post.PostRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public boolean update(Post post) {
        return postRepository.updateNameAndDesc(post.getName(), post.getDescription(), post.getId());
    }

    public boolean delete(int postId) {
        return postRepository.delete(postId);
    }

    public Optional<Post> findById(int postId) {
        return  postRepository.findById(postId);
    }
}
