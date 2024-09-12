package com.example.project2.controller;

import com.example.project2.model.DirectionModel;
import com.example.project2.service.DirectionService;
import com.example.project2.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/direction")
public class DirectionController {

    private final DirectionService directionService;
    private final GroupService groupService;

    public DirectionController(DirectionService directionService, GroupService groupService) {
        this.directionService = directionService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getDirectionAll(Model model) {
        var directions = directionService.findAllDirections();
        model.addAttribute("directions", directions);
        return "directionList";
    }


    @GetMapping("/create")
    public String getCreateDirection() {
        return "createDirection";
    }

    @PostMapping("/create")
    public String postCreateDirection(@RequestParam(name="name") String name) {
        directionService.createDirection(new DirectionModel(name));
        return "redirect:/direction";
    }

    @GetMapping("/{id}/edit")
    public String getEditDirection(@PathVariable UUID id, Model model) {
        var direction = directionService.findDirectionById(id);
        model.addAttribute("direction", direction);
        return "editDirection";
    }

    @PostMapping("/{id}/edit")
    public String postEditDirection(
            @PathVariable UUID id,
            @RequestParam(name="name") String name
    ) {
        var direction = new DirectionModel(name);
        direction.setId(id);
        directionService.updateDirection(direction);
        return "redirect:/direction";
    }

    @PostMapping("/{id}/delete")
    public String deleteDirection(@PathVariable UUID id) {
        directionService.deleteDirection(id);
        return "redirect:/direction";
    }

    @GetMapping("/{id}/groups")
    public String getGroupsByDirection(@PathVariable UUID id, Model model) {
        var direction = directionService.findDirectionById(id);
        var groups = groupService.findGroupsByDirectionId(id);
        model.addAttribute("directions", direction);
        model.addAttribute("groups", groups);
        return "groupsByDirection";
    }
}
