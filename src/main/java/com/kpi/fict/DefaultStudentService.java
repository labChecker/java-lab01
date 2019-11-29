package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsWithoutExams() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public Student findFirstWithoutMath() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().indexOf(Exam.Type.MATH) == -1)
                .findFirst()
                .get();
    }

    @Override
    public List<Student> findAllWithRatingLessThan300() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getRating() < 300)
                .collect(Collectors.toList());
    }

    @Override
    public Student findStudentWithMaxAvgExamRating() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
