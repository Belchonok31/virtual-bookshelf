package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Statistics;
import ru.belosludtsev.virtualbookshelf.repositories.StatisticsRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServices {

    private final StatisticsRepositories statisticsRepositories;

    public List<Statistics> findAll(){
        return statisticsRepositories.findAll();
    }

    public Statistics findOne(long id){
        return statisticsRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(Statistics statistics){
        statisticsRepositories.save(statistics);
    }

    @Transactional
    public void update(long id, Statistics statisticsUpdate){
        statisticsUpdate.setId(id);
        statisticsRepositories.save(statisticsUpdate);
    }

    @Transactional
    public void delete(long id){
        statisticsRepositories.deleteById(id);
    }
}
