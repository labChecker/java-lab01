package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

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
        return studentRepository.findAll().stream().filter(student -> student.getExams().size() == 2).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam() {
        List<Student> students = studentRepository.findAll();
        List<Exam> mathExams = students
            .stream()
            .map(student -> 
                student.getExams().stream().filter(exam -> exam.getType() == Exam.Type.MATH).findFirst()
            )
            .filter(exam -> exam.isPresent())
            .map(exam -> exam.get())
            .collect(Collectors.toList());

        List<Double> mathScores = mathExams.stream().map(exam -> exam.getScore()).collect(Collectors.toList());

        Double mathAvg = mathScores.stream().reduce(0.0, (acc, val) -> acc + val / mathScores.size());

        return students.stream().filter(student -> 
            student
                .getExams()
                .stream()
                .filter(exam -> exam.getType() == Exam.Type.MATH)
                .findAny()
                .orElse(new Exam(Exam.Type.MATH, 0))
                .getScore()
                > mathAvg
            &&
            student
                .getExams()
                .stream()
                .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                .findAny()
                .isPresent()
        ).collect(Collectors.toList());
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
    public List<Student> findTwoStudentsWithMaxEngRating() {
        return studentRepository
            .findAll()
            .stream()
            .filter(student ->
                student
                    .getExams()
                    .stream()
                    .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                    .findAny()
                    .isPresent()
            )
            .sorted(Comparator.comparingDouble(student -> 
                student
                    .getExams()
                    .stream()
                    .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                    .findAny()
                    .orElse(new Exam(Exam.Type.ENGLISH, 0))
                    .getScore()
            ))
            .limit(2)
            .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return studentRepository
            .findAll()
            .stream()
            .filter(student ->
                student
                    .getExams()
                    .stream()
                    .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                    .findAny()
                    .isPresent()
                &&
                student.getRating() >= 11
            )
            .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
