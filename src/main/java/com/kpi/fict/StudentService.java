package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithoutExams();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();

    List<String> getExamSumAndRatingForEachStudent();

    Student findStudentWithMaxAvgExamRating();

    List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore();
}
