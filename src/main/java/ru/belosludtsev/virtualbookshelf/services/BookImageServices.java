package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.repositories.BookImageRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookImageServices {

    private final BookImageRepositories bookImageRepositories;

    public List<BookImage> findAll(){
        return bookImageRepositories.findAll();
    }

    public BookImage findOne(long id){
        return bookImageRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(BookImage bookImage){
        bookImageRepositories.save(bookImage);
    }

    @Transactional
    public void update(long id, BookImage bookImageUpdate){
        bookImageUpdate.setId(id);
        bookImageRepositories.save(bookImageUpdate);
    }

    @Transactional
    public void delete(long id){
        bookImageRepositories.deleteById(id);
    }
}
