package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithTwoExams();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();

    List<String> getExamSumAndRatingForEachStudent();

    List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore();

    List<Student> findTwoStudentsWithMaxEngRating();
}
