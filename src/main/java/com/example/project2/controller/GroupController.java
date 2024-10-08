package com.example.project2.controller;

import com.example.project2.model.GroupModel;
import com.example.project2.model.StudentModel;
import com.example.project2.service.DirectionService;
import com.example.project2.service.GroupService;
import com.example.project2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final DirectionService directionService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, DirectionService directionService, StudentService studentService) {
        this.groupService = groupService;
        this.directionService = directionService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getGroupAll(Model model) {
        List<GroupModel> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        model.addAttribute("directions", directionService.findAllDirections());
        model.addAttribute("directionService", directionService);
        return "groupList";
    }

    @GetMapping("/create")
    public String getCreateGroup(Model model) {
        model.addAttribute("directions", directionService.findAllDirections());
        return "createGroup";
    }

    @PostMapping("/create")
    public String postCreateGroup(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "directionId") UUID directionId // ID
    ) {
        groupService.createGroup(new GroupModel(name, directionId));
        return "redirect:/group";
    }

    @GetMapping("/{id}/edit")
    public String getEditGroup(@PathVariable UUID id, Model model) {
        GroupModel group = groupService.findGroupById(id);
        if (group == null) {
            return "redirect:/group";
        }
        model.addAttribute("group", group);
        model.addAttribute("directions", directionService.findAllDirections());
        return "editGroup";
    }

    @PostMapping("/{id}/edit")
    public String postEditGroup(
            @PathVariable UUID id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "directionId") UUID directionId
    ) {
        GroupModel group = new GroupModel(name, directionId);
        group.setId(id);
        groupService.updateGroup(group);
        return "redirect:/group";
    }

    @PostMapping("/{id}/delete")
    public String deleteGroup(@PathVariable UUID id) {
        List<StudentModel> studentsInGroup = studentService.findStudentsByGroupId(id);
        for (StudentModel student : studentsInGroup) {
            student.setGroupId(null);
            studentService.updateStudent(student);
        }
        groupService.deleteGroup(id);
        return "redirect:/group";
    }

    @GetMapping("/{id}/students")
    public String getStudentsByGroup(@PathVariable UUID id, Model model) {
        List<StudentModel> students = studentService.findStudentsByGroupId(id);
        model.addAttribute("students", students);
        return "studentsByGroup";
    }
}