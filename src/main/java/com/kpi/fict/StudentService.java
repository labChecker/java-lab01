package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithoutExams();

    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();

    Student findStudentWithMaxAvgExamRating();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();
}
