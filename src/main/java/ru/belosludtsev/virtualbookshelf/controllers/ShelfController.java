package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.services.ShelfServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class ShelfController {

    private final ShelfServices shelfServices;

    @GetMapping
    public List<Shelf> index() {
        return shelfServices.findAll();
    }

    @GetMapping("/{id}")
    public Shelf show(@PathVariable("id") long id){
        return shelfServices.findOne(id);
    }

    @PostMapping
    public void save(Shelf shelf){
        shelfServices.save(shelf);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id,  Shelf shelfUpdate){
        shelfServices.update(id, shelfUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        shelfServices.delete(id);
    }
}
