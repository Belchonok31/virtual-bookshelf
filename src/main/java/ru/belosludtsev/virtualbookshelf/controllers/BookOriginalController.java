package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.services.BookOriginalServices;

import java.util.List;

@RestController
@RequestMapping("/bookOriginal")
@RequiredArgsConstructor
public class BookOriginalController {

    private final BookOriginalServices bookOriginalServices;

    @GetMapping
    public ResponseEntity<List<BookOriginal>> getAll() {
        List<BookOriginal> booksOriginal = bookOriginalServices.findAll();
        return ResponseEntity.ok(booksOriginal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOriginal> getBookOriginalById(@PathVariable("id") long id) {
        BookOriginal bookOriginal = bookOriginalServices.findOne(id);
        if (bookOriginal != null) {
            return ResponseEntity.ok(bookOriginal);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createBookOriginal(@RequestBody BookOriginal bookOriginal) {
        bookOriginalServices.save(bookOriginal);
//        return ResponseEntity.ok("BookOriginal created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id,
                                             @RequestBody BookOriginal bookOriginalUpdate) {
        if (bookOriginalServices.findOne(id) != null) {
            bookOriginalServices.update(id, bookOriginalUpdate);
            return ResponseEntity.ok("BookOriginal updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        if (bookOriginalServices.findOne(id) != null) {
            bookOriginalServices.delete(id);
            return ResponseEntity.ok("BookOriginal deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
