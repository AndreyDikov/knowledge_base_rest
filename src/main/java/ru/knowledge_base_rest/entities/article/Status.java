package ru.knowledge_base_rest.entities.article;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    NEW("Новая"),
    TO_BE_AGREED("На согласовании"),
    IN_EDITING("В редактировании"),
    ACTIVE("Активна"),
    CHANGED("Изменена"),
    DELETED("Удалена");

    private final String name;
}
