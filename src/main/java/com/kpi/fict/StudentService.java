package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findFirstWithoutMath();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();

    Student findStudentWithMaxEngRating();

    List<String> getExamSumAndRatingForEachStudent();
}
