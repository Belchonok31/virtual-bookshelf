package ru.belosludtsev.virtualbookshelf.controllers.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.services.ShelfServices;

import java.util.List;

@RestController
@RequestMapping("/client/{clientId}/shelf")
@RequiredArgsConstructor
public class ClientShelfController {

    private final ShelfServices shelfServices;

    @GetMapping
    public ResponseEntity<List<Shelf>> getAllShelf(@PathVariable("clientId") long clientId) {
        List<Shelf> shelves = shelfServices.findAll(clientId);
        return ResponseEntity.ok(shelves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelfById(@PathVariable("clientId") long clientId, @PathVariable("id") long id) {
        Shelf shelf = shelfServices.findOne(clientId, id);
        if (shelf != null) {
            return ResponseEntity.ok(shelf);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createShelf(@PathVariable("clientId") long clientId, @RequestBody Shelf shelf) {
        shelfServices.save(clientId, shelf);
//        return ResponseEntity.ok("Shelf created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateShelf(@PathVariable("clientId") long clientId,
                                              @PathVariable("id") long id, @RequestBody Shelf shelfUpdate) {
        if (shelfServices.findOne(id) != null) {
            shelfServices.update(clientId, id, shelfUpdate);
            return ResponseEntity.ok("Shelf updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable("clientId") long clientId, @PathVariable("id") long id) {
        if (shelfServices.findOne(id) != null) {
            shelfServices.delete(clientId, id);
            return ResponseEntity.ok("Shelf deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
