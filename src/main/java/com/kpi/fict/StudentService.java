package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    String findStudentWithMaxAvgExamRating();

    double findAvgRatingOfMathExam();

    List<String> calculateAvgRatingForEachStudent();

    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();
}
