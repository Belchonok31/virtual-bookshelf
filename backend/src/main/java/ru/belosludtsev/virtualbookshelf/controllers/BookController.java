package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.services.BookServices;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookServices bookServices;

    private final StatisticsServices statisticsServices;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> books = bookServices.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/shelf/{shelfId}/book")
    public ResponseEntity<List<Book>> getAllBook(@PathVariable("shelfId") long shelfId) {
        List<Book> books = bookServices.findAll(shelfId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookServices.findOne(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/bookOriginal/{bookOriginalId}")
    public ResponseEntity<String> createBook(@PathVariable("bookOriginalId") long bookOriginalId,
                                             @RequestParam(name = "shelfId", required = true) long shelfId,
                                             @RequestBody Book book) {
        bookServices.save(shelfId, bookOriginalId, book);
        statisticsServices.updateAdditions(bookOriginalId);
//        return ResponseEntity.ok("BookImage created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id,
                                             @RequestBody Book bookUpdate) {
        if (bookServices.findOne(id) != null) {
            bookServices.update(id, bookUpdate);
            return ResponseEntity.ok("Book updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        if (bookServices.findOne(id) != null) {
            bookServices.delete(id);
            return ResponseEntity.ok("Book deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
