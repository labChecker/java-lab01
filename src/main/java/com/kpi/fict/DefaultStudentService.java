package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.*;
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
                .orElseThrow(NoSuchFieldError::new);
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student
                        .getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType() == Exam.Type.ENGLISH)
                        &&
                        student.getRating() >= 11)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam() {
        OptionalDouble average = studentRepository
                .findAll()
                .stream()
                .map(Student::getExams)
                .flatMap(Collection::stream)
                .filter(exam -> exam.getType() == Exam.Type.MATH)
                .mapToDouble(Exam::getScore)
                .average();

        double avg = average.orElseThrow(NoSuchFieldError::new);


        return studentRepository.findAll()
                .stream()
                .filter(student ->

                        student.getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType() == Exam.Type.ENGLISH)
                &&
                        student.getExams()
                        .stream()
                        .anyMatch(exam -> exam.getType() == Exam.Type.MATH
                                &&
                                exam.getScore() > avg))
                .collect(Collectors.toList());
    }

    @Override
    public Student findStudentWithMaxEngRating() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getExams().stream().anyMatch(exam -> exam.getType() == Exam.Type.ENGLISH))
                .max((student, t1) -> {
                    Optional<Exam> score1 = student.getExams().stream().filter(exam -> exam.getType() == Exam.Type.ENGLISH).findFirst();
                    Optional<Exam> score2 = t1.getExams().stream().filter(exam -> exam.getType() == Exam.Type.ENGLISH).findFirst();

                    return (int) (score1.get().getScore() - score2.get().getScore());
                }).orElseThrow(NoSuchElementException::new);
    }

    //Delimiter: ','
    @Override
    public List<String> getExamSumAndRatingForEachStudent() {
        return studentRepository.findAll().stream()
                .map(student -> student
                        .getExams()
                        .stream()
                        .mapToDouble(Exam::getScore)
                        .sum()
                        + ","
                        + student.getRating()
                        + ","
                        + student.getName())
                .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
