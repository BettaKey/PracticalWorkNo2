package com.example.project2.service;

import com.example.project2.model.StudentModel;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentModel> findAllStudent();
    StudentModel findStudentById(UUID id);
    StudentModel createStudent(StudentModel student);
    StudentModel updateStudent(StudentModel student);
    void deleteStudent(UUID id);
    List<StudentModel> findStudentsByGroupId(UUID groupId);
}
