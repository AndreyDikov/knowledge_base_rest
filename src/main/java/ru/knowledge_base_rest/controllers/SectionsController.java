package ru.knowledge_base_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectionsController {

    @GetMapping("/sections")
    public String getKnowledgeBaseSections() {
        return "sections";
    }
}
