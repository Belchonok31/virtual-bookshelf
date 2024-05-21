package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.services.BookImageServices;
import ru.belosludtsev.virtualbookshelf.services.BookServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getBookImageById(@PathVariable("id") long id) throws IOException {
        BookImage bookImage = bookImageServices.findOne(id);
        if (bookImage != null) {
            Path filePath = Paths.get(bookImage.getUrl());
            byte[] imageBytes = Files.readAllBytes(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(getMediaTypeFromFileName(bookImage.getName()));
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }
    private MediaType getMediaTypeFromFileName(String fileName) throws IOException {
        String contentType = "application/octet-stream";
        contentType = Files.probeContentType(Paths.get(fileName));
        return MediaType.parseMediaType(contentType);
    }

    @PostMapping
    public ResponseEntity<String> createBookImage(@PathVariable("bookId") long bookId, @RequestPart MultipartFile file) throws IOException {
        bookImageServices.save(bookId, file);
//        return ResponseEntity.ok("BookImage created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") long id, @RequestPart MultipartFile fileUpdate) throws IOException {
        if (bookImageServices.findOne(id) != null) {
            bookImageServices.update(id, fileUpdate);
            return ResponseEntity.ok("BookImage updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookImage(@PathVariable("id") long id) {
        if (bookImageServices.findOne(id) != null) {
            bookImageServices.delete(id);
            return ResponseEntity.ok("BookImage deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
