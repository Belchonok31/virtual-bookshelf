package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.repositories.ShelfRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.UserRepositories;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepositories userRepositories;

    private final ShelfServices shelfServices;

    public List<User> findAll(){
        return userRepositories.findAll();
    }

    public User findOne(long id){
        return userRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(User user){
        userRepositories.save(user);
    }

    @Transactional
    public void update(long id, User userUpdate){
        userUpdate.setId(id);
        userRepositories.save(userUpdate);
    }

    @Transactional
    public void delete(long id) {
        shelfServices.deleteAllShelfByClientId(id);
        userRepositories.deleteById(id);
    }
}
