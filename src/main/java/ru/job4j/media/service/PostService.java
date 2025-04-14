package ru.job4j.media.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.media.dto.UserPostDto;
import ru.job4j.media.mapstuct.UserPostMapper;
import ru.job4j.media.model.Post;
import ru.job4j.media.model.User;
import ru.job4j.media.repository.post.PostRepository;
import ru.job4j.media.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserPostMapper userPostMapper;

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
        return postRepository.findById(postId);
    }

    public List<UserPostDto> getAllPostsUser(List<Long> usersId) {
        List<UserPostDto> result = new ArrayList<>();
        for (Long i : usersId) {
            User user = userRepository.findById(i).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
            user.setPosts(postRepository.findPostsSubscribers(i));
            result.add(userPostMapper.getUserPostDtoFromEntity(user));
        }
        return result;
    }
}
