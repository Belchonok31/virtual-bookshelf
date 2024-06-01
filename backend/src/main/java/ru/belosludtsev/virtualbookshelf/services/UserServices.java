package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.MeUserDetails;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices implements UserDetailsService {

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
    public User update(long id, User userUpdate){
        userUpdate.setId(id);
        Optional<User> optionalUser = userRepositories.findById(id);
        optionalUser.ifPresent(user -> userUpdate.setEmail(user.getEmail()));
        optionalUser.ifPresent(user -> userUpdate.setPassword(user.getPassword()));
        optionalUser.ifPresent(user -> userUpdate.setRole(user.getRole()));
        return userRepositories.save(userUpdate);
    }

    @Transactional
    public void delete(long id) {
        shelfServices.deleteAllShelfByClientId(id);
        userRepositories.deleteById(id);
    }

    @Override
    public MeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepositories.findByEmail(username);
        if (optionalUser.isPresent()) {
            return new MeUserDetails(optionalUser.get());
        }
        else throw new UsernameNotFoundException("User not found");
    }
}
