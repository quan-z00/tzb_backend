package com.tzb.backend.web.controller;

import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.web.service.SpecialExhibitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 */
@CrossOrigin
@RestController
@ResultWrapper
@RequiredArgsConstructor
@RequestMapping("/api")
public class SpecialExhibitController {
    private final SpecialExhibitService service;

    @GetMapping("/specialExhibits")
    public Object getSpecialExhibits() {
        return service.getSpecialExhibits();
    }

    @GetMapping("/specialExhibit/{id}")
    public Object getSpecialExhibitById(@PathVariable int id) {
        return service.getSpecialExhibitById(id);
    }
}
