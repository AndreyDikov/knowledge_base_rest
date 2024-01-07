package ru.knowledge_base_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String getMainPage() {
        // todo добавить вию для главной страницы
        return "/index";
    }
}