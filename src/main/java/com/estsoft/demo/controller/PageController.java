package com.estsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafPage(Model model) {
        Person person = new Person();
        person.setId(1L);
        person.setName("길동홍");
        person.setAge(40);
        person.setHobbies(Arrays.asList("여행", "조깅"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage";   // html 페이지
    }
}
