package ru.job4j.media.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @EqualsAndHashCode.Include
    @NotBlank(message = "Оглавления для поста не может быть пустым")
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime created;
}
