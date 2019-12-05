package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
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

    @Override
    public List<Student> findStudentsWithoutExams() {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getExams().size() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
