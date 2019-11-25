package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findFirstWithoutMath();

    List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore();

    Student findStudentWithMaxEngRating();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();

    List<Student> findStudentsWithTwoExams();
}
