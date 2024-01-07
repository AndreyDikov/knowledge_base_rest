package ru.knowledge_base_rest.entities.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Типы ролей пользователей
 */
@Getter
@AllArgsConstructor
public enum Role {

    ADMINISTRATOR("Администратор"),
    EDITOR("Редактор"),
    REGISTERED_USER("Зарегистрированный пользователь");

    private final String name;
}
