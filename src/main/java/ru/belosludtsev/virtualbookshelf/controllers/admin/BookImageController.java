package ru.belosludtsev.virtualbookshelf.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.services.BookImageServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookImage")
public class BookImageController {

    private final BookImageServices bookImageServices;

    @GetMapping
    public List<BookImage> index() {
        return bookImageServices.findAll();
    }

    @GetMapping("/{id}")
    public BookImage show(@PathVariable("id") long id) {
        return bookImageServices.findOne(id);
    }

    @PostMapping
    public void save(@RequestBody BookImage bookImage) {
        bookImageServices.save(bookImage);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody BookImage bookImageUpdate) {
        bookImageServices.update(id, bookImageUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        bookImageServices.delete(id);
    }
}
