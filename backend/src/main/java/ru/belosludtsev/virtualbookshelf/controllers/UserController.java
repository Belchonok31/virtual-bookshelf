package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.services.AuthenticatedUserService;
import ru.belosludtsev.virtualbookshelf.services.UserServices;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping
    public List<User> index() {
        return userServices.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") long id) {
        return userServices.findOne(id);
    }

    @GetMapping("/context")
    public User getUserFromContext() {
        return authenticatedUserService.getAuthenticatedUser();
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userServices.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") long id, @RequestBody User userUpdate) {
        return userServices.update(id, userUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userServices.delete(id);
    }
}
