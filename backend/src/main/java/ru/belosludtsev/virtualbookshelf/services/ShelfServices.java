package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.repositories.ShelfRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelfServices {

    private final ShelfRepositories shelfRepositories;

    private final UserRepositories userRepositories;

    private final BookServices bookServices;

    public List<Shelf> findAll() {
        return shelfRepositories.findAll();
    }

    public List<Shelf> findAll(long clientId) {
        return shelfRepositories.findAll()
                .stream().filter(shelf -> shelf.getUser().getId() == clientId)
                .collect(Collectors.toList());
    }

    public Shelf findOne(long id) {
        return shelfRepositories.findById(id).orElse(null);
    }

    @Transactional
    public Shelf save(long clientId, Shelf shelf) {
        // todo add check valid clientId
        Optional<User> userOptional = userRepositories.findById(clientId);
        userOptional.ifPresent(shelf::setUser);
        return shelfRepositories.save(shelf);
    }

    @Transactional
    public Shelf update(long id, long clientId, Shelf shelfUpdate) {
        shelfUpdate.setId(id);
        Optional<User> userOptional = userRepositories.findById(clientId);
        userOptional.ifPresent(shelfUpdate::setUser);
        return shelfRepositories.save(shelfUpdate);
    }

    @Transactional
    public void delete(long id) {
        bookServices.deleteAllBookByShelfId(id);
        shelfRepositories.deleteById(id);
    }

    @Transactional
    public void deleteAllShelfByClientId(long clientId) {
        shelfRepositories.findAll().stream()
                .filter(shelf -> shelf.getUser().getId() == clientId)
                .map(Shelf::getId)
                .forEach(this::delete);
    }
}
