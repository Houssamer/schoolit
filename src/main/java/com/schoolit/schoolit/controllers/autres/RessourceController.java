package com.schoolit.schoolit.controllers.autres;

import com.schoolit.schoolit.services.ressource.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ressource")
public class RessourceController {
    private final RessourceService ressourceService;

    @Autowired
    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }
}
