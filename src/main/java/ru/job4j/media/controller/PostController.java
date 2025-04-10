package ru.job4j.media.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.media.dto.UserPostDto;
import ru.job4j.media.model.Post;
import ru.job4j.media.service.PostService;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> save(@Valid @RequestBody Post post) {
        postService.save(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable
                                           @NotNull
                                           @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                           int postId) {
        if (postService.delete(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody Post post) {
        if (postService.update(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<UserPostDto>> getAllPostsUser(@RequestParam List<Integer> usersId) {
        List<UserPostDto> result = postService.getAllPostsUser(usersId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
