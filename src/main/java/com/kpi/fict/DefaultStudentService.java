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
    public Student findFirstWithoutMath() {
        return this.studentRepository
                .findAll()
                .stream()
                .filter(student ->
                        student.getExams()
                                .stream()
                                .noneMatch(exam -> exam.getType() == Exam.Type.MATH)
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore() {
        return this.studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getExams().size() == 2)
                .filter(student ->
                        student.getExams()
                                .stream()
                                .allMatch(exam -> exam.getScore() > 180)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Student findStudentWithMaxEngRating() {
        return this.studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getExams()
                                            .stream()
                                            .anyMatch(exam -> exam.getType() == Exam.Type.ENGLISH)
                )
                .max((left, right) -> (int) Math.round(
                        left.getExams()
                                .stream()
                                .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                                .findAny()
                                .orElse(null)
                                .getScore() - right.getExams()
                                                        .stream()
                                                        .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                                                        .findAny()
                                                        .orElse(null)
                                                        .getScore()
                        )
                )
                .orElse(null);
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return this.studentRepository
                    .findAll()
                    .stream()
                    .filter(student -> student.getRating() >= 11)
                    .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        return this.studentRepository
                    .findAll()
                    .stream()
                    .filter(student -> student.getExams().size() == 2)
                    .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
