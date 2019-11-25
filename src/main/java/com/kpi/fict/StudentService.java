package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllWithRatingLessThan300();

    double findAvgRatingOfMathExam();

    List<String> calculateAvgRatingForEachStudent();

    List<Student> findStudentsWithoutExams();

    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();
}
