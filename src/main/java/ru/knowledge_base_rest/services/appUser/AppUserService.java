package ru.knowledge_base_rest.services.appUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knowledge_base_rest.entities.user.AppUser;
import ru.knowledge_base_rest.repositories.appUser.AppUserRepository;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository userRepository;

    @Autowired
    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> showAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
