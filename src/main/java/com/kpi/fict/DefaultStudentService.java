package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findFirstWithoutMath() {
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student
                        .getExams()
                        .stream()
                        .noneMatch(exam -> exam.getType() == Exam.Type.MATH))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Student> findStudentsWhoTakeMathAndOneMoreExam() {
        return studentRepository.findAll().stream().filter(student ->
                student
                        .getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType() == Exam.Type.MATH)
                        &&
                        student
                                .getExams()
                                .size() == 2
        ).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam() {
        double avgRating = studentRepository
                .findAll()
                .stream()
                .mapToDouble(Student::getRating)
                .average()
                .orElse(0.0);

        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getRating() > avgRating && s
                        .getExams()
                        .stream()
                        .anyMatch(e -> e.getType() == Exam.Type.MATH))
                .collect(Collectors.toList());
    }

    @Override
    public double findAvgRatingOfMathExam() {
        return studentRepository
                .findAll()
                .stream()
                .map(Student::getExams)
                .flatMap(Collection::stream)
                .filter(e -> e.getType() == Exam.Type.MATH)
                .mapToDouble(Exam::getScore)
                .average()
                .orElse(0.0);
    }

    
}
