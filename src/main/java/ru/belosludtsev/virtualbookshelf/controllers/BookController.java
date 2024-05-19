package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.services.BookServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf/{shelfId}/book")
public class BookController {

    private final BookServices bookServices;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookServices.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(@PathVariable("shelfId") long shelfId) {
        List<Book> books = bookServices.findAll(shelfId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookServices.findOne(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createBook(@PathVariable("shelfId") long shelfId, @RequestBody Book book) {
        bookServices.save(shelfId, book);
//        return ResponseEntity.ok("Shelf created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id, @RequestBody Book bookUpdate) {
        if (bookServices.findOne(id) != null) {
            bookServices.update(id, bookUpdate);
            return ResponseEntity.ok("Shelf updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        if (bookServices.findOne(id) != null) {
            bookServices.delete(id);
            return ResponseEntity.ok("Shelf deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
