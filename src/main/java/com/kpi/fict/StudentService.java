package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllWithRatingLessThan300();

    List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore();

    Student findStudentWithMaxEngRating();

    List<Student> findStudentsWithTwoExams();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();
}
