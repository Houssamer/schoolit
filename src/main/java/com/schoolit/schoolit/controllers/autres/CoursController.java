package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.services.cours.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cours")
public class CoursController {
    private final CoursService coursService;

    @Autowired
    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }
}
