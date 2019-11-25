package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore();

    double findAvgRatingOfMathExam();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();
}
