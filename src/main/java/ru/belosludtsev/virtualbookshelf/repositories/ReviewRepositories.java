package ru.belosludtsev.virtualbookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.virtualbookshelf.entities.Review;

@Repository
public interface ReviewRepositories extends JpaRepository<Review, Long> {
}
