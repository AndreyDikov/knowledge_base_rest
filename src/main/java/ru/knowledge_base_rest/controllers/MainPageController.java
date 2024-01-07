package ru.knowledge_base_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/super-admin-page")
    public String getSuperAdminPage() {
        return "/super-admin-page";
    }

    @GetMapping("/super-admin-and-editor-page")
    public String getSuperAdminAndEditorPage() {
        return "/super-admin-and-editor-page";
    }
}
