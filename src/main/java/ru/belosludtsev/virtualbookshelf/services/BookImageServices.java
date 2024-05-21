package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.repositories.BookImageRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookImageServices {

    private final BookImageRepositories bookImageRepositories;

    private final BookRepositories bookRepositories;

    @Value("${upload.directory}")
    private String UPLOAD_DIRECTORY;

    public List<BookImage> findAll() {
        return bookImageRepositories.findAll();
    }

    public List<BookImage> findAll(long bookId) {
        return bookImageRepositories.findAll().stream()
                .filter(bookImage -> bookImage.getBook().getId() == bookId)
                .collect(Collectors.toList());
    }

    public BookImage findOne(long id) {
        return bookImageRepositories.findById(id).orElse(null);
    }

    public BookImage findOneByBookId(long bookId) {
        return bookImageRepositories.findAll().stream()
                .filter(bookImage -> bookImage.getBook().getId() == bookId)
                .findFirst().orElse(null);
    }

    @Transactional
    public void save(long bookId, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
        Files.copy(file.getInputStream(), filePath);

        var bookImage = new BookImage();
        bookImage.setName(fileName);
        bookImage.setUrl(filePath.toString());

        // todo add check valid bookId
        Optional<Book> optionalBook = bookRepositories.findById(bookId);
        optionalBook.ifPresent(bookImage::setBook);
        bookImageRepositories.save(bookImage);
    }

    @Transactional
    public void update(long id, MultipartFile updateFile) throws IOException {
        Optional<BookImage> optionalBookImage = bookImageRepositories.findById(id);
        if (optionalBookImage.isPresent()) {
            var bookImage = optionalBookImage.get();
            Path oldFilePath = Paths.get(UPLOAD_DIRECTORY, bookImage.getName());
            Files.deleteIfExists(oldFilePath);

            String fileName = updateFile.getOriginalFilename();
            Path newFilePath = Paths.get(UPLOAD_DIRECTORY, fileName);
            Files.copy(updateFile.getInputStream(), newFilePath);

            bookImage.setName(fileName);
            bookImage.setUrl(newFilePath.toString());
            bookImage.setBook(optionalBookImage.get().getBook());
            bookImageRepositories.save(bookImage);
        } else {
            throw new RuntimeException("Image not found");
        }
    }

    @Transactional
    public void delete(long id){
        BookImage bookImage = bookImageRepositories.findById(id).get();
        Path filePath = Paths.get(bookImage.getUrl());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookImageRepositories.deleteById(id);
    }

    @Transactional
    public void deleteAllBookImageByBookId(long bookId){
        bookImageRepositories.findAll().stream()
                .filter(bookImage -> bookImage.getBook().getId() == bookId)
                .map(BookImage::getId)
                .forEach(this::delete);
    }
}
