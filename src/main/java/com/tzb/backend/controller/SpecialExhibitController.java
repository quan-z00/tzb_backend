package com.tzb.backend.controller;

import com.tzb.backend.annotation.ResultWrapper;
import com.tzb.backend.dto.projection.ArtifactCardProjection;
import com.tzb.backend.service.SpecialExhibitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
