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
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getExams().size() <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public Student findFirstWithoutMath() {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s
                        .getExams()
                        .stream()
                        .noneMatch(e -> e.getType() == Exam.Type.MATH))
                .findFirst()
                .get();
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
                        .reduce(Double::sum)
                        .get() < 300)
                .collect(Collectors.toList());
    }

    @Override
    public Student findStudentWithMaxAvgExamRating() {
        return studentRepository
                .findAll()
                .stream().min((a, b) -> {
                    double av1 = a
                            .getExams()
                            .stream()
                            .map(Exam::getScore)
                            .reduce(Double::sum).get() / a.getExams().size();
                    double av2 = b
                            .getExams()
                            .stream()
                            .map(Exam::getScore)
                            .reduce(Double::sum).get() / b.getExams().size();
                    return Double.compare(av2, av1);
                })
                .get();
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getExams().size() == 2)
                .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
