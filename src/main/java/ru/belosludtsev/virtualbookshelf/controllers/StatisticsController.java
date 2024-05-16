package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Statistics;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsServices statisticsServices;

    @GetMapping
    public List<Statistics> index() {
        return statisticsServices.findAll();
    }

    @GetMapping("/{id}")
    public Statistics show(@PathVariable("id") long id) {
        return statisticsServices.findOne(id);
    }

    @PostMapping
    public void save(@RequestBody Statistics statistics) {
        statisticsServices.save(statistics);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Statistics statisticsUpdate) {
        statisticsServices.update(id, statisticsUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        statisticsServices.delete(id);
    }
}
