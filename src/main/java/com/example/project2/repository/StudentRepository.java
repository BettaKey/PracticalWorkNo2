package com.example.project2.repository;

import com.example.project2.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
public class StudentRepository {
    private final List<StudentModel> STUDENTS = new ArrayList<>();

    public List<StudentModel> findAllStudent() {
        return STUDENTS;
    }

    public StudentModel findStudentById(UUID id) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public StudentModel createStudent(StudentModel student) {
        STUDENTS.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student) {
        int studentIndex = IntStream.range(0, STUDENTS.size())
                .filter(index -> STUDENTS.get(index).getId().equals(student.getId()))
                .findFirst()
                .orElse(-1);
        if (studentIndex != -1) {
            STUDENTS.set(studentIndex, student);
            return student;
        }
        return null;
    }

    public void deleteStudent(UUID id) {
        StudentModel student = findStudentById(id);
        if (student != null) {
            STUDENTS.remove(student);
        }
    }
}