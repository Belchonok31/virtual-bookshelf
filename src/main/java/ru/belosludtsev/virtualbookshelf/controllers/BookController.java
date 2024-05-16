package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.services.BookServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookServices bookServices;

    @GetMapping
    public List<Book> index() {
        return bookServices.findAll();
    }

    @GetMapping("/{id}")
    public Book show(@PathVariable("id") long id){
        return bookServices.findOne(id);
    }

    @PostMapping
    public void save(Book book){
        bookServices.save(book);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id,  Book bookUpdate){
        bookServices.update(id, bookUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        bookServices.delete(id);
    }
}
