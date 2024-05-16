package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.services.BookServices;
import ru.belosludtsev.virtualbookshelf.services.UserServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    @GetMapping
    public List<User> index() {
        return userServices.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") long id) {
        return userServices.findOne(id);
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userServices.save(user);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody User userUpdate) {
        userServices.update(id, userUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userServices.delete(id);
    }
}
