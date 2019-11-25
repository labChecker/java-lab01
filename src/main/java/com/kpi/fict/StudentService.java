package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findStudentWithMaxAvgExamRating();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findTwoStudentsWithMaxEngRating();

    List<String> getExamSumAndRatingForEachStudent();

    Student findFirstWithoutMath();
}
