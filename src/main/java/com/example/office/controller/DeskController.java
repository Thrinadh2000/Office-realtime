package com.example.office.controller;

import com.example.office.model.Desk;
import com.example.office.service.DeskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desks")
public class DeskController {

    private final DeskService deskService;

    public DeskController(DeskService deskService) {

        this.deskService = deskService;
    }

    @GetMapping
    public List<Desk> getAllDesks() {

        return deskService.getAllDesks();
    }

    @GetMapping("/{id}")
    public Desk getDesk(@PathVariable String id) {

        return deskService.getDesk(id);
    }
}
