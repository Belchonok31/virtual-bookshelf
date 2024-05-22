package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.services.AuthenticatedUserService;
import ru.belosludtsev.virtualbookshelf.services.ShelfServices;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class ShelfController {

    private final ShelfServices shelfServices;

    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping("/all")
    public ResponseEntity<List<Shelf>> getAllShelf(){
        List<Shelf> shelves = shelfServices.findAll();
        return ResponseEntity.ok(shelves);
    }

    @GetMapping
    public ResponseEntity<List<Shelf>> getAllShelfByClientId() {
        User user = authenticatedUserService.getAuthenticatedUser();
        List<Shelf> shelves = shelfServices.findAll(user.getId());
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelfById() {
        User user = authenticatedUserService.getAuthenticatedUser();
        Shelf shelf = shelfServices.findOne(user.getId());
        if (shelf != null) {
            return ResponseEntity.ok(shelf);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createShelf(@RequestBody Shelf shelf) {
        User user = authenticatedUserService.getAuthenticatedUser();
        shelfServices.save(user.getId(), shelf);
//        return ResponseEntity.ok("Shelf created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateShelf(@PathVariable("id") long id, @RequestBody Shelf shelfUpdate) {
        if (shelfServices.findOne(id) != null) {
            shelfServices.update(id, shelfUpdate);
            return ResponseEntity.ok("Shelf updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable("id") long id) {
        if (shelfServices.findOne(id) != null) {
            shelfServices.delete(id);
            return ResponseEntity.ok("Shelf deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
