package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllWithRatingLessThan300() {
        return studentRepository
        .findAll()
        .stream()
        .filter(s -> s
                .getExams()
                .stream()
                .map(Exam::getScore)
                .reduce(0.0, Double::sum) < 300)
        .collect(Collectors.toList());
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
    public List<Student> findStudentsWithoutExams() {
        return studentRepository
        .findAll()
        .stream()
        .filter(s -> s.getExams().size() == 0)
        .collect(Collectors.toList());
    }

    //Delimiter: ','
    @Override
    public List<String> calculateAvgRatingForEachStudent() {
        return studentRepository
        .findAll()
        .stream()
        .map(s -> s.getName() + "," + s
                .getExams()
                .stream()
                .mapToDouble(Exam::getScore)
                .average()
                .orElse(0.0))
        .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
