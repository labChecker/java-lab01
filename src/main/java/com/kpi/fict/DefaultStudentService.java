package com.kpi.fict;
import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;


import java.util.List;

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
                .filter(s -> s.getExams().size() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam() {
        double avg = studentRepository
                .findAll()
                .stream()
                .flatMap(s -> s.getExams().stream())
                .filter(e -> e.getType() == Exam.Type.MATH)
                .mapToDouble(Exam::getScore)
                .average()
                .orElse(0.);
        return studentRepository
                .findAll()
                .stream()
                .filter(s ->
                        s
                                .getExams()
                                .stream()
                                .filter(e -> e.getType() == Exam.Type.MATH)
                                .findFirst()
                                .orElse(new Exam(Exam.Type.MATH, 0))
                                .getScore() > avg &&
                                s
                                        .getExams()
                                        .stream()
                                        .anyMatch(e -> e.getType() == Exam.Type.ENGLISH))
                .collect(Collectors.toList());
    }

    //Delimiter: ','
    @Override
    public List<String> getExamSumAndRatingForEachStudent() {
        return studentRepository.findAll().stream().map(student ->
                String.join(
                        ",",
                        student.getExams().stream().map(exam -> exam.getScore()).reduce(0.0, (a, v) -> a + v).toString(),
                        Double.toString(student.getRating()),
                        student.getName()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public Student findStudentWithMaxAvgExamRating() {
        return studentRepository
                .findAll()
                .stream()
                .max((a, b) -> {
                    double r1 = a
                            .getExams()
                            .stream()
                            .mapToDouble(Exam::getScore)
                            .average()
                            .orElse(0.);
                    double r2 = b
                            .getExams()
                            .stream()
                            .mapToDouble(Exam::getScore)
                            .average()
                            .orElse(0.);
                    return Double.compare(r1, r2);
                })
                .orElse(studentRepository.findAll().get(0));
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return this.studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getExams().size() > 0)
                .filter(student -> student.getRating() >= 11)
                .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
