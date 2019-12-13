package com.kpi.fict;

import com.kpi.fict.entities.Student;
import com.kpi.fict.entities.Exam;
import com.kpi.fict.repositories.StudentRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        return this.studentRepository.findAll().stream().filter(student -> (long) student.getExams().size() == 2).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return this.studentRepository.findAll().stream().filter(student -> student.getRating() >= 11 && student.getExams().stream().anyMatch(exam -> exam.getType() == Exam.Type.ENGLISH)).collect(Collectors.toList());
    }

    @Override
    public List<Student> findTwoStudentsWithMaxEngRating() {
        return this.studentRepository.findAll().stream().filter(student -> (long) student.getExams().size() > 0).sorted(Comparator.comparing(stud -> stud.getExams().stream().filter(exam -> exam.getType() == Exam.Type.ENGLISH).mapToDouble(Exam::getScore).toArray()[0]))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
            })).stream().limit(2).collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));//.collect(Collectors.toList());
    }

    @Override
    public List<String> calculateAvgRatingForEachStudent() {
        return this.studentRepository.findAll().stream().filter(student -> (long) student.getExams().size() > 0).map(student -> student.getRating() + ", " + (student.getExams().stream().mapToDouble(Exam::getScore).reduce(0, Double::sum) / student.getExams().size())).collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllWithRatingLessThan300() {
        return this.studentRepository.findAll().stream().filter(student ->
                student.getExams().stream().mapToDouble(Exam::getScore).reduce(0, Double::sum) < 300).collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
