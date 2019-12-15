package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findFirstWithoutMath();

    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();

    double findAvgRatingOfMathExam();

    List<String> calculateAvgRatingForEachStudent();
}
