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
import ru.job4j.media.model.User;
import ru.job4j.media.service.UserService;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        userService.save(user);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable
                                        @NotNull
                                        @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                        int userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody User user) {
        if (userService.save(user) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable
                                           @NotNull
                                           @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                           int userId) {
        if (userService.findById(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
