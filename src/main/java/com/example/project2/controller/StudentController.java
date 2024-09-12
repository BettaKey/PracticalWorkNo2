package com.example.project2.controller;

import com.example.project2.model.StudentModel;
import com.example.project2.model.GroupModel;
import com.example.project2.service.InMemoryStudentImpl;
import com.example.project2.service.StudentService;
import com.example.project2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(InMemoryStudentImpl studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getStudentAll(Model model) {
        var students = studentService.findAllStudent();
        model.addAttribute("students", students);
        model.addAttribute("groupService", groupService);
        return "index";
    }


    @GetMapping("/create")
    public String getCreateStudent(Model model) {
        var groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "createStudent";
    }

    @PostMapping("/create")
    public String postCreateStudent(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "groupId") UUID groupId
    ) {
        studentService.createStudent(new StudentModel(name, email, password, groupId));
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String getEditStudent(@PathVariable UUID id, Model model) {
        var student = studentService.findStudentById(id);
        if (student == null) {
            return "redirect:/student";
        }
        var groups = groupService.findAllGroups();
        model.addAttribute("student", student);
        model.addAttribute("groups", groups);
        return "editStudent";
    }

    @PostMapping("/{id}/edit")
    public String postEditStudent(
            @PathVariable UUID id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam UUID groupId
    ) {
        var student = studentService.findStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setPassword(password);
            student.setGroupId(groupId);
            studentService.updateStudent(student);
        }
        return "redirect:/student";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
}
