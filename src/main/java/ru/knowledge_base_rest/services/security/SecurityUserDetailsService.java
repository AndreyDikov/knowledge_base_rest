package ru.knowledge_base_rest.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.knowledge_base_rest.entities.security.Role;
import ru.knowledge_base_rest.entities.security.SecurityUser;
import ru.knowledge_base_rest.repositories.security.SecurityUserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис, отвечающий за загрузку пользователей
 * при входе и за сохранение при регистрации
 */
@Service
public class  SecurityUserDetailsService implements UserDetailsService {

    private final SecurityUserRepository securityUserRepository;

    @Autowired
    public SecurityUserDetailsService(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }

    /**
     * <pre>
     * При входе загружает пользователя по его имени из БД.
     * Меппит его в Spring Security формат пользователя.
     * </pre>
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = getSecurityUser(username);
        return new org.springframework.security.core.userdetails
                .User(securityUser.getUsername(), securityUser.getPassword(),
                mapRolesToAuthorities(securityUser.getRoles()));
    }

    public SecurityUser getSecurityUser(String username) {
        Optional<SecurityUser> myUser = securityUserRepository.findByUsername(username);
        if (myUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        SecurityUser securityUser = myUser.get();
        return securityUser;
    }

    /**
     * <pre>
     *     Мэппит роли пользователя из БД в формат GrantedAuthority,
     *     т.к. User из Spring Security работает в таком формате
     * </pre>
     *
     * @param roles
     * @return List<? extends GrantedAuthority>
     */
    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toList());
    }

    /**
     * Добавление пользователя в БД при регистрации
     * @param securityUser
     * @throws Exception
     */
    public void addUser(SecurityUser securityUser) throws Exception {
        Optional<SecurityUser> userFromDb = securityUserRepository
                .findByUsername(securityUser.getUsername());
        if (userFromDb.isPresent()) {
            throw new Exception("user exists");
        }
        securityUser.setRoles(Collections.singleton(Role.REGISTERED_USER));
        securityUser.setActive(true);
        // todo зашифровать, 43 минута
        securityUserRepository.save(securityUser);
    }
}
