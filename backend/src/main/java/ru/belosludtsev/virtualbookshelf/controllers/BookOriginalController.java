package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.services.BookOriginalServices;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequestMapping("/bookOriginal")
@RequiredArgsConstructor
public class BookOriginalController {

    private final BookOriginalServices bookOriginalServices;

    private final StatisticsServices statisticsServices;

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
    public BookOriginal createBookOriginal(@RequestBody BookOriginal bookOriginal) {
        BookOriginal bookOriginal1 = bookOriginalServices.save(bookOriginal);
        statisticsServices.save(bookOriginal);
        return bookOriginal1;
//        return ResponseEntity.ok("BookOriginal created successfully");
    }

    @PutMapping("/{id}")
    public BookOriginal updateBook(@PathVariable("id") long id,
                                             @RequestBody BookOriginal bookOriginalUpdate) {
        if (bookOriginalServices.findOne(id) != null) {
            return bookOriginalServices.update(id, bookOriginalUpdate);
        } else return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        if (bookOriginalServices.findOne(id) != null) {
            bookOriginalServices.delete(id);
            return ResponseEntity.ok("BookOriginal deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
