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
    public List<Student> findStudentsWhoTakeMathAndOneMoreExam() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().size() >= 2 &&
                        student.getExams().stream().anyMatch(exam -> exam.getType().equals(Exam.Type.MATH)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getRating() >= 11)
                .filter(student -> student.getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType().equals(Exam.Type.ENGLISH)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore() {
        return studentRepository.findAll().stream()
                .filter(student -> student
                        .getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType().equals(Exam.Type.MATH) && exam.getScore() >= 180))
                .filter(student -> student
                        .getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType().equals(Exam.Type.ENGLISH) && exam.getScore() >= 180))
                .collect(Collectors.toList());
    }

    @Override
    public double findAvgRatingOfMathExam() {
        return studentRepository.findAll()
                .stream()
                .flatMap(student -> student.getExams().stream())
                .filter(exam -> exam.getType().equals(Exam.Type.MATH))
                .mapToDouble(Exam::getScore)
                .average()
                .getAsDouble();
    }

    @Override
    public List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam() {
        double avgRatingOfMathExam = findAvgRatingOfMathExam();

        return studentRepository.findAll().stream()
                .filter(student -> student.getExams()
                        .stream()
                        .anyMatch(exam -> (exam.getType().equals(Exam.Type.MATH) && exam.getScore() > avgRatingOfMathExam))
                        && student.getExams().stream().anyMatch(exam -> exam.getType().equals(Exam.Type.ENGLISH)))
                .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
