package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithoutExams();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findTwoStudentsWithMaxEngRating();

    List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore();

    List<String> getExamSumAndRatingForEachStudent();
}
