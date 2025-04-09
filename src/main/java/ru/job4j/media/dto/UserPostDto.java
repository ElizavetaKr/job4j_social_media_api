package ru.job4j.media.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.media.model.Post;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {
    private int userId;
    private String name;
    private List<Post> posts = new ArrayList<>();
}
