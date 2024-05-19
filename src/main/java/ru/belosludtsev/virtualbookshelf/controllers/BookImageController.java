package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.services.BookImageServices;
import ru.belosludtsev.virtualbookshelf.services.BookServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("book/{bookId}/bookImage")
public class BookImageController {

    private final BookImageServices bookImageServices;

    @GetMapping("/all")
    public ResponseEntity<List<BookImage>> getAllBookImage() {
        List<BookImage> bookImages = bookImageServices.findAll();
        return ResponseEntity.ok(bookImages);
    }

    @GetMapping
    public ResponseEntity<List<BookImage>> getAllBookImage(@PathVariable("bookId") long bookId) {
        List<BookImage> bookImages = bookImageServices.findAll(bookId);
        return ResponseEntity.ok(bookImages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookImage> getBookImageById(@PathVariable("id") long id) {
        BookImage bookImage = bookImageServices.findOne(id);
        if (bookImage != null) {
            return ResponseEntity.ok(bookImage);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createBookImage(@PathVariable("bookId") long bookId, @RequestBody BookImage bookImage) {
        bookImageServices.save(bookId, bookImage);
//        return ResponseEntity.ok("Shelf created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id, @RequestBody BookImage bookImageUpdate) {
        if (bookImageServices.findOne(id) != null) {
            bookImageServices.update(id, bookImageUpdate);
            return ResponseEntity.ok("Shelf updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookImage(@PathVariable("id") long id) {
        if (bookImageServices.findOne(id) != null) {
            bookImageServices.delete(id);
            return ResponseEntity.ok("Shelf deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
