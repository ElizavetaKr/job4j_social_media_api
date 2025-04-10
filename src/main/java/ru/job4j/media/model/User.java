package ru.job4j.media.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String name;
    @EqualsAndHashCode.Include
    @NotBlank(message = "email не может быть пустым")
    private String email;
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "targetUser")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "targetUser")
    private List<Subscriber> subscribers = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
