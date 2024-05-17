package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.repositories.ShelfRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelfServices {

    private final ShelfRepositories shelfRepositories;

    public List<Shelf> findAll(long clientId) {
        return shelfRepositories.findAll();
    }

    public Shelf findOne(long clientId, long id) {
        return shelfRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(long clientId, Shelf shelf) {
        shelfRepositories.save(shelf);
    }

    @Transactional
    public void update(long clientId, long id, Shelf shelfUpdate) {
        shelfUpdate.setId(id);
        shelfRepositories.save(shelfUpdate);
    }

    @Transactional
    public void delete(long clientId, long id) {
        shelfRepositories.deleteById(id);
    }

    //admin methods------------------------------------------
    public List<Shelf> findAll() {
        return shelfRepositories.findAll();
    }

    public Shelf findOne(long id) {
        return shelfRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(Shelf shelf) {
        shelfRepositories.save(shelf);
    }

    @Transactional
    public void update(long id, Shelf shelfUpdate) {
        shelfUpdate.setId(id);
        shelfRepositories.save(shelfUpdate);
    }

    @Transactional
    public void delete(long id) {
        shelfRepositories.deleteById(id);
    }
}
