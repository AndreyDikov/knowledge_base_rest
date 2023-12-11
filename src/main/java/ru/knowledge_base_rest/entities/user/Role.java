package ru.knowledge_base_rest.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMINISTRATOR("Администратор"),
    EDITOR("Редактор"),
    REGISTERED_USER("Зарегистрированный пользователь"),
    UNREGISTERED_USER("Незарегистрированный пользователь");

    private final String name;
}
