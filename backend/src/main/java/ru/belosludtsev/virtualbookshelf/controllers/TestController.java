package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.services.ShelfServices;

import java.util.List;

// todo delete this class

@RestController
@RequiredArgsConstructor
@RequestMapping("/all")
public class TestController {

    private final ShelfServices shelfServices;

    @GetMapping
    public ResponseEntity<List<Shelf>> getAllShelf() {
        List<Shelf> shelves = shelfServices.findAll();
        return ResponseEntity.ok(shelves);
    }
}
