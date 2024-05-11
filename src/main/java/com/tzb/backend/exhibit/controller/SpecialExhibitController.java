package com.tzb.backend.exhibit.controller;

import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.exhibit.service.SpecialExhibitService;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 */
@CrossOrigin
@RestController
@ResultWrapper
@RequestMapping("/api")
public class SpecialExhibitController {
    private final SpecialExhibitService service;

    public SpecialExhibitController(SpecialExhibitService service) {
        this.service = service;
    }

    @GetMapping("/specialExhibits")
    public Object getSpecialExhibits() {
        return service.getSpecialExhibits();
    }

    @GetMapping("/specialExhibit/{id}")
    public Object getSpecialExhibitById(@PathVariable int id) {
        return service.getSpecialExhibitById(id);
    }
}
