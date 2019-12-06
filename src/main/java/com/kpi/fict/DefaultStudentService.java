package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsWithoutExams() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .filter(student -> student.getExams().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> (student.getExams().stream()
                        .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                        .findAny()
                        .isPresent())
                        && (student.getRating() >= 11))
                .collect(Collectors.toList());
    }
    @Override
    public List<Student> findTwoStudentsWithMaxEngRating() {

         List<Double> engRating = studentRepository.findAll()
                 .stream()
                 .flatMap(student -> student.getExams().stream())
                 .filter(exam -> exam.getType() == Exam.Type.ENGLISH)
                 .mapToDouble(Exam::getScore)
                 .sorted()
                 .boxed()
                 .collect(Collectors.toList());
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getExams().stream()
                        .anyMatch(exam ->
                                (exam.getScore() == engRating.get(1))
                                        ||(exam.getScore() == engRating.get(2))
                        )
                )
                .collect(Collectors.toList());

    }

    @Override
    public List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore() {
//        return studentRepository.findAll()
//                .stream()
//                .filter( student ->
//                        (student.getExams()
//                                .stream()
//                                .anyMatch(exam -> (exam.getType() == Exam.Type.ENGLISH)
//                                    && (exam.getScore() >= 180)))
//                        && (student.getExams()
//                                .stream()
//                                .anyMatch(exam -> (exam.getType() == Exam.Type.MATH)
//                                    && (exam.getScore() >= 180)))
//                )
//                .collect(Collectors.toList());
        throw new UnsupportedOperationException("Need to make implementation");
    }

    //Delimiter: ','
    @Override
    public List<String> getExamSumAndRatingForEachStudent() {
        return studentRepository.findAll()
                .stream()
                .map(student ->
                        (student.getExams().stream().mapToDouble(Exam::getScore).sum())
                                + student.getRating()
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
