package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belosludtsev.virtualbookshelf.entities.Statistics;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bookOriginal/{bookOriginalId}/statistics")
public class StatisticsController {

    private final StatisticsServices statisticsServices;

    @GetMapping("/all")
    public ResponseEntity<List<Statistics>> getAllStatistics() {
        List<Statistics> statistics = statisticsServices.findAll();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping
    public ResponseEntity<Statistics> getStatisticsByBookOriginalId(@PathVariable("bookOriginalId") long bookOriginalId) {
        Statistics statistics = statisticsServices.findOneByBookOriginalId(bookOriginalId);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistics> getStatisticsById(@PathVariable("id") long id) {
        Statistics statistics = statisticsServices.findOne(id);
        if (statistics != null) {
            return ResponseEntity.ok(statistics);
        } else return ResponseEntity.notFound().build();
    }
}
