package com.example.project2.controller;

import com.example.project2.model.GroupModel;
import com.example.project2.model.StudentModel;
import com.example.project2.service.GroupService;
import com.example.project2.service.InMemoryStudentImpl;
import com.example.project2.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getStudentAll(Model model) {
        List<StudentModel> students = studentService.findAllStudent();
        List<GroupModel> groups = groupService.findAllGroups();
        model.addAttribute("students", students);
        model.addAttribute("groups", groups);
        return "index";
    }

    @GetMapping("/create")
    public String getCreateStudent(Model model) {
        List<GroupModel> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "createStudent";
    }

    @PostMapping("/create")
    public String postCreateStudent(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, @RequestParam(name = "groupId") UUID groupId
    ) {
        studentService.createStudent(new StudentModel(name, email, password, groupId));
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String getEditStudent(@PathVariable UUID id, Model model) {
        StudentModel student = studentService.findStudentById(id);
        if (student == null) {
            return "redirect:/student";
        }
        List<GroupModel> groups = groupService.findAllGroups();
        model.addAttribute("student", student);
        model.addAttribute("groups", groups);
        return "editStudent";
    }

    @PostMapping("/{id}/edit")
    public String postEditStudent(@PathVariable UUID id, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam UUID groupId
    ) {
        StudentModel student = studentService.findStudentById(id);
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