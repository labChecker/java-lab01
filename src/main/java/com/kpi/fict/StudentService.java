package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithTwoExams();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findTwoStudentsWithMaxEngRating();

    List<String> calculateAvgRatingForEachStudent();

    List<Student> findAllWithRatingLessThan300();
}
